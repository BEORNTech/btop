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

import com.beorn.onlinepayment.model.Seller;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the seller service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Sebastien Meunier
 * @see SellerPersistenceImpl
 * @see SellerUtil
 * @generated
 */
public interface SellerPersistence extends BasePersistence<Seller> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SellerUtil} to access the seller persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the seller in the entity cache if it is enabled.
	*
	* @param seller the seller
	*/
	public void cacheResult(com.beorn.onlinepayment.model.Seller seller);

	/**
	* Caches the sellers in the entity cache if it is enabled.
	*
	* @param sellers the sellers
	*/
	public void cacheResult(
		java.util.List<com.beorn.onlinepayment.model.Seller> sellers);

	/**
	* Creates a new seller with the primary key. Does not add the seller to the database.
	*
	* @param sellerId the primary key for the new seller
	* @return the new seller
	*/
	public com.beorn.onlinepayment.model.Seller create(long sellerId);

	/**
	* Removes the seller with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param sellerId the primary key of the seller
	* @return the seller that was removed
	* @throws com.beorn.onlinepayment.NoSuchSellerException if a seller with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.Seller remove(long sellerId)
		throws com.beorn.onlinepayment.NoSuchSellerException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.beorn.onlinepayment.model.Seller updateImpl(
		com.beorn.onlinepayment.model.Seller seller, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the seller with the primary key or throws a {@link com.beorn.onlinepayment.NoSuchSellerException} if it could not be found.
	*
	* @param sellerId the primary key of the seller
	* @return the seller
	* @throws com.beorn.onlinepayment.NoSuchSellerException if a seller with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.Seller findByPrimaryKey(long sellerId)
		throws com.beorn.onlinepayment.NoSuchSellerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the seller with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param sellerId the primary key of the seller
	* @return the seller, or <code>null</code> if a seller with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.Seller fetchByPrimaryKey(long sellerId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the sellers where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching sellers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.beorn.onlinepayment.model.Seller> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the sellers where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of sellers
	* @param end the upper bound of the range of sellers (not inclusive)
	* @return the range of matching sellers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.beorn.onlinepayment.model.Seller> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the sellers where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of sellers
	* @param end the upper bound of the range of sellers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sellers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.beorn.onlinepayment.model.Seller> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first seller in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching seller
	* @throws com.beorn.onlinepayment.NoSuchSellerException if a matching seller could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.Seller findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchSellerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first seller in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching seller, or <code>null</code> if a matching seller could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.Seller fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last seller in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching seller
	* @throws com.beorn.onlinepayment.NoSuchSellerException if a matching seller could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.Seller findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchSellerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last seller in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching seller, or <code>null</code> if a matching seller could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.Seller fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the sellers before and after the current seller in the ordered set where uuid = &#63;.
	*
	* @param sellerId the primary key of the current seller
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next seller
	* @throws com.beorn.onlinepayment.NoSuchSellerException if a seller with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.Seller[] findByUuid_PrevAndNext(
		long sellerId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchSellerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the seller where uuid = &#63; and groupId = &#63; or throws a {@link com.beorn.onlinepayment.NoSuchSellerException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching seller
	* @throws com.beorn.onlinepayment.NoSuchSellerException if a matching seller could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.Seller findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.beorn.onlinepayment.NoSuchSellerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the seller where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching seller, or <code>null</code> if a matching seller could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.Seller fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the seller where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching seller, or <code>null</code> if a matching seller could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.Seller fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the sellers where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching sellers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.beorn.onlinepayment.model.Seller> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the sellers where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of sellers
	* @param end the upper bound of the range of sellers (not inclusive)
	* @return the range of matching sellers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.beorn.onlinepayment.model.Seller> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the sellers where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of sellers
	* @param end the upper bound of the range of sellers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sellers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.beorn.onlinepayment.model.Seller> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first seller in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching seller
	* @throws com.beorn.onlinepayment.NoSuchSellerException if a matching seller could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.Seller findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchSellerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first seller in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching seller, or <code>null</code> if a matching seller could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.Seller fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last seller in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching seller
	* @throws com.beorn.onlinepayment.NoSuchSellerException if a matching seller could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.Seller findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchSellerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last seller in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching seller, or <code>null</code> if a matching seller could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.Seller fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the sellers before and after the current seller in the ordered set where companyId = &#63;.
	*
	* @param sellerId the primary key of the current seller
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next seller
	* @throws com.beorn.onlinepayment.NoSuchSellerException if a seller with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.Seller[] findByCompanyId_PrevAndNext(
		long sellerId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.onlinepayment.NoSuchSellerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the seller where companyId = &#63; and name = &#63; or throws a {@link com.beorn.onlinepayment.NoSuchSellerException} if it could not be found.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the matching seller
	* @throws com.beorn.onlinepayment.NoSuchSellerException if a matching seller could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.Seller findByCompanyIdAndName(
		long companyId, java.lang.String name)
		throws com.beorn.onlinepayment.NoSuchSellerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the seller where companyId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the matching seller, or <code>null</code> if a matching seller could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.Seller fetchByCompanyIdAndName(
		long companyId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the seller where companyId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param name the name
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching seller, or <code>null</code> if a matching seller could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.Seller fetchByCompanyIdAndName(
		long companyId, java.lang.String name, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the sellers.
	*
	* @return the sellers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.beorn.onlinepayment.model.Seller> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the sellers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of sellers
	* @param end the upper bound of the range of sellers (not inclusive)
	* @return the range of sellers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.beorn.onlinepayment.model.Seller> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the sellers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of sellers
	* @param end the upper bound of the range of sellers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of sellers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.beorn.onlinepayment.model.Seller> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the sellers where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the seller where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the seller that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.Seller removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.beorn.onlinepayment.NoSuchSellerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the sellers where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the seller where companyId = &#63; and name = &#63; from the database.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the seller that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.Seller removeByCompanyIdAndName(
		long companyId, java.lang.String name)
		throws com.beorn.onlinepayment.NoSuchSellerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the sellers from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of sellers where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching sellers
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of sellers where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching sellers
	* @throws SystemException if a system exception occurred
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of sellers where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching sellers
	* @throws SystemException if a system exception occurred
	*/
	public int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of sellers where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the number of matching sellers
	* @throws SystemException if a system exception occurred
	*/
	public int countByCompanyIdAndName(long companyId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of sellers.
	*
	* @return the number of sellers
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the payment plugin configs associated with the seller.
	*
	* @param pk the primary key of the seller
	* @return the payment plugin configs associated with the seller
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.beorn.onlinepayment.model.PaymentPluginConfig> getPaymentPluginConfigs(
		long pk) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the payment plugin configs associated with the seller.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the seller
	* @param start the lower bound of the range of sellers
	* @param end the upper bound of the range of sellers (not inclusive)
	* @return the range of payment plugin configs associated with the seller
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.beorn.onlinepayment.model.PaymentPluginConfig> getPaymentPluginConfigs(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the payment plugin configs associated with the seller.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the seller
	* @param start the lower bound of the range of sellers
	* @param end the upper bound of the range of sellers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of payment plugin configs associated with the seller
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.beorn.onlinepayment.model.PaymentPluginConfig> getPaymentPluginConfigs(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of payment plugin configs associated with the seller.
	*
	* @param pk the primary key of the seller
	* @return the number of payment plugin configs associated with the seller
	* @throws SystemException if a system exception occurred
	*/
	public int getPaymentPluginConfigsSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns <code>true</code> if the payment plugin config is associated with the seller.
	*
	* @param pk the primary key of the seller
	* @param paymentPluginConfigPK the primary key of the payment plugin config
	* @return <code>true</code> if the payment plugin config is associated with the seller; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public boolean containsPaymentPluginConfig(long pk,
		long paymentPluginConfigPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns <code>true</code> if the seller has any payment plugin configs associated with it.
	*
	* @param pk the primary key of the seller to check for associations with payment plugin configs
	* @return <code>true</code> if the seller has any payment plugin configs associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public boolean containsPaymentPluginConfigs(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the transactions associated with the seller.
	*
	* @param pk the primary key of the seller
	* @return the transactions associated with the seller
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.beorn.onlinepayment.model.Transaction> getTransactions(
		long pk) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the transactions associated with the seller.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the seller
	* @param start the lower bound of the range of sellers
	* @param end the upper bound of the range of sellers (not inclusive)
	* @return the range of transactions associated with the seller
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.beorn.onlinepayment.model.Transaction> getTransactions(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the transactions associated with the seller.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the seller
	* @param start the lower bound of the range of sellers
	* @param end the upper bound of the range of sellers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of transactions associated with the seller
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.beorn.onlinepayment.model.Transaction> getTransactions(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of transactions associated with the seller.
	*
	* @param pk the primary key of the seller
	* @return the number of transactions associated with the seller
	* @throws SystemException if a system exception occurred
	*/
	public int getTransactionsSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns <code>true</code> if the transaction is associated with the seller.
	*
	* @param pk the primary key of the seller
	* @param transactionPK the primary key of the transaction
	* @return <code>true</code> if the transaction is associated with the seller; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public boolean containsTransaction(long pk, long transactionPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns <code>true</code> if the seller has any transactions associated with it.
	*
	* @param pk the primary key of the seller to check for associations with transactions
	* @return <code>true</code> if the seller has any transactions associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public boolean containsTransactions(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;
}