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

import java.util.ArrayList;
import java.util.List;

import javax.jms.Message;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.beorn.onlinepayment.model.PaymentMethod;
import com.beorn.onlinepayment.model.Transaction;
import com.beorn.onlinepayment.service.PaymentMethodLocalServiceUtil;
import com.beorn.onlinepayment.service.TransactionLocalServiceUtil;
import com.beorn.onlinepayment.util.JSONUtil;
import com.beorn.paymentapi.messaging.messageprocessor.MessageContext;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

@PrepareForTest({ LogFactoryUtil.class, JSONUtil.class,
		TransactionLocalServiceUtil.class, PaymentMethodLocalServiceUtil.class })
@RunWith(PowerMockRunner.class)
public class GetPaymentMethodsMessageProcessorTest extends PowerMockito {

	@Before
	public void setUp() {
		mockStatic(LogFactoryUtil.class);

		Log log = mock(Log.class);

		when(LogFactoryUtil.getLog(Mockito.any(Class.class))).thenReturn(log);
	}

	/**
	 * Verifies that the GetPaymentMethodsMessageProcessor sends an answer
	 * containing available payment methods that corresponds to the
	 * transactionId parameter
	 * 
	 * @throws Exception
	 */
	@Test
	public void testProcessMessage() throws Exception {
		// Data

		String applicationId = "appID";
		long transactionId = 123;
		String countryCode = "FR";
		List<PaymentMethod> paymentMethods = new ArrayList<PaymentMethod>();
		String jsonStringPaymentMethods = "jsonStringPaymentMethods";

		// Mocks

		mockStatic(TransactionLocalServiceUtil.class);
		mockStatic(PaymentMethodLocalServiceUtil.class);
		mockStatic(JSONUtil.class);

		Message message = mock(Message.class);
		Message response = mock(Message.class);
		MessageContext messageContext = mock(MessageContext.class);
		Transaction transaction = mock(Transaction.class);
		JSONArray jsonPaymentMethods = mock(JSONArray.class);

		when(message.getStringProperty(Mockito.eq("applicationId")))
				.thenReturn(applicationId);
		when(message.getLongProperty(Mockito.eq("transactionId"))).thenReturn(
				transactionId);
		when(message.getStringProperty(Mockito.eq("countryCode"))).thenReturn(
				countryCode);
		when(
				TransactionLocalServiceUtil.getTransaction(Mockito
						.eq(transactionId))).thenReturn(transaction);
		when(
				PaymentMethodLocalServiceUtil
						.getTransactionAvailablePaymentMethods(
								Mockito.eq(transaction),
								Mockito.eq(countryCode))).thenReturn(
				paymentMethods);
		when(messageContext.createResponse(Mockito.eq(message))).thenReturn(
				response);
		when(JSONUtil.paymentMethodsToJSON(Mockito.eq(paymentMethods)))
				.thenReturn(jsonPaymentMethods);
		when(jsonPaymentMethods.toString())
				.thenReturn(jsonStringPaymentMethods);

		// Test

		GetPaymentMethodsMessageProcessor getPaymentMethodsMessageProcessor = new GetPaymentMethodsMessageProcessor();
		getPaymentMethodsMessageProcessor.processMessage(message,
				messageContext);

		Mockito.verify(response).setStringProperty(Mockito.eq("methods"),
				Mockito.eq(jsonStringPaymentMethods));

		Mockito.verify(messageContext).sendResponse(Mockito.eq(message),
				Mockito.eq(response));
	}
}
