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

package com.beorn.onlinepayment.service.base;

import com.beorn.onlinepayment.service.TransactionServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class TransactionServiceClpInvoker {
	public TransactionServiceClpInvoker() {
		_methodName72 = "getBeanIdentifier";

		_methodParameterTypes72 = new String[] {  };

		_methodName73 = "setBeanIdentifier";

		_methodParameterTypes73 = new String[] { "java.lang.String" };

		_methodName78 = "search";

		_methodParameterTypes78 = new String[] {
				"long", "long", "java.lang.Long", "java.lang.String",
				"java.lang.Long", "java.lang.Long", "java.lang.String",
				"java.lang.String", "java.lang.Long", "java.lang.Double",
				"java.lang.Double", "java.lang.String", "java.util.Date",
				"java.util.Date", "java.lang.String", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName79 = "searchCount";

		_methodParameterTypes79 = new String[] {
				"long", "long", "java.lang.Long", "java.lang.String",
				"java.lang.Long", "java.lang.Long", "java.lang.String",
				"java.lang.String", "java.lang.Long", "java.lang.Double",
				"java.lang.Double", "java.lang.String", "java.util.Date",
				"java.util.Date", "java.lang.String", "boolean"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName72.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes72, parameterTypes)) {
			return TransactionServiceUtil.getBeanIdentifier();
		}

		if (_methodName73.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes73, parameterTypes)) {
			TransactionServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);
		}

		if (_methodName78.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes78, parameterTypes)) {
			return TransactionServiceUtil.search(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.lang.Long)arguments[2],
				(java.lang.String)arguments[3], (java.lang.Long)arguments[4],
				(java.lang.Long)arguments[5], (java.lang.String)arguments[6],
				(java.lang.String)arguments[7], (java.lang.Long)arguments[8],
				(java.lang.Double)arguments[9],
				(java.lang.Double)arguments[10],
				(java.lang.String)arguments[11], (java.util.Date)arguments[12],
				(java.util.Date)arguments[13], (java.lang.String)arguments[14],
				((Boolean)arguments[15]).booleanValue(),
				((Integer)arguments[16]).intValue(),
				((Integer)arguments[17]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[18]);
		}

		if (_methodName79.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes79, parameterTypes)) {
			return TransactionServiceUtil.searchCount(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.lang.Long)arguments[2],
				(java.lang.String)arguments[3], (java.lang.Long)arguments[4],
				(java.lang.Long)arguments[5], (java.lang.String)arguments[6],
				(java.lang.String)arguments[7], (java.lang.Long)arguments[8],
				(java.lang.Double)arguments[9],
				(java.lang.Double)arguments[10],
				(java.lang.String)arguments[11], (java.util.Date)arguments[12],
				(java.util.Date)arguments[13], (java.lang.String)arguments[14],
				((Boolean)arguments[15]).booleanValue());
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName72;
	private String[] _methodParameterTypes72;
	private String _methodName73;
	private String[] _methodParameterTypes73;
	private String _methodName78;
	private String[] _methodParameterTypes78;
	private String _methodName79;
	private String[] _methodParameterTypes79;
}