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
import com.liferay.portal.service.InvokableService;

/**
 * The utility for the transaction remote service. This utility wraps {@link com.beorn.onlinepayment.service.impl.TransactionServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Sebastien Meunier
 * @see TransactionService
 * @see com.beorn.onlinepayment.service.base.TransactionServiceBaseImpl
 * @see com.beorn.onlinepayment.service.impl.TransactionServiceImpl
 * @generated
 */
public class TransactionServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.beorn.onlinepayment.service.impl.TransactionServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

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

	public static void clearService() {
		_service = null;
	}

	public static TransactionService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					TransactionService.class.getName());

			if (invokableService instanceof TransactionService) {
				_service = (TransactionService)invokableService;
			}
			else {
				_service = new TransactionServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(TransactionServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(TransactionService service) {
	}

	private static TransactionService _service;
}