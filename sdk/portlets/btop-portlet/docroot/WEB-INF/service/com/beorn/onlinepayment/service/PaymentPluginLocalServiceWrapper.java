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
 * This class is a wrapper for {@link PaymentPluginLocalService}.
 * </p>
 *
 * @author    Sebastien Meunier
 * @see       PaymentPluginLocalService
 * @generated
 */
public class PaymentPluginLocalServiceWrapper
	implements PaymentPluginLocalService,
		ServiceWrapper<PaymentPluginLocalService> {
	public PaymentPluginLocalServiceWrapper(
		PaymentPluginLocalService paymentPluginLocalService) {
		_paymentPluginLocalService = paymentPluginLocalService;
	}

	/**
	* Adds the payment plugin to the database. Also notifies the appropriate model listeners.
	*
	* @param paymentPlugin the payment plugin
	* @return the payment plugin that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentPlugin addPaymentPlugin(
		com.beorn.onlinepayment.model.PaymentPlugin paymentPlugin)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginLocalService.addPaymentPlugin(paymentPlugin);
	}

	/**
	* Creates a new payment plugin with the primary key. Does not add the payment plugin to the database.
	*
	* @param paymentPluginId the primary key for the new payment plugin
	* @return the new payment plugin
	*/
	public com.beorn.onlinepayment.model.PaymentPlugin createPaymentPlugin(
		long paymentPluginId) {
		return _paymentPluginLocalService.createPaymentPlugin(paymentPluginId);
	}

	/**
	* Deletes the payment plugin with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param paymentPluginId the primary key of the payment plugin
	* @return the payment plugin that was removed
	* @throws PortalException if a payment plugin with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentPlugin deletePaymentPlugin(
		long paymentPluginId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginLocalService.deletePaymentPlugin(paymentPluginId);
	}

	/**
	* Deletes the payment plugin from the database. Also notifies the appropriate model listeners.
	*
	* @param paymentPlugin the payment plugin
	* @return the payment plugin that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentPlugin deletePaymentPlugin(
		com.beorn.onlinepayment.model.PaymentPlugin paymentPlugin)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginLocalService.deletePaymentPlugin(paymentPlugin);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _paymentPluginLocalService.dynamicQuery();
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
		return _paymentPluginLocalService.dynamicQuery(dynamicQuery);
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
		return _paymentPluginLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _paymentPluginLocalService.dynamicQuery(dynamicQuery, start,
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
		return _paymentPluginLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.beorn.onlinepayment.model.PaymentPlugin fetchPaymentPlugin(
		long paymentPluginId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginLocalService.fetchPaymentPlugin(paymentPluginId);
	}

	/**
	* Returns the payment plugin with the primary key.
	*
	* @param paymentPluginId the primary key of the payment plugin
	* @return the payment plugin
	* @throws PortalException if a payment plugin with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentPlugin getPaymentPlugin(
		long paymentPluginId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginLocalService.getPaymentPlugin(paymentPluginId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the payment plugin with the UUID in the group.
	*
	* @param uuid the UUID of payment plugin
	* @param groupId the group id of the payment plugin
	* @return the payment plugin
	* @throws PortalException if a payment plugin with the UUID in the group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentPlugin getPaymentPluginByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginLocalService.getPaymentPluginByUuidAndGroupId(uuid,
			groupId);
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
	public java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> getPaymentPlugins(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginLocalService.getPaymentPlugins(start, end);
	}

	/**
	* Returns the number of payment plugins.
	*
	* @return the number of payment plugins
	* @throws SystemException if a system exception occurred
	*/
	public int getPaymentPluginsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginLocalService.getPaymentPluginsCount();
	}

	/**
	* Updates the payment plugin in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param paymentPlugin the payment plugin
	* @return the payment plugin that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentPlugin updatePaymentPlugin(
		com.beorn.onlinepayment.model.PaymentPlugin paymentPlugin)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginLocalService.updatePaymentPlugin(paymentPlugin);
	}

	/**
	* Updates the payment plugin in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param paymentPlugin the payment plugin
	* @param merge whether to merge the payment plugin with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the payment plugin that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentPlugin updatePaymentPlugin(
		com.beorn.onlinepayment.model.PaymentPlugin paymentPlugin, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginLocalService.updatePaymentPlugin(paymentPlugin,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _paymentPluginLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_paymentPluginLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _paymentPluginLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.beorn.onlinepayment.model.PaymentPlugin addPaymentPlugin(
		long userId, java.lang.String applicationId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.lang.String pluginConfigParameters,
		java.lang.String sellerConfigParameters,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginLocalService.addPaymentPlugin(userId,
			applicationId, nameMap, pluginConfigParameters,
			sellerConfigParameters, serviceContext);
	}

	public java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> getPaymentPlugins(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginLocalService.getPaymentPlugins(start, end,
			orderByComparator);
	}

	public java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> getSellerPaymentPlugins(
		long sellerId, java.lang.Boolean configured, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginLocalService.getSellerPaymentPlugins(sellerId,
			configured, start, end, orderByComparator);
	}

	public int getSellerPaymentPluginsCount(long sellerId,
		java.lang.Boolean configured)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginLocalService.getSellerPaymentPluginsCount(sellerId,
			configured);
	}

	public java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> getPaymentMethodPaymentPlugins(
		long paymentMethodId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginLocalService.getPaymentMethodPaymentPlugins(paymentMethodId,
			start, end, orderByComparator);
	}

	public int getPaymentMethodPaymentPluginsCount(long paymentMethodId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginLocalService.getPaymentMethodPaymentPluginsCount(paymentMethodId);
	}

	public com.beorn.onlinepayment.model.PaymentPlugin getPaymentPluginByApplicationId(
		java.lang.String applicationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginLocalService.getPaymentPluginByApplicationId(applicationId);
	}

	public java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> getAvailablePaymentPlugins(
		long transactionId, long paymentMethodId, java.lang.String countryCode)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginLocalService.getAvailablePaymentPlugins(transactionId,
			paymentMethodId, countryCode);
	}

	public java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> getPaymentPluginsByTransactionAndPaymentMethod(
		long transactionId, long paymentMethodId, java.lang.Boolean configured,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginLocalService.getPaymentPluginsByTransactionAndPaymentMethod(transactionId,
			paymentMethodId, configured, start, end, obc);
	}

	public int getPaymentPluginsByTransactionAndPaymentMethodCount(
		long transactionId, long paymentMethodId, java.lang.Boolean configured)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginLocalService.getPaymentPluginsByTransactionAndPaymentMethodCount(transactionId,
			paymentMethodId, configured);
	}

	public java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> search(
		long companyId, java.lang.String keywords, java.lang.Boolean active,
		java.lang.Long paymentMethodId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginLocalService.search(companyId, keywords, active,
			paymentMethodId, start, end, orderByComparator);
	}

	public int searchCount(long companyId, java.lang.String keywords,
		java.lang.Boolean active, java.lang.Long paymentMethodId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginLocalService.searchCount(companyId, keywords,
			active, paymentMethodId);
	}

	public com.beorn.onlinepayment.model.PaymentPlugin updatePaymentPlugin(
		long paymentPluginId, java.lang.String applicationId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.lang.String pluginConfigParameters,
		java.lang.String sellerConfigParameters, java.lang.String pluginConfig,
		boolean configured,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginLocalService.updatePaymentPlugin(paymentPluginId,
			applicationId, nameMap, pluginConfigParameters,
			sellerConfigParameters, pluginConfig, configured, serviceContext);
	}

	public void updatePaymentPluginPaymentMethods(long paymentPluginId,
		java.util.List<com.beorn.onlinepayment.model.PaymentMethod> paymentMethods)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_paymentPluginLocalService.updatePaymentPluginPaymentMethods(paymentPluginId,
			paymentMethods);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public PaymentPluginLocalService getWrappedPaymentPluginLocalService() {
		return _paymentPluginLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedPaymentPluginLocalService(
		PaymentPluginLocalService paymentPluginLocalService) {
		_paymentPluginLocalService = paymentPluginLocalService;
	}

	public PaymentPluginLocalService getWrappedService() {
		return _paymentPluginLocalService;
	}

	public void setWrappedService(
		PaymentPluginLocalService paymentPluginLocalService) {
		_paymentPluginLocalService = paymentPluginLocalService;
	}

	private PaymentPluginLocalService _paymentPluginLocalService;
}