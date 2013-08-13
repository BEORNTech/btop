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
 * The utility for the order transaction local service. This utility wraps {@link com.beorn.onlinepayment.service.impl.OrderTransactionLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Sebastien Meunier
 * @see OrderTransactionLocalService
 * @see com.beorn.onlinepayment.service.base.OrderTransactionLocalServiceBaseImpl
 * @see com.beorn.onlinepayment.service.impl.OrderTransactionLocalServiceImpl
 * @generated
 */
public class OrderTransactionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.beorn.onlinepayment.service.impl.OrderTransactionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the order transaction to the database. Also notifies the appropriate model listeners.
	*
	* @param orderTransaction the order transaction
	* @return the order transaction that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.OrderTransaction addOrderTransaction(
		com.beorn.onlinepayment.model.OrderTransaction orderTransaction)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addOrderTransaction(orderTransaction);
	}

	/**
	* Creates a new order transaction with the primary key. Does not add the order transaction to the database.
	*
	* @param orderId the primary key for the new order transaction
	* @return the new order transaction
	*/
	public static com.beorn.onlinepayment.model.OrderTransaction createOrderTransaction(
		long orderId) {
		return getService().createOrderTransaction(orderId);
	}

	/**
	* Deletes the order transaction with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param orderId the primary key of the order transaction
	* @return the order transaction that was removed
	* @throws PortalException if a order transaction with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.OrderTransaction deleteOrderTransaction(
		long orderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteOrderTransaction(orderId);
	}

	/**
	* Deletes the order transaction from the database. Also notifies the appropriate model listeners.
	*
	* @param orderTransaction the order transaction
	* @return the order transaction that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.OrderTransaction deleteOrderTransaction(
		com.beorn.onlinepayment.model.OrderTransaction orderTransaction)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteOrderTransaction(orderTransaction);
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

	public static com.beorn.onlinepayment.model.OrderTransaction fetchOrderTransaction(
		long orderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchOrderTransaction(orderId);
	}

	/**
	* Returns the order transaction with the primary key.
	*
	* @param orderId the primary key of the order transaction
	* @return the order transaction
	* @throws PortalException if a order transaction with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.OrderTransaction getOrderTransaction(
		long orderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getOrderTransaction(orderId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the order transactions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of order transactions
	* @param end the upper bound of the range of order transactions (not inclusive)
	* @return the range of order transactions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.OrderTransaction> getOrderTransactions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getOrderTransactions(start, end);
	}

	/**
	* Returns the number of order transactions.
	*
	* @return the number of order transactions
	* @throws SystemException if a system exception occurred
	*/
	public static int getOrderTransactionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getOrderTransactionsCount();
	}

	/**
	* Updates the order transaction in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param orderTransaction the order transaction
	* @return the order transaction that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.OrderTransaction updateOrderTransaction(
		com.beorn.onlinepayment.model.OrderTransaction orderTransaction)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateOrderTransaction(orderTransaction);
	}

	/**
	* Updates the order transaction in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param orderTransaction the order transaction
	* @param merge whether to merge the order transaction with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the order transaction that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.OrderTransaction updateOrderTransaction(
		com.beorn.onlinepayment.model.OrderTransaction orderTransaction,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateOrderTransaction(orderTransaction, merge);
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

	public static com.beorn.onlinepayment.model.OrderTransaction addOrderTransaction(
		long orderId, long transactionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().addOrderTransaction(orderId, transactionId);
	}

	public static com.beorn.onlinepayment.model.OrderTransaction getOrderTransactionByTransactionId(
		long transactionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getOrderTransactionByTransactionId(transactionId);
	}

	public static void clearService() {
		_service = null;
	}

	public static OrderTransactionLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					OrderTransactionLocalService.class.getName());

			if (invokableLocalService instanceof OrderTransactionLocalService) {
				_service = (OrderTransactionLocalService)invokableLocalService;
			}
			else {
				_service = new OrderTransactionLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(OrderTransactionLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(OrderTransactionLocalService service) {
	}

	private static OrderTransactionLocalService _service;
}