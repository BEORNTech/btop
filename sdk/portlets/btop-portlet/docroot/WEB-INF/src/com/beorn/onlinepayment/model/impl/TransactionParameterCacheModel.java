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

import com.beorn.onlinepayment.model.TransactionParameter;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing TransactionParameter in entity cache.
 *
 * @author Sebastien Meunier
 * @see TransactionParameter
 * @generated
 */
public class TransactionParameterCacheModel implements CacheModel<TransactionParameter>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", transactionParameterId=");
		sb.append(transactionParameterId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", transactionId=");
		sb.append(transactionId);
		sb.append(", key=");
		sb.append(key);
		sb.append(", value=");
		sb.append(value);
		sb.append("}");

		return sb.toString();
	}

	public TransactionParameter toEntityModel() {
		TransactionParameterImpl transactionParameterImpl = new TransactionParameterImpl();

		if (uuid == null) {
			transactionParameterImpl.setUuid(StringPool.BLANK);
		}
		else {
			transactionParameterImpl.setUuid(uuid);
		}

		transactionParameterImpl.setCompanyId(companyId);
		transactionParameterImpl.setGroupId(groupId);
		transactionParameterImpl.setUserId(userId);
		transactionParameterImpl.setTransactionParameterId(transactionParameterId);

		if (createDate == Long.MIN_VALUE) {
			transactionParameterImpl.setCreateDate(null);
		}
		else {
			transactionParameterImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			transactionParameterImpl.setModifiedDate(null);
		}
		else {
			transactionParameterImpl.setModifiedDate(new Date(modifiedDate));
		}

		transactionParameterImpl.setTransactionId(transactionId);

		if (key == null) {
			transactionParameterImpl.setKey(StringPool.BLANK);
		}
		else {
			transactionParameterImpl.setKey(key);
		}

		if (value == null) {
			transactionParameterImpl.setValue(StringPool.BLANK);
		}
		else {
			transactionParameterImpl.setValue(value);
		}

		transactionParameterImpl.resetOriginalValues();

		return transactionParameterImpl;
	}

	public String uuid;
	public long companyId;
	public long groupId;
	public long userId;
	public long transactionParameterId;
	public long createDate;
	public long modifiedDate;
	public long transactionId;
	public String key;
	public String value;
}