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
 * This class is a wrapper for {@link TransactionLog}.
 * </p>
 *
 * @author    Sebastien Meunier
 * @see       TransactionLog
 * @generated
 */
public class TransactionLogWrapper implements TransactionLog,
	ModelWrapper<TransactionLog> {
	public TransactionLogWrapper(TransactionLog transactionLog) {
		_transactionLog = transactionLog;
	}

	public Class<?> getModelClass() {
		return TransactionLog.class;
	}

	public String getModelClassName() {
		return TransactionLog.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("transactionLogId", getTransactionLogId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("transactionId", getTransactionId());
		attributes.put("paymentApplicationId", getPaymentApplicationId());
		attributes.put("remoteId", getRemoteId());
		attributes.put("amount", getAmount());
		attributes.put("type", getType());

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

		Long transactionLogId = (Long)attributes.get("transactionLogId");

		if (transactionLogId != null) {
			setTransactionLogId(transactionLogId);
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

		String paymentApplicationId = (String)attributes.get(
				"paymentApplicationId");

		if (paymentApplicationId != null) {
			setPaymentApplicationId(paymentApplicationId);
		}

		String remoteId = (String)attributes.get("remoteId");

		if (remoteId != null) {
			setRemoteId(remoteId);
		}

		Double amount = (Double)attributes.get("amount");

		if (amount != null) {
			setAmount(amount);
		}

		Long type = (Long)attributes.get("type");

		if (type != null) {
			setType(type);
		}
	}

	/**
	* Returns the primary key of this transaction log.
	*
	* @return the primary key of this transaction log
	*/
	public long getPrimaryKey() {
		return _transactionLog.getPrimaryKey();
	}

	/**
	* Sets the primary key of this transaction log.
	*
	* @param primaryKey the primary key of this transaction log
	*/
	public void setPrimaryKey(long primaryKey) {
		_transactionLog.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this transaction log.
	*
	* @return the uuid of this transaction log
	*/
	public java.lang.String getUuid() {
		return _transactionLog.getUuid();
	}

	/**
	* Sets the uuid of this transaction log.
	*
	* @param uuid the uuid of this transaction log
	*/
	public void setUuid(java.lang.String uuid) {
		_transactionLog.setUuid(uuid);
	}

	/**
	* Returns the company ID of this transaction log.
	*
	* @return the company ID of this transaction log
	*/
	public long getCompanyId() {
		return _transactionLog.getCompanyId();
	}

	/**
	* Sets the company ID of this transaction log.
	*
	* @param companyId the company ID of this transaction log
	*/
	public void setCompanyId(long companyId) {
		_transactionLog.setCompanyId(companyId);
	}

	/**
	* Returns the group ID of this transaction log.
	*
	* @return the group ID of this transaction log
	*/
	public long getGroupId() {
		return _transactionLog.getGroupId();
	}

	/**
	* Sets the group ID of this transaction log.
	*
	* @param groupId the group ID of this transaction log
	*/
	public void setGroupId(long groupId) {
		_transactionLog.setGroupId(groupId);
	}

	/**
	* Returns the user ID of this transaction log.
	*
	* @return the user ID of this transaction log
	*/
	public long getUserId() {
		return _transactionLog.getUserId();
	}

	/**
	* Sets the user ID of this transaction log.
	*
	* @param userId the user ID of this transaction log
	*/
	public void setUserId(long userId) {
		_transactionLog.setUserId(userId);
	}

	/**
	* Returns the user uuid of this transaction log.
	*
	* @return the user uuid of this transaction log
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _transactionLog.getUserUuid();
	}

	/**
	* Sets the user uuid of this transaction log.
	*
	* @param userUuid the user uuid of this transaction log
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_transactionLog.setUserUuid(userUuid);
	}

	/**
	* Returns the transaction log ID of this transaction log.
	*
	* @return the transaction log ID of this transaction log
	*/
	public long getTransactionLogId() {
		return _transactionLog.getTransactionLogId();
	}

	/**
	* Sets the transaction log ID of this transaction log.
	*
	* @param transactionLogId the transaction log ID of this transaction log
	*/
	public void setTransactionLogId(long transactionLogId) {
		_transactionLog.setTransactionLogId(transactionLogId);
	}

	/**
	* Returns the create date of this transaction log.
	*
	* @return the create date of this transaction log
	*/
	public java.util.Date getCreateDate() {
		return _transactionLog.getCreateDate();
	}

	/**
	* Sets the create date of this transaction log.
	*
	* @param createDate the create date of this transaction log
	*/
	public void setCreateDate(java.util.Date createDate) {
		_transactionLog.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this transaction log.
	*
	* @return the modified date of this transaction log
	*/
	public java.util.Date getModifiedDate() {
		return _transactionLog.getModifiedDate();
	}

	/**
	* Sets the modified date of this transaction log.
	*
	* @param modifiedDate the modified date of this transaction log
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_transactionLog.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the transaction ID of this transaction log.
	*
	* @return the transaction ID of this transaction log
	*/
	public long getTransactionId() {
		return _transactionLog.getTransactionId();
	}

	/**
	* Sets the transaction ID of this transaction log.
	*
	* @param transactionId the transaction ID of this transaction log
	*/
	public void setTransactionId(long transactionId) {
		_transactionLog.setTransactionId(transactionId);
	}

	/**
	* Returns the payment application ID of this transaction log.
	*
	* @return the payment application ID of this transaction log
	*/
	public java.lang.String getPaymentApplicationId() {
		return _transactionLog.getPaymentApplicationId();
	}

	/**
	* Sets the payment application ID of this transaction log.
	*
	* @param paymentApplicationId the payment application ID of this transaction log
	*/
	public void setPaymentApplicationId(java.lang.String paymentApplicationId) {
		_transactionLog.setPaymentApplicationId(paymentApplicationId);
	}

	/**
	* Returns the remote ID of this transaction log.
	*
	* @return the remote ID of this transaction log
	*/
	public java.lang.String getRemoteId() {
		return _transactionLog.getRemoteId();
	}

	/**
	* Sets the remote ID of this transaction log.
	*
	* @param remoteId the remote ID of this transaction log
	*/
	public void setRemoteId(java.lang.String remoteId) {
		_transactionLog.setRemoteId(remoteId);
	}

	/**
	* Returns the amount of this transaction log.
	*
	* @return the amount of this transaction log
	*/
	public double getAmount() {
		return _transactionLog.getAmount();
	}

	/**
	* Sets the amount of this transaction log.
	*
	* @param amount the amount of this transaction log
	*/
	public void setAmount(double amount) {
		_transactionLog.setAmount(amount);
	}

	/**
	* Returns the type of this transaction log.
	*
	* @return the type of this transaction log
	*/
	public long getType() {
		return _transactionLog.getType();
	}

	/**
	* Sets the type of this transaction log.
	*
	* @param type the type of this transaction log
	*/
	public void setType(long type) {
		_transactionLog.setType(type);
	}

	public boolean isNew() {
		return _transactionLog.isNew();
	}

	public void setNew(boolean n) {
		_transactionLog.setNew(n);
	}

	public boolean isCachedModel() {
		return _transactionLog.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_transactionLog.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _transactionLog.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _transactionLog.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_transactionLog.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _transactionLog.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_transactionLog.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new TransactionLogWrapper((TransactionLog)_transactionLog.clone());
	}

	public int compareTo(
		com.beorn.onlinepayment.model.TransactionLog transactionLog) {
		return _transactionLog.compareTo(transactionLog);
	}

	@Override
	public int hashCode() {
		return _transactionLog.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.beorn.onlinepayment.model.TransactionLog> toCacheModel() {
		return _transactionLog.toCacheModel();
	}

	public com.beorn.onlinepayment.model.TransactionLog toEscapedModel() {
		return new TransactionLogWrapper(_transactionLog.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _transactionLog.toString();
	}

	public java.lang.String toXmlString() {
		return _transactionLog.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_transactionLog.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public TransactionLog getWrappedTransactionLog() {
		return _transactionLog;
	}

	public TransactionLog getWrappedModel() {
		return _transactionLog;
	}

	public void resetOriginalValues() {
		_transactionLog.resetOriginalValues();
	}

	private TransactionLog _transactionLog;
}