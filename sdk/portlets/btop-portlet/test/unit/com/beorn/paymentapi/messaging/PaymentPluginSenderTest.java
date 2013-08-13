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

import java.io.InputStream;

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
import com.beorn.paymentpluginapi.messaging.PaymentPluginSender;
import com.beorn.paymentpluginapi.util.PaymentPluginUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

@PrepareForTest({ LogFactoryUtil.class, BaseMessageSender.class,
		ApiTransaction.class, ApiPaymentMethod.class, SAXReaderUtil.class,
		PaymentPluginUtil.class, JSONFactoryUtil.class })
@RunWith(PowerMockRunner.class)
public class PaymentPluginSenderTest extends PowerMockito {

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
	 * Verifies that the PaymentPluginSender uses correct keys to send a
	 * register message
	 * 
	 * @throws Exception
	 */
	@Test
	public void testRegister() throws Exception {
		// Data

		String servletContextName = "servletContextName";
		String compactedDocumentString = "compactedDocumentString";

		// Mocks

		mockStatic(PaymentPluginUtil.class);
		mockStatic(SAXReaderUtil.class);

		ServletContext servletContext = mock(ServletContext.class);
		InputStream is = mock(InputStream.class);
		Document document = mock(Document.class);
		Message message = mock(Message.class);
		AsynchronousMessenger asynchronousMessenger = mock(AsynchronousMessenger.class);

		when(servletContext.getServletContextName()).thenReturn(
				servletContextName);
		when(
				PaymentPluginUtil.getPluginDescriptionStream(Mockito
						.eq(servletContext))).thenReturn(is);
		when(SAXReaderUtil.read(Mockito.eq(is))).thenReturn(document);
		when(document.compactString()).thenReturn(compactedDocumentString);

		when(_session.createMessage()).thenReturn(message);

		// Test

		PaymentPluginSender messageSender = spy(new PaymentPluginSender(
				servletContext));

		// when(messageSender.getSynchronousMessenger()) doesn't work here
		doReturn(asynchronousMessenger).when(messageSender)
				.getAsynchronousMessenger();

		messageSender.start();

		messageSender.register();

		Mockito.verify(message).setStringProperty(Mockito.eq("method"),
				Mockito.eq("register"));
		Mockito.verify(message).setStringProperty(Mockito.eq("data"),
				Mockito.eq(compactedDocumentString));
		Mockito.verify(asynchronousMessenger).send(Mockito.eq(message));
	}

	/**
	 * Verifies that the PaymentPluginSender uses correct keys to send a
	 * addPayment message
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddPayment() throws Exception {
		// Data

		String servletContextName = "servletContextName";
		long transactionId = 123;
		String remoteId = "456";
		double amountPaid = 789;

		// Mocks

		mockStatic(PaymentPluginUtil.class);
		mockStatic(SAXReaderUtil.class);

		ServletContext servletContext = mock(ServletContext.class);
		Message message = mock(Message.class);
		AsynchronousMessenger asynchronousMessenger = mock(AsynchronousMessenger.class);

		when(servletContext.getServletContextName()).thenReturn(
				servletContextName);

		when(_session.createMessage()).thenReturn(message);

		// Test

		PaymentPluginSender messageSender = spy(new PaymentPluginSender(
				servletContext));

		// when(messageSender.getSynchronousMessenger()) doesn't work here
		doReturn(asynchronousMessenger).when(messageSender)
				.getAsynchronousMessenger();

		messageSender.start();

		messageSender.addPayment(transactionId, remoteId, amountPaid);

		Mockito.verify(message).setStringProperty(Mockito.eq("method"),
				Mockito.eq("addPayment"));
		Mockito.verify(message).setStringProperty(Mockito.eq("applicationId"),
				Mockito.eq(servletContextName));
		Mockito.verify(message).setLongProperty(Mockito.eq("transactionId"),
				Mockito.eq(transactionId));
		Mockito.verify(message).setStringProperty(Mockito.eq("remoteId"),
				Mockito.eq(remoteId));
		Mockito.verify(message).setDoubleProperty(Mockito.eq("amountPaid"),
				Mockito.eq(amountPaid));
		Mockito.verify(asynchronousMessenger).send(Mockito.eq(message));
	}


	/**
	 * Verifies that the PaymentPluginSender uses correct keys to send a
	 * getPaymentPluginConfig message
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetPaymentPluginConfig() throws Exception {
		// Data

		String servletContextName = "servletContextName";
		long sellerId = 123;
		String jsonPluginConfig = "pluginConfig";
		String jsonSellerConfig = "sellerConfig";

		// Mocks

		mockStatic(JSONFactoryUtil.class);

		ServletContext servletContext = mock(ServletContext.class);
		Message message = mock(Message.class);
		SynchronousMessenger synchronousMessenger = mock(SynchronousMessenger.class);
		Message response = mock(Message.class);
		JSONObject configuration = mock(JSONObject.class);
		JSONObject pluginConfig = mock(JSONObject.class);
		JSONObject sellerConfig = mock(JSONObject.class);

		when(servletContext.getServletContextName()).thenReturn(
				servletContextName);
		when(_session.createMessage()).thenReturn(message);
		when(synchronousMessenger.send(Mockito.eq(message))).thenReturn(
				response);
		when(JSONFactoryUtil.createJSONObject()).thenReturn(configuration);
		when(response.getStringProperty(Mockito.eq("pluginConfig")))
				.thenReturn(jsonPluginConfig);
		when(response.getStringProperty(Mockito.eq("sellerConfig")))
				.thenReturn(jsonSellerConfig);
		when(JSONFactoryUtil.createJSONObject(Mockito.eq(jsonPluginConfig)))
				.thenReturn(pluginConfig);
		when(JSONFactoryUtil.createJSONObject(Mockito.eq(jsonSellerConfig)))
				.thenReturn(sellerConfig);

		// Test

		PaymentPluginSender messageSender = spy(new PaymentPluginSender(
				servletContext));

		// when(messageSender.getSynchronousMessenger()) doesn't work here
		doReturn(synchronousMessenger).when(messageSender)
				.getSynchronousMessenger();

		messageSender.start();

		JSONObject returnedConfiguration = messageSender
				.getPaymentPluginConfig(sellerId);

		Mockito.verify(message).setStringProperty(Mockito.eq("method"),
				Mockito.eq("getPaymentPluginConfig"));
		Mockito.verify(message).setStringProperty(Mockito.eq("applicationId"),
				Mockito.eq(servletContextName));
		Mockito.verify(message).setLongProperty(Mockito.eq("sellerId"),
				Mockito.eq(sellerId));

		Mockito.verify(configuration).put(Mockito.eq("pluginConfig"),
				Mockito.eq(pluginConfig));
		Mockito.verify(configuration).put(Mockito.eq("sellerConfig"),
				Mockito.eq(sellerConfig));

		Assert.assertEquals(configuration, returnedConfiguration);
	}

}
