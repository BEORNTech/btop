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

package com.beorn.onlinepayment.model;

import com.beorn.onlinepayment.service.OrderTransactionLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sebastien Meunier
 */
public class OrderTransactionClp extends BaseModelImpl<OrderTransaction>
	implements OrderTransaction {
	public OrderTransactionClp() {
	}

	public Class<?> getModelClass() {
		return OrderTransaction.class;
	}

	public String getModelClassName() {
		return OrderTransaction.class.getName();
	}

	public long getPrimaryKey() {
		return _orderId;
	}

	public void setPrimaryKey(long primaryKey) {
		setOrderId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_orderId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("orderId", getOrderId());
		attributes.put("transactionId", getTransactionId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long orderId = (Long)attributes.get("orderId");

		if (orderId != null) {
			setOrderId(orderId);
		}

		Long transactionId = (Long)attributes.get("transactionId");

		if (transactionId != null) {
			setTransactionId(transactionId);
		}
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getOrderId() {
		return _orderId;
	}

	public void setOrderId(long orderId) {
		_orderId = orderId;
	}

	public long getTransactionId() {
		return _transactionId;
	}

	public void setTransactionId(long transactionId) {
		_transactionId = transactionId;
	}

	public BaseModel<?> getOrderTransactionRemoteModel() {
		return _orderTransactionRemoteModel;
	}

	public void setOrderTransactionRemoteModel(
		BaseModel<?> orderTransactionRemoteModel) {
		_orderTransactionRemoteModel = orderTransactionRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			OrderTransactionLocalServiceUtil.addOrderTransaction(this);
		}
		else {
			OrderTransactionLocalServiceUtil.updateOrderTransaction(this);
		}
	}

	@Override
	public OrderTransaction toEscapedModel() {
		return (OrderTransaction)Proxy.newProxyInstance(OrderTransaction.class.getClassLoader(),
			new Class[] { OrderTransaction.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		OrderTransactionClp clone = new OrderTransactionClp();

		clone.setUuid(getUuid());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setOrderId(getOrderId());
		clone.setTransactionId(getTransactionId());

		return clone;
	}

	public int compareTo(OrderTransaction orderTransaction) {
		int value = 0;

		if (getOrderId() < orderTransaction.getOrderId()) {
			value = -1;
		}
		else if (getOrderId() > orderTransaction.getOrderId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		OrderTransactionClp orderTransaction = null;

		try {
			orderTransaction = (OrderTransactionClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = orderTransaction.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", orderId=");
		sb.append(getOrderId());
		sb.append(", transactionId=");
		sb.append(getTransactionId());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("com.beorn.onlinepayment.model.OrderTransaction");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>orderId</column-name><column-value><![CDATA[");
		sb.append(getOrderId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>transactionId</column-name><column-value><![CDATA[");
		sb.append(getTransactionId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _uuid;
	private Date _createDate;
	private Date _modifiedDate;
	private long _orderId;
	private long _transactionId;
	private BaseModel<?> _orderTransactionRemoteModel;
}