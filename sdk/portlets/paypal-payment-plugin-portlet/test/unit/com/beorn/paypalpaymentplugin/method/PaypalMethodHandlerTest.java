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

package com.beorn.paypalpaymentplugin.method;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import urn.ebay.api.PayPalAPI.PayPalAPIInterfaceServiceService;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutReq;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutResponseType;
import urn.ebay.apis.eBLBaseComponents.AckCodeType;

import com.beorn.paymentapi.model.ApiTransaction;
import com.beorn.paymentpluginapi.messaging.PaymentPluginSender;
import com.beorn.paymentpluginapi.test.MethodHandlerTestBase;
import com.beorn.paymentpluginapi.util.PaymentPluginUtil;
import com.beorn.paypalpaymentplugin.service.TokenLocalServiceUtil;
import com.beorn.paypalpaymentplugin.util.PayPalConfigUtil;
import com.beorn.paypalpaymentplugin.util.PayPalUtil;
import com.beorn.paypalpaymentplugin.util.TokenStatus;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.HttpUtil;

@PrepareForTest({ HttpUtil.class, PaymentPluginUtil.class,
		PayPalConfigUtil.class, PayPalAPIInterfaceServiceService.class,
		PayPalUtil.class, TokenLocalServiceUtil.class })
@RunWith(PowerMockRunner.class)
public class PaypalMethodHandlerTest extends MethodHandlerTestBase {

	@Before
	public void setUp() {
		mockStatic(HttpUtil.class);
		when(HttpUtil.encodeURL(Mockito.anyString())).thenAnswer(
				new Answer<String>() {
					public String answer(InvocationOnMock invocationOnMock)
							throws Throwable {

						return "[encoded["
								+ (String) invocationOnMock.getArguments()[0]
								+ "]/encoded]";
					}
				});
	}

	@Test
	public void testInitialize() throws PortalException, SystemException {
		PaypalMethodHandler methodHandler = new PaypalMethodHandler();
		methodHandler.initialize(null);
	}

	@Test
	public void testGetPaymentUrl() throws Exception {

		// Data

		String domain = "http://testdomain";
		String token = "t1";
		Map<String, String> serviceConfigMap = new HashMap<String, String>();
		serviceConfigMap.put("acct1.UserName", "testUserName");
		serviceConfigMap.put("acct1.Password", "testPassword");
		serviceConfigMap.put("acct1.Signature", "testSignature");
		serviceConfigMap.put("acct1.AppId", "testAppId");
		serviceConfigMap.put("mode", "sandbox");

		long transactionId = 123;
		long sellerId = 456;
		double amount = 789;
		String currencyCode = "EUR";
		long status = 1;
		ApiTransaction transaction = new ApiTransaction(transactionId,
				sellerId, amount, currencyCode, status);
		String languageId = "B";
		String backUrl = "C";
		String successUrl = "D";
		String errorUrl = "E";

		// Mocks

		mockStatic(PaymentPluginUtil.class);
		mockStatic(PayPalConfigUtil.class);
		mockStatic(TokenLocalServiceUtil.class);

		ServletContext servletContext = mock(ServletContext.class);
		JSONObject miscSellerConfig = mock(JSONObject.class);
		JSONObject sellerConfig = mock(JSONObject.class);
		JSONObject serviceSandboxConfig = mock(JSONObject.class);
		JSONObject pluginConfig = mock(JSONObject.class);
		JSONObject config = mock(JSONObject.class);
		PaymentPluginSender paymentPluginSender = mock(PaymentPluginSender.class);
		SetExpressCheckoutResponseType setExpressCheckoutResponseType = mock(SetExpressCheckoutResponseType.class);
		PayPalAPIInterfaceServiceService payPalAPIInterfaceServiceService = mock(PayPalAPIInterfaceServiceService.class);

		when(PaymentPluginUtil.getPaymentPluginSender()).thenReturn(
				paymentPluginSender);
		when(paymentPluginSender.getPaymentPluginConfig(sellerId)).thenReturn(
				config);
		when(PayPalConfigUtil.toPaypalServiceConfig(config)).thenReturn(
				serviceConfigMap);
		whenNew(PayPalAPIInterfaceServiceService.class).withArguments(
				serviceConfigMap).thenReturn(payPalAPIInterfaceServiceService);
		when(
				payPalAPIInterfaceServiceService.setExpressCheckout(Mockito
						.any(SetExpressCheckoutReq.class))).thenReturn(
				setExpressCheckoutResponseType);
		when(setExpressCheckoutResponseType.getAck()).thenReturn(
				AckCodeType.SUCCESS);
		when(setExpressCheckoutResponseType.getToken()).thenReturn(token);
		when(config.getJSONObject("sellerConfig")).thenReturn(sellerConfig);
		when(config.getJSONObject("pluginConfig")).thenReturn(pluginConfig);
		when(sellerConfig.getJSONObject("misc")).thenReturn(miscSellerConfig);
		when(miscSellerConfig.getBoolean("useSandbox")).thenReturn(true);
		when(pluginConfig.getJSONObject("serviceSandbox")).thenReturn(
				serviceSandboxConfig);
		when(serviceSandboxConfig.getString("paymentDomain"))
				.thenReturn(domain);

		// Test

		PaypalMethodHandler methodHandler = new PaypalMethodHandler();
		methodHandler.initialize(servletContext);

		String paymentUrl = methodHandler.getPaymentUrl(transaction,
				languageId, backUrl, successUrl, errorUrl);

		verifyStatic();
		TokenLocalServiceUtil.addToken(token, transactionId,
				TokenStatus.AWAITING_PAYMENT);

		Assert.assertEquals(
				domain
						+ "/cgi-bin/webscr?cmd=_express-checkout&useraction=commit&token="
						+ token, paymentUrl);
	}

	@Test
	public void testRefusedGetPaymentUrl() throws Exception {

		// Data

		Map<String, String> serviceConfigMap = new HashMap<String, String>();
		serviceConfigMap.put("acct1.UserName", "testUserName");
		serviceConfigMap.put("acct1.Password", "testPassword");
		serviceConfigMap.put("acct1.Signature", "testSignature");
		serviceConfigMap.put("acct1.AppId", "testAppId");
		serviceConfigMap.put("mode", "sandbox");

		long transactionId = 123;
		long sellerId = 456;
		double amount = 789;
		String currencyCode = "EUR";
		long status = 1;
		ApiTransaction transaction = new ApiTransaction(transactionId,
				sellerId, amount, currencyCode, status);
		String languageId = "B";
		String backUrl = "C";
		String successUrl = "D";
		String errorUrl = "E";

		// Mocks

		mockStatic(PaymentPluginUtil.class);
		mockStatic(PayPalConfigUtil.class);
		mockStatic(TokenLocalServiceUtil.class);

		ServletContext servletContext = mock(ServletContext.class);
		JSONObject config = mock(JSONObject.class);
		PaymentPluginSender paymentPluginSender = mock(PaymentPluginSender.class);
		SetExpressCheckoutResponseType setExpressCheckoutResponseType = mock(SetExpressCheckoutResponseType.class);
		PayPalAPIInterfaceServiceService payPalAPIInterfaceServiceService = mock(PayPalAPIInterfaceServiceService.class);

		when(PaymentPluginUtil.getPaymentPluginSender()).thenReturn(
				paymentPluginSender);
		when(paymentPluginSender.getPaymentPluginConfig(sellerId)).thenReturn(
				config);
		when(PayPalConfigUtil.toPaypalServiceConfig(config)).thenReturn(
				serviceConfigMap);
		whenNew(PayPalAPIInterfaceServiceService.class).withArguments(
				serviceConfigMap).thenReturn(payPalAPIInterfaceServiceService);
		when(
				payPalAPIInterfaceServiceService.setExpressCheckout(Mockito
						.any(SetExpressCheckoutReq.class))).thenReturn(
				setExpressCheckoutResponseType);
		when(setExpressCheckoutResponseType.getAck()).thenReturn(
				AckCodeType.FAILURE);

		// Test

		PaypalMethodHandler methodHandler = new PaypalMethodHandler();
		methodHandler.initialize(servletContext);

		try {
			methodHandler.getPaymentUrl(transaction, languageId, backUrl,
					successUrl, errorUrl);

			Assert.fail("Exception expected");

		} catch (SystemException e) {
		}

		verifyStatic(Mockito.never());
		TokenLocalServiceUtil.addToken(Mockito.anyString(), Mockito.anyLong(),
				Mockito.anyLong());

		verifyStatic(Mockito.never());
		TokenLocalServiceUtil.updateToken(Mockito.anyString(),
				Mockito.anyLong(), Mockito.anyLong());
	}
}
