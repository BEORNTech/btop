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
 * This class is a wrapper for {@link Transaction}.
 * </p>
 *
 * @author    Sebastien Meunier
 * @see       Transaction
 * @generated
 */
public class TransactionWrapper implements Transaction,
	ModelWrapper<Transaction> {
	public TransactionWrapper(Transaction transaction) {
		_transaction = transaction;
	}

	public Class<?> getModelClass() {
		return Transaction.class;
	}

	public String getModelClassName() {
		return Transaction.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("transactionId", getTransactionId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("applicationId", getApplicationId());
		attributes.put("sellerId", getSellerId());
		attributes.put("amount", getAmount());
		attributes.put("currencyCode", getCurrencyCode());
		attributes.put("status", getStatus());
		attributes.put("paymentApplicationId", getPaymentApplicationId());
		attributes.put("amountPaid", getAmountPaid());
		attributes.put("amountRefunded", getAmountRefunded());

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

		Long transactionId = (Long)attributes.get("transactionId");

		if (transactionId != null) {
			setTransactionId(transactionId);
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

		Long sellerId = (Long)attributes.get("sellerId");

		if (sellerId != null) {
			setSellerId(sellerId);
		}

		Double amount = (Double)attributes.get("amount");

		if (amount != null) {
			setAmount(amount);
		}

		String currencyCode = (String)attributes.get("currencyCode");

		if (currencyCode != null) {
			setCurrencyCode(currencyCode);
		}

		Long status = (Long)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		String paymentApplicationId = (String)attributes.get(
				"paymentApplicationId");

		if (paymentApplicationId != null) {
			setPaymentApplicationId(paymentApplicationId);
		}

		Double amountPaid = (Double)attributes.get("amountPaid");

		if (amountPaid != null) {
			setAmountPaid(amountPaid);
		}

		Double amountRefunded = (Double)attributes.get("amountRefunded");

		if (amountRefunded != null) {
			setAmountRefunded(amountRefunded);
		}
	}

	/**
	* Returns the primary key of this transaction.
	*
	* @return the primary key of this transaction
	*/
	public long getPrimaryKey() {
		return _transaction.getPrimaryKey();
	}

	/**
	* Sets the primary key of this transaction.
	*
	* @param primaryKey the primary key of this transaction
	*/
	public void setPrimaryKey(long primaryKey) {
		_transaction.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this transaction.
	*
	* @return the uuid of this transaction
	*/
	public java.lang.String getUuid() {
		return _transaction.getUuid();
	}

	/**
	* Sets the uuid of this transaction.
	*
	* @param uuid the uuid of this transaction
	*/
	public void setUuid(java.lang.String uuid) {
		_transaction.setUuid(uuid);
	}

	/**
	* Returns the company ID of this transaction.
	*
	* @return the company ID of this transaction
	*/
	public long getCompanyId() {
		return _transaction.getCompanyId();
	}

	/**
	* Sets the company ID of this transaction.
	*
	* @param companyId the company ID of this transaction
	*/
	public void setCompanyId(long companyId) {
		_transaction.setCompanyId(companyId);
	}

	/**
	* Returns the group ID of this transaction.
	*
	* @return the group ID of this transaction
	*/
	public long getGroupId() {
		return _transaction.getGroupId();
	}

	/**
	* Sets the group ID of this transaction.
	*
	* @param groupId the group ID of this transaction
	*/
	public void setGroupId(long groupId) {
		_transaction.setGroupId(groupId);
	}

	/**
	* Returns the user ID of this transaction.
	*
	* @return the user ID of this transaction
	*/
	public long getUserId() {
		return _transaction.getUserId();
	}

	/**
	* Sets the user ID of this transaction.
	*
	* @param userId the user ID of this transaction
	*/
	public void setUserId(long userId) {
		_transaction.setUserId(userId);
	}

	/**
	* Returns the user uuid of this transaction.
	*
	* @return the user uuid of this transaction
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _transaction.getUserUuid();
	}

	/**
	* Sets the user uuid of this transaction.
	*
	* @param userUuid the user uuid of this transaction
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_transaction.setUserUuid(userUuid);
	}

	/**
	* Returns the transaction ID of this transaction.
	*
	* @return the transaction ID of this transaction
	*/
	public long getTransactionId() {
		return _transaction.getTransactionId();
	}

	/**
	* Sets the transaction ID of this transaction.
	*
	* @param transactionId the transaction ID of this transaction
	*/
	public void setTransactionId(long transactionId) {
		_transaction.setTransactionId(transactionId);
	}

	/**
	* Returns the create date of this transaction.
	*
	* @return the create date of this transaction
	*/
	public java.util.Date getCreateDate() {
		return _transaction.getCreateDate();
	}

	/**
	* Sets the create date of this transaction.
	*
	* @param createDate the create date of this transaction
	*/
	public void setCreateDate(java.util.Date createDate) {
		_transaction.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this transaction.
	*
	* @return the modified date of this transaction
	*/
	public java.util.Date getModifiedDate() {
		return _transaction.getModifiedDate();
	}

	/**
	* Sets the modified date of this transaction.
	*
	* @param modifiedDate the modified date of this transaction
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_transaction.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the application ID of this transaction.
	*
	* @return the application ID of this transaction
	*/
	public java.lang.String getApplicationId() {
		return _transaction.getApplicationId();
	}

	/**
	* Sets the application ID of this transaction.
	*
	* @param applicationId the application ID of this transaction
	*/
	public void setApplicationId(java.lang.String applicationId) {
		_transaction.setApplicationId(applicationId);
	}

	/**
	* Returns the seller ID of this transaction.
	*
	* @return the seller ID of this transaction
	*/
	public long getSellerId() {
		return _transaction.getSellerId();
	}

	/**
	* Sets the seller ID of this transaction.
	*
	* @param sellerId the seller ID of this transaction
	*/
	public void setSellerId(long sellerId) {
		_transaction.setSellerId(sellerId);
	}

	/**
	* Returns the amount of this transaction.
	*
	* @return the amount of this transaction
	*/
	public double getAmount() {
		return _transaction.getAmount();
	}

	/**
	* Sets the amount of this transaction.
	*
	* @param amount the amount of this transaction
	*/
	public void setAmount(double amount) {
		_transaction.setAmount(amount);
	}

	/**
	* Returns the currency code of this transaction.
	*
	* @return the currency code of this transaction
	*/
	public java.lang.String getCurrencyCode() {
		return _transaction.getCurrencyCode();
	}

	/**
	* Sets the currency code of this transaction.
	*
	* @param currencyCode the currency code of this transaction
	*/
	public void setCurrencyCode(java.lang.String currencyCode) {
		_transaction.setCurrencyCode(currencyCode);
	}

	/**
	* Returns the status of this transaction.
	*
	* @return the status of this transaction
	*/
	public long getStatus() {
		return _transaction.getStatus();
	}

	/**
	* Sets the status of this transaction.
	*
	* @param status the status of this transaction
	*/
	public void setStatus(long status) {
		_transaction.setStatus(status);
	}

	/**
	* Returns the payment application ID of this transaction.
	*
	* @return the payment application ID of this transaction
	*/
	public java.lang.String getPaymentApplicationId() {
		return _transaction.getPaymentApplicationId();
	}

	/**
	* Sets the payment application ID of this transaction.
	*
	* @param paymentApplicationId the payment application ID of this transaction
	*/
	public void setPaymentApplicationId(java.lang.String paymentApplicationId) {
		_transaction.setPaymentApplicationId(paymentApplicationId);
	}

	/**
	* Returns the amount paid of this transaction.
	*
	* @return the amount paid of this transaction
	*/
	public double getAmountPaid() {
		return _transaction.getAmountPaid();
	}

	/**
	* Sets the amount paid of this transaction.
	*
	* @param amountPaid the amount paid of this transaction
	*/
	public void setAmountPaid(double amountPaid) {
		_transaction.setAmountPaid(amountPaid);
	}

	/**
	* Returns the amount refunded of this transaction.
	*
	* @return the amount refunded of this transaction
	*/
	public double getAmountRefunded() {
		return _transaction.getAmountRefunded();
	}

	/**
	* Sets the amount refunded of this transaction.
	*
	* @param amountRefunded the amount refunded of this transaction
	*/
	public void setAmountRefunded(double amountRefunded) {
		_transaction.setAmountRefunded(amountRefunded);
	}

	public boolean isNew() {
		return _transaction.isNew();
	}

	public void setNew(boolean n) {
		_transaction.setNew(n);
	}

	public boolean isCachedModel() {
		return _transaction.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_transaction.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _transaction.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _transaction.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_transaction.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _transaction.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_transaction.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new TransactionWrapper((Transaction)_transaction.clone());
	}

	public int compareTo(com.beorn.onlinepayment.model.Transaction transaction) {
		return _transaction.compareTo(transaction);
	}

	@Override
	public int hashCode() {
		return _transaction.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.beorn.onlinepayment.model.Transaction> toCacheModel() {
		return _transaction.toCacheModel();
	}

	public com.beorn.onlinepayment.model.Transaction toEscapedModel() {
		return new TransactionWrapper(_transaction.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _transaction.toString();
	}

	public java.lang.String toXmlString() {
		return _transaction.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_transaction.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public Transaction getWrappedTransaction() {
		return _transaction;
	}

	public Transaction getWrappedModel() {
		return _transaction;
	}

	public void resetOriginalValues() {
		_transaction.resetOriginalValues();
	}

	private Transaction _transaction;
}