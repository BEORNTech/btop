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
public class TransactionParameterSoap implements Serializable {
	public static TransactionParameterSoap toSoapModel(
		TransactionParameter model) {
		TransactionParameterSoap soapModel = new TransactionParameterSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setTransactionParameterId(model.getTransactionParameterId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTransactionId(model.getTransactionId());
		soapModel.setKey(model.getKey());
		soapModel.setValue(model.getValue());

		return soapModel;
	}

	public static TransactionParameterSoap[] toSoapModels(
		TransactionParameter[] models) {
		TransactionParameterSoap[] soapModels = new TransactionParameterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TransactionParameterSoap[][] toSoapModels(
		TransactionParameter[][] models) {
		TransactionParameterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TransactionParameterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TransactionParameterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TransactionParameterSoap[] toSoapModels(
		List<TransactionParameter> models) {
		List<TransactionParameterSoap> soapModels = new ArrayList<TransactionParameterSoap>(models.size());

		for (TransactionParameter model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TransactionParameterSoap[soapModels.size()]);
	}

	public TransactionParameterSoap() {
	}

	public long getPrimaryKey() {
		return _transactionParameterId;
	}

	public void setPrimaryKey(long pk) {
		setTransactionParameterId(pk);
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

	public long getTransactionParameterId() {
		return _transactionParameterId;
	}

	public void setTransactionParameterId(long transactionParameterId) {
		_transactionParameterId = transactionParameterId;
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

	public String getKey() {
		return _key;
	}

	public void setKey(String key) {
		_key = key;
	}

	public String getValue() {
		return _value;
	}

	public void setValue(String value) {
		_value = value;
	}

	private String _uuid;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private long _transactionParameterId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _transactionId;
	private String _key;
	private String _value;
}