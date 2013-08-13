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

package com.beorn.demopaymentplugin.portlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import com.beorn.paymentpluginapi.messaging.PaymentPluginSender;
import com.beorn.paymentpluginapi.util.PaymentPluginUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

public class PaymentPluginPortlet extends MVCPortlet {

	public void completePayment(ActionRequest actionRequest,
			ActionResponse actionResponse) throws IOException, PortletException {

		try {
			long transactionId = ParamUtil.getLong(actionRequest,
					"transactionId");
			double amountPaid = ParamUtil
					.getDouble(actionRequest, "amountPaid");
			String successUrl = ParamUtil
					.getString(actionRequest, "successUrl");
			String errorUrl = ParamUtil.getString(actionRequest, "errorUrl");

			PaymentPluginSender paymentPluginSender = PaymentPluginUtil
					.getPaymentPluginSender();

			try {
				// XXX A real payment plugin would use a unique id for this
				// payment, coming from the remote platform where the payment
				// happened
				String remoteId = String.valueOf(transactionId);

				paymentPluginSender.addPayment(transactionId, remoteId,
						amountPaid);
				actionResponse.sendRedirect(successUrl);

			} catch (Exception e) {
				actionResponse.sendRedirect(errorUrl);

			}
			actionResponse.sendRedirect(successUrl);

		} catch (Exception e) {
			_log.error(e);
			actionResponse.setRenderParameters(actionRequest.getParameterMap());
			SessionErrors.add(actionRequest, e.getClass().getName(), e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(PaymentPluginPortlet.class);
}
