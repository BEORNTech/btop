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

package com.beorn.paymentpluginapi.messaging.messageprocessor;

import java.util.Map;

import javax.jms.Message;

import com.beorn.paymentapi.messaging.messageprocessor.MessageContext;
import com.beorn.paymentapi.messaging.messageprocessor.MessageProcessor;
import com.beorn.paymentapi.model.ApiTransaction;
import com.beorn.paymentpluginapi.exception.NoMethodHandlerException;
import com.beorn.paymentpluginapi.method.MethodHandler;
import com.beorn.paymentpluginapi.util.PaymentPluginUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * Process payment url requests for a transaction via method handlers (see MethodHandler)
 * 
 * @author Sébastien Meunier
 */

public class GetPaymentUrlMessageProcessor implements MessageProcessor {

	public void processMessage(Message message, MessageContext messageContext) throws Exception {
		if (_methodHandlers == null)
			initializeMethodHandlers(messageContext);

		String transactionJSON = message.getStringProperty("transaction");
		String paymentMethodKey = message.getStringProperty("paymentMethodKey");
		String languageId = message.getStringProperty("languageId");
		String backUrl = message.getStringProperty("backUrl");
		String successUrl = message.getStringProperty("successUrl");
		String errorUrl = message.getStringProperty("errorUrl");

		ApiTransaction transaction = ApiTransaction.fromJSONObject(transactionJSON);

		// Handle request

		MethodHandler methodHandler = _methodHandlers.get(paymentMethodKey);

		if (methodHandler == null) {
			throw new NoMethodHandlerException("Cannot find method key '" + paymentMethodKey + "' in "
					+ _methodHandlers.keySet());
		}

		String paymentUrl = methodHandler.getPaymentUrl(transaction, languageId, backUrl, successUrl, errorUrl);

		// Send response

		Message response = messageContext.createResponse(message);
		response.setStringProperty("paymentUrl", paymentUrl);

		messageContext.sendResponse(message, response);
	}

	private void initializeMethodHandlers(MessageContext messageContext) throws PortalException, SystemException {
		_methodHandlers = PaymentPluginUtil.getPluginMethodHandlers(messageContext.getServletContext());
	}

	private Map<String, MethodHandler> _methodHandlers;
}
