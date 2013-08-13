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
 * This class is used by SOAP remote services, specifically {@link com.beorn.onlinepayment.service.http.PaymentPluginServiceSoap}.
 *
 * @author    Sebastien Meunier
 * @see       com.beorn.onlinepayment.service.http.PaymentPluginServiceSoap
 * @generated
 */
public class PaymentPluginSoap implements Serializable {
	public static PaymentPluginSoap toSoapModel(PaymentPlugin model) {
		PaymentPluginSoap soapModel = new PaymentPluginSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setPaymentPluginId(model.getPaymentPluginId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setApplicationId(model.getApplicationId());
		soapModel.setName(model.getName());
		soapModel.setPluginConfigParameters(model.getPluginConfigParameters());
		soapModel.setSellerConfigParameters(model.getSellerConfigParameters());
		soapModel.setPluginConfig(model.getPluginConfig());
		soapModel.setConfigured(model.getConfigured());

		return soapModel;
	}

	public static PaymentPluginSoap[] toSoapModels(PaymentPlugin[] models) {
		PaymentPluginSoap[] soapModels = new PaymentPluginSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PaymentPluginSoap[][] toSoapModels(PaymentPlugin[][] models) {
		PaymentPluginSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PaymentPluginSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PaymentPluginSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PaymentPluginSoap[] toSoapModels(List<PaymentPlugin> models) {
		List<PaymentPluginSoap> soapModels = new ArrayList<PaymentPluginSoap>(models.size());

		for (PaymentPlugin model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PaymentPluginSoap[soapModels.size()]);
	}

	public PaymentPluginSoap() {
	}

	public long getPrimaryKey() {
		return _paymentPluginId;
	}

	public void setPrimaryKey(long pk) {
		setPaymentPluginId(pk);
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

	public long getPaymentPluginId() {
		return _paymentPluginId;
	}

	public void setPaymentPluginId(long paymentPluginId) {
		_paymentPluginId = paymentPluginId;
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

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getPluginConfigParameters() {
		return _pluginConfigParameters;
	}

	public void setPluginConfigParameters(String pluginConfigParameters) {
		_pluginConfigParameters = pluginConfigParameters;
	}

	public String getSellerConfigParameters() {
		return _sellerConfigParameters;
	}

	public void setSellerConfigParameters(String sellerConfigParameters) {
		_sellerConfigParameters = sellerConfigParameters;
	}

	public String getPluginConfig() {
		return _pluginConfig;
	}

	public void setPluginConfig(String pluginConfig) {
		_pluginConfig = pluginConfig;
	}

	public boolean getConfigured() {
		return _configured;
	}

	public boolean isConfigured() {
		return _configured;
	}

	public void setConfigured(boolean configured) {
		_configured = configured;
	}

	private String _uuid;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private long _paymentPluginId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _applicationId;
	private String _name;
	private String _pluginConfigParameters;
	private String _sellerConfigParameters;
	private String _pluginConfig;
	private boolean _configured;
}