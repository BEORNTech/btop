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

package com.beorn.onlinepayment.shopping.notification;

import com.beorn.onlinepayment.model.OrderTransaction;
import com.beorn.onlinepayment.service.OrderTransactionLocalServiceUtil;
import com.beorn.paymentapi.model.ApiTransaction;
import com.beorn.paymentapi.util.TransactionStatus;
import com.beorn.paymentappapi.notification.NotificationListener;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.shopping.model.ShoppingOrder;
import com.liferay.portlet.shopping.model.ShoppingOrderConstants;
import com.liferay.portlet.shopping.service.ShoppingOrderLocalServiceUtil;

public class NotificationListenerImpl implements NotificationListener {

	public void onTransactionUpdated(ApiTransaction transaction) {
		if (transaction.getStatus() != TransactionStatus.PAID)
			return;

		try {
			OrderTransaction orderTransaction = OrderTransactionLocalServiceUtil
					.getOrderTransactionByTransactionId(transaction
							.getTransactionId());

			ShoppingOrder order = ShoppingOrderLocalServiceUtil
					.getOrder(orderTransaction.getOrderId());

			String invoice = order.getNumber();
			String txnId = String.valueOf(transaction.getTransactionId());
			String paymentStatus = ShoppingOrderConstants.STATUS_COMPLETED;
			double paymentGross = transaction.getAmount();
			String receiverEmail = StringPool.BLANK;
			String payerEmail = StringPool.BLANK;

			// Assuming it isn't used
			ServiceContext serviceContext = null;

			ShoppingOrderLocalServiceUtil.completeOrder(invoice, txnId,
					paymentStatus, paymentGross, receiverEmail, payerEmail,
					true, serviceContext);

		} catch (Exception e) {
			_log.error(
					"Error while processing onTransactionUpdated notification",
					e);
		}
	}

	private static Log _log = LogFactoryUtil
			.getLog(NotificationListenerImpl.class);
}
