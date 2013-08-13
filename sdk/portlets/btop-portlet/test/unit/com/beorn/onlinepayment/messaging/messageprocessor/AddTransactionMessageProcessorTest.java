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

import java.util.Map;

import javax.jms.Message;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.beorn.onlinepayment.model.Seller;
import com.beorn.onlinepayment.model.Transaction;
import com.beorn.onlinepayment.service.SellerLocalServiceUtil;
import com.beorn.onlinepayment.service.TransactionLocalServiceUtil;
import com.beorn.onlinepayment.util.ServiceContextUtil;
import com.beorn.paymentapi.messaging.messageprocessor.MessageContext;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.service.ServiceContext;

@PrepareForTest({ ServiceContextUtil.class, TransactionLocalServiceUtil.class,
		LogFactoryUtil.class, SellerLocalServiceUtil.class })
@RunWith(PowerMockRunner.class)
public class AddTransactionMessageProcessorTest extends PowerMockito {

	@Before
	public void setUp() {
		mockStatic(LogFactoryUtil.class);

		Log log = mock(Log.class);

		when(LogFactoryUtil.getLog(Mockito.any(Class.class))).thenReturn(log);
	}

	/**
	 * Verifies that the AddTransactionMessageProcessor adds the transaction
	 * with parameters from the received message, and sends an answer containing
	 * the id of the new transaction
	 * 
	 * @throws Exception
	 */
	@Test
	public void testProcessMessage() throws Exception {
		// Data

		String applicationId = "123";
		long sellerId = 456;
		double amount = 789;
		String currencyCode = "753";
		String parameters = "{\"parameter1\":\"value1\", \"parameter2\":\"value2\"}";
		long transactionId = 369;

		// Mocks

		mockStatic(ServiceContextUtil.class);
		mockStatic(TransactionLocalServiceUtil.class);
		mockStatic(SellerLocalServiceUtil.class);

		Message message = mock(Message.class);
		Message response = mock(Message.class);
		MessageContext messageContext = mock(MessageContext.class);
		ServiceContext serviceContext = mock(ServiceContext.class);
		Seller seller = mock(Seller.class);
		Transaction transaction = mock(Transaction.class);

		when(ServiceContextUtil.getDefaultServiceContext()).thenReturn(
				serviceContext);
		when(message.getStringProperty(Mockito.eq("applicationId")))
				.thenReturn(applicationId);
		when(message.getLongProperty(Mockito.eq("sellerId"))).thenReturn(
				sellerId);
		when(message.getDoubleProperty(Mockito.eq("amount")))
				.thenReturn(amount);
		when(message.getStringProperty(Mockito.eq("currency"))).thenReturn(
				currencyCode);

		when(message.propertyExists(Mockito.eq("parameters"))).thenReturn(true);
		when(message.getStringProperty(Mockito.eq("parameters"))).thenReturn(
				parameters);
		when(SellerLocalServiceUtil.getSeller(Mockito.eq(sellerId)))
				.thenReturn(seller);
		when(seller.getUserId()).thenReturn(sellerId);
		when(
				TransactionLocalServiceUtil.addTransaction(
						Mockito.eq(sellerId), Mockito.eq(applicationId),
						Mockito.eq(sellerId), Mockito.eq(amount),
						Mockito.eq(currencyCode),
						Mockito.anyMapOf(String.class, String.class),
						Mockito.eq(serviceContext))).thenReturn(transaction);
		when(messageContext.createResponse(Mockito.eq(message))).thenReturn(
				response);
		when(transaction.getTransactionId()).thenReturn(transactionId);

		// Test

		AddTransactionMessageProcessor addTransactionMessageProcessor = new AddTransactionMessageProcessor();
		addTransactionMessageProcessor.processMessage(message, messageContext);

		verifyStatic();
		TransactionLocalServiceUtil.addTransaction(Mockito.eq(sellerId),
				Mockito.eq(applicationId), Mockito.eq(sellerId),
				Mockito.eq(amount), Mockito.eq(currencyCode),
				Mockito.argThat(new ArgumentMatcher<Map<String, String>>() {
					@Override
					public boolean matches(Object argument) {
						if (argument == null)
							return false;

						Map<String, String> map = (Map<String, String>) argument;
						return (map.containsKey("parameter1") && map.get(
								"parameter1").equals("value1"))
								&& (map.containsKey("parameter2") && map.get(
										"parameter2").equals("value2"));
					}
				}), Mockito.eq(serviceContext));

		Mockito.verify(response).setLongProperty(Mockito.eq("transactionId"),
				Mockito.eq(transactionId));

		Mockito.verify(messageContext).sendResponse(Mockito.eq(message),
				Mockito.eq(response));
	}
}
