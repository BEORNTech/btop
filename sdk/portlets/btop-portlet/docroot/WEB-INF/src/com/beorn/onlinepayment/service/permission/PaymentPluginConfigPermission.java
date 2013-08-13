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

import com.beorn.onlinepayment.model.PaymentPluginConfig;
import com.beorn.onlinepayment.service.PaymentPluginConfigLocalServiceUtil;
import com.beorn.onlinepayment.util.ActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Sébastien Meunier
 */
public class PaymentPluginConfigPermission {

	public static void check(PermissionChecker permissionChecker, long paymentPluginConfigId, String actionId)
			throws PortalException {

		if (!contains(permissionChecker, paymentPluginConfigId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(PermissionChecker permissionChecker, PaymentPluginConfig paymentPluginConfig, String actionId)
			throws PortalException {

		if (!contains(permissionChecker, paymentPluginConfig, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(PermissionChecker permissionChecker, long paymentPluginConfigId, String actionId) {

		try {
			PaymentPluginConfig paymentPluginConfig = PaymentPluginConfigLocalServiceUtil
					.getPaymentPluginConfig(paymentPluginConfigId);
			return contains(permissionChecker, paymentPluginConfig, actionId);

		} catch (Exception e) {
			return false;
		}
	}

	public static boolean contains(PermissionChecker permissionChecker, PaymentPluginConfig paymentPluginConfig,
			String actionId) {

		if ((actionId.equals(ActionKeys.VIEW) || actionId.equals(ActionKeys.UPDATE))
				&& permissionChecker.hasOwnerPermission(paymentPluginConfig.getCompanyId(),
						PaymentPluginConfig.class.getName(), paymentPluginConfig.getPaymentPluginConfigId(),
						paymentPluginConfig.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(paymentPluginConfig.getGroupId(), PaymentPluginConfig.class.getName(),
				paymentPluginConfig.getPaymentPluginConfigId(), actionId);
	}
}