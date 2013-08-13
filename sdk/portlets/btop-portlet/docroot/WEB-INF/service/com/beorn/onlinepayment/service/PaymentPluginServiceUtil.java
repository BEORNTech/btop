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
 * The utility for the payment plugin remote service. This utility wraps {@link com.beorn.onlinepayment.service.impl.PaymentPluginServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Sebastien Meunier
 * @see PaymentPluginService
 * @see com.beorn.onlinepayment.service.base.PaymentPluginServiceBaseImpl
 * @see com.beorn.onlinepayment.service.impl.PaymentPluginServiceImpl
 * @generated
 */
public class PaymentPluginServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.beorn.onlinepayment.service.impl.PaymentPluginServiceImpl} and rerun ServiceBuilder to regenerate this class.
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

	public static java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> search(
		long companyId, java.lang.String keywords, java.lang.Boolean active,
		java.lang.Long paymentMethodId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(companyId, keywords, active, paymentMethodId, start,
			end, orderByComparator);
	}

	public static int searchCount(long companyId, java.lang.String keywords,
		java.lang.Boolean active, java.lang.Long paymentMethodId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .searchCount(companyId, keywords, active, paymentMethodId);
	}

	public static void clearService() {
		_service = null;
	}

	public static PaymentPluginService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					PaymentPluginService.class.getName());

			if (invokableService instanceof PaymentPluginService) {
				_service = (PaymentPluginService)invokableService;
			}
			else {
				_service = new PaymentPluginServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(PaymentPluginServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(PaymentPluginService service) {
	}

	private static PaymentPluginService _service;
}