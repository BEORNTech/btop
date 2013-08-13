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

import com.beorn.onlinepayment.model.Transaction;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the transaction service. This utility wraps {@link TransactionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Sebastien Meunier
 * @see TransactionPersistence
 * @see TransactionPersistenceImpl
 * @generated
 */
public class TransactionUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(Transaction transaction) {
		getPersistence().clearCache(transaction);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Transaction> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Transaction> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Transaction> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static Transaction update(Transaction transaction, boolean merge)
		throws SystemException {
		return getPersistence().update(transaction, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static Transaction update(Transaction transaction, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(transaction, merge, serviceContext);
	}

	/**
	* Caches the transaction in the entity cache if it is enabled.
	*
	* @param transaction the transaction
	*/
	public static void cacheResult(
		com.beorn.onlinepayment.model.Transaction transaction) {
		getPersistence().cacheResult(transaction);
	}

	/**
	* Caches the transactions in the entity cache if it is enabled.
	*
	* @param transactions the transactions
	*/
	public static void cacheResult(
		java.util.List<com.beorn.onlinepayment.model.Transaction> transactions) {
		getPersistence().cacheResult(transactions);
	}

	/**
	* Creates a new transaction with the primary key. Does not add the transaction to the database.
	*
	* @param transactionId the primary key for the new transaction
	* @return the new transaction
	*/
	public static com.beorn.onlinepayment.model.Transaction create(
		long transactionId) {
		return getPersistence().create(transactionId);
	}

	/**
	* Removes the transaction with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param transactionId the primary key of the transaction
	* @return the transaction that was removed
	* @throws com.beorn.onlinepayment.NoSuchTransactionException if a transaction with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction remove(
		long transactionId)
		throws com.beorn.onlinepayment.NoSuchTransactionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(transactionId);
	}

	public static com.beorn.onlinepayment.model.Transaction updateImpl(
		com.beorn.onlinepayment.model.Transaction transaction, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(transaction, merge);
	}

	/**
	* Returns the transaction with the primary key or throws a {@link com.beorn.onlinepayment.NoSuchTransactionException} if it could not be found.
	*
	* @param transactionId the primary key of the transaction
	* @return the transaction
	* @throws com.beorn.onlinepayment.NoSuchTransactionException if a transaction with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction findByPrimaryKey(
		long transactionId)
		throws com.beorn.onlinepayment.NoSuchTransactionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(transactionId);
	}

	/**
	* Returns the transaction with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param transactionId the primary key of the transaction
	* @return the transaction, or <code>null</code> if a transaction with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction fetchByPrimaryKey(
		long transactionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(transactionId);
	}

	/**
	* Returns all the transactions where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching transactions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.Transaction> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the transactions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of transactions
	* @param end the upper bound of the range of transactions (not inclusive)
	* @return the range of matching transactions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.Transaction> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the transactions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of transactions
	* @param end the upper bound of the range of transactions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching transactions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.Transaction> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first transaction in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching transaction
	* @throws com.beorn.onlinepayment.NoSuchTransactionException if a matching transaction could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchTransactionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first transaction in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching transaction, or <code>null</code> if a matching transaction could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last transaction in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching transaction
	* @throws com.beorn.onlinepayment.NoSuchTransactionException if a matching transaction could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchTransactionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last transaction in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching transaction, or <code>null</code> if a matching transaction could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the transactions before and after the current transaction in the ordered set where uuid = &#63;.
	*
	* @param transactionId the primary key of the current transaction
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next transaction
	* @throws com.beorn.onlinepayment.NoSuchTransactionException if a transaction with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction[] findByUuid_PrevAndNext(
		long transactionId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchTransactionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_PrevAndNext(transactionId, uuid,
			orderByComparator);
	}

	/**
	* Returns the transaction where uuid = &#63; and groupId = &#63; or throws a {@link com.beorn.onlinepayment.NoSuchTransactionException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching transaction
	* @throws com.beorn.onlinepayment.NoSuchTransactionException if a matching transaction could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.beorn.onlinepayment.NoSuchTransactionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the transaction where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching transaction, or <code>null</code> if a matching transaction could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the transaction where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching transaction, or <code>null</code> if a matching transaction could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Returns all the transactions where applicationId = &#63;.
	*
	* @param applicationId the application ID
	* @return the matching transactions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.Transaction> findByApplicationId(
		java.lang.String applicationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByApplicationId(applicationId);
	}

	/**
	* Returns a range of all the transactions where applicationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param applicationId the application ID
	* @param start the lower bound of the range of transactions
	* @param end the upper bound of the range of transactions (not inclusive)
	* @return the range of matching transactions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.Transaction> findByApplicationId(
		java.lang.String applicationId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByApplicationId(applicationId, start, end);
	}

	/**
	* Returns an ordered range of all the transactions where applicationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param applicationId the application ID
	* @param start the lower bound of the range of transactions
	* @param end the upper bound of the range of transactions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching transactions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.Transaction> findByApplicationId(
		java.lang.String applicationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByApplicationId(applicationId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first transaction in the ordered set where applicationId = &#63;.
	*
	* @param applicationId the application ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching transaction
	* @throws com.beorn.onlinepayment.NoSuchTransactionException if a matching transaction could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction findByApplicationId_First(
		java.lang.String applicationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchTransactionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByApplicationId_First(applicationId, orderByComparator);
	}

	/**
	* Returns the first transaction in the ordered set where applicationId = &#63;.
	*
	* @param applicationId the application ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching transaction, or <code>null</code> if a matching transaction could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction fetchByApplicationId_First(
		java.lang.String applicationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByApplicationId_First(applicationId, orderByComparator);
	}

	/**
	* Returns the last transaction in the ordered set where applicationId = &#63;.
	*
	* @param applicationId the application ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching transaction
	* @throws com.beorn.onlinepayment.NoSuchTransactionException if a matching transaction could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction findByApplicationId_Last(
		java.lang.String applicationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchTransactionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByApplicationId_Last(applicationId, orderByComparator);
	}

	/**
	* Returns the last transaction in the ordered set where applicationId = &#63;.
	*
	* @param applicationId the application ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching transaction, or <code>null</code> if a matching transaction could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction fetchByApplicationId_Last(
		java.lang.String applicationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByApplicationId_Last(applicationId, orderByComparator);
	}

	/**
	* Returns the transactions before and after the current transaction in the ordered set where applicationId = &#63;.
	*
	* @param transactionId the primary key of the current transaction
	* @param applicationId the application ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next transaction
	* @throws com.beorn.onlinepayment.NoSuchTransactionException if a transaction with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction[] findByApplicationId_PrevAndNext(
		long transactionId, java.lang.String applicationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchTransactionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByApplicationId_PrevAndNext(transactionId,
			applicationId, orderByComparator);
	}

	/**
	* Returns all the transactions where sellerId = &#63;.
	*
	* @param sellerId the seller ID
	* @return the matching transactions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.Transaction> findBySellerId(
		long sellerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBySellerId(sellerId);
	}

	/**
	* Returns a range of all the transactions where sellerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param sellerId the seller ID
	* @param start the lower bound of the range of transactions
	* @param end the upper bound of the range of transactions (not inclusive)
	* @return the range of matching transactions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.Transaction> findBySellerId(
		long sellerId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBySellerId(sellerId, start, end);
	}

	/**
	* Returns an ordered range of all the transactions where sellerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param sellerId the seller ID
	* @param start the lower bound of the range of transactions
	* @param end the upper bound of the range of transactions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching transactions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.Transaction> findBySellerId(
		long sellerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySellerId(sellerId, start, end, orderByComparator);
	}

	/**
	* Returns the first transaction in the ordered set where sellerId = &#63;.
	*
	* @param sellerId the seller ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching transaction
	* @throws com.beorn.onlinepayment.NoSuchTransactionException if a matching transaction could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction findBySellerId_First(
		long sellerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchTransactionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBySellerId_First(sellerId, orderByComparator);
	}

	/**
	* Returns the first transaction in the ordered set where sellerId = &#63;.
	*
	* @param sellerId the seller ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching transaction, or <code>null</code> if a matching transaction could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction fetchBySellerId_First(
		long sellerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySellerId_First(sellerId, orderByComparator);
	}

	/**
	* Returns the last transaction in the ordered set where sellerId = &#63;.
	*
	* @param sellerId the seller ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching transaction
	* @throws com.beorn.onlinepayment.NoSuchTransactionException if a matching transaction could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction findBySellerId_Last(
		long sellerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchTransactionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBySellerId_Last(sellerId, orderByComparator);
	}

	/**
	* Returns the last transaction in the ordered set where sellerId = &#63;.
	*
	* @param sellerId the seller ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching transaction, or <code>null</code> if a matching transaction could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction fetchBySellerId_Last(
		long sellerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchBySellerId_Last(sellerId, orderByComparator);
	}

	/**
	* Returns the transactions before and after the current transaction in the ordered set where sellerId = &#63;.
	*
	* @param transactionId the primary key of the current transaction
	* @param sellerId the seller ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next transaction
	* @throws com.beorn.onlinepayment.NoSuchTransactionException if a transaction with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction[] findBySellerId_PrevAndNext(
		long transactionId, long sellerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchTransactionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySellerId_PrevAndNext(transactionId, sellerId,
			orderByComparator);
	}

	/**
	* Returns all the transactions where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching transactions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.Transaction> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns a range of all the transactions where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of transactions
	* @param end the upper bound of the range of transactions (not inclusive)
	* @return the range of matching transactions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.Transaction> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the transactions where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of transactions
	* @param end the upper bound of the range of transactions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching transactions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.Transaction> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns the first transaction in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching transaction
	* @throws com.beorn.onlinepayment.NoSuchTransactionException if a matching transaction could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchTransactionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first transaction in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching transaction, or <code>null</code> if a matching transaction could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last transaction in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching transaction
	* @throws com.beorn.onlinepayment.NoSuchTransactionException if a matching transaction could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchTransactionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last transaction in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching transaction, or <code>null</code> if a matching transaction could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the transactions before and after the current transaction in the ordered set where userId = &#63;.
	*
	* @param transactionId the primary key of the current transaction
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next transaction
	* @throws com.beorn.onlinepayment.NoSuchTransactionException if a transaction with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction[] findByUserId_PrevAndNext(
		long transactionId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchTransactionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_PrevAndNext(transactionId, userId,
			orderByComparator);
	}

	/**
	* Returns all the transactions where applicationId = &#63; and sellerId = &#63;.
	*
	* @param applicationId the application ID
	* @param sellerId the seller ID
	* @return the matching transactions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.Transaction> findByApplicationIdAndSellerId(
		java.lang.String applicationId, long sellerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByApplicationIdAndSellerId(applicationId, sellerId);
	}

	/**
	* Returns a range of all the transactions where applicationId = &#63; and sellerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param applicationId the application ID
	* @param sellerId the seller ID
	* @param start the lower bound of the range of transactions
	* @param end the upper bound of the range of transactions (not inclusive)
	* @return the range of matching transactions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.Transaction> findByApplicationIdAndSellerId(
		java.lang.String applicationId, long sellerId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByApplicationIdAndSellerId(applicationId, sellerId,
			start, end);
	}

	/**
	* Returns an ordered range of all the transactions where applicationId = &#63; and sellerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param applicationId the application ID
	* @param sellerId the seller ID
	* @param start the lower bound of the range of transactions
	* @param end the upper bound of the range of transactions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching transactions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.Transaction> findByApplicationIdAndSellerId(
		java.lang.String applicationId, long sellerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByApplicationIdAndSellerId(applicationId, sellerId,
			start, end, orderByComparator);
	}

	/**
	* Returns the first transaction in the ordered set where applicationId = &#63; and sellerId = &#63;.
	*
	* @param applicationId the application ID
	* @param sellerId the seller ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching transaction
	* @throws com.beorn.onlinepayment.NoSuchTransactionException if a matching transaction could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction findByApplicationIdAndSellerId_First(
		java.lang.String applicationId, long sellerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchTransactionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByApplicationIdAndSellerId_First(applicationId,
			sellerId, orderByComparator);
	}

	/**
	* Returns the first transaction in the ordered set where applicationId = &#63; and sellerId = &#63;.
	*
	* @param applicationId the application ID
	* @param sellerId the seller ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching transaction, or <code>null</code> if a matching transaction could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction fetchByApplicationIdAndSellerId_First(
		java.lang.String applicationId, long sellerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByApplicationIdAndSellerId_First(applicationId,
			sellerId, orderByComparator);
	}

	/**
	* Returns the last transaction in the ordered set where applicationId = &#63; and sellerId = &#63;.
	*
	* @param applicationId the application ID
	* @param sellerId the seller ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching transaction
	* @throws com.beorn.onlinepayment.NoSuchTransactionException if a matching transaction could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction findByApplicationIdAndSellerId_Last(
		java.lang.String applicationId, long sellerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchTransactionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByApplicationIdAndSellerId_Last(applicationId,
			sellerId, orderByComparator);
	}

	/**
	* Returns the last transaction in the ordered set where applicationId = &#63; and sellerId = &#63;.
	*
	* @param applicationId the application ID
	* @param sellerId the seller ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching transaction, or <code>null</code> if a matching transaction could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction fetchByApplicationIdAndSellerId_Last(
		java.lang.String applicationId, long sellerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByApplicationIdAndSellerId_Last(applicationId,
			sellerId, orderByComparator);
	}

	/**
	* Returns the transactions before and after the current transaction in the ordered set where applicationId = &#63; and sellerId = &#63;.
	*
	* @param transactionId the primary key of the current transaction
	* @param applicationId the application ID
	* @param sellerId the seller ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next transaction
	* @throws com.beorn.onlinepayment.NoSuchTransactionException if a transaction with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction[] findByApplicationIdAndSellerId_PrevAndNext(
		long transactionId, java.lang.String applicationId, long sellerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchTransactionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByApplicationIdAndSellerId_PrevAndNext(transactionId,
			applicationId, sellerId, orderByComparator);
	}

	/**
	* Returns all the transactions where applicationId = &#63; and userId = &#63;.
	*
	* @param applicationId the application ID
	* @param userId the user ID
	* @return the matching transactions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.Transaction> findByApplicationIdAndUserId(
		java.lang.String applicationId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByApplicationIdAndUserId(applicationId, userId);
	}

	/**
	* Returns a range of all the transactions where applicationId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param applicationId the application ID
	* @param userId the user ID
	* @param start the lower bound of the range of transactions
	* @param end the upper bound of the range of transactions (not inclusive)
	* @return the range of matching transactions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.Transaction> findByApplicationIdAndUserId(
		java.lang.String applicationId, long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByApplicationIdAndUserId(applicationId, userId, start,
			end);
	}

	/**
	* Returns an ordered range of all the transactions where applicationId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param applicationId the application ID
	* @param userId the user ID
	* @param start the lower bound of the range of transactions
	* @param end the upper bound of the range of transactions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching transactions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.Transaction> findByApplicationIdAndUserId(
		java.lang.String applicationId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByApplicationIdAndUserId(applicationId, userId, start,
			end, orderByComparator);
	}

	/**
	* Returns the first transaction in the ordered set where applicationId = &#63; and userId = &#63;.
	*
	* @param applicationId the application ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching transaction
	* @throws com.beorn.onlinepayment.NoSuchTransactionException if a matching transaction could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction findByApplicationIdAndUserId_First(
		java.lang.String applicationId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchTransactionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByApplicationIdAndUserId_First(applicationId, userId,
			orderByComparator);
	}

	/**
	* Returns the first transaction in the ordered set where applicationId = &#63; and userId = &#63;.
	*
	* @param applicationId the application ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching transaction, or <code>null</code> if a matching transaction could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction fetchByApplicationIdAndUserId_First(
		java.lang.String applicationId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByApplicationIdAndUserId_First(applicationId, userId,
			orderByComparator);
	}

	/**
	* Returns the last transaction in the ordered set where applicationId = &#63; and userId = &#63;.
	*
	* @param applicationId the application ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching transaction
	* @throws com.beorn.onlinepayment.NoSuchTransactionException if a matching transaction could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction findByApplicationIdAndUserId_Last(
		java.lang.String applicationId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchTransactionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByApplicationIdAndUserId_Last(applicationId, userId,
			orderByComparator);
	}

	/**
	* Returns the last transaction in the ordered set where applicationId = &#63; and userId = &#63;.
	*
	* @param applicationId the application ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching transaction, or <code>null</code> if a matching transaction could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction fetchByApplicationIdAndUserId_Last(
		java.lang.String applicationId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByApplicationIdAndUserId_Last(applicationId, userId,
			orderByComparator);
	}

	/**
	* Returns the transactions before and after the current transaction in the ordered set where applicationId = &#63; and userId = &#63;.
	*
	* @param transactionId the primary key of the current transaction
	* @param applicationId the application ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next transaction
	* @throws com.beorn.onlinepayment.NoSuchTransactionException if a transaction with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction[] findByApplicationIdAndUserId_PrevAndNext(
		long transactionId, java.lang.String applicationId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchTransactionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByApplicationIdAndUserId_PrevAndNext(transactionId,
			applicationId, userId, orderByComparator);
	}

	/**
	* Returns all the transactions.
	*
	* @return the transactions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.Transaction> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the transactions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of transactions
	* @param end the upper bound of the range of transactions (not inclusive)
	* @return the range of transactions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.Transaction> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the transactions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of transactions
	* @param end the upper bound of the range of transactions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of transactions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.Transaction> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the transactions where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Removes the transaction where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the transaction that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.beorn.onlinepayment.NoSuchTransactionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Removes all the transactions where applicationId = &#63; from the database.
	*
	* @param applicationId the application ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByApplicationId(java.lang.String applicationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByApplicationId(applicationId);
	}

	/**
	* Removes all the transactions where sellerId = &#63; from the database.
	*
	* @param sellerId the seller ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeBySellerId(long sellerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeBySellerId(sellerId);
	}

	/**
	* Removes all the transactions where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Removes all the transactions where applicationId = &#63; and sellerId = &#63; from the database.
	*
	* @param applicationId the application ID
	* @param sellerId the seller ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByApplicationIdAndSellerId(
		java.lang.String applicationId, long sellerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByApplicationIdAndSellerId(applicationId, sellerId);
	}

	/**
	* Removes all the transactions where applicationId = &#63; and userId = &#63; from the database.
	*
	* @param applicationId the application ID
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByApplicationIdAndUserId(
		java.lang.String applicationId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByApplicationIdAndUserId(applicationId, userId);
	}

	/**
	* Removes all the transactions from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of transactions where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching transactions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the number of transactions where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching transactions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of transactions where applicationId = &#63;.
	*
	* @param applicationId the application ID
	* @return the number of matching transactions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByApplicationId(java.lang.String applicationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByApplicationId(applicationId);
	}

	/**
	* Returns the number of transactions where sellerId = &#63;.
	*
	* @param sellerId the seller ID
	* @return the number of matching transactions
	* @throws SystemException if a system exception occurred
	*/
	public static int countBySellerId(long sellerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countBySellerId(sellerId);
	}

	/**
	* Returns the number of transactions where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching transactions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns the number of transactions where applicationId = &#63; and sellerId = &#63;.
	*
	* @param applicationId the application ID
	* @param sellerId the seller ID
	* @return the number of matching transactions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByApplicationIdAndSellerId(
		java.lang.String applicationId, long sellerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByApplicationIdAndSellerId(applicationId, sellerId);
	}

	/**
	* Returns the number of transactions where applicationId = &#63; and userId = &#63;.
	*
	* @param applicationId the application ID
	* @param userId the user ID
	* @return the number of matching transactions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByApplicationIdAndUserId(
		java.lang.String applicationId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByApplicationIdAndUserId(applicationId, userId);
	}

	/**
	* Returns the number of transactions.
	*
	* @return the number of transactions
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	/**
	* Returns all the transaction logs associated with the transaction.
	*
	* @param pk the primary key of the transaction
	* @return the transaction logs associated with the transaction
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.TransactionLog> getTransactionLogs(
		long pk) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getTransactionLogs(pk);
	}

	/**
	* Returns a range of all the transaction logs associated with the transaction.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the transaction
	* @param start the lower bound of the range of transactions
	* @param end the upper bound of the range of transactions (not inclusive)
	* @return the range of transaction logs associated with the transaction
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.TransactionLog> getTransactionLogs(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getTransactionLogs(pk, start, end);
	}

	/**
	* Returns an ordered range of all the transaction logs associated with the transaction.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the transaction
	* @param start the lower bound of the range of transactions
	* @param end the upper bound of the range of transactions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of transaction logs associated with the transaction
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.TransactionLog> getTransactionLogs(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .getTransactionLogs(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of transaction logs associated with the transaction.
	*
	* @param pk the primary key of the transaction
	* @return the number of transaction logs associated with the transaction
	* @throws SystemException if a system exception occurred
	*/
	public static int getTransactionLogsSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getTransactionLogsSize(pk);
	}

	/**
	* Returns <code>true</code> if the transaction log is associated with the transaction.
	*
	* @param pk the primary key of the transaction
	* @param transactionLogPK the primary key of the transaction log
	* @return <code>true</code> if the transaction log is associated with the transaction; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsTransactionLog(long pk, long transactionLogPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsTransactionLog(pk, transactionLogPK);
	}

	/**
	* Returns <code>true</code> if the transaction has any transaction logs associated with it.
	*
	* @param pk the primary key of the transaction to check for associations with transaction logs
	* @return <code>true</code> if the transaction has any transaction logs associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsTransactionLogs(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsTransactionLogs(pk);
	}

	/**
	* Returns all the transaction parameters associated with the transaction.
	*
	* @param pk the primary key of the transaction
	* @return the transaction parameters associated with the transaction
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.TransactionParameter> getTransactionParameters(
		long pk) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getTransactionParameters(pk);
	}

	/**
	* Returns a range of all the transaction parameters associated with the transaction.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the transaction
	* @param start the lower bound of the range of transactions
	* @param end the upper bound of the range of transactions (not inclusive)
	* @return the range of transaction parameters associated with the transaction
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.TransactionParameter> getTransactionParameters(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getTransactionParameters(pk, start, end);
	}

	/**
	* Returns an ordered range of all the transaction parameters associated with the transaction.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the transaction
	* @param start the lower bound of the range of transactions
	* @param end the upper bound of the range of transactions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of transaction parameters associated with the transaction
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.TransactionParameter> getTransactionParameters(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .getTransactionParameters(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of transaction parameters associated with the transaction.
	*
	* @param pk the primary key of the transaction
	* @return the number of transaction parameters associated with the transaction
	* @throws SystemException if a system exception occurred
	*/
	public static int getTransactionParametersSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getTransactionParametersSize(pk);
	}

	/**
	* Returns <code>true</code> if the transaction parameter is associated with the transaction.
	*
	* @param pk the primary key of the transaction
	* @param transactionParameterPK the primary key of the transaction parameter
	* @return <code>true</code> if the transaction parameter is associated with the transaction; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsTransactionParameter(long pk,
		long transactionParameterPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .containsTransactionParameter(pk, transactionParameterPK);
	}

	/**
	* Returns <code>true</code> if the transaction has any transaction parameters associated with it.
	*
	* @param pk the primary key of the transaction to check for associations with transaction parameters
	* @return <code>true</code> if the transaction has any transaction parameters associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsTransactionParameters(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsTransactionParameters(pk);
	}

	public static TransactionPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (TransactionPersistence)PortletBeanLocatorUtil.locate(com.beorn.onlinepayment.service.ClpSerializer.getServletContextName(),
					TransactionPersistence.class.getName());

			ReferenceRegistry.registerReference(TransactionUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(TransactionPersistence persistence) {
	}

	private static TransactionPersistence _persistence;
}