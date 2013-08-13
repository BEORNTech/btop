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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * The utility for the seller local service. This utility wraps {@link com.beorn.onlinepayment.service.impl.SellerLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Sebastien Meunier
 * @see SellerLocalService
 * @see com.beorn.onlinepayment.service.base.SellerLocalServiceBaseImpl
 * @see com.beorn.onlinepayment.service.impl.SellerLocalServiceImpl
 * @generated
 */
public class SellerLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.beorn.onlinepayment.service.impl.SellerLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the seller to the database. Also notifies the appropriate model listeners.
	*
	* @param seller the seller
	* @return the seller that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Seller addSeller(
		com.beorn.onlinepayment.model.Seller seller)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addSeller(seller);
	}

	/**
	* Creates a new seller with the primary key. Does not add the seller to the database.
	*
	* @param sellerId the primary key for the new seller
	* @return the new seller
	*/
	public static com.beorn.onlinepayment.model.Seller createSeller(
		long sellerId) {
		return getService().createSeller(sellerId);
	}

	/**
	* Deletes the seller with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param sellerId the primary key of the seller
	* @return the seller that was removed
	* @throws PortalException if a seller with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Seller deleteSeller(
		long sellerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteSeller(sellerId);
	}

	/**
	* Deletes the seller from the database. Also notifies the appropriate model listeners.
	*
	* @param seller the seller
	* @return the seller that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Seller deleteSeller(
		com.beorn.onlinepayment.model.Seller seller)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteSeller(seller);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static com.beorn.onlinepayment.model.Seller fetchSeller(
		long sellerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchSeller(sellerId);
	}

	/**
	* Returns the seller with the primary key.
	*
	* @param sellerId the primary key of the seller
	* @return the seller
	* @throws PortalException if a seller with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Seller getSeller(long sellerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getSeller(sellerId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
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
	public static com.beorn.onlinepayment.model.Seller getSellerByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getSellerByUuidAndGroupId(uuid, groupId);
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
	public static java.util.List<com.beorn.onlinepayment.model.Seller> getSellers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSellers(start, end);
	}

	/**
	* Returns the number of sellers.
	*
	* @return the number of sellers
	* @throws SystemException if a system exception occurred
	*/
	public static int getSellersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSellersCount();
	}

	/**
	* Updates the seller in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param seller the seller
	* @return the seller that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Seller updateSeller(
		com.beorn.onlinepayment.model.Seller seller)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateSeller(seller);
	}

	/**
	* Updates the seller in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param seller the seller
	* @param merge whether to merge the seller with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the seller that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Seller updateSeller(
		com.beorn.onlinepayment.model.Seller seller, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateSeller(seller, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static com.beorn.onlinepayment.model.Seller addSeller(long userId,
		java.lang.String name, boolean active,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().addSeller(userId, name, active, serviceContext);
	}

	public static java.util.List<com.beorn.onlinepayment.model.Seller> getSellers(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getSellers(start, end, orderByComparator);
	}

	public static com.beorn.onlinepayment.model.Seller getDefaultSeller(
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getDefaultSeller(serviceContext);
	}

	public static java.util.List<com.beorn.onlinepayment.model.Seller> getCompanySellers(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getCompanySellers(companyId, start, end, orderByComparator);
	}

	public static int getCompanySellersCount(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getCompanySellersCount(companyId);
	}

	public static com.beorn.onlinepayment.model.Seller getSellerByCompanyIdAndName(
		long companyId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getSellerByCompanyIdAndName(companyId, name);
	}

	public static java.util.List<com.beorn.onlinepayment.model.Seller> search(
		long companyId, java.lang.String keywords, java.lang.Boolean active,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(companyId, keywords, active, start, end,
			orderByComparator);
	}

	public static int searchCount(long companyId, java.lang.String keywords,
		java.lang.Boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().searchCount(companyId, keywords, active);
	}

	public static com.beorn.onlinepayment.model.Seller updateSeller(
		long sellerId, java.lang.String name, boolean active,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().updateSeller(sellerId, name, active, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static SellerLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					SellerLocalService.class.getName());

			if (invokableLocalService instanceof SellerLocalService) {
				_service = (SellerLocalService)invokableLocalService;
			}
			else {
				_service = new SellerLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(SellerLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(SellerLocalService service) {
	}

	private static SellerLocalService _service;
}