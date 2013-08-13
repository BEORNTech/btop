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

package com.beorn.onlinepayment.service.impl;

import java.util.Date;

import com.beorn.onlinepayment.model.OrderTransaction;
import com.beorn.onlinepayment.service.base.OrderTransactionLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.shopping.service.ShoppingOrderLocalServiceUtil;

/**
 * The implementation of the order transaction local service.
 * 
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.beorn.betop.service.OrderTransactionLocalService} interface.
 * 
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 * 
 * @author Sebastien Meunier
 * @see com.beorn.betop.service.base.OrderTransactionLocalServiceBaseImpl
 * @see com.beorn.betop.service.OrderTransactionLocalServiceUtil
 */
public class OrderTransactionLocalServiceImpl extends
		OrderTransactionLocalServiceBaseImpl {

	public OrderTransaction addOrderTransaction(long orderId, long transactionId)
			throws PortalException, SystemException {

		Date now = new Date();

		validate(null, orderId, transactionId);

		OrderTransaction orderTransaction = orderTransactionPersistence
				.create(orderId);

		orderTransaction.setCreateDate(now);
		orderTransaction.setModifiedDate(now);
		orderTransaction.setOrderId(orderId);
		orderTransaction.setTransactionId(transactionId);

		orderTransactionPersistence.update(orderTransaction, false);

		return orderTransaction;
	}

	public OrderTransaction getOrderTransactionByTransactionId(
			long transactionId) throws PortalException, SystemException {

		return orderTransactionPersistence.findByTransactionId(transactionId);
	}

	public OrderTransaction deleteOrderTransaction(long orderId)
			throws PortalException, SystemException {

		return deleteOrderTransaction(orderTransactionPersistence
				.findByPrimaryKey(orderId));
	}

	public OrderTransaction deleteOrderTransaction(
			OrderTransaction orderTransaction) throws SystemException {

		return super.deleteOrderTransaction(orderTransaction);
	}

	private void validate(OrderTransaction orderTransaction, long orderId,
			long transactionId) throws PortalException, SystemException {

		ShoppingOrderLocalServiceUtil.getOrder(orderId);
	}
}