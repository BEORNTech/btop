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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author    Sebastien Meunier
 * @generated
 */
public class TransactionLogSoap implements Serializable {
	public static TransactionLogSoap toSoapModel(TransactionLog model) {
		TransactionLogSoap soapModel = new TransactionLogSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setTransactionLogId(model.getTransactionLogId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTransactionId(model.getTransactionId());
		soapModel.setPaymentApplicationId(model.getPaymentApplicationId());
		soapModel.setRemoteId(model.getRemoteId());
		soapModel.setAmount(model.getAmount());
		soapModel.setType(model.getType());

		return soapModel;
	}

	public static TransactionLogSoap[] toSoapModels(TransactionLog[] models) {
		TransactionLogSoap[] soapModels = new TransactionLogSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TransactionLogSoap[][] toSoapModels(TransactionLog[][] models) {
		TransactionLogSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TransactionLogSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TransactionLogSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TransactionLogSoap[] toSoapModels(List<TransactionLog> models) {
		List<TransactionLogSoap> soapModels = new ArrayList<TransactionLogSoap>(models.size());

		for (TransactionLog model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TransactionLogSoap[soapModels.size()]);
	}

	public TransactionLogSoap() {
	}

	public long getPrimaryKey() {
		return _transactionLogId;
	}

	public void setPrimaryKey(long pk) {
		setTransactionLogId(pk);
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

	private String _uuid;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private long _transactionLogId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _transactionId;
	private String _paymentApplicationId;
	private String _remoteId;
	private double _amount;
	private long _type;
}