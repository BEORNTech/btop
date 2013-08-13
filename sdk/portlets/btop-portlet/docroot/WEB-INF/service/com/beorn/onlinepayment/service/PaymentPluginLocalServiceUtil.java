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
 * The utility for the payment plugin local service. This utility wraps {@link com.beorn.onlinepayment.service.impl.PaymentPluginLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Sebastien Meunier
 * @see PaymentPluginLocalService
 * @see com.beorn.onlinepayment.service.base.PaymentPluginLocalServiceBaseImpl
 * @see com.beorn.onlinepayment.service.impl.PaymentPluginLocalServiceImpl
 * @generated
 */
public class PaymentPluginLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.beorn.onlinepayment.service.impl.PaymentPluginLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the payment plugin to the database. Also notifies the appropriate model listeners.
	*
	* @param paymentPlugin the payment plugin
	* @return the payment plugin that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPlugin addPaymentPlugin(
		com.beorn.onlinepayment.model.PaymentPlugin paymentPlugin)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addPaymentPlugin(paymentPlugin);
	}

	/**
	* Creates a new payment plugin with the primary key. Does not add the payment plugin to the database.
	*
	* @param paymentPluginId the primary key for the new payment plugin
	* @return the new payment plugin
	*/
	public static com.beorn.onlinepayment.model.PaymentPlugin createPaymentPlugin(
		long paymentPluginId) {
		return getService().createPaymentPlugin(paymentPluginId);
	}

	/**
	* Deletes the payment plugin with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param paymentPluginId the primary key of the payment plugin
	* @return the payment plugin that was removed
	* @throws PortalException if a payment plugin with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPlugin deletePaymentPlugin(
		long paymentPluginId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deletePaymentPlugin(paymentPluginId);
	}

	/**
	* Deletes the payment plugin from the database. Also notifies the appropriate model listeners.
	*
	* @param paymentPlugin the payment plugin
	* @return the payment plugin that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPlugin deletePaymentPlugin(
		com.beorn.onlinepayment.model.PaymentPlugin paymentPlugin)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deletePaymentPlugin(paymentPlugin);
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

	public static com.beorn.onlinepayment.model.PaymentPlugin fetchPaymentPlugin(
		long paymentPluginId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchPaymentPlugin(paymentPluginId);
	}

	/**
	* Returns the payment plugin with the primary key.
	*
	* @param paymentPluginId the primary key of the payment plugin
	* @return the payment plugin
	* @throws PortalException if a payment plugin with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPlugin getPaymentPlugin(
		long paymentPluginId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPaymentPlugin(paymentPluginId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
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
	public static com.beorn.onlinepayment.model.PaymentPlugin getPaymentPluginByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPaymentPluginByUuidAndGroupId(uuid, groupId);
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
	public static java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> getPaymentPlugins(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getPaymentPlugins(start, end);
	}

	/**
	* Returns the number of payment plugins.
	*
	* @return the number of payment plugins
	* @throws SystemException if a system exception occurred
	*/
	public static int getPaymentPluginsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getPaymentPluginsCount();
	}

	/**
	* Updates the payment plugin in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param paymentPlugin the payment plugin
	* @return the payment plugin that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPlugin updatePaymentPlugin(
		com.beorn.onlinepayment.model.PaymentPlugin paymentPlugin)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updatePaymentPlugin(paymentPlugin);
	}

	/**
	* Updates the payment plugin in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param paymentPlugin the payment plugin
	* @param merge whether to merge the payment plugin with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the payment plugin that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPlugin updatePaymentPlugin(
		com.beorn.onlinepayment.model.PaymentPlugin paymentPlugin, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updatePaymentPlugin(paymentPlugin, merge);
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

	public static com.beorn.onlinepayment.model.PaymentPlugin addPaymentPlugin(
		long userId, java.lang.String applicationId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.lang.String pluginConfigParameters,
		java.lang.String sellerConfigParameters,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addPaymentPlugin(userId, applicationId, nameMap,
			pluginConfigParameters, sellerConfigParameters, serviceContext);
	}

	public static java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> getPaymentPlugins(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPaymentPlugins(start, end, orderByComparator);
	}

	public static java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> getSellerPaymentPlugins(
		long sellerId, java.lang.Boolean configured, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getSellerPaymentPlugins(sellerId, configured, start, end,
			orderByComparator);
	}

	public static int getSellerPaymentPluginsCount(long sellerId,
		java.lang.Boolean configured)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getSellerPaymentPluginsCount(sellerId, configured);
	}

	public static java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> getPaymentMethodPaymentPlugins(
		long paymentMethodId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getPaymentMethodPaymentPlugins(paymentMethodId, start, end,
			orderByComparator);
	}

	public static int getPaymentMethodPaymentPluginsCount(long paymentMethodId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPaymentMethodPaymentPluginsCount(paymentMethodId);
	}

	public static com.beorn.onlinepayment.model.PaymentPlugin getPaymentPluginByApplicationId(
		java.lang.String applicationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPaymentPluginByApplicationId(applicationId);
	}

	public static java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> getAvailablePaymentPlugins(
		long transactionId, long paymentMethodId, java.lang.String countryCode)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAvailablePaymentPlugins(transactionId, paymentMethodId,
			countryCode);
	}

	public static java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> getPaymentPluginsByTransactionAndPaymentMethod(
		long transactionId, long paymentMethodId, java.lang.Boolean configured,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getPaymentPluginsByTransactionAndPaymentMethod(transactionId,
			paymentMethodId, configured, start, end, obc);
	}

	public static int getPaymentPluginsByTransactionAndPaymentMethodCount(
		long transactionId, long paymentMethodId, java.lang.Boolean configured)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getPaymentPluginsByTransactionAndPaymentMethodCount(transactionId,
			paymentMethodId, configured);
	}

	public static java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> search(
		long companyId, java.lang.String keywords, java.lang.Boolean active,
		java.lang.Long paymentMethodId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(companyId, keywords, active, paymentMethodId, start,
			end, orderByComparator);
	}

	public static int searchCount(long companyId, java.lang.String keywords,
		java.lang.Boolean active, java.lang.Long paymentMethodId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .searchCount(companyId, keywords, active, paymentMethodId);
	}

	public static com.beorn.onlinepayment.model.PaymentPlugin updatePaymentPlugin(
		long paymentPluginId, java.lang.String applicationId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.lang.String pluginConfigParameters,
		java.lang.String sellerConfigParameters, java.lang.String pluginConfig,
		boolean configured,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updatePaymentPlugin(paymentPluginId, applicationId,
			nameMap, pluginConfigParameters, sellerConfigParameters,
			pluginConfig, configured, serviceContext);
	}

	public static void updatePaymentPluginPaymentMethods(long paymentPluginId,
		java.util.List<com.beorn.onlinepayment.model.PaymentMethod> paymentMethods)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.updatePaymentPluginPaymentMethods(paymentPluginId, paymentMethods);
	}

	public static void clearService() {
		_service = null;
	}

	public static PaymentPluginLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					PaymentPluginLocalService.class.getName());

			if (invokableLocalService instanceof PaymentPluginLocalService) {
				_service = (PaymentPluginLocalService)invokableLocalService;
			}
			else {
				_service = new PaymentPluginLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(PaymentPluginLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(PaymentPluginLocalService service) {
	}

	private static PaymentPluginLocalService _service;
}