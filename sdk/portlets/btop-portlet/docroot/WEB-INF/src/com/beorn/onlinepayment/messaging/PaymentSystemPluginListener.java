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

package com.beorn.onlinepayment.messaging;

import javax.servlet.ServletContext;

import com.beorn.onlinepayment.messaging.messageprocessor.AddPaymentMessageProcessor;
import com.beorn.onlinepayment.messaging.messageprocessor.GetPaymentPluginConfigMessageProcessor;
import com.beorn.onlinepayment.messaging.messageprocessor.GetTransactionMessageProcessor;
import com.beorn.onlinepayment.messaging.messageprocessor.RegisterMessageProcessor;
import com.beorn.paymentapi.messaging.BaseMessageListener;
import com.beorn.paymentapi.messaging.DestinationNames;

/**
 * Route messages from payment plugins to message processors
 * 
 * @author Sébastien Meunier
 */
public class PaymentSystemPluginListener extends BaseMessageListener {

	public PaymentSystemPluginListener(ServletContext servletContext) {
		super(servletContext, DestinationNames.PAYMENT_SYSTEM_PLUGINS);

		register("addPayment", new AddPaymentMessageProcessor());
		register("getPaymentPluginConfig", new GetPaymentPluginConfigMessageProcessor());
		register("getTransaction", new GetTransactionMessageProcessor());
		register("register", new RegisterMessageProcessor());
	}
}
