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

import com.beorn.onlinepayment.model.PaymentPlugin;
import com.beorn.onlinepayment.service.PaymentPluginLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Sébastien Meunier
 */
public class PaymentPluginPermission {

	public static void check(PermissionChecker permissionChecker, long paymentPluginId, String actionId)
			throws PortalException {

		if (!contains(permissionChecker, paymentPluginId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(PermissionChecker permissionChecker, PaymentPlugin paymentPlugin, String actionId)
			throws PortalException {

		if (!contains(permissionChecker, paymentPlugin, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(PermissionChecker permissionChecker, long paymentPluginId, String actionId) {
		try {
			PaymentPlugin paymentPlugin = PaymentPluginLocalServiceUtil.getPaymentPlugin(paymentPluginId);
			return contains(permissionChecker, paymentPlugin, actionId);

		} catch (Exception e) {
			return false;
		}
	}

	public static boolean contains(PermissionChecker permissionChecker, PaymentPlugin paymentPlugin, String actionId) {
		return permissionChecker.hasPermission(paymentPlugin.getGroupId(), PaymentPlugin.class.getName(),
				paymentPlugin.getPaymentPluginId(), actionId);
	}
}