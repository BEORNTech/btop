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

import com.beorn.onlinepayment.model.TransactionParameter;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

/**
 * @author Sébastien Meunier
 */

public class TransactionParameterFinderImpl extends
		BasePersistenceImpl<TransactionParameter> implements
		TransactionParameterFinder {

	public static String FIND_PARAMETER_KEYS_FOR_SELLER_ID = TransactionParameterFinder.class
			.getName() + ".findParameterKeysForSellerId";

	public List<String> findParameterKeysForSellerId(long sellerId)
			throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_PARAMETER_KEYS_FOR_SELLER_ID);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar("Payment_TransactionParameter.key_", Type.STRING);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(sellerId);

			return (List<String>) QueryUtil.list(q, getDialect(),
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		} catch (Exception e) {
			throw new SystemException(e);

		} finally {
			closeSession(session);
		}
	}

	private static Log _log = LogFactoryUtil
			.getLog(TransactionParameterFinderImpl.class);
}