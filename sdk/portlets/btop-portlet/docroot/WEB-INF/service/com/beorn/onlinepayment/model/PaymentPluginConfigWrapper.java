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
 * This class is a wrapper for {@link PaymentPluginConfig}.
 * </p>
 *
 * @author    Sebastien Meunier
 * @see       PaymentPluginConfig
 * @generated
 */
public class PaymentPluginConfigWrapper implements PaymentPluginConfig,
	ModelWrapper<PaymentPluginConfig> {
	public PaymentPluginConfigWrapper(PaymentPluginConfig paymentPluginConfig) {
		_paymentPluginConfig = paymentPluginConfig;
	}

	public Class<?> getModelClass() {
		return PaymentPluginConfig.class;
	}

	public String getModelClassName() {
		return PaymentPluginConfig.class.getName();
	}

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

	/**
	* Returns the primary key of this payment plugin config.
	*
	* @return the primary key of this payment plugin config
	*/
	public long getPrimaryKey() {
		return _paymentPluginConfig.getPrimaryKey();
	}

	/**
	* Sets the primary key of this payment plugin config.
	*
	* @param primaryKey the primary key of this payment plugin config
	*/
	public void setPrimaryKey(long primaryKey) {
		_paymentPluginConfig.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this payment plugin config.
	*
	* @return the uuid of this payment plugin config
	*/
	public java.lang.String getUuid() {
		return _paymentPluginConfig.getUuid();
	}

	/**
	* Sets the uuid of this payment plugin config.
	*
	* @param uuid the uuid of this payment plugin config
	*/
	public void setUuid(java.lang.String uuid) {
		_paymentPluginConfig.setUuid(uuid);
	}

	/**
	* Returns the company ID of this payment plugin config.
	*
	* @return the company ID of this payment plugin config
	*/
	public long getCompanyId() {
		return _paymentPluginConfig.getCompanyId();
	}

	/**
	* Sets the company ID of this payment plugin config.
	*
	* @param companyId the company ID of this payment plugin config
	*/
	public void setCompanyId(long companyId) {
		_paymentPluginConfig.setCompanyId(companyId);
	}

	/**
	* Returns the group ID of this payment plugin config.
	*
	* @return the group ID of this payment plugin config
	*/
	public long getGroupId() {
		return _paymentPluginConfig.getGroupId();
	}

	/**
	* Sets the group ID of this payment plugin config.
	*
	* @param groupId the group ID of this payment plugin config
	*/
	public void setGroupId(long groupId) {
		_paymentPluginConfig.setGroupId(groupId);
	}

	/**
	* Returns the user ID of this payment plugin config.
	*
	* @return the user ID of this payment plugin config
	*/
	public long getUserId() {
		return _paymentPluginConfig.getUserId();
	}

	/**
	* Sets the user ID of this payment plugin config.
	*
	* @param userId the user ID of this payment plugin config
	*/
	public void setUserId(long userId) {
		_paymentPluginConfig.setUserId(userId);
	}

	/**
	* Returns the user uuid of this payment plugin config.
	*
	* @return the user uuid of this payment plugin config
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginConfig.getUserUuid();
	}

	/**
	* Sets the user uuid of this payment plugin config.
	*
	* @param userUuid the user uuid of this payment plugin config
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_paymentPluginConfig.setUserUuid(userUuid);
	}

	/**
	* Returns the payment plugin config ID of this payment plugin config.
	*
	* @return the payment plugin config ID of this payment plugin config
	*/
	public long getPaymentPluginConfigId() {
		return _paymentPluginConfig.getPaymentPluginConfigId();
	}

	/**
	* Sets the payment plugin config ID of this payment plugin config.
	*
	* @param paymentPluginConfigId the payment plugin config ID of this payment plugin config
	*/
	public void setPaymentPluginConfigId(long paymentPluginConfigId) {
		_paymentPluginConfig.setPaymentPluginConfigId(paymentPluginConfigId);
	}

	/**
	* Returns the create date of this payment plugin config.
	*
	* @return the create date of this payment plugin config
	*/
	public java.util.Date getCreateDate() {
		return _paymentPluginConfig.getCreateDate();
	}

	/**
	* Sets the create date of this payment plugin config.
	*
	* @param createDate the create date of this payment plugin config
	*/
	public void setCreateDate(java.util.Date createDate) {
		_paymentPluginConfig.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this payment plugin config.
	*
	* @return the modified date of this payment plugin config
	*/
	public java.util.Date getModifiedDate() {
		return _paymentPluginConfig.getModifiedDate();
	}

	/**
	* Sets the modified date of this payment plugin config.
	*
	* @param modifiedDate the modified date of this payment plugin config
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_paymentPluginConfig.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the seller ID of this payment plugin config.
	*
	* @return the seller ID of this payment plugin config
	*/
	public long getSellerId() {
		return _paymentPluginConfig.getSellerId();
	}

	/**
	* Sets the seller ID of this payment plugin config.
	*
	* @param sellerId the seller ID of this payment plugin config
	*/
	public void setSellerId(long sellerId) {
		_paymentPluginConfig.setSellerId(sellerId);
	}

	/**
	* Returns the payment plugin ID of this payment plugin config.
	*
	* @return the payment plugin ID of this payment plugin config
	*/
	public long getPaymentPluginId() {
		return _paymentPluginConfig.getPaymentPluginId();
	}

	/**
	* Sets the payment plugin ID of this payment plugin config.
	*
	* @param paymentPluginId the payment plugin ID of this payment plugin config
	*/
	public void setPaymentPluginId(long paymentPluginId) {
		_paymentPluginConfig.setPaymentPluginId(paymentPluginId);
	}

	/**
	* Returns the config of this payment plugin config.
	*
	* @return the config of this payment plugin config
	*/
	public java.lang.String getConfig() {
		return _paymentPluginConfig.getConfig();
	}

	/**
	* Sets the config of this payment plugin config.
	*
	* @param config the config of this payment plugin config
	*/
	public void setConfig(java.lang.String config) {
		_paymentPluginConfig.setConfig(config);
	}

	/**
	* Returns the configured of this payment plugin config.
	*
	* @return the configured of this payment plugin config
	*/
	public boolean getConfigured() {
		return _paymentPluginConfig.getConfigured();
	}

	/**
	* Returns <code>true</code> if this payment plugin config is configured.
	*
	* @return <code>true</code> if this payment plugin config is configured; <code>false</code> otherwise
	*/
	public boolean isConfigured() {
		return _paymentPluginConfig.isConfigured();
	}

	/**
	* Sets whether this payment plugin config is configured.
	*
	* @param configured the configured of this payment plugin config
	*/
	public void setConfigured(boolean configured) {
		_paymentPluginConfig.setConfigured(configured);
	}

	/**
	* Returns the default plugin of this payment plugin config.
	*
	* @return the default plugin of this payment plugin config
	*/
	public boolean getDefaultPlugin() {
		return _paymentPluginConfig.getDefaultPlugin();
	}

	/**
	* Returns <code>true</code> if this payment plugin config is default plugin.
	*
	* @return <code>true</code> if this payment plugin config is default plugin; <code>false</code> otherwise
	*/
	public boolean isDefaultPlugin() {
		return _paymentPluginConfig.isDefaultPlugin();
	}

	/**
	* Sets whether this payment plugin config is default plugin.
	*
	* @param defaultPlugin the default plugin of this payment plugin config
	*/
	public void setDefaultPlugin(boolean defaultPlugin) {
		_paymentPluginConfig.setDefaultPlugin(defaultPlugin);
	}

	public boolean isNew() {
		return _paymentPluginConfig.isNew();
	}

	public void setNew(boolean n) {
		_paymentPluginConfig.setNew(n);
	}

	public boolean isCachedModel() {
		return _paymentPluginConfig.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_paymentPluginConfig.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _paymentPluginConfig.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _paymentPluginConfig.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_paymentPluginConfig.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _paymentPluginConfig.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_paymentPluginConfig.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new PaymentPluginConfigWrapper((PaymentPluginConfig)_paymentPluginConfig.clone());
	}

	public int compareTo(
		com.beorn.onlinepayment.model.PaymentPluginConfig paymentPluginConfig) {
		return _paymentPluginConfig.compareTo(paymentPluginConfig);
	}

	@Override
	public int hashCode() {
		return _paymentPluginConfig.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.beorn.onlinepayment.model.PaymentPluginConfig> toCacheModel() {
		return _paymentPluginConfig.toCacheModel();
	}

	public com.beorn.onlinepayment.model.PaymentPluginConfig toEscapedModel() {
		return new PaymentPluginConfigWrapper(_paymentPluginConfig.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _paymentPluginConfig.toString();
	}

	public java.lang.String toXmlString() {
		return _paymentPluginConfig.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_paymentPluginConfig.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public PaymentPluginConfig getWrappedPaymentPluginConfig() {
		return _paymentPluginConfig;
	}

	public PaymentPluginConfig getWrappedModel() {
		return _paymentPluginConfig;
	}

	public void resetOriginalValues() {
		_paymentPluginConfig.resetOriginalValues();
	}

	private PaymentPluginConfig _paymentPluginConfig;
}