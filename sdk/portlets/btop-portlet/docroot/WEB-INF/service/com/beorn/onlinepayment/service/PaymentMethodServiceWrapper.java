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
 * This class is a wrapper for {@link PaymentMethodService}.
 * </p>
 *
 * @author    Sebastien Meunier
 * @see       PaymentMethodService
 * @generated
 */
public class PaymentMethodServiceWrapper implements PaymentMethodService,
	ServiceWrapper<PaymentMethodService> {
	public PaymentMethodServiceWrapper(
		PaymentMethodService paymentMethodService) {
		_paymentMethodService = paymentMethodService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _paymentMethodService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_paymentMethodService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _paymentMethodService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public java.util.List<com.beorn.onlinepayment.model.PaymentMethod> search(
		long companyId, java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _paymentMethodService.search(companyId, keywords, start, end,
			orderByComparator);
	}

	public int searchCount(long companyId, java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _paymentMethodService.searchCount(companyId, keywords);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public PaymentMethodService getWrappedPaymentMethodService() {
		return _paymentMethodService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedPaymentMethodService(
		PaymentMethodService paymentMethodService) {
		_paymentMethodService = paymentMethodService;
	}

	public PaymentMethodService getWrappedService() {
		return _paymentMethodService;
	}

	public void setWrappedService(PaymentMethodService paymentMethodService) {
		_paymentMethodService = paymentMethodService;
	}

	private PaymentMethodService _paymentMethodService;
}