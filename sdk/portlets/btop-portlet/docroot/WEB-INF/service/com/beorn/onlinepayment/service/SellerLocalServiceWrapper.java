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

package com.beorn.onlinepayment.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link SellerLocalService}.
 * </p>
 *
 * @author    Sebastien Meunier
 * @see       SellerLocalService
 * @generated
 */
public class SellerLocalServiceWrapper implements SellerLocalService,
	ServiceWrapper<SellerLocalService> {
	public SellerLocalServiceWrapper(SellerLocalService sellerLocalService) {
		_sellerLocalService = sellerLocalService;
	}

	/**
	* Adds the seller to the database. Also notifies the appropriate model listeners.
	*
	* @param seller the seller
	* @return the seller that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.Seller addSeller(
		com.beorn.onlinepayment.model.Seller seller)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _sellerLocalService.addSeller(seller);
	}

	/**
	* Creates a new seller with the primary key. Does not add the seller to the database.
	*
	* @param sellerId the primary key for the new seller
	* @return the new seller
	*/
	public com.beorn.onlinepayment.model.Seller createSeller(long sellerId) {
		return _sellerLocalService.createSeller(sellerId);
	}

	/**
	* Deletes the seller with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param sellerId the primary key of the seller
	* @return the seller that was removed
	* @throws PortalException if a seller with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.Seller deleteSeller(long sellerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _sellerLocalService.deleteSeller(sellerId);
	}

	/**
	* Deletes the seller from the database. Also notifies the appropriate model listeners.
	*
	* @param seller the seller
	* @return the seller that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.Seller deleteSeller(
		com.beorn.onlinepayment.model.Seller seller)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _sellerLocalService.deleteSeller(seller);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _sellerLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _sellerLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _sellerLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _sellerLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _sellerLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.beorn.onlinepayment.model.Seller fetchSeller(long sellerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _sellerLocalService.fetchSeller(sellerId);
	}

	/**
	* Returns the seller with the primary key.
	*
	* @param sellerId the primary key of the seller
	* @return the seller
	* @throws PortalException if a seller with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.Seller getSeller(long sellerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _sellerLocalService.getSeller(sellerId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _sellerLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the seller with the UUID in the group.
	*
	* @param uuid the UUID of seller
	* @param groupId the group id of the seller
	* @return the seller
	* @throws PortalException if a seller with the UUID in the group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.Seller getSellerByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _sellerLocalService.getSellerByUuidAndGroupId(uuid, groupId);
	}

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
	public java.util.List<com.beorn.onlinepayment.model.Seller> getSellers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _sellerLocalService.getSellers(start, end);
	}

	/**
	* Returns the number of sellers.
	*
	* @return the number of sellers
	* @throws SystemException if a system exception occurred
	*/
	public int getSellersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _sellerLocalService.getSellersCount();
	}

	/**
	* Updates the seller in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param seller the seller
	* @return the seller that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.Seller updateSeller(
		com.beorn.onlinepayment.model.Seller seller)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _sellerLocalService.updateSeller(seller);
	}

	/**
	* Updates the seller in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param seller the seller
	* @param merge whether to merge the seller with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the seller that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.Seller updateSeller(
		com.beorn.onlinepayment.model.Seller seller, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _sellerLocalService.updateSeller(seller, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _sellerLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_sellerLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _sellerLocalService.invokeMethod(name, parameterTypes, arguments);
	}

	public com.beorn.onlinepayment.model.Seller addSeller(long userId,
		java.lang.String name, boolean active,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _sellerLocalService.addSeller(userId, name, active,
			serviceContext);
	}

	public java.util.List<com.beorn.onlinepayment.model.Seller> getSellers(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _sellerLocalService.getSellers(start, end, orderByComparator);
	}

	public com.beorn.onlinepayment.model.Seller getDefaultSeller(
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _sellerLocalService.getDefaultSeller(serviceContext);
	}

	public java.util.List<com.beorn.onlinepayment.model.Seller> getCompanySellers(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _sellerLocalService.getCompanySellers(companyId, start, end,
			orderByComparator);
	}

	public int getCompanySellersCount(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _sellerLocalService.getCompanySellersCount(companyId);
	}

	public com.beorn.onlinepayment.model.Seller getSellerByCompanyIdAndName(
		long companyId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _sellerLocalService.getSellerByCompanyIdAndName(companyId, name);
	}

	public java.util.List<com.beorn.onlinepayment.model.Seller> search(
		long companyId, java.lang.String keywords, java.lang.Boolean active,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _sellerLocalService.search(companyId, keywords, active, start,
			end, orderByComparator);
	}

	public int searchCount(long companyId, java.lang.String keywords,
		java.lang.Boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _sellerLocalService.searchCount(companyId, keywords, active);
	}

	public com.beorn.onlinepayment.model.Seller updateSeller(long sellerId,
		java.lang.String name, boolean active,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _sellerLocalService.updateSeller(sellerId, name, active,
			serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public SellerLocalService getWrappedSellerLocalService() {
		return _sellerLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedSellerLocalService(
		SellerLocalService sellerLocalService) {
		_sellerLocalService = sellerLocalService;
	}

	public SellerLocalService getWrappedService() {
		return _sellerLocalService;
	}

	public void setWrappedService(SellerLocalService sellerLocalService) {
		_sellerLocalService = sellerLocalService;
	}

	private SellerLocalService _sellerLocalService;
}