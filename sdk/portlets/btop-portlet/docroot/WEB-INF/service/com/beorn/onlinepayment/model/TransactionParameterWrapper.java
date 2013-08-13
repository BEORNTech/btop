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
 * This class is a wrapper for {@link TransactionParameter}.
 * </p>
 *
 * @author    Sebastien Meunier
 * @see       TransactionParameter
 * @generated
 */
public class TransactionParameterWrapper implements TransactionParameter,
	ModelWrapper<TransactionParameter> {
	public TransactionParameterWrapper(
		TransactionParameter transactionParameter) {
		_transactionParameter = transactionParameter;
	}

	public Class<?> getModelClass() {
		return TransactionParameter.class;
	}

	public String getModelClassName() {
		return TransactionParameter.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("transactionParameterId", getTransactionParameterId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("transactionId", getTransactionId());
		attributes.put("key", getKey());
		attributes.put("value", getValue());

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

		Long transactionParameterId = (Long)attributes.get(
				"transactionParameterId");

		if (transactionParameterId != null) {
			setTransactionParameterId(transactionParameterId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long transactionId = (Long)attributes.get("transactionId");

		if (transactionId != null) {
			setTransactionId(transactionId);
		}

		String key = (String)attributes.get("key");

		if (key != null) {
			setKey(key);
		}

		String value = (String)attributes.get("value");

		if (value != null) {
			setValue(value);
		}
	}

	/**
	* Returns the primary key of this transaction parameter.
	*
	* @return the primary key of this transaction parameter
	*/
	public long getPrimaryKey() {
		return _transactionParameter.getPrimaryKey();
	}

	/**
	* Sets the primary key of this transaction parameter.
	*
	* @param primaryKey the primary key of this transaction parameter
	*/
	public void setPrimaryKey(long primaryKey) {
		_transactionParameter.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this transaction parameter.
	*
	* @return the uuid of this transaction parameter
	*/
	public java.lang.String getUuid() {
		return _transactionParameter.getUuid();
	}

	/**
	* Sets the uuid of this transaction parameter.
	*
	* @param uuid the uuid of this transaction parameter
	*/
	public void setUuid(java.lang.String uuid) {
		_transactionParameter.setUuid(uuid);
	}

	/**
	* Returns the company ID of this transaction parameter.
	*
	* @return the company ID of this transaction parameter
	*/
	public long getCompanyId() {
		return _transactionParameter.getCompanyId();
	}

	/**
	* Sets the company ID of this transaction parameter.
	*
	* @param companyId the company ID of this transaction parameter
	*/
	public void setCompanyId(long companyId) {
		_transactionParameter.setCompanyId(companyId);
	}

	/**
	* Returns the group ID of this transaction parameter.
	*
	* @return the group ID of this transaction parameter
	*/
	public long getGroupId() {
		return _transactionParameter.getGroupId();
	}

	/**
	* Sets the group ID of this transaction parameter.
	*
	* @param groupId the group ID of this transaction parameter
	*/
	public void setGroupId(long groupId) {
		_transactionParameter.setGroupId(groupId);
	}

	/**
	* Returns the user ID of this transaction parameter.
	*
	* @return the user ID of this transaction parameter
	*/
	public long getUserId() {
		return _transactionParameter.getUserId();
	}

	/**
	* Sets the user ID of this transaction parameter.
	*
	* @param userId the user ID of this transaction parameter
	*/
	public void setUserId(long userId) {
		_transactionParameter.setUserId(userId);
	}

	/**
	* Returns the user uuid of this transaction parameter.
	*
	* @return the user uuid of this transaction parameter
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _transactionParameter.getUserUuid();
	}

	/**
	* Sets the user uuid of this transaction parameter.
	*
	* @param userUuid the user uuid of this transaction parameter
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_transactionParameter.setUserUuid(userUuid);
	}

	/**
	* Returns the transaction parameter ID of this transaction parameter.
	*
	* @return the transaction parameter ID of this transaction parameter
	*/
	public long getTransactionParameterId() {
		return _transactionParameter.getTransactionParameterId();
	}

	/**
	* Sets the transaction parameter ID of this transaction parameter.
	*
	* @param transactionParameterId the transaction parameter ID of this transaction parameter
	*/
	public void setTransactionParameterId(long transactionParameterId) {
		_transactionParameter.setTransactionParameterId(transactionParameterId);
	}

	/**
	* Returns the create date of this transaction parameter.
	*
	* @return the create date of this transaction parameter
	*/
	public java.util.Date getCreateDate() {
		return _transactionParameter.getCreateDate();
	}

	/**
	* Sets the create date of this transaction parameter.
	*
	* @param createDate the create date of this transaction parameter
	*/
	public void setCreateDate(java.util.Date createDate) {
		_transactionParameter.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this transaction parameter.
	*
	* @return the modified date of this transaction parameter
	*/
	public java.util.Date getModifiedDate() {
		return _transactionParameter.getModifiedDate();
	}

	/**
	* Sets the modified date of this transaction parameter.
	*
	* @param modifiedDate the modified date of this transaction parameter
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_transactionParameter.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the transaction ID of this transaction parameter.
	*
	* @return the transaction ID of this transaction parameter
	*/
	public long getTransactionId() {
		return _transactionParameter.getTransactionId();
	}

	/**
	* Sets the transaction ID of this transaction parameter.
	*
	* @param transactionId the transaction ID of this transaction parameter
	*/
	public void setTransactionId(long transactionId) {
		_transactionParameter.setTransactionId(transactionId);
	}

	/**
	* Returns the key of this transaction parameter.
	*
	* @return the key of this transaction parameter
	*/
	public java.lang.String getKey() {
		return _transactionParameter.getKey();
	}

	/**
	* Sets the key of this transaction parameter.
	*
	* @param key the key of this transaction parameter
	*/
	public void setKey(java.lang.String key) {
		_transactionParameter.setKey(key);
	}

	/**
	* Returns the value of this transaction parameter.
	*
	* @return the value of this transaction parameter
	*/
	public java.lang.String getValue() {
		return _transactionParameter.getValue();
	}

	/**
	* Sets the value of this transaction parameter.
	*
	* @param value the value of this transaction parameter
	*/
	public void setValue(java.lang.String value) {
		_transactionParameter.setValue(value);
	}

	public boolean isNew() {
		return _transactionParameter.isNew();
	}

	public void setNew(boolean n) {
		_transactionParameter.setNew(n);
	}

	public boolean isCachedModel() {
		return _transactionParameter.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_transactionParameter.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _transactionParameter.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _transactionParameter.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_transactionParameter.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _transactionParameter.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_transactionParameter.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new TransactionParameterWrapper((TransactionParameter)_transactionParameter.clone());
	}

	public int compareTo(
		com.beorn.onlinepayment.model.TransactionParameter transactionParameter) {
		return _transactionParameter.compareTo(transactionParameter);
	}

	@Override
	public int hashCode() {
		return _transactionParameter.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.beorn.onlinepayment.model.TransactionParameter> toCacheModel() {
		return _transactionParameter.toCacheModel();
	}

	public com.beorn.onlinepayment.model.TransactionParameter toEscapedModel() {
		return new TransactionParameterWrapper(_transactionParameter.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _transactionParameter.toString();
	}

	public java.lang.String toXmlString() {
		return _transactionParameter.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_transactionParameter.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public TransactionParameter getWrappedTransactionParameter() {
		return _transactionParameter;
	}

	public TransactionParameter getWrappedModel() {
		return _transactionParameter;
	}

	public void resetOriginalValues() {
		_transactionParameter.resetOriginalValues();
	}

	private TransactionParameter _transactionParameter;
}