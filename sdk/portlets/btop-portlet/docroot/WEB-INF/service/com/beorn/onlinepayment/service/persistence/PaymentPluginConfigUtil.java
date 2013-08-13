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

import com.beorn.onlinepayment.model.PaymentPluginConfig;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the payment plugin config service. This utility wraps {@link PaymentPluginConfigPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Sebastien Meunier
 * @see PaymentPluginConfigPersistence
 * @see PaymentPluginConfigPersistenceImpl
 * @generated
 */
public class PaymentPluginConfigUtil {
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
	public static void clearCache(PaymentPluginConfig paymentPluginConfig) {
		getPersistence().clearCache(paymentPluginConfig);
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
	public static List<PaymentPluginConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PaymentPluginConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PaymentPluginConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static PaymentPluginConfig update(
		PaymentPluginConfig paymentPluginConfig, boolean merge)
		throws SystemException {
		return getPersistence().update(paymentPluginConfig, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static PaymentPluginConfig update(
		PaymentPluginConfig paymentPluginConfig, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence()
				   .update(paymentPluginConfig, merge, serviceContext);
	}

	/**
	* Caches the payment plugin config in the entity cache if it is enabled.
	*
	* @param paymentPluginConfig the payment plugin config
	*/
	public static void cacheResult(
		com.beorn.onlinepayment.model.PaymentPluginConfig paymentPluginConfig) {
		getPersistence().cacheResult(paymentPluginConfig);
	}

	/**
	* Caches the payment plugin configs in the entity cache if it is enabled.
	*
	* @param paymentPluginConfigs the payment plugin configs
	*/
	public static void cacheResult(
		java.util.List<com.beorn.onlinepayment.model.PaymentPluginConfig> paymentPluginConfigs) {
		getPersistence().cacheResult(paymentPluginConfigs);
	}

	/**
	* Creates a new payment plugin config with the primary key. Does not add the payment plugin config to the database.
	*
	* @param paymentPluginConfigId the primary key for the new payment plugin config
	* @return the new payment plugin config
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig create(
		long paymentPluginConfigId) {
		return getPersistence().create(paymentPluginConfigId);
	}

	/**
	* Removes the payment plugin config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param paymentPluginConfigId the primary key of the payment plugin config
	* @return the payment plugin config that was removed
	* @throws com.beorn.onlinepayment.NoSuchPluginConfigException if a payment plugin config with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig remove(
		long paymentPluginConfigId)
		throws com.beorn.onlinepayment.NoSuchPluginConfigException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(paymentPluginConfigId);
	}

	public static com.beorn.onlinepayment.model.PaymentPluginConfig updateImpl(
		com.beorn.onlinepayment.model.PaymentPluginConfig paymentPluginConfig,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(paymentPluginConfig, merge);
	}

	/**
	* Returns the payment plugin config with the primary key or throws a {@link com.beorn.onlinepayment.NoSuchPluginConfigException} if it could not be found.
	*
	* @param paymentPluginConfigId the primary key of the payment plugin config
	* @return the payment plugin config
	* @throws com.beorn.onlinepayment.NoSuchPluginConfigException if a payment plugin config with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig findByPrimaryKey(
		long paymentPluginConfigId)
		throws com.beorn.onlinepayment.NoSuchPluginConfigException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(paymentPluginConfigId);
	}

	/**
	* Returns the payment plugin config with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param paymentPluginConfigId the primary key of the payment plugin config
	* @return the payment plugin config, or <code>null</code> if a payment plugin config with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig fetchByPrimaryKey(
		long paymentPluginConfigId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(paymentPluginConfigId);
	}

	/**
	* Returns all the payment plugin configs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching payment plugin configs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentPluginConfig> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the payment plugin configs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of payment plugin configs
	* @param end the upper bound of the range of payment plugin configs (not inclusive)
	* @return the range of matching payment plugin configs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentPluginConfig> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the payment plugin configs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of payment plugin configs
	* @param end the upper bound of the range of payment plugin configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching payment plugin configs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentPluginConfig> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first payment plugin config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment plugin config
	* @throws com.beorn.onlinepayment.NoSuchPluginConfigException if a matching payment plugin config could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchPluginConfigException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first payment plugin config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment plugin config, or <code>null</code> if a matching payment plugin config could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last payment plugin config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment plugin config
	* @throws com.beorn.onlinepayment.NoSuchPluginConfigException if a matching payment plugin config could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchPluginConfigException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last payment plugin config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment plugin config, or <code>null</code> if a matching payment plugin config could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the payment plugin configs before and after the current payment plugin config in the ordered set where uuid = &#63;.
	*
	* @param paymentPluginConfigId the primary key of the current payment plugin config
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next payment plugin config
	* @throws com.beorn.onlinepayment.NoSuchPluginConfigException if a payment plugin config with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig[] findByUuid_PrevAndNext(
		long paymentPluginConfigId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchPluginConfigException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_PrevAndNext(paymentPluginConfigId, uuid,
			orderByComparator);
	}

	/**
	* Returns the payment plugin config where uuid = &#63; and groupId = &#63; or throws a {@link com.beorn.onlinepayment.NoSuchPluginConfigException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching payment plugin config
	* @throws com.beorn.onlinepayment.NoSuchPluginConfigException if a matching payment plugin config could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.beorn.onlinepayment.NoSuchPluginConfigException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the payment plugin config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching payment plugin config, or <code>null</code> if a matching payment plugin config could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the payment plugin config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching payment plugin config, or <code>null</code> if a matching payment plugin config could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Returns all the payment plugin configs where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching payment plugin configs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentPluginConfig> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the payment plugin configs where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of payment plugin configs
	* @param end the upper bound of the range of payment plugin configs (not inclusive)
	* @return the range of matching payment plugin configs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentPluginConfig> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the payment plugin configs where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of payment plugin configs
	* @param end the upper bound of the range of payment plugin configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching payment plugin configs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentPluginConfig> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first payment plugin config in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment plugin config
	* @throws com.beorn.onlinepayment.NoSuchPluginConfigException if a matching payment plugin config could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchPluginConfigException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first payment plugin config in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment plugin config, or <code>null</code> if a matching payment plugin config could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last payment plugin config in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment plugin config
	* @throws com.beorn.onlinepayment.NoSuchPluginConfigException if a matching payment plugin config could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchPluginConfigException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last payment plugin config in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment plugin config, or <code>null</code> if a matching payment plugin config could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the payment plugin configs before and after the current payment plugin config in the ordered set where companyId = &#63;.
	*
	* @param paymentPluginConfigId the primary key of the current payment plugin config
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next payment plugin config
	* @throws com.beorn.onlinepayment.NoSuchPluginConfigException if a payment plugin config with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig[] findByCompanyId_PrevAndNext(
		long paymentPluginConfigId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchPluginConfigException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(paymentPluginConfigId,
			companyId, orderByComparator);
	}

	/**
	* Returns all the payment plugin configs where sellerId = &#63;.
	*
	* @param sellerId the seller ID
	* @return the matching payment plugin configs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentPluginConfig> findBySellerId(
		long sellerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBySellerId(sellerId);
	}

	/**
	* Returns a range of all the payment plugin configs where sellerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param sellerId the seller ID
	* @param start the lower bound of the range of payment plugin configs
	* @param end the upper bound of the range of payment plugin configs (not inclusive)
	* @return the range of matching payment plugin configs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentPluginConfig> findBySellerId(
		long sellerId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBySellerId(sellerId, start, end);
	}

	/**
	* Returns an ordered range of all the payment plugin configs where sellerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param sellerId the seller ID
	* @param start the lower bound of the range of payment plugin configs
	* @param end the upper bound of the range of payment plugin configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching payment plugin configs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentPluginConfig> findBySellerId(
		long sellerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySellerId(sellerId, start, end, orderByComparator);
	}

	/**
	* Returns the first payment plugin config in the ordered set where sellerId = &#63;.
	*
	* @param sellerId the seller ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment plugin config
	* @throws com.beorn.onlinepayment.NoSuchPluginConfigException if a matching payment plugin config could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig findBySellerId_First(
		long sellerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchPluginConfigException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBySellerId_First(sellerId, orderByComparator);
	}

	/**
	* Returns the first payment plugin config in the ordered set where sellerId = &#63;.
	*
	* @param sellerId the seller ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment plugin config, or <code>null</code> if a matching payment plugin config could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig fetchBySellerId_First(
		long sellerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySellerId_First(sellerId, orderByComparator);
	}

	/**
	* Returns the last payment plugin config in the ordered set where sellerId = &#63;.
	*
	* @param sellerId the seller ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment plugin config
	* @throws com.beorn.onlinepayment.NoSuchPluginConfigException if a matching payment plugin config could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig findBySellerId_Last(
		long sellerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchPluginConfigException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBySellerId_Last(sellerId, orderByComparator);
	}

	/**
	* Returns the last payment plugin config in the ordered set where sellerId = &#63;.
	*
	* @param sellerId the seller ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment plugin config, or <code>null</code> if a matching payment plugin config could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig fetchBySellerId_Last(
		long sellerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchBySellerId_Last(sellerId, orderByComparator);
	}

	/**
	* Returns the payment plugin configs before and after the current payment plugin config in the ordered set where sellerId = &#63;.
	*
	* @param paymentPluginConfigId the primary key of the current payment plugin config
	* @param sellerId the seller ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next payment plugin config
	* @throws com.beorn.onlinepayment.NoSuchPluginConfigException if a payment plugin config with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig[] findBySellerId_PrevAndNext(
		long paymentPluginConfigId, long sellerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchPluginConfigException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySellerId_PrevAndNext(paymentPluginConfigId, sellerId,
			orderByComparator);
	}

	/**
	* Returns all the payment plugin configs where paymentPluginId = &#63;.
	*
	* @param paymentPluginId the payment plugin ID
	* @return the matching payment plugin configs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentPluginConfig> findByPaymentPluginId(
		long paymentPluginId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPaymentPluginId(paymentPluginId);
	}

	/**
	* Returns a range of all the payment plugin configs where paymentPluginId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param paymentPluginId the payment plugin ID
	* @param start the lower bound of the range of payment plugin configs
	* @param end the upper bound of the range of payment plugin configs (not inclusive)
	* @return the range of matching payment plugin configs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentPluginConfig> findByPaymentPluginId(
		long paymentPluginId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPaymentPluginId(paymentPluginId, start, end);
	}

	/**
	* Returns an ordered range of all the payment plugin configs where paymentPluginId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param paymentPluginId the payment plugin ID
	* @param start the lower bound of the range of payment plugin configs
	* @param end the upper bound of the range of payment plugin configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching payment plugin configs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentPluginConfig> findByPaymentPluginId(
		long paymentPluginId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPaymentPluginId(paymentPluginId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first payment plugin config in the ordered set where paymentPluginId = &#63;.
	*
	* @param paymentPluginId the payment plugin ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment plugin config
	* @throws com.beorn.onlinepayment.NoSuchPluginConfigException if a matching payment plugin config could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig findByPaymentPluginId_First(
		long paymentPluginId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchPluginConfigException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPaymentPluginId_First(paymentPluginId,
			orderByComparator);
	}

	/**
	* Returns the first payment plugin config in the ordered set where paymentPluginId = &#63;.
	*
	* @param paymentPluginId the payment plugin ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment plugin config, or <code>null</code> if a matching payment plugin config could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig fetchByPaymentPluginId_First(
		long paymentPluginId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByPaymentPluginId_First(paymentPluginId,
			orderByComparator);
	}

	/**
	* Returns the last payment plugin config in the ordered set where paymentPluginId = &#63;.
	*
	* @param paymentPluginId the payment plugin ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment plugin config
	* @throws com.beorn.onlinepayment.NoSuchPluginConfigException if a matching payment plugin config could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig findByPaymentPluginId_Last(
		long paymentPluginId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchPluginConfigException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPaymentPluginId_Last(paymentPluginId,
			orderByComparator);
	}

	/**
	* Returns the last payment plugin config in the ordered set where paymentPluginId = &#63;.
	*
	* @param paymentPluginId the payment plugin ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment plugin config, or <code>null</code> if a matching payment plugin config could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig fetchByPaymentPluginId_Last(
		long paymentPluginId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByPaymentPluginId_Last(paymentPluginId,
			orderByComparator);
	}

	/**
	* Returns the payment plugin configs before and after the current payment plugin config in the ordered set where paymentPluginId = &#63;.
	*
	* @param paymentPluginConfigId the primary key of the current payment plugin config
	* @param paymentPluginId the payment plugin ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next payment plugin config
	* @throws com.beorn.onlinepayment.NoSuchPluginConfigException if a payment plugin config with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig[] findByPaymentPluginId_PrevAndNext(
		long paymentPluginConfigId, long paymentPluginId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchPluginConfigException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPaymentPluginId_PrevAndNext(paymentPluginConfigId,
			paymentPluginId, orderByComparator);
	}

	/**
	* Returns the payment plugin config where sellerId = &#63; and paymentPluginId = &#63; or throws a {@link com.beorn.onlinepayment.NoSuchPluginConfigException} if it could not be found.
	*
	* @param sellerId the seller ID
	* @param paymentPluginId the payment plugin ID
	* @return the matching payment plugin config
	* @throws com.beorn.onlinepayment.NoSuchPluginConfigException if a matching payment plugin config could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig findBySellerIdAndPaymentPluginId(
		long sellerId, long paymentPluginId)
		throws com.beorn.onlinepayment.NoSuchPluginConfigException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySellerIdAndPaymentPluginId(sellerId, paymentPluginId);
	}

	/**
	* Returns the payment plugin config where sellerId = &#63; and paymentPluginId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param sellerId the seller ID
	* @param paymentPluginId the payment plugin ID
	* @return the matching payment plugin config, or <code>null</code> if a matching payment plugin config could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig fetchBySellerIdAndPaymentPluginId(
		long sellerId, long paymentPluginId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySellerIdAndPaymentPluginId(sellerId, paymentPluginId);
	}

	/**
	* Returns the payment plugin config where sellerId = &#63; and paymentPluginId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param sellerId the seller ID
	* @param paymentPluginId the payment plugin ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching payment plugin config, or <code>null</code> if a matching payment plugin config could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig fetchBySellerIdAndPaymentPluginId(
		long sellerId, long paymentPluginId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySellerIdAndPaymentPluginId(sellerId,
			paymentPluginId, retrieveFromCache);
	}

	/**
	* Returns the payment plugin config where sellerId = &#63; and defaultPlugin = &#63; or throws a {@link com.beorn.onlinepayment.NoSuchPluginConfigException} if it could not be found.
	*
	* @param sellerId the seller ID
	* @param defaultPlugin the default plugin
	* @return the matching payment plugin config
	* @throws com.beorn.onlinepayment.NoSuchPluginConfigException if a matching payment plugin config could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig findBySellerIdAndDefaultPlugin(
		long sellerId, boolean defaultPlugin)
		throws com.beorn.onlinepayment.NoSuchPluginConfigException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySellerIdAndDefaultPlugin(sellerId, defaultPlugin);
	}

	/**
	* Returns the payment plugin config where sellerId = &#63; and defaultPlugin = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param sellerId the seller ID
	* @param defaultPlugin the default plugin
	* @return the matching payment plugin config, or <code>null</code> if a matching payment plugin config could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig fetchBySellerIdAndDefaultPlugin(
		long sellerId, boolean defaultPlugin)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySellerIdAndDefaultPlugin(sellerId, defaultPlugin);
	}

	/**
	* Returns the payment plugin config where sellerId = &#63; and defaultPlugin = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param sellerId the seller ID
	* @param defaultPlugin the default plugin
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching payment plugin config, or <code>null</code> if a matching payment plugin config could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig fetchBySellerIdAndDefaultPlugin(
		long sellerId, boolean defaultPlugin, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySellerIdAndDefaultPlugin(sellerId, defaultPlugin,
			retrieveFromCache);
	}

	/**
	* Returns all the payment plugin configs.
	*
	* @return the payment plugin configs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentPluginConfig> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the payment plugin configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of payment plugin configs
	* @param end the upper bound of the range of payment plugin configs (not inclusive)
	* @return the range of payment plugin configs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentPluginConfig> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the payment plugin configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of payment plugin configs
	* @param end the upper bound of the range of payment plugin configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of payment plugin configs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.PaymentPluginConfig> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the payment plugin configs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Removes the payment plugin config where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the payment plugin config that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.beorn.onlinepayment.NoSuchPluginConfigException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Removes all the payment plugin configs where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Removes all the payment plugin configs where sellerId = &#63; from the database.
	*
	* @param sellerId the seller ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeBySellerId(long sellerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeBySellerId(sellerId);
	}

	/**
	* Removes all the payment plugin configs where paymentPluginId = &#63; from the database.
	*
	* @param paymentPluginId the payment plugin ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByPaymentPluginId(long paymentPluginId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByPaymentPluginId(paymentPluginId);
	}

	/**
	* Removes the payment plugin config where sellerId = &#63; and paymentPluginId = &#63; from the database.
	*
	* @param sellerId the seller ID
	* @param paymentPluginId the payment plugin ID
	* @return the payment plugin config that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig removeBySellerIdAndPaymentPluginId(
		long sellerId, long paymentPluginId)
		throws com.beorn.onlinepayment.NoSuchPluginConfigException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .removeBySellerIdAndPaymentPluginId(sellerId, paymentPluginId);
	}

	/**
	* Removes the payment plugin config where sellerId = &#63; and defaultPlugin = &#63; from the database.
	*
	* @param sellerId the seller ID
	* @param defaultPlugin the default plugin
	* @return the payment plugin config that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig removeBySellerIdAndDefaultPlugin(
		long sellerId, boolean defaultPlugin)
		throws com.beorn.onlinepayment.NoSuchPluginConfigException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .removeBySellerIdAndDefaultPlugin(sellerId, defaultPlugin);
	}

	/**
	* Removes all the payment plugin configs from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of payment plugin configs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching payment plugin configs
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the number of payment plugin configs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching payment plugin configs
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of payment plugin configs where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching payment plugin configs
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns the number of payment plugin configs where sellerId = &#63;.
	*
	* @param sellerId the seller ID
	* @return the number of matching payment plugin configs
	* @throws SystemException if a system exception occurred
	*/
	public static int countBySellerId(long sellerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countBySellerId(sellerId);
	}

	/**
	* Returns the number of payment plugin configs where paymentPluginId = &#63;.
	*
	* @param paymentPluginId the payment plugin ID
	* @return the number of matching payment plugin configs
	* @throws SystemException if a system exception occurred
	*/
	public static int countByPaymentPluginId(long paymentPluginId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByPaymentPluginId(paymentPluginId);
	}

	/**
	* Returns the number of payment plugin configs where sellerId = &#63; and paymentPluginId = &#63;.
	*
	* @param sellerId the seller ID
	* @param paymentPluginId the payment plugin ID
	* @return the number of matching payment plugin configs
	* @throws SystemException if a system exception occurred
	*/
	public static int countBySellerIdAndPaymentPluginId(long sellerId,
		long paymentPluginId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countBySellerIdAndPaymentPluginId(sellerId, paymentPluginId);
	}

	/**
	* Returns the number of payment plugin configs where sellerId = &#63; and defaultPlugin = &#63;.
	*
	* @param sellerId the seller ID
	* @param defaultPlugin the default plugin
	* @return the number of matching payment plugin configs
	* @throws SystemException if a system exception occurred
	*/
	public static int countBySellerIdAndDefaultPlugin(long sellerId,
		boolean defaultPlugin)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countBySellerIdAndDefaultPlugin(sellerId, defaultPlugin);
	}

	/**
	* Returns the number of payment plugin configs.
	*
	* @return the number of payment plugin configs
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	/**
	* Returns all the rules associated with the payment plugin config.
	*
	* @param pk the primary key of the payment plugin config
	* @return the rules associated with the payment plugin config
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.Rule> getRules(
		long pk) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getRules(pk);
	}

	/**
	* Returns a range of all the rules associated with the payment plugin config.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the payment plugin config
	* @param start the lower bound of the range of payment plugin configs
	* @param end the upper bound of the range of payment plugin configs (not inclusive)
	* @return the range of rules associated with the payment plugin config
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.Rule> getRules(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getRules(pk, start, end);
	}

	/**
	* Returns an ordered range of all the rules associated with the payment plugin config.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the payment plugin config
	* @param start the lower bound of the range of payment plugin configs
	* @param end the upper bound of the range of payment plugin configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of rules associated with the payment plugin config
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.Rule> getRules(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getRules(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of rules associated with the payment plugin config.
	*
	* @param pk the primary key of the payment plugin config
	* @return the number of rules associated with the payment plugin config
	* @throws SystemException if a system exception occurred
	*/
	public static int getRulesSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getRulesSize(pk);
	}

	/**
	* Returns <code>true</code> if the rule is associated with the payment plugin config.
	*
	* @param pk the primary key of the payment plugin config
	* @param rulePK the primary key of the rule
	* @return <code>true</code> if the rule is associated with the payment plugin config; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsRule(long pk, long rulePK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsRule(pk, rulePK);
	}

	/**
	* Returns <code>true</code> if the payment plugin config has any rules associated with it.
	*
	* @param pk the primary key of the payment plugin config to check for associations with rules
	* @return <code>true</code> if the payment plugin config has any rules associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsRules(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsRules(pk);
	}

	public static PaymentPluginConfigPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (PaymentPluginConfigPersistence)PortletBeanLocatorUtil.locate(com.beorn.onlinepayment.service.ClpSerializer.getServletContextName(),
					PaymentPluginConfigPersistence.class.getName());

			ReferenceRegistry.registerReference(PaymentPluginConfigUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(PaymentPluginConfigPersistence persistence) {
	}

	private static PaymentPluginConfigPersistence _persistence;
}