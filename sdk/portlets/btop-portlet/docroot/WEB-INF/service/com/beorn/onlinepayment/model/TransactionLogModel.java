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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the TransactionLog service. Represents a row in the &quot;Payment_TransactionLog&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.beorn.onlinepayment.model.impl.TransactionLogModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.beorn.onlinepayment.model.impl.TransactionLogImpl}.
 * </p>
 *
 * @author Sebastien Meunier
 * @see TransactionLog
 * @see com.beorn.onlinepayment.model.impl.TransactionLogImpl
 * @see com.beorn.onlinepayment.model.impl.TransactionLogModelImpl
 * @generated
 */
public interface TransactionLogModel extends BaseModel<TransactionLog> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a transaction log model instance should use the {@link TransactionLog} interface instead.
	 */

	/**
	 * Returns the primary key of this transaction log.
	 *
	 * @return the primary key of this transaction log
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this transaction log.
	 *
	 * @param primaryKey the primary key of this transaction log
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this transaction log.
	 *
	 * @return the uuid of this transaction log
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this transaction log.
	 *
	 * @param uuid the uuid of this transaction log
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the company ID of this transaction log.
	 *
	 * @return the company ID of this transaction log
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this transaction log.
	 *
	 * @param companyId the company ID of this transaction log
	 */
	public void setCompanyId(long companyId);

	/**
	 * Returns the group ID of this transaction log.
	 *
	 * @return the group ID of this transaction log
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this transaction log.
	 *
	 * @param groupId the group ID of this transaction log
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the user ID of this transaction log.
	 *
	 * @return the user ID of this transaction log
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this transaction log.
	 *
	 * @param userId the user ID of this transaction log
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this transaction log.
	 *
	 * @return the user uuid of this transaction log
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this transaction log.
	 *
	 * @param userUuid the user uuid of this transaction log
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the transaction log ID of this transaction log.
	 *
	 * @return the transaction log ID of this transaction log
	 */
	public long getTransactionLogId();

	/**
	 * Sets the transaction log ID of this transaction log.
	 *
	 * @param transactionLogId the transaction log ID of this transaction log
	 */
	public void setTransactionLogId(long transactionLogId);

	/**
	 * Returns the create date of this transaction log.
	 *
	 * @return the create date of this transaction log
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this transaction log.
	 *
	 * @param createDate the create date of this transaction log
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this transaction log.
	 *
	 * @return the modified date of this transaction log
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this transaction log.
	 *
	 * @param modifiedDate the modified date of this transaction log
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the transaction ID of this transaction log.
	 *
	 * @return the transaction ID of this transaction log
	 */
	public long getTransactionId();

	/**
	 * Sets the transaction ID of this transaction log.
	 *
	 * @param transactionId the transaction ID of this transaction log
	 */
	public void setTransactionId(long transactionId);

	/**
	 * Returns the payment application ID of this transaction log.
	 *
	 * @return the payment application ID of this transaction log
	 */
	@AutoEscape
	public String getPaymentApplicationId();

	/**
	 * Sets the payment application ID of this transaction log.
	 *
	 * @param paymentApplicationId the payment application ID of this transaction log
	 */
	public void setPaymentApplicationId(String paymentApplicationId);

	/**
	 * Returns the remote ID of this transaction log.
	 *
	 * @return the remote ID of this transaction log
	 */
	@AutoEscape
	public String getRemoteId();

	/**
	 * Sets the remote ID of this transaction log.
	 *
	 * @param remoteId the remote ID of this transaction log
	 */
	public void setRemoteId(String remoteId);

	/**
	 * Returns the amount of this transaction log.
	 *
	 * @return the amount of this transaction log
	 */
	public double getAmount();

	/**
	 * Sets the amount of this transaction log.
	 *
	 * @param amount the amount of this transaction log
	 */
	public void setAmount(double amount);

	/**
	 * Returns the type of this transaction log.
	 *
	 * @return the type of this transaction log
	 */
	public long getType();

	/**
	 * Sets the type of this transaction log.
	 *
	 * @param type the type of this transaction log
	 */
	public void setType(long type);

	public boolean isNew();

	public void setNew(boolean n);

	public boolean isCachedModel();

	public void setCachedModel(boolean cachedModel);

	public boolean isEscapedModel();

	public Serializable getPrimaryKeyObj();

	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	public ExpandoBridge getExpandoBridge();

	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public Object clone();

	public int compareTo(TransactionLog transactionLog);

	public int hashCode();

	public CacheModel<TransactionLog> toCacheModel();

	public TransactionLog toEscapedModel();

	public String toString();

	public String toXmlString();
}