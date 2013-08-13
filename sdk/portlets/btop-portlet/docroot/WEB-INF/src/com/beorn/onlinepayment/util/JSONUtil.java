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

package com.beorn.onlinepayment.util;

import java.util.List;

import com.beorn.onlinepayment.model.PaymentMethod;
import com.beorn.onlinepayment.model.Transaction;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

/**
 * @author Sébastien Meunier
 */
public class JSONUtil {

	public static JSONArray paymentMethodsToJSON(List<PaymentMethod> paymentMethods) {
		JSONArray results = JSONFactoryUtil.createJSONArray();
		for (PaymentMethod paymentMethod : paymentMethods) {
			results.put(paymentMethodToJSON(paymentMethod));
		}
		return results;
	}

	public static JSONObject paymentMethodToJSON(PaymentMethod paymentMethod) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		result.put("paymentMethodId", paymentMethod.getPaymentMethodId());
		result.put("key", paymentMethod.getKey());
		result.put("name", paymentMethod.getName());
		return result;
	}

	public static JSONArray transactionsToJSON(List<Transaction> transactions) {
		JSONArray results = JSONFactoryUtil.createJSONArray();
		for (Transaction transaction : transactions) {
			results.put(transactionToJSON(transaction));
		}
		return results;
	}

	public static JSONObject transactionToJSON(Transaction transaction) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		result.put("transactionId", transaction.getTransactionId());
		result.put("sellerId", transaction.getSellerId());
		result.put("amount", transaction.getAmount());
		result.put("currencyCode", transaction.getCurrencyCode());
		result.put("status", transaction.getStatus());
		return result;
	}
}
