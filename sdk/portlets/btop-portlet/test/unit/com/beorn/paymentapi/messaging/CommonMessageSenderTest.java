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

import com.beorn.paymentapi.model.ApiTransaction;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

@PrepareForTest({ LogFactoryUtil.class, BaseMessageSender.class,
		ApiTransaction.class })
@RunWith(PowerMockRunner.class)
public class CommonMessageSenderTest extends PowerMockito {

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
	 * Verifies that the CommonMessageSender uses correct keys to send a
	 * getTransaction message
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetTransaction() throws Exception {
		// Data

		long transactionId = 123;
		String servletContextName = "servletContextName";
		String defaultDestination = "defaultDestination";
		String jsonTransaction = "jsonTransaction";

		// Mocks

		mockStatic(ApiTransaction.class);

		ServletContext servletContext = mock(ServletContext.class);
		Message message = mock(Message.class);
		SynchronousMessenger synchronousMessenger = mock(SynchronousMessenger.class);
		Message response = mock(Message.class);
		ApiTransaction apiTransaction = mock(ApiTransaction.class);

		when(servletContext.getServletContextName()).thenReturn(
				servletContextName);
		when(_session.createMessage()).thenReturn(message);
		when(synchronousMessenger.send(Mockito.eq(message))).thenReturn(
				response);
		when(response.getStringProperty(Mockito.eq("transaction"))).thenReturn(
				jsonTransaction);
		when(ApiTransaction.fromJSONObject(Mockito.eq(jsonTransaction)))
				.thenReturn(apiTransaction);

		// Test

		CommonMessageSender messageSender = spy(new ConcreteCommonMessageSender(
				servletContext, defaultDestination));

		// when(messageSender.getSynchronousMessenger()) doesn't work here
		doReturn(synchronousMessenger).when(messageSender)
				.getSynchronousMessenger();

		messageSender.start();

		ApiTransaction returnedTransaction = messageSender
				.getTransaction(transactionId);

		Mockito.verify(message).setStringProperty(Mockito.eq("method"),
				Mockito.eq("getTransaction"));
		Mockito.verify(message).setStringProperty(Mockito.eq("applicationId"),
				Mockito.eq(servletContextName));
		Mockito.verify(message).setLongProperty(Mockito.eq("transactionId"),
				Mockito.eq(transactionId));

		Assert.assertEquals(apiTransaction, returnedTransaction);
	}

	private static class ConcreteCommonMessageSender extends
			CommonMessageSender {

		public ConcreteCommonMessageSender(ServletContext servletContext,
				String defaultDestinationName) {

			super(servletContext, defaultDestinationName);
		}

		public ConcreteCommonMessageSender(ServletContext servletContext) {
			super(servletContext);
		}
	}
}
