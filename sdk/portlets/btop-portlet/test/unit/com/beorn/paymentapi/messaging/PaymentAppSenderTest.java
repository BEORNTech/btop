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

package com.beorn.paymentapi.messaging;

import java.util.ArrayList;
import java.util.List;

import javax.jms.Connection;
import javax.jms.Message;
import javax.jms.Session;
import javax.servlet.ServletContext;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.beorn.paymentapi.model.ApiPaymentMethod;
import com.beorn.paymentapi.model.ApiTransaction;
import com.beorn.paymentappapi.messaging.PaymentAppSender;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

@PrepareForTest({ LogFactoryUtil.class, BaseMessageSender.class,
		ApiTransaction.class, ApiPaymentMethod.class })
@RunWith(PowerMockRunner.class)
public class PaymentAppSenderTest extends PowerMockito {

	private Session _session;

	@Before
	public void setUp() throws Exception {
		mockStatic(LogFactoryUtil.class);

		Log log = mock(Log.class);

		when(LogFactoryUtil.getLog(Mockito.any(Class.class))).thenReturn(log);

		ActiveMQConnectionFactory connectionFactory = mock(ActiveMQConnectionFactory.class);
		Connection connection = mock(Connection.class);

		_session = mock(Session.class);
		whenNew(ActiveMQConnectionFactory.class).withArguments(
				Mockito.anyString()).thenReturn(connectionFactory);
		when(connectionFactory.createConnection()).thenReturn(connection);
		when(connection.createSession(Mockito.anyBoolean(), Mockito.anyInt()))
				.thenReturn(_session);
	}

	/**
	 * Verifies that the PaymentAppSender uses correct keys to send a
	 * addTransaction message
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddTransaction() throws Exception {
		// Data

		String servletContextName = "servletContextName";
		long sellerId = 456;
		double amount = 789.0;
		String currencyCode = "EUR";
		long transactionId = 123;

		// Mocks

		ServletContext servletContext = mock(ServletContext.class);
		Message message = mock(Message.class);
		SynchronousMessenger synchronousMessenger = mock(SynchronousMessenger.class);
		Message response = mock(Message.class);

		when(servletContext.getServletContextName()).thenReturn(
				servletContextName);
		when(_session.createMessage()).thenReturn(message);
		when(synchronousMessenger.send(Mockito.eq(message))).thenReturn(
				response);
		when(response.getLongProperty(Mockito.eq("transactionId"))).thenReturn(
				transactionId);

		// Test

		PaymentAppSender messageSender = spy(new PaymentAppSender(
				servletContext));

		// when(messageSender.getSynchronousMessenger()) doesn't work here
		doReturn(synchronousMessenger).when(messageSender)
				.getSynchronousMessenger();

		messageSender.start();

		long returnedTransactionId = messageSender.addTransaction(sellerId,
				amount, currencyCode);

		Mockito.verify(message).setStringProperty(Mockito.eq("method"),
				Mockito.eq("addTransaction"));
		Mockito.verify(message).setStringProperty(Mockito.eq("applicationId"),
				Mockito.eq(servletContextName));
		Mockito.verify(message).setLongProperty(Mockito.eq("sellerId"),
				Mockito.eq(sellerId));
		Mockito.verify(message).setDoubleProperty(Mockito.eq("amount"),
				Mockito.eq(amount));
		Mockito.verify(message).setStringProperty(Mockito.eq("currency"),
				Mockito.eq(currencyCode));

		Assert.assertEquals(transactionId, returnedTransactionId);
	}

	/**
	 * Verifies that the PaymentAppSender uses correct keys to send a
	 * getTransactions message
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetTransactions() throws Exception {
		// Data

		String servletContextName = "servletContextName";
		String jsonTransactions = "jsonTransactions";
		List<ApiTransaction> transactions = new ArrayList<ApiTransaction>();

		// Mocks

		mockStatic(ApiTransaction.class);

		ServletContext servletContext = mock(ServletContext.class);
		Message message = mock(Message.class);
		SynchronousMessenger synchronousMessenger = mock(SynchronousMessenger.class);
		Message response = mock(Message.class);

		when(servletContext.getServletContextName()).thenReturn(
				servletContextName);
		when(_session.createMessage()).thenReturn(message);
		when(synchronousMessenger.send(Mockito.eq(message))).thenReturn(
				response);
		when(response.getStringProperty(Mockito.eq("transactions")))
				.thenReturn(jsonTransactions);
		when(ApiTransaction.fromJSONArray(Mockito.eq(jsonTransactions)))
				.thenReturn(transactions);

		// Test

		PaymentAppSender messageSender = spy(new PaymentAppSender(
				servletContext));

		// when(messageSender.getSynchronousMessenger()) doesn't work here
		doReturn(synchronousMessenger).when(messageSender)
				.getSynchronousMessenger();

		messageSender.start();

		List<ApiTransaction> returnedTransactions = messageSender
				.getTransactions();

		Mockito.verify(message).setStringProperty(Mockito.eq("method"),
				Mockito.eq("getTransactions"));
		Mockito.verify(message).setStringProperty(Mockito.eq("applicationId"),
				Mockito.eq(servletContextName));

		Assert.assertEquals(transactions, returnedTransactions);
	}

	/**
	 * Verifies that the PaymentAppSender uses correct keys to send a
	 * getPaymentMethods message
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetPaymentMethods() throws Exception {
		// Data

		long transactionId = 123;
		String servletContextName = "servletContextName";
		String jsonMethods = "jsonTransactions";
		List<ApiPaymentMethod> methods = new ArrayList<ApiPaymentMethod>();

		// Mocks

		mockStatic(ApiPaymentMethod.class);

		ServletContext servletContext = mock(ServletContext.class);
		Message message = mock(Message.class);
		SynchronousMessenger synchronousMessenger = mock(SynchronousMessenger.class);
		Message response = mock(Message.class);

		when(servletContext.getServletContextName()).thenReturn(
				servletContextName);
		when(_session.createMessage()).thenReturn(message);
		when(synchronousMessenger.send(Mockito.eq(message))).thenReturn(
				response);
		when(response.getStringProperty(Mockito.eq("methods"))).thenReturn(
				jsonMethods);
		when(ApiPaymentMethod.fromJSONArray(Mockito.eq(jsonMethods)))
				.thenReturn(methods);

		// Test

		PaymentAppSender messageSender = spy(new PaymentAppSender(
				servletContext));

		// when(messageSender.getSynchronousMessenger()) doesn't work here
		doReturn(synchronousMessenger).when(messageSender)
				.getSynchronousMessenger();

		messageSender.start();

		List<ApiPaymentMethod> returnedMethods = messageSender
				.getPaymentMethods(transactionId);

		Mockito.verify(message).setStringProperty(Mockito.eq("method"),
				Mockito.eq("getPaymentMethods"));
		Mockito.verify(message).setLongProperty(Mockito.eq("transactionId"),
				Mockito.eq(transactionId));
		Mockito.verify(message).setStringProperty(Mockito.eq("applicationId"),
				Mockito.eq(servletContextName));

		Assert.assertEquals(methods, returnedMethods);
	}

	/**
	 * Verifies that the PaymentAppSender uses correct keys to send a
	 * getPaymentUrl message
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetPaymentUrl() throws Exception {
		// Data

		long transactionId = 123;
		String servletContextName = "servletContextName";
		String paymentUrl = "http://paymenturl.com";
		long paymentMethodId = 456;
		String languageId = "FR_fr";
		String backUrl = "http://backurl.com";
		String successUrl = "http://successurl.com";
		String errorUrl = "http://errorurl.com";

		// Mocks

		ServletContext servletContext = mock(ServletContext.class);
		Message message = mock(Message.class);
		SynchronousMessenger synchronousMessenger = mock(SynchronousMessenger.class);
		Message response = mock(Message.class);

		when(servletContext.getServletContextName()).thenReturn(
				servletContextName);
		when(_session.createMessage()).thenReturn(message);
		when(synchronousMessenger.send(Mockito.eq(message))).thenReturn(
				response);
		when(response.getStringProperty(Mockito.eq("paymentUrl"))).thenReturn(
				paymentUrl);

		// Test

		PaymentAppSender messageSender = spy(new PaymentAppSender(
				servletContext));

		// when(messageSender.getSynchronousMessenger()) doesn't work here
		doReturn(synchronousMessenger).when(messageSender)
				.getSynchronousMessenger();

		messageSender.start();

		String returnedPaymentUrl = messageSender.getPaymentUrl(transactionId,
				paymentMethodId, languageId, backUrl, successUrl, errorUrl);

		Mockito.verify(message).setStringProperty(Mockito.eq("method"),
				Mockito.eq("getPaymentUrl"));
		Mockito.verify(message).setStringProperty(Mockito.eq("applicationId"),
				Mockito.eq(servletContextName));
		Mockito.verify(message).setLongProperty(Mockito.eq("transactionId"),
				Mockito.eq(transactionId));
		Mockito.verify(message).setLongProperty(Mockito.eq("paymentMethodId"),
				Mockito.eq(paymentMethodId));
		Mockito.verify(message).setStringProperty(Mockito.eq("backUrl"),
				Mockito.eq(backUrl));
		Mockito.verify(message).setStringProperty(Mockito.eq("successUrl"),
				Mockito.eq(successUrl));
		Mockito.verify(message).setStringProperty(Mockito.eq("errorUrl"),
				Mockito.eq(errorUrl));

		Assert.assertEquals(paymentUrl, returnedPaymentUrl);
	}

	/**
	 * Verifies that the PaymentAppSender uses correct keys to send a
	 * geolocalizeIp message
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGeolocalizeIp() throws Exception {
		// Data

		String servletContextName = "servletContextName";
		String ip = "1.2.3.4";
		String countryCode = "RD";

		// Mocks

		ServletContext servletContext = mock(ServletContext.class);
		Message message = mock(Message.class);
		SynchronousMessenger synchronousMessenger = mock(SynchronousMessenger.class);
		Message response = mock(Message.class);

		when(servletContext.getServletContextName()).thenReturn(
				servletContextName);
		when(_session.createMessage()).thenReturn(message);
		when(synchronousMessenger.send(Mockito.eq(message))).thenReturn(
				response);
		when(response.getStringProperty(Mockito.eq("countryCode"))).thenReturn(
				countryCode);

		// Test

		PaymentAppSender messageSender = spy(new PaymentAppSender(
				servletContext));

		// when(messageSender.getSynchronousMessenger()) doesn't work here
		doReturn(synchronousMessenger).when(messageSender)
				.getSynchronousMessenger();

		messageSender.start();

		String returnedCountryCode = messageSender.geolocalizeIp(ip);

		Mockito.verify(message).setStringProperty(Mockito.eq("method"),
				Mockito.eq("geolocalizeIp"));
		Mockito.verify(message).setStringProperty(Mockito.eq("applicationId"),
				Mockito.eq(servletContextName));
		Mockito.verify(message).setStringProperty(Mockito.eq("ip"),
				Mockito.eq(ip));

		Assert.assertEquals(countryCode, returnedCountryCode);
	}
}
