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
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.servlet.ServletContext;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.beorn.paymentapi.messaging.exception.NoMessageProcessorException;
import com.beorn.paymentapi.messaging.messageprocessor.MessageContext;
import com.beorn.paymentapi.messaging.messageprocessor.MessageProcessor;
import com.beorn.paymentapi.util.MessageUtil;
import com.liferay.portal.kernel.cache.Lifecycle;
import com.liferay.portal.kernel.cache.ThreadLocalCacheManager;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CentralizedThreadLocal;

/**
 * Handles receiving of messages via message processors (see MessageProcessor)
 * 
 * @author Sébastien Meunier
 */
public abstract class BaseMessageListener {

	/**
	 * @param servletContext
	 *            the servlet context of the webapp that created this message listener
	 * @param destinationName
	 *            the destination that this listener listens to
	 */
	public BaseMessageListener(ServletContext servletContext, String destinationName) {
		_servletContext = servletContext;
		_destinationName = destinationName;
		_messageProcessors = new HashMap<String, MessageProcessor>();
	}

	/**
	 * Add a message processor to this message listener. Only one message processor can exist for
	 * each method.
	 * 
	 * @param method
	 *            the message processor will handle messages that have their method property set to
	 *            this value
	 * @param messageProcessor
	 *            the message processor to add
	 */
	public void register(String method, MessageProcessor messageProcessor) {
		_messageProcessors.put(method, messageProcessor);
	}

	/**
	 * Initialize this message listener. Do not call this; it is called at the webapp's
	 * initialization.
	 * 
	 * @throws JMSException
	 * @throws URISyntaxException
	 */
	public void start() throws JMSException {
		_log.info("Starting message listener for " + _servletContext.getServletContextName() + ", destination "
				+ _destinationName);

		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);

		_connection = connectionFactory.createConnection();
		_connection.start();

		_session = _connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

		Destination destination = _session.createQueue(_destinationName);
		MessageConsumer consumer = _session.createConsumer(destination);

		consumer.setMessageListener(new MessageListener() {
			public void onMessage(Message message) {
				// Note : This method is called by one of ActiveMQ's threads
				try {
					processMessage(message);

				} finally {
					// fix for http://jira.beorn.tech/browse/BTOP-94
					ThreadLocalCacheManager.clearAll(Lifecycle.REQUEST);
					CentralizedThreadLocal.clearShortLivedThreadLocals();
				}
			}
		});

		_responseProducer = _session.createProducer(null);
		_responseProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
	}

	/**
	 * Clear resources used by this message listener. Do not call this; it is called at the webapp's
	 * un-initialization.
	 * 
	 * @throws JMSException
	 */
	public void stop() throws JMSException {
		_session.close();
		_connection.close();

		_log.info("Stopped listening to " + _destinationName);
	}

	/**
	 * Route a message so the corresponding message processor can process it
	 * 
	 * @param message
	 */
	protected void processMessage(Message message) {
		try {
			String method = message.getStringProperty("method");
			MessageProcessor messageProcessor = _messageProcessors.get(method);

			if (messageProcessor == null)
				throw new NoMessageProcessorException(method);

			messageProcessor.processMessage(message, _messageContext);

		} catch (Exception e) {
			_log.error("Error while processing message received by " + getServletContext().getServletContextName()
					+ " on destination " + _destinationName, e);
			try {
				sendExceptionResponse(message, e);

			} catch (JMSException e1) {
				_log.error(e1);
			}

		} finally {
			try {
				message.acknowledge();

			} catch (JMSException e) {
				_log.error("Could not acknowledge message", e);
			}
		}
	}

	/**
	 * Creates a message to answer another message. The response message must be sent message with
	 * the sendResponse method.
	 * 
	 * @param message
	 *            the message to answer
	 * @return a response message
	 * @throws JMSException
	 */
	protected Message createResponse(Message message) throws JMSException {
		Message response = getSession().createMessage();
		response.setJMSCorrelationID(message.getJMSCorrelationID());
		return response;
	}

	/**
	 * Sends a response message created with the createResponse method
	 * 
	 * @param toMessage
	 *            the message the response message responds to
	 * @param response
	 *            a response message
	 * @throws JMSException
	 */
	protected void sendResponse(Message toMessage, Message response) throws JMSException {
		Destination destination = toMessage.getJMSReplyTo();

		if (destination == null)
			throw new IllegalArgumentException("Cannot reply to a message that has a null reply-to destination");

		_log.debug("sending response - " + MessageUtil.messagePropertiesToString(response));
		_responseProducer.send(destination, response);
	}

	/**
	 * Sends a message notifying the sender that an exception occurred.
	 * 
	 * No message will be sent if the sender does not expect an answer.
	 * 
	 * @param message
	 *            the message that caused the exception
	 * @param exception
	 *            the exception that occured
	 * @throws JMSException
	 */
	protected void sendExceptionResponse(Message message, Exception exception) throws JMSException {
		if (message.getJMSReplyTo() == null)
			return;

		Message response = createResponse(message);
		response.setStringProperty("exceptionClass", exception.getClass().getName());
		response.setStringProperty("exceptionMessage", exception.getMessage());

		sendResponse(message, response);
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
	private String _destinationName;
	private Connection _connection;
	private Session _session;

	private MessageProducer _responseProducer;
	private Map<String, MessageProcessor> _messageProcessors;

	private MessageContext _messageContext = new MessageContext() {
		public void sendResponse(Message message, Message response) throws JMSException {
			BaseMessageListener.this.sendResponse(message, response);
		}

		public ServletContext getServletContext() {
			return BaseMessageListener.this.getServletContext();
		}

		public Message createResponse(Message message) throws JMSException {
			return BaseMessageListener.this.createResponse(message);
		}
	};

	private static Log _log = LogFactoryUtil.getLog(BaseMessageListener.class);
}
