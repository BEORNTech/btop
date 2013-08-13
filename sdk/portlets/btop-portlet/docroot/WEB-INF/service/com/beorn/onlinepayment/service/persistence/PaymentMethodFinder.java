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

/**
 * @author Sebastien Meunier
 */
public interface PaymentMethodFinder {
	public java.util.List<com.beorn.onlinepayment.model.PaymentMethod> findBySellerId(
		long sellerId, java.lang.Boolean configured, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countBySellerId(long sellerId, java.lang.Boolean configured)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.beorn.onlinepayment.model.PaymentMethod> search(
		long companyId, java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc,
		boolean withPermissionCheck)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int searchCount(long companyId, java.lang.String keywords,
		boolean withPermissionCheck)
		throws com.liferay.portal.kernel.exception.SystemException;
}