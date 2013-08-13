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

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import com.beorn.onlinepayment.service.OrderTransactionLocalServiceUtil;
import com.beorn.onlinepayment.shopping.util.ShoppingUtil;
import com.beorn.paymentapi.model.ApiPaymentMethod;
import com.beorn.paymentappapi.messaging.PaymentAppSender;
import com.beorn.paymentappapi.util.PaymentAppUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.struts.BaseStrutsPortletAction;
import com.liferay.portal.kernel.struts.StrutsPortletAction;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PrefsParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portlet.shopping.model.ShoppingCart;
import com.liferay.portlet.shopping.model.ShoppingOrder;

public class BtopCheckoutAction extends BaseStrutsPortletAction {

	@Override
	public void processAction(StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
				.getAttribute(WebKeys.THEME_DISPLAY);

		long companyId = themeDisplay.getCompanyId();
		long ownerId = themeDisplay.getScopeGroupId();
		int ownerType = PortletKeys.PREFS_OWNER_TYPE_GROUP;
		long plid = PortletKeys.PREFS_PLID_SHARED;
		String portletId = PortletKeys.SHOPPING;

		PortletPreferences preferences = PortletPreferencesLocalServiceUtil
				.getPreferences(companyId, ownerId, ownerType, plid, portletId);

		String paymentType = PrefsParamUtil.getString(preferences,
				actionRequest, "paymentType");

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);
		if (cmd.equals(Constants.SAVE) && paymentType.equals("Betop")) {
			processSaveCheckoutActionWithoutForwarding(
					originalStrutsPortletAction, actionRequest, actionResponse);

			PaymentAppSender paymentAppSender = PaymentAppUtil
					.getPaymentAppSender();

			ShoppingCart cart = ShoppingUtil.getCart(actionRequest);

			ShoppingOrder order = (ShoppingOrder) actionRequest
					.getAttribute("SHOPPING_ORDER");

			long sellerId = GetterUtil.getLong(preferences.getValue("sellerId",
					"0"));
			double amount = ShoppingUtil.calculateTotal(cart.getItems(),
					order.getBillingState(), cart.getCoupon(),
					cart.getAltShipping(), cart.isInsure());
			String currencyCode = preferences.getValue("currencyId", "USD");

			Long transactionId = paymentAppSender.addTransaction(sellerId,
					amount, currencyCode);

			OrderTransactionLocalServiceUtil.addOrderTransaction(
					order.getOrderId(), transactionId);

			HttpServletRequest request = PortalUtil
					.getHttpServletRequest(actionRequest);

			String remoteIp = request.getRemoteAddr();
			String forwardedForIp = StringUtil.extractFirst(
					request.getHeader("X-Forwarded-For"),
					StringPool.COMMA_AND_SPACE);
			if (forwardedForIp != null)
				remoteIp = forwardedForIp;

			List<ApiPaymentMethod> paymentMethods = paymentAppSender
					.getPaymentMethods(transactionId,
							paymentAppSender.geolocalizeIp(remoteIp));

			Long[] paymentMethodIds = new Long[paymentMethods.size()];
			String[] paymentMethodNames = new String[paymentMethods.size()];

			for (int i = 0; i < paymentMethods.size(); ++i) {
				paymentMethodIds[i] = paymentMethods.get(i)
						.getPaymentMethodId();
				paymentMethodNames[i] = paymentMethods.get(i).getName(
						themeDisplay.getLocale());
			}

			actionRequest.setAttribute("orderId", order.getOrderId());
			actionRequest.setAttribute("paymentMethodIds", paymentMethodIds);
			actionRequest
					.setAttribute("paymentMethodNames", paymentMethodNames);

		} else {
			originalStrutsPortletAction.processAction(
					originalStrutsPortletAction, portletConfig, actionRequest,
					actionResponse);
		}
	}

	@Override
	public String render(StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest
				.getAttribute(WebKeys.THEME_DISPLAY);

		long companyId = themeDisplay.getCompanyId();
		long ownerId = themeDisplay.getScopeGroupId();
		int ownerType = PortletKeys.PREFS_OWNER_TYPE_GROUP;
		long plid = PortletKeys.PREFS_PLID_SHARED;
		String portletId = PortletKeys.SHOPPING;

		PortletPreferences preferences = PortletPreferencesLocalServiceUtil
				.getPreferences(companyId, ownerId, ownerType, plid, portletId);

		String paymentType = PrefsParamUtil.getString(preferences,
				renderRequest, "paymentType");

		String cmd = ParamUtil.getString(renderRequest, Constants.CMD);
		if (cmd.equals(Constants.SAVE) && paymentType.equals("Betop"))
			return "/portlet/shopping/btop_choose_payment_method.jsp";

		return originalStrutsPortletAction.render(originalStrutsPortletAction,
				portletConfig, renderRequest, renderResponse);
	}

	private void processSaveCheckoutActionWithoutForwarding(
			StrutsPortletAction originalStrutsPortletAction,
			ActionRequest actionRequest, ActionResponse actionResponse)
			throws NoSuchFieldException, IllegalAccessException,
			NoSuchMethodException, InvocationTargetException {

		Object realAction = getRealAction(originalStrutsPortletAction);

		callMethod(realAction, "getLatestOrder",
				new Class<?>[] { ActionRequest.class }, actionRequest);

		callMethod(realAction, "updateCart", new Class<?>[] {
				ActionRequest.class, ActionResponse.class }, actionRequest,
				actionResponse);

		callMethod(realAction, "updateLatestOrder",
				new Class<?>[] { ActionRequest.class }, actionRequest);

		callMethod(realAction, "saveLatestOrder",
				new Class<?>[] { ActionRequest.class }, actionRequest);
	}

	private Object getRealAction(StrutsPortletAction originalStrutsPortletAction)
			throws NoSuchFieldException, IllegalAccessException {

		Class<? extends StrutsPortletAction> adapterClass = originalStrutsPortletAction
				.getClass();
		Field _portletActionField = adapterClass
				.getDeclaredField("_portletAction");
		_portletActionField.setAccessible(true);

		return _portletActionField.get(originalStrutsPortletAction);
	}

	private Object callMethod(Object target, String methodName,
			Class<?>[] parameterTypes, Object... parameters)
			throws NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {

		Class<? extends Object> targetClass = target.getClass();

		Method method = null;
		while (targetClass != null && method == null) {
			try {
				method = targetClass.getDeclaredMethod(methodName,
						parameterTypes);

			} catch (NoSuchMethodException e) {
				targetClass = targetClass.getSuperclass();
			}
		}

		method.setAccessible(true);
		return method.invoke(target, parameters);
	}

	private static Log _log = LogFactoryUtil.getLog(BtopCheckoutAction.class);
}