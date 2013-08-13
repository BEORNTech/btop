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

package com.beorn.onlinepayment.service.http;

import com.beorn.onlinepayment.service.PaymentPluginServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * <p>
 * This class provides a SOAP utility for the
 * {@link com.beorn.onlinepayment.service.PaymentPluginServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 * </p>
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.beorn.onlinepayment.model.PaymentPluginSoap}.
 * If the method in the service utility returns a
 * {@link com.beorn.onlinepayment.model.PaymentPlugin}, that is translated to a
 * {@link com.beorn.onlinepayment.model.PaymentPluginSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at
 * http://localhost:8080/api/secure/axis. Set the property
 * <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author    Sebastien Meunier
 * @see       PaymentPluginServiceHttp
 * @see       com.beorn.onlinepayment.model.PaymentPluginSoap
 * @see       com.beorn.onlinepayment.service.PaymentPluginServiceUtil
 * @generated
 */
public class PaymentPluginServiceSoap {
	public static com.beorn.onlinepayment.model.PaymentPluginSoap[] search(
		long companyId, java.lang.String keywords, java.lang.Boolean active,
		java.lang.Long paymentMethodId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws RemoteException {
		try {
			java.util.List<com.beorn.onlinepayment.model.PaymentPlugin> returnValue =
				PaymentPluginServiceUtil.search(companyId, keywords, active,
					paymentMethodId, start, end, orderByComparator);

			return com.beorn.onlinepayment.model.PaymentPluginSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int searchCount(long companyId, java.lang.String keywords,
		java.lang.Boolean active, java.lang.Long paymentMethodId)
		throws RemoteException {
		try {
			int returnValue = PaymentPluginServiceUtil.searchCount(companyId,
					keywords, active, paymentMethodId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(PaymentPluginServiceSoap.class);
}