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

import javax.jms.Message;

/**
 * A message processor handles messages received by a message listener
 * 
 * @author Sébastien Meunier
 */
public interface MessageProcessor {

	/**
	 * @param message
	 *            the message received
	 * @param messageContext
	 *            a context for that message
	 * @throws Exception
	 *             if an exception is thrown, a exception response will be send as a response to the
	 *             received message
	 */
	void processMessage(Message message, MessageContext messageContext) throws Exception;

}
