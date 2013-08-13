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
 * The utility for the transaction parameter local service. This utility wraps {@link com.beorn.onlinepayment.service.impl.TransactionParameterLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Sebastien Meunier
 * @see TransactionParameterLocalService
 * @see com.beorn.onlinepayment.service.base.TransactionParameterLocalServiceBaseImpl
 * @see com.beorn.onlinepayment.service.impl.TransactionParameterLocalServiceImpl
 * @generated
 */
public class TransactionParameterLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.beorn.onlinepayment.service.impl.TransactionParameterLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the transaction parameter to the database. Also notifies the appropriate model listeners.
	*
	* @param transactionParameter the transaction parameter
	* @return the transaction parameter that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.TransactionParameter addTransactionParameter(
		com.beorn.onlinepayment.model.TransactionParameter transactionParameter)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addTransactionParameter(transactionParameter);
	}

	/**
	* Creates a new transaction parameter with the primary key. Does not add the transaction parameter to the database.
	*
	* @param transactionParameterId the primary key for the new transaction parameter
	* @return the new transaction parameter
	*/
	public static com.beorn.onlinepayment.model.TransactionParameter createTransactionParameter(
		long transactionParameterId) {
		return getService().createTransactionParameter(transactionParameterId);
	}

	/**
	* Deletes the transaction parameter with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param transactionParameterId the primary key of the transaction parameter
	* @return the transaction parameter that was removed
	* @throws PortalException if a transaction parameter with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.TransactionParameter deleteTransactionParameter(
		long transactionParameterId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTransactionParameter(transactionParameterId);
	}

	/**
	* Deletes the transaction parameter from the database. Also notifies the appropriate model listeners.
	*
	* @param transactionParameter the transaction parameter
	* @return the transaction parameter that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.TransactionParameter deleteTransactionParameter(
		com.beorn.onlinepayment.model.TransactionParameter transactionParameter)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTransactionParameter(transactionParameter);
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

	public static com.beorn.onlinepayment.model.TransactionParameter fetchTransactionParameter(
		long transactionParameterId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchTransactionParameter(transactionParameterId);
	}

	/**
	* Returns the transaction parameter with the primary key.
	*
	* @param transactionParameterId the primary key of the transaction parameter
	* @return the transaction parameter
	* @throws PortalException if a transaction parameter with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.TransactionParameter getTransactionParameter(
		long transactionParameterId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTransactionParameter(transactionParameterId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the transaction parameter with the UUID in the group.
	*
	* @param uuid the UUID of transaction parameter
	* @param groupId the group id of the transaction parameter
	* @return the transaction parameter
	* @throws PortalException if a transaction parameter with the UUID in the group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.TransactionParameter getTransactionParameterByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getTransactionParameterByUuidAndGroupId(uuid, groupId);
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
	public static java.util.List<com.beorn.onlinepayment.model.TransactionParameter> getTransactionParameters(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTransactionParameters(start, end);
	}

	/**
	* Returns the number of transaction parameters.
	*
	* @return the number of transaction parameters
	* @throws SystemException if a system exception occurred
	*/
	public static int getTransactionParametersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTransactionParametersCount();
	}

	/**
	* Updates the transaction parameter in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param transactionParameter the transaction parameter
	* @return the transaction parameter that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.TransactionParameter updateTransactionParameter(
		com.beorn.onlinepayment.model.TransactionParameter transactionParameter)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTransactionParameter(transactionParameter);
	}

	/**
	* Updates the transaction parameter in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param transactionParameter the transaction parameter
	* @param merge whether to merge the transaction parameter with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the transaction parameter that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.TransactionParameter updateTransactionParameter(
		com.beorn.onlinepayment.model.TransactionParameter transactionParameter,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateTransactionParameter(transactionParameter, merge);
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

	public static com.beorn.onlinepayment.model.TransactionParameter addTransactionParameter(
		long userId, long transactionId, java.lang.String key,
		java.lang.String value,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addTransactionParameter(userId, transactionId, key, value,
			serviceContext);
	}

	public static java.util.List<com.beorn.onlinepayment.model.TransactionParameter> getTransactionTransactionParameters(
		long transactionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getTransactionTransactionParameters(transactionId, start,
			end, orderByComparator);
	}

	public static java.util.List<java.lang.String> getSellerTransactionParameterKeys(
		long sellerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSellerTransactionParameterKeys(sellerId);
	}

	public static void clearService() {
		_service = null;
	}

	public static TransactionParameterLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					TransactionParameterLocalService.class.getName());

			if (invokableLocalService instanceof TransactionParameterLocalService) {
				_service = (TransactionParameterLocalService)invokableLocalService;
			}
			else {
				_service = new TransactionParameterLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(TransactionParameterLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(TransactionParameterLocalService service) {
	}

	private static TransactionParameterLocalService _service;
}