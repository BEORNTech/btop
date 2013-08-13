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

package com.beorn.onlinepayment.messaging;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.servlet.ServletContext;

import com.beorn.onlinepayment.model.Transaction;
import com.beorn.onlinepayment.service.TransactionLocalServiceUtil;
import com.beorn.onlinepayment.util.JSONUtil;
import com.beorn.paymentapi.messaging.BaseMessageSender;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * Sends messages to shop plugins
 * 
 * @author Sébastien Meunier
 */
public class PaymentSystemAppSender extends BaseMessageSender {

	public PaymentSystemAppSender(ServletContext servletContext) {
		super(servletContext);
	}

	public void updateTransaction(long transactionId) throws PortalException,
			SystemException {
		try {
			Transaction transaction = TransactionLocalServiceUtil
					.getTransaction(transactionId);

			Message message = createMessage();
			message.setStringProperty("method", "updateTransaction");
			message.setStringProperty("transaction", JSONUtil
					.transactionToJSON(transaction).toString());

			getAppAsynchronousMessenger(transaction.getApplicationId()).send(
					message);

		} catch (JMSException e) {
			throw new SystemException(e);
		}
	}
}
