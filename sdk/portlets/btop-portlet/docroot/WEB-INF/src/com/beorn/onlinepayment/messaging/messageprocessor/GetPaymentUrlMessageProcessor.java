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

import com.beorn.onlinepayment.NoSuchPluginException;
import com.beorn.onlinepayment.messaging.PaymentSystemPluginSender;
import com.beorn.onlinepayment.model.PaymentMethod;
import com.beorn.onlinepayment.model.PaymentPlugin;
import com.beorn.onlinepayment.service.PaymentMethodLocalServiceUtil;
import com.beorn.onlinepayment.service.PaymentPluginLocalServiceUtil;
import com.beorn.onlinepayment.util.PaymentSystemUtil;
import com.beorn.paymentapi.messaging.messageprocessor.MessageContext;
import com.beorn.paymentapi.messaging.messageprocessor.MessageProcessor;

/**
 * Handles retrieving the urls used by an user to pay a transaction
 * 
 * @author Sébastien Meunier
 */
public class GetPaymentUrlMessageProcessor implements MessageProcessor {

	public void processMessage(Message message, MessageContext messageContext)
			throws Exception {
		long transactionId = message.getLongProperty("transactionId");
		long paymentMethodId = message.getLongProperty("paymentMethodId");
		String languageId = message.getStringProperty("languageId");
		String countryCode = message.getStringProperty("countryCode");
		String backUrl = message.getStringProperty("backUrl");
		String successUrl = message.getStringProperty("successUrl");
		String errorUrl = message.getStringProperty("errorUrl");

		PaymentMethod paymentMethod = PaymentMethodLocalServiceUtil
				.getPaymentMethod(paymentMethodId);

		// Find available plugins for the transaction and payment method

		List<PaymentPlugin> paymentPlugins = PaymentPluginLocalServiceUtil
				.getAvailablePaymentPlugins(transactionId, paymentMethodId,
						countryCode);

		if (paymentPlugins.isEmpty()) {
			throw new NoSuchPluginException(
					"There is no available plugin for transaction "
							+ transactionId + " and method " + paymentMethodId);
		}

		// Delegate the request to the first available plugin (they are sorted
		// by priority)
		// TODO fallback to the next plugin if the first one fails

		PaymentSystemPluginSender paymentSystemPluginSender = PaymentSystemUtil
				.getMessagingContext().getPaymentSystemPluginSender();

		PaymentPlugin paymentPlugin = paymentPlugins.get(0);

		String paymentUrl = paymentSystemPluginSender.getPaymentUrl(
				paymentPlugin.getApplicationId(), transactionId,
				paymentMethod.getKey(), languageId, backUrl, successUrl,
				errorUrl);

		// Send response

		Message response = messageContext.createResponse(message);
		response.setStringProperty("paymentUrl", paymentUrl);

		messageContext.sendResponse(message, response);
	}
}
