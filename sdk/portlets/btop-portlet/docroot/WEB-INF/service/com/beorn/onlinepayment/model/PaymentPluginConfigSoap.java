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
public class PaymentPluginConfigSoap implements Serializable {
	public static PaymentPluginConfigSoap toSoapModel(PaymentPluginConfig model) {
		PaymentPluginConfigSoap soapModel = new PaymentPluginConfigSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setPaymentPluginConfigId(model.getPaymentPluginConfigId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setSellerId(model.getSellerId());
		soapModel.setPaymentPluginId(model.getPaymentPluginId());
		soapModel.setConfig(model.getConfig());
		soapModel.setConfigured(model.getConfigured());
		soapModel.setDefaultPlugin(model.getDefaultPlugin());

		return soapModel;
	}

	public static PaymentPluginConfigSoap[] toSoapModels(
		PaymentPluginConfig[] models) {
		PaymentPluginConfigSoap[] soapModels = new PaymentPluginConfigSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PaymentPluginConfigSoap[][] toSoapModels(
		PaymentPluginConfig[][] models) {
		PaymentPluginConfigSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PaymentPluginConfigSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PaymentPluginConfigSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PaymentPluginConfigSoap[] toSoapModels(
		List<PaymentPluginConfig> models) {
		List<PaymentPluginConfigSoap> soapModels = new ArrayList<PaymentPluginConfigSoap>(models.size());

		for (PaymentPluginConfig model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PaymentPluginConfigSoap[soapModels.size()]);
	}

	public PaymentPluginConfigSoap() {
	}

	public long getPrimaryKey() {
		return _paymentPluginConfigId;
	}

	public void setPrimaryKey(long pk) {
		setPaymentPluginConfigId(pk);
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

	public long getPaymentPluginConfigId() {
		return _paymentPluginConfigId;
	}

	public void setPaymentPluginConfigId(long paymentPluginConfigId) {
		_paymentPluginConfigId = paymentPluginConfigId;
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

	public long getSellerId() {
		return _sellerId;
	}

	public void setSellerId(long sellerId) {
		_sellerId = sellerId;
	}

	public long getPaymentPluginId() {
		return _paymentPluginId;
	}

	public void setPaymentPluginId(long paymentPluginId) {
		_paymentPluginId = paymentPluginId;
	}

	public String getConfig() {
		return _config;
	}

	public void setConfig(String config) {
		_config = config;
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

	public boolean getDefaultPlugin() {
		return _defaultPlugin;
	}

	public boolean isDefaultPlugin() {
		return _defaultPlugin;
	}

	public void setDefaultPlugin(boolean defaultPlugin) {
		_defaultPlugin = defaultPlugin;
	}

	private String _uuid;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private long _paymentPluginConfigId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _sellerId;
	private long _paymentPluginId;
	private String _config;
	private boolean _configured;
	private boolean _defaultPlugin;
}