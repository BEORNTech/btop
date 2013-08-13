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

import com.beorn.onlinepayment.model.TransactionLog;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing TransactionLog in entity cache.
 *
 * @author Sebastien Meunier
 * @see TransactionLog
 * @generated
 */
public class TransactionLogCacheModel implements CacheModel<TransactionLog>,
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
		sb.append(", transactionLogId=");
		sb.append(transactionLogId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", transactionId=");
		sb.append(transactionId);
		sb.append(", paymentApplicationId=");
		sb.append(paymentApplicationId);
		sb.append(", remoteId=");
		sb.append(remoteId);
		sb.append(", amount=");
		sb.append(amount);
		sb.append(", type=");
		sb.append(type);
		sb.append("}");

		return sb.toString();
	}

	public TransactionLog toEntityModel() {
		TransactionLogImpl transactionLogImpl = new TransactionLogImpl();

		if (uuid == null) {
			transactionLogImpl.setUuid(StringPool.BLANK);
		}
		else {
			transactionLogImpl.setUuid(uuid);
		}

		transactionLogImpl.setCompanyId(companyId);
		transactionLogImpl.setGroupId(groupId);
		transactionLogImpl.setUserId(userId);
		transactionLogImpl.setTransactionLogId(transactionLogId);

		if (createDate == Long.MIN_VALUE) {
			transactionLogImpl.setCreateDate(null);
		}
		else {
			transactionLogImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			transactionLogImpl.setModifiedDate(null);
		}
		else {
			transactionLogImpl.setModifiedDate(new Date(modifiedDate));
		}

		transactionLogImpl.setTransactionId(transactionId);

		if (paymentApplicationId == null) {
			transactionLogImpl.setPaymentApplicationId(StringPool.BLANK);
		}
		else {
			transactionLogImpl.setPaymentApplicationId(paymentApplicationId);
		}

		if (remoteId == null) {
			transactionLogImpl.setRemoteId(StringPool.BLANK);
		}
		else {
			transactionLogImpl.setRemoteId(remoteId);
		}

		transactionLogImpl.setAmount(amount);
		transactionLogImpl.setType(type);

		transactionLogImpl.resetOriginalValues();

		return transactionLogImpl;
	}

	public String uuid;
	public long companyId;
	public long groupId;
	public long userId;
	public long transactionLogId;
	public long createDate;
	public long modifiedDate;
	public long transactionId;
	public String paymentApplicationId;
	public String remoteId;
	public double amount;
	public long type;
}