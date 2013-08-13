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

import com.beorn.onlinepayment.model.Seller;
import com.beorn.onlinepayment.model.impl.SellerImpl;
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

public class SellerFinderImpl extends BasePersistenceImpl<Seller> implements SellerFinder {

	public List<Seller> search(long companyId, String keywords, Boolean active, int start, int end, OrderByComparator obc,
			boolean withPermissionCheck) throws SystemException {

		Session session = null;

		SQLQuery q = null;

		try {
			session = openSession();

			q = buildSearchQuery(session, companyId, keywords, active, obc, false, withPermissionCheck);

			return (List<Seller>) QueryUtil.list(q, getDialect(), start, end);

		} catch (Exception e) {
			if (q != null)
				_log.error("Query failed : " + q.toString());

			throw new SystemException(e);

		} finally {
			closeSession(session);
		}
	}

	public int searchCount(long companyId, String keywords, Boolean active, boolean withPermissionCheck)
			throws SystemException {

		Session session = null;

		SQLQuery q = null;

		try {
			session = openSession();

			q = buildSearchQuery(session, companyId, keywords, active, null, true, withPermissionCheck);

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

	private SQLQuery buildSearchQuery(Session session, long companyId, String keywords, Boolean active,
			OrderByComparator obc, boolean count, boolean withPermissionCheck) throws PortalException, SystemException {

		String sql = "SELECT " + (count ? "count(*) as COUNT_VALUE" : " {Payment_Seller.*}") + " FROM Payment_Seller";
		sql += " WHERE (Payment_Seller.companyId = ?)";

		if (Validator.isNotNull(keywords)) {
			sql += " AND (Payment_Seller.name LIKE ?)";
		}
		if (Validator.isNotNull(active)) {
			sql += " AND (Payment_Seller.active_ = ?)";
		}

		if (!count)
			sql += " ORDER BY Payment_Seller.name ASC";

		sql = PortalUtil.transformCustomSQL(sql);
		sql = CustomSQLUtil.replaceIsNull(sql);
		if (!count)
			sql = CustomSQLUtil.replaceOrderBy(sql, obc);

		if (withPermissionCheck) {
			Group guestGroup = GroupLocalServiceUtil.getGroup(companyId, GroupConstants.GUEST);

			sql = InlineSQLHelperUtil.replacePermissionCheck(sql, Seller.class.getName(), "Payment_Seller.sellerId",
					guestGroup.getGroupId());
		}

		SQLQuery q = session.createSQLQuery(sql);

		if (!count)
			q.addEntity("Payment_Seller", SellerImpl.class);
		else
			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (Validator.isNotNull(keywords)) {
			qPos.add("%" + keywords + "%");
		}
		if (Validator.isNotNull(active)) {
			qPos.add(active);
		}

		return q;
	}

	private static Log _log = LogFactoryUtil.getLog(TransactionFinderImpl.class);
}