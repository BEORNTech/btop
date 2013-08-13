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
 * The utility for the payment plugin config local service. This utility wraps {@link com.beorn.onlinepayment.service.impl.PaymentPluginConfigLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Sebastien Meunier
 * @see PaymentPluginConfigLocalService
 * @see com.beorn.onlinepayment.service.base.PaymentPluginConfigLocalServiceBaseImpl
 * @see com.beorn.onlinepayment.service.impl.PaymentPluginConfigLocalServiceImpl
 * @generated
 */
public class PaymentPluginConfigLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.beorn.onlinepayment.service.impl.PaymentPluginConfigLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the payment plugin config to the database. Also notifies the appropriate model listeners.
	*
	* @param paymentPluginConfig the payment plugin config
	* @return the payment plugin config that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig addPaymentPluginConfig(
		com.beorn.onlinepayment.model.PaymentPluginConfig paymentPluginConfig)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addPaymentPluginConfig(paymentPluginConfig);
	}

	/**
	* Creates a new payment plugin config with the primary key. Does not add the payment plugin config to the database.
	*
	* @param paymentPluginConfigId the primary key for the new payment plugin config
	* @return the new payment plugin config
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig createPaymentPluginConfig(
		long paymentPluginConfigId) {
		return getService().createPaymentPluginConfig(paymentPluginConfigId);
	}

	/**
	* Deletes the payment plugin config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param paymentPluginConfigId the primary key of the payment plugin config
	* @return the payment plugin config that was removed
	* @throws PortalException if a payment plugin config with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig deletePaymentPluginConfig(
		long paymentPluginConfigId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deletePaymentPluginConfig(paymentPluginConfigId);
	}

	/**
	* Deletes the payment plugin config from the database. Also notifies the appropriate model listeners.
	*
	* @param paymentPluginConfig the payment plugin config
	* @return the payment plugin config that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig deletePaymentPluginConfig(
		com.beorn.onlinepayment.model.PaymentPluginConfig paymentPluginConfig)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deletePaymentPluginConfig(paymentPluginConfig);
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

	public static com.beorn.onlinepayment.model.PaymentPluginConfig fetchPaymentPluginConfig(
		long paymentPluginConfigId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchPaymentPluginConfig(paymentPluginConfigId);
	}

	/**
	* Returns the payment plugin config with the primary key.
	*
	* @param paymentPluginConfigId the primary key of the payment plugin config
	* @return the payment plugin config
	* @throws PortalException if a payment plugin config with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig getPaymentPluginConfig(
		long paymentPluginConfigId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPaymentPluginConfig(paymentPluginConfigId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
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
	public static com.beorn.onlinepayment.model.PaymentPluginConfig getPaymentPluginConfigByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPaymentPluginConfigByUuidAndGroupId(uuid, groupId);
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
	public static java.util.List<com.beorn.onlinepayment.model.PaymentPluginConfig> getPaymentPluginConfigs(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getPaymentPluginConfigs(start, end);
	}

	/**
	* Returns the number of payment plugin configs.
	*
	* @return the number of payment plugin configs
	* @throws SystemException if a system exception occurred
	*/
	public static int getPaymentPluginConfigsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getPaymentPluginConfigsCount();
	}

	/**
	* Updates the payment plugin config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param paymentPluginConfig the payment plugin config
	* @return the payment plugin config that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig updatePaymentPluginConfig(
		com.beorn.onlinepayment.model.PaymentPluginConfig paymentPluginConfig)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updatePaymentPluginConfig(paymentPluginConfig);
	}

	/**
	* Updates the payment plugin config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param paymentPluginConfig the payment plugin config
	* @param merge whether to merge the payment plugin config with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the payment plugin config that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentPluginConfig updatePaymentPluginConfig(
		com.beorn.onlinepayment.model.PaymentPluginConfig paymentPluginConfig,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updatePaymentPluginConfig(paymentPluginConfig, merge);
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

	public static com.beorn.onlinepayment.model.PaymentPluginConfig addPaymentPluginConfig(
		long userId, long sellerId, long paymentPluginId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addPaymentPluginConfig(userId, sellerId, paymentPluginId,
			serviceContext);
	}

	public static com.beorn.onlinepayment.model.PaymentPluginConfig addPaymentPluginConfig(
		long userId, long sellerId, long paymentPluginId,
		java.lang.String config, boolean configured,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addPaymentPluginConfig(userId, sellerId, paymentPluginId,
			config, configured, serviceContext);
	}

	public static java.util.List<com.beorn.onlinepayment.model.PaymentPluginConfig> getSellerPaymentPluginConfigs(
		long sellerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getSellerPaymentPluginConfigs(sellerId, start, end,
			orderByComparator);
	}

	public static java.util.List<com.beorn.onlinepayment.model.PaymentPluginConfig> getSellerPaymentPluginConfigs(
		long sellerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getSellerPaymentPluginConfigs(sellerId);
	}

	public static int getSellerPaymentPluginConfigsCount(long sellerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getSellerPaymentPluginConfigsCount(sellerId);
	}

	public static java.util.List<com.beorn.onlinepayment.model.PaymentPluginConfig> getPaymentPluginPaymentPluginConfigs(
		long paymentPluginId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPaymentPluginPaymentPluginConfigs(paymentPluginId);
	}

	public static int getPaymentPluginPaymentPluginConfigsCount(
		long paymentPluginId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getPaymentPluginPaymentPluginConfigsCount(paymentPluginId);
	}

	public static boolean hasPaymentPluginConfigBySellerIdAndPaymentPluginId(
		long sellerId, long paymentPluginId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .hasPaymentPluginConfigBySellerIdAndPaymentPluginId(sellerId,
			paymentPluginId);
	}

	public static com.beorn.onlinepayment.model.PaymentPluginConfig getPaymentPluginConfigBySellerIdAndPaymentPluginId(
		long sellerId, long paymentPluginId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getPaymentPluginConfigBySellerIdAndPaymentPluginId(sellerId,
			paymentPluginId);
	}

	public static com.beorn.onlinepayment.model.PaymentPluginConfig getSellerDefaultPaymentPluginConfig(
		long sellerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getSellerDefaultPaymentPluginConfig(sellerId);
	}

	public static com.beorn.onlinepayment.model.PaymentPluginConfig updatePaymentPluginConfig(
		long paymentPluginConfigId, long sellerId, long paymentPluginId,
		java.lang.String config, boolean configured,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updatePaymentPluginConfig(paymentPluginConfigId, sellerId,
			paymentPluginId, config, configured, serviceContext);
	}

	public static void updateDefaultPluginConfig(long sellerId,
		long defaultPaymentPluginConfigId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.updateDefaultPluginConfig(sellerId, defaultPaymentPluginConfigId);
	}

	public static void clearService() {
		_service = null;
	}

	public static PaymentPluginConfigLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					PaymentPluginConfigLocalService.class.getName());

			if (invokableLocalService instanceof PaymentPluginConfigLocalService) {
				_service = (PaymentPluginConfigLocalService)invokableLocalService;
			}
			else {
				_service = new PaymentPluginConfigLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(PaymentPluginConfigLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(PaymentPluginConfigLocalService service) {
	}

	private static PaymentPluginConfigLocalService _service;
}