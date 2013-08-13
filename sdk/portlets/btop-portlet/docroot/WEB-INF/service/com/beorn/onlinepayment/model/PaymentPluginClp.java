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

import com.beorn.onlinepayment.service.PaymentPluginLocalServiceUtil;

import com.liferay.portal.LocaleException;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Sebastien Meunier
 */
public class PaymentPluginClp extends BaseModelImpl<PaymentPlugin>
	implements PaymentPlugin {
	public PaymentPluginClp() {
	}

	public Class<?> getModelClass() {
		return PaymentPlugin.class;
	}

	public String getModelClassName() {
		return PaymentPlugin.class.getName();
	}

	public long getPrimaryKey() {
		return _paymentPluginId;
	}

	public void setPrimaryKey(long primaryKey) {
		setPaymentPluginId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_paymentPluginId);
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
		attributes.put("paymentPluginId", getPaymentPluginId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("applicationId", getApplicationId());
		attributes.put("name", getName());
		attributes.put("pluginConfigParameters", getPluginConfigParameters());
		attributes.put("sellerConfigParameters", getSellerConfigParameters());
		attributes.put("pluginConfig", getPluginConfig());
		attributes.put("configured", getConfigured());

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

		Long paymentPluginId = (Long)attributes.get("paymentPluginId");

		if (paymentPluginId != null) {
			setPaymentPluginId(paymentPluginId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String pluginConfigParameters = (String)attributes.get(
				"pluginConfigParameters");

		if (pluginConfigParameters != null) {
			setPluginConfigParameters(pluginConfigParameters);
		}

		String sellerConfigParameters = (String)attributes.get(
				"sellerConfigParameters");

		if (sellerConfigParameters != null) {
			setSellerConfigParameters(sellerConfigParameters);
		}

		String pluginConfig = (String)attributes.get("pluginConfig");

		if (pluginConfig != null) {
			setPluginConfig(pluginConfig);
		}

		Boolean configured = (Boolean)attributes.get("configured");

		if (configured != null) {
			setConfigured(configured);
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

	public String getName(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId);
	}

	public String getName(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId, useDefault);
	}

	public String getName(String languageId) {
		return LocalizationUtil.getLocalization(getName(), languageId);
	}

	public String getName(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getName(), languageId,
			useDefault);
	}

	public String getNameCurrentLanguageId() {
		return _nameCurrentLanguageId;
	}

	public String getNameCurrentValue() {
		Locale locale = getLocale(_nameCurrentLanguageId);

		return getName(locale);
	}

	public Map<Locale, String> getNameMap() {
		return LocalizationUtil.getLocalizationMap(getName());
	}

	public void setName(String name) {
		_name = name;
	}

	public void setName(String name, Locale locale) {
		setName(name, locale, LocaleUtil.getDefault());
	}

	public void setName(String name, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(name)) {
			setName(LocalizationUtil.updateLocalization(getName(), "Name",
					name, languageId, defaultLanguageId));
		}
		else {
			setName(LocalizationUtil.removeLocalization(getName(), "Name",
					languageId));
		}
	}

	public void setNameCurrentLanguageId(String languageId) {
		_nameCurrentLanguageId = languageId;
	}

	public void setNameMap(Map<Locale, String> nameMap) {
		setNameMap(nameMap, LocaleUtil.getDefault());
	}

	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale) {
		if (nameMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			Locale[] locales = LanguageUtil.getAvailableLocales();

			for (Locale locale : locales) {
				String name = nameMap.get(locale);

				setName(name, locale, defaultLocale);
			}
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
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

	public BaseModel<?> getPaymentPluginRemoteModel() {
		return _paymentPluginRemoteModel;
	}

	public void setPaymentPluginRemoteModel(
		BaseModel<?> paymentPluginRemoteModel) {
		_paymentPluginRemoteModel = paymentPluginRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			PaymentPluginLocalServiceUtil.addPaymentPlugin(this);
		}
		else {
			PaymentPluginLocalServiceUtil.updatePaymentPlugin(this);
		}
	}

	@SuppressWarnings("unused")
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException {
		setName(getName(defaultImportLocale), defaultImportLocale,
			defaultImportLocale);
	}

	@Override
	public PaymentPlugin toEscapedModel() {
		return (PaymentPlugin)Proxy.newProxyInstance(PaymentPlugin.class.getClassLoader(),
			new Class[] { PaymentPlugin.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		PaymentPluginClp clone = new PaymentPluginClp();

		clone.setUuid(getUuid());
		clone.setCompanyId(getCompanyId());
		clone.setGroupId(getGroupId());
		clone.setUserId(getUserId());
		clone.setPaymentPluginId(getPaymentPluginId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setApplicationId(getApplicationId());
		clone.setName(getName());
		clone.setPluginConfigParameters(getPluginConfigParameters());
		clone.setSellerConfigParameters(getSellerConfigParameters());
		clone.setPluginConfig(getPluginConfig());
		clone.setConfigured(getConfigured());

		return clone;
	}

	public int compareTo(PaymentPlugin paymentPlugin) {
		int value = 0;

		value = DateUtil.compareTo(getModifiedDate(),
				paymentPlugin.getModifiedDate());

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

		PaymentPluginClp paymentPlugin = null;

		try {
			paymentPlugin = (PaymentPluginClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = paymentPlugin.getPrimaryKey();

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
		StringBundler sb = new StringBundler(27);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", paymentPluginId=");
		sb.append(getPaymentPluginId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", applicationId=");
		sb.append(getApplicationId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", pluginConfigParameters=");
		sb.append(getPluginConfigParameters());
		sb.append(", sellerConfigParameters=");
		sb.append(getSellerConfigParameters());
		sb.append(", pluginConfig=");
		sb.append(getPluginConfig());
		sb.append(", configured=");
		sb.append(getConfigured());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(43);

		sb.append("<model><model-name>");
		sb.append("com.beorn.onlinepayment.model.PaymentPlugin");
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
			"<column><column-name>paymentPluginId</column-name><column-value><![CDATA[");
		sb.append(getPaymentPluginId());
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
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>pluginConfigParameters</column-name><column-value><![CDATA[");
		sb.append(getPluginConfigParameters());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sellerConfigParameters</column-name><column-value><![CDATA[");
		sb.append(getSellerConfigParameters());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>pluginConfig</column-name><column-value><![CDATA[");
		sb.append(getPluginConfig());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>configured</column-name><column-value><![CDATA[");
		sb.append(getConfigured());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _uuid;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userUuid;
	private long _paymentPluginId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _applicationId;
	private String _name;
	private String _nameCurrentLanguageId;
	private String _pluginConfigParameters;
	private String _sellerConfigParameters;
	private String _pluginConfig;
	private boolean _configured;
	private BaseModel<?> _paymentPluginRemoteModel;
}