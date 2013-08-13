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

package com.beorn.paymentpluginapi.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;

import com.beorn.paymentapi.messaging.exception.MessagingContextUnavailableException;
import com.beorn.paymentpluginapi.config.ConfigDescription;
import com.beorn.paymentpluginapi.config.ConfigDescriptionUtil;
import com.beorn.paymentpluginapi.context.PluginMessagingContext;
import com.beorn.paymentpluginapi.messaging.PaymentPluginSender;
import com.beorn.paymentpluginapi.method.MethodHandler;
import com.beorn.paymentpluginapi.validator.ConfigValidator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

/**
 * Various utility methods used by payment plugins
 * 
 * @author Sébastien Meunier
 */
public class PaymentPluginUtil {

	/**
	 * Retrieve the messaging context used by this payment plugin
	 * 
	 * @return this payment plugin's messaging context
	 * @throws MessagingContextUnavailableException
	 */
	public static PluginMessagingContext getPluginMessagingContext()
			throws MessagingContextUnavailableException {

		FutureTask<PluginMessagingContext> futurePluginMessagingContext = PluginMessagingContext
				.getInstance();

		try {
			return futurePluginMessagingContext
					.get(3000, TimeUnit.MILLISECONDS);

		} catch (Exception e) {
			throw new MessagingContextUnavailableException(e);
		}
	}

	/**
	 * Retrieve the messaging context used by this payment plugin
	 * 
	 * @param servletContext
	 *            this webapp's servlet context
	 * @return this payment plugin's messaging context
	 * @throws MessagingContextUnavailableException
	 */
	@Deprecated
	public static PluginMessagingContext getPluginMessagingContext(
			ServletContext servletContext)
			throws MessagingContextUnavailableException {

		return getPluginMessagingContext();
	}

	/**
	 * Retrieve the message sender used by this payment plugin
	 * 
	 * @return this payment plugin's message sender
	 * @throws MessagingContextUnavailableException
	 */
	public static PaymentPluginSender getPaymentPluginSender()
			throws MessagingContextUnavailableException {

		return getPluginMessagingContext().getPaymentPluginSender();
	}

	/**
	 * Retrieve the message sender used by this payment plugin
	 * 
	 * @param servletContext
	 *            this webapp's servlet context
	 * @return this payment plugin's message sender
	 * @throws MessagingContextUnavailableException
	 */
	@Deprecated
	public static PaymentPluginSender getPaymentPluginSender(
			ServletContext servletContext)
			throws MessagingContextUnavailableException {

		return getPaymentPluginSender();
	}

	/**
	 * Retrieve this plugin's name as defined in its payment-plugin.xml
	 * 
	 * @param servletContext
	 *            this webapp's servlet context
	 * @return this plugin's name
	 * @throws PortalException
	 * @throws IOException
	 */
	public static String getPluginName(ServletContext servletContext)
			throws PortalException, IOException {
		InputStream is = getPluginDescriptionStream(servletContext);
		try {
			Document document = SAXReaderUtil.read(is);

			Node nameNode = document.selectSingleNode("/plugin/name");
			return nameNode.getStringValue();

		} catch (Exception e) {
			throw new PortalException(e);

		} finally {
			is.close();
		}
	}

	/**
	 * Retrieve all the payment method handlers as defined in this plugin's
	 * payment-plugin.xml
	 * 
	 * @param servletContext
	 *            this webapp's servlet context
	 * @return the supported payment method handlers
	 * @throws PortalException
	 * @throws SystemException
	 */
	public static Map<String, MethodHandler> getPluginMethodHandlers(
			ServletContext servletContext) throws PortalException,
			SystemException {

		try {
			InputStream is = getPluginDescriptionStream(servletContext);
			try {
				Document document = SAXReaderUtil.read(is);

				Map<String, MethodHandler> methodHandlers = new HashMap<String, MethodHandler>();

				List<Node> methodNodes = document
						.selectNodes("/plugin/methods/method");
				for (Node methodNode : methodNodes) {

					Node keyNode = methodNode.selectSingleNode("key");
					Node classNameNode = methodNode.selectSingleNode("class");

					String key = keyNode.getStringValue();
					String className = classNameNode.getStringValue();

					try {
						MethodHandler methodHandler = (MethodHandler) Class
								.forName(className).newInstance();

						methodHandler.initialize(servletContext);
						methodHandlers.put(key, methodHandler);

					} catch (Exception e) {
						_log.error(
								"Could not initialize method handler for {plugin:"
										+ servletContext
												.getServletContextName()
										+ ", key:" + key + ", classname:"
										+ className + "}", e);
					}
				}

				return methodHandlers;

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
	 * Retrieve the plugin config validator as defined in this plugin's
	 * payment-plugin.xml
	 * 
	 * @param servletContext
	 *            this webapp's servlet context
	 * @return the payment method plugin validator
	 * @throws PortalException
	 * @throws SystemException
	 */
	public static ConfigValidator getPluginConfigValidator(
			ServletContext servletContext) throws PortalException,
			SystemException {

		return getConfigValidator(servletContext, "pluginConfig");
	}

	/**
	 * Retrieve the seller config validator as defined in this plugin's
	 * payment-plugin.xml
	 * 
	 * @param servletContext
	 *            this webapp's servlet context
	 * @return the payment method config validator
	 * @throws PortalException
	 * @throws SystemException
	 */
	public static ConfigValidator getSellerConfigValidator(
			ServletContext servletContext) throws PortalException,
			SystemException {

		return getConfigValidator(servletContext, "sellerConfig");
	}

	/**
	 * Retrieve the config validator as defined in this plugin's
	 * payment-plugin.xml
	 * 
	 * @param servletContext
	 *            this webapp's servlet context
	 * @param key
	 *            kind of config to retrieve
	 * @return the payment method config validator
	 * @throws PortalException
	 * @throws SystemException
	 */
	public static ConfigValidator getConfigValidator(
			ServletContext servletContext, String key) throws PortalException,
			SystemException {

		try {
			InputStream is = getPluginDescriptionStream(servletContext);

			if (is == null)
				return null;

			try {
				Document document = SAXReaderUtil.read(is);

				Node configNode = document.selectSingleNode("/plugin/" + key);
				if (configNode == null)
					return null;

				Node classNameNode = configNode
						.selectSingleNode("./validatorClass");
				if (classNameNode == null)
					return null;

				String className = classNameNode.getStringValue();

				try {
					ConfigDescription configDescription = ConfigDescriptionUtil
							.parseConfigDescription(configNode);

					ConfigValidator configValidator = (ConfigValidator) Class
							.forName(className).newInstance();
					configValidator.setConfigDescription(configDescription);

					return configValidator;

				} catch (Exception e) {
					throw new PortalException(
							"Could not initialize validator for {plugin:"
									+ servletContext.getServletContextName()
									+ ", key:" + key + ", classname:"
									+ className + "}", e);
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
	 * Retrieve this plugin's payment-plugin.xml as a string
	 * 
	 * @param servletContext
	 *            this webapp's servlet context
	 * @return a string containing the contents of this plugin's
	 *         payment-plugin.xml
	 * @throws IOException
	 */
	public static String getPluginDescriptionString(
			ServletContext servletContext) throws IOException {
		InputStream is = getPluginDescriptionStream(servletContext);
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
	 * Retrieve this plugin's payment-plugin.xml as a stream
	 * 
	 * @param servletContext
	 * @return a stream with the contents of this plugin's payment-plugin.xml
	 * @throws IOException
	 */
	public static InputStream getPluginDescriptionStream(
			ServletContext servletContext) throws IOException {
		InputStream is = servletContext
				.getResourceAsStream("/WEB-INF/payment-plugin.xml");

		if (is == null)
			_log.warn("payment-plugin.xml is missing");

		return is;
	}

	private static Log _log = LogFactoryUtil.getLog(PaymentPluginUtil.class);

}