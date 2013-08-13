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

package com.beorn.onlinepayment.messaging.messageprocessor;

import javax.jms.Message;

import com.beorn.onlinepayment.model.Transaction;
import com.beorn.onlinepayment.service.TransactionLocalServiceUtil;
import com.beorn.onlinepayment.util.ServiceContextUtil;
import com.beorn.paymentapi.messaging.messageprocessor.MessageContext;
import com.beorn.paymentapi.messaging.messageprocessor.MessageProcessor;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.service.ServiceContext;

/**
 * Handles messages sent when a payment plugin notifies the payment system of a
 * payment.
 * 
 * @author Sébastien Meunier
 */
public class AddPaymentMessageProcessor implements MessageProcessor {

	public void processMessage(Message message, MessageContext messageContext)
			throws Exception {

		String applicationId = message.getStringProperty("applicationId");
		long transactionId = message.getLongProperty("transactionId");
		String remoteId = message.getStringProperty("remoteId");
		double amountPaid = message.getDoubleProperty("amountPaid");

		ServiceContext serviceContext = ServiceContextUtil
				.getDefaultServiceContext();

		Transaction transaction = TransactionLocalServiceUtil.addPayment(
				transactionId, remoteId, applicationId, amountPaid,
				serviceContext);

		if (_log.isDebugEnabled()) {
			_log.debug(amountPaid + " " + transaction.getCurrencyCode()
					+ " paid to transaction " + transactionId + " from plugin "
					+ applicationId + " (remoteId is " + remoteId + ")");
		}
	}

	private static Log _log = LogFactoryUtil
			.getLog(AddPaymentMessageProcessor.class);
}
