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

package com.beorn.onlinepayment.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Sebastien Meunier
 */
public class TransactionFinderUtil {
	public static java.util.List<com.beorn.onlinepayment.model.Transaction> search(
		long companyId, long groupId, java.lang.Long userId,
		java.lang.String keywords, java.lang.Long sellerId,
		java.lang.Long methodId, java.lang.String applicationId,
		java.lang.String paymentApplicationId, java.lang.Long status,
		java.lang.Double amountMin, java.lang.Double amountMax,
		java.lang.String currencyCode, java.util.Date dateMin,
		java.util.Date dateMax, java.lang.String dateType,
		boolean isAndOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc,
		boolean withPermissionCheck)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .search(companyId, groupId, userId, keywords, sellerId,
			methodId, applicationId, paymentApplicationId, status, amountMin,
			amountMax, currencyCode, dateMin, dateMax, dateType, isAndOperator,
			start, end, obc, withPermissionCheck);
	}

	public static int searchCount(long companyId, long groupId,
		java.lang.Long userId, java.lang.String keywords,
		java.lang.Long sellerId, java.lang.Long methodId,
		java.lang.String applicationId, java.lang.String paymentApplicationId,
		java.lang.Long status, java.lang.Double amountMin,
		java.lang.Double amountMax, java.lang.String currencyCode,
		java.util.Date dateMin, java.util.Date dateMax,
		java.lang.String dateType, boolean isAndOperator,
		boolean withPermissionCheck)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .searchCount(companyId, groupId, userId, keywords, sellerId,
			methodId, applicationId, paymentApplicationId, status, amountMin,
			amountMax, currencyCode, dateMin, dateMax, dateType, isAndOperator,
			withPermissionCheck);
	}

	public static TransactionFinder getFinder() {
		if (_finder == null) {
			_finder = (TransactionFinder)PortletBeanLocatorUtil.locate(com.beorn.onlinepayment.service.ClpSerializer.getServletContextName(),
					TransactionFinder.class.getName());

			ReferenceRegistry.registerReference(TransactionFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(TransactionFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(TransactionFinderUtil.class,
			"_finder");
	}

	private static TransactionFinder _finder;
}