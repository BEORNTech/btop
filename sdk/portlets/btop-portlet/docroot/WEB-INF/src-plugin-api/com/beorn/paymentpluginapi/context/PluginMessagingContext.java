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

package com.beorn.paymentpluginapi.context;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import javax.jms.JMSException;
import javax.servlet.ServletContext;

import com.beorn.paymentpluginapi.messaging.PaymentPluginListener;
import com.beorn.paymentpluginapi.messaging.PaymentPluginSender;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * Initialize the listeners and senders used by a payment plugin
 * 
 * @author Sébastien Meunier
 */
public class PluginMessagingContext {

	public PluginMessagingContext(ServletContext servletContext)
			throws JMSException, PortalException, SystemException {

		_paymentPluginListener = new PaymentPluginListener(servletContext);
		_paymentPluginListener.start();

		_paymentPluginSender = new PaymentPluginSender(servletContext);
		_paymentPluginSender.start();

		_paymentPluginSender.register();
	}

	public void stop() throws JMSException {
		if (_paymentPluginListener != null)
			_paymentPluginListener.stop();
		if (_paymentPluginSender != null)
			_paymentPluginSender.stop();
	}

	public PaymentPluginListener getPaymentPluginListener() {
		return _paymentPluginListener;
	}

	public PaymentPluginSender getPaymentPluginSender() {
		return _paymentPluginSender;
	}

	public static FutureTask<PluginMessagingContext> getInstance() {
		return _futurePluginMessagingContext;
	}

	protected static void startInstance(final ServletContext servletContext) {
		ExecutorService service = Executors.newSingleThreadExecutor();

		_futurePluginMessagingContext = new FutureTask<PluginMessagingContext>(
				new Callable<PluginMessagingContext>() {
					public PluginMessagingContext call() throws Exception {
						return new PluginMessagingContext(servletContext);
					}
				});

		service.execute(_futurePluginMessagingContext);
	}

	protected static void stopInstance() {
		try {
			if (_futurePluginMessagingContext.cancel(true)) {
				_log.warn("Cancelled PluginMessageContext start");

			} else {
				_futurePluginMessagingContext.get().stop();
			}

		} catch (Exception e) {
			_log.error("Failed to stop", e);
		}
	}

	private PaymentPluginListener _paymentPluginListener;
	private PaymentPluginSender _paymentPluginSender;

	private static FutureTask<PluginMessagingContext> _futurePluginMessagingContext;

	private static Log _log = LogFactoryUtil
			.getLog(PluginMessagingContext.class);
}
