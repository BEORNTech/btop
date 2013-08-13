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

import com.beorn.onlinepayment.service.TransactionServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * <p>
 * This class provides a SOAP utility for the
 * {@link com.beorn.onlinepayment.service.TransactionServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 * </p>
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.beorn.onlinepayment.model.TransactionSoap}.
 * If the method in the service utility returns a
 * {@link com.beorn.onlinepayment.model.Transaction}, that is translated to a
 * {@link com.beorn.onlinepayment.model.TransactionSoap}. Methods that SOAP cannot
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
 * @see       TransactionServiceHttp
 * @see       com.beorn.onlinepayment.model.TransactionSoap
 * @see       com.beorn.onlinepayment.service.TransactionServiceUtil
 * @generated
 */
public class TransactionServiceSoap {
	public static com.beorn.onlinepayment.model.TransactionSoap[] search(
		long companyId, long groupId, java.lang.Long userId,
		java.lang.String keywords, java.lang.Long sellerId,
		java.lang.Long methodId, java.lang.String applicationId,
		java.lang.String paymentApplicationId, java.lang.Long status,
		java.lang.Double amountMin, java.lang.Double amountMax,
		java.lang.String currencyCode, java.util.Date dateMin,
		java.util.Date dateMax, java.lang.String dateType,
		boolean isAndOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws RemoteException {
		try {
			java.util.List<com.beorn.onlinepayment.model.Transaction> returnValue =
				TransactionServiceUtil.search(companyId, groupId, userId,
					keywords, sellerId, methodId, applicationId,
					paymentApplicationId, status, amountMin, amountMax,
					currencyCode, dateMin, dateMax, dateType, isAndOperator,
					start, end, obc);

			return com.beorn.onlinepayment.model.TransactionSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int searchCount(long companyId, long groupId,
		java.lang.Long userId, java.lang.String keywords,
		java.lang.Long sellerId, java.lang.Long methodId,
		java.lang.String applicationId, java.lang.String paymentApplicationId,
		java.lang.Long status, java.lang.Double amountMin,
		java.lang.Double amountMax, java.lang.String currencyCode,
		java.util.Date dateMin, java.util.Date dateMax,
		java.lang.String dateType, boolean isAndOperator)
		throws RemoteException {
		try {
			int returnValue = TransactionServiceUtil.searchCount(companyId,
					groupId, userId, keywords, sellerId, methodId,
					applicationId, paymentApplicationId, status, amountMin,
					amountMax, currencyCode, dateMin, dateMax, dateType,
					isAndOperator);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(TransactionServiceSoap.class);
}