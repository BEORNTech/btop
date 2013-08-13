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
 * The utility for the transaction local service. This utility wraps {@link com.beorn.onlinepayment.service.impl.TransactionLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Sebastien Meunier
 * @see TransactionLocalService
 * @see com.beorn.onlinepayment.service.base.TransactionLocalServiceBaseImpl
 * @see com.beorn.onlinepayment.service.impl.TransactionLocalServiceImpl
 * @generated
 */
public class TransactionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.beorn.onlinepayment.service.impl.TransactionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the transaction to the database. Also notifies the appropriate model listeners.
	*
	* @param transaction the transaction
	* @return the transaction that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction addTransaction(
		com.beorn.onlinepayment.model.Transaction transaction)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addTransaction(transaction);
	}

	/**
	* Creates a new transaction with the primary key. Does not add the transaction to the database.
	*
	* @param transactionId the primary key for the new transaction
	* @return the new transaction
	*/
	public static com.beorn.onlinepayment.model.Transaction createTransaction(
		long transactionId) {
		return getService().createTransaction(transactionId);
	}

	/**
	* Deletes the transaction with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param transactionId the primary key of the transaction
	* @return the transaction that was removed
	* @throws PortalException if a transaction with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction deleteTransaction(
		long transactionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTransaction(transactionId);
	}

	/**
	* Deletes the transaction from the database. Also notifies the appropriate model listeners.
	*
	* @param transaction the transaction
	* @return the transaction that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction deleteTransaction(
		com.beorn.onlinepayment.model.Transaction transaction)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTransaction(transaction);
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

	public static com.beorn.onlinepayment.model.Transaction fetchTransaction(
		long transactionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchTransaction(transactionId);
	}

	/**
	* Returns the transaction with the primary key.
	*
	* @param transactionId the primary key of the transaction
	* @return the transaction
	* @throws PortalException if a transaction with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction getTransaction(
		long transactionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTransaction(transactionId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the transaction with the UUID in the group.
	*
	* @param uuid the UUID of transaction
	* @param groupId the group id of the transaction
	* @return the transaction
	* @throws PortalException if a transaction with the UUID in the group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction getTransactionByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTransactionByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the transactions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of transactions
	* @param end the upper bound of the range of transactions (not inclusive)
	* @return the range of transactions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.onlinepayment.model.Transaction> getTransactions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTransactions(start, end);
	}

	/**
	* Returns the number of transactions.
	*
	* @return the number of transactions
	* @throws SystemException if a system exception occurred
	*/
	public static int getTransactionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTransactionsCount();
	}

	/**
	* Updates the transaction in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param transaction the transaction
	* @return the transaction that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction updateTransaction(
		com.beorn.onlinepayment.model.Transaction transaction)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTransaction(transaction);
	}

	/**
	* Updates the transaction in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param transaction the transaction
	* @param merge whether to merge the transaction with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the transaction that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.onlinepayment.model.Transaction updateTransaction(
		com.beorn.onlinepayment.model.Transaction transaction, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTransaction(transaction, merge);
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

	public static com.beorn.onlinepayment.model.Transaction addTransaction(
		long userId, java.lang.String applicationId, long sellerId,
		double amount, java.lang.String currencyCode,
		java.util.Map<java.lang.String, java.lang.String> parameters,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addTransaction(userId, applicationId, sellerId, amount,
			currencyCode, parameters, serviceContext);
	}

	public static java.util.List<com.beorn.onlinepayment.model.Transaction> getTransactions(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTransactions(start, end, orderByComparator);
	}

	public static java.util.List<com.beorn.onlinepayment.model.Transaction> getApplicationTransactions(
		java.lang.String applicationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getApplicationTransactions(applicationId, start, end,
			orderByComparator);
	}

	public static int getApplicationTransactionsCount(
		java.lang.String applicationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getApplicationTransactionsCount(applicationId);
	}

	public static java.util.List<com.beorn.onlinepayment.model.Transaction> getSellerTransactions(
		long sellerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getSellerTransactions(sellerId, start, end,
			orderByComparator);
	}

	public static int getSellerTransactionsCount(long sellerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSellerTransactionsCount(sellerId);
	}

	public static java.util.List<com.beorn.onlinepayment.model.Transaction> getUserTransactions(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getUserTransactions(userId, start, end, orderByComparator);
	}

	public static int getUserTransactionsCount(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserTransactionsCount(userId);
	}

	public static java.util.List<com.beorn.onlinepayment.model.Transaction> getApplicationTransactionsBySellerId(
		java.lang.String applicationId, long sellerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getApplicationTransactionsBySellerId(applicationId,
			sellerId, start, end, orderByComparator);
	}

	public static int getApplicationTransactionsBySellerIdCount(
		java.lang.String applicationId, long sellerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getApplicationTransactionsBySellerIdCount(applicationId,
			sellerId);
	}

	public static java.util.List<com.beorn.onlinepayment.model.Transaction> getApplicationTransactionsByUserId(
		java.lang.String applicationId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getApplicationTransactionsByUserId(applicationId, userId,
			start, end, orderByComparator);
	}

	public static int getApplicationTransactionsByUserIdCount(
		java.lang.String applicationId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getApplicationTransactionsByUserIdCount(applicationId,
			userId);
	}

	public static java.util.List<com.beorn.onlinepayment.model.Transaction> search(
		long companyId, long groupId, java.lang.Long userId,
		java.lang.String keywords, java.lang.Long sellerId,
		java.lang.Long methodId, java.lang.String applicationId,
		java.lang.String paymentApplicationId, java.lang.Long status,
		java.lang.Double amountMin, java.lang.Double amountMax,
		java.lang.String currencyCode, java.util.Date dateMin,
		java.util.Date dateMax, java.lang.String dateType,
		boolean isAndOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(companyId, groupId, userId, keywords, sellerId,
			methodId, applicationId, paymentApplicationId, status, amountMin,
			amountMax, currencyCode, dateMin, dateMax, dateType, isAndOperator,
			start, end, obc);
	}

	public static int searchCount(long companyId, long groupId,
		java.lang.Long userId, java.lang.String keywords,
		java.lang.Long sellerId, java.lang.Long methodId,
		java.lang.String applicationId, java.lang.String paymentApplicationId,
		java.lang.Long status, java.lang.Double amountMin,
		java.lang.Double amountMax, java.lang.String currencyCode,
		java.util.Date dateMin, java.util.Date dateMax,
		java.lang.String dateType, boolean isAndOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .searchCount(companyId, groupId, userId, keywords, sellerId,
			methodId, applicationId, paymentApplicationId, status, amountMin,
			amountMax, currencyCode, dateMin, dateMax, dateType, isAndOperator);
	}

	public static java.util.Map<java.lang.String, java.lang.String> getTransactionParametersMap(
		long transactionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTransactionParametersMap(transactionId);
	}

	public static com.beorn.onlinepayment.model.Transaction addPayment(
		long transactionId, java.lang.String remoteId,
		java.lang.String paymentApplicationId, double amountPaid,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addPayment(transactionId, remoteId, paymentApplicationId,
			amountPaid, serviceContext);
	}

	public static com.beorn.onlinepayment.model.Transaction addRefund(
		long transactionId, java.lang.String refundId,
		java.lang.String paymentApplicationId, double amountRefunded,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addRefund(transactionId, refundId, paymentApplicationId,
			amountRefunded, serviceContext);
	}

	public static com.beorn.onlinepayment.model.Transaction updateTransaction(
		long transactionId, java.lang.String applicationId, long sellerId,
		double amount, java.lang.String currencyCode,
		java.lang.String paymentApplicationId, double amountPaid,
		double amountRefunded,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateTransaction(transactionId, applicationId, sellerId,
			amount, currencyCode, paymentApplicationId, amountPaid,
			amountRefunded, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static TransactionLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					TransactionLocalService.class.getName());

			if (invokableLocalService instanceof TransactionLocalService) {
				_service = (TransactionLocalService)invokableLocalService;
			}
			else {
				_service = new TransactionLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(TransactionLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(TransactionLocalService service) {
	}

	private static TransactionLocalService _service;
}