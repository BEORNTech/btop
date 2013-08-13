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

import com.beorn.onlinepayment.service.TransactionLocalServiceUtil;

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
public class TransactionClp extends BaseModelImpl<Transaction>
	implements Transaction {
	public TransactionClp() {
	}

	public Class<?> getModelClass() {
		return Transaction.class;
	}

	public String getModelClassName() {
		return Transaction.class.getName();
	}

	public long getPrimaryKey() {
		return _transactionId;
	}

	public void setPrimaryKey(long primaryKey) {
		setTransactionId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_transactionId);
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
		attributes.put("transactionId", getTransactionId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("applicationId", getApplicationId());
		attributes.put("sellerId", getSellerId());
		attributes.put("amount", getAmount());
		attributes.put("currencyCode", getCurrencyCode());
		attributes.put("status", getStatus());
		attributes.put("paymentApplicationId", getPaymentApplicationId());
		attributes.put("amountPaid", getAmountPaid());
		attributes.put("amountRefunded", getAmountRefunded());

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

		Long transactionId = (Long)attributes.get("transactionId");

		if (transactionId != null) {
			setTransactionId(transactionId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String applicationId = (String)attributes.get("applicationId");

		if (applicationId != null) {
			setApplicationId(applicationId);
		}

		Long sellerId = (Long)attributes.get("sellerId");

		if (sellerId != null) {
			setSellerId(sellerId);
		}

		Double amount = (Double)attributes.get("amount");

		if (amount != null) {
			setAmount(amount);
		}

		String currencyCode = (String)attributes.get("currencyCode");

		if (currencyCode != null) {
			setCurrencyCode(currencyCode);
		}

		Long status = (Long)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		String paymentApplicationId = (String)attributes.get(
				"paymentApplicationId");

		if (paymentApplicationId != null) {
			setPaymentApplicationId(paymentApplicationId);
		}

		Double amountPaid = (Double)attributes.get("amountPaid");

		if (amountPaid != null) {
			setAmountPaid(amountPaid);
		}

		Double amountRefunded = (Double)attributes.get("amountRefunded");

		if (amountRefunded != null) {
			setAmountRefunded(amountRefunded);
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

	public long getTransactionId() {
		return _transactionId;
	}

	public void setTransactionId(long transactionId) {
		_transactionId = transactionId;
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

	public String getApplicationId() {
		return _applicationId;
	}

	public void setApplicationId(String applicationId) {
		_applicationId = applicationId;
	}

	public long getSellerId() {
		return _sellerId;
	}

	public void setSellerId(long sellerId) {
		_sellerId = sellerId;
	}

	public double getAmount() {
		return _amount;
	}

	public void setAmount(double amount) {
		_amount = amount;
	}

	public String getCurrencyCode() {
		return _currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		_currencyCode = currencyCode;
	}

	public long getStatus() {
		return _status;
	}

	public void setStatus(long status) {
		_status = status;
	}

	public String getPaymentApplicationId() {
		return _paymentApplicationId;
	}

	public void setPaymentApplicationId(String paymentApplicationId) {
		_paymentApplicationId = paymentApplicationId;
	}

	public double getAmountPaid() {
		return _amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		_amountPaid = amountPaid;
	}

	public double getAmountRefunded() {
		return _amountRefunded;
	}

	public void setAmountRefunded(double amountRefunded) {
		_amountRefunded = amountRefunded;
	}

	public BaseModel<?> getTransactionRemoteModel() {
		return _transactionRemoteModel;
	}

	public void setTransactionRemoteModel(BaseModel<?> transactionRemoteModel) {
		_transactionRemoteModel = transactionRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			TransactionLocalServiceUtil.addTransaction(this);
		}
		else {
			TransactionLocalServiceUtil.updateTransaction(this);
		}
	}

	@Override
	public Transaction toEscapedModel() {
		return (Transaction)Proxy.newProxyInstance(Transaction.class.getClassLoader(),
			new Class[] { Transaction.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		TransactionClp clone = new TransactionClp();

		clone.setUuid(getUuid());
		clone.setCompanyId(getCompanyId());
		clone.setGroupId(getGroupId());
		clone.setUserId(getUserId());
		clone.setTransactionId(getTransactionId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setApplicationId(getApplicationId());
		clone.setSellerId(getSellerId());
		clone.setAmount(getAmount());
		clone.setCurrencyCode(getCurrencyCode());
		clone.setStatus(getStatus());
		clone.setPaymentApplicationId(getPaymentApplicationId());
		clone.setAmountPaid(getAmountPaid());
		clone.setAmountRefunded(getAmountRefunded());

		return clone;
	}

	public int compareTo(Transaction transaction) {
		int value = 0;

		value = DateUtil.compareTo(getModifiedDate(),
				transaction.getModifiedDate());

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

		TransactionClp transaction = null;

		try {
			transaction = (TransactionClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = transaction.getPrimaryKey();

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
		StringBundler sb = new StringBundler(31);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", transactionId=");
		sb.append(getTransactionId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", applicationId=");
		sb.append(getApplicationId());
		sb.append(", sellerId=");
		sb.append(getSellerId());
		sb.append(", amount=");
		sb.append(getAmount());
		sb.append(", currencyCode=");
		sb.append(getCurrencyCode());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append(", paymentApplicationId=");
		sb.append(getPaymentApplicationId());
		sb.append(", amountPaid=");
		sb.append(getAmountPaid());
		sb.append(", amountRefunded=");
		sb.append(getAmountRefunded());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(49);

		sb.append("<model><model-name>");
		sb.append("com.beorn.onlinepayment.model.Transaction");
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
			"<column><column-name>transactionId</column-name><column-value><![CDATA[");
		sb.append(getTransactionId());
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
			"<column><column-name>applicationId</column-name><column-value><![CDATA[");
		sb.append(getApplicationId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sellerId</column-name><column-value><![CDATA[");
		sb.append(getSellerId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>amount</column-name><column-value><![CDATA[");
		sb.append(getAmount());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>currencyCode</column-name><column-value><![CDATA[");
		sb.append(getCurrencyCode());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>paymentApplicationId</column-name><column-value><![CDATA[");
		sb.append(getPaymentApplicationId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>amountPaid</column-name><column-value><![CDATA[");
		sb.append(getAmountPaid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>amountRefunded</column-name><column-value><![CDATA[");
		sb.append(getAmountRefunded());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _uuid;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userUuid;
	private long _transactionId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _applicationId;
	private long _sellerId;
	private double _amount;
	private String _currencyCode;
	private long _status;
	private String _paymentApplicationId;
	private double _amountPaid;
	private double _amountRefunded;
	private BaseModel<?> _transactionRemoteModel;
}