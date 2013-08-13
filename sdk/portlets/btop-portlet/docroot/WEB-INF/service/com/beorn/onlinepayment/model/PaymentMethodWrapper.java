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
 * This class is a wrapper for {@link PaymentMethod}.
 * </p>
 *
 * @author    Sebastien Meunier
 * @see       PaymentMethod
 * @generated
 */
public class PaymentMethodWrapper implements PaymentMethod,
	ModelWrapper<PaymentMethod> {
	public PaymentMethodWrapper(PaymentMethod paymentMethod) {
		_paymentMethod = paymentMethod;
	}

	public Class<?> getModelClass() {
		return PaymentMethod.class;
	}

	public String getModelClassName() {
		return PaymentMethod.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("paymentMethodId", getPaymentMethodId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("key", getKey());
		attributes.put("name", getName());

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

		Long paymentMethodId = (Long)attributes.get("paymentMethodId");

		if (paymentMethodId != null) {
			setPaymentMethodId(paymentMethodId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String key = (String)attributes.get("key");

		if (key != null) {
			setKey(key);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}
	}

	/**
	* Returns the primary key of this payment method.
	*
	* @return the primary key of this payment method
	*/
	public long getPrimaryKey() {
		return _paymentMethod.getPrimaryKey();
	}

	/**
	* Sets the primary key of this payment method.
	*
	* @param primaryKey the primary key of this payment method
	*/
	public void setPrimaryKey(long primaryKey) {
		_paymentMethod.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this payment method.
	*
	* @return the uuid of this payment method
	*/
	public java.lang.String getUuid() {
		return _paymentMethod.getUuid();
	}

	/**
	* Sets the uuid of this payment method.
	*
	* @param uuid the uuid of this payment method
	*/
	public void setUuid(java.lang.String uuid) {
		_paymentMethod.setUuid(uuid);
	}

	/**
	* Returns the company ID of this payment method.
	*
	* @return the company ID of this payment method
	*/
	public long getCompanyId() {
		return _paymentMethod.getCompanyId();
	}

	/**
	* Sets the company ID of this payment method.
	*
	* @param companyId the company ID of this payment method
	*/
	public void setCompanyId(long companyId) {
		_paymentMethod.setCompanyId(companyId);
	}

	/**
	* Returns the group ID of this payment method.
	*
	* @return the group ID of this payment method
	*/
	public long getGroupId() {
		return _paymentMethod.getGroupId();
	}

	/**
	* Sets the group ID of this payment method.
	*
	* @param groupId the group ID of this payment method
	*/
	public void setGroupId(long groupId) {
		_paymentMethod.setGroupId(groupId);
	}

	/**
	* Returns the user ID of this payment method.
	*
	* @return the user ID of this payment method
	*/
	public long getUserId() {
		return _paymentMethod.getUserId();
	}

	/**
	* Sets the user ID of this payment method.
	*
	* @param userId the user ID of this payment method
	*/
	public void setUserId(long userId) {
		_paymentMethod.setUserId(userId);
	}

	/**
	* Returns the user uuid of this payment method.
	*
	* @return the user uuid of this payment method
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _paymentMethod.getUserUuid();
	}

	/**
	* Sets the user uuid of this payment method.
	*
	* @param userUuid the user uuid of this payment method
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_paymentMethod.setUserUuid(userUuid);
	}

	/**
	* Returns the payment method ID of this payment method.
	*
	* @return the payment method ID of this payment method
	*/
	public long getPaymentMethodId() {
		return _paymentMethod.getPaymentMethodId();
	}

	/**
	* Sets the payment method ID of this payment method.
	*
	* @param paymentMethodId the payment method ID of this payment method
	*/
	public void setPaymentMethodId(long paymentMethodId) {
		_paymentMethod.setPaymentMethodId(paymentMethodId);
	}

	/**
	* Returns the create date of this payment method.
	*
	* @return the create date of this payment method
	*/
	public java.util.Date getCreateDate() {
		return _paymentMethod.getCreateDate();
	}

	/**
	* Sets the create date of this payment method.
	*
	* @param createDate the create date of this payment method
	*/
	public void setCreateDate(java.util.Date createDate) {
		_paymentMethod.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this payment method.
	*
	* @return the modified date of this payment method
	*/
	public java.util.Date getModifiedDate() {
		return _paymentMethod.getModifiedDate();
	}

	/**
	* Sets the modified date of this payment method.
	*
	* @param modifiedDate the modified date of this payment method
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_paymentMethod.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the key of this payment method.
	*
	* @return the key of this payment method
	*/
	public java.lang.String getKey() {
		return _paymentMethod.getKey();
	}

	/**
	* Sets the key of this payment method.
	*
	* @param key the key of this payment method
	*/
	public void setKey(java.lang.String key) {
		_paymentMethod.setKey(key);
	}

	/**
	* Returns the name of this payment method.
	*
	* @return the name of this payment method
	*/
	public java.lang.String getName() {
		return _paymentMethod.getName();
	}

	/**
	* Returns the localized name of this payment method in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized name of this payment method
	*/
	public java.lang.String getName(java.util.Locale locale) {
		return _paymentMethod.getName(locale);
	}

	/**
	* Returns the localized name of this payment method in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this payment method. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	public java.lang.String getName(java.util.Locale locale, boolean useDefault) {
		return _paymentMethod.getName(locale, useDefault);
	}

	/**
	* Returns the localized name of this payment method in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized name of this payment method
	*/
	public java.lang.String getName(java.lang.String languageId) {
		return _paymentMethod.getName(languageId);
	}

	/**
	* Returns the localized name of this payment method in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this payment method
	*/
	public java.lang.String getName(java.lang.String languageId,
		boolean useDefault) {
		return _paymentMethod.getName(languageId, useDefault);
	}

	public java.lang.String getNameCurrentLanguageId() {
		return _paymentMethod.getNameCurrentLanguageId();
	}

	public java.lang.String getNameCurrentValue() {
		return _paymentMethod.getNameCurrentValue();
	}

	/**
	* Returns a map of the locales and localized names of this payment method.
	*
	* @return the locales and localized names of this payment method
	*/
	public java.util.Map<java.util.Locale, java.lang.String> getNameMap() {
		return _paymentMethod.getNameMap();
	}

	/**
	* Sets the name of this payment method.
	*
	* @param name the name of this payment method
	*/
	public void setName(java.lang.String name) {
		_paymentMethod.setName(name);
	}

	/**
	* Sets the localized name of this payment method in the language.
	*
	* @param name the localized name of this payment method
	* @param locale the locale of the language
	*/
	public void setName(java.lang.String name, java.util.Locale locale) {
		_paymentMethod.setName(name, locale);
	}

	/**
	* Sets the localized name of this payment method in the language, and sets the default locale.
	*
	* @param name the localized name of this payment method
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	public void setName(java.lang.String name, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_paymentMethod.setName(name, locale, defaultLocale);
	}

	public void setNameCurrentLanguageId(java.lang.String languageId) {
		_paymentMethod.setNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized names of this payment method from the map of locales and localized names.
	*
	* @param nameMap the locales and localized names of this payment method
	*/
	public void setNameMap(
		java.util.Map<java.util.Locale, java.lang.String> nameMap) {
		_paymentMethod.setNameMap(nameMap);
	}

	/**
	* Sets the localized names of this payment method from the map of locales and localized names, and sets the default locale.
	*
	* @param nameMap the locales and localized names of this payment method
	* @param defaultLocale the default locale
	*/
	public void setNameMap(
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Locale defaultLocale) {
		_paymentMethod.setNameMap(nameMap, defaultLocale);
	}

	public boolean isNew() {
		return _paymentMethod.isNew();
	}

	public void setNew(boolean n) {
		_paymentMethod.setNew(n);
	}

	public boolean isCachedModel() {
		return _paymentMethod.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_paymentMethod.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _paymentMethod.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _paymentMethod.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_paymentMethod.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _paymentMethod.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_paymentMethod.setExpandoBridgeAttributes(serviceContext);
	}

	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.LocaleException {
		_paymentMethod.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public java.lang.Object clone() {
		return new PaymentMethodWrapper((PaymentMethod)_paymentMethod.clone());
	}

	public int compareTo(
		com.beorn.onlinepayment.model.PaymentMethod paymentMethod) {
		return _paymentMethod.compareTo(paymentMethod);
	}

	@Override
	public int hashCode() {
		return _paymentMethod.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.beorn.onlinepayment.model.PaymentMethod> toCacheModel() {
		return _paymentMethod.toCacheModel();
	}

	public com.beorn.onlinepayment.model.PaymentMethod toEscapedModel() {
		return new PaymentMethodWrapper(_paymentMethod.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _paymentMethod.toString();
	}

	public java.lang.String toXmlString() {
		return _paymentMethod.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_paymentMethod.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public PaymentMethod getWrappedPaymentMethod() {
		return _paymentMethod;
	}

	public PaymentMethod getWrappedModel() {
		return _paymentMethod;
	}

	public void resetOriginalValues() {
		_paymentMethod.resetOriginalValues();
	}

	private PaymentMethod _paymentMethod;
}