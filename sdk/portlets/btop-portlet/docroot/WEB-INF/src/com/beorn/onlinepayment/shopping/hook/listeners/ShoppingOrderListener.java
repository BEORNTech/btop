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

package com.beorn.onlinepayment.shopping.hook.listeners;

import com.beorn.onlinepayment.NoSuchOrderTransactionException;
import com.beorn.onlinepayment.service.OrderTransactionLocalServiceUtil;
import com.liferay.portal.ModelListenerException;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portlet.shopping.model.ShoppingOrder;

/**
 * @author Sébastien Meunier
 */

public class ShoppingOrderListener extends BaseModelListener<ShoppingOrder> {

	public void onBeforeRemove(ShoppingOrder shoppingOrder)
			throws ModelListenerException {

		try {
			OrderTransactionLocalServiceUtil
					.deleteOrderTransaction(shoppingOrder.getOrderId());

		} catch (NoSuchOrderTransactionException e) {
			// Ignore

		} catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

}