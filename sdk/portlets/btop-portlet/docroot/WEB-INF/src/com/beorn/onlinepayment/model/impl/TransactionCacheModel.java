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

import com.beorn.onlinepayment.model.Transaction;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing Transaction in entity cache.
 *
 * @author Sebastien Meunier
 * @see Transaction
 * @generated
 */
public class TransactionCacheModel implements CacheModel<Transaction>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", transactionId=");
		sb.append(transactionId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", applicationId=");
		sb.append(applicationId);
		sb.append(", sellerId=");
		sb.append(sellerId);
		sb.append(", amount=");
		sb.append(amount);
		sb.append(", currencyCode=");
		sb.append(currencyCode);
		sb.append(", status=");
		sb.append(status);
		sb.append(", paymentApplicationId=");
		sb.append(paymentApplicationId);
		sb.append(", amountPaid=");
		sb.append(amountPaid);
		sb.append(", amountRefunded=");
		sb.append(amountRefunded);
		sb.append("}");

		return sb.toString();
	}

	public Transaction toEntityModel() {
		TransactionImpl transactionImpl = new TransactionImpl();

		if (uuid == null) {
			transactionImpl.setUuid(StringPool.BLANK);
		}
		else {
			transactionImpl.setUuid(uuid);
		}

		transactionImpl.setCompanyId(companyId);
		transactionImpl.setGroupId(groupId);
		transactionImpl.setUserId(userId);
		transactionImpl.setTransactionId(transactionId);

		if (createDate == Long.MIN_VALUE) {
			transactionImpl.setCreateDate(null);
		}
		else {
			transactionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			transactionImpl.setModifiedDate(null);
		}
		else {
			transactionImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (applicationId == null) {
			transactionImpl.setApplicationId(StringPool.BLANK);
		}
		else {
			transactionImpl.setApplicationId(applicationId);
		}

		transactionImpl.setSellerId(sellerId);
		transactionImpl.setAmount(amount);

		if (currencyCode == null) {
			transactionImpl.setCurrencyCode(StringPool.BLANK);
		}
		else {
			transactionImpl.setCurrencyCode(currencyCode);
		}

		transactionImpl.setStatus(status);

		if (paymentApplicationId == null) {
			transactionImpl.setPaymentApplicationId(StringPool.BLANK);
		}
		else {
			transactionImpl.setPaymentApplicationId(paymentApplicationId);
		}

		transactionImpl.setAmountPaid(amountPaid);
		transactionImpl.setAmountRefunded(amountRefunded);

		transactionImpl.resetOriginalValues();

		return transactionImpl;
	}

	public String uuid;
	public long companyId;
	public long groupId;
	public long userId;
	public long transactionId;
	public long createDate;
	public long modifiedDate;
	public String applicationId;
	public long sellerId;
	public double amount;
	public String currencyCode;
	public long status;
	public String paymentApplicationId;
	public double amountPaid;
	public double amountRefunded;
}