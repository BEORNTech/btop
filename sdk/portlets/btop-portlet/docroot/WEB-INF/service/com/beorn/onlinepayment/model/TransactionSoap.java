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
 * This class is used by SOAP remote services, specifically {@link com.beorn.onlinepayment.service.http.TransactionServiceSoap}.
 *
 * @author    Sebastien Meunier
 * @see       com.beorn.onlinepayment.service.http.TransactionServiceSoap
 * @generated
 */
public class TransactionSoap implements Serializable {
	public static TransactionSoap toSoapModel(Transaction model) {
		TransactionSoap soapModel = new TransactionSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setTransactionId(model.getTransactionId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setApplicationId(model.getApplicationId());
		soapModel.setSellerId(model.getSellerId());
		soapModel.setAmount(model.getAmount());
		soapModel.setCurrencyCode(model.getCurrencyCode());
		soapModel.setStatus(model.getStatus());
		soapModel.setPaymentApplicationId(model.getPaymentApplicationId());
		soapModel.setAmountPaid(model.getAmountPaid());
		soapModel.setAmountRefunded(model.getAmountRefunded());

		return soapModel;
	}

	public static TransactionSoap[] toSoapModels(Transaction[] models) {
		TransactionSoap[] soapModels = new TransactionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TransactionSoap[][] toSoapModels(Transaction[][] models) {
		TransactionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TransactionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TransactionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TransactionSoap[] toSoapModels(List<Transaction> models) {
		List<TransactionSoap> soapModels = new ArrayList<TransactionSoap>(models.size());

		for (Transaction model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TransactionSoap[soapModels.size()]);
	}

	public TransactionSoap() {
	}

	public long getPrimaryKey() {
		return _transactionId;
	}

	public void setPrimaryKey(long pk) {
		setTransactionId(pk);
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

	private String _uuid;
	private long _companyId;
	private long _groupId;
	private long _userId;
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
}