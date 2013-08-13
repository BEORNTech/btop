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

import com.beorn.onlinepayment.model.OrderTransaction;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the order transaction service. This utility wraps {@link OrderTransactionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Sebastien Meunier
 * @see OrderTransactionPersistence
 * @see OrderTransactionPersistenceImpl
 * @generated
 */
public class OrderTransactionUtil {
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
	public static void clearCache(OrderTransaction orderTransaction) {
		getPersistence().clearCache(orderTransaction);
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
	public static List<OrderTransaction> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<OrderTransaction> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<OrderTransaction> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static OrderTransaction update(OrderTransaction orderTransaction,
		boolean merge) throws SystemException {
		return getPersistence().update(orderTransaction, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static OrderTransaction update(OrderTransaction orderTransaction,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(orderTransaction, merge, serviceContext);
	}

	/**
	* Caches the order transaction in the entity cache if it is enabled.
	*
	* @param orderTransaction the order transaction
	*/
	public static void cacheResult(
		com.beorn.onlinepayment.model.OrderTransaction orderTransaction) {
		getPersistence().cacheResult(orderTransaction);
	}

	/**
	* Caches the order transactions in the entity cache if it is enabled.
	*
	* @param orderTransactions the order transactions
	*/
	public static void cacheResult(
		java.util.List<com.beorn.onlinepayment.model.OrderTransaction> orderTransactions) {
		getPersistence().cacheResult(orderTransactions);
	}

	/**
	* Creates a new order transaction with the primary key. Does not add the order transaction to the database.
	*
	* @param orderId the primary key for the new order transaction
	* @return the new order transaction
	*/
	public static com.beorn.onlinepayment.model.OrderTransaction create(
		long orderId) {
		return getPersistence().create(orderId);
	}

	/**
	* Removes the order transaction with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param orderId the primary key of the order transaction
	* @return the order transaction that was removed
	* @throws com.beorn.onlinepayment.NoSuchOrderTransactionException if a order transaction with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.OrderTransaction remove(
		long orderId)
		throws com.beorn.onlinepayment.NoSuchOrderTransactionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(orderId);
	}

	public static com.beorn.onlinepayment.model.OrderTransaction updateImpl(
		com.beorn.onlinepayment.model.OrderTransaction orderTransaction,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(orderTransaction, merge);
	}

	/**
	* Returns the order transaction with the primary key or throws a {@link com.beorn.onlinepayment.NoSuchOrderTransactionException} if it could not be found.
	*
	* @param orderId the primary key of the order transaction
	* @return the order transaction
	* @throws com.beorn.onlinepayment.NoSuchOrderTransactionException if a order transaction with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.OrderTransaction findByPrimaryKey(
		long orderId)
		throws com.beorn.onlinepayment.NoSuchOrderTransactionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(orderId);
	}

	/**
	* Returns the order transaction with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param orderId the primary key of the order transaction
	* @return the order transaction, or <code>null</code> if a order transaction with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.OrderTransaction fetchByPrimaryKey(
		long orderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(orderId);
	}

	/**
	* Returns all the order transactions where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching order transactions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.OrderTransaction> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the order transactions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of order transactions
	* @param end the upper bound of the range of order transactions (not inclusive)
	* @return the range of matching order transactions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.OrderTransaction> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the order transactions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of order transactions
	* @param end the upper bound of the range of order transactions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching order transactions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.OrderTransaction> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first order transaction in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching order transaction
	* @throws com.beorn.onlinepayment.NoSuchOrderTransactionException if a matching order transaction could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.OrderTransaction findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchOrderTransactionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first order transaction in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching order transaction, or <code>null</code> if a matching order transaction could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.OrderTransaction fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last order transaction in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching order transaction
	* @throws com.beorn.onlinepayment.NoSuchOrderTransactionException if a matching order transaction could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.OrderTransaction findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchOrderTransactionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last order transaction in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching order transaction, or <code>null</code> if a matching order transaction could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.OrderTransaction fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the order transactions before and after the current order transaction in the ordered set where uuid = &#63;.
	*
	* @param orderId the primary key of the current order transaction
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next order transaction
	* @throws com.beorn.onlinepayment.NoSuchOrderTransactionException if a order transaction with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.OrderTransaction[] findByUuid_PrevAndNext(
		long orderId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchOrderTransactionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_PrevAndNext(orderId, uuid, orderByComparator);
	}

	/**
	* Returns the order transaction where transactionId = &#63; or throws a {@link com.beorn.onlinepayment.NoSuchOrderTransactionException} if it could not be found.
	*
	* @param transactionId the transaction ID
	* @return the matching order transaction
	* @throws com.beorn.onlinepayment.NoSuchOrderTransactionException if a matching order transaction could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.OrderTransaction findByTransactionId(
		long transactionId)
		throws com.beorn.onlinepayment.NoSuchOrderTransactionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTransactionId(transactionId);
	}

	/**
	* Returns the order transaction where transactionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param transactionId the transaction ID
	* @return the matching order transaction, or <code>null</code> if a matching order transaction could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.OrderTransaction fetchByTransactionId(
		long transactionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByTransactionId(transactionId);
	}

	/**
	* Returns the order transaction where transactionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param transactionId the transaction ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching order transaction, or <code>null</code> if a matching order transaction could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.OrderTransaction fetchByTransactionId(
		long transactionId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByTransactionId(transactionId, retrieveFromCache);
	}

	/**
	* Returns all the order transactions.
	*
	* @return the order transactions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.OrderTransaction> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the order transactions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of order transactions
	* @param end the upper bound of the range of order transactions (not inclusive)
	* @return the range of order transactions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.OrderTransaction> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the order transactions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of order transactions
	* @param end the upper bound of the range of order transactions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of order transactions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.OrderTransaction> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the order transactions where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Removes the order transaction where transactionId = &#63; from the database.
	*
	* @param transactionId the transaction ID
	* @return the order transaction that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.OrderTransaction removeByTransactionId(
		long transactionId)
		throws com.beorn.onlinepayment.NoSuchOrderTransactionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByTransactionId(transactionId);
	}

	/**
	* Removes all the order transactions from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of order transactions where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching order transactions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the number of order transactions where transactionId = &#63;.
	*
	* @param transactionId the transaction ID
	* @return the number of matching order transactions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByTransactionId(long transactionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByTransactionId(transactionId);
	}

	/**
	* Returns the number of order transactions.
	*
	* @return the number of order transactions
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static OrderTransactionPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (OrderTransactionPersistence)PortletBeanLocatorUtil.locate(com.beorn.onlinepayment.service.ClpSerializer.getServletContextName(),
					OrderTransactionPersistence.class.getName());

			ReferenceRegistry.registerReference(OrderTransactionUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(OrderTransactionPersistence persistence) {
	}

	private static OrderTransactionPersistence _persistence;
}