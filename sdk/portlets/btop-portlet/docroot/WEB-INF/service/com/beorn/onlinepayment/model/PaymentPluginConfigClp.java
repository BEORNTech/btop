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

import com.beorn.onlinepayment.service.PaymentPluginConfigLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
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
public class PaymentPluginConfigClp extends BaseModelImpl<PaymentPluginConfig>
	implements PaymentPluginConfig {
	public PaymentPluginConfigClp() {
	}

	public Class<?> getModelClass() {
		return PaymentPluginConfig.class;
	}

	public String getModelClassName() {
		return PaymentPluginConfig.class.getName();
	}

	public long getPrimaryKey() {
		return _paymentPluginConfigId;
	}

	public void setPrimaryKey(long primaryKey) {
		setPaymentPluginConfigId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_paymentPluginConfigId);
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
		attributes.put("paymentPluginConfigId", getPaymentPluginConfigId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("sellerId", getSellerId());
		attributes.put("paymentPluginId", getPaymentPluginId());
		attributes.put("config", getConfig());
		attributes.put("configured", getConfigured());
		attributes.put("defaultPlugin", getDefaultPlugin());

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

		Long paymentPluginConfigId = (Long)attributes.get(
				"paymentPluginConfigId");

		if (paymentPluginConfigId != null) {
			setPaymentPluginConfigId(paymentPluginConfigId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long sellerId = (Long)attributes.get("sellerId");

		if (sellerId != null) {
			setSellerId(sellerId);
		}

		Long paymentPluginId = (Long)attributes.get("paymentPluginId");

		if (paymentPluginId != null) {
			setPaymentPluginId(paymentPluginId);
		}

		String config = (String)attributes.get("config");

		if (config != null) {
			setConfig(config);
		}

		Boolean configured = (Boolean)attributes.get("configured");

		if (configured != null) {
			setConfigured(configured);
		}

		Boolean defaultPlugin = (Boolean)attributes.get("defaultPlugin");

		if (defaultPlugin != null) {
			setDefaultPlugin(defaultPlugin);
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

	public BaseModel<?> getPaymentPluginConfigRemoteModel() {
		return _paymentPluginConfigRemoteModel;
	}

	public void setPaymentPluginConfigRemoteModel(
		BaseModel<?> paymentPluginConfigRemoteModel) {
		_paymentPluginConfigRemoteModel = paymentPluginConfigRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			PaymentPluginConfigLocalServiceUtil.addPaymentPluginConfig(this);
		}
		else {
			PaymentPluginConfigLocalServiceUtil.updatePaymentPluginConfig(this);
		}
	}

	@Override
	public PaymentPluginConfig toEscapedModel() {
		return (PaymentPluginConfig)Proxy.newProxyInstance(PaymentPluginConfig.class.getClassLoader(),
			new Class[] { PaymentPluginConfig.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		PaymentPluginConfigClp clone = new PaymentPluginConfigClp();

		clone.setUuid(getUuid());
		clone.setCompanyId(getCompanyId());
		clone.setGroupId(getGroupId());
		clone.setUserId(getUserId());
		clone.setPaymentPluginConfigId(getPaymentPluginConfigId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setSellerId(getSellerId());
		clone.setPaymentPluginId(getPaymentPluginId());
		clone.setConfig(getConfig());
		clone.setConfigured(getConfigured());
		clone.setDefaultPlugin(getDefaultPlugin());

		return clone;
	}

	public int compareTo(PaymentPluginConfig paymentPluginConfig) {
		int value = 0;

		if (getPaymentPluginId() < paymentPluginConfig.getPaymentPluginId()) {
			value = -1;
		}
		else if (getPaymentPluginId() > paymentPluginConfig.getPaymentPluginId()) {
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

		PaymentPluginConfigClp paymentPluginConfig = null;

		try {
			paymentPluginConfig = (PaymentPluginConfigClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = paymentPluginConfig.getPrimaryKey();

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
		sb.append(", paymentPluginConfigId=");
		sb.append(getPaymentPluginConfigId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", sellerId=");
		sb.append(getSellerId());
		sb.append(", paymentPluginId=");
		sb.append(getPaymentPluginId());
		sb.append(", config=");
		sb.append(getConfig());
		sb.append(", configured=");
		sb.append(getConfigured());
		sb.append(", defaultPlugin=");
		sb.append(getDefaultPlugin());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(40);

		sb.append("<model><model-name>");
		sb.append("com.beorn.onlinepayment.model.PaymentPluginConfig");
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
			"<column><column-name>paymentPluginConfigId</column-name><column-value><![CDATA[");
		sb.append(getPaymentPluginConfigId());
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
			"<column><column-name>sellerId</column-name><column-value><![CDATA[");
		sb.append(getSellerId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>paymentPluginId</column-name><column-value><![CDATA[");
		sb.append(getPaymentPluginId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>config</column-name><column-value><![CDATA[");
		sb.append(getConfig());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>configured</column-name><column-value><![CDATA[");
		sb.append(getConfigured());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>defaultPlugin</column-name><column-value><![CDATA[");
		sb.append(getDefaultPlugin());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _uuid;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userUuid;
	private long _paymentPluginConfigId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _sellerId;
	private long _paymentPluginId;
	private String _config;
	private boolean _configured;
	private boolean _defaultPlugin;
	private BaseModel<?> _paymentPluginConfigRemoteModel;
}