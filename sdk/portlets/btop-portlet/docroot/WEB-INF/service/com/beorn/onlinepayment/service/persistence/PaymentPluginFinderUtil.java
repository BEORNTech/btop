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
public class PaymentPluginFinderUtil {
	public static java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> findBySellerId(
		long sellerId, java.lang.Boolean configured, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findBySellerId(sellerId, configured, start, end, obc);
	}

	public static int countBySellerId(long sellerId,
		java.lang.Boolean configured)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countBySellerId(sellerId, configured);
	}

	public static java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> findByTransactionAndPaymentMethod(
		long transactionId, long paymentMethodId, java.lang.Boolean configured,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByTransactionAndPaymentMethod(transactionId,
			paymentMethodId, configured, start, end, obc);
	}

	public static int countByTransactionAndPaymentMethod(long transactionId,
		long paymentMethodId, java.lang.Boolean configured)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByTransactionAndPaymentMethod(transactionId,
			paymentMethodId, configured);
	}

	public static java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> search(
		long companyId, java.lang.String keywords,
		java.lang.Boolean configured, java.lang.Long paymentMethodId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc,
		boolean withPermissionCheck)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .search(companyId, keywords, configured, paymentMethodId,
			start, end, obc, withPermissionCheck);
	}

	public static int searchCount(long companyId, java.lang.String keywords,
		java.lang.Boolean configured, java.lang.Long paymentMethodId,
		boolean withPermissionCheck)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .searchCount(companyId, keywords, configured,
			paymentMethodId, withPermissionCheck);
	}

	public static PaymentPluginFinder getFinder() {
		if (_finder == null) {
			_finder = (PaymentPluginFinder)PortletBeanLocatorUtil.locate(com.beorn.onlinepayment.service.ClpSerializer.getServletContextName(),
					PaymentPluginFinder.class.getName());

			ReferenceRegistry.registerReference(PaymentPluginFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(PaymentPluginFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(PaymentPluginFinderUtil.class,
			"_finder");
	}

	private static PaymentPluginFinder _finder;
}