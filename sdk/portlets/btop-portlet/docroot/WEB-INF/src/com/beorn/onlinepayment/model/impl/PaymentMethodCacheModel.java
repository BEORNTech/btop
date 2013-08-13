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

import com.beorn.onlinepayment.model.PaymentMethod;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing PaymentMethod in entity cache.
 *
 * @author Sebastien Meunier
 * @see PaymentMethod
 * @generated
 */
public class PaymentMethodCacheModel implements CacheModel<PaymentMethod>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", paymentMethodId=");
		sb.append(paymentMethodId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", key=");
		sb.append(key);
		sb.append(", name=");
		sb.append(name);
		sb.append("}");

		return sb.toString();
	}

	public PaymentMethod toEntityModel() {
		PaymentMethodImpl paymentMethodImpl = new PaymentMethodImpl();

		if (uuid == null) {
			paymentMethodImpl.setUuid(StringPool.BLANK);
		}
		else {
			paymentMethodImpl.setUuid(uuid);
		}

		paymentMethodImpl.setCompanyId(companyId);
		paymentMethodImpl.setGroupId(groupId);
		paymentMethodImpl.setUserId(userId);
		paymentMethodImpl.setPaymentMethodId(paymentMethodId);

		if (createDate == Long.MIN_VALUE) {
			paymentMethodImpl.setCreateDate(null);
		}
		else {
			paymentMethodImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			paymentMethodImpl.setModifiedDate(null);
		}
		else {
			paymentMethodImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (key == null) {
			paymentMethodImpl.setKey(StringPool.BLANK);
		}
		else {
			paymentMethodImpl.setKey(key);
		}

		if (name == null) {
			paymentMethodImpl.setName(StringPool.BLANK);
		}
		else {
			paymentMethodImpl.setName(name);
		}

		paymentMethodImpl.resetOriginalValues();

		return paymentMethodImpl;
	}

	public String uuid;
	public long companyId;
	public long groupId;
	public long userId;
	public long paymentMethodId;
	public long createDate;
	public long modifiedDate;
	public String key;
	public String name;
}