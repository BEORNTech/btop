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

package com.beorn.onlinepayment.service.permission;

import com.beorn.onlinepayment.model.Transaction;
import com.beorn.onlinepayment.service.TransactionLocalServiceUtil;
import com.beorn.onlinepayment.util.ActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Sébastien Meunier
 */
public class TransactionPermission {

	public static void check(PermissionChecker permissionChecker, long transactionId, String actionId)
			throws PortalException {

		if (!contains(permissionChecker, transactionId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(PermissionChecker permissionChecker, Transaction transaction, String actionId)
			throws PortalException, SystemException {

		if (!contains(permissionChecker, transaction, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(PermissionChecker permissionChecker, long transactionId, String actionId) {
		try {
			Transaction transaction = TransactionLocalServiceUtil.getTransaction(transactionId);
			return contains(permissionChecker, transaction, actionId);

		} catch (Exception e) {
			return false;
		}
	}

	public static boolean contains(PermissionChecker permissionChecker, Transaction transaction, String actionId)
			throws PortalException, SystemException {

		if (actionId.equals(ActionKeys.VIEW)
				&& permissionChecker.hasOwnerPermission(transaction.getCompanyId(), Transaction.class.getName(),
						transaction.getTransactionId(), transaction.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(transaction.getGroupId(), Transaction.class.getName(),
				transaction.getTransactionId(), actionId);
	}
}