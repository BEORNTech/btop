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

import javax.jms.JMSException;
import javax.jms.Message;
import javax.servlet.ServletContext;

import com.beorn.paymentapi.model.ApiTransaction;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * Sends messages that are can be sent by both shop and payment plugins
 * 
 * @author Sébastien Meunier
 */
public abstract class CommonMessageSender extends BaseMessageSender {

	/**
	 * @param servletContext
	 *            the servlet context of the webapp that created this message listener
	 * @param destinationName
	 *            the default destination that this sender sends to
	 */
	public CommonMessageSender(ServletContext servletContext, String defaultDestinationName) {
		super(servletContext, defaultDestinationName);
	}

	/**
	 * @param servletContext
	 *            the servlet context of the webapp that created this message listener
	 */
	public CommonMessageSender(ServletContext servletContext) {
		super(servletContext);
	}

	/**
	 * Retrieves a transaction by its transaction id
	 * 
	 * @param transactionId
	 *            the id of the transaction to retrieve
	 * @return a transaction matching the transaction id
	 * @throws PortalException
	 * @throws SystemException
	 */
	public ApiTransaction getTransaction(long transactionId) throws PortalException, SystemException {
		try {
			ServletContext servletContext = getServletContext();

			Message message = getSession().createMessage();
			message.setStringProperty("method", "getTransaction");
			message.setStringProperty("applicationId", servletContext.getServletContextName());
			message.setLongProperty("transactionId", transactionId);

			Message response = getSynchronousMessenger().send(message);

			return ApiTransaction.fromJSONObject(response.getStringProperty("transaction"));

		} catch (JMSException e) {
			throw new SystemException(e);
		}
	}
}