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

package com.beorn.onlinepayment.messaging.messageprocessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.jms.Message;
import javax.servlet.ServletContext;

import com.beorn.onlinepayment.NoSuchMethodException;
import com.beorn.onlinepayment.NoSuchPluginException;
import com.beorn.onlinepayment.messaging.PaymentSystemPluginSender;
import com.beorn.onlinepayment.model.PaymentMethod;
import com.beorn.onlinepayment.model.PaymentPlugin;
import com.beorn.onlinepayment.model.PaymentPluginConfig;
import com.beorn.onlinepayment.service.PaymentMethodLocalServiceUtil;
import com.beorn.onlinepayment.service.PaymentPluginConfigLocalServiceUtil;
import com.beorn.onlinepayment.service.PaymentPluginLocalServiceUtil;
import com.beorn.onlinepayment.util.PaymentSystemUtil;
import com.beorn.onlinepayment.util.ServiceContextUtil;
import com.beorn.paymentapi.messaging.messageprocessor.MessageContext;
import com.beorn.paymentapi.messaging.messageprocessor.MessageProcessor;
import com.beorn.paymentpluginapi.config.ConfigDescriptionUtil;
import com.beorn.paymentpluginapi.exception.InvalidConfigDescriptionException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.service.ServiceContext;

/**
 * Handles registration messages from payment plugins
 * 
 * @author Sébastien Meunier
 */
public class RegisterMessageProcessor implements MessageProcessor {

	public void processMessage(Message message, MessageContext messageContext)
			throws Exception {
		String applicationId = message.getStringProperty("applicationId");
		String data = message.getStringProperty("data");

		Document document = SAXReaderUtil.read(data);
		ServletContext servletContext = messageContext.getServletContext();
		ServiceContext serviceContext = ServiceContextUtil
				.getDefaultServiceContext();

		PaymentPlugin paymentPlugin = registerPaymentPlugin(document,
				applicationId, servletContext, serviceContext);
		registerPaymentMethods(document, paymentPlugin, serviceContext);
	}

	private PaymentPlugin registerPaymentPlugin(Document document,
			String applicationId, ServletContext servletContext,
			ServiceContext serviceContext) throws PortalException,
			SystemException {

		Node nameNode = document.selectSingleNode("/plugin/name");
		Node pluginConfigParametersNode = document
				.selectSingleNode("/plugin/pluginConfig");
		Node sellerConfigParametersNode = document
				.selectSingleNode("/plugin/sellerConfig");

		rewriteConfigurationForLocalization(pluginConfigParametersNode);
		rewriteConfigurationForLocalization(sellerConfigParametersNode);

		checkConfigDescription(pluginConfigParametersNode,
				sellerConfigParametersNode);

		Map<Locale, String> nameMap = getLocalizationMap(nameNode);

		String pluginConfigParameters = null;
		String sellerConfigParameters = null;
		try {
			if (pluginConfigParametersNode != null)
				pluginConfigParameters = XML_VERSION_1_0
						+ pluginConfigParametersNode.compactString();

			if (sellerConfigParametersNode != null)
				sellerConfigParameters = XML_VERSION_1_0
						+ sellerConfigParametersNode.compactString();

		} catch (IOException e) {
			throw new SystemException(e);
		}

		PaymentPlugin paymentPlugin;
		try {
			paymentPlugin = PaymentPluginLocalServiceUtil
					.getPaymentPluginByApplicationId(applicationId);

			PaymentSystemPluginSender paymentSystemPluginSender = PaymentSystemUtil
					.getMessagingContext().getPaymentSystemPluginSender();

			boolean configured = paymentPlugin.isConfigured();
			if (Validator.isNull(pluginConfigParameters)) {
				configured = true;

			} else if (paymentPlugin.isConfigured()
					&& !paymentSystemPluginSender.validateConfig(applicationId,
							paymentPlugin.getPluginConfig(), false)) {

				configured = false;

				_log.info("Payment plugin config for plugin "
						+ paymentPlugin.getPaymentPluginId()
						+ " is now invalid.");
			}

			paymentPlugin = PaymentPluginLocalServiceUtil
					.updatePaymentPlugin(paymentPlugin.getPaymentPluginId(),
							applicationId, nameMap, pluginConfigParameters,
							sellerConfigParameters,
							paymentPlugin.getPluginConfig(), configured,
							serviceContext);

			revalidatePaymentPluginConfigs(applicationId, paymentPlugin,
					paymentSystemPluginSender, serviceContext);

		} catch (NoSuchPluginException e) {
			paymentPlugin = PaymentPluginLocalServiceUtil.addPaymentPlugin(
					serviceContext.getUserId(), applicationId, nameMap,
					pluginConfigParameters, sellerConfigParameters,
					serviceContext);
		}

		return paymentPlugin;
	}

	private void registerPaymentMethods(Document document,
			PaymentPlugin paymentPlugin, ServiceContext serviceContext)
			throws PortalException, SystemException {

		List<PaymentMethod> paymentMethods = new ArrayList<PaymentMethod>();

		List<Node> methodNodes = document.selectNodes("/plugin/methods/method");
		for (Node methodNode : methodNodes) {
			Node methodKeyNode = methodNode.selectSingleNode("key");
			String methodKey = methodKeyNode.getStringValue();

			Node methodNameNode = methodNode.selectSingleNode("name");
			Map<Locale, String> methodNameMap = getLocalizationMap(methodNameNode);

			PaymentMethod paymentMethod;
			try {
				paymentMethod = PaymentMethodLocalServiceUtil
						.getPaymentMethodByKey(methodKey);
				methodNameMap = mergeLocalizationMaps(
						paymentMethod.getNameMap(), methodNameMap);

				paymentMethod = PaymentMethodLocalServiceUtil
						.updatePaymentMethod(
								paymentMethod.getPaymentMethodId(), methodKey,
								methodNameMap, serviceContext);

			} catch (NoSuchMethodException e) {
				paymentMethod = PaymentMethodLocalServiceUtil.addPaymentMethod(
						serviceContext.getUserId(), methodKey, methodNameMap,
						serviceContext);
			}

			paymentMethods.add(paymentMethod);
		}

		PaymentPluginLocalServiceUtil.updatePaymentPluginPaymentMethods(
				paymentPlugin.getPaymentPluginId(), paymentMethods);
	}

	private void revalidatePaymentPluginConfigs(String applicationId,
			PaymentPlugin paymentPlugin,
			PaymentSystemPluginSender paymentSystemPluginSender,
			ServiceContext serviceContext) throws PortalException,
			SystemException {

		List<PaymentPluginConfig> paymentPluginConfigs = PaymentPluginConfigLocalServiceUtil
				.getPaymentPluginPaymentPluginConfigs(paymentPlugin
						.getPaymentPluginId());

		for (PaymentPluginConfig paymentPluginConfig : paymentPluginConfigs) {
			if (paymentPluginConfig.isConfigured()
					&& !paymentSystemPluginSender.validateConfig(applicationId,
							paymentPluginConfig.getConfig(), true)) {

				_log.info("Payment plugin config for seller "
						+ paymentPluginConfig.getSellerId() + " and plugin "
						+ paymentPluginConfig.getPaymentPluginId()
						+ " is now invalid.");

				PaymentPluginConfigLocalServiceUtil.updatePaymentPluginConfig(
						paymentPluginConfig.getPaymentPluginConfigId(),
						paymentPluginConfig.getSellerId(),
						paymentPluginConfig.getPaymentPluginId(),
						paymentPluginConfig.getConfig(), false, serviceContext);
			}
		}
	}

	private void rewriteConfigurationForLocalization(Node configParametersNode) {
		if (configParametersNode == null)
			return;

		List<Node> localizableNodes = configParametersNode
				.selectNodes("group/name | group/parameter/name | group/parameter/helpMessage");

		for (Node localizableNode : localizableNodes) {
			Map<Locale, String> localizationMap = getLocalizationMap(localizableNode);

			String xml = StringPool.BLANK;

			for (Entry<Locale, String> localizationMapEntry : localizationMap
					.entrySet()) {

				String key = localizableNode.getName();
				String value = localizationMapEntry.getValue();
				Locale locale = localizationMapEntry.getKey();
				String requestedLanguageId = LocaleUtil.toLanguageId(locale);

				xml = LocalizationUtil.updateLocalization(xml, key, value,
						requestedLanguageId);
			}

			for (Node childNode : localizableNode.selectNodes("*"))
				childNode.detach();

			localizableNode.setText(xml);
		}
	}

	private Map<Locale, String> getLocalizationMap(Node node) {
		List<Node> children = node.selectNodes("*");

		if (children.isEmpty())
			return LocalizationUtil.getLocalizationMap(node.getStringValue());

		Locale[] locales = LanguageUtil.getAvailableLocales();

		Map<Locale, String> map = new HashMap<Locale, String>();

		for (Locale locale : locales) {
			String languageId = LocaleUtil.toLanguageId(locale);

			Node languageNode = node.selectSingleNode(languageId);
			if (languageNode == null)
				continue;

			String localization = languageNode.getStringValue();
			if (Validator.isNull(localization))
				continue;

			map.put(locale, localization);
		}

		return map;
	}

	private Map<Locale, String> mergeLocalizationMaps(
			Map<Locale, String> oldNameMap, Map<Locale, String> newNameMap) {

		Map<Locale, String> updatedNameMap = new HashMap<Locale, String>(
				newNameMap);
		updatedNameMap.putAll(oldNameMap);

		return updatedNameMap;
	}

	private void checkConfigDescription(Node pluginConfigParametersNode,
			Node sellerConfigParametersNode)
			throws InvalidConfigDescriptionException {

		if (pluginConfigParametersNode != null)
			ConfigDescriptionUtil
					.parseConfigDescription(pluginConfigParametersNode);

		if (sellerConfigParametersNode != null)
			ConfigDescriptionUtil
					.parseConfigDescription(sellerConfigParametersNode);
	}

	private static final String XML_VERSION_1_0 = "<?xml version=\"1.0\"?>\n";
	private static Log _log = LogFactoryUtil
			.getLog(RegisterMessageProcessor.class);
}
