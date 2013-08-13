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

package com.beorn.paymentappapi.context;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import javax.jms.JMSException;
import javax.servlet.ServletContext;

import com.beorn.paymentappapi.messaging.PaymentAppListener;
import com.beorn.paymentappapi.messaging.PaymentAppSender;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * Initialize the listeners and senders used by a shop plugin
 * 
 * @author Sébastien Meunier
 */
public class AppMessagingContext {

	public AppMessagingContext(ServletContext servletContext)
			throws JMSException, PortalException, SystemException {

		_paymentAppListener = new PaymentAppListener(servletContext);
		_paymentAppListener.start();

		_paymentAppSender = new PaymentAppSender(servletContext);
		_paymentAppSender.start();
	}

	public void stop() throws JMSException {
		if (_paymentAppListener != null)
			_paymentAppListener.stop();
		if (_paymentAppSender != null)
			_paymentAppSender.stop();
	}

	public PaymentAppListener getPaymentAppListener() {
		return _paymentAppListener;
	}

	public PaymentAppSender getPaymentAppSender() {
		return _paymentAppSender;
	}

	public static FutureTask<AppMessagingContext> getInstance() {
		return _futureAppMessagingContext;
	}

	protected static void startInstance(final ServletContext servletContext) {
		ExecutorService service = Executors.newSingleThreadExecutor();

		_futureAppMessagingContext = new FutureTask<AppMessagingContext>(
				new Callable<AppMessagingContext>() {
					public AppMessagingContext call() throws Exception {
						return new AppMessagingContext(servletContext);
					}
				});

		service.execute(_futureAppMessagingContext);
	}

	protected static void stopInstance() {
		try {
			if (_futureAppMessagingContext.cancel(true)) {
				_log.warn("Cancelled AppMessageContext start");

			} else {
				_futureAppMessagingContext.get().stop();
			}

		} catch (Exception e) {
			_log.error("Failed to stop", e);
		}
	}

	private PaymentAppListener _paymentAppListener;
	private PaymentAppSender _paymentAppSender;

	private static FutureTask<AppMessagingContext> _futureAppMessagingContext;

	private static Log _log = LogFactoryUtil
			.getLog(PaymentContextListener.class);
}
