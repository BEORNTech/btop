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

import java.util.List;

import javax.jms.Message;

import com.beorn.onlinepayment.model.PaymentMethod;
import com.beorn.onlinepayment.model.Transaction;
import com.beorn.onlinepayment.service.PaymentMethodLocalServiceUtil;
import com.beorn.onlinepayment.service.TransactionLocalServiceUtil;
import com.beorn.onlinepayment.util.JSONUtil;
import com.beorn.paymentapi.messaging.messageprocessor.MessageContext;
import com.beorn.paymentapi.messaging.messageprocessor.MessageProcessor;

/**
 * Handles retrieving a list of valid payment methods for a transaction
 * 
 * @author Sébastien Meunier
 */
public class GetPaymentMethodsMessageProcessor implements MessageProcessor {

	public void processMessage(Message message, MessageContext messageContext) throws Exception {
		String applicationId = message.getStringProperty("applicationId");
		long transactionId = message.getLongProperty("transactionId");
		String countryCode = message.getStringProperty("countryCode");

		// Handle request

		Transaction transaction = TransactionLocalServiceUtil.getTransaction(transactionId);

		List<PaymentMethod> paymentMethods = PaymentMethodLocalServiceUtil.getTransactionAvailablePaymentMethods(
				transaction, countryCode);

		// Send response

		Message response = messageContext.createResponse(message);
		response.setStringProperty("methods", JSONUtil.paymentMethodsToJSON(paymentMethods).toString());

		messageContext.sendResponse(message, response);
	}

}
