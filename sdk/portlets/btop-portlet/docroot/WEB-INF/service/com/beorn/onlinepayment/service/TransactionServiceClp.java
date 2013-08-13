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

import com.liferay.portal.service.InvokableService;

/**
 * @author Sebastien Meunier
 */
public class TransactionServiceClp implements TransactionService {
	public TransactionServiceClp(InvokableService invokableService) {
		_invokableService = invokableService;

		_methodName0 = "getBeanIdentifier";

		_methodParameterTypes0 = new String[] {  };

		_methodName1 = "setBeanIdentifier";

		_methodParameterTypes1 = new String[] { "java.lang.String" };

		_methodName3 = "search";

		_methodParameterTypes3 = new String[] {
				"long", "long", "java.lang.Long", "java.lang.String",
				"java.lang.Long", "java.lang.Long", "java.lang.String",
				"java.lang.String", "java.lang.Long", "java.lang.Double",
				"java.lang.Double", "java.lang.String", "java.util.Date",
				"java.util.Date", "java.lang.String", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName4 = "searchCount";

		_methodParameterTypes4 = new String[] {
				"long", "long", "java.lang.Long", "java.lang.String",
				"java.lang.Long", "java.lang.Long", "java.lang.String",
				"java.lang.String", "java.lang.Long", "java.lang.Double",
				"java.lang.Double", "java.lang.String", "java.util.Date",
				"java.util.Date", "java.lang.String", "boolean"
			};
	}

	public java.lang.String getBeanIdentifier() {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName0,
					_methodParameterTypes0, new Object[] {  });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		try {
			_invokableService.invokeMethod(_methodName1,
				_methodParameterTypes1,
				new Object[] { ClpSerializer.translateInput(beanIdentifier) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		throw new UnsupportedOperationException();
	}

	public java.util.List<com.beorn.onlinepayment.model.Transaction> search(
		long companyId, long groupId, java.lang.Long userId,
		java.lang.String keywords, java.lang.Long sellerId,
		java.lang.Long methodId, java.lang.String applicationId,
		java.lang.String paymentApplicationId, java.lang.Long status,
		java.lang.Double amountMin, java.lang.Double amountMax,
		java.lang.String currencyCode, java.util.Date dateMin,
		java.util.Date dateMax, java.lang.String dateType,
		boolean isAndOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName3,
					_methodParameterTypes3,
					new Object[] {
						companyId,
						
					groupId,
						
					ClpSerializer.translateInput(userId),
						
					ClpSerializer.translateInput(keywords),
						
					ClpSerializer.translateInput(sellerId),
						
					ClpSerializer.translateInput(methodId),
						
					ClpSerializer.translateInput(applicationId),
						
					ClpSerializer.translateInput(paymentApplicationId),
						
					ClpSerializer.translateInput(status),
						
					ClpSerializer.translateInput(amountMin),
						
					ClpSerializer.translateInput(amountMax),
						
					ClpSerializer.translateInput(currencyCode),
						
					ClpSerializer.translateInput(dateMin),
						
					ClpSerializer.translateInput(dateMax),
						
					ClpSerializer.translateInput(dateType),
						
					isAndOperator,
						
					start,
						
					end,
						
					ClpSerializer.translateInput(obc)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.beorn.onlinepayment.model.Transaction>)ClpSerializer.translateOutput(returnObj);
	}

	public int searchCount(long companyId, long groupId, java.lang.Long userId,
		java.lang.String keywords, java.lang.Long sellerId,
		java.lang.Long methodId, java.lang.String applicationId,
		java.lang.String paymentApplicationId, java.lang.Long status,
		java.lang.Double amountMin, java.lang.Double amountMax,
		java.lang.String currencyCode, java.util.Date dateMin,
		java.util.Date dateMax, java.lang.String dateType, boolean isAndOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName4,
					_methodParameterTypes4,
					new Object[] {
						companyId,
						
					groupId,
						
					ClpSerializer.translateInput(userId),
						
					ClpSerializer.translateInput(keywords),
						
					ClpSerializer.translateInput(sellerId),
						
					ClpSerializer.translateInput(methodId),
						
					ClpSerializer.translateInput(applicationId),
						
					ClpSerializer.translateInput(paymentApplicationId),
						
					ClpSerializer.translateInput(status),
						
					ClpSerializer.translateInput(amountMin),
						
					ClpSerializer.translateInput(amountMax),
						
					ClpSerializer.translateInput(currencyCode),
						
					ClpSerializer.translateInput(dateMin),
						
					ClpSerializer.translateInput(dateMax),
						
					ClpSerializer.translateInput(dateType),
						
					isAndOperator
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	private InvokableService _invokableService;
	private String _methodName0;
	private String[] _methodParameterTypes0;
	private String _methodName1;
	private String[] _methodParameterTypes1;
	private String _methodName3;
	private String[] _methodParameterTypes3;
	private String _methodName4;
	private String[] _methodParameterTypes4;
}