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

import com.beorn.onlinepayment.model.PaymentMethod;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the payment method service. This utility wraps {@link PaymentMethodPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Sebastien Meunier
 * @see PaymentMethodPersistence
 * @see PaymentMethodPersistenceImpl
 * @generated
 */
public class PaymentMethodUtil {
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
	public static void clearCache(PaymentMethod paymentMethod) {
		getPersistence().clearCache(paymentMethod);
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
	public static List<PaymentMethod> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PaymentMethod> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PaymentMethod> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static PaymentMethod update(PaymentMethod paymentMethod,
		boolean merge) throws SystemException {
		return getPersistence().update(paymentMethod, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static PaymentMethod update(PaymentMethod paymentMethod,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(paymentMethod, merge, serviceContext);
	}

	/**
	* Caches the payment method in the entity cache if it is enabled.
	*
	* @param paymentMethod the payment method
	*/
	public static void cacheResult(
		com.beorn.onlinepayment.model.PaymentMethod paymentMethod) {
		getPersistence().cacheResult(paymentMethod);
	}

	/**
	* Caches the payment methods in the entity cache if it is enabled.
	*
	* @param paymentMethods the payment methods
	*/
	public static void cacheResult(
		java.util.List<com.beorn.onlinepayment.model.PaymentMethod> paymentMethods) {
		getPersistence().cacheResult(paymentMethods);
	}

	/**
	* Creates a new payment method with the primary key. Does not add the payment method to the database.
	*
	* @param paymentMethodId the primary key for the new payment method
	* @return the new payment method
	*/
	public static com.beorn.onlinepayment.model.PaymentMethod create(
		long paymentMethodId) {
		return getPersistence().create(paymentMethodId);
	}

	/**
	* Removes the payment method with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param paymentMethodId the primary key of the payment method
	* @return the payment method that was removed
	* @throws com.beorn.onlinepayment.NoSuchMethodException if a payment method with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentMethod remove(
		long paymentMethodId)
		throws com.beorn.onlinepayment.NoSuchMethodException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(paymentMethodId);
	}

	public static com.beorn.onlinepayment.model.PaymentMethod updateImpl(
		com.beorn.onlinepayment.model.PaymentMethod paymentMethod, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(paymentMethod, merge);
	}

	/**
	* Returns the payment method with the primary key or throws a {@link com.beorn.onlinepayment.NoSuchMethodException} if it could not be found.
	*
	* @param paymentMethodId the primary key of the payment method
	* @return the payment method
	* @throws com.beorn.onlinepayment.NoSuchMethodException if a payment method with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentMethod findByPrimaryKey(
		long paymentMethodId)
		throws com.beorn.onlinepayment.NoSuchMethodException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(paymentMethodId);
	}

	/**
	* Returns the payment method with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param paymentMethodId the primary key of the payment method
	* @return the payment method, or <code>null</code> if a payment method with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentMethod fetchByPrimaryKey(
		long paymentMethodId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(paymentMethodId);
	}

	/**
	* Returns all the payment methods where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching payment methods
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentMethod> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the payment methods where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of payment methods
	* @param end the upper bound of the range of payment methods (not inclusive)
	* @return the range of matching payment methods
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentMethod> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the payment methods where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of payment methods
	* @param end the upper bound of the range of payment methods (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching payment methods
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentMethod> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first payment method in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment method
	* @throws com.beorn.onlinepayment.NoSuchMethodException if a matching payment method could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentMethod findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchMethodException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first payment method in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment method, or <code>null</code> if a matching payment method could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentMethod fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last payment method in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment method
	* @throws com.beorn.onlinepayment.NoSuchMethodException if a matching payment method could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentMethod findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchMethodException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last payment method in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment method, or <code>null</code> if a matching payment method could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentMethod fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the payment methods before and after the current payment method in the ordered set where uuid = &#63;.
	*
	* @param paymentMethodId the primary key of the current payment method
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next payment method
	* @throws com.beorn.onlinepayment.NoSuchMethodException if a payment method with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentMethod[] findByUuid_PrevAndNext(
		long paymentMethodId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchMethodException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_PrevAndNext(paymentMethodId, uuid,
			orderByComparator);
	}

	/**
	* Returns the payment method where uuid = &#63; and groupId = &#63; or throws a {@link com.beorn.onlinepayment.NoSuchMethodException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching payment method
	* @throws com.beorn.onlinepayment.NoSuchMethodException if a matching payment method could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentMethod findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.beorn.onlinepayment.NoSuchMethodException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the payment method where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching payment method, or <code>null</code> if a matching payment method could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentMethod fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the payment method where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching payment method, or <code>null</code> if a matching payment method could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentMethod fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Returns all the payment methods where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching payment methods
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentMethod> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the payment methods where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of payment methods
	* @param end the upper bound of the range of payment methods (not inclusive)
	* @return the range of matching payment methods
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentMethod> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the payment methods where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of payment methods
	* @param end the upper bound of the range of payment methods (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching payment methods
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentMethod> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first payment method in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment method
	* @throws com.beorn.onlinepayment.NoSuchMethodException if a matching payment method could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentMethod findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchMethodException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first payment method in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment method, or <code>null</code> if a matching payment method could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentMethod fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last payment method in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment method
	* @throws com.beorn.onlinepayment.NoSuchMethodException if a matching payment method could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentMethod findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchMethodException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last payment method in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment method, or <code>null</code> if a matching payment method could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentMethod fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the payment methods before and after the current payment method in the ordered set where companyId = &#63;.
	*
	* @param paymentMethodId the primary key of the current payment method
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next payment method
	* @throws com.beorn.onlinepayment.NoSuchMethodException if a payment method with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentMethod[] findByCompanyId_PrevAndNext(
		long paymentMethodId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchMethodException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(paymentMethodId, companyId,
			orderByComparator);
	}

	/**
	* Returns the payment method where key = &#63; or throws a {@link com.beorn.onlinepayment.NoSuchMethodException} if it could not be found.
	*
	* @param key the key
	* @return the matching payment method
	* @throws com.beorn.onlinepayment.NoSuchMethodException if a matching payment method could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentMethod findByKey(
		java.lang.String key)
		throws com.beorn.onlinepayment.NoSuchMethodException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByKey(key);
	}

	/**
	* Returns the payment method where key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param key the key
	* @return the matching payment method, or <code>null</code> if a matching payment method could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentMethod fetchByKey(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByKey(key);
	}

	/**
	* Returns the payment method where key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param key the key
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching payment method, or <code>null</code> if a matching payment method could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentMethod fetchByKey(
		java.lang.String key, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByKey(key, retrieveFromCache);
	}

	/**
	* Returns all the payment methods.
	*
	* @return the payment methods
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentMethod> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the payment methods.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of payment methods
	* @param end the upper bound of the range of payment methods (not inclusive)
	* @return the range of payment methods
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentMethod> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the payment methods.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of payment methods
	* @param end the upper bound of the range of payment methods (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of payment methods
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentMethod> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the payment methods where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Removes the payment method where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the payment method that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentMethod removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.beorn.onlinepayment.NoSuchMethodException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Removes all the payment methods where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Removes the payment method where key = &#63; from the database.
	*
	* @param key the key
	* @return the payment method that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentMethod removeByKey(
		java.lang.String key)
		throws com.beorn.onlinepayment.NoSuchMethodException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByKey(key);
	}

	/**
	* Removes all the payment methods from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of payment methods where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching payment methods
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the number of payment methods where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching payment methods
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of payment methods where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching payment methods
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns the number of payment methods where key = &#63;.
	*
	* @param key the key
	* @return the number of matching payment methods
	* @throws SystemException if a system exception occurred
	*/
	public static int countByKey(java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByKey(key);
	}

	/**
	* Returns the number of payment methods.
	*
	* @return the number of payment methods
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	/**
	* Returns all the payment plugins associated with the payment method.
	*
	* @param pk the primary key of the payment method
	* @return the payment plugins associated with the payment method
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> getPaymentPlugins(
		long pk) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getPaymentPlugins(pk);
	}

	/**
	* Returns a range of all the payment plugins associated with the payment method.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the payment method
	* @param start the lower bound of the range of payment methods
	* @param end the upper bound of the range of payment methods (not inclusive)
	* @return the range of payment plugins associated with the payment method
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> getPaymentPlugins(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getPaymentPlugins(pk, start, end);
	}

	/**
	* Returns an ordered range of all the payment plugins associated with the payment method.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the payment method
	* @param start the lower bound of the range of payment methods
	* @param end the upper bound of the range of payment methods (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of payment plugins associated with the payment method
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> getPaymentPlugins(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .getPaymentPlugins(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of payment plugins associated with the payment method.
	*
	* @param pk the primary key of the payment method
	* @return the number of payment plugins associated with the payment method
	* @throws SystemException if a system exception occurred
	*/
	public static int getPaymentPluginsSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getPaymentPluginsSize(pk);
	}

	/**
	* Returns <code>true</code> if the payment plugin is associated with the payment method.
	*
	* @param pk the primary key of the payment method
	* @param paymentPluginPK the primary key of the payment plugin
	* @return <code>true</code> if the payment plugin is associated with the payment method; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsPaymentPlugin(long pk, long paymentPluginPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsPaymentPlugin(pk, paymentPluginPK);
	}

	/**
	* Returns <code>true</code> if the payment method has any payment plugins associated with it.
	*
	* @param pk the primary key of the payment method to check for associations with payment plugins
	* @return <code>true</code> if the payment method has any payment plugins associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsPaymentPlugins(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsPaymentPlugins(pk);
	}

	/**
	* Adds an association between the payment method and the payment plugin. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the payment method
	* @param paymentPluginPK the primary key of the payment plugin
	* @throws SystemException if a system exception occurred
	*/
	public static void addPaymentPlugin(long pk, long paymentPluginPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addPaymentPlugin(pk, paymentPluginPK);
	}

	/**
	* Adds an association between the payment method and the payment plugin. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the payment method
	* @param paymentPlugin the payment plugin
	* @throws SystemException if a system exception occurred
	*/
	public static void addPaymentPlugin(long pk,
		com.beorn.onlinepayment.model.PaymentPlugin paymentPlugin)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addPaymentPlugin(pk, paymentPlugin);
	}

	/**
	* Adds an association between the payment method and the payment plugins. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the payment method
	* @param paymentPluginPKs the primary keys of the payment plugins
	* @throws SystemException if a system exception occurred
	*/
	public static void addPaymentPlugins(long pk, long[] paymentPluginPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addPaymentPlugins(pk, paymentPluginPKs);
	}

	/**
	* Adds an association between the payment method and the payment plugins. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the payment method
	* @param paymentPlugins the payment plugins
	* @throws SystemException if a system exception occurred
	*/
	public static void addPaymentPlugins(long pk,
		java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> paymentPlugins)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addPaymentPlugins(pk, paymentPlugins);
	}

	/**
	* Clears all associations between the payment method and its payment plugins. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the payment method to clear the associated payment plugins from
	* @throws SystemException if a system exception occurred
	*/
	public static void clearPaymentPlugins(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().clearPaymentPlugins(pk);
	}

	/**
	* Removes the association between the payment method and the payment plugin. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the payment method
	* @param paymentPluginPK the primary key of the payment plugin
	* @throws SystemException if a system exception occurred
	*/
	public static void removePaymentPlugin(long pk, long paymentPluginPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removePaymentPlugin(pk, paymentPluginPK);
	}

	/**
	* Removes the association between the payment method and the payment plugin. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the payment method
	* @param paymentPlugin the payment plugin
	* @throws SystemException if a system exception occurred
	*/
	public static void removePaymentPlugin(long pk,
		com.beorn.onlinepayment.model.PaymentPlugin paymentPlugin)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removePaymentPlugin(pk, paymentPlugin);
	}

	/**
	* Removes the association between the payment method and the payment plugins. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the payment method
	* @param paymentPluginPKs the primary keys of the payment plugins
	* @throws SystemException if a system exception occurred
	*/
	public static void removePaymentPlugins(long pk, long[] paymentPluginPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removePaymentPlugins(pk, paymentPluginPKs);
	}

	/**
	* Removes the association between the payment method and the payment plugins. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the payment method
	* @param paymentPlugins the payment plugins
	* @throws SystemException if a system exception occurred
	*/
	public static void removePaymentPlugins(long pk,
		java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> paymentPlugins)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removePaymentPlugins(pk, paymentPlugins);
	}

	/**
	* Sets the payment plugins associated with the payment method, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the payment method
	* @param paymentPluginPKs the primary keys of the payment plugins to be associated with the payment method
	* @throws SystemException if a system exception occurred
	*/
	public static void setPaymentPlugins(long pk, long[] paymentPluginPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setPaymentPlugins(pk, paymentPluginPKs);
	}

	/**
	* Sets the payment plugins associated with the payment method, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the payment method
	* @param paymentPlugins the payment plugins to be associated with the payment method
	* @throws SystemException if a system exception occurred
	*/
	public static void setPaymentPlugins(long pk,
		java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> paymentPlugins)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setPaymentPlugins(pk, paymentPlugins);
	}

	public static PaymentMethodPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (PaymentMethodPersistence)PortletBeanLocatorUtil.locate(com.beorn.onlinepayment.service.ClpSerializer.getServletContextName(),
					PaymentMethodPersistence.class.getName());

			ReferenceRegistry.registerReference(PaymentMethodUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(PaymentMethodPersistence persistence) {
	}

	private static PaymentMethodPersistence _persistence;
}