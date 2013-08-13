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

import com.beorn.onlinepayment.model.OrderTransaction;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing OrderTransaction in entity cache.
 *
 * @author Sebastien Meunier
 * @see OrderTransaction
 * @generated
 */
public class OrderTransactionCacheModel implements CacheModel<OrderTransaction>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", orderId=");
		sb.append(orderId);
		sb.append(", transactionId=");
		sb.append(transactionId);
		sb.append("}");

		return sb.toString();
	}

	public OrderTransaction toEntityModel() {
		OrderTransactionImpl orderTransactionImpl = new OrderTransactionImpl();

		if (uuid == null) {
			orderTransactionImpl.setUuid(StringPool.BLANK);
		}
		else {
			orderTransactionImpl.setUuid(uuid);
		}

		if (createDate == Long.MIN_VALUE) {
			orderTransactionImpl.setCreateDate(null);
		}
		else {
			orderTransactionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			orderTransactionImpl.setModifiedDate(null);
		}
		else {
			orderTransactionImpl.setModifiedDate(new Date(modifiedDate));
		}

		orderTransactionImpl.setOrderId(orderId);
		orderTransactionImpl.setTransactionId(transactionId);

		orderTransactionImpl.resetOriginalValues();

		return orderTransactionImpl;
	}

	public String uuid;
	public long createDate;
	public long modifiedDate;
	public long orderId;
	public long transactionId;
}