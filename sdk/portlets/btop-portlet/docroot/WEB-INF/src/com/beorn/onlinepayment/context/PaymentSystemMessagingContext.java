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

package com.beorn.onlinepayment.context;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import javax.jms.JMSException;
import javax.servlet.ServletContext;

import com.beorn.onlinepayment.messaging.PaymentSystemAppListener;
import com.beorn.onlinepayment.messaging.PaymentSystemAppSender;
import com.beorn.onlinepayment.messaging.PaymentSystemPluginListener;
import com.beorn.onlinepayment.messaging.PaymentSystemPluginSender;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Sébastien Meunier
 */
public class PaymentSystemMessagingContext {

	public PaymentSystemMessagingContext(ServletContext servletContext)
			throws JMSException, PortalException, SystemException {

		_paymentSystemPluginListener = new PaymentSystemPluginListener(
				servletContext);
		_paymentSystemPluginListener.start();

		_paymentSystemPluginSender = new PaymentSystemPluginSender(
				servletContext);
		_paymentSystemPluginSender.start();

		_paymentSystemAppListener = new PaymentSystemAppListener(servletContext);
		_paymentSystemAppListener.start();

		_paymentSystemAppSender = new PaymentSystemAppSender(servletContext);
		_paymentSystemAppSender.start();
	}

	public void stop() throws JMSException {
		if (_paymentSystemPluginListener != null)
			_paymentSystemPluginListener.stop();
		if (_paymentSystemPluginSender != null)
			_paymentSystemPluginSender.stop();
		if (_paymentSystemAppListener != null)
			_paymentSystemAppListener.stop();
		if (_paymentSystemAppSender != null)
			_paymentSystemAppSender.stop();
	}

	public PaymentSystemAppListener getPaymentSystemAppListener() {
		return _paymentSystemAppListener;
	}

	public PaymentSystemAppSender getPaymentSystemAppSender() {
		return _paymentSystemAppSender;
	}

	public PaymentSystemPluginListener getPaymentSystemPluginListener() {
		return _paymentSystemPluginListener;
	}

	public PaymentSystemPluginSender getPaymentSystemPluginSender() {
		return _paymentSystemPluginSender;
	}

	public static FutureTask<PaymentSystemMessagingContext> getInstance() {
		return _futurePaymentSystemMessagingContext;
	}

	protected static void startInstance(final ServletContext servletContext) {
		ExecutorService service = Executors.newSingleThreadExecutor();

		_futurePaymentSystemMessagingContext = new FutureTask<PaymentSystemMessagingContext>(
				new Callable<PaymentSystemMessagingContext>() {
					public PaymentSystemMessagingContext call()
							throws Exception {
						return new PaymentSystemMessagingContext(servletContext);
					}
				});

		service.execute(_futurePaymentSystemMessagingContext);
	}

	protected static void stopInstance() {
		try {
			if (_futurePaymentSystemMessagingContext.cancel(true)) {
				_log.warn("Cancelled AppMessageContext start");

			} else {
				_futurePaymentSystemMessagingContext.get().stop();
			}

		} catch (Exception e) {
			_log.error("Failed to stop", e);
		}
	}

	private PaymentSystemPluginListener _paymentSystemPluginListener;
	private PaymentSystemPluginSender _paymentSystemPluginSender;
	private PaymentSystemAppListener _paymentSystemAppListener;
	private PaymentSystemAppSender _paymentSystemAppSender;

	private static FutureTask<PaymentSystemMessagingContext> _futurePaymentSystemMessagingContext;

	private static Log _log = LogFactoryUtil
			.getLog(PaymentSystemMessagingContext.class);
}
