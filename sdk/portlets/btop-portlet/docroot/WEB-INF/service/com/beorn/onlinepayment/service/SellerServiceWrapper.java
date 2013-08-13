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
 * This class is a wrapper for {@link SellerService}.
 * </p>
 *
 * @author    Sebastien Meunier
 * @see       SellerService
 * @generated
 */
public class SellerServiceWrapper implements SellerService,
	ServiceWrapper<SellerService> {
	public SellerServiceWrapper(SellerService sellerService) {
		_sellerService = sellerService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _sellerService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_sellerService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _sellerService.invokeMethod(name, parameterTypes, arguments);
	}

	public java.util.List<com.beorn.onlinepayment.model.Seller> search(
		long companyId, java.lang.String keywords, java.lang.Boolean active,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _sellerService.search(companyId, keywords, active, start, end,
			orderByComparator);
	}

	public int searchCount(long companyId, java.lang.String keywords,
		java.lang.Boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _sellerService.searchCount(companyId, keywords, active);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public SellerService getWrappedSellerService() {
		return _sellerService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedSellerService(SellerService sellerService) {
		_sellerService = sellerService;
	}

	public SellerService getWrappedService() {
		return _sellerService;
	}

	public void setWrappedService(SellerService sellerService) {
		_sellerService = sellerService;
	}

	private SellerService _sellerService;
}