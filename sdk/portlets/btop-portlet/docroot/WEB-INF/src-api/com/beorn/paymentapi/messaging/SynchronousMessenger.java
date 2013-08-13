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

import java.util.UUID;

import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TemporaryQueue;

import com.beorn.paymentapi.messaging.exception.NoResponseException;
import com.beorn.paymentapi.messaging.exception.RemoteException;
import com.beorn.paymentapi.util.MessageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * A messenger expecting an answer to a message
 * 
 * @author Sébastien Meunier
 */
public class SynchronousMessenger {

	/**
	 * @param session
	 *            the jms session used by the parent sender
	 * @param destinationName
	 *            the destination to which this sender will send messages
	 * @throws JMSException
	 */
	public SynchronousMessenger(Session session, String destinationName) throws JMSException {
		_session = session;
		_destinationName = destinationName;

		_requestDestination = _session.createQueue(_destinationName);
		_requestProducer = _session.createProducer(_requestDestination);
		_requestProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
	}

	/**
	 * Sends a message to this messenger's destination and wait for a response for 10 seconds (see
	 * _DEFAULT_TIMEOUT)
	 * 
	 * @param requestMessage
	 *            the message to send
	 * @return the response message
	 * @throws JMSException
	 * @throws NoResponseException
	 *             thrown when the timeout is reached
	 * @throws RemoteException
	 *             thrown when the message caused an exception on the listener's side
	 */
	public Message send(Message requestMessage) throws JMSException, RemoteException, NoResponseException {
		return send(requestMessage, _DEFAULT_TIMEOUT);
	}

	/**
	 * Sends a message to this messenger's destination and wait for a response
	 * 
	 * @param requestMessage
	 *            the message to send
	 * @param timeout
	 *            how long to wait in milliseconds
	 * @return the response message
	 * @throws JMSException
	 * @throws NoResponseException
	 *             thrown when the timeout is reached
	 * @throws RemoteException
	 *             thrown when the message caused an exception on the listener's side
	 */
	public Message send(Message requestMessage, long timeout) throws JMSException, RemoteException, NoResponseException {
		_log.debug("sending message - " + MessageUtil.messagePropertiesToString(requestMessage));

		final String correlationId = UUID.randomUUID().toString();
		requestMessage.setJMSCorrelationID(correlationId);

		Message response;
		TemporaryQueue responseDestination = _session.createTemporaryQueue();
		try {
			requestMessage.setJMSReplyTo(responseDestination);

			MessageConsumer responseConsumer = _session.createConsumer(responseDestination);
			try {
				_requestProducer.send(requestMessage);
				response = responseConsumer.receive(timeout);

				if (response != null)
					response.acknowledge();

			} finally {
				responseConsumer.close();
			}

		} finally {
			responseDestination.delete();
		}

		checkResponse(response, correlationId);

		return response;
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

	/**
	 * Checks a message for exceptions, throwing a PortalException if one is found. Will also throw
	 * a PortalException if there is no message (no response caused by a timeout)
	 * 
	 * @param response
	 *            the message to check for exception
	 * @param correlationId
	 * @throws JMSException
	 * @throws NoResponseException
	 */
	protected void checkResponse(Message response, String correlationId) throws JMSException, RemoteException,
			NoResponseException {

		if (response == null)
			throw new NoResponseException("No response from destination " + _destinationName);

		if (!response.getJMSCorrelationID().equals(correlationId)) {
			throw new JMSException("Wrong correlation id : expected " + correlationId + ", got "
					+ response.getJMSCorrelationID());
		}

		if (response.propertyExists("exceptionClass")) {
			throw new RemoteException("Remote listener threw exception:\n" + response.getStringProperty("exceptionClass")
					+ ": " + response.getStringProperty("exceptionMessage"));
		}
	}

	private Session _session;
	private String _destinationName;

	private Destination _requestDestination;
	private MessageProducer _requestProducer;

	private static final long _DEFAULT_TIMEOUT = 10000;

	private static Log _log = LogFactoryUtil.getLog(SynchronousMessenger.class);
}