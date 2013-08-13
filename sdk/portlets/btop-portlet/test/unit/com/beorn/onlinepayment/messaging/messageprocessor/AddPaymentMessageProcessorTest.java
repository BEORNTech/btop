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

import com.beorn.onlinepayment.service.TransactionLocalServiceUtil;
import com.beorn.onlinepayment.util.ServiceContextUtil;
import com.beorn.paymentapi.messaging.messageprocessor.MessageContext;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.service.ServiceContext;

@PrepareForTest({ ServiceContextUtil.class, TransactionLocalServiceUtil.class,
		LogFactoryUtil.class })
@RunWith(PowerMockRunner.class)
public class AddPaymentMessageProcessorTest extends PowerMockito {

	@Before
	public void setUp() {
		mockStatic(LogFactoryUtil.class);

		Log log = mock(Log.class);

		when(LogFactoryUtil.getLog(Mockito.any(Class.class))).thenReturn(log);
	}

	/**
	 * Verifies that the AddPaymentMessageProcessor adds the payment with
	 * parameters from the received message
	 * 
	 * @throws Exception
	 */
	@Test
	public void testProcessMessage() throws Exception {
		// Data

		long transactionId = 123;
		String remoteId = "456";
		String applicationId = "789";
		double amountPaid = 753;

		// Mocks

		mockStatic(ServiceContextUtil.class);
		mockStatic(TransactionLocalServiceUtil.class);

		Message message = mock(Message.class);
		MessageContext messageContext = mock(MessageContext.class);
		ServiceContext serviceContext = mock(ServiceContext.class);

		when(ServiceContextUtil.getDefaultServiceContext()).thenReturn(
				serviceContext);
		when(message.getStringProperty(Mockito.eq("applicationId")))
				.thenReturn(applicationId);
		when(message.getLongProperty(Mockito.eq("transactionId"))).thenReturn(
				transactionId);
		when(message.getStringProperty(Mockito.eq("remoteId"))).thenReturn(
				remoteId);
		when(message.getDoubleProperty(Mockito.eq("amountPaid"))).thenReturn(
				amountPaid);

		// Test

		AddPaymentMessageProcessor addPaymentMessageProcessor = new AddPaymentMessageProcessor();
		addPaymentMessageProcessor.processMessage(message, messageContext);

		verifyStatic();
		TransactionLocalServiceUtil.addPayment(Mockito.eq(transactionId),
				Mockito.eq(remoteId), Mockito.eq(applicationId),
				Mockito.eq(amountPaid), Mockito.eq(serviceContext));
	}
}
