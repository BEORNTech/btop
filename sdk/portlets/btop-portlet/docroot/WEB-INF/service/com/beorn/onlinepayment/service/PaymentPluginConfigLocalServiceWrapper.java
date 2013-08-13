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
 * This class is a wrapper for {@link PaymentPluginConfigLocalService}.
 * </p>
 *
 * @author    Sebastien Meunier
 * @see       PaymentPluginConfigLocalService
 * @generated
 */
public class PaymentPluginConfigLocalServiceWrapper
	implements PaymentPluginConfigLocalService,
		ServiceWrapper<PaymentPluginConfigLocalService> {
	public PaymentPluginConfigLocalServiceWrapper(
		PaymentPluginConfigLocalService paymentPluginConfigLocalService) {
		_paymentPluginConfigLocalService = paymentPluginConfigLocalService;
	}

	/**
	* Adds the payment plugin config to the database. Also notifies the appropriate model listeners.
	*
	* @param paymentPluginConfig the payment plugin config
	* @return the payment plugin config that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentPluginConfig addPaymentPluginConfig(
		com.beorn.onlinepayment.model.PaymentPluginConfig paymentPluginConfig)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginConfigLocalService.addPaymentPluginConfig(paymentPluginConfig);
	}

	/**
	* Creates a new payment plugin config with the primary key. Does not add the payment plugin config to the database.
	*
	* @param paymentPluginConfigId the primary key for the new payment plugin config
	* @return the new payment plugin config
	*/
	public com.beorn.onlinepayment.model.PaymentPluginConfig createPaymentPluginConfig(
		long paymentPluginConfigId) {
		return _paymentPluginConfigLocalService.createPaymentPluginConfig(paymentPluginConfigId);
	}

	/**
	* Deletes the payment plugin config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param paymentPluginConfigId the primary key of the payment plugin config
	* @return the payment plugin config that was removed
	* @throws PortalException if a payment plugin config with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentPluginConfig deletePaymentPluginConfig(
		long paymentPluginConfigId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginConfigLocalService.deletePaymentPluginConfig(paymentPluginConfigId);
	}

	/**
	* Deletes the payment plugin config from the database. Also notifies the appropriate model listeners.
	*
	* @param paymentPluginConfig the payment plugin config
	* @return the payment plugin config that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentPluginConfig deletePaymentPluginConfig(
		com.beorn.onlinepayment.model.PaymentPluginConfig paymentPluginConfig)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginConfigLocalService.deletePaymentPluginConfig(paymentPluginConfig);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _paymentPluginConfigLocalService.dynamicQuery();
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
		return _paymentPluginConfigLocalService.dynamicQuery(dynamicQuery);
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
		return _paymentPluginConfigLocalService.dynamicQuery(dynamicQuery,
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
		return _paymentPluginConfigLocalService.dynamicQuery(dynamicQuery,
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
		return _paymentPluginConfigLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.beorn.onlinepayment.model.PaymentPluginConfig fetchPaymentPluginConfig(
		long paymentPluginConfigId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginConfigLocalService.fetchPaymentPluginConfig(paymentPluginConfigId);
	}

	/**
	* Returns the payment plugin config with the primary key.
	*
	* @param paymentPluginConfigId the primary key of the payment plugin config
	* @return the payment plugin config
	* @throws PortalException if a payment plugin config with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentPluginConfig getPaymentPluginConfig(
		long paymentPluginConfigId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginConfigLocalService.getPaymentPluginConfig(paymentPluginConfigId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginConfigLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the payment plugin config with the UUID in the group.
	*
	* @param uuid the UUID of payment plugin config
	* @param groupId the group id of the payment plugin config
	* @return the payment plugin config
	* @throws PortalException if a payment plugin config with the UUID in the group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentPluginConfig getPaymentPluginConfigByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginConfigLocalService.getPaymentPluginConfigByUuidAndGroupId(uuid,
			groupId);
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
	public java.util.List<com.beorn.onlinepayment.model.PaymentPluginConfig> getPaymentPluginConfigs(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginConfigLocalService.getPaymentPluginConfigs(start,
			end);
	}

	/**
	* Returns the number of payment plugin configs.
	*
	* @return the number of payment plugin configs
	* @throws SystemException if a system exception occurred
	*/
	public int getPaymentPluginConfigsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginConfigLocalService.getPaymentPluginConfigsCount();
	}

	/**
	* Updates the payment plugin config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param paymentPluginConfig the payment plugin config
	* @return the payment plugin config that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentPluginConfig updatePaymentPluginConfig(
		com.beorn.onlinepayment.model.PaymentPluginConfig paymentPluginConfig)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginConfigLocalService.updatePaymentPluginConfig(paymentPluginConfig);
	}

	/**
	* Updates the payment plugin config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param paymentPluginConfig the payment plugin config
	* @param merge whether to merge the payment plugin config with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the payment plugin config that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentPluginConfig updatePaymentPluginConfig(
		com.beorn.onlinepayment.model.PaymentPluginConfig paymentPluginConfig,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginConfigLocalService.updatePaymentPluginConfig(paymentPluginConfig,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _paymentPluginConfigLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_paymentPluginConfigLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _paymentPluginConfigLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	public com.beorn.onlinepayment.model.PaymentPluginConfig addPaymentPluginConfig(
		long userId, long sellerId, long paymentPluginId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginConfigLocalService.addPaymentPluginConfig(userId,
			sellerId, paymentPluginId, serviceContext);
	}

	public com.beorn.onlinepayment.model.PaymentPluginConfig addPaymentPluginConfig(
		long userId, long sellerId, long paymentPluginId,
		java.lang.String config, boolean configured,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginConfigLocalService.addPaymentPluginConfig(userId,
			sellerId, paymentPluginId, config, configured, serviceContext);
	}

	public java.util.List<com.beorn.onlinepayment.model.PaymentPluginConfig> getSellerPaymentPluginConfigs(
		long sellerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginConfigLocalService.getSellerPaymentPluginConfigs(sellerId,
			start, end, orderByComparator);
	}

	public java.util.List<com.beorn.onlinepayment.model.PaymentPluginConfig> getSellerPaymentPluginConfigs(
		long sellerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginConfigLocalService.getSellerPaymentPluginConfigs(sellerId);
	}

	public int getSellerPaymentPluginConfigsCount(long sellerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginConfigLocalService.getSellerPaymentPluginConfigsCount(sellerId);
	}

	public java.util.List<com.beorn.onlinepayment.model.PaymentPluginConfig> getPaymentPluginPaymentPluginConfigs(
		long paymentPluginId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginConfigLocalService.getPaymentPluginPaymentPluginConfigs(paymentPluginId);
	}

	public int getPaymentPluginPaymentPluginConfigsCount(long paymentPluginId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginConfigLocalService.getPaymentPluginPaymentPluginConfigsCount(paymentPluginId);
	}

	public boolean hasPaymentPluginConfigBySellerIdAndPaymentPluginId(
		long sellerId, long paymentPluginId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginConfigLocalService.hasPaymentPluginConfigBySellerIdAndPaymentPluginId(sellerId,
			paymentPluginId);
	}

	public com.beorn.onlinepayment.model.PaymentPluginConfig getPaymentPluginConfigBySellerIdAndPaymentPluginId(
		long sellerId, long paymentPluginId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginConfigLocalService.getPaymentPluginConfigBySellerIdAndPaymentPluginId(sellerId,
			paymentPluginId);
	}

	public com.beorn.onlinepayment.model.PaymentPluginConfig getSellerDefaultPaymentPluginConfig(
		long sellerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginConfigLocalService.getSellerDefaultPaymentPluginConfig(sellerId);
	}

	public com.beorn.onlinepayment.model.PaymentPluginConfig updatePaymentPluginConfig(
		long paymentPluginConfigId, long sellerId, long paymentPluginId,
		java.lang.String config, boolean configured,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginConfigLocalService.updatePaymentPluginConfig(paymentPluginConfigId,
			sellerId, paymentPluginId, config, configured, serviceContext);
	}

	public void updateDefaultPluginConfig(long sellerId,
		long defaultPaymentPluginConfigId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_paymentPluginConfigLocalService.updateDefaultPluginConfig(sellerId,
			defaultPaymentPluginConfigId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public PaymentPluginConfigLocalService getWrappedPaymentPluginConfigLocalService() {
		return _paymentPluginConfigLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedPaymentPluginConfigLocalService(
		PaymentPluginConfigLocalService paymentPluginConfigLocalService) {
		_paymentPluginConfigLocalService = paymentPluginConfigLocalService;
	}

	public PaymentPluginConfigLocalService getWrappedService() {
		return _paymentPluginConfigLocalService;
	}

	public void setWrappedService(
		PaymentPluginConfigLocalService paymentPluginConfigLocalService) {
		_paymentPluginConfigLocalService = paymentPluginConfigLocalService;
	}

	private PaymentPluginConfigLocalService _paymentPluginConfigLocalService;
}