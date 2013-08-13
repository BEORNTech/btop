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
 * This class is a wrapper for {@link TransactionService}.
 * </p>
 *
 * @author    Sebastien Meunier
 * @see       TransactionService
 * @generated
 */
public class TransactionServiceWrapper implements TransactionService,
	ServiceWrapper<TransactionService> {
	public TransactionServiceWrapper(TransactionService transactionService) {
		_transactionService = transactionService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _transactionService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_transactionService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _transactionService.invokeMethod(name, parameterTypes, arguments);
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
		return _transactionService.search(companyId, groupId, userId, keywords,
			sellerId, methodId, applicationId, paymentApplicationId, status,
			amountMin, amountMax, currencyCode, dateMin, dateMax, dateType,
			isAndOperator, start, end, obc);
	}

	public int searchCount(long companyId, long groupId, java.lang.Long userId,
		java.lang.String keywords, java.lang.Long sellerId,
		java.lang.Long methodId, java.lang.String applicationId,
		java.lang.String paymentApplicationId, java.lang.Long status,
		java.lang.Double amountMin, java.lang.Double amountMax,
		java.lang.String currencyCode, java.util.Date dateMin,
		java.util.Date dateMax, java.lang.String dateType, boolean isAndOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _transactionService.searchCount(companyId, groupId, userId,
			keywords, sellerId, methodId, applicationId, paymentApplicationId,
			status, amountMin, amountMax, currencyCode, dateMin, dateMax,
			dateType, isAndOperator);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public TransactionService getWrappedTransactionService() {
		return _transactionService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedTransactionService(
		TransactionService transactionService) {
		_transactionService = transactionService;
	}

	public TransactionService getWrappedService() {
		return _transactionService;
	}

	public void setWrappedService(TransactionService transactionService) {
		_transactionService = transactionService;
	}

	private TransactionService _transactionService;
}