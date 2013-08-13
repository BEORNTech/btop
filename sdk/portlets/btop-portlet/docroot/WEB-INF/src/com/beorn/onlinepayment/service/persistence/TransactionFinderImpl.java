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

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.beorn.onlinepayment.model.Transaction;
import com.beorn.onlinepayment.model.impl.TransactionImpl;
import com.beorn.onlinepayment.util.TransactionDateTypes;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
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

public class TransactionFinderImpl extends BasePersistenceImpl<Transaction> implements TransactionFinder {

	public List<Transaction> search(long companyId, long groupId, Long userId, String keywords, Long sellerId,
			Long methodId, String applicationId, String paymentApplicationId, Long status, Double amountMin,
			Double amountMax, String currencyCode, Date dateMin, Date dateMax, String dateType, boolean isAndOperator,
			int start, int end, OrderByComparator obc, boolean withPermissionCheck) throws SystemException {

		Session session = null;

		SQLQuery q = null;

		try {
			session = openSession();

			q = buildSearchQuery(session, companyId, groupId, userId, keywords, sellerId, methodId, applicationId,
					paymentApplicationId, status, amountMin, amountMax, currencyCode, dateMin, dateMax, dateType,
					isAndOperator, obc, false, withPermissionCheck);

			return (List<Transaction>) QueryUtil.list(q, getDialect(), start, end);

		} catch (Exception e) {
			if (q != null)
				_log.error("Query failed : " + q.toString());

			throw new SystemException(e);

		} finally {
			closeSession(session);
		}
	}

	public int searchCount(long companyId, long groupId, Long userId, String keywords, Long sellerId, Long methodId,
			String applicationId, String paymentApplicationId, Long status, Double amountMin, Double amountMax,
			String currencyCode, Date dateMin, Date dateMax, String dateType, boolean isAndOperator,
			boolean withPermissionCheck) throws SystemException {

		Session session = null;

		SQLQuery q = null;

		try {
			session = openSession();

			q = buildSearchQuery(session, companyId, groupId, userId, keywords, sellerId, methodId, applicationId,
					paymentApplicationId, status, amountMin, amountMax, currencyCode, dateMin, dateMax, dateType,
					isAndOperator, null, true, withPermissionCheck);

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

	private SQLQuery buildSearchQuery(Session session, long companyId, long groupId, Long userId, String keywords,
			Long sellerId, Long methodId, String applicationId, String paymentApplicationId, Long status, Double amountMin,
			Double amountMax, String currencyCode, Date dateMin, Date dateMax, String dateType, boolean isAndOperator,
			OrderByComparator obc, boolean count, boolean withPermissionCheck) throws PortalException, SystemException {

		String sql = "SELECT " + (count ? "count(*) as COUNT_VALUE" : " {Payment_Transaction.*}")
				+ " FROM Payment_Transaction";

		if (Validator.isNotNull(methodId)) {
			sql += " INNER JOIN Payment_PaymentPlugin ON (Payment_Transaction.paymentApplicationId = Payment_PaymentPlugin.applicationId)";
			sql += " INNER JOIN Payment_PaymentPlugin_PaymentMethod ON (Payment_PaymentPlugin.paymentPluginId = Payment_PaymentPlugin_PaymentMethod.paymentPluginId)";
		}
		if (Validator.isNotNull(keywords)) {
			sql += " LEFT JOIN Payment_TransactionLog ON (Payment_Transaction.transactionId = Payment_TransactionLog.transactionId)";
		}

		sql += " WHERE (Payment_Transaction.companyId = ?)";
		if (groupId > 0) {
			sql += " AND (Payment_Transaction.groupId = ?)";
		}
		if (Validator.isNotNull(userId)) {
			sql += " AND (Payment_Transaction.userId = ?)";
		}
		if (Validator.isNotNull(keywords)) {
			sql += " AND ((Payment_Transaction.transactionId = ?) OR (Payment_TransactionLog.remoteId LIKE ?))";
		}
		if (Validator.isNotNull(sellerId)) {
			sql += " AND (Payment_Transaction.sellerId = ?)";
		}
		if (Validator.isNotNull(methodId)) {
			sql += " AND (Payment_PaymentPlugin_PaymentMethod.paymentMethodId = ?)";
		}
		if (Validator.isNotNull(applicationId)) {
			sql += " AND (Payment_Transaction.applicationId = ?)";
		}
		if (Validator.isNotNull(paymentApplicationId)) {
			sql += " AND (Payment_Transaction.paymentApplicationId = ?)";
		}
		if (Validator.isNotNull(status)) {
			sql += " AND (Payment_Transaction.status = ?)";
		}
		if (Validator.isNotNull(amountMin)) {
			sql += " AND (Payment_Transaction.amount >= ?)";
		}
		if (Validator.isNotNull(amountMax)) {
			sql += " AND (Payment_Transaction.amount <= ?)";
		}
		if (Validator.isNotNull(currencyCode)) {
			sql += " AND (Payment_Transaction.currencyCode = ?)";
		}
		if (isValidDateType(dateType)) {
			if (Validator.isNotNull(dateMin)) {
				sql += " AND (Payment_Transaction." + dateType + "Date >= ?)";
			}
			if (Validator.isNotNull(dateMax)) {
				sql += " AND (Payment_Transaction." + dateType + "Date <= ?)";
			}
		}

		if (!count)
			sql += " ORDER BY Payment_Transaction.modifiedDate DESC";

		sql = PortalUtil.transformCustomSQL(sql);
		sql = CustomSQLUtil.replaceIsNull(sql);
		if (!count)
			sql = CustomSQLUtil.replaceOrderBy(sql, obc);

		if (withPermissionCheck) {
			Group guestGroup = GroupLocalServiceUtil.getGroup(companyId, GroupConstants.GUEST);

			sql = InlineSQLHelperUtil.replacePermissionCheck(sql, Transaction.class.getName(),
					"Payment_Transaction.transactionId", guestGroup.getGroupId());
		}

		SQLQuery q = session.createSQLQuery(sql);

		if (!count)
			q.addEntity("Payment_Transaction", TransactionImpl.class);
		else
			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (groupId > 0) {
			qPos.add(groupId);
		}
		if (Validator.isNotNull(userId)) {
			qPos.add(userId);
		}
		if (Validator.isNotNull(keywords)) {
			qPos.add(keywords);
			qPos.add("%" + keywords + "%");
		}
		if (Validator.isNotNull(sellerId)) {
			qPos.add(sellerId);
		}
		if (Validator.isNotNull(methodId)) {
			qPos.add(methodId);
		}
		if (Validator.isNotNull(applicationId)) {
			qPos.add(applicationId);
		}
		if (Validator.isNotNull(paymentApplicationId)) {
			qPos.add(paymentApplicationId);
		}
		if (Validator.isNotNull(status)) {
			qPos.add(status);
		}
		if (Validator.isNotNull(amountMin)) {
			qPos.add(amountMin);
		}
		if (Validator.isNotNull(amountMax)) {
			qPos.add(amountMax);
		}
		if (Validator.isNotNull(currencyCode)) {
			qPos.add(currencyCode);
		}
		if (isValidDateType(dateType)) {
			if (Validator.isNotNull(dateMin)) {
				qPos.add(CalendarUtil.getTimestamp(dateMin));
			}
			if (Validator.isNotNull(dateMax)) {
				qPos.add(CalendarUtil.getTimestamp(dateMax));
			}
		}

		return q;
	}

	private boolean isValidDateType(String dateType) {
		if (Validator.isNull(dateType))
			return false;

		return ArrayUtil.contains(TransactionDateTypes.VALID_DATE_TYPES, dateType);
	}

	private static Log _log = LogFactoryUtil.getLog(TransactionFinderImpl.class);
}