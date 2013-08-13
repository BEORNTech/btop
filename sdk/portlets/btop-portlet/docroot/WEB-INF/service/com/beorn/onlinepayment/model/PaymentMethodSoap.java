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
 * This class is used by SOAP remote services, specifically {@link com.beorn.onlinepayment.service.http.PaymentMethodServiceSoap}.
 *
 * @author    Sebastien Meunier
 * @see       com.beorn.onlinepayment.service.http.PaymentMethodServiceSoap
 * @generated
 */
public class PaymentMethodSoap implements Serializable {
	public static PaymentMethodSoap toSoapModel(PaymentMethod model) {
		PaymentMethodSoap soapModel = new PaymentMethodSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setPaymentMethodId(model.getPaymentMethodId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setKey(model.getKey());
		soapModel.setName(model.getName());

		return soapModel;
	}

	public static PaymentMethodSoap[] toSoapModels(PaymentMethod[] models) {
		PaymentMethodSoap[] soapModels = new PaymentMethodSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PaymentMethodSoap[][] toSoapModels(PaymentMethod[][] models) {
		PaymentMethodSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PaymentMethodSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PaymentMethodSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PaymentMethodSoap[] toSoapModels(List<PaymentMethod> models) {
		List<PaymentMethodSoap> soapModels = new ArrayList<PaymentMethodSoap>(models.size());

		for (PaymentMethod model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PaymentMethodSoap[soapModels.size()]);
	}

	public PaymentMethodSoap() {
	}

	public long getPrimaryKey() {
		return _paymentMethodId;
	}

	public void setPrimaryKey(long pk) {
		setPaymentMethodId(pk);
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

	public long getPaymentMethodId() {
		return _paymentMethodId;
	}

	public void setPaymentMethodId(long paymentMethodId) {
		_paymentMethodId = paymentMethodId;
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

	public String getKey() {
		return _key;
	}

	public void setKey(String key) {
		_key = key;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	private String _uuid;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private long _paymentMethodId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _key;
	private String _name;
}