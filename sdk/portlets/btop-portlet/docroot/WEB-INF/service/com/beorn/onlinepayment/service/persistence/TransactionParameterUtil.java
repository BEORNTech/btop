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

import com.beorn.onlinepayment.model.TransactionParameter;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the transaction parameter service. This utility wraps {@link TransactionParameterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Sebastien Meunier
 * @see TransactionParameterPersistence
 * @see TransactionParameterPersistenceImpl
 * @generated
 */
public class TransactionParameterUtil {
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
	public static void clearCache(TransactionParameter transactionParameter) {
		getPersistence().clearCache(transactionParameter);
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
	public static List<TransactionParameter> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TransactionParameter> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TransactionParameter> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static TransactionParameter update(
		TransactionParameter transactionParameter, boolean merge)
		throws SystemException {
		return getPersistence().update(transactionParameter, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static TransactionParameter update(
		TransactionParameter transactionParameter, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence()
				   .update(transactionParameter, merge, serviceContext);
	}

	/**
	* Caches the transaction parameter in the entity cache if it is enabled.
	*
	* @param transactionParameter the transaction parameter
	*/
	public static void cacheResult(
		com.beorn.onlinepayment.model.TransactionParameter transactionParameter) {
		getPersistence().cacheResult(transactionParameter);
	}

	/**
	* Caches the transaction parameters in the entity cache if it is enabled.
	*
	* @param transactionParameters the transaction parameters
	*/
	public static void cacheResult(
		java.util.List<com.beorn.onlinepayment.model.TransactionParameter> transactionParameters) {
		getPersistence().cacheResult(transactionParameters);
	}

	/**
	* Creates a new transaction parameter with the primary key. Does not add the transaction parameter to the database.
	*
	* @param transactionParameterId the primary key for the new transaction parameter
	* @return the new transaction parameter
	*/
	public static com.beorn.onlinepayment.model.TransactionParameter create(
		long transactionParameterId) {
		return getPersistence().create(transactionParameterId);
	}

	/**
	* Removes the transaction parameter with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param transactionParameterId the primary key of the transaction parameter
	* @return the transaction parameter that was removed
	* @throws com.beorn.onlinepayment.NoSuchTransactionParameterException if a transaction parameter with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.TransactionParameter remove(
		long transactionParameterId)
		throws com.beorn.onlinepayment.NoSuchTransactionParameterException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(transactionParameterId);
	}

	public static com.beorn.onlinepayment.model.TransactionParameter updateImpl(
		com.beorn.onlinepayment.model.TransactionParameter transactionParameter,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(transactionParameter, merge);
	}

	/**
	* Returns the transaction parameter with the primary key or throws a {@link com.beorn.onlinepayment.NoSuchTransactionParameterException} if it could not be found.
	*
	* @param transactionParameterId the primary key of the transaction parameter
	* @return the transaction parameter
	* @throws com.beorn.onlinepayment.NoSuchTransactionParameterException if a transaction parameter with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.TransactionParameter findByPrimaryKey(
		long transactionParameterId)
		throws com.beorn.onlinepayment.NoSuchTransactionParameterException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(transactionParameterId);
	}

	/**
	* Returns the transaction parameter with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param transactionParameterId the primary key of the transaction parameter
	* @return the transaction parameter, or <code>null</code> if a transaction parameter with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.TransactionParameter fetchByPrimaryKey(
		long transactionParameterId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(transactionParameterId);
	}

	/**
	* Returns all the transaction parameters where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching transaction parameters
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.TransactionParameter> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the transaction parameters where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of transaction parameters
	* @param end the upper bound of the range of transaction parameters (not inclusive)
	* @return the range of matching transaction parameters
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.TransactionParameter> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the transaction parameters where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of transaction parameters
	* @param end the upper bound of the range of transaction parameters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching transaction parameters
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.TransactionParameter> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first transaction parameter in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching transaction parameter
	* @throws com.beorn.onlinepayment.NoSuchTransactionParameterException if a matching transaction parameter could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.TransactionParameter findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchTransactionParameterException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first transaction parameter in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching transaction parameter, or <code>null</code> if a matching transaction parameter could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.TransactionParameter fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last transaction parameter in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching transaction parameter
	* @throws com.beorn.onlinepayment.NoSuchTransactionParameterException if a matching transaction parameter could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.TransactionParameter findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchTransactionParameterException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last transaction parameter in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching transaction parameter, or <code>null</code> if a matching transaction parameter could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.TransactionParameter fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the transaction parameters before and after the current transaction parameter in the ordered set where uuid = &#63;.
	*
	* @param transactionParameterId the primary key of the current transaction parameter
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next transaction parameter
	* @throws com.beorn.onlinepayment.NoSuchTransactionParameterException if a transaction parameter with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.TransactionParameter[] findByUuid_PrevAndNext(
		long transactionParameterId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchTransactionParameterException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_PrevAndNext(transactionParameterId, uuid,
			orderByComparator);
	}

	/**
	* Returns the transaction parameter where uuid = &#63; and groupId = &#63; or throws a {@link com.beorn.onlinepayment.NoSuchTransactionParameterException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching transaction parameter
	* @throws com.beorn.onlinepayment.NoSuchTransactionParameterException if a matching transaction parameter could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.TransactionParameter findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.beorn.onlinepayment.NoSuchTransactionParameterException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the transaction parameter where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching transaction parameter, or <code>null</code> if a matching transaction parameter could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.TransactionParameter fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the transaction parameter where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching transaction parameter, or <code>null</code> if a matching transaction parameter could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.TransactionParameter fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Returns the transaction parameter where transactionId = &#63; and key = &#63; or throws a {@link com.beorn.onlinepayment.NoSuchTransactionParameterException} if it could not be found.
	*
	* @param transactionId the transaction ID
	* @param key the key
	* @return the matching transaction parameter
	* @throws com.beorn.onlinepayment.NoSuchTransactionParameterException if a matching transaction parameter could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.TransactionParameter findByTransactionIdAndKey(
		long transactionId, java.lang.String key)
		throws com.beorn.onlinepayment.NoSuchTransactionParameterException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTransactionIdAndKey(transactionId, key);
	}

	/**
	* Returns the transaction parameter where transactionId = &#63; and key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param transactionId the transaction ID
	* @param key the key
	* @return the matching transaction parameter, or <code>null</code> if a matching transaction parameter could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.TransactionParameter fetchByTransactionIdAndKey(
		long transactionId, java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByTransactionIdAndKey(transactionId, key);
	}

	/**
	* Returns the transaction parameter where transactionId = &#63; and key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param transactionId the transaction ID
	* @param key the key
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching transaction parameter, or <code>null</code> if a matching transaction parameter could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.TransactionParameter fetchByTransactionIdAndKey(
		long transactionId, java.lang.String key, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByTransactionIdAndKey(transactionId, key,
			retrieveFromCache);
	}

	/**
	* Returns all the transaction parameters.
	*
	* @return the transaction parameters
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.TransactionParameter> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the transaction parameters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of transaction parameters
	* @param end the upper bound of the range of transaction parameters (not inclusive)
	* @return the range of transaction parameters
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.TransactionParameter> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the transaction parameters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of transaction parameters
	* @param end the upper bound of the range of transaction parameters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of transaction parameters
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.TransactionParameter> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the transaction parameters where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Removes the transaction parameter where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the transaction parameter that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.TransactionParameter removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.beorn.onlinepayment.NoSuchTransactionParameterException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Removes the transaction parameter where transactionId = &#63; and key = &#63; from the database.
	*
	* @param transactionId the transaction ID
	* @param key the key
	* @return the transaction parameter that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.TransactionParameter removeByTransactionIdAndKey(
		long transactionId, java.lang.String key)
		throws com.beorn.onlinepayment.NoSuchTransactionParameterException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByTransactionIdAndKey(transactionId, key);
	}

	/**
	* Removes all the transaction parameters from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of transaction parameters where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching transaction parameters
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the number of transaction parameters where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching transaction parameters
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of transaction parameters where transactionId = &#63; and key = &#63;.
	*
	* @param transactionId the transaction ID
	* @param key the key
	* @return the number of matching transaction parameters
	* @throws SystemException if a system exception occurred
	*/
	public static int countByTransactionIdAndKey(long transactionId,
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByTransactionIdAndKey(transactionId, key);
	}

	/**
	* Returns the number of transaction parameters.
	*
	* @return the number of transaction parameters
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static TransactionParameterPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (TransactionParameterPersistence)PortletBeanLocatorUtil.locate(com.beorn.onlinepayment.service.ClpSerializer.getServletContextName(),
					TransactionParameterPersistence.class.getName());

			ReferenceRegistry.registerReference(TransactionParameterUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(TransactionParameterPersistence persistence) {
	}

	private static TransactionParameterPersistence _persistence;
}