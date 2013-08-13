/**
 * Copyright (c) 2007-2013 BEORN Technologies, SARL. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */

package com.beorn.onlinepayment.service.persistence;

import java.util.Iterator;
import java.util.List;

import com.beorn.onlinepayment.model.PaymentPlugin;
import com.beorn.onlinepayment.model.impl.PaymentPluginImpl;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.dao.orm.CustomSQLUtil;

/**
 * @author Sébastien Meunier
 */

public class PaymentPluginFinderImpl extends BasePersistenceImpl<PaymentPlugin> implements PaymentPluginFinder {

	public static String FIND_BY_SELLERID = PaymentPluginFinder.class.getName() + ".findBySellerId";

	public static String COUNT_BY_SELLERID = PaymentPluginFinder.class.getName() + ".countBySellerId";

	public static String FIND_BY_TRANSACTIONID_AND_PAYMENTMETHODID = PaymentPluginFinder.class.getName()
			+ ".findByTransactionIdAndPaymentMethodId";

	public static String COUNT_BY_TRANSACTIONID_AND_PAYMENTMETHODID = PaymentPluginFinder.class.getName()
			+ ".countByTransactionIdAndPaymentMethodId";

	public List<PaymentPlugin> findBySellerId(long sellerId, Boolean configured, int start, int end, OrderByComparator obc)
			throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_SELLERID);

			if (configured == null) {
				sql = StringUtil.replace(sql, "(Payment_PaymentPlugin.configured = ?) AND", "");
				sql = StringUtil.replace(sql, "(Payment_PaymentPluginConfig.configured = ?) AND", "");
			}

			CustomSQLUtil.replaceOrderBy(sql, obc);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("Payment_PaymentPlugin", PaymentPluginImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(sellerId);
			if (configured != null) {
				qPos.add(configured);
				qPos.add(configured);
			}

			List<PaymentPlugin> results = (List<PaymentPlugin>) QueryUtil.list(q, getDialect(), start, end);

			return results;

		} catch (Exception e) {
			throw new SystemException(e);

		} finally {
			closeSession(session);
		}
	}

	public int countBySellerId(long sellerId, Boolean configured) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_SELLERID);

			if (configured == null) {
				sql = StringUtil.replace(sql, "(Payment_PaymentPlugin.configured = ?) AND", "");
				sql = StringUtil.replace(sql, "(Payment_PaymentPluginConfig.configured = ?) AND", "");
			}

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(sellerId);
			if (configured != null) {
				qPos.add(configured);
				qPos.add(configured);
			}

			Iterator<Long> itr = q.list().iterator();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;

		} catch (Exception e) {
			throw new SystemException(e);

		} finally {
			closeSession(session);
		}
	}

	public List<PaymentPlugin> findByTransactionAndPaymentMethod(long transactionId, long paymentMethodId,
			Boolean configured, int start, int end, OrderByComparator obc) throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_TRANSACTIONID_AND_PAYMENTMETHODID);

			if (configured == null) {
				sql = StringUtil.replace(sql, "(Payment_PaymentPlugin.configured = ?) AND", "");
				sql = StringUtil.replace(sql, "(Payment_PaymentPluginConfig.configured = ?) AND", "");
			}

			CustomSQLUtil.replaceOrderBy(sql, obc);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("Payment_PaymentPlugin", PaymentPluginImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(transactionId);
			qPos.add(paymentMethodId);
			if (configured != null) {
				qPos.add(configured);
				qPos.add(configured);
			}

			List<PaymentPlugin> results = (List<PaymentPlugin>) QueryUtil.list(q, getDialect(), start, end);

			return results;

		} catch (Exception e) {
			throw new SystemException(e);

		} finally {
			closeSession(session);
		}
	}

	public int countByTransactionAndPaymentMethod(long transactionId, long paymentMethodId, Boolean configured)
			throws SystemException {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_TRANSACTIONID_AND_PAYMENTMETHODID);

			if (configured == null) {
				sql = StringUtil.replace(sql, "(Payment_PaymentPlugin.configured = ?) AND", "");
				sql = StringUtil.replace(sql, "(Payment_PaymentPluginConfig.configured = ?) AND", "");
			}

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(transactionId);
			qPos.add(paymentMethodId);
			if (configured != null) {
				qPos.add(configured);
				qPos.add(configured);
			}

			Iterator<Long> itr = q.list().iterator();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;

		} catch (Exception e) {
			throw new SystemException(e);

		} finally {
			closeSession(session);
		}
	}

	public List<PaymentPlugin> search(long companyId, String keywords, Boolean configured, Long paymentMethodId, int start,
			int end, OrderByComparator obc, boolean withPermissionCheck) throws SystemException {

		Session session = null;

		SQLQuery q = null;

		try {
			session = openSession();

			q = buildSearchQuery(session, companyId, keywords, configured, paymentMethodId, obc, false, withPermissionCheck);

			return (List<PaymentPlugin>) QueryUtil.list(q, getDialect(), start, end);

		} catch (Exception e) {
			if (q != null)
				_log.error("Query failed : " + q.toString());

			throw new SystemException(e);

		} finally {
			closeSession(session);
		}
	}

	public int searchCount(long companyId, String keywords, Boolean configured, Long paymentMethodId,
			boolean withPermissionCheck) throws SystemException {

		Session session = null;

		SQLQuery q = null;

		try {
			session = openSession();

			q = buildSearchQuery(session, companyId, keywords, configured, paymentMethodId, null, true, withPermissionCheck);

			Iterator<Long> itr = q.list().iterator();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;

		} catch (Exception e) {
			if (q != null)
				_log.error("Query failed : " + q.toString());

			throw new SystemException(e);

		} finally {
			closeSession(session);
		}
	}

	private SQLQuery buildSearchQuery(Session session, long companyId, String keywords, Boolean configured,
			Long paymentMethodId, OrderByComparator obc, boolean count, boolean withPermissionCheck) throws PortalException,
			SystemException {

		String sql = "SELECT " + (count ? "count(*) as COUNT_VALUE" : " {Payment_PaymentPlugin.*}")
				+ " FROM Payment_PaymentPlugin";

		if (Validator.isNotNull(paymentMethodId))
			sql += " INNER JOIN Payment_PaymentPlugin_PaymentMethod ON (Payment_PaymentPlugin.paymentPluginId = Payment_PaymentPlugin_PaymentMethod.paymentPluginId)";

		sql += " WHERE (Payment_PaymentPlugin.companyId = ?)";

		if (Validator.isNotNull(keywords)) {
			sql += " AND (Payment_PaymentPlugin.name LIKE ?)";
		}
		if (Validator.isNotNull(configured)) {
			sql += " AND (Payment_PaymentPlugin.configured = ?)";
		}
		if (Validator.isNotNull(paymentMethodId)) {
			sql += " AND (Payment_PaymentPlugin_PaymentMethod.paymentMethodId = ?)";
		}

		if (!count)
			sql += " ORDER BY Payment_PaymentPlugin.name ASC";

		sql = PortalUtil.transformCustomSQL(sql);
		sql = CustomSQLUtil.replaceIsNull(sql);
		if (!count)
			sql = CustomSQLUtil.replaceOrderBy(sql, obc);

		if (withPermissionCheck) {
			Group guestGroup = GroupLocalServiceUtil.getGroup(companyId, GroupConstants.GUEST);

			sql = InlineSQLHelperUtil.replacePermissionCheck(sql, PaymentPlugin.class.getName(),
					"Payment_PaymentPlugin.paymentPluginId", guestGroup.getGroupId());
		}

		SQLQuery q = session.createSQLQuery(sql);

		if (!count)
			q.addEntity("Payment_PaymentPlugin", PaymentPluginImpl.class);
		else
			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (Validator.isNotNull(keywords)) {
			qPos.add("%" + keywords + "%");
		}
		if (Validator.isNotNull(configured)) {
			qPos.add(configured);
		}
		if (Validator.isNotNull(paymentMethodId)) {
			qPos.add(paymentMethodId);
		}

		return q;
	}

	private static Log _log = LogFactoryUtil.getLog(PaymentPluginFinderImpl.class);
}