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

import com.beorn.onlinepayment.model.PaymentPluginConfig;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing PaymentPluginConfig in entity cache.
 *
 * @author Sebastien Meunier
 * @see PaymentPluginConfig
 * @generated
 */
public class PaymentPluginConfigCacheModel implements CacheModel<PaymentPluginConfig>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", paymentPluginConfigId=");
		sb.append(paymentPluginConfigId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", sellerId=");
		sb.append(sellerId);
		sb.append(", paymentPluginId=");
		sb.append(paymentPluginId);
		sb.append(", config=");
		sb.append(config);
		sb.append(", configured=");
		sb.append(configured);
		sb.append(", defaultPlugin=");
		sb.append(defaultPlugin);
		sb.append("}");

		return sb.toString();
	}

	public PaymentPluginConfig toEntityModel() {
		PaymentPluginConfigImpl paymentPluginConfigImpl = new PaymentPluginConfigImpl();

		if (uuid == null) {
			paymentPluginConfigImpl.setUuid(StringPool.BLANK);
		}
		else {
			paymentPluginConfigImpl.setUuid(uuid);
		}

		paymentPluginConfigImpl.setCompanyId(companyId);
		paymentPluginConfigImpl.setGroupId(groupId);
		paymentPluginConfigImpl.setUserId(userId);
		paymentPluginConfigImpl.setPaymentPluginConfigId(paymentPluginConfigId);

		if (createDate == Long.MIN_VALUE) {
			paymentPluginConfigImpl.setCreateDate(null);
		}
		else {
			paymentPluginConfigImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			paymentPluginConfigImpl.setModifiedDate(null);
		}
		else {
			paymentPluginConfigImpl.setModifiedDate(new Date(modifiedDate));
		}

		paymentPluginConfigImpl.setSellerId(sellerId);
		paymentPluginConfigImpl.setPaymentPluginId(paymentPluginId);

		if (config == null) {
			paymentPluginConfigImpl.setConfig(StringPool.BLANK);
		}
		else {
			paymentPluginConfigImpl.setConfig(config);
		}

		paymentPluginConfigImpl.setConfigured(configured);
		paymentPluginConfigImpl.setDefaultPlugin(defaultPlugin);

		paymentPluginConfigImpl.resetOriginalValues();

		return paymentPluginConfigImpl;
	}

	public String uuid;
	public long companyId;
	public long groupId;
	public long userId;
	public long paymentPluginConfigId;
	public long createDate;
	public long modifiedDate;
	public long sellerId;
	public long paymentPluginId;
	public String config;
	public boolean configured;
	public boolean defaultPlugin;
}