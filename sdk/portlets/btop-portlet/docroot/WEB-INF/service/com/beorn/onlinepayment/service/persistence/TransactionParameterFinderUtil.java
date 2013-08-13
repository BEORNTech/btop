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
public class TransactionParameterFinderUtil {
	public static java.util.List<java.lang.String> findParameterKeysForSellerId(
		long sellerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findParameterKeysForSellerId(sellerId);
	}

	public static TransactionParameterFinder getFinder() {
		if (_finder == null) {
			_finder = (TransactionParameterFinder)PortletBeanLocatorUtil.locate(com.beorn.onlinepayment.service.ClpSerializer.getServletContextName(),
					TransactionParameterFinder.class.getName());

			ReferenceRegistry.registerReference(TransactionParameterFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(TransactionParameterFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(TransactionParameterFinderUtil.class,
			"_finder");
	}

	private static TransactionParameterFinder _finder;
}