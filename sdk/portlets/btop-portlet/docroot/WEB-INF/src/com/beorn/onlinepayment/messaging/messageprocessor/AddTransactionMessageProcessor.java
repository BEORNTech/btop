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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.jms.Message;

import org.json.JSONObject;

import com.beorn.onlinepayment.model.Seller;
import com.beorn.onlinepayment.model.Transaction;
import com.beorn.onlinepayment.service.SellerLocalServiceUtil;
import com.beorn.onlinepayment.service.TransactionLocalServiceUtil;
import com.beorn.onlinepayment.util.ServiceContextUtil;
import com.beorn.paymentapi.messaging.messageprocessor.MessageContext;
import com.beorn.paymentapi.messaging.messageprocessor.MessageProcessor;
import com.liferay.portal.service.ServiceContext;

/**
 * Handles transaction creation messages from shop plugins
 * 
 * @author Sébastien Meunier
 */
public class AddTransactionMessageProcessor implements MessageProcessor {

	public void processMessage(Message message, MessageContext messageContext)
			throws Exception {

		String applicationId = message.getStringProperty("applicationId");
		long sellerId = message.getLongProperty("sellerId");
		double amount = message.getDoubleProperty("amount");
		String currencyCode = message.getStringProperty("currency");
		Map<String, String> parameters = null;

		if (message.propertyExists("parameters")) {
			JSONObject parametersObject = new JSONObject(
					message.getStringProperty("parameters"));
			parameters = new HashMap<String, String>(parametersObject.length());

			Iterator<String> keyIterator = parametersObject.keys();
			while (keyIterator.hasNext()) {
				String key = keyIterator.next();
				parameters.put(key, parametersObject.getString(key));
			}
		}

		// Handle request

		ServiceContext serviceContext = ServiceContextUtil
				.getDefaultServiceContext();

		Seller seller;
		if (sellerId == 0) {
			seller = SellerLocalServiceUtil.getDefaultSeller(serviceContext);
			sellerId = seller.getSellerId();

		} else {
			seller = SellerLocalServiceUtil.getSeller(sellerId);
		}

		Transaction transaction = TransactionLocalServiceUtil.addTransaction(
				seller.getUserId(), applicationId, sellerId, amount,
				currencyCode, parameters, serviceContext);

		// Send response

		Message response = messageContext.createResponse(message);
		response.setLongProperty("transactionId",
				transaction.getTransactionId());

		messageContext.sendResponse(message, response);
	}
}
