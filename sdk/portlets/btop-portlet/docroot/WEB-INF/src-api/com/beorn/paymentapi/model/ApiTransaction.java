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

package com.beorn.paymentapi.model;

import java.util.ArrayList;
import java.util.List;

import com.beorn.paymentapi.util.TransactionStatus;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

/**
 * Represent a transaction as seen by a shop or payment plugin
 * 
 * @author Sébastien Meunier
 */
public class ApiTransaction {

	public ApiTransaction(long transactionId, long sellerId, double amount, String currencyCode, long status) {
		_transactionId = transactionId;
		_sellerId = sellerId;
		_amount = amount;
		_currencyCode = currencyCode;
		_status = status;
	}

	public long getTransactionId() {
		return _transactionId;
	}

	public long getSellerId() {
		return _sellerId;
	}

	public double getAmount() {
		return _amount;
	}

	/**
	 * @return a ISO 4217 currency code if this transaction
	 */
	public String getCurrencyCode() {
		return _currencyCode;
	}

	/**
	 * @see TransactionStatus
	 * @return
	 */
	public long getStatus() {
		return _status;
	}

	public static List<ApiTransaction> fromJSONArray(String json) throws JSONException {
		return fromJSON(JSONFactoryUtil.createJSONArray(json));
	}

	public static ApiTransaction fromJSONObject(String json) throws JSONException {
		return fromJSON(JSONFactoryUtil.createJSONObject(json));
	}

	public static List<ApiTransaction> fromJSON(JSONArray jsonArray) {
		List<ApiTransaction> results = new ArrayList<ApiTransaction>(jsonArray.length());

		for (int i = 0; i < jsonArray.length(); ++i) {
			results.add(fromJSON(jsonArray.getJSONObject(i)));
		}
		return results;
	}

	public static ApiTransaction fromJSON(JSONObject jsonObject) {
		return new ApiTransaction(jsonObject.getLong("transactionId"), jsonObject.getLong("sellerId"),
				jsonObject.getDouble("amount"), jsonObject.getString("currencyCode"), jsonObject.getLong("status"));
	}

	private long _transactionId;
	private long _sellerId;
	private double _amount;
	private String _currencyCode;
	private long _status;
}
