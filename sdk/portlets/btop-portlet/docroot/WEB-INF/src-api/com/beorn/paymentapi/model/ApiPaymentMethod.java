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
import java.util.Locale;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;

/**
 * Represent a payment method as seen by a shop or payment plugin
 * 
 * @author Sébastien Meunier
 */
public class ApiPaymentMethod {

	public ApiPaymentMethod(long paymentMethodId, String key, String name) {
		_paymentMethodId = paymentMethodId;
		_key = key;
		_name = name;
	}

	public long getPaymentMethodId() {
		return _paymentMethodId;
	}

	public String getKey() {
		return _key;
	}

	public String getName() {
		return _name;
	}

	public String getName(Locale locale) {
		return LocalizationUtil.getLocalization(_name, LocaleUtil.toLanguageId(locale));
	}

	public static List<ApiPaymentMethod> fromJSONArray(String json) throws JSONException {
		return fromJSON(JSONFactoryUtil.createJSONArray(json));
	}

	public static ApiPaymentMethod fromJSONObject(String json) throws JSONException {
		return fromJSON(JSONFactoryUtil.createJSONObject(json));
	}

	public static List<ApiPaymentMethod> fromJSON(JSONArray jsonArray) {
		List<ApiPaymentMethod> results = new ArrayList<ApiPaymentMethod>(jsonArray.length());

		for (int i = 0; i < jsonArray.length(); ++i) {
			results.add(fromJSON(jsonArray.getJSONObject(i)));
		}
		return results;
	}

	public static ApiPaymentMethod fromJSON(JSONObject jsonObject) {
		return new ApiPaymentMethod(jsonObject.getLong("paymentMethodId"), jsonObject.getString("key"),
				jsonObject.getString("name"));
	}

	private long _paymentMethodId;
	private String _key;
	private String _name;

}
