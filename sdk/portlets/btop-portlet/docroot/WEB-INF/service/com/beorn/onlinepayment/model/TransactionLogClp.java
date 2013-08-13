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

import com.beorn.onlinepayment.service.TransactionLogLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sebastien Meunier
 */
public class TransactionLogClp extends BaseModelImpl<TransactionLog>
	implements TransactionLog {
	public TransactionLogClp() {
	}

	public Class<?> getModelClass() {
		return TransactionLog.class;
	}

	public String getModelClassName() {
		return TransactionLog.class.getName();
	}

	public long getPrimaryKey() {
		return _transactionLogId;
	}

	public void setPrimaryKey(long primaryKey) {
		setTransactionLogId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_transactionLogId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("transactionLogId", getTransactionLogId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("transactionId", getTransactionId());
		attributes.put("paymentApplicationId", getPaymentApplicationId());
		attributes.put("remoteId", getRemoteId());
		attributes.put("amount", getAmount());
		attributes.put("type", getType());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long transactionLogId = (Long)attributes.get("transactionLogId");

		if (transactionLogId != null) {
			setTransactionLogId(transactionLogId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long transactionId = (Long)attributes.get("transactionId");

		if (transactionId != null) {
			setTransactionId(transactionId);
		}

		String paymentApplicationId = (String)attributes.get(
				"paymentApplicationId");

		if (paymentApplicationId != null) {
			setPaymentApplicationId(paymentApplicationId);
		}

		String remoteId = (String)attributes.get("remoteId");

		if (remoteId != null) {
			setRemoteId(remoteId);
		}

		Double amount = (Double)attributes.get("amount");

		if (amount != null) {
			setAmount(amount);
		}

		Long type = (Long)attributes.get("type");

		if (type != null) {
			setType(type);
		}
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public long getTransactionLogId() {
		return _transactionLogId;
	}

	public void setTransactionLogId(long transactionLogId) {
		_transactionLogId = transactionLogId;
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

	public long getTransactionId() {
		return _transactionId;
	}

	public void setTransactionId(long transactionId) {
		_transactionId = transactionId;
	}

	public String getPaymentApplicationId() {
		return _paymentApplicationId;
	}

	public void setPaymentApplicationId(String paymentApplicationId) {
		_paymentApplicationId = paymentApplicationId;
	}

	public String getRemoteId() {
		return _remoteId;
	}

	public void setRemoteId(String remoteId) {
		_remoteId = remoteId;
	}

	public double getAmount() {
		return _amount;
	}

	public void setAmount(double amount) {
		_amount = amount;
	}

	public long getType() {
		return _type;
	}

	public void setType(long type) {
		_type = type;
	}

	public BaseModel<?> getTransactionLogRemoteModel() {
		return _transactionLogRemoteModel;
	}

	public void setTransactionLogRemoteModel(
		BaseModel<?> transactionLogRemoteModel) {
		_transactionLogRemoteModel = transactionLogRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			TransactionLogLocalServiceUtil.addTransactionLog(this);
		}
		else {
			TransactionLogLocalServiceUtil.updateTransactionLog(this);
		}
	}

	@Override
	public TransactionLog toEscapedModel() {
		return (TransactionLog)Proxy.newProxyInstance(TransactionLog.class.getClassLoader(),
			new Class[] { TransactionLog.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		TransactionLogClp clone = new TransactionLogClp();

		clone.setUuid(getUuid());
		clone.setCompanyId(getCompanyId());
		clone.setGroupId(getGroupId());
		clone.setUserId(getUserId());
		clone.setTransactionLogId(getTransactionLogId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setTransactionId(getTransactionId());
		clone.setPaymentApplicationId(getPaymentApplicationId());
		clone.setRemoteId(getRemoteId());
		clone.setAmount(getAmount());
		clone.setType(getType());

		return clone;
	}

	public int compareTo(TransactionLog transactionLog) {
		int value = 0;

		value = DateUtil.compareTo(getModifiedDate(),
				transactionLog.getModifiedDate());

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

		TransactionLogClp transactionLog = null;

		try {
			transactionLog = (TransactionLogClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = transactionLog.getPrimaryKey();

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
		StringBundler sb = new StringBundler(25);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", transactionLogId=");
		sb.append(getTransactionLogId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", transactionId=");
		sb.append(getTransactionId());
		sb.append(", paymentApplicationId=");
		sb.append(getPaymentApplicationId());
		sb.append(", remoteId=");
		sb.append(getRemoteId());
		sb.append(", amount=");
		sb.append(getAmount());
		sb.append(", type=");
		sb.append(getType());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(40);

		sb.append("<model><model-name>");
		sb.append("com.beorn.onlinepayment.model.TransactionLog");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>transactionLogId</column-name><column-value><![CDATA[");
		sb.append(getTransactionLogId());
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
			"<column><column-name>transactionId</column-name><column-value><![CDATA[");
		sb.append(getTransactionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>paymentApplicationId</column-name><column-value><![CDATA[");
		sb.append(getPaymentApplicationId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>remoteId</column-name><column-value><![CDATA[");
		sb.append(getRemoteId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>amount</column-name><column-value><![CDATA[");
		sb.append(getAmount());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _uuid;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userUuid;
	private long _transactionLogId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _transactionId;
	private String _paymentApplicationId;
	private String _remoteId;
	private double _amount;
	private long _type;
	private BaseModel<?> _transactionLogRemoteModel;
}