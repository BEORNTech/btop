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

import com.beorn.onlinepayment.model.PaymentMethod;
import com.beorn.onlinepayment.model.impl.PaymentMethodImpl;
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

public class PaymentMethodFinderImpl extends BasePersistenceImpl<PaymentMethod> implements PaymentMethodFinder {

	public static String FIND_BY_SELLERID = PaymentMethodFinder.class.getName() + ".findBySellerId";

	public static String COUNT_BY_SELLERID = PaymentMethodFinder.class.getName() + ".countBySellerId";

	public List<PaymentMethod> findBySellerId(long sellerId, Boolean configured, int start, int end, OrderByComparator obc)
			throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_SELLERID);

			if (configured == null) {
				sql = StringUtil.replace(sql, "(Payment_PaymentPluginConfig.configured = ?) AND", "");
			}

			CustomSQLUtil.replaceOrderBy(sql, obc);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("Payment_PaymentMethod", PaymentMethodImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(sellerId);
			if (configured != null)
				qPos.add(configured);

			List<PaymentMethod> results = (List<PaymentMethod>) QueryUtil.list(q, getDialect(), start, end);

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
				sql = StringUtil.replace(sql, "(Payment_PaymentPluginConfig.configured = ?) AND", "");
			}

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(sellerId);
			if (configured != null)
				qPos.add(configured);

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

	public List<PaymentMethod> search(long companyId, String keywords, int start, int end, OrderByComparator obc,
			boolean withPermissionCheck) throws SystemException {

		Session session = null;

		SQLQuery q = null;

		try {
			session = openSession();

			q = buildSearchQuery(session, companyId, keywords, obc, false, withPermissionCheck);

			return (List<PaymentMethod>) QueryUtil.list(q, getDialect(), start, end);

		} catch (Exception e) {
			if (q != null)
				_log.error("Query failed : " + q.toString());

			throw new SystemException(e);

		} finally {
			closeSession(session);
		}
	}

	public int searchCount(long companyId, String keywords, boolean withPermissionCheck) throws SystemException {

		Session session = null;

		SQLQuery q = null;

		try {
			session = openSession();

			q = buildSearchQuery(session, companyId, keywords, null, true, withPermissionCheck);

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

	private SQLQuery buildSearchQuery(Session session, long companyId, String keywords, OrderByComparator obc,
			boolean count, boolean withPermissionCheck) throws PortalException, SystemException {

		String sql = "SELECT " + (count ? "count(*) as COUNT_VALUE" : " {Payment_PaymentMethod.*}")
				+ " FROM Payment_PaymentMethod";

		sql += " WHERE (Payment_PaymentMethod.companyId = ?)";

		if (Validator.isNotNull(keywords)) {
			sql += " AND (Payment_PaymentMethod.name LIKE ?)";
		}

		if (!count)
			sql += " ORDER BY Payment_PaymentMethod.name ASC";

		sql = PortalUtil.transformCustomSQL(sql);
		sql = CustomSQLUtil.replaceIsNull(sql);
		if (!count)
			sql = CustomSQLUtil.replaceOrderBy(sql, obc);

		if (withPermissionCheck) {
			Group guestGroup = GroupLocalServiceUtil.getGroup(companyId, GroupConstants.GUEST);

			sql = InlineSQLHelperUtil.replacePermissionCheck(sql, PaymentMethod.class.getName(),
					"Payment_PaymentMethod.paymentMethodId", guestGroup.getGroupId());
		}

		SQLQuery q = session.createSQLQuery(sql);

		if (!count)
			q.addEntity("Payment_PaymentMethod", PaymentMethodImpl.class);
		else
			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (Validator.isNotNull(keywords)) {
			qPos.add("%" + keywords + "%");
		}

		return q;
	}

	private static Log _log = LogFactoryUtil.getLog(PaymentMethodFinderImpl.class);
}