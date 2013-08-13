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

package com.beorn.paypalpaymentplugin.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentReq;
import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentRequestType;
import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentResponseType;
import urn.ebay.api.PayPalAPI.PayPalAPIInterfaceServiceService;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutReq;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutRequestType;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutResponseType;
import urn.ebay.apis.CoreComponentTypes.BasicAmountType;
import urn.ebay.apis.eBLBaseComponents.AckCodeType;
import urn.ebay.apis.eBLBaseComponents.AllowedPaymentMethodType;
import urn.ebay.apis.eBLBaseComponents.CurrencyCodeType;
import urn.ebay.apis.eBLBaseComponents.DoExpressCheckoutPaymentRequestDetailsType;
import urn.ebay.apis.eBLBaseComponents.DoExpressCheckoutPaymentResponseDetailsType;
import urn.ebay.apis.eBLBaseComponents.ErrorParameterType;
import urn.ebay.apis.eBLBaseComponents.ErrorType;
import urn.ebay.apis.eBLBaseComponents.PaymentActionCodeType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsType;
import urn.ebay.apis.eBLBaseComponents.PaymentInfoType;
import urn.ebay.apis.eBLBaseComponents.PaymentStatusCodeType;
import urn.ebay.apis.eBLBaseComponents.SetExpressCheckoutRequestDetailsType;

import com.beorn.paymentapi.model.ApiTransaction;
import com.beorn.paymentpluginapi.messaging.PaymentPluginSender;
import com.beorn.paymentpluginapi.util.PaymentPluginUtil;
import com.beorn.paypalpaymentplugin.model.Token;
import com.beorn.paypalpaymentplugin.service.TokenLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringBundler;

public class PayPalUtil {

	public static PayPalAPIInterfaceServiceService getPaypalService(
			JSONObject config) throws SystemException, PortalException {

		Map<String, String> configurationMap = PayPalConfigUtil
				.toPaypalServiceConfig(config);
		return new PayPalAPIInterfaceServiceService(configurationMap);
	}

	public static String getPaymentUrl(ServletContext servletContext,
			ApiTransaction transaction, String languageId, String backUrl,
			String successUrl, String errorUrl) throws SystemException,
			PortalException {

		PaymentPluginSender paymentPluginSender = PaymentPluginUtil
				.getPaymentPluginSender();
		JSONObject config = paymentPluginSender
				.getPaymentPluginConfig(transaction.getSellerId());

		PayPalAPIInterfaceServiceService paypalService = getPaypalService(config);

		List<PaymentDetailsType> paymentDetails = getPaymentDetailsTypes(transaction);

		String localeCode = LocaleUtil.fromLanguageId(languageId).getCountry();

		SetExpressCheckoutRequestDetailsType requestDetails = new SetExpressCheckoutRequestDetailsType();
		requestDetails.setReturnURL(getReturnUrl(servletContext, successUrl,
				errorUrl));
		requestDetails.setCancelURL(backUrl);
		requestDetails.setPaymentDetails(paymentDetails);
		requestDetails.setLocaleCode(localeCode);
		requestDetails.setAllowNote("0"); // No buyer note
		requestDetails.setNoShipping("1"); // No shipping address fields

		SetExpressCheckoutRequestType requestType = new SetExpressCheckoutRequestType();
		requestType.setSetExpressCheckoutRequestDetails(requestDetails);

		SetExpressCheckoutReq request = new SetExpressCheckoutReq();
		request.setSetExpressCheckoutRequest(requestType);

		SetExpressCheckoutResponseType response;
		try {
			response = paypalService.setExpressCheckout(request);

		} catch (Exception e) {
			throw new SystemException(e);
		}

		boolean success = response.getAck().equals(AckCodeType.SUCCESS);

		if (success) {
			String tokenId = response.getToken();

			JSONObject sellerConfig = config.getJSONObject("sellerConfig");
			JSONObject pluginConfig = config.getJSONObject("pluginConfig");
			boolean useSandbox = sellerConfig.getJSONObject("misc").getBoolean(
					"useSandbox");
			String paymentDomain = pluginConfig.getJSONObject(
					useSandbox ? "serviceSandbox" : "service").getString(
					"paymentDomain");

			TokenLocalServiceUtil.addToken(tokenId,
					transaction.getTransactionId(),
					TokenStatus.AWAITING_PAYMENT);

			return paymentDomain
					+ "/cgi-bin/webscr?cmd=_express-checkout&useraction=commit&token="
					+ tokenId;

		} else {
			List<ErrorType> errorTypes = response.getErrors();
			throw new SystemException(PayPalUtil.toString(response.getAck(),
					errorTypes));
		}
	}

	public static void confirmTransaction(ServletContext servletContext,
			String tokenId, String payerID, String successUrl, String errorUrl)
			throws SystemException, PortalException {

		Token token = TokenLocalServiceUtil.getToken(tokenId);
		long transactionId = token.getTransactionId();

		PaymentPluginSender paymentPluginSender = PaymentPluginUtil
				.getPaymentPluginSender();

		ApiTransaction transaction = paymentPluginSender
				.getTransaction(transactionId);
		JSONObject config = paymentPluginSender
				.getPaymentPluginConfig(transaction.getSellerId());

		PayPalAPIInterfaceServiceService paypalService = getPaypalService(config);

		token = TokenLocalServiceUtil.updateToken(tokenId,
				transaction.getTransactionId(), TokenStatus.MAYBE_PAID);

		List<PaymentDetailsType> paymentDetailsTypes = getPaymentDetailsTypes(transaction);

		DoExpressCheckoutPaymentRequestDetailsType requestDetails = new DoExpressCheckoutPaymentRequestDetailsType();
		requestDetails.setToken(tokenId);
		requestDetails.setPayerID(payerID);
		requestDetails.setPaymentAction(PaymentActionCodeType.SALE);
		requestDetails.setPaymentDetails(paymentDetailsTypes);

		DoExpressCheckoutPaymentRequestType requestType = new DoExpressCheckoutPaymentRequestType();
		requestType.setDoExpressCheckoutPaymentRequestDetails(requestDetails);

		DoExpressCheckoutPaymentReq request = new DoExpressCheckoutPaymentReq();
		request.setDoExpressCheckoutPaymentRequest(requestType);

		DoExpressCheckoutPaymentResponseType response;
		try {
			response = paypalService.doExpressCheckoutPayment(request);

		} catch (Exception e) {
			throw new SystemException(e);
		}

		boolean success = response.getAck().equals(AckCodeType.SUCCESS);

		if (success) {
			DoExpressCheckoutPaymentResponseDetailsType responseDetails = response
					.getDoExpressCheckoutPaymentResponseDetails();

			List<PaymentInfoType> paymentInfos = responseDetails
					.getPaymentInfo();
			for (PaymentInfoType paymentInfo : paymentInfos) {
				PaymentStatusCodeType paymentStatus = paymentInfo
						.getPaymentStatus();

				if (!paymentStatus.equals(PaymentStatusCodeType.COMPLETED)) {
					_log.warn("transaction isn't completed (" + paymentStatus
							+ ")");
					continue;
				}

				String remoteId = paymentInfo.getTransactionID();
				BasicAmountType grossAmount = paymentInfo.getGrossAmount();
				double amountPaid = Double.parseDouble(grossAmount.getValue());

				paymentPluginSender.addPayment(transaction.getTransactionId(),
						remoteId, amountPaid);
			}

			token = TokenLocalServiceUtil.updateToken(tokenId,
					transaction.getTransactionId(), TokenStatus.PAID);

		} else {
			List<ErrorType> errorTypes = response.getErrors();
			throw new SystemException(PayPalUtil.toString(response.getAck(),
					errorTypes));
		}
	}

	public static List<PaymentDetailsType> getPaymentDetailsTypes(
			ApiTransaction transaction) {
		CurrencyCodeType currencyCodeType = CurrencyCodeType
				.fromValue(transaction.getCurrencyCode());

		BasicAmountType orderTotal = new BasicAmountType(currencyCodeType,
				Double.toString(transaction.getAmount()));

		PaymentDetailsType paymentDetailsType = new PaymentDetailsType();
		paymentDetailsType.setPaymentAction(PaymentActionCodeType.SALE);
		paymentDetailsType.setOrderTotal(orderTotal);

		// If this isn't set, the confirmTransaction method can receive payments
		// in the "Pending" state, which isn't handled currently
		paymentDetailsType
				.setAllowedPaymentMethod(AllowedPaymentMethodType.INSTANTPAYMENTONLY);

		List<PaymentDetailsType> paymentDetailsTypes = new ArrayList<PaymentDetailsType>();
		paymentDetailsTypes.add(paymentDetailsType);

		return paymentDetailsTypes;
	}

	public static String getReturnUrl(ServletContext servletContext,
			String successUrl, String errorUrl) {
		String returnUrl = HttpUtil.getProtocol(successUrl) + "://"
				+ HttpUtil.getDomain(successUrl);
		returnUrl += servletContext.getContextPath() + _returnServletPath;
		returnUrl = HttpUtil.addParameter(returnUrl, "successUrl", successUrl);
		returnUrl = HttpUtil.addParameter(returnUrl, "errorUrl", errorUrl);
		return returnUrl;
	}

	public static String toString(AckCodeType ackCodeType,
			List<ErrorType> errorTypes) {
		StringBundler sb = new StringBundler(2 + errorTypes.size() * 16);
		sb.append(ackCodeType.getValue());
		sb.append("\n");
		for (ErrorType errorType : errorTypes) {
			sb.append("errorCode:");
			sb.append(errorType.getErrorCode());
			sb.append(", message:");
			sb.append(errorType.getLongMessage());
			sb.append(", parameters:[");
			List<ErrorParameterType> errorParameterTypes = errorType
					.getErrorParameters();
			for (ErrorParameterType errorParameterType : errorParameterTypes) {
				sb.append(errorParameterType.getValue());
				sb.append(", ");
			}
			sb.append("]");
			sb.append("\n");
		}

		return sb.toString();
	}

	private static final String _returnServletPath = "/return-servlet";

	private static Log _log = LogFactoryUtil.getLog(PayPalUtil.class);
}
