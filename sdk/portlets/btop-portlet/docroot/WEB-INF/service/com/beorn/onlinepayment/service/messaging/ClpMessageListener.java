/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.beorn.onlinepayment.service.messaging;

import com.beorn.onlinepayment.service.ClpSerializer;
import com.beorn.onlinepayment.service.OrderTransactionLocalServiceUtil;
import com.beorn.onlinepayment.service.PaymentMethodLocalServiceUtil;
import com.beorn.onlinepayment.service.PaymentMethodServiceUtil;
import com.beorn.onlinepayment.service.PaymentPluginConfigLocalServiceUtil;
import com.beorn.onlinepayment.service.PaymentPluginLocalServiceUtil;
import com.beorn.onlinepayment.service.PaymentPluginServiceUtil;
import com.beorn.onlinepayment.service.RuleLocalServiceUtil;
import com.beorn.onlinepayment.service.SellerLocalServiceUtil;
import com.beorn.onlinepayment.service.SellerServiceUtil;
import com.beorn.onlinepayment.service.TransactionLocalServiceUtil;
import com.beorn.onlinepayment.service.TransactionLogLocalServiceUtil;
import com.beorn.onlinepayment.service.TransactionParameterLocalServiceUtil;
import com.beorn.onlinepayment.service.TransactionServiceUtil;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

/**
 * @author Brian Wing Shun Chan
 */
public class ClpMessageListener extends BaseMessageListener {
	public static String getServletContextName() {
		return ClpSerializer.getServletContextName();
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		String command = message.getString("command");
		String servletContextName = message.getString("servletContextName");

		if (command.equals("undeploy") &&
				servletContextName.equals(getServletContextName())) {
			OrderTransactionLocalServiceUtil.clearService();

			PaymentMethodLocalServiceUtil.clearService();

			PaymentMethodServiceUtil.clearService();
			PaymentPluginLocalServiceUtil.clearService();

			PaymentPluginServiceUtil.clearService();
			PaymentPluginConfigLocalServiceUtil.clearService();

			RuleLocalServiceUtil.clearService();

			SellerLocalServiceUtil.clearService();

			SellerServiceUtil.clearService();
			TransactionLocalServiceUtil.clearService();

			TransactionServiceUtil.clearService();
			TransactionLogLocalServiceUtil.clearService();

			TransactionParameterLocalServiceUtil.clearService();
		}
	}
}