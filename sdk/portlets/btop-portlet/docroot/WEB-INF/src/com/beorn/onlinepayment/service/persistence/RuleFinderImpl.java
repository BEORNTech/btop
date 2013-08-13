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

import com.beorn.onlinepayment.model.Rule;
import com.beorn.onlinepayment.model.impl.RuleImpl;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

/**
 * @author Sébastien Meunier
 */

public class RuleFinderImpl extends BasePersistenceImpl<Rule> implements RuleFinder {

	public static String FIND_BY_SELLERID = RuleFinder.class.getName() + ".findBySellerId";

	public static String COUNT_BY_SELLERID = RuleFinder.class.getName() + ".countBySellerId";

	public static String FIND_BY_TRANSACTIONID_AND_PAYMENTMETHODID = RuleFinder.class.getName()
			+ ".findByTransactionIdAndPaymentMethodId";

	public static String COUNT_BY_TRANSACTIONID_AND_PAYMENTMETHODID = RuleFinder.class.getName()
			+ ".countByTransactionIdAndPaymentMethodId";

	public List<Rule> findBySellerId(long sellerId, int start, int end, OrderByComparator obc) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_SELLERID);

			CustomSQLUtil.replaceOrderBy(sql, obc);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("Payment_Rule", RuleImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(sellerId);

			List<Rule> results = (List<Rule>) QueryUtil.list(q, getDialect(), start, end);

			return results;

		} catch (Exception e) {
			throw new SystemException(e);

		} finally {
			closeSession(session);
		}
	}

	public int countBySellerId(long sellerId) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_SELLERID);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(sellerId);

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

	public List<Rule> findByTransactionAndPaymentMethod(long transactionId, long paymentMethodId, Boolean configured,
			int start, int end, OrderByComparator obc) throws SystemException {

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

			q.addEntity("Payment_Rule", RuleImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(transactionId);
			qPos.add(paymentMethodId);
			if (configured != null) {
				qPos.add(configured);
				qPos.add(configured);
			}

			List<Rule> results = (List<Rule>) QueryUtil.list(q, getDialect(), start, end);

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

	private static Log _log = LogFactoryUtil.getLog(RuleFinderImpl.class);
}