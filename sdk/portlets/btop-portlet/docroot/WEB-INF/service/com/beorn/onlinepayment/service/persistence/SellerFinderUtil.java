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
public class SellerFinderUtil {
	public static java.util.List<com.beorn.onlinepayment.model.Seller> search(
		long companyId, java.lang.String keywords, java.lang.Boolean active,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc,
		boolean withPermissionCheck)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .search(companyId, keywords, active, start, end, obc,
			withPermissionCheck);
	}

	public static int searchCount(long companyId, java.lang.String keywords,
		java.lang.Boolean active, boolean withPermissionCheck)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .searchCount(companyId, keywords, active, withPermissionCheck);
	}

	public static SellerFinder getFinder() {
		if (_finder == null) {
			_finder = (SellerFinder)PortletBeanLocatorUtil.locate(com.beorn.onlinepayment.service.ClpSerializer.getServletContextName(),
					SellerFinder.class.getName());

			ReferenceRegistry.registerReference(SellerFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(SellerFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(SellerFinderUtil.class, "_finder");
	}

	private static SellerFinder _finder;
}