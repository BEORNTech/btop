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

import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PaymentPlugin}.
 * </p>
 *
 * @author    Sebastien Meunier
 * @see       PaymentPlugin
 * @generated
 */
public class PaymentPluginWrapper implements PaymentPlugin,
	ModelWrapper<PaymentPlugin> {
	public PaymentPluginWrapper(PaymentPlugin paymentPlugin) {
		_paymentPlugin = paymentPlugin;
	}

	public Class<?> getModelClass() {
		return PaymentPlugin.class;
	}

	public String getModelClassName() {
		return PaymentPlugin.class.getName();
	}

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

	/**
	* Returns the primary key of this payment plugin.
	*
	* @return the primary key of this payment plugin
	*/
	public long getPrimaryKey() {
		return _paymentPlugin.getPrimaryKey();
	}

	/**
	* Sets the primary key of this payment plugin.
	*
	* @param primaryKey the primary key of this payment plugin
	*/
	public void setPrimaryKey(long primaryKey) {
		_paymentPlugin.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this payment plugin.
	*
	* @return the uuid of this payment plugin
	*/
	public java.lang.String getUuid() {
		return _paymentPlugin.getUuid();
	}

	/**
	* Sets the uuid of this payment plugin.
	*
	* @param uuid the uuid of this payment plugin
	*/
	public void setUuid(java.lang.String uuid) {
		_paymentPlugin.setUuid(uuid);
	}

	/**
	* Returns the company ID of this payment plugin.
	*
	* @return the company ID of this payment plugin
	*/
	public long getCompanyId() {
		return _paymentPlugin.getCompanyId();
	}

	/**
	* Sets the company ID of this payment plugin.
	*
	* @param companyId the company ID of this payment plugin
	*/
	public void setCompanyId(long companyId) {
		_paymentPlugin.setCompanyId(companyId);
	}

	/**
	* Returns the group ID of this payment plugin.
	*
	* @return the group ID of this payment plugin
	*/
	public long getGroupId() {
		return _paymentPlugin.getGroupId();
	}

	/**
	* Sets the group ID of this payment plugin.
	*
	* @param groupId the group ID of this payment plugin
	*/
	public void setGroupId(long groupId) {
		_paymentPlugin.setGroupId(groupId);
	}

	/**
	* Returns the user ID of this payment plugin.
	*
	* @return the user ID of this payment plugin
	*/
	public long getUserId() {
		return _paymentPlugin.getUserId();
	}

	/**
	* Sets the user ID of this payment plugin.
	*
	* @param userId the user ID of this payment plugin
	*/
	public void setUserId(long userId) {
		_paymentPlugin.setUserId(userId);
	}

	/**
	* Returns the user uuid of this payment plugin.
	*
	* @return the user uuid of this payment plugin
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _paymentPlugin.getUserUuid();
	}

	/**
	* Sets the user uuid of this payment plugin.
	*
	* @param userUuid the user uuid of this payment plugin
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_paymentPlugin.setUserUuid(userUuid);
	}

	/**
	* Returns the payment plugin ID of this payment plugin.
	*
	* @return the payment plugin ID of this payment plugin
	*/
	public long getPaymentPluginId() {
		return _paymentPlugin.getPaymentPluginId();
	}

	/**
	* Sets the payment plugin ID of this payment plugin.
	*
	* @param paymentPluginId the payment plugin ID of this payment plugin
	*/
	public void setPaymentPluginId(long paymentPluginId) {
		_paymentPlugin.setPaymentPluginId(paymentPluginId);
	}

	/**
	* Returns the create date of this payment plugin.
	*
	* @return the create date of this payment plugin
	*/
	public java.util.Date getCreateDate() {
		return _paymentPlugin.getCreateDate();
	}

	/**
	* Sets the create date of this payment plugin.
	*
	* @param createDate the create date of this payment plugin
	*/
	public void setCreateDate(java.util.Date createDate) {
		_paymentPlugin.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this payment plugin.
	*
	* @return the modified date of this payment plugin
	*/
	public java.util.Date getModifiedDate() {
		return _paymentPlugin.getModifiedDate();
	}

	/**
	* Sets the modified date of this payment plugin.
	*
	* @param modifiedDate the modified date of this payment plugin
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_paymentPlugin.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the application ID of this payment plugin.
	*
	* @return the application ID of this payment plugin
	*/
	public java.lang.String getApplicationId() {
		return _paymentPlugin.getApplicationId();
	}

	/**
	* Sets the application ID of this payment plugin.
	*
	* @param applicationId the application ID of this payment plugin
	*/
	public void setApplicationId(java.lang.String applicationId) {
		_paymentPlugin.setApplicationId(applicationId);
	}

	/**
	* Returns the name of this payment plugin.
	*
	* @return the name of this payment plugin
	*/
	public java.lang.String getName() {
		return _paymentPlugin.getName();
	}

	/**
	* Returns the localized name of this payment plugin in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized name of this payment plugin
	*/
	public java.lang.String getName(java.util.Locale locale) {
		return _paymentPlugin.getName(locale);
	}

	/**
	* Returns the localized name of this payment plugin in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this payment plugin. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	public java.lang.String getName(java.util.Locale locale, boolean useDefault) {
		return _paymentPlugin.getName(locale, useDefault);
	}

	/**
	* Returns the localized name of this payment plugin in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized name of this payment plugin
	*/
	public java.lang.String getName(java.lang.String languageId) {
		return _paymentPlugin.getName(languageId);
	}

	/**
	* Returns the localized name of this payment plugin in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this payment plugin
	*/
	public java.lang.String getName(java.lang.String languageId,
		boolean useDefault) {
		return _paymentPlugin.getName(languageId, useDefault);
	}

	public java.lang.String getNameCurrentLanguageId() {
		return _paymentPlugin.getNameCurrentLanguageId();
	}

	public java.lang.String getNameCurrentValue() {
		return _paymentPlugin.getNameCurrentValue();
	}

	/**
	* Returns a map of the locales and localized names of this payment plugin.
	*
	* @return the locales and localized names of this payment plugin
	*/
	public java.util.Map<java.util.Locale, java.lang.String> getNameMap() {
		return _paymentPlugin.getNameMap();
	}

	/**
	* Sets the name of this payment plugin.
	*
	* @param name the name of this payment plugin
	*/
	public void setName(java.lang.String name) {
		_paymentPlugin.setName(name);
	}

	/**
	* Sets the localized name of this payment plugin in the language.
	*
	* @param name the localized name of this payment plugin
	* @param locale the locale of the language
	*/
	public void setName(java.lang.String name, java.util.Locale locale) {
		_paymentPlugin.setName(name, locale);
	}

	/**
	* Sets the localized name of this payment plugin in the language, and sets the default locale.
	*
	* @param name the localized name of this payment plugin
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	public void setName(java.lang.String name, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_paymentPlugin.setName(name, locale, defaultLocale);
	}

	public void setNameCurrentLanguageId(java.lang.String languageId) {
		_paymentPlugin.setNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized names of this payment plugin from the map of locales and localized names.
	*
	* @param nameMap the locales and localized names of this payment plugin
	*/
	public void setNameMap(
		java.util.Map<java.util.Locale, java.lang.String> nameMap) {
		_paymentPlugin.setNameMap(nameMap);
	}

	/**
	* Sets the localized names of this payment plugin from the map of locales and localized names, and sets the default locale.
	*
	* @param nameMap the locales and localized names of this payment plugin
	* @param defaultLocale the default locale
	*/
	public void setNameMap(
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Locale defaultLocale) {
		_paymentPlugin.setNameMap(nameMap, defaultLocale);
	}

	/**
	* Returns the plugin config parameters of this payment plugin.
	*
	* @return the plugin config parameters of this payment plugin
	*/
	public java.lang.String getPluginConfigParameters() {
		return _paymentPlugin.getPluginConfigParameters();
	}

	/**
	* Sets the plugin config parameters of this payment plugin.
	*
	* @param pluginConfigParameters the plugin config parameters of this payment plugin
	*/
	public void setPluginConfigParameters(
		java.lang.String pluginConfigParameters) {
		_paymentPlugin.setPluginConfigParameters(pluginConfigParameters);
	}

	/**
	* Returns the seller config parameters of this payment plugin.
	*
	* @return the seller config parameters of this payment plugin
	*/
	public java.lang.String getSellerConfigParameters() {
		return _paymentPlugin.getSellerConfigParameters();
	}

	/**
	* Sets the seller config parameters of this payment plugin.
	*
	* @param sellerConfigParameters the seller config parameters of this payment plugin
	*/
	public void setSellerConfigParameters(
		java.lang.String sellerConfigParameters) {
		_paymentPlugin.setSellerConfigParameters(sellerConfigParameters);
	}

	/**
	* Returns the plugin config of this payment plugin.
	*
	* @return the plugin config of this payment plugin
	*/
	public java.lang.String getPluginConfig() {
		return _paymentPlugin.getPluginConfig();
	}

	/**
	* Sets the plugin config of this payment plugin.
	*
	* @param pluginConfig the plugin config of this payment plugin
	*/
	public void setPluginConfig(java.lang.String pluginConfig) {
		_paymentPlugin.setPluginConfig(pluginConfig);
	}

	/**
	* Returns the configured of this payment plugin.
	*
	* @return the configured of this payment plugin
	*/
	public boolean getConfigured() {
		return _paymentPlugin.getConfigured();
	}

	/**
	* Returns <code>true</code> if this payment plugin is configured.
	*
	* @return <code>true</code> if this payment plugin is configured; <code>false</code> otherwise
	*/
	public boolean isConfigured() {
		return _paymentPlugin.isConfigured();
	}

	/**
	* Sets whether this payment plugin is configured.
	*
	* @param configured the configured of this payment plugin
	*/
	public void setConfigured(boolean configured) {
		_paymentPlugin.setConfigured(configured);
	}

	public boolean isNew() {
		return _paymentPlugin.isNew();
	}

	public void setNew(boolean n) {
		_paymentPlugin.setNew(n);
	}

	public boolean isCachedModel() {
		return _paymentPlugin.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_paymentPlugin.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _paymentPlugin.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _paymentPlugin.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_paymentPlugin.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _paymentPlugin.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_paymentPlugin.setExpandoBridgeAttributes(serviceContext);
	}

	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.LocaleException {
		_paymentPlugin.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public java.lang.Object clone() {
		return new PaymentPluginWrapper((PaymentPlugin)_paymentPlugin.clone());
	}

	public int compareTo(
		com.beorn.onlinepayment.model.PaymentPlugin paymentPlugin) {
		return _paymentPlugin.compareTo(paymentPlugin);
	}

	@Override
	public int hashCode() {
		return _paymentPlugin.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.beorn.onlinepayment.model.PaymentPlugin> toCacheModel() {
		return _paymentPlugin.toCacheModel();
	}

	public com.beorn.onlinepayment.model.PaymentPlugin toEscapedModel() {
		return new PaymentPluginWrapper(_paymentPlugin.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _paymentPlugin.toString();
	}

	public java.lang.String toXmlString() {
		return _paymentPlugin.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_paymentPlugin.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public PaymentPlugin getWrappedPaymentPlugin() {
		return _paymentPlugin;
	}

	public PaymentPlugin getWrappedModel() {
		return _paymentPlugin;
	}

	public void resetOriginalValues() {
		_paymentPlugin.resetOriginalValues();
	}

	private PaymentPlugin _paymentPlugin;
}