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
public class RuleFinderUtil {
	public static java.util.List<com.beorn.onlinepayment.model.Rule> findBySellerId(
		long sellerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findBySellerId(sellerId, start, end, obc);
	}

	public static int countBySellerId(long sellerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countBySellerId(sellerId);
	}

	public static java.util.List<com.beorn.onlinepayment.model.Rule> findByTransactionAndPaymentMethod(
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

	public static RuleFinder getFinder() {
		if (_finder == null) {
			_finder = (RuleFinder)PortletBeanLocatorUtil.locate(com.beorn.onlinepayment.service.ClpSerializer.getServletContextName(),
					RuleFinder.class.getName());

			ReferenceRegistry.registerReference(RuleFinderUtil.class, "_finder");
		}

		return _finder;
	}

	public void setFinder(RuleFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(RuleFinderUtil.class, "_finder");
	}

	private static RuleFinder _finder;
}