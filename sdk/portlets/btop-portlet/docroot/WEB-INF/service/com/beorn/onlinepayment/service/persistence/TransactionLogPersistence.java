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

package com.beorn.onlinepayment.service.persistence;

import com.beorn.onlinepayment.model.TransactionLog;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the transaction log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Sebastien Meunier
 * @see TransactionLogPersistenceImpl
 * @see TransactionLogUtil
 * @generated
 */
public interface TransactionLogPersistence extends BasePersistence<TransactionLog> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TransactionLogUtil} to access the transaction log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the transaction log in the entity cache if it is enabled.
	*
	* @param transactionLog the transaction log
	*/
	public void cacheResult(
		com.beorn.onlinepayment.model.TransactionLog transactionLog);

	/**
	* Caches the transaction logs in the entity cache if it is enabled.
	*
	* @param transactionLogs the transaction logs
	*/
	public void cacheResult(
		java.util.List<com.beorn.onlinepayment.model.TransactionLog> transactionLogs);

	/**
	* Creates a new transaction log with the primary key. Does not add the transaction log to the database.
	*
	* @param transactionLogId the primary key for the new transaction log
	* @return the new transaction log
	*/
	public com.beorn.onlinepayment.model.TransactionLog create(
		long transactionLogId);

	/**
	* Removes the transaction log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param transactionLogId the primary key of the transaction log
	* @return the transaction log that was removed
	* @throws com.beorn.onlinepayment.NoSuchTransactionLogException if a transaction log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.TransactionLog remove(
		long transactionLogId)
		throws com.beorn.onlinepayment.NoSuchTransactionLogException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.beorn.onlinepayment.model.TransactionLog updateImpl(
		com.beorn.onlinepayment.model.TransactionLog transactionLog,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the transaction log with the primary key or throws a {@link com.beorn.onlinepayment.NoSuchTransactionLogException} if it could not be found.
	*
	* @param transactionLogId the primary key of the transaction log
	* @return the transaction log
	* @throws com.beorn.onlinepayment.NoSuchTransactionLogException if a transaction log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.TransactionLog findByPrimaryKey(
		long transactionLogId)
		throws com.beorn.onlinepayment.NoSuchTransactionLogException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the transaction log with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param transactionLogId the primary key of the transaction log
	* @return the transaction log, or <code>null</code> if a transaction log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.TransactionLog fetchByPrimaryKey(
		long transactionLogId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the transaction logs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching transaction logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.beorn.onlinepayment.model.TransactionLog> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the transaction logs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of transaction logs
	* @param end the upper bound of the range of transaction logs (not inclusive)
	* @return the range of matching transaction logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.beorn.onlinepayment.model.TransactionLog> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the transaction logs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of transaction logs
	* @param end the upper bound of the range of transaction logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching transaction logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.beorn.onlinepayment.model.TransactionLog> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first transaction log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching transaction log
	* @throws com.beorn.onlinepayment.NoSuchTransactionLogException if a matching transaction log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.TransactionLog findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchTransactionLogException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first transaction log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching transaction log, or <code>null</code> if a matching transaction log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.TransactionLog fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last transaction log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching transaction log
	* @throws com.beorn.onlinepayment.NoSuchTransactionLogException if a matching transaction log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.TransactionLog findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchTransactionLogException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last transaction log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching transaction log, or <code>null</code> if a matching transaction log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.TransactionLog fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the transaction logs before and after the current transaction log in the ordered set where uuid = &#63;.
	*
	* @param transactionLogId the primary key of the current transaction log
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next transaction log
	* @throws com.beorn.onlinepayment.NoSuchTransactionLogException if a transaction log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.TransactionLog[] findByUuid_PrevAndNext(
		long transactionLogId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchTransactionLogException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the transaction log where uuid = &#63; and groupId = &#63; or throws a {@link com.beorn.onlinepayment.NoSuchTransactionLogException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching transaction log
	* @throws com.beorn.onlinepayment.NoSuchTransactionLogException if a matching transaction log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.TransactionLog findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.beorn.onlinepayment.NoSuchTransactionLogException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the transaction log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching transaction log, or <code>null</code> if a matching transaction log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.TransactionLog fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the transaction log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching transaction log, or <code>null</code> if a matching transaction log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.TransactionLog fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the transaction logs where paymentApplicationId = &#63;.
	*
	* @param paymentApplicationId the payment application ID
	* @return the matching transaction logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.beorn.onlinepayment.model.TransactionLog> findByPaymentApplicationId(
		java.lang.String paymentApplicationId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the transaction logs where paymentApplicationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param paymentApplicationId the payment application ID
	* @param start the lower bound of the range of transaction logs
	* @param end the upper bound of the range of transaction logs (not inclusive)
	* @return the range of matching transaction logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.beorn.onlinepayment.model.TransactionLog> findByPaymentApplicationId(
		java.lang.String paymentApplicationId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the transaction logs where paymentApplicationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param paymentApplicationId the payment application ID
	* @param start the lower bound of the range of transaction logs
	* @param end the upper bound of the range of transaction logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching transaction logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.beorn.onlinepayment.model.TransactionLog> findByPaymentApplicationId(
		java.lang.String paymentApplicationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first transaction log in the ordered set where paymentApplicationId = &#63;.
	*
	* @param paymentApplicationId the payment application ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching transaction log
	* @throws com.beorn.onlinepayment.NoSuchTransactionLogException if a matching transaction log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.TransactionLog findByPaymentApplicationId_First(
		java.lang.String paymentApplicationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchTransactionLogException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first transaction log in the ordered set where paymentApplicationId = &#63;.
	*
	* @param paymentApplicationId the payment application ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching transaction log, or <code>null</code> if a matching transaction log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.TransactionLog fetchByPaymentApplicationId_First(
		java.lang.String paymentApplicationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last transaction log in the ordered set where paymentApplicationId = &#63;.
	*
	* @param paymentApplicationId the payment application ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching transaction log
	* @throws com.beorn.onlinepayment.NoSuchTransactionLogException if a matching transaction log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.TransactionLog findByPaymentApplicationId_Last(
		java.lang.String paymentApplicationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchTransactionLogException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last transaction log in the ordered set where paymentApplicationId = &#63;.
	*
	* @param paymentApplicationId the payment application ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching transaction log, or <code>null</code> if a matching transaction log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.TransactionLog fetchByPaymentApplicationId_Last(
		java.lang.String paymentApplicationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the transaction logs before and after the current transaction log in the ordered set where paymentApplicationId = &#63;.
	*
	* @param transactionLogId the primary key of the current transaction log
	* @param paymentApplicationId the payment application ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next transaction log
	* @throws com.beorn.onlinepayment.NoSuchTransactionLogException if a transaction log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.TransactionLog[] findByPaymentApplicationId_PrevAndNext(
		long transactionLogId, java.lang.String paymentApplicationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchTransactionLogException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the transaction log where paymentApplicationId = &#63; and remoteId = &#63; or throws a {@link com.beorn.onlinepayment.NoSuchTransactionLogException} if it could not be found.
	*
	* @param paymentApplicationId the payment application ID
	* @param remoteId the remote ID
	* @return the matching transaction log
	* @throws com.beorn.onlinepayment.NoSuchTransactionLogException if a matching transaction log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.TransactionLog findByPaymentApplicationIdAndRemoteId(
		java.lang.String paymentApplicationId, java.lang.String remoteId)
		throws com.beorn.onlinepayment.NoSuchTransactionLogException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the transaction log where paymentApplicationId = &#63; and remoteId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param paymentApplicationId the payment application ID
	* @param remoteId the remote ID
	* @return the matching transaction log, or <code>null</code> if a matching transaction log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.TransactionLog fetchByPaymentApplicationIdAndRemoteId(
		java.lang.String paymentApplicationId, java.lang.String remoteId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the transaction log where paymentApplicationId = &#63; and remoteId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param paymentApplicationId the payment application ID
	* @param remoteId the remote ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching transaction log, or <code>null</code> if a matching transaction log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.TransactionLog fetchByPaymentApplicationIdAndRemoteId(
		java.lang.String paymentApplicationId, java.lang.String remoteId,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the transaction logs.
	*
	* @return the transaction logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.beorn.onlinepayment.model.TransactionLog> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the transaction logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of transaction logs
	* @param end the upper bound of the range of transaction logs (not inclusive)
	* @return the range of transaction logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.beorn.onlinepayment.model.TransactionLog> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the transaction logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of transaction logs
	* @param end the upper bound of the range of transaction logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of transaction logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.beorn.onlinepayment.model.TransactionLog> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the transaction logs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the transaction log where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the transaction log that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.TransactionLog removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.beorn.onlinepayment.NoSuchTransactionLogException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the transaction logs where paymentApplicationId = &#63; from the database.
	*
	* @param paymentApplicationId the payment application ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByPaymentApplicationId(
		java.lang.String paymentApplicationId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the transaction log where paymentApplicationId = &#63; and remoteId = &#63; from the database.
	*
	* @param paymentApplicationId the payment application ID
	* @param remoteId the remote ID
	* @return the transaction log that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.TransactionLog removeByPaymentApplicationIdAndRemoteId(
		java.lang.String paymentApplicationId, java.lang.String remoteId)
		throws com.beorn.onlinepayment.NoSuchTransactionLogException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the transaction logs from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of transaction logs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching transaction logs
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of transaction logs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching transaction logs
	* @throws SystemException if a system exception occurred
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of transaction logs where paymentApplicationId = &#63;.
	*
	* @param paymentApplicationId the payment application ID
	* @return the number of matching transaction logs
	* @throws SystemException if a system exception occurred
	*/
	public int countByPaymentApplicationId(
		java.lang.String paymentApplicationId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of transaction logs where paymentApplicationId = &#63; and remoteId = &#63;.
	*
	* @param paymentApplicationId the payment application ID
	* @param remoteId the remote ID
	* @return the number of matching transaction logs
	* @throws SystemException if a system exception occurred
	*/
	public int countByPaymentApplicationIdAndRemoteId(
		java.lang.String paymentApplicationId, java.lang.String remoteId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of transaction logs.
	*
	* @return the number of transaction logs
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}