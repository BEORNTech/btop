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

package com.beorn.onlinepayment.util;

import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;

import com.beorn.onlinepayment.context.PaymentSystemMessagingContext;
import com.beorn.paymentapi.messaging.exception.MessagingContextUnavailableException;

/**
 * Various utility methods used by the payment system
 * 
 * @author Sébastien Meunier
 */
public class PaymentSystemUtil {

	@Deprecated
	public static PaymentSystemMessagingContext getMessagingContext(
			ServletContext servletContext)
			throws MessagingContextUnavailableException {

		return getMessagingContext();
	}

	public static PaymentSystemMessagingContext getMessagingContext()
			throws MessagingContextUnavailableException {

		FutureTask<PaymentSystemMessagingContext> futurePaymentSystemMessagingContext = PaymentSystemMessagingContext
				.getInstance();

		try {
			return futurePaymentSystemMessagingContext.get(3000,
					TimeUnit.MILLISECONDS);

		} catch (Exception e) {
			throw new MessagingContextUnavailableException(e);
		}
	}

}