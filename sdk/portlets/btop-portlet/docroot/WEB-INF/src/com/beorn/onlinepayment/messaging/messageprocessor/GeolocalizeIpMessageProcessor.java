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

import com.beorn.onlinepayment.geoip.GeoIpUtil;
import com.beorn.paymentapi.messaging.messageprocessor.MessageContext;
import com.beorn.paymentapi.messaging.messageprocessor.MessageProcessor;

/**
 * Handle messages about geolocalizing an ip
 * 
 * @author Sébastien Meunier
 */
public class GeolocalizeIpMessageProcessor implements MessageProcessor {

	public void processMessage(Message message, MessageContext messageContext) throws Exception {
		String applicationId = message.getStringProperty("applicationId");
		String ip = message.getStringProperty("ip");

		// Handle request

		String countryCode = GeoIpUtil.geolocalizeIp(ip);

		// Send response

		Message response = messageContext.createResponse(message);
		response.setStringProperty("countryCode", countryCode);

		messageContext.sendResponse(message, response);
	}
}
