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
 * This class is a wrapper for {@link TransactionParameterLocalService}.
 * </p>
 *
 * @author    Sebastien Meunier
 * @see       TransactionParameterLocalService
 * @generated
 */
public class TransactionParameterLocalServiceWrapper
	implements TransactionParameterLocalService,
		ServiceWrapper<TransactionParameterLocalService> {
	public TransactionParameterLocalServiceWrapper(
		TransactionParameterLocalService transactionParameterLocalService) {
		_transactionParameterLocalService = transactionParameterLocalService;
	}

	/**
	* Adds the transaction parameter to the database. Also notifies the appropriate model listeners.
	*
	* @param transactionParameter the transaction parameter
	* @return the transaction parameter that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.TransactionParameter addTransactionParameter(
		com.beorn.onlinepayment.model.TransactionParameter transactionParameter)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _transactionParameterLocalService.addTransactionParameter(transactionParameter);
	}

	/**
	* Creates a new transaction parameter with the primary key. Does not add the transaction parameter to the database.
	*
	* @param transactionParameterId the primary key for the new transaction parameter
	* @return the new transaction parameter
	*/
	public com.beorn.onlinepayment.model.TransactionParameter createTransactionParameter(
		long transactionParameterId) {
		return _transactionParameterLocalService.createTransactionParameter(transactionParameterId);
	}

	/**
	* Deletes the transaction parameter with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param transactionParameterId the primary key of the transaction parameter
	* @return the transaction parameter that was removed
	* @throws PortalException if a transaction parameter with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.TransactionParameter deleteTransactionParameter(
		long transactionParameterId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _transactionParameterLocalService.deleteTransactionParameter(transactionParameterId);
	}

	/**
	* Deletes the transaction parameter from the database. Also notifies the appropriate model listeners.
	*
	* @param transactionParameter the transaction parameter
	* @return the transaction parameter that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.TransactionParameter deleteTransactionParameter(
		com.beorn.onlinepayment.model.TransactionParameter transactionParameter)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _transactionParameterLocalService.deleteTransactionParameter(transactionParameter);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _transactionParameterLocalService.dynamicQuery();
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
		return _transactionParameterLocalService.dynamicQuery(dynamicQuery);
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
		return _transactionParameterLocalService.dynamicQuery(dynamicQuery,
			start, end);
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
		return _transactionParameterLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
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
		return _transactionParameterLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.beorn.onlinepayment.model.TransactionParameter fetchTransactionParameter(
		long transactionParameterId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _transactionParameterLocalService.fetchTransactionParameter(transactionParameterId);
	}

	/**
	* Returns the transaction parameter with the primary key.
	*
	* @param transactionParameterId the primary key of the transaction parameter
	* @return the transaction parameter
	* @throws PortalException if a transaction parameter with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.TransactionParameter getTransactionParameter(
		long transactionParameterId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _transactionParameterLocalService.getTransactionParameter(transactionParameterId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _transactionParameterLocalService.getPersistedModel(primaryKeyObj);
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
	public com.beorn.onlinepayment.model.TransactionParameter getTransactionParameterByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _transactionParameterLocalService.getTransactionParameterByUuidAndGroupId(uuid,
			groupId);
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
	public java.util.List<com.beorn.onlinepayment.model.TransactionParameter> getTransactionParameters(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _transactionParameterLocalService.getTransactionParameters(start,
			end);
	}

	/**
	* Returns the number of transaction parameters.
	*
	* @return the number of transaction parameters
	* @throws SystemException if a system exception occurred
	*/
	public int getTransactionParametersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _transactionParameterLocalService.getTransactionParametersCount();
	}

	/**
	* Updates the transaction parameter in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param transactionParameter the transaction parameter
	* @return the transaction parameter that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.TransactionParameter updateTransactionParameter(
		com.beorn.onlinepayment.model.TransactionParameter transactionParameter)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _transactionParameterLocalService.updateTransactionParameter(transactionParameter);
	}

	/**
	* Updates the transaction parameter in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param transactionParameter the transaction parameter
	* @param merge whether to merge the transaction parameter with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the transaction parameter that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.TransactionParameter updateTransactionParameter(
		com.beorn.onlinepayment.model.TransactionParameter transactionParameter,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _transactionParameterLocalService.updateTransactionParameter(transactionParameter,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _transactionParameterLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_transactionParameterLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _transactionParameterLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	public com.beorn.onlinepayment.model.TransactionParameter addTransactionParameter(
		long userId, long transactionId, java.lang.String key,
		java.lang.String value,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _transactionParameterLocalService.addTransactionParameter(userId,
			transactionId, key, value, serviceContext);
	}

	public java.util.List<com.beorn.onlinepayment.model.TransactionParameter> getTransactionTransactionParameters(
		long transactionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _transactionParameterLocalService.getTransactionTransactionParameters(transactionId,
			start, end, orderByComparator);
	}

	public java.util.List<java.lang.String> getSellerTransactionParameterKeys(
		long sellerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _transactionParameterLocalService.getSellerTransactionParameterKeys(sellerId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public TransactionParameterLocalService getWrappedTransactionParameterLocalService() {
		return _transactionParameterLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedTransactionParameterLocalService(
		TransactionParameterLocalService transactionParameterLocalService) {
		_transactionParameterLocalService = transactionParameterLocalService;
	}

	public TransactionParameterLocalService getWrappedService() {
		return _transactionParameterLocalService;
	}

	public void setWrappedService(
		TransactionParameterLocalService transactionParameterLocalService) {
		_transactionParameterLocalService = transactionParameterLocalService;
	}

	private TransactionParameterLocalService _transactionParameterLocalService;
}