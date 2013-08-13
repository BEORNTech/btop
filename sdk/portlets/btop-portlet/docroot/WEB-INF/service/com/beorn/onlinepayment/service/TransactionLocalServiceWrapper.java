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
 * This class is a wrapper for {@link TransactionLocalService}.
 * </p>
 *
 * @author    Sebastien Meunier
 * @see       TransactionLocalService
 * @generated
 */
public class TransactionLocalServiceWrapper implements TransactionLocalService,
	ServiceWrapper<TransactionLocalService> {
	public TransactionLocalServiceWrapper(
		TransactionLocalService transactionLocalService) {
		_transactionLocalService = transactionLocalService;
	}

	/**
	* Adds the transaction to the database. Also notifies the appropriate model listeners.
	*
	* @param transaction the transaction
	* @return the transaction that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.Transaction addTransaction(
		com.beorn.onlinepayment.model.Transaction transaction)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _transactionLocalService.addTransaction(transaction);
	}

	/**
	* Creates a new transaction with the primary key. Does not add the transaction to the database.
	*
	* @param transactionId the primary key for the new transaction
	* @return the new transaction
	*/
	public com.beorn.onlinepayment.model.Transaction createTransaction(
		long transactionId) {
		return _transactionLocalService.createTransaction(transactionId);
	}

	/**
	* Deletes the transaction with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param transactionId the primary key of the transaction
	* @return the transaction that was removed
	* @throws PortalException if a transaction with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.Transaction deleteTransaction(
		long transactionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _transactionLocalService.deleteTransaction(transactionId);
	}

	/**
	* Deletes the transaction from the database. Also notifies the appropriate model listeners.
	*
	* @param transaction the transaction
	* @return the transaction that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.Transaction deleteTransaction(
		com.beorn.onlinepayment.model.Transaction transaction)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _transactionLocalService.deleteTransaction(transaction);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _transactionLocalService.dynamicQuery();
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
		return _transactionLocalService.dynamicQuery(dynamicQuery);
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
		return _transactionLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _transactionLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _transactionLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.beorn.onlinepayment.model.Transaction fetchTransaction(
		long transactionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _transactionLocalService.fetchTransaction(transactionId);
	}

	/**
	* Returns the transaction with the primary key.
	*
	* @param transactionId the primary key of the transaction
	* @return the transaction
	* @throws PortalException if a transaction with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.Transaction getTransaction(
		long transactionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _transactionLocalService.getTransaction(transactionId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _transactionLocalService.getPersistedModel(primaryKeyObj);
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
	public com.beorn.onlinepayment.model.Transaction getTransactionByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _transactionLocalService.getTransactionByUuidAndGroupId(uuid,
			groupId);
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
	public java.util.List<com.beorn.onlinepayment.model.Transaction> getTransactions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _transactionLocalService.getTransactions(start, end);
	}

	/**
	* Returns the number of transactions.
	*
	* @return the number of transactions
	* @throws SystemException if a system exception occurred
	*/
	public int getTransactionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _transactionLocalService.getTransactionsCount();
	}

	/**
	* Updates the transaction in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param transaction the transaction
	* @return the transaction that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.Transaction updateTransaction(
		com.beorn.onlinepayment.model.Transaction transaction)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _transactionLocalService.updateTransaction(transaction);
	}

	/**
	* Updates the transaction in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param transaction the transaction
	* @param merge whether to merge the transaction with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the transaction that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.Transaction updateTransaction(
		com.beorn.onlinepayment.model.Transaction transaction, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _transactionLocalService.updateTransaction(transaction, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _transactionLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_transactionLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _transactionLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.beorn.onlinepayment.model.Transaction addTransaction(
		long userId, java.lang.String applicationId, long sellerId,
		double amount, java.lang.String currencyCode,
		java.util.Map<java.lang.String, java.lang.String> parameters,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _transactionLocalService.addTransaction(userId, applicationId,
			sellerId, amount, currencyCode, parameters, serviceContext);
	}

	public java.util.List<com.beorn.onlinepayment.model.Transaction> getTransactions(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _transactionLocalService.getTransactions(start, end,
			orderByComparator);
	}

	public java.util.List<com.beorn.onlinepayment.model.Transaction> getApplicationTransactions(
		java.lang.String applicationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _transactionLocalService.getApplicationTransactions(applicationId,
			start, end, orderByComparator);
	}

	public int getApplicationTransactionsCount(java.lang.String applicationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _transactionLocalService.getApplicationTransactionsCount(applicationId);
	}

	public java.util.List<com.beorn.onlinepayment.model.Transaction> getSellerTransactions(
		long sellerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _transactionLocalService.getSellerTransactions(sellerId, start,
			end, orderByComparator);
	}

	public int getSellerTransactionsCount(long sellerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _transactionLocalService.getSellerTransactionsCount(sellerId);
	}

	public java.util.List<com.beorn.onlinepayment.model.Transaction> getUserTransactions(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _transactionLocalService.getUserTransactions(userId, start, end,
			orderByComparator);
	}

	public int getUserTransactionsCount(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _transactionLocalService.getUserTransactionsCount(userId);
	}

	public java.util.List<com.beorn.onlinepayment.model.Transaction> getApplicationTransactionsBySellerId(
		java.lang.String applicationId, long sellerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _transactionLocalService.getApplicationTransactionsBySellerId(applicationId,
			sellerId, start, end, orderByComparator);
	}

	public int getApplicationTransactionsBySellerIdCount(
		java.lang.String applicationId, long sellerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _transactionLocalService.getApplicationTransactionsBySellerIdCount(applicationId,
			sellerId);
	}

	public java.util.List<com.beorn.onlinepayment.model.Transaction> getApplicationTransactionsByUserId(
		java.lang.String applicationId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _transactionLocalService.getApplicationTransactionsByUserId(applicationId,
			userId, start, end, orderByComparator);
	}

	public int getApplicationTransactionsByUserIdCount(
		java.lang.String applicationId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _transactionLocalService.getApplicationTransactionsByUserIdCount(applicationId,
			userId);
	}

	public java.util.List<com.beorn.onlinepayment.model.Transaction> search(
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
		return _transactionLocalService.search(companyId, groupId, userId,
			keywords, sellerId, methodId, applicationId, paymentApplicationId,
			status, amountMin, amountMax, currencyCode, dateMin, dateMax,
			dateType, isAndOperator, start, end, obc);
	}

	public int searchCount(long companyId, long groupId, java.lang.Long userId,
		java.lang.String keywords, java.lang.Long sellerId,
		java.lang.Long methodId, java.lang.String applicationId,
		java.lang.String paymentApplicationId, java.lang.Long status,
		java.lang.Double amountMin, java.lang.Double amountMax,
		java.lang.String currencyCode, java.util.Date dateMin,
		java.util.Date dateMax, java.lang.String dateType, boolean isAndOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _transactionLocalService.searchCount(companyId, groupId, userId,
			keywords, sellerId, methodId, applicationId, paymentApplicationId,
			status, amountMin, amountMax, currencyCode, dateMin, dateMax,
			dateType, isAndOperator);
	}

	public java.util.Map<java.lang.String, java.lang.String> getTransactionParametersMap(
		long transactionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _transactionLocalService.getTransactionParametersMap(transactionId);
	}

	public com.beorn.onlinepayment.model.Transaction addPayment(
		long transactionId, java.lang.String remoteId,
		java.lang.String paymentApplicationId, double amountPaid,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _transactionLocalService.addPayment(transactionId, remoteId,
			paymentApplicationId, amountPaid, serviceContext);
	}

	public com.beorn.onlinepayment.model.Transaction addRefund(
		long transactionId, java.lang.String refundId,
		java.lang.String paymentApplicationId, double amountRefunded,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _transactionLocalService.addRefund(transactionId, refundId,
			paymentApplicationId, amountRefunded, serviceContext);
	}

	public com.beorn.onlinepayment.model.Transaction updateTransaction(
		long transactionId, java.lang.String applicationId, long sellerId,
		double amount, java.lang.String currencyCode,
		java.lang.String paymentApplicationId, double amountPaid,
		double amountRefunded,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _transactionLocalService.updateTransaction(transactionId,
			applicationId, sellerId, amount, currencyCode,
			paymentApplicationId, amountPaid, amountRefunded, serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public TransactionLocalService getWrappedTransactionLocalService() {
		return _transactionLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedTransactionLocalService(
		TransactionLocalService transactionLocalService) {
		_transactionLocalService = transactionLocalService;
	}

	public TransactionLocalService getWrappedService() {
		return _transactionLocalService;
	}

	public void setWrappedService(
		TransactionLocalService transactionLocalService) {
		_transactionLocalService = transactionLocalService;
	}

	private TransactionLocalService _transactionLocalService;
}