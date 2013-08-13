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
 * The utility for the transaction log local service. This utility wraps {@link com.beorn.onlinepayment.service.impl.TransactionLogLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Sebastien Meunier
 * @see TransactionLogLocalService
 * @see com.beorn.onlinepayment.service.base.TransactionLogLocalServiceBaseImpl
 * @see com.beorn.onlinepayment.service.impl.TransactionLogLocalServiceImpl
 * @generated
 */
public class TransactionLogLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.beorn.onlinepayment.service.impl.TransactionLogLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the transaction log to the database. Also notifies the appropriate model listeners.
	*
	* @param transactionLog the transaction log
	* @return the transaction log that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.TransactionLog addTransactionLog(
		com.beorn.onlinepayment.model.TransactionLog transactionLog)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addTransactionLog(transactionLog);
	}

	/**
	* Creates a new transaction log with the primary key. Does not add the transaction log to the database.
	*
	* @param transactionLogId the primary key for the new transaction log
	* @return the new transaction log
	*/
	public static com.beorn.onlinepayment.model.TransactionLog createTransactionLog(
		long transactionLogId) {
		return getService().createTransactionLog(transactionLogId);
	}

	/**
	* Deletes the transaction log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param transactionLogId the primary key of the transaction log
	* @return the transaction log that was removed
	* @throws PortalException if a transaction log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.TransactionLog deleteTransactionLog(
		long transactionLogId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTransactionLog(transactionLogId);
	}

	/**
	* Deletes the transaction log from the database. Also notifies the appropriate model listeners.
	*
	* @param transactionLog the transaction log
	* @return the transaction log that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.TransactionLog deleteTransactionLog(
		com.beorn.onlinepayment.model.TransactionLog transactionLog)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTransactionLog(transactionLog);
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

	public static com.beorn.onlinepayment.model.TransactionLog fetchTransactionLog(
		long transactionLogId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchTransactionLog(transactionLogId);
	}

	/**
	* Returns the transaction log with the primary key.
	*
	* @param transactionLogId the primary key of the transaction log
	* @return the transaction log
	* @throws PortalException if a transaction log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.TransactionLog getTransactionLog(
		long transactionLogId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTransactionLog(transactionLogId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the transaction log with the UUID in the group.
	*
	* @param uuid the UUID of transaction log
	* @param groupId the group id of the transaction log
	* @return the transaction log
	* @throws PortalException if a transaction log with the UUID in the group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.TransactionLog getTransactionLogByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTransactionLogByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the transaction logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of transaction logs
	* @param end the upper bound of the range of transaction logs (not inclusive)
	* @return the range of transaction logs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.TransactionLog> getTransactionLogs(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTransactionLogs(start, end);
	}

	/**
	* Returns the number of transaction logs.
	*
	* @return the number of transaction logs
	* @throws SystemException if a system exception occurred
	*/
	public static int getTransactionLogsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTransactionLogsCount();
	}

	/**
	* Updates the transaction log in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param transactionLog the transaction log
	* @return the transaction log that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.TransactionLog updateTransactionLog(
		com.beorn.onlinepayment.model.TransactionLog transactionLog)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTransactionLog(transactionLog);
	}

	/**
	* Updates the transaction log in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param transactionLog the transaction log
	* @param merge whether to merge the transaction log with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the transaction log that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.TransactionLog updateTransactionLog(
		com.beorn.onlinepayment.model.TransactionLog transactionLog,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTransactionLog(transactionLog, merge);
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

	public static com.beorn.onlinepayment.model.TransactionLog addTransactionLog(
		long userId, long transactionId, java.lang.String paymentApplicationId,
		java.lang.String remoteId, double amount, long type,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addTransactionLog(userId, transactionId,
			paymentApplicationId, remoteId, amount, type, serviceContext);
	}

	public static java.util.List<com.beorn.onlinepayment.model.TransactionLog> getTransactionTransactionLogs(
		long transactionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getTransactionTransactionLogs(transactionId, start, end,
			orderByComparator);
	}

	public static int getTransactionTransactionLogsCount(long transactionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTransactionTransactionLogsCount(transactionId);
	}

	public static com.beorn.onlinepayment.model.TransactionLog updateTransactionLog(
		long transactionLogId, long transactionId,
		java.lang.String paymentApplicationId, java.lang.String remoteId,
		double amount, long type,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateTransactionLog(transactionLogId, transactionId,
			paymentApplicationId, remoteId, amount, type, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static TransactionLogLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					TransactionLogLocalService.class.getName());

			if (invokableLocalService instanceof TransactionLogLocalService) {
				_service = (TransactionLogLocalService)invokableLocalService;
			}
			else {
				_service = new TransactionLogLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(TransactionLogLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(TransactionLogLocalService service) {
	}

	private static TransactionLogLocalService _service;
}