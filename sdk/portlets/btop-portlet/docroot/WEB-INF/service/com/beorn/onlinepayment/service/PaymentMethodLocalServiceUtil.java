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
 * The utility for the payment method local service. This utility wraps {@link com.beorn.onlinepayment.service.impl.PaymentMethodLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Sebastien Meunier
 * @see PaymentMethodLocalService
 * @see com.beorn.onlinepayment.service.base.PaymentMethodLocalServiceBaseImpl
 * @see com.beorn.onlinepayment.service.impl.PaymentMethodLocalServiceImpl
 * @generated
 */
public class PaymentMethodLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.beorn.onlinepayment.service.impl.PaymentMethodLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the payment method to the database. Also notifies the appropriate model listeners.
	*
	* @param paymentMethod the payment method
	* @return the payment method that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentMethod addPaymentMethod(
		com.beorn.onlinepayment.model.PaymentMethod paymentMethod)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addPaymentMethod(paymentMethod);
	}

	/**
	* Creates a new payment method with the primary key. Does not add the payment method to the database.
	*
	* @param paymentMethodId the primary key for the new payment method
	* @return the new payment method
	*/
	public static com.beorn.onlinepayment.model.PaymentMethod createPaymentMethod(
		long paymentMethodId) {
		return getService().createPaymentMethod(paymentMethodId);
	}

	/**
	* Deletes the payment method with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param paymentMethodId the primary key of the payment method
	* @return the payment method that was removed
	* @throws PortalException if a payment method with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentMethod deletePaymentMethod(
		long paymentMethodId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deletePaymentMethod(paymentMethodId);
	}

	/**
	* Deletes the payment method from the database. Also notifies the appropriate model listeners.
	*
	* @param paymentMethod the payment method
	* @return the payment method that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentMethod deletePaymentMethod(
		com.beorn.onlinepayment.model.PaymentMethod paymentMethod)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deletePaymentMethod(paymentMethod);
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

	public static com.beorn.onlinepayment.model.PaymentMethod fetchPaymentMethod(
		long paymentMethodId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchPaymentMethod(paymentMethodId);
	}

	/**
	* Returns the payment method with the primary key.
	*
	* @param paymentMethodId the primary key of the payment method
	* @return the payment method
	* @throws PortalException if a payment method with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentMethod getPaymentMethod(
		long paymentMethodId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPaymentMethod(paymentMethodId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
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
	public static com.beorn.onlinepayment.model.PaymentMethod getPaymentMethodByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPaymentMethodByUuidAndGroupId(uuid, groupId);
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
	public static java.util.List<com.beorn.onlinepayment.model.PaymentMethod> getPaymentMethods(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getPaymentMethods(start, end);
	}

	/**
	* Returns the number of payment methods.
	*
	* @return the number of payment methods
	* @throws SystemException if a system exception occurred
	*/
	public static int getPaymentMethodsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getPaymentMethodsCount();
	}

	/**
	* Updates the payment method in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param paymentMethod the payment method
	* @return the payment method that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentMethod updatePaymentMethod(
		com.beorn.onlinepayment.model.PaymentMethod paymentMethod)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updatePaymentMethod(paymentMethod);
	}

	/**
	* Updates the payment method in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param paymentMethod the payment method
	* @param merge whether to merge the payment method with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the payment method that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.PaymentMethod updatePaymentMethod(
		com.beorn.onlinepayment.model.PaymentMethod paymentMethod, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updatePaymentMethod(paymentMethod, merge);
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

	public static com.beorn.onlinepayment.model.PaymentMethod addPaymentMethod(
		long userId, java.lang.String key,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addPaymentMethod(userId, key, nameMap, serviceContext);
	}

	public static com.beorn.onlinepayment.model.PaymentMethod getPaymentMethodByKey(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPaymentMethodByKey(key);
	}

	public static java.util.List<com.beorn.onlinepayment.model.PaymentMethod> getSellerPaymentMethods(
		long sellerId, java.lang.Boolean configured, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getSellerPaymentMethods(sellerId, configured, start, end,
			orderByComparator);
	}

	public static int getSellerPaymentMethodsCount(long sellerId,
		java.lang.Boolean configured, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getSellerPaymentMethodsCount(sellerId, configured, start,
			end, orderByComparator);
	}

	public static java.util.List<com.beorn.onlinepayment.model.PaymentMethod> getTransactionAvailablePaymentMethods(
		com.beorn.onlinepayment.model.Transaction transaction,
		java.lang.String countryCode)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getTransactionAvailablePaymentMethods(transaction,
			countryCode);
	}

	public static java.util.List<com.beorn.onlinepayment.model.PaymentMethod> getPaymentPluginPaymentMethods(
		long paymentPluginId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getPaymentPluginPaymentMethods(paymentPluginId, start, end,
			orderByComparator);
	}

	public static int getPaymentPluginPaymentMethodsCount(
		long paymentPluginId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getPaymentPluginPaymentMethodsCount(paymentPluginId, start,
			end, orderByComparator);
	}

	public static java.util.List<com.beorn.onlinepayment.model.PaymentMethod> search(
		long companyId, java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(companyId, keywords, start, end, orderByComparator);
	}

	public static int searchCount(long companyId, java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().searchCount(companyId, keywords);
	}

	public static com.beorn.onlinepayment.model.PaymentMethod updatePaymentMethod(
		long paymentMethodId, java.lang.String key,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updatePaymentMethod(paymentMethodId, key, nameMap,
			serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static PaymentMethodLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					PaymentMethodLocalService.class.getName());

			if (invokableLocalService instanceof PaymentMethodLocalService) {
				_service = (PaymentMethodLocalService)invokableLocalService;
			}
			else {
				_service = new PaymentMethodLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(PaymentMethodLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(PaymentMethodLocalService service) {
	}

	private static PaymentMethodLocalService _service;
}