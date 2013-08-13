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

import java.io.IOException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.servlet.ServletContext;

import com.beorn.paymentapi.messaging.CommonMessageSender;
import com.beorn.paymentapi.messaging.DestinationNames;
import com.beorn.paymentpluginapi.util.PaymentPluginUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

/**
 * Contains methods that a payment plugin can send to the payment system
 * 
 * @author Sébastien Meunier
 */
public class PaymentPluginSender extends CommonMessageSender {

	public PaymentPluginSender(ServletContext servletContext) throws PortalException, SystemException {
		super(servletContext, DestinationNames.PAYMENT_SYSTEM_PLUGINS);
	}

	/**
	 * Register the payment plugin into the payment system
	 * 
	 * @throws PortalException
	 * @throws SystemException
	 */
	public void register() throws PortalException, SystemException {
		ServletContext servletContext = getServletContext();

		try {
			Document pluginDescriptionDocument = SAXReaderUtil.read(PaymentPluginUtil
					.getPluginDescriptionStream(servletContext));

			Message message = createMessage();
			message.setStringProperty("method", "register");
			message.setStringProperty("applicationId", servletContext.getServletContextName());
			message.setStringProperty("data", pluginDescriptionDocument.compactString());

			getAsynchronousMessenger().send(message);

		} catch (DocumentException e) {
			throw new SystemException(e);

		} catch (JMSException e) {
			throw new SystemException(e);

		} catch (IOException e) {
			throw new PortalException(e);
		}
	}

	/**
	 * Notifies the payment system of a payment made using one of the payment methods supported by
	 * this payment plugin
	 * 
	 * @param transactionId
	 *            the id of the transaction that this payment is for
	 * @param remoteId
	 *            an unique id for a payment. multiple payments with the same id will be ignored by
	 *            the payment system
	 * @param amountPaid
	 *            the amount paid to the transaction
	 * @throws PortalException
	 * @throws SystemException
	 */
	public void addPayment(long transactionId, String remoteId, double amountPaid) throws PortalException, SystemException {
		ServletContext servletContext = getServletContext();

		try {
			Message message = createMessage();
			message.setStringProperty("method", "addPayment");
			message.setStringProperty("applicationId", servletContext.getServletContextName());
			message.setLongProperty("transactionId", transactionId);
			message.setStringProperty("remoteId", remoteId);
			message.setDoubleProperty("amountPaid", amountPaid);

			getAsynchronousMessenger().send(message);

		} catch (JMSException e) {
			throw new SystemException(e);
		}
	}

	/**
	 * Retrieves a transaction by its transaction id
	 * 
	 * @param transactionId
	 *            the id of the transaction to retrieve
	 * @return a transaction matching the transaction id
	 * @throws PortalException
	 * @throws SystemException
	 */
	public JSONObject getPaymentPluginConfig(long sellerId) throws PortalException, SystemException {
		try {
			ServletContext servletContext = getServletContext();

			Message message = createMessage();
			message.setStringProperty("method", "getPaymentPluginConfig");
			message.setStringProperty("applicationId", servletContext.getServletContextName());
			message.setLongProperty("sellerId", sellerId);

			Message response = getSynchronousMessenger().send(message);

			String pluginConfig = response.getStringProperty("pluginConfig");
			String sellerConfig = response.getStringProperty("sellerConfig");

			JSONObject result = JSONFactoryUtil.createJSONObject();
			if (Validator.isNotNull(pluginConfig))
				result.put("pluginConfig", JSONFactoryUtil.createJSONObject(pluginConfig));
			if (Validator.isNotNull(sellerConfig))
				result.put("sellerConfig", JSONFactoryUtil.createJSONObject(sellerConfig));

			return result;

		} catch (JMSException e) {
			throw new SystemException(e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(PaymentPluginSender.class);

}