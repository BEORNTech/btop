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
 * This class is a wrapper for {@link OrderTransaction}.
 * </p>
 *
 * @author    Sebastien Meunier
 * @see       OrderTransaction
 * @generated
 */
public class OrderTransactionWrapper implements OrderTransaction,
	ModelWrapper<OrderTransaction> {
	public OrderTransactionWrapper(OrderTransaction orderTransaction) {
		_orderTransaction = orderTransaction;
	}

	public Class<?> getModelClass() {
		return OrderTransaction.class;
	}

	public String getModelClassName() {
		return OrderTransaction.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("orderId", getOrderId());
		attributes.put("transactionId", getTransactionId());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long orderId = (Long)attributes.get("orderId");

		if (orderId != null) {
			setOrderId(orderId);
		}

		Long transactionId = (Long)attributes.get("transactionId");

		if (transactionId != null) {
			setTransactionId(transactionId);
		}
	}

	/**
	* Returns the primary key of this order transaction.
	*
	* @return the primary key of this order transaction
	*/
	public long getPrimaryKey() {
		return _orderTransaction.getPrimaryKey();
	}

	/**
	* Sets the primary key of this order transaction.
	*
	* @param primaryKey the primary key of this order transaction
	*/
	public void setPrimaryKey(long primaryKey) {
		_orderTransaction.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this order transaction.
	*
	* @return the uuid of this order transaction
	*/
	public java.lang.String getUuid() {
		return _orderTransaction.getUuid();
	}

	/**
	* Sets the uuid of this order transaction.
	*
	* @param uuid the uuid of this order transaction
	*/
	public void setUuid(java.lang.String uuid) {
		_orderTransaction.setUuid(uuid);
	}

	/**
	* Returns the create date of this order transaction.
	*
	* @return the create date of this order transaction
	*/
	public java.util.Date getCreateDate() {
		return _orderTransaction.getCreateDate();
	}

	/**
	* Sets the create date of this order transaction.
	*
	* @param createDate the create date of this order transaction
	*/
	public void setCreateDate(java.util.Date createDate) {
		_orderTransaction.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this order transaction.
	*
	* @return the modified date of this order transaction
	*/
	public java.util.Date getModifiedDate() {
		return _orderTransaction.getModifiedDate();
	}

	/**
	* Sets the modified date of this order transaction.
	*
	* @param modifiedDate the modified date of this order transaction
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_orderTransaction.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the order ID of this order transaction.
	*
	* @return the order ID of this order transaction
	*/
	public long getOrderId() {
		return _orderTransaction.getOrderId();
	}

	/**
	* Sets the order ID of this order transaction.
	*
	* @param orderId the order ID of this order transaction
	*/
	public void setOrderId(long orderId) {
		_orderTransaction.setOrderId(orderId);
	}

	/**
	* Returns the transaction ID of this order transaction.
	*
	* @return the transaction ID of this order transaction
	*/
	public long getTransactionId() {
		return _orderTransaction.getTransactionId();
	}

	/**
	* Sets the transaction ID of this order transaction.
	*
	* @param transactionId the transaction ID of this order transaction
	*/
	public void setTransactionId(long transactionId) {
		_orderTransaction.setTransactionId(transactionId);
	}

	public boolean isNew() {
		return _orderTransaction.isNew();
	}

	public void setNew(boolean n) {
		_orderTransaction.setNew(n);
	}

	public boolean isCachedModel() {
		return _orderTransaction.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_orderTransaction.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _orderTransaction.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _orderTransaction.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_orderTransaction.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _orderTransaction.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_orderTransaction.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new OrderTransactionWrapper((OrderTransaction)_orderTransaction.clone());
	}

	public int compareTo(OrderTransaction orderTransaction) {
		return _orderTransaction.compareTo(orderTransaction);
	}

	@Override
	public int hashCode() {
		return _orderTransaction.hashCode();
	}

	public com.liferay.portal.model.CacheModel<OrderTransaction> toCacheModel() {
		return _orderTransaction.toCacheModel();
	}

	public OrderTransaction toEscapedModel() {
		return new OrderTransactionWrapper(_orderTransaction.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _orderTransaction.toString();
	}

	public java.lang.String toXmlString() {
		return _orderTransaction.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_orderTransaction.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public OrderTransaction getWrappedOrderTransaction() {
		return _orderTransaction;
	}

	public OrderTransaction getWrappedModel() {
		return _orderTransaction;
	}

	public void resetOriginalValues() {
		_orderTransaction.resetOriginalValues();
	}

	private OrderTransaction _orderTransaction;
}