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

import com.beorn.onlinepayment.model.PaymentPlugin;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the payment plugin service. This utility wraps {@link PaymentPluginPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Sebastien Meunier
 * @see PaymentPluginPersistence
 * @see PaymentPluginPersistenceImpl
 * @generated
 */
public class PaymentPluginUtil {
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
	public static void clearCache(PaymentPlugin paymentPlugin) {
		getPersistence().clearCache(paymentPlugin);
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
	public static List<PaymentPlugin> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PaymentPlugin> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PaymentPlugin> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static PaymentPlugin update(PaymentPlugin paymentPlugin,
		boolean merge) throws SystemException {
		return getPersistence().update(paymentPlugin, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static PaymentPlugin update(PaymentPlugin paymentPlugin,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(paymentPlugin, merge, serviceContext);
	}

	/**
	* Caches the payment plugin in the entity cache if it is enabled.
	*
	* @param paymentPlugin the payment plugin
	*/
	public static void cacheResult(
		com.beorn.onlinepayment.model.PaymentPlugin paymentPlugin) {
		getPersistence().cacheResult(paymentPlugin);
	}

	/**
	* Caches the payment plugins in the entity cache if it is enabled.
	*
	* @param paymentPlugins the payment plugins
	*/
	public static void cacheResult(
		java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> paymentPlugins) {
		getPersistence().cacheResult(paymentPlugins);
	}

	/**
	* Creates a new payment plugin with the primary key. Does not add the payment plugin to the database.
	*
	* @param paymentPluginId the primary key for the new payment plugin
	* @return the new payment plugin
	*/
	public static com.beorn.onlinepayment.model.PaymentPlugin create(
		long paymentPluginId) {
		return getPersistence().create(paymentPluginId);
	}

	/**
	* Removes the payment plugin with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param paymentPluginId the primary key of the payment plugin
	* @return the payment plugin that was removed
	* @throws com.beorn.onlinepayment.NoSuchPluginException if a payment plugin with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPlugin remove(
		long paymentPluginId)
		throws com.beorn.onlinepayment.NoSuchPluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(paymentPluginId);
	}

	public static com.beorn.onlinepayment.model.PaymentPlugin updateImpl(
		com.beorn.onlinepayment.model.PaymentPlugin paymentPlugin, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(paymentPlugin, merge);
	}

	/**
	* Returns the payment plugin with the primary key or throws a {@link com.beorn.onlinepayment.NoSuchPluginException} if it could not be found.
	*
	* @param paymentPluginId the primary key of the payment plugin
	* @return the payment plugin
	* @throws com.beorn.onlinepayment.NoSuchPluginException if a payment plugin with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPlugin findByPrimaryKey(
		long paymentPluginId)
		throws com.beorn.onlinepayment.NoSuchPluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(paymentPluginId);
	}

	/**
	* Returns the payment plugin with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param paymentPluginId the primary key of the payment plugin
	* @return the payment plugin, or <code>null</code> if a payment plugin with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPlugin fetchByPrimaryKey(
		long paymentPluginId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(paymentPluginId);
	}

	/**
	* Returns all the payment plugins where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching payment plugins
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the payment plugins where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of payment plugins
	* @param end the upper bound of the range of payment plugins (not inclusive)
	* @return the range of matching payment plugins
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the payment plugins where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of payment plugins
	* @param end the upper bound of the range of payment plugins (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching payment plugins
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first payment plugin in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment plugin
	* @throws com.beorn.onlinepayment.NoSuchPluginException if a matching payment plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPlugin findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchPluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first payment plugin in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment plugin, or <code>null</code> if a matching payment plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPlugin fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last payment plugin in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment plugin
	* @throws com.beorn.onlinepayment.NoSuchPluginException if a matching payment plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPlugin findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchPluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last payment plugin in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment plugin, or <code>null</code> if a matching payment plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPlugin fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the payment plugins before and after the current payment plugin in the ordered set where uuid = &#63;.
	*
	* @param paymentPluginId the primary key of the current payment plugin
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next payment plugin
	* @throws com.beorn.onlinepayment.NoSuchPluginException if a payment plugin with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPlugin[] findByUuid_PrevAndNext(
		long paymentPluginId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchPluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_PrevAndNext(paymentPluginId, uuid,
			orderByComparator);
	}

	/**
	* Returns the payment plugin where uuid = &#63; and groupId = &#63; or throws a {@link com.beorn.onlinepayment.NoSuchPluginException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching payment plugin
	* @throws com.beorn.onlinepayment.NoSuchPluginException if a matching payment plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPlugin findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.beorn.onlinepayment.NoSuchPluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the payment plugin where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching payment plugin, or <code>null</code> if a matching payment plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPlugin fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the payment plugin where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching payment plugin, or <code>null</code> if a matching payment plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPlugin fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Returns all the payment plugins where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching payment plugins
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the payment plugins where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of payment plugins
	* @param end the upper bound of the range of payment plugins (not inclusive)
	* @return the range of matching payment plugins
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the payment plugins where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of payment plugins
	* @param end the upper bound of the range of payment plugins (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching payment plugins
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first payment plugin in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment plugin
	* @throws com.beorn.onlinepayment.NoSuchPluginException if a matching payment plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPlugin findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchPluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first payment plugin in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment plugin, or <code>null</code> if a matching payment plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPlugin fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last payment plugin in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment plugin
	* @throws com.beorn.onlinepayment.NoSuchPluginException if a matching payment plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPlugin findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchPluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last payment plugin in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment plugin, or <code>null</code> if a matching payment plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPlugin fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the payment plugins before and after the current payment plugin in the ordered set where companyId = &#63;.
	*
	* @param paymentPluginId the primary key of the current payment plugin
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next payment plugin
	* @throws com.beorn.onlinepayment.NoSuchPluginException if a payment plugin with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPlugin[] findByCompanyId_PrevAndNext(
		long paymentPluginId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchPluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(paymentPluginId, companyId,
			orderByComparator);
	}

	/**
	* Returns the payment plugin where applicationId = &#63; or throws a {@link com.beorn.onlinepayment.NoSuchPluginException} if it could not be found.
	*
	* @param applicationId the application ID
	* @return the matching payment plugin
	* @throws com.beorn.onlinepayment.NoSuchPluginException if a matching payment plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPlugin findByApplicationId(
		java.lang.String applicationId)
		throws com.beorn.onlinepayment.NoSuchPluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByApplicationId(applicationId);
	}

	/**
	* Returns the payment plugin where applicationId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param applicationId the application ID
	* @return the matching payment plugin, or <code>null</code> if a matching payment plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPlugin fetchByApplicationId(
		java.lang.String applicationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByApplicationId(applicationId);
	}

	/**
	* Returns the payment plugin where applicationId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param applicationId the application ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching payment plugin, or <code>null</code> if a matching payment plugin could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPlugin fetchByApplicationId(
		java.lang.String applicationId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByApplicationId(applicationId, retrieveFromCache);
	}

	/**
	* Returns all the payment plugins.
	*
	* @return the payment plugins
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the payment plugins.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of payment plugins
	* @param end the upper bound of the range of payment plugins (not inclusive)
	* @return the range of payment plugins
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the payment plugins.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of payment plugins
	* @param end the upper bound of the range of payment plugins (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of payment plugins
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the payment plugins where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Removes the payment plugin where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the payment plugin that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPlugin removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.beorn.onlinepayment.NoSuchPluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Removes all the payment plugins where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Removes the payment plugin where applicationId = &#63; from the database.
	*
	* @param applicationId the application ID
	* @return the payment plugin that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPlugin removeByApplicationId(
		java.lang.String applicationId)
		throws com.beorn.onlinepayment.NoSuchPluginException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByApplicationId(applicationId);
	}

	/**
	* Removes all the payment plugins from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of payment plugins where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching payment plugins
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the number of payment plugins where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching payment plugins
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of payment plugins where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching payment plugins
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns the number of payment plugins where applicationId = &#63;.
	*
	* @param applicationId the application ID
	* @return the number of matching payment plugins
	* @throws SystemException if a system exception occurred
	*/
	public static int countByApplicationId(java.lang.String applicationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByApplicationId(applicationId);
	}

	/**
	* Returns the number of payment plugins.
	*
	* @return the number of payment plugins
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	/**
	* Returns all the payment plugin configs associated with the payment plugin.
	*
	* @param pk the primary key of the payment plugin
	* @return the payment plugin configs associated with the payment plugin
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentPluginConfig> getPaymentPluginConfigs(
		long pk) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getPaymentPluginConfigs(pk);
	}

	/**
	* Returns a range of all the payment plugin configs associated with the payment plugin.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the payment plugin
	* @param start the lower bound of the range of payment plugins
	* @param end the upper bound of the range of payment plugins (not inclusive)
	* @return the range of payment plugin configs associated with the payment plugin
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentPluginConfig> getPaymentPluginConfigs(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getPaymentPluginConfigs(pk, start, end);
	}

	/**
	* Returns an ordered range of all the payment plugin configs associated with the payment plugin.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the payment plugin
	* @param start the lower bound of the range of payment plugins
	* @param end the upper bound of the range of payment plugins (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of payment plugin configs associated with the payment plugin
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentPluginConfig> getPaymentPluginConfigs(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .getPaymentPluginConfigs(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of payment plugin configs associated with the payment plugin.
	*
	* @param pk the primary key of the payment plugin
	* @return the number of payment plugin configs associated with the payment plugin
	* @throws SystemException if a system exception occurred
	*/
	public static int getPaymentPluginConfigsSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getPaymentPluginConfigsSize(pk);
	}

	/**
	* Returns <code>true</code> if the payment plugin config is associated with the payment plugin.
	*
	* @param pk the primary key of the payment plugin
	* @param paymentPluginConfigPK the primary key of the payment plugin config
	* @return <code>true</code> if the payment plugin config is associated with the payment plugin; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsPaymentPluginConfig(long pk,
		long paymentPluginConfigPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .containsPaymentPluginConfig(pk, paymentPluginConfigPK);
	}

	/**
	* Returns <code>true</code> if the payment plugin has any payment plugin configs associated with it.
	*
	* @param pk the primary key of the payment plugin to check for associations with payment plugin configs
	* @return <code>true</code> if the payment plugin has any payment plugin configs associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsPaymentPluginConfigs(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsPaymentPluginConfigs(pk);
	}

	/**
	* Returns all the payment methods associated with the payment plugin.
	*
	* @param pk the primary key of the payment plugin
	* @return the payment methods associated with the payment plugin
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentMethod> getPaymentMethods(
		long pk) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getPaymentMethods(pk);
	}

	/**
	* Returns a range of all the payment methods associated with the payment plugin.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the payment plugin
	* @param start the lower bound of the range of payment plugins
	* @param end the upper bound of the range of payment plugins (not inclusive)
	* @return the range of payment methods associated with the payment plugin
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentMethod> getPaymentMethods(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getPaymentMethods(pk, start, end);
	}

	/**
	* Returns an ordered range of all the payment methods associated with the payment plugin.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the payment plugin
	* @param start the lower bound of the range of payment plugins
	* @param end the upper bound of the range of payment plugins (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of payment methods associated with the payment plugin
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentMethod> getPaymentMethods(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .getPaymentMethods(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of payment methods associated with the payment plugin.
	*
	* @param pk the primary key of the payment plugin
	* @return the number of payment methods associated with the payment plugin
	* @throws SystemException if a system exception occurred
	*/
	public static int getPaymentMethodsSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getPaymentMethodsSize(pk);
	}

	/**
	* Returns <code>true</code> if the payment method is associated with the payment plugin.
	*
	* @param pk the primary key of the payment plugin
	* @param paymentMethodPK the primary key of the payment method
	* @return <code>true</code> if the payment method is associated with the payment plugin; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsPaymentMethod(long pk, long paymentMethodPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsPaymentMethod(pk, paymentMethodPK);
	}

	/**
	* Returns <code>true</code> if the payment plugin has any payment methods associated with it.
	*
	* @param pk the primary key of the payment plugin to check for associations with payment methods
	* @return <code>true</code> if the payment plugin has any payment methods associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsPaymentMethods(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsPaymentMethods(pk);
	}

	/**
	* Adds an association between the payment plugin and the payment method. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the payment plugin
	* @param paymentMethodPK the primary key of the payment method
	* @throws SystemException if a system exception occurred
	*/
	public static void addPaymentMethod(long pk, long paymentMethodPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addPaymentMethod(pk, paymentMethodPK);
	}

	/**
	* Adds an association between the payment plugin and the payment method. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the payment plugin
	* @param paymentMethod the payment method
	* @throws SystemException if a system exception occurred
	*/
	public static void addPaymentMethod(long pk,
		com.beorn.onlinepayment.model.PaymentMethod paymentMethod)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addPaymentMethod(pk, paymentMethod);
	}

	/**
	* Adds an association between the payment plugin and the payment methods. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the payment plugin
	* @param paymentMethodPKs the primary keys of the payment methods
	* @throws SystemException if a system exception occurred
	*/
	public static void addPaymentMethods(long pk, long[] paymentMethodPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addPaymentMethods(pk, paymentMethodPKs);
	}

	/**
	* Adds an association between the payment plugin and the payment methods. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the payment plugin
	* @param paymentMethods the payment methods
	* @throws SystemException if a system exception occurred
	*/
	public static void addPaymentMethods(long pk,
		java.util.List<com.beorn.onlinepayment.model.PaymentMethod> paymentMethods)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addPaymentMethods(pk, paymentMethods);
	}

	/**
	* Clears all associations between the payment plugin and its payment methods. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the payment plugin to clear the associated payment methods from
	* @throws SystemException if a system exception occurred
	*/
	public static void clearPaymentMethods(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().clearPaymentMethods(pk);
	}

	/**
	* Removes the association between the payment plugin and the payment method. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the payment plugin
	* @param paymentMethodPK the primary key of the payment method
	* @throws SystemException if a system exception occurred
	*/
	public static void removePaymentMethod(long pk, long paymentMethodPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removePaymentMethod(pk, paymentMethodPK);
	}

	/**
	* Removes the association between the payment plugin and the payment method. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the payment plugin
	* @param paymentMethod the payment method
	* @throws SystemException if a system exception occurred
	*/
	public static void removePaymentMethod(long pk,
		com.beorn.onlinepayment.model.PaymentMethod paymentMethod)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removePaymentMethod(pk, paymentMethod);
	}

	/**
	* Removes the association between the payment plugin and the payment methods. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the payment plugin
	* @param paymentMethodPKs the primary keys of the payment methods
	* @throws SystemException if a system exception occurred
	*/
	public static void removePaymentMethods(long pk, long[] paymentMethodPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removePaymentMethods(pk, paymentMethodPKs);
	}

	/**
	* Removes the association between the payment plugin and the payment methods. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the payment plugin
	* @param paymentMethods the payment methods
	* @throws SystemException if a system exception occurred
	*/
	public static void removePaymentMethods(long pk,
		java.util.List<com.beorn.onlinepayment.model.PaymentMethod> paymentMethods)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removePaymentMethods(pk, paymentMethods);
	}

	/**
	* Sets the payment methods associated with the payment plugin, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the payment plugin
	* @param paymentMethodPKs the primary keys of the payment methods to be associated with the payment plugin
	* @throws SystemException if a system exception occurred
	*/
	public static void setPaymentMethods(long pk, long[] paymentMethodPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setPaymentMethods(pk, paymentMethodPKs);
	}

	/**
	* Sets the payment methods associated with the payment plugin, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the payment plugin
	* @param paymentMethods the payment methods to be associated with the payment plugin
	* @throws SystemException if a system exception occurred
	*/
	public static void setPaymentMethods(long pk,
		java.util.List<com.beorn.onlinepayment.model.PaymentMethod> paymentMethods)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setPaymentMethods(pk, paymentMethods);
	}

	public static PaymentPluginPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (PaymentPluginPersistence)PortletBeanLocatorUtil.locate(com.beorn.onlinepayment.service.ClpSerializer.getServletContextName(),
					PaymentPluginPersistence.class.getName());

			ReferenceRegistry.registerReference(PaymentPluginUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(PaymentPluginPersistence persistence) {
	}

	private static PaymentPluginPersistence _persistence;
}