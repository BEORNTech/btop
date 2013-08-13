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

package com.beorn.paymentpluginapi.messaging.messageprocessor;

import javax.jms.Message;

import com.beorn.paymentapi.messaging.messageprocessor.MessageContext;
import com.beorn.paymentapi.messaging.messageprocessor.MessageProcessor;
import com.beorn.paymentpluginapi.validator.ConfigValidator;

/**
 * Validate the plugin configuration
 * 
 * @author Sébastien Meunier
 */

public class ValidateConfigMessageProcessor implements MessageProcessor {

	public ValidateConfigMessageProcessor(ConfigValidator pluginConfigValidator, ConfigValidator sellerConfigValidator) {
		_pluginConfigValidator = pluginConfigValidator;
		_sellerConfigValidator = sellerConfigValidator;
	}

	public void processMessage(Message message, MessageContext messageContext) throws Exception {
		String config = message.getStringProperty("config");
		boolean isSellerConfig = message.getBooleanProperty("isSellerConfig");

		// Handle request

		boolean isValid;
		if (isSellerConfig) {
			isValid = _sellerConfigValidator == null || _sellerConfigValidator.isValid(config);

		} else {
			isValid = _pluginConfigValidator == null || _pluginConfigValidator.isValid(config);
		}

		// Send response

		Message response = messageContext.createResponse(message);
		response.setBooleanProperty("isValid", isValid);

		messageContext.sendResponse(message, response);
	}

	private ConfigValidator _pluginConfigValidator;
	private ConfigValidator _sellerConfigValidator;
}
