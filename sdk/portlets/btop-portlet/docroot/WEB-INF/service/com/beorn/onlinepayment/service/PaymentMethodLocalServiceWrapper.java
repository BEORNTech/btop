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
 * This class is a wrapper for {@link PaymentMethodLocalService}.
 * </p>
 *
 * @author    Sebastien Meunier
 * @see       PaymentMethodLocalService
 * @generated
 */
public class PaymentMethodLocalServiceWrapper
	implements PaymentMethodLocalService,
		ServiceWrapper<PaymentMethodLocalService> {
	public PaymentMethodLocalServiceWrapper(
		PaymentMethodLocalService paymentMethodLocalService) {
		_paymentMethodLocalService = paymentMethodLocalService;
	}

	/**
	* Adds the payment method to the database. Also notifies the appropriate model listeners.
	*
	* @param paymentMethod the payment method
	* @return the payment method that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentMethod addPaymentMethod(
		com.beorn.onlinepayment.model.PaymentMethod paymentMethod)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _paymentMethodLocalService.addPaymentMethod(paymentMethod);
	}

	/**
	* Creates a new payment method with the primary key. Does not add the payment method to the database.
	*
	* @param paymentMethodId the primary key for the new payment method
	* @return the new payment method
	*/
	public com.beorn.onlinepayment.model.PaymentMethod createPaymentMethod(
		long paymentMethodId) {
		return _paymentMethodLocalService.createPaymentMethod(paymentMethodId);
	}

	/**
	* Deletes the payment method with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param paymentMethodId the primary key of the payment method
	* @return the payment method that was removed
	* @throws PortalException if a payment method with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentMethod deletePaymentMethod(
		long paymentMethodId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentMethodLocalService.deletePaymentMethod(paymentMethodId);
	}

	/**
	* Deletes the payment method from the database. Also notifies the appropriate model listeners.
	*
	* @param paymentMethod the payment method
	* @return the payment method that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentMethod deletePaymentMethod(
		com.beorn.onlinepayment.model.PaymentMethod paymentMethod)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentMethodLocalService.deletePaymentMethod(paymentMethod);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _paymentMethodLocalService.dynamicQuery();
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
		return _paymentMethodLocalService.dynamicQuery(dynamicQuery);
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
		return _paymentMethodLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _paymentMethodLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _paymentMethodLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.beorn.onlinepayment.model.PaymentMethod fetchPaymentMethod(
		long paymentMethodId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _paymentMethodLocalService.fetchPaymentMethod(paymentMethodId);
	}

	/**
	* Returns the payment method with the primary key.
	*
	* @param paymentMethodId the primary key of the payment method
	* @return the payment method
	* @throws PortalException if a payment method with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentMethod getPaymentMethod(
		long paymentMethodId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentMethodLocalService.getPaymentMethod(paymentMethodId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentMethodLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the payment method with the UUID in the group.
	*
	* @param uuid the UUID of payment method
	* @param groupId the group id of the payment method
	* @return the payment method
	* @throws PortalException if a payment method with the UUID in the group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentMethod getPaymentMethodByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentMethodLocalService.getPaymentMethodByUuidAndGroupId(uuid,
			groupId);
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
	public java.util.List<com.beorn.onlinepayment.model.PaymentMethod> getPaymentMethods(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _paymentMethodLocalService.getPaymentMethods(start, end);
	}

	/**
	* Returns the number of payment methods.
	*
	* @return the number of payment methods
	* @throws SystemException if a system exception occurred
	*/
	public int getPaymentMethodsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _paymentMethodLocalService.getPaymentMethodsCount();
	}

	/**
	* Updates the payment method in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param paymentMethod the payment method
	* @return the payment method that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentMethod updatePaymentMethod(
		com.beorn.onlinepayment.model.PaymentMethod paymentMethod)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _paymentMethodLocalService.updatePaymentMethod(paymentMethod);
	}

	/**
	* Updates the payment method in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param paymentMethod the payment method
	* @param merge whether to merge the payment method with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the payment method that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentMethod updatePaymentMethod(
		com.beorn.onlinepayment.model.PaymentMethod paymentMethod, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _paymentMethodLocalService.updatePaymentMethod(paymentMethod,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _paymentMethodLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_paymentMethodLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _paymentMethodLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.beorn.onlinepayment.model.PaymentMethod addPaymentMethod(
		long userId, java.lang.String key,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentMethodLocalService.addPaymentMethod(userId, key,
			nameMap, serviceContext);
	}

	public com.beorn.onlinepayment.model.PaymentMethod getPaymentMethodByKey(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentMethodLocalService.getPaymentMethodByKey(key);
	}

	public java.util.List<com.beorn.onlinepayment.model.PaymentMethod> getSellerPaymentMethods(
		long sellerId, java.lang.Boolean configured, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _paymentMethodLocalService.getSellerPaymentMethods(sellerId,
			configured, start, end, orderByComparator);
	}

	public int getSellerPaymentMethodsCount(long sellerId,
		java.lang.Boolean configured, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _paymentMethodLocalService.getSellerPaymentMethodsCount(sellerId,
			configured, start, end, orderByComparator);
	}

	public java.util.List<com.beorn.onlinepayment.model.PaymentMethod> getTransactionAvailablePaymentMethods(
		com.beorn.onlinepayment.model.Transaction transaction,
		java.lang.String countryCode)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentMethodLocalService.getTransactionAvailablePaymentMethods(transaction,
			countryCode);
	}

	public java.util.List<com.beorn.onlinepayment.model.PaymentMethod> getPaymentPluginPaymentMethods(
		long paymentPluginId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _paymentMethodLocalService.getPaymentPluginPaymentMethods(paymentPluginId,
			start, end, orderByComparator);
	}

	public int getPaymentPluginPaymentMethodsCount(long paymentPluginId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _paymentMethodLocalService.getPaymentPluginPaymentMethodsCount(paymentPluginId,
			start, end, orderByComparator);
	}

	public java.util.List<com.beorn.onlinepayment.model.PaymentMethod> search(
		long companyId, java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _paymentMethodLocalService.search(companyId, keywords, start,
			end, orderByComparator);
	}

	public int searchCount(long companyId, java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _paymentMethodLocalService.searchCount(companyId, keywords);
	}

	public com.beorn.onlinepayment.model.PaymentMethod updatePaymentMethod(
		long paymentMethodId, java.lang.String key,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentMethodLocalService.updatePaymentMethod(paymentMethodId,
			key, nameMap, serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public PaymentMethodLocalService getWrappedPaymentMethodLocalService() {
		return _paymentMethodLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedPaymentMethodLocalService(
		PaymentMethodLocalService paymentMethodLocalService) {
		_paymentMethodLocalService = paymentMethodLocalService;
	}

	public PaymentMethodLocalService getWrappedService() {
		return _paymentMethodLocalService;
	}

	public void setWrappedService(
		PaymentMethodLocalService paymentMethodLocalService) {
		_paymentMethodLocalService = paymentMethodLocalService;
	}

	private PaymentMethodLocalService _paymentMethodLocalService;
}