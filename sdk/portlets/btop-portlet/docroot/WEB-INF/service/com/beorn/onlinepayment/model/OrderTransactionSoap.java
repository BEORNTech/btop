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
public class OrderTransactionSoap implements Serializable {
	public static OrderTransactionSoap toSoapModel(OrderTransaction model) {
		OrderTransactionSoap soapModel = new OrderTransactionSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setOrderId(model.getOrderId());
		soapModel.setTransactionId(model.getTransactionId());

		return soapModel;
	}

	public static OrderTransactionSoap[] toSoapModels(OrderTransaction[] models) {
		OrderTransactionSoap[] soapModels = new OrderTransactionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static OrderTransactionSoap[][] toSoapModels(
		OrderTransaction[][] models) {
		OrderTransactionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new OrderTransactionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new OrderTransactionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static OrderTransactionSoap[] toSoapModels(
		List<OrderTransaction> models) {
		List<OrderTransactionSoap> soapModels = new ArrayList<OrderTransactionSoap>(models.size());

		for (OrderTransaction model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new OrderTransactionSoap[soapModels.size()]);
	}

	public OrderTransactionSoap() {
	}

	public long getPrimaryKey() {
		return _orderId;
	}

	public void setPrimaryKey(long pk) {
		setOrderId(pk);
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

	private String _uuid;
	private Date _createDate;
	private Date _modifiedDate;
	private long _orderId;
	private long _transactionId;
}