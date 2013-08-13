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

package com.beorn.paymentappapi.messaging;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.servlet.ServletContext;

import com.beorn.paymentapi.messaging.CommonMessageSender;
import com.beorn.paymentapi.messaging.DestinationNames;
import com.beorn.paymentapi.model.ApiPaymentMethod;
import com.beorn.paymentapi.model.ApiTransaction;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;

/**
 * Contains methods that a shop plugin can send to the payment system
 * 
 * @author Sébastien Meunier
 */
public class PaymentAppSender extends CommonMessageSender {

	public static final long DEFAULT_SELLER_ID = 0;

	public PaymentAppSender(ServletContext servletContext)
			throws PortalException, SystemException {
		super(servletContext, DestinationNames.PAYMENT_SYSTEM_APPS);
	}

	/**
	 * Creates a new transaction
	 * 
	 * @param sellerId
	 *            id of a seller the will receive the money from this
	 *            transaction
	 * @param amount
	 *            the amount of money
	 * @param currencyCode
	 *            ISO 4217 currency code used by the transaction
	 * @return the transaction id of the new transaction
	 * @throws SystemException
	 * @throws PortalException
	 */
	public long addTransaction(long sellerId, double amount, String currencyCode)
			throws SystemException, PortalException {

		return addTransaction(sellerId, amount, currencyCode, null);
	}

	/**
	 * Creates a new transaction
	 * 
	 * @param sellerId
	 *            id of a seller the will receive the money from this
	 *            transaction
	 * @param amount
	 *            the amount of money
	 * @param currencyCode
	 *            ISO 4217 currency code used by the transaction
	 * @param parameters
	 *            optional parameters used by the rule system
	 * @return the transaction id of the new transaction
	 * @throws SystemException
	 * @throws PortalException
	 */
	public long addTransaction(long sellerId, double amount,
			String currencyCode, Map<String, String> parameters)
			throws SystemException, PortalException {

		try {
			ServletContext servletContext = getServletContext();

			Message message = createMessage();
			message.setStringProperty("method", "addTransaction");
			message.setStringProperty("applicationId",
					servletContext.getServletContextName());
			message.setLongProperty("sellerId", sellerId);
			message.setDoubleProperty("amount", amount);
			message.setStringProperty("currency", currencyCode);

			if (parameters != null && parameters.size() > 0) {
				JSONObject parametersObject = JSONFactoryUtil
						.createJSONObject();
				for (Entry<String, String> parameterEntry : parameters
						.entrySet())
					parametersObject.put(parameterEntry.getKey(),
							parameterEntry.getValue());

				message.setStringProperty("parameters",
						parametersObject.toString());
			}

			Message response = getSynchronousMessenger().send(message);

			return response.getLongProperty("transactionId");

		} catch (JMSException e) {
			throw new SystemException(e);
		}
	}

	/**
	 * Retrieves all the transactions created by this shop plugin
	 * 
	 * @return a list of the transactions created by this shop plugin
	 * @throws PortalException
	 * @throws SystemException
	 */
	public List<ApiTransaction> getTransactions() throws PortalException,
			SystemException {
		try {
			ServletContext servletContext = getServletContext();

			Message message = createMessage();
			message.setStringProperty("method", "getTransactions");
			message.setStringProperty("applicationId",
					servletContext.getServletContextName());

			Message response = getSynchronousMessenger().send(message);

			return ApiTransaction.fromJSONArray(response
					.getStringProperty("transactions"));

		} catch (JMSException e) {
			throw new SystemException(e);
		}
	}

	/**
	 * Retrieves a list of payment methods applicable for a transaction
	 * 
	 * @param transactionId
	 *            the id of the transaction to get the payment methods from
	 * @return a list of the payment methods applicable for that transaction
	 * @throws PortalException
	 * @throws SystemException
	 */
	public List<ApiPaymentMethod> getPaymentMethods(long transactionId)
			throws PortalException, SystemException {
		return getPaymentMethods(transactionId, null);
	}

	/**
	 * Retrieves a list of payment methods applicable for a transaction from a
	 * country
	 * 
	 * @param transactionId
	 *            the id of the transaction to get the payment methods from
	 * @param countryCode
	 *            the ISO 3166 2-letter country code from which this transaction
	 *            will be paid from
	 * @return a list of the payment methods applicable for that transaction and
	 *         country
	 * @throws PortalException
	 * @throws SystemException
	 */
	public List<ApiPaymentMethod> getPaymentMethods(long transactionId,
			String countryCode) throws PortalException, SystemException {
		try {
			ServletContext servletContext = getServletContext();

			Message message = createMessage();
			message.setStringProperty("method", "getPaymentMethods");
			message.setStringProperty("applicationId",
					servletContext.getServletContextName());
			message.setLongProperty("transactionId", transactionId);
			if (Validator.isNotNull(countryCode))
				message.setStringProperty("countryCode", countryCode);

			Message response = getSynchronousMessenger().send(message);

			return ApiPaymentMethod.fromJSONArray(response
					.getStringProperty("methods"));

		} catch (JMSException e) {
			throw new SystemException(e);
		}
	}

	/**
	 * Retrieves the url that the user must use to pay a transaction using a
	 * payment method
	 * 
	 * @param transactionId
	 *            the id of the transaction to pay
	 * @param paymentMethodId
	 *            the method used to pay that transaction
	 * @param languageId
	 *            the language in which the payment page must be displayed,
	 *            format is
	 *            "(ISO 639 language code)_(uppercase ISO 3166 2-letter country code, or UN M.49 3-digit country code)_(variant code)"
	 *            where country code and variant code are optional
	 * @param backUrl
	 *            the url to go to if the user cancels the transaction
	 * @param successUrl
	 *            the url to go to if the transaction is paid successfully
	 * @param errorUrl
	 *            the url to go to if there is an error while paying the
	 *            transaction
	 * @return an url to pay the transaction using the specified payment method
	 * @throws PortalException
	 * @throws SystemException
	 */
	public String getPaymentUrl(long transactionId, long paymentMethodId,
			String languageId, String backUrl, String successUrl,
			String errorUrl) throws PortalException, SystemException {

		return getPaymentUrl(transactionId, paymentMethodId, languageId, null,
				backUrl, successUrl, errorUrl);
	}

	/**
	 * Retrieves the url that the user must use to pay a transaction using a
	 * payment method
	 * 
	 * @param transactionId
	 *            the id of the transaction to pay
	 * @param paymentMethodId
	 *            the method used to pay that transaction
	 * @param languageId
	 *            the language in which the payment page must be displayed,
	 *            format is
	 *            "(ISO 639 language code)_(uppercase ISO 3166 2-letter country code, or UN M.49 3-digit country code)_(variant code)"
	 *            where country code and variant code are optional
	 * @param countryCode
	 *            the ISO 3166 2-letter country code from which this transaction
	 *            will be paid from
	 * @param backUrl
	 *            the url to go to if the user cancels the transaction
	 * @param successUrl
	 *            the url to go to if the transaction is paid successfully
	 * @param errorUrl
	 *            the url to go to if there is an error while paying the
	 *            transaction
	 * @return an url to pay the transaction using the specified payment method
	 * @throws PortalException
	 * @throws SystemException
	 */
	public String getPaymentUrl(long transactionId, long paymentMethodId,
			String languageId, String countryCode, String backUrl,
			String successUrl, String errorUrl) throws PortalException,
			SystemException {

		try {
			ServletContext servletContext = getServletContext();

			Message message = createMessage();
			message.setStringProperty("method", "getPaymentUrl");
			message.setStringProperty("applicationId",
					servletContext.getServletContextName());
			message.setLongProperty("transactionId", transactionId);
			message.setLongProperty("paymentMethodId", paymentMethodId);
			message.setStringProperty("languageId", languageId);
			if (Validator.isNotNull(countryCode))
				message.setStringProperty("countryCode", countryCode);
			message.setStringProperty("backUrl", backUrl);
			message.setStringProperty("successUrl", successUrl);
			message.setStringProperty("errorUrl", errorUrl);

			Message response = getSynchronousMessenger().send(message);

			return response.getStringProperty("paymentUrl");

		} catch (JMSException e) {
			throw new SystemException(e);
		}
	}

	/**
	 * 
	 * @param ip
	 *            the ip to geolocalize
	 * @return the ISO 3166 2-letter country code corresponding to this ip
	 * @throws SystemException
	 * @throws PortalException
	 */
	public String geolocalizeIp(String ip) throws SystemException,
			PortalException {
		try {
			ServletContext servletContext = getServletContext();

			Message message = createMessage();
			message.setStringProperty("method", "geolocalizeIp");
			message.setStringProperty("applicationId",
					servletContext.getServletContextName());
			message.setStringProperty("ip", ip);

			Message response = getSynchronousMessenger().send(message);

			return response.getStringProperty("countryCode");

		} catch (JMSException e) {
			throw new SystemException(e);
		}
	}

}