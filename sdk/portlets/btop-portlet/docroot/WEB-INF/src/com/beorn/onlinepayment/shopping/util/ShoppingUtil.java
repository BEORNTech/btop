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

package com.beorn.onlinepayment.shopping.util;

import java.util.Map;

import javax.portlet.PortletRequest;

import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.PortalClassInvoker;
import com.liferay.portlet.shopping.model.ShoppingCart;
import com.liferay.portlet.shopping.model.ShoppingCartItem;
import com.liferay.portlet.shopping.model.ShoppingCoupon;

/**
 * @author Sébastien Meunier
 */

public class ShoppingUtil {

	public static ShoppingCart getCart(PortletRequest portletRequest)
			throws Exception {

		return (ShoppingCart) PortalClassInvoker.invoke(false,
				_ShoppingUtilGetCartMethodKey, portletRequest);
	}

	public static double calculateTotal(Map<ShoppingCartItem, Integer> items,
			String billingState, ShoppingCoupon coupon, int altShipping,
			boolean insure) throws Exception {

		return (Double) PortalClassInvoker.invoke(false,
				_ShoppingUtilCalculateTotalMethodKey, items, billingState,
				coupon, altShipping, insure);
	}

	private static final MethodKey _ShoppingUtilGetCartMethodKey = new MethodKey(
			"com.liferay.portlet.shopping.util.ShoppingUtil", "getCart",
			PortletRequest.class);

	private static final MethodKey _ShoppingUtilCalculateTotalMethodKey = new MethodKey(
			"com.liferay.portlet.shopping.util.ShoppingUtil", "calculateTotal",
			Map.class, String.class, ShoppingCoupon.class, int.class,
			boolean.class);

}