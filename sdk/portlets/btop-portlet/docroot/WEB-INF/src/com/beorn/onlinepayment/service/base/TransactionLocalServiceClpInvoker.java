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

import com.beorn.onlinepayment.service.TransactionLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class TransactionLocalServiceClpInvoker {
	public TransactionLocalServiceClpInvoker() {
		_methodName0 = "addTransaction";

		_methodParameterTypes0 = new String[] {
				"com.beorn.onlinepayment.model.Transaction"
			};

		_methodName1 = "createTransaction";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteTransaction";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteTransaction";

		_methodParameterTypes3 = new String[] {
				"com.beorn.onlinepayment.model.Transaction"
			};

		_methodName4 = "dynamicQuery";

		_methodParameterTypes4 = new String[] {  };

		_methodName5 = "dynamicQuery";

		_methodParameterTypes5 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName6 = "dynamicQuery";

		_methodParameterTypes6 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int"
			};

		_methodName7 = "dynamicQuery";

		_methodParameterTypes7 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName8 = "dynamicQueryCount";

		_methodParameterTypes8 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName9 = "fetchTransaction";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getTransaction";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getTransactionByUuidAndGroupId";

		_methodParameterTypes12 = new String[] { "java.lang.String", "long" };

		_methodName13 = "getTransactions";

		_methodParameterTypes13 = new String[] { "int", "int" };

		_methodName14 = "getTransactionsCount";

		_methodParameterTypes14 = new String[] {  };

		_methodName15 = "updateTransaction";

		_methodParameterTypes15 = new String[] {
				"com.beorn.onlinepayment.model.Transaction"
			};

		_methodName16 = "updateTransaction";

		_methodParameterTypes16 = new String[] {
				"com.beorn.onlinepayment.model.Transaction", "boolean"
			};

		_methodName89 = "getBeanIdentifier";

		_methodParameterTypes89 = new String[] {  };

		_methodName90 = "setBeanIdentifier";

		_methodParameterTypes90 = new String[] { "java.lang.String" };

		_methodName95 = "addTransaction";

		_methodParameterTypes95 = new String[] {
				"long", "java.lang.String", "long", "double", "java.lang.String",
				"java.util.Map", "com.liferay.portal.service.ServiceContext"
			};

		_methodName96 = "getTransactions";

		_methodParameterTypes96 = new String[] {
				"int", "int", "com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName97 = "getApplicationTransactions";

		_methodParameterTypes97 = new String[] {
				"java.lang.String", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName98 = "getApplicationTransactionsCount";

		_methodParameterTypes98 = new String[] { "java.lang.String" };

		_methodName99 = "getSellerTransactions";

		_methodParameterTypes99 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName100 = "getSellerTransactionsCount";

		_methodParameterTypes100 = new String[] { "long" };

		_methodName101 = "getUserTransactions";

		_methodParameterTypes101 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName102 = "getUserTransactionsCount";

		_methodParameterTypes102 = new String[] { "long" };

		_methodName103 = "getApplicationTransactionsBySellerId";

		_methodParameterTypes103 = new String[] {
				"java.lang.String", "long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName104 = "getApplicationTransactionsBySellerIdCount";

		_methodParameterTypes104 = new String[] { "java.lang.String", "long" };

		_methodName105 = "getApplicationTransactionsByUserId";

		_methodParameterTypes105 = new String[] {
				"java.lang.String", "long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName106 = "getApplicationTransactionsByUserIdCount";

		_methodParameterTypes106 = new String[] { "java.lang.String", "long" };

		_methodName107 = "search";

		_methodParameterTypes107 = new String[] {
				"long", "long", "java.lang.Long", "java.lang.String",
				"java.lang.Long", "java.lang.Long", "java.lang.String",
				"java.lang.String", "java.lang.Long", "java.lang.Double",
				"java.lang.Double", "java.lang.String", "java.util.Date",
				"java.util.Date", "java.lang.String", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName108 = "searchCount";

		_methodParameterTypes108 = new String[] {
				"long", "long", "java.lang.Long", "java.lang.String",
				"java.lang.Long", "java.lang.Long", "java.lang.String",
				"java.lang.String", "java.lang.Long", "java.lang.Double",
				"java.lang.Double", "java.lang.String", "java.util.Date",
				"java.util.Date", "java.lang.String", "boolean"
			};

		_methodName109 = "getTransactionParametersMap";

		_methodParameterTypes109 = new String[] { "long" };

		_methodName110 = "addPayment";

		_methodParameterTypes110 = new String[] {
				"long", "java.lang.String", "java.lang.String", "double",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName111 = "addRefund";

		_methodParameterTypes111 = new String[] {
				"long", "java.lang.String", "java.lang.String", "double",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName112 = "updateTransaction";

		_methodParameterTypes112 = new String[] {
				"long", "java.lang.String", "long", "double", "java.lang.String",
				"java.lang.String", "double", "double",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName113 = "deleteTransaction";

		_methodParameterTypes113 = new String[] { "long" };

		_methodName114 = "deleteTransaction";

		_methodParameterTypes114 = new String[] {
				"com.beorn.onlinepayment.model.Transaction"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return TransactionLocalServiceUtil.addTransaction((com.beorn.onlinepayment.model.Transaction)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return TransactionLocalServiceUtil.createTransaction(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return TransactionLocalServiceUtil.deleteTransaction(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return TransactionLocalServiceUtil.deleteTransaction((com.beorn.onlinepayment.model.Transaction)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return TransactionLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return TransactionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return TransactionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return TransactionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return TransactionLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return TransactionLocalServiceUtil.fetchTransaction(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return TransactionLocalServiceUtil.getTransaction(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return TransactionLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return TransactionLocalServiceUtil.getTransactionByUuidAndGroupId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return TransactionLocalServiceUtil.getTransactions(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return TransactionLocalServiceUtil.getTransactionsCount();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return TransactionLocalServiceUtil.updateTransaction((com.beorn.onlinepayment.model.Transaction)arguments[0]);
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			return TransactionLocalServiceUtil.updateTransaction((com.beorn.onlinepayment.model.Transaction)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName89.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes89, parameterTypes)) {
			return TransactionLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName90.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes90, parameterTypes)) {
			TransactionLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);
		}

		if (_methodName95.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes95, parameterTypes)) {
			return TransactionLocalServiceUtil.addTransaction(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Long)arguments[2]).longValue(),
				((Double)arguments[3]).doubleValue(),
				(java.lang.String)arguments[4],
				(java.util.Map<java.lang.String, java.lang.String>)arguments[5],
				(com.liferay.portal.service.ServiceContext)arguments[6]);
		}

		if (_methodName96.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes96, parameterTypes)) {
			return TransactionLocalServiceUtil.getTransactions(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[2]);
		}

		if (_methodName97.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes97, parameterTypes)) {
			return TransactionLocalServiceUtil.getApplicationTransactions((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName98.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes98, parameterTypes)) {
			return TransactionLocalServiceUtil.getApplicationTransactionsCount((java.lang.String)arguments[0]);
		}

		if (_methodName99.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes99, parameterTypes)) {
			return TransactionLocalServiceUtil.getSellerTransactions(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName100.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes100, parameterTypes)) {
			return TransactionLocalServiceUtil.getSellerTransactionsCount(((Long)arguments[0]).longValue());
		}

		if (_methodName101.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes101, parameterTypes)) {
			return TransactionLocalServiceUtil.getUserTransactions(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName102.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes102, parameterTypes)) {
			return TransactionLocalServiceUtil.getUserTransactionsCount(((Long)arguments[0]).longValue());
		}

		if (_methodName103.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes103, parameterTypes)) {
			return TransactionLocalServiceUtil.getApplicationTransactionsBySellerId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4]);
		}

		if (_methodName104.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes104, parameterTypes)) {
			return TransactionLocalServiceUtil.getApplicationTransactionsBySellerIdCount((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName105.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes105, parameterTypes)) {
			return TransactionLocalServiceUtil.getApplicationTransactionsByUserId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4]);
		}

		if (_methodName106.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes106, parameterTypes)) {
			return TransactionLocalServiceUtil.getApplicationTransactionsByUserIdCount((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName107.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes107, parameterTypes)) {
			return TransactionLocalServiceUtil.search(((Long)arguments[0]).longValue(),
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

		if (_methodName108.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes108, parameterTypes)) {
			return TransactionLocalServiceUtil.searchCount(((Long)arguments[0]).longValue(),
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

		if (_methodName109.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes109, parameterTypes)) {
			return TransactionLocalServiceUtil.getTransactionParametersMap(((Long)arguments[0]).longValue());
		}

		if (_methodName110.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes110, parameterTypes)) {
			return TransactionLocalServiceUtil.addPayment(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				((Double)arguments[3]).doubleValue(),
				(com.liferay.portal.service.ServiceContext)arguments[4]);
		}

		if (_methodName111.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes111, parameterTypes)) {
			return TransactionLocalServiceUtil.addRefund(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				((Double)arguments[3]).doubleValue(),
				(com.liferay.portal.service.ServiceContext)arguments[4]);
		}

		if (_methodName112.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes112, parameterTypes)) {
			return TransactionLocalServiceUtil.updateTransaction(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Long)arguments[2]).longValue(),
				((Double)arguments[3]).doubleValue(),
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				((Double)arguments[6]).doubleValue(),
				((Double)arguments[7]).doubleValue(),
				(com.liferay.portal.service.ServiceContext)arguments[8]);
		}

		if (_methodName113.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes113, parameterTypes)) {
			return TransactionLocalServiceUtil.deleteTransaction(((Long)arguments[0]).longValue());
		}

		if (_methodName114.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes114, parameterTypes)) {
			return TransactionLocalServiceUtil.deleteTransaction((com.beorn.onlinepayment.model.Transaction)arguments[0]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName0;
	private String[] _methodParameterTypes0;
	private String _methodName1;
	private String[] _methodParameterTypes1;
	private String _methodName2;
	private String[] _methodParameterTypes2;
	private String _methodName3;
	private String[] _methodParameterTypes3;
	private String _methodName4;
	private String[] _methodParameterTypes4;
	private String _methodName5;
	private String[] _methodParameterTypes5;
	private String _methodName6;
	private String[] _methodParameterTypes6;
	private String _methodName7;
	private String[] _methodParameterTypes7;
	private String _methodName8;
	private String[] _methodParameterTypes8;
	private String _methodName9;
	private String[] _methodParameterTypes9;
	private String _methodName10;
	private String[] _methodParameterTypes10;
	private String _methodName11;
	private String[] _methodParameterTypes11;
	private String _methodName12;
	private String[] _methodParameterTypes12;
	private String _methodName13;
	private String[] _methodParameterTypes13;
	private String _methodName14;
	private String[] _methodParameterTypes14;
	private String _methodName15;
	private String[] _methodParameterTypes15;
	private String _methodName16;
	private String[] _methodParameterTypes16;
	private String _methodName89;
	private String[] _methodParameterTypes89;
	private String _methodName90;
	private String[] _methodParameterTypes90;
	private String _methodName95;
	private String[] _methodParameterTypes95;
	private String _methodName96;
	private String[] _methodParameterTypes96;
	private String _methodName97;
	private String[] _methodParameterTypes97;
	private String _methodName98;
	private String[] _methodParameterTypes98;
	private String _methodName99;
	private String[] _methodParameterTypes99;
	private String _methodName100;
	private String[] _methodParameterTypes100;
	private String _methodName101;
	private String[] _methodParameterTypes101;
	private String _methodName102;
	private String[] _methodParameterTypes102;
	private String _methodName103;
	private String[] _methodParameterTypes103;
	private String _methodName104;
	private String[] _methodParameterTypes104;
	private String _methodName105;
	private String[] _methodParameterTypes105;
	private String _methodName106;
	private String[] _methodParameterTypes106;
	private String _methodName107;
	private String[] _methodParameterTypes107;
	private String _methodName108;
	private String[] _methodParameterTypes108;
	private String _methodName109;
	private String[] _methodParameterTypes109;
	private String _methodName110;
	private String[] _methodParameterTypes110;
	private String _methodName111;
	private String[] _methodParameterTypes111;
	private String _methodName112;
	private String[] _methodParameterTypes112;
	private String _methodName113;
	private String[] _methodParameterTypes113;
	private String _methodName114;
	private String[] _methodParameterTypes114;
}