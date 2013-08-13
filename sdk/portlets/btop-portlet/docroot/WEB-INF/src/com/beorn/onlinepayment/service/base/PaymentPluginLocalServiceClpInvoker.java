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

import com.beorn.onlinepayment.service.PaymentPluginLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class PaymentPluginLocalServiceClpInvoker {
	public PaymentPluginLocalServiceClpInvoker() {
		_methodName0 = "addPaymentPlugin";

		_methodParameterTypes0 = new String[] {
				"com.beorn.onlinepayment.model.PaymentPlugin"
			};

		_methodName1 = "createPaymentPlugin";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deletePaymentPlugin";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deletePaymentPlugin";

		_methodParameterTypes3 = new String[] {
				"com.beorn.onlinepayment.model.PaymentPlugin"
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

		_methodName9 = "fetchPaymentPlugin";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getPaymentPlugin";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getPaymentPluginByUuidAndGroupId";

		_methodParameterTypes12 = new String[] { "java.lang.String", "long" };

		_methodName13 = "getPaymentPlugins";

		_methodParameterTypes13 = new String[] { "int", "int" };

		_methodName14 = "getPaymentPluginsCount";

		_methodParameterTypes14 = new String[] {  };

		_methodName15 = "updatePaymentPlugin";

		_methodParameterTypes15 = new String[] {
				"com.beorn.onlinepayment.model.PaymentPlugin"
			};

		_methodName16 = "updatePaymentPlugin";

		_methodParameterTypes16 = new String[] {
				"com.beorn.onlinepayment.model.PaymentPlugin", "boolean"
			};

		_methodName89 = "getBeanIdentifier";

		_methodParameterTypes89 = new String[] {  };

		_methodName90 = "setBeanIdentifier";

		_methodParameterTypes90 = new String[] { "java.lang.String" };

		_methodName95 = "addPaymentPlugin";

		_methodParameterTypes95 = new String[] {
				"long", "java.lang.String", "java.util.Map", "java.lang.String",
				"java.lang.String", "com.liferay.portal.service.ServiceContext"
			};

		_methodName96 = "getPaymentPlugins";

		_methodParameterTypes96 = new String[] {
				"int", "int", "com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName97 = "getSellerPaymentPlugins";

		_methodParameterTypes97 = new String[] {
				"long", "java.lang.Boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName98 = "getSellerPaymentPluginsCount";

		_methodParameterTypes98 = new String[] { "long", "java.lang.Boolean" };

		_methodName99 = "getPaymentMethodPaymentPlugins";

		_methodParameterTypes99 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName100 = "getPaymentMethodPaymentPluginsCount";

		_methodParameterTypes100 = new String[] { "long" };

		_methodName101 = "getPaymentPluginByApplicationId";

		_methodParameterTypes101 = new String[] { "java.lang.String" };

		_methodName102 = "getAvailablePaymentPlugins";

		_methodParameterTypes102 = new String[] {
				"long", "long", "java.lang.String"
			};

		_methodName103 = "getPaymentPluginsByTransactionAndPaymentMethod";

		_methodParameterTypes103 = new String[] {
				"long", "long", "java.lang.Boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName104 = "getPaymentPluginsByTransactionAndPaymentMethodCount";

		_methodParameterTypes104 = new String[] {
				"long", "long", "java.lang.Boolean"
			};

		_methodName105 = "search";

		_methodParameterTypes105 = new String[] {
				"long", "java.lang.String", "java.lang.Boolean",
				"java.lang.Long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName106 = "searchCount";

		_methodParameterTypes106 = new String[] {
				"long", "java.lang.String", "java.lang.Boolean",
				"java.lang.Long"
			};

		_methodName107 = "updatePaymentPlugin";

		_methodParameterTypes107 = new String[] {
				"long", "java.lang.String", "java.util.Map", "java.lang.String",
				"java.lang.String", "java.lang.String", "boolean",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName108 = "updatePaymentPluginPaymentMethods";

		_methodParameterTypes108 = new String[] { "long", "java.util.List" };

		_methodName109 = "deletePaymentPlugin";

		_methodParameterTypes109 = new String[] { "long" };

		_methodName110 = "deletePaymentPlugin";

		_methodParameterTypes110 = new String[] {
				"com.beorn.onlinepayment.model.PaymentPlugin"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return PaymentPluginLocalServiceUtil.addPaymentPlugin((com.beorn.onlinepayment.model.PaymentPlugin)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return PaymentPluginLocalServiceUtil.createPaymentPlugin(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return PaymentPluginLocalServiceUtil.deletePaymentPlugin(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return PaymentPluginLocalServiceUtil.deletePaymentPlugin((com.beorn.onlinepayment.model.PaymentPlugin)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return PaymentPluginLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return PaymentPluginLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return PaymentPluginLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return PaymentPluginLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return PaymentPluginLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return PaymentPluginLocalServiceUtil.fetchPaymentPlugin(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return PaymentPluginLocalServiceUtil.getPaymentPlugin(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return PaymentPluginLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return PaymentPluginLocalServiceUtil.getPaymentPluginByUuidAndGroupId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return PaymentPluginLocalServiceUtil.getPaymentPlugins(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return PaymentPluginLocalServiceUtil.getPaymentPluginsCount();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return PaymentPluginLocalServiceUtil.updatePaymentPlugin((com.beorn.onlinepayment.model.PaymentPlugin)arguments[0]);
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			return PaymentPluginLocalServiceUtil.updatePaymentPlugin((com.beorn.onlinepayment.model.PaymentPlugin)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName89.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes89, parameterTypes)) {
			return PaymentPluginLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName90.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes90, parameterTypes)) {
			PaymentPluginLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);
		}

		if (_methodName95.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes95, parameterTypes)) {
			return PaymentPluginLocalServiceUtil.addPaymentPlugin(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(com.liferay.portal.service.ServiceContext)arguments[5]);
		}

		if (_methodName96.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes96, parameterTypes)) {
			return PaymentPluginLocalServiceUtil.getPaymentPlugins(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[2]);
		}

		if (_methodName97.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes97, parameterTypes)) {
			return PaymentPluginLocalServiceUtil.getSellerPaymentPlugins(((Long)arguments[0]).longValue(),
				(java.lang.Boolean)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4]);
		}

		if (_methodName98.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes98, parameterTypes)) {
			return PaymentPluginLocalServiceUtil.getSellerPaymentPluginsCount(((Long)arguments[0]).longValue(),
				(java.lang.Boolean)arguments[1]);
		}

		if (_methodName99.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes99, parameterTypes)) {
			return PaymentPluginLocalServiceUtil.getPaymentMethodPaymentPlugins(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName100.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes100, parameterTypes)) {
			return PaymentPluginLocalServiceUtil.getPaymentMethodPaymentPluginsCount(((Long)arguments[0]).longValue());
		}

		if (_methodName101.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes101, parameterTypes)) {
			return PaymentPluginLocalServiceUtil.getPaymentPluginByApplicationId((java.lang.String)arguments[0]);
		}

		if (_methodName102.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes102, parameterTypes)) {
			return PaymentPluginLocalServiceUtil.getAvailablePaymentPlugins(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.lang.String)arguments[2]);
		}

		if (_methodName103.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes103, parameterTypes)) {
			return PaymentPluginLocalServiceUtil.getPaymentPluginsByTransactionAndPaymentMethod(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.Boolean)arguments[2],
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[5]);
		}

		if (_methodName104.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes104, parameterTypes)) {
			return PaymentPluginLocalServiceUtil.getPaymentPluginsByTransactionAndPaymentMethodCount(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.Boolean)arguments[2]);
		}

		if (_methodName105.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes105, parameterTypes)) {
			return PaymentPluginLocalServiceUtil.search(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(java.lang.Boolean)arguments[2], (java.lang.Long)arguments[3],
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[6]);
		}

		if (_methodName106.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes106, parameterTypes)) {
			return PaymentPluginLocalServiceUtil.searchCount(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(java.lang.Boolean)arguments[2], (java.lang.Long)arguments[3]);
		}

		if (_methodName107.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes107, parameterTypes)) {
			return PaymentPluginLocalServiceUtil.updatePaymentPlugin(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5],
				((Boolean)arguments[6]).booleanValue(),
				(com.liferay.portal.service.ServiceContext)arguments[7]);
		}

		if (_methodName108.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes108, parameterTypes)) {
			PaymentPluginLocalServiceUtil.updatePaymentPluginPaymentMethods(((Long)arguments[0]).longValue(),
				(java.util.List<com.beorn.onlinepayment.model.PaymentMethod>)arguments[1]);
		}

		if (_methodName109.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes109, parameterTypes)) {
			return PaymentPluginLocalServiceUtil.deletePaymentPlugin(((Long)arguments[0]).longValue());
		}

		if (_methodName110.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes110, parameterTypes)) {
			return PaymentPluginLocalServiceUtil.deletePaymentPlugin((com.beorn.onlinepayment.model.PaymentPlugin)arguments[0]);
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
}