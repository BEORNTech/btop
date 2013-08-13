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

import javax.jms.Message;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.beorn.onlinepayment.model.PaymentPlugin;
import com.beorn.onlinepayment.model.PaymentPluginConfig;
import com.beorn.onlinepayment.service.PaymentPluginConfigLocalServiceUtil;
import com.beorn.onlinepayment.service.PaymentPluginLocalServiceUtil;
import com.beorn.paymentapi.messaging.messageprocessor.MessageContext;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

@PrepareForTest({ LogFactoryUtil.class, PaymentPluginLocalServiceUtil.class,
		PaymentPluginConfigLocalServiceUtil.class })
@RunWith(PowerMockRunner.class)
public class GetPaymentPluginConfigMessageProcessorTest extends PowerMockito {

	@Before
	public void setUp() {
		mockStatic(LogFactoryUtil.class);

		Log log = mock(Log.class);

		when(LogFactoryUtil.getLog(Mockito.any(Class.class))).thenReturn(log);
	}

	/**
	 * Verifies that the GetPaymentPluginConfigMessageProcessor sends an answer
	 * containing the seller's configuration for a payment plugin
	 * 
	 * @throws Exception
	 */
	@Test
	public void testProcessMessage() throws Exception {
		// Data

		String applicationId = "appID";
		long sellerId = 123;
		long paymentPluginId = 321;
		String pluginConfig = "pluginConfig";
		String config = "config";

		// Mocks

		mockStatic(PaymentPluginLocalServiceUtil.class);
		mockStatic(PaymentPluginConfigLocalServiceUtil.class);

		Message message = mock(Message.class);
		Message response = mock(Message.class);
		MessageContext messageContext = mock(MessageContext.class);
		PaymentPlugin paymentPlugin = mock(PaymentPlugin.class);
		PaymentPluginConfig paymentPluginConfig = mock(PaymentPluginConfig.class);

		when(message.getStringProperty(Mockito.eq("applicationId")))
				.thenReturn(applicationId);
		when(message.getLongProperty(Mockito.eq("sellerId"))).thenReturn(
				sellerId);
		when(
				PaymentPluginLocalServiceUtil
						.getPaymentPluginByApplicationId(Mockito
								.eq(applicationId))).thenReturn(paymentPlugin);
		when(paymentPlugin.getPaymentPluginId()).thenReturn(paymentPluginId);
		when(
				PaymentPluginConfigLocalServiceUtil
						.getPaymentPluginConfigBySellerIdAndPaymentPluginId(
								Mockito.eq(sellerId),
								Mockito.eq(paymentPluginId))).thenReturn(
				paymentPluginConfig);

		when(paymentPlugin.getPluginConfig()).thenReturn(pluginConfig);

		when(paymentPluginConfig.getConfig()).thenReturn(config);

		when(messageContext.createResponse(Mockito.eq(message))).thenReturn(
				response);

		// Test

		GetPaymentPluginConfigMessageProcessor getPaymentPluginConfigMessageProcessor = new GetPaymentPluginConfigMessageProcessor();
		getPaymentPluginConfigMessageProcessor.processMessage(message,
				messageContext);

		Mockito.verify(response).setStringProperty(Mockito.eq("pluginConfig"),
				Mockito.eq(pluginConfig));

		Mockito.verify(response).setStringProperty(Mockito.eq("sellerConfig"),
				Mockito.eq(config));

		Mockito.verify(messageContext).sendResponse(Mockito.eq(message),
				Mockito.eq(response));
	}
}
