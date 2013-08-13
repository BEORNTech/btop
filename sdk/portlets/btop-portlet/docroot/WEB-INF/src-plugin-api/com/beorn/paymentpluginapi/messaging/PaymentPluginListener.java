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

package com.beorn.paymentpluginapi.messaging;

import javax.servlet.ServletContext;

import com.beorn.paymentapi.messaging.BaseMessageListener;
import com.beorn.paymentapi.messaging.DestinationNames;
import com.beorn.paymentapi.messaging.messageprocessor.MessageProcessor;
import com.beorn.paymentpluginapi.messaging.messageprocessor.GetPaymentUrlMessageProcessor;
import com.beorn.paymentpluginapi.messaging.messageprocessor.ValidateConfigMessageProcessor;
import com.beorn.paymentpluginapi.util.PaymentPluginUtil;
import com.beorn.paymentpluginapi.validator.ConfigValidator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * Listens to messages directed at a payment plugin
 * 
 * @author Sébastien Meunier
 */
public class PaymentPluginListener extends BaseMessageListener {

	public PaymentPluginListener(ServletContext servletContext) throws PortalException, SystemException {
		super(servletContext, DestinationNames.PAYMENT_PLUGIN_PREFIX + servletContext.getServletContextName());

		register("getPaymentUrl", new GetPaymentUrlMessageProcessor());
		register("validateConfig", getConfigValidator(servletContext));
	}

	private MessageProcessor getConfigValidator(ServletContext servletContext) throws PortalException, SystemException {
		ConfigValidator pluginConfigValidator = PaymentPluginUtil.getPluginConfigValidator(servletContext);
		ConfigValidator sellerConfigValidator = PaymentPluginUtil.getSellerConfigValidator(servletContext);

		return new ValidateConfigMessageProcessor(pluginConfigValidator, sellerConfigValidator);
	}
}
