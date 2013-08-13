package com.beorn.paymentappapi.messaging.messageprocessor;

import javax.jms.Message;
import javax.servlet.ServletContext;

import com.beorn.paymentapi.messaging.messageprocessor.MessageContext;
import com.beorn.paymentapi.messaging.messageprocessor.MessageProcessor;
import com.beorn.paymentapi.model.ApiTransaction;
import com.beorn.paymentappapi.notification.NotificationListener;
import com.beorn.paymentappapi.util.PaymentAppUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * Message received when a transaction from this application is updated
 * 
 * @author Sébastien Meunier
 */

public class UpdateTransactionMessageProcessor implements MessageProcessor {

	public UpdateTransactionMessageProcessor(ServletContext servletContext)
			throws PortalException, SystemException {

		_notificationListener = PaymentAppUtil
				.getNotificationListener(servletContext);
	}

	public void processMessage(Message message, MessageContext messageContext)
			throws Exception {

		if (_notificationListener == null)
			return;

		String transactionJSON = message.getStringProperty("transaction");

		ApiTransaction transaction = ApiTransaction
				.fromJSONObject(transactionJSON);

		// Handle request

		_notificationListener.onTransactionUpdated(transaction);
	}

	private NotificationListener _notificationListener;
}
