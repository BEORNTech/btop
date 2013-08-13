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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the payment method service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Sebastien Meunier
 * @see PaymentMethodPersistenceImpl
 * @see PaymentMethodUtil
 * @generated
 */
public interface PaymentMethodPersistence extends BasePersistence<PaymentMethod> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PaymentMethodUtil} to access the payment method persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the payment method in the entity cache if it is enabled.
	*
	* @param paymentMethod the payment method
	*/
	public void cacheResult(
		com.beorn.onlinepayment.model.PaymentMethod paymentMethod);

	/**
	* Caches the payment methods in the entity cache if it is enabled.
	*
	* @param paymentMethods the payment methods
	*/
	public void cacheResult(
		java.util.List<com.beorn.onlinepayment.model.PaymentMethod> paymentMethods);

	/**
	* Creates a new payment method with the primary key. Does not add the payment method to the database.
	*
	* @param paymentMethodId the primary key for the new payment method
	* @return the new payment method
	*/
	public com.beorn.onlinepayment.model.PaymentMethod create(
		long paymentMethodId);

	/**
	* Removes the payment method with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param paymentMethodId the primary key of the payment method
	* @return the payment method that was removed
	* @throws com.beorn.onlinepayment.NoSuchMethodException if a payment method with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentMethod remove(
		long paymentMethodId)
		throws com.beorn.onlinepayment.NoSuchMethodException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.beorn.onlinepayment.model.PaymentMethod updateImpl(
		com.beorn.onlinepayment.model.PaymentMethod paymentMethod, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the payment method with the primary key or throws a {@link com.beorn.onlinepayment.NoSuchMethodException} if it could not be found.
	*
	* @param paymentMethodId the primary key of the payment method
	* @return the payment method
	* @throws com.beorn.onlinepayment.NoSuchMethodException if a payment method with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentMethod findByPrimaryKey(
		long paymentMethodId)
		throws com.beorn.onlinepayment.NoSuchMethodException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the payment method with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param paymentMethodId the primary key of the payment method
	* @return the payment method, or <code>null</code> if a payment method with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentMethod fetchByPrimaryKey(
		long paymentMethodId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the payment methods where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching payment methods
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.beorn.onlinepayment.model.PaymentMethod> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.beorn.onlinepayment.model.PaymentMethod> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.beorn.onlinepayment.model.PaymentMethod> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first payment method in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment method
	* @throws com.beorn.onlinepayment.NoSuchMethodException if a matching payment method could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentMethod findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchMethodException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first payment method in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment method, or <code>null</code> if a matching payment method could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentMethod fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last payment method in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment method
	* @throws com.beorn.onlinepayment.NoSuchMethodException if a matching payment method could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentMethod findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchMethodException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last payment method in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment method, or <code>null</code> if a matching payment method could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentMethod fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.beorn.onlinepayment.model.PaymentMethod[] findByUuid_PrevAndNext(
		long paymentMethodId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchMethodException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the payment method where uuid = &#63; and groupId = &#63; or throws a {@link com.beorn.onlinepayment.NoSuchMethodException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching payment method
	* @throws com.beorn.onlinepayment.NoSuchMethodException if a matching payment method could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentMethod findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.beorn.onlinepayment.NoSuchMethodException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the payment method where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching payment method, or <code>null</code> if a matching payment method could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentMethod fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the payment method where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching payment method, or <code>null</code> if a matching payment method could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentMethod fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the payment methods where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching payment methods
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.beorn.onlinepayment.model.PaymentMethod> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.beorn.onlinepayment.model.PaymentMethod> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.beorn.onlinepayment.model.PaymentMethod> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first payment method in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment method
	* @throws com.beorn.onlinepayment.NoSuchMethodException if a matching payment method could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentMethod findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchMethodException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first payment method in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment method, or <code>null</code> if a matching payment method could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentMethod fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last payment method in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment method
	* @throws com.beorn.onlinepayment.NoSuchMethodException if a matching payment method could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentMethod findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchMethodException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last payment method in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment method, or <code>null</code> if a matching payment method could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentMethod fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.beorn.onlinepayment.model.PaymentMethod[] findByCompanyId_PrevAndNext(
		long paymentMethodId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchMethodException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the payment method where key = &#63; or throws a {@link com.beorn.onlinepayment.NoSuchMethodException} if it could not be found.
	*
	* @param key the key
	* @return the matching payment method
	* @throws com.beorn.onlinepayment.NoSuchMethodException if a matching payment method could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentMethod findByKey(
		java.lang.String key)
		throws com.beorn.onlinepayment.NoSuchMethodException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the payment method where key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param key the key
	* @return the matching payment method, or <code>null</code> if a matching payment method could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentMethod fetchByKey(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the payment method where key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param key the key
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching payment method, or <code>null</code> if a matching payment method could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentMethod fetchByKey(
		java.lang.String key, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the payment methods.
	*
	* @return the payment methods
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.beorn.onlinepayment.model.PaymentMethod> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.beorn.onlinepayment.model.PaymentMethod> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.beorn.onlinepayment.model.PaymentMethod> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the payment methods where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the payment method where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the payment method that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentMethod removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.beorn.onlinepayment.NoSuchMethodException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the payment methods where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the payment method where key = &#63; from the database.
	*
	* @param key the key
	* @return the payment method that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentMethod removeByKey(
		java.lang.String key)
		throws com.beorn.onlinepayment.NoSuchMethodException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the payment methods from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of payment methods where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching payment methods
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of payment methods where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching payment methods
	* @throws SystemException if a system exception occurred
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of payment methods where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching payment methods
	* @throws SystemException if a system exception occurred
	*/
	public int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of payment methods where key = &#63;.
	*
	* @param key the key
	* @return the number of matching payment methods
	* @throws SystemException if a system exception occurred
	*/
	public int countByKey(java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of payment methods.
	*
	* @return the number of payment methods
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the payment plugins associated with the payment method.
	*
	* @param pk the primary key of the payment method
	* @return the payment plugins associated with the payment method
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> getPaymentPlugins(
		long pk) throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> getPaymentPlugins(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> getPaymentPlugins(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of payment plugins associated with the payment method.
	*
	* @param pk the primary key of the payment method
	* @return the number of payment plugins associated with the payment method
	* @throws SystemException if a system exception occurred
	*/
	public int getPaymentPluginsSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns <code>true</code> if the payment plugin is associated with the payment method.
	*
	* @param pk the primary key of the payment method
	* @param paymentPluginPK the primary key of the payment plugin
	* @return <code>true</code> if the payment plugin is associated with the payment method; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public boolean containsPaymentPlugin(long pk, long paymentPluginPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns <code>true</code> if the payment method has any payment plugins associated with it.
	*
	* @param pk the primary key of the payment method to check for associations with payment plugins
	* @return <code>true</code> if the payment method has any payment plugins associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public boolean containsPaymentPlugins(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the payment method and the payment plugin. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the payment method
	* @param paymentPluginPK the primary key of the payment plugin
	* @throws SystemException if a system exception occurred
	*/
	public void addPaymentPlugin(long pk, long paymentPluginPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the payment method and the payment plugin. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the payment method
	* @param paymentPlugin the payment plugin
	* @throws SystemException if a system exception occurred
	*/
	public void addPaymentPlugin(long pk,
		com.beorn.onlinepayment.model.PaymentPlugin paymentPlugin)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the payment method and the payment plugins. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the payment method
	* @param paymentPluginPKs the primary keys of the payment plugins
	* @throws SystemException if a system exception occurred
	*/
	public void addPaymentPlugins(long pk, long[] paymentPluginPKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the payment method and the payment plugins. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the payment method
	* @param paymentPlugins the payment plugins
	* @throws SystemException if a system exception occurred
	*/
	public void addPaymentPlugins(long pk,
		java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> paymentPlugins)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Clears all associations between the payment method and its payment plugins. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the payment method to clear the associated payment plugins from
	* @throws SystemException if a system exception occurred
	*/
	public void clearPaymentPlugins(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the payment method and the payment plugin. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the payment method
	* @param paymentPluginPK the primary key of the payment plugin
	* @throws SystemException if a system exception occurred
	*/
	public void removePaymentPlugin(long pk, long paymentPluginPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the payment method and the payment plugin. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the payment method
	* @param paymentPlugin the payment plugin
	* @throws SystemException if a system exception occurred
	*/
	public void removePaymentPlugin(long pk,
		com.beorn.onlinepayment.model.PaymentPlugin paymentPlugin)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the payment method and the payment plugins. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the payment method
	* @param paymentPluginPKs the primary keys of the payment plugins
	* @throws SystemException if a system exception occurred
	*/
	public void removePaymentPlugins(long pk, long[] paymentPluginPKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the payment method and the payment plugins. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the payment method
	* @param paymentPlugins the payment plugins
	* @throws SystemException if a system exception occurred
	*/
	public void removePaymentPlugins(long pk,
		java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> paymentPlugins)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Sets the payment plugins associated with the payment method, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the payment method
	* @param paymentPluginPKs the primary keys of the payment plugins to be associated with the payment method
	* @throws SystemException if a system exception occurred
	*/
	public void setPaymentPlugins(long pk, long[] paymentPluginPKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Sets the payment plugins associated with the payment method, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the payment method
	* @param paymentPlugins the payment plugins to be associated with the payment method
	* @throws SystemException if a system exception occurred
	*/
	public void setPaymentPlugins(long pk,
		java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> paymentPlugins)
		throws com.liferay.portal.kernel.exception.SystemException;
}