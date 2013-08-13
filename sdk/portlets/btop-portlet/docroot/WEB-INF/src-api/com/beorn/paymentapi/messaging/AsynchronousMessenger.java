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

import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import com.beorn.paymentapi.util.MessageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * A messenger that does not expect an answer to a message
 * 
 * @author Sébastien Meunier
 */
public class AsynchronousMessenger {

	/**
	 * @param session
	 *            the jms session used by the parent sender
	 * @param destinationName
	 *            the destination to which this sender will send messages
	 * @throws JMSException
	 */
	public AsynchronousMessenger(Session session, String destinationName) throws JMSException {
		_session = session;
		_destinationName = destinationName;

		_requestDestination = _session.createQueue(_destinationName);
		_requestProducer = _session.createProducer(_requestDestination);
		_requestProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
	}

	/**
	 * Sends a message to this messenger's destination
	 * 
	 * @param requestMessage
	 *            the message to send
	 * @throws JMSException
	 */
	public void send(Message requestMessage) throws JMSException {
		_log.debug("sending message - " + MessageUtil.messagePropertiesToString(requestMessage));
		_requestProducer.send(requestMessage);
	}

	/**
	 * Clear resources used by this messenger. Do not call this. It will be closed by the message
	 * sender that created it.
	 * 
	 * @throws JMSException
	 */
	public void close() throws JMSException {
		_requestProducer.close();
	}

	private Session _session;
	private String _destinationName;

	private Destination _requestDestination;
	private MessageProducer _requestProducer;

	private static Log _log = LogFactoryUtil.getLog(AsynchronousMessenger.class);
}