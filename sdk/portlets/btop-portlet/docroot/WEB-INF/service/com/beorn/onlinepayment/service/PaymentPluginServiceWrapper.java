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
 * This class is a wrapper for {@link PaymentPluginService}.
 * </p>
 *
 * @author    Sebastien Meunier
 * @see       PaymentPluginService
 * @generated
 */
public class PaymentPluginServiceWrapper implements PaymentPluginService,
	ServiceWrapper<PaymentPluginService> {
	public PaymentPluginServiceWrapper(
		PaymentPluginService paymentPluginService) {
		_paymentPluginService = paymentPluginService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _paymentPluginService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_paymentPluginService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _paymentPluginService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> search(
		long companyId, java.lang.String keywords, java.lang.Boolean active,
		java.lang.Long paymentMethodId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginService.search(companyId, keywords, active,
			paymentMethodId, start, end, orderByComparator);
	}

	public int searchCount(long companyId, java.lang.String keywords,
		java.lang.Boolean active, java.lang.Long paymentMethodId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _paymentPluginService.searchCount(companyId, keywords, active,
			paymentMethodId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public PaymentPluginService getWrappedPaymentPluginService() {
		return _paymentPluginService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedPaymentPluginService(
		PaymentPluginService paymentPluginService) {
		_paymentPluginService = paymentPluginService;
	}

	public PaymentPluginService getWrappedService() {
		return _paymentPluginService;
	}

	public void setWrappedService(PaymentPluginService paymentPluginService) {
		_paymentPluginService = paymentPluginService;
	}

	private PaymentPluginService _paymentPluginService;
}