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

package com.beorn.onlinepayment.shopping.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import com.beorn.onlinepayment.model.OrderTransaction;
import com.beorn.onlinepayment.service.OrderTransactionLocalServiceUtil;
import com.beorn.paymentappapi.messaging.PaymentAppSender;
import com.beorn.paymentappapi.util.PaymentAppUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.struts.BaseStrutsPortletAction;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletURLFactoryUtil;

public class BtopSelectPaymentMethodAction extends BaseStrutsPortletAction {

	@Override
	public void processAction(PortletConfig portletConfig,
			ActionRequest actionRequest, ActionResponse actionResponse)
			throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
				.getAttribute(WebKeys.THEME_DISPLAY);

		long orderId = ParamUtil.getLong(actionRequest, "orderId");
		long paymentMethodId = ParamUtil.getLong(actionRequest, "methodId");

		OrderTransaction orderTransaction = OrderTransactionLocalServiceUtil
				.getOrderTransaction(orderId);

		PaymentAppSender paymentAppSender = PaymentAppUtil
				.getPaymentAppSender();

		HttpServletRequest request = PortalUtil
				.getHttpServletRequest(actionRequest);

		String remoteIp = request.getRemoteAddr();
		String forwardedForIp = StringUtil.extractFirst(
				request.getHeader("X-Forwarded-For"),
				StringPool.COMMA_AND_SPACE);
		if (forwardedForIp != null)
			remoteIp = forwardedForIp;

		String countryCode = paymentAppSender.geolocalizeIp(remoteIp);

		LiferayPortletURL returnURL = PortletURLFactoryUtil.create(
				actionRequest, "34", themeDisplay.getPlid(),
				PortletRequest.ACTION_PHASE);
		returnURL.setWindowState(LiferayWindowState.NORMAL);
		returnURL.setParameter("struts_action", "/shopping/checkout");
		returnURL.setParameter(Constants.CMD, Constants.VIEW);
		returnURL.setParameter("orderId", String.valueOf(orderId));

		String backUrl = returnURL.toString();
		String successUrl = returnURL.toString();
		String errorUrl = returnURL.toString();

		String paymentUrl = paymentAppSender.getPaymentUrl(
				orderTransaction.getTransactionId(), paymentMethodId,
				themeDisplay.getLanguageId(), countryCode, backUrl, successUrl,
				errorUrl);

		actionResponse.sendRedirect(paymentUrl);
	}

	@Override
	public String render(PortletConfig portletConfig,
			RenderRequest renderRequest, RenderResponse renderResponse)
			throws Exception {

		return null;
	}

	private static Log _log = LogFactoryUtil.getLog(BtopCheckoutAction.class);
}