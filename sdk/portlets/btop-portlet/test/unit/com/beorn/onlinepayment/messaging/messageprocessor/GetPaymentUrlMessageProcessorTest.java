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

package com.beorn.onlinepayment.messaging.messageprocessor;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.jms.Message;
import javax.servlet.ServletContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.beorn.onlinepayment.NoSuchPluginException;
import com.beorn.onlinepayment.context.PaymentSystemMessagingContext;
import com.beorn.onlinepayment.messaging.PaymentSystemPluginSender;
import com.beorn.onlinepayment.model.PaymentMethod;
import com.beorn.onlinepayment.model.PaymentPlugin;
import com.beorn.onlinepayment.service.PaymentMethodLocalServiceUtil;
import com.beorn.onlinepayment.service.PaymentPluginLocalServiceUtil;
import com.beorn.onlinepayment.util.PaymentSystemUtil;
import com.beorn.paymentapi.messaging.messageprocessor.MessageContext;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

@PrepareForTest({ LogFactoryUtil.class, PaymentMethodLocalServiceUtil.class,
		PaymentPluginLocalServiceUtil.class, PaymentSystemUtil.class })
@RunWith(PowerMockRunner.class)
public class GetPaymentUrlMessageProcessorTest extends PowerMockito {

	@Before
	public void setUp() {
		mockStatic(LogFactoryUtil.class);

		Log log = mock(Log.class);

		when(LogFactoryUtil.getLog(Mockito.any(Class.class))).thenReturn(log);
	}

	/**
	 * Verifies that the GetPaymentUrlMessageProcessor throws a
	 * NoSuchPluginException when no plugin is available for a transaction and a
	 * payment method
	 * 
	 * @throws Exception
	 */
	@Test
	public void testEmptyListPlugins() throws Exception {
		// Data

		long transactionId = 123;
		long paymentMethodId = 131;
		String languageId = "fr";
		String countryCode = "FR";
		String backUrl = "http://backurl.com";
		String successUrl = "http://successurl.com";
		String errorUrl = "http://errorurl.com";

		// Mocks

		mockStatic(PaymentMethodLocalServiceUtil.class);
		mockStatic(PaymentSystemUtil.class);
		mockStatic(PaymentPluginLocalServiceUtil.class);

		Message message = mock(Message.class);
		MessageContext messageContext = mock(MessageContext.class);
		List<PaymentPlugin> paymentPlugins = mock(List.class);

		when(message.getLongProperty(Mockito.eq("transactionId"))).thenReturn(
				transactionId);
		when(message.getLongProperty(Mockito.eq("paymentMethodId")))
				.thenReturn(paymentMethodId);
		when(message.getStringProperty(Mockito.eq("languageId"))).thenReturn(
				languageId);
		when(message.getStringProperty(Mockito.eq("countryCode"))).thenReturn(
				countryCode);
		when(message.getStringProperty(Mockito.eq("backUrl"))).thenReturn(
				backUrl);
		when(message.getStringProperty(Mockito.eq("successUrl"))).thenReturn(
				successUrl);
		when(message.getStringProperty(Mockito.eq("errorUrl"))).thenReturn(
				errorUrl);
		when(
				PaymentPluginLocalServiceUtil.getAvailablePaymentPlugins(
						Mockito.eq(transactionId), Mockito.eq(paymentMethodId),
						Mockito.eq(countryCode))).thenReturn(paymentPlugins);

		when(paymentPlugins.isEmpty()).thenReturn(true);

		try {

			GetPaymentUrlMessageProcessor getPaymentUrlMessageProcessor = new GetPaymentUrlMessageProcessor();
			getPaymentUrlMessageProcessor.processMessage(message,
					messageContext);

		} catch (NoSuchPluginException e) {

			assertEquals("There is no available plugin for transaction "
					+ transactionId + " and method " + paymentMethodId,
					e.getMessage());
		}
	}

	/**
	 * Verifies that the GetPaymentUrlMessageProcessor sends an answer
	 * containing the payment plugin's payment url for a transaction and a
	 * payment method
	 * 
	 * @throws Exception
	 */
	@Test
	public void testProcessMessage() throws Exception {
		// Data

		String applicationId = "appID";
		long transactionId = 123;
		long paymentMethodId = 131;
		String languageId = "fr";
		String countryCode = "FR";
		String backUrl = "http://backurl.com";
		String successUrl = "http://successurl.com";
		String errorUrl = "http://errorurl.com";
		String paymentUrl = "http://paymenturl.com";
		String paymentMethodKey = "paypal";

		// Mocks

		mockStatic(PaymentMethodLocalServiceUtil.class);
		mockStatic(PaymentSystemUtil.class);
		mockStatic(PaymentPluginLocalServiceUtil.class);

		Message message = mock(Message.class);
		Message response = mock(Message.class);
		MessageContext messageContext = mock(MessageContext.class);
		ServletContext servletContext = mock(ServletContext.class);
		PaymentMethod paymentMethod = mock(PaymentMethod.class);
		PaymentSystemPluginSender paymentSystemPluginSender = mock(PaymentSystemPluginSender.class);
		PaymentPlugin paymentPlugin = mock(PaymentPlugin.class);
		List<PaymentPlugin> paymentPlugins = mock(List.class);
		PaymentSystemMessagingContext paymentSystemMessagingContext = mock(PaymentSystemMessagingContext.class);

		when(message.getLongProperty(Mockito.eq("transactionId"))).thenReturn(
				transactionId);
		when(message.getLongProperty(Mockito.eq("paymentMethodId")))
				.thenReturn(paymentMethodId);
		when(message.getStringProperty(Mockito.eq("languageId"))).thenReturn(
				languageId);
		when(message.getStringProperty(Mockito.eq("countryCode"))).thenReturn(
				countryCode);
		when(message.getStringProperty(Mockito.eq("backUrl"))).thenReturn(
				backUrl);
		when(message.getStringProperty(Mockito.eq("successUrl"))).thenReturn(
				successUrl);
		when(message.getStringProperty(Mockito.eq("errorUrl"))).thenReturn(
				errorUrl);

		when(
				PaymentMethodLocalServiceUtil.getPaymentMethod(Mockito
						.eq(paymentMethodId))).thenReturn(paymentMethod);
		when(
				PaymentPluginLocalServiceUtil.getAvailablePaymentPlugins(
						Mockito.eq(transactionId), Mockito.eq(paymentMethodId),
						Mockito.eq(countryCode))).thenReturn(paymentPlugins);

		when(paymentPlugins.isEmpty()).thenReturn(false);

		when(messageContext.getServletContext()).thenReturn(servletContext);

		when(PaymentSystemUtil.getMessagingContext()).thenReturn(
				paymentSystemMessagingContext);

		when(paymentSystemMessagingContext.getPaymentSystemPluginSender())
				.thenReturn(paymentSystemPluginSender);

		when(paymentPlugins.get(Mockito.eq(0))).thenReturn(paymentPlugin);

		when(paymentMethod.getKey()).thenReturn(paymentMethodKey);

		when(paymentPlugin.getApplicationId()).thenReturn(applicationId);

		when(
				paymentSystemPluginSender.getPaymentUrl(
						Mockito.eq(applicationId), Mockito.eq(transactionId),
						Mockito.eq(paymentMethodKey), Mockito.eq(languageId),
						Mockito.eq(backUrl), Mockito.eq(successUrl),
						Mockito.eq(errorUrl))).thenReturn(paymentUrl);

		when(messageContext.createResponse(Mockito.eq(message))).thenReturn(
				response);

		// Test

		GetPaymentUrlMessageProcessor getPaymentUrlMessageProcessor = new GetPaymentUrlMessageProcessor();
		getPaymentUrlMessageProcessor.processMessage(message, messageContext);

		Mockito.verify(response).setStringProperty(Mockito.eq("paymentUrl"),
				Mockito.eq(paymentUrl));

		Mockito.verify(messageContext).sendResponse(Mockito.eq(message),
				Mockito.eq(response));

	}
}
