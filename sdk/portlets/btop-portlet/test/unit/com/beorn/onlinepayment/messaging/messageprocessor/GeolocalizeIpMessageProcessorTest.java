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
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;

import com.beorn.onlinepayment.geoip.GeoIpUtil;
import com.beorn.onlinepayment.service.TransactionLocalServiceUtil;
import com.beorn.paymentapi.messaging.messageprocessor.MessageContext;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

@SuppressStaticInitializationFor("com.beorn.onlinepayment.geoip.GeoIpUtil")
@PrepareForTest({ GeoIpUtil.class, LogFactoryUtil.class })
@RunWith(PowerMockRunner.class)
public class GeolocalizeIpMessageProcessorTest extends PowerMockito {

	@Before
	public void setUp() {
		mockStatic(LogFactoryUtil.class);

		Log log = mock(Log.class);

		when(LogFactoryUtil.getLog(Mockito.any(Class.class))).thenReturn(log);
	}

	/**
	 * Verifies that the GeolocalizeIpMessageProcessor sends an answer
	 * containing the country code that corresponds to the ip parameter returned
	 * by the GeoIpUtil class
	 * 
	 * @throws Exception
	 */
	@Test
	public void testProcessMessage() throws Exception {
		// Data

		String applicationId = "appID";
		String ip = "1.2.3.4";
		String countryCode = "FR";

		// Mocks

		mockStatic(GeoIpUtil.class);
		mockStatic(TransactionLocalServiceUtil.class);

		Message message = mock(Message.class);
		Message response = mock(Message.class);
		MessageContext messageContext = mock(MessageContext.class);

		when(message.getStringProperty(Mockito.eq("applicationId")))
				.thenReturn(applicationId);
		when(message.getStringProperty(Mockito.eq("ip"))).thenReturn(ip);

		when(GeoIpUtil.geolocalizeIp(Mockito.eq(ip))).thenReturn(countryCode);

		when(messageContext.createResponse(Mockito.eq(message))).thenReturn(
				response);

		// Test

		GeolocalizeIpMessageProcessor geolocalizeIpMessageProcessor = new GeolocalizeIpMessageProcessor();
		geolocalizeIpMessageProcessor.processMessage(message, messageContext);

		Mockito.verify(response).setStringProperty(Mockito.eq("countryCode"),
				Mockito.eq(countryCode));

		Mockito.verify(messageContext).sendResponse(Mockito.eq(message),
				Mockito.eq(response));
	}
}
