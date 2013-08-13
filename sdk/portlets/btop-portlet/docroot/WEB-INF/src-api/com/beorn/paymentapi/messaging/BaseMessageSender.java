/**
 * Copyright (c) 2007-2013 BEORN Technologies, SARL. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */

package com.beorn.paymentapi.messaging;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.servlet.ServletContext;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.beorn.paymentapi.messaging.exception.NoDefaultDestinationException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * Base class to send messages between plugins
 * 
 * @author Sébastien Meunier
 */
public abstract class BaseMessageSender {

	/**
	 * @param servletContext
	 *            the servlet context of the webapp that created this message listener
	 * @param destinationName
	 *            the default destination that this sender sends to
	 */
	public BaseMessageSender(ServletContext servletContext, String defaultDestinationName) {
		_servletContext = servletContext;
		_defaultDestinationName = defaultDestinationName;
	}

	/**
	 * @param servletContext
	 *            the servlet context of the webapp that created this message listener
	 */
	public BaseMessageSender(ServletContext servletContext) {
		_servletContext = servletContext;
		_defaultDestinationName = null;
	}

	/**
	 * Initialize this message sender. Do not call this; it is called at the webapp's
	 * initialization.
	 * 
	 * @throws JMSException
	 * @throws URISyntaxException
	 */
	public void start() throws JMSException {
		_log.info("Starting message sender for " + _servletContext.getServletContextName());

		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);

		_connection = connectionFactory.createConnection();
		_connection.start();
		_session = _connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
	}

	/**
	 * Clear resources used by this message sender. Do not call this; it is called at the webapp's
	 * un-initialization.
	 * 
	 * @throws JMSException
	 */
	public void stop() throws JMSException {
		for (SynchronousMessenger synchronousMessenger : _synchronousMessengers.values()) {
			synchronousMessenger.close();
		}
		_synchronousMessengers.clear();

		for (AsynchronousMessenger asynchronousMessenger : _asynchronousMessengers.values()) {
			asynchronousMessenger.close();
		}
		_asynchronousMessengers.clear();

		_session.close();
		_connection.close();
	}

	/**
	 * @return create a new message to be send using this sender
	 * @throws JMSException
	 */
	protected Message createMessage() throws JMSException {
		return _session.createMessage();
	}

	/**
	 * Retrieve a synchronous sender to the default destination of the sender
	 * 
	 * @return
	 * @throws JMSException
	 * @throws PortalException
	 */
	protected SynchronousMessenger getSynchronousMessenger() throws JMSException, PortalException {
		if (_defaultDestinationName == null)
			throw new NoDefaultDestinationException();

		return getSynchronousMessenger(_defaultDestinationName);
	}

	/**
	 * Retrieve an asynchronous sender to the default destination of the sender
	 * 
	 * @return
	 * @throws JMSException
	 * @throws PortalException
	 */
	protected AsynchronousMessenger getAsynchronousMessenger() throws JMSException, PortalException {
		if (_defaultDestinationName == null)
			throw new NoDefaultDestinationException();

		return getAsynchronousMessenger(_defaultDestinationName);
	}

	/**
	 * Retrieve a synchronous sender to a payment plugin
	 * 
	 * @param applicationId
	 *            the servlet context name of the target plugin
	 * @return
	 * @throws JMSException
	 */
	protected SynchronousMessenger getPluginSynchronousMessenger(String applicationId) throws JMSException {
		return getSynchronousMessenger(DestinationNames.PAYMENT_PLUGIN_PREFIX + applicationId);
	}

	/**
	 * Retrieve an asynchronous sender to a payment plugin
	 * 
	 * @param applicationId
	 *            the servlet context name of the target plugin
	 * @return
	 * @throws JMSException
	 */
	protected AsynchronousMessenger getPluginAsynchronousMessenger(String applicationId) throws JMSException {
		return getAsynchronousMessenger(DestinationNames.PAYMENT_PLUGIN_PREFIX + applicationId);
	}

	/**
	 * Retrieve a synchronous sender to a shop plugin
	 * 
	 * @param applicationId
	 *            the servlet context name name of the target plugin
	 * @return
	 * @throws JMSException
	 */
	protected SynchronousMessenger getAppSynchronousMessenger(String applicationId) throws JMSException {
		return getSynchronousMessenger(DestinationNames.PAYMENT_APP_PREFIX + applicationId);
	}

	/**
	 * Retrieve an asynchronous sender to a shop plugin
	 * 
	 * @param applicationId
	 *            the name of the target plugin
	 * @return
	 * @throws JMSException
	 */
	protected AsynchronousMessenger getAppAsynchronousMessenger(String applicationId) throws JMSException {
		return getAsynchronousMessenger(DestinationNames.PAYMENT_APP_PREFIX + applicationId);
	}

	/**
	 * Retrieve a synchronous sender to a destination
	 * 
	 * @param destinationName
	 *            the name of the destination
	 * @return
	 * @throws JMSException
	 */
	protected SynchronousMessenger getSynchronousMessenger(String destinationName) throws JMSException {
		SynchronousMessenger synchronousMessenger = _synchronousMessengers.get(destinationName);
		if (synchronousMessenger == null) {
			synchronousMessenger = new SynchronousMessenger(_session, destinationName);
			_synchronousMessengers.put(destinationName, synchronousMessenger);
		}
		return synchronousMessenger;
	}

	/**
	 * Retrieve an asynchronous sender to a destination
	 * 
	 * @param destinationName
	 *            the name of the destination
	 * @return
	 * @throws JMSException
	 */
	protected AsynchronousMessenger getAsynchronousMessenger(String destinationName) throws JMSException {
		AsynchronousMessenger asynchronousMessenger = _asynchronousMessengers.get(destinationName);
		if (asynchronousMessenger == null) {
			asynchronousMessenger = new AsynchronousMessenger(_session, destinationName);
			_asynchronousMessengers.put(destinationName, asynchronousMessenger);
		}
		return asynchronousMessenger;
	}

	/**
	 * @return the jms session used by this sender
	 */
	protected Session getSession() {
		return _session;
	}

	/**
	 * @return the servlet context by which this sender has been created
	 */
	protected ServletContext getServletContext() {
		return _servletContext;
	}

	private ServletContext _servletContext;
	private String _defaultDestinationName;
	private Connection _connection;
	private Session _session;
	private Map<String, SynchronousMessenger> _synchronousMessengers = new HashMap<String, SynchronousMessenger>();
	private Map<String, AsynchronousMessenger> _asynchronousMessengers = new HashMap<String, AsynchronousMessenger>();

	private static Log _log = LogFactoryUtil.getLog(BaseMessageSender.class);

}