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

package com.beorn.paymentappapi.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;

import com.beorn.paymentapi.messaging.exception.MessagingContextUnavailableException;
import com.beorn.paymentappapi.context.AppMessagingContext;
import com.beorn.paymentappapi.messaging.PaymentAppSender;
import com.beorn.paymentappapi.notification.NotificationListener;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

/**
 * Various utility methods used by shop plugins
 * 
 * @author Sébastien Meunier
 */
public class PaymentAppUtil {

	/**
	 * Retrieve the messaging context used by this shop plugin
	 * 
	 * @return this shop's messaging context
	 * @throws MessagingContextUnavailableException
	 */
	public static AppMessagingContext getAppMessagingContext()
			throws MessagingContextUnavailableException {

		FutureTask<AppMessagingContext> futureAppMessagingContext = AppMessagingContext
				.getInstance();

		try {
			return futureAppMessagingContext.get(3000, TimeUnit.MILLISECONDS);

		} catch (Exception e) {
			throw new MessagingContextUnavailableException(e);
		}
	}

	/**
	 * Retrieve the messaging context used by this shop plugin
	 * 
	 * @param servletContext
	 * @return this shop's messaging context
	 * @throws MessagingContextUnavailableException
	 */
	@Deprecated
	public static AppMessagingContext getAppMessagingContext(
			ServletContext servletContext)
			throws MessagingContextUnavailableException {

		return getAppMessagingContext();
	}

	/**
	 * Retrieve the message sender used by this shop plugin
	 * 
	 * @param servletContext
	 * @return a message sender
	 * @throws PaymentContextUnavailableException
	 */
	public static PaymentAppSender getPaymentAppSender()
			throws MessagingContextUnavailableException {

		return getAppMessagingContext().getPaymentAppSender();
	}

	/**
	 * Retrieve the message sender used by this shop plugin
	 * 
	 * @param servletContext
	 * @return a message sender
	 * @throws PaymentContextUnavailableException
	 */
	@Deprecated
	public static PaymentAppSender getPaymentAppSender(
			ServletContext servletContext)
			throws MessagingContextUnavailableException {

		return getPaymentAppSender();
	}

	/**
	 * Retrieve the notification listener as defined in this app's
	 * payment-app.xml
	 * 
	 * @param servletContext
	 *            this webapp's servlet context
	 * @return the notification listener
	 * @throws PortalException
	 * @throws SystemException
	 */
	public static NotificationListener getNotificationListener(
			ServletContext servletContext) throws PortalException,
			SystemException {

		try {
			InputStream is = getAppDescriptionStream(servletContext);

			if (is == null)
				return null;

			try {
				Document document = SAXReaderUtil.read(is);

				Node notificationListenerClassNode = document
						.selectSingleNode("/plugin/notification-listener-class");
				if (notificationListenerClassNode == null)
					return null;

				String className = notificationListenerClassNode
						.getStringValue();

				try {
					NotificationListener notificationListener = (NotificationListener) Class
							.forName(className).newInstance();

					return notificationListener;

				} catch (Exception e) {
					throw new PortalException(
							"Could not initialize notification listener for {app:"
									+ servletContext.getServletContextName()
									+ ", classname:" + className + "}", e);
				}

			} catch (Exception e) {
				throw new PortalException(e);

			} finally {
				is.close();
			}

		} catch (IOException e1) {
			throw new SystemException(e1);
		}
	}

	/**
	 * Retrieve this app's payment-plugin.xml as a string
	 * 
	 * @param servletContext
	 *            this webapp's servlet context
	 * @return a string containing the contents of this app's payment-app.xml
	 * @throws IOException
	 */
	public static String getAppDescriptionString(ServletContext servletContext)
			throws IOException {
		InputStream is = getAppDescriptionStream(servletContext);
		try {
			return new Scanner(is).useDelimiter("\\A").next();

		} catch (NoSuchElementException e) {
			return StringPool.BLANK;

		} finally {
			if (is != null)
				is.close();
		}
	}

	/**
	 * Retrieve this app's payment-app.xml as a stream
	 * 
	 * @param servletContext
	 * @return a stream with the contents of this app's payment-app.xml
	 * @throws IOException
	 */
	public static InputStream getAppDescriptionStream(
			ServletContext servletContext) throws IOException {
		InputStream is = servletContext
				.getResourceAsStream("/WEB-INF/payment-app.xml");

		if (is == null)
			_log.warn("payment-app.xml is missing");

		return is;
	}

	private static Log _log = LogFactoryUtil.getLog(PaymentAppUtil.class);
}
