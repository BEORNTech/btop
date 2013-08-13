/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.beorn.onlinepayment.model.impl;

import com.beorn.onlinepayment.model.PaymentPlugin;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing PaymentPlugin in entity cache.
 *
 * @author Sebastien Meunier
 * @see PaymentPlugin
 * @generated
 */
public class PaymentPluginCacheModel implements CacheModel<PaymentPlugin>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", paymentPluginId=");
		sb.append(paymentPluginId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", applicationId=");
		sb.append(applicationId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", pluginConfigParameters=");
		sb.append(pluginConfigParameters);
		sb.append(", sellerConfigParameters=");
		sb.append(sellerConfigParameters);
		sb.append(", pluginConfig=");
		sb.append(pluginConfig);
		sb.append(", configured=");
		sb.append(configured);
		sb.append("}");

		return sb.toString();
	}

	public PaymentPlugin toEntityModel() {
		PaymentPluginImpl paymentPluginImpl = new PaymentPluginImpl();

		if (uuid == null) {
			paymentPluginImpl.setUuid(StringPool.BLANK);
		}
		else {
			paymentPluginImpl.setUuid(uuid);
		}

		paymentPluginImpl.setCompanyId(companyId);
		paymentPluginImpl.setGroupId(groupId);
		paymentPluginImpl.setUserId(userId);
		paymentPluginImpl.setPaymentPluginId(paymentPluginId);

		if (createDate == Long.MIN_VALUE) {
			paymentPluginImpl.setCreateDate(null);
		}
		else {
			paymentPluginImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			paymentPluginImpl.setModifiedDate(null);
		}
		else {
			paymentPluginImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (applicationId == null) {
			paymentPluginImpl.setApplicationId(StringPool.BLANK);
		}
		else {
			paymentPluginImpl.setApplicationId(applicationId);
		}

		if (name == null) {
			paymentPluginImpl.setName(StringPool.BLANK);
		}
		else {
			paymentPluginImpl.setName(name);
		}

		if (pluginConfigParameters == null) {
			paymentPluginImpl.setPluginConfigParameters(StringPool.BLANK);
		}
		else {
			paymentPluginImpl.setPluginConfigParameters(pluginConfigParameters);
		}

		if (sellerConfigParameters == null) {
			paymentPluginImpl.setSellerConfigParameters(StringPool.BLANK);
		}
		else {
			paymentPluginImpl.setSellerConfigParameters(sellerConfigParameters);
		}

		if (pluginConfig == null) {
			paymentPluginImpl.setPluginConfig(StringPool.BLANK);
		}
		else {
			paymentPluginImpl.setPluginConfig(pluginConfig);
		}

		paymentPluginImpl.setConfigured(configured);

		paymentPluginImpl.resetOriginalValues();

		return paymentPluginImpl;
	}

	public String uuid;
	public long companyId;
	public long groupId;
	public long userId;
	public long paymentPluginId;
	public long createDate;
	public long modifiedDate;
	public String applicationId;
	public String name;
	public String pluginConfigParameters;
	public String sellerConfigParameters;
	public String pluginConfig;
	public boolean configured;
}