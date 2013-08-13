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

package com.beorn.paymentapi.messaging.messageprocessor;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.servlet.ServletContext;

/**
 * The context for a received message
 * 
 * @author Sébastien Meunier
 */
public interface MessageContext {

	/**
	 * Create a response message to the received message
	 * 
	 * @param message
	 *            the received message
	 * @return a response message
	 * @throws JMSException
	 */
	Message createResponse(Message message) throws JMSException;

	/**
	 * Sends the response message
	 * 
	 * @param message
	 *            the received message
	 * @param response
	 *            the response message
	 * @throws JMSException
	 */
	void sendResponse(Message message, Message response) throws JMSException;

	/**
	 * @return the servlet context of the webapp that received the message
	 */
	ServletContext getServletContext();

}
