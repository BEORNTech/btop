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

import java.util.HashMap;
import java.util.Map;

import com.beorn.paymentpluginapi.exception.InvalidConfigException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

public class PayPalConfigUtil {

	public static Map<String, String> toPaypalServiceConfig(JSONObject config)
			throws InvalidConfigException {
		Map<String, String> configurationMap = new HashMap<String, String>();

		JSONObject sellerConfig = config.getJSONObject("sellerConfig");
		if (sellerConfig == null)
			throw new InvalidConfigException("No sellerConfig");

		JSONObject pluginConfig = config.getJSONObject("pluginConfig");
		if (pluginConfig == null)
			throw new InvalidConfigException("No pluginConfig");

		boolean useSandbox = sellerConfig.getJSONObject("misc").getBoolean(
				"useSandbox");

		putSellerConfig(configurationMap, sellerConfig);
		putPluginConfig(configurationMap, pluginConfig, useSandbox);

		return configurationMap;
	}

	public static boolean isPluginConfigValid(JSONObject config) {
		try {
			Map<String, String> configurationMap = new HashMap<String, String>();
			putPluginConfig(configurationMap, config, true);
			configurationMap.clear();
			putPluginConfig(configurationMap, config, false);
			return true;

		} catch (Throwable t) {
			_log.debug(t);
			return false;
		}
	}

	public static boolean isSellerConfigValid(JSONObject config) {
		try {
			Map<String, String> configurationMap = new HashMap<String, String>();
			putSellerConfig(configurationMap, config);
			return true;

		} catch (Throwable t) {
			_log.debug(t);
			return false;
		}
	}

	private static void putSellerConfig(Map<String, String> configurationMap,
			JSONObject sellerConfig) throws InvalidConfigException {

		putPropertiesFromGroup(configurationMap, sellerConfig, "account",
				CONFIG_PROPERTIES_SELLER_ACCOUNT);
	}

	private static void putPluginConfig(Map<String, String> configurationMap,
			JSONObject pluginConfig, boolean useSandbox)
			throws InvalidConfigException {

		putPropertiesFromGroup(configurationMap, pluginConfig, "connection",
				CONFIG_PROPERTIES_PLUGIN_CONNECTION);
		putPropertiesFromGroup(configurationMap, pluginConfig, "proxy",
				CONFIG_PROPERTIES_PLUGIN_PROXY);
		putPropertiesFromGroup(configurationMap, pluginConfig,
				useSandbox ? "serviceSandbox" : "service",
				CONFIG_PROPERTIES_PLUGIN_SERVICE);
		putPropertiesFromGroup(configurationMap, pluginConfig, "misc",
				CONFIG_PROPERTIES_PLUGIN_MISC);
	}

	private static void putPropertiesFromGroup(
			Map<String, String> configurationMap, JSONObject config,
			String groupName, String[] propertiesName)
			throws InvalidConfigException {

		if (!config.has(groupName))
			throw new InvalidConfigException("Missing group \"" + groupName
					+ "\"");

		JSONObject groupConfig = config.getJSONObject(groupName);

		for (String propertyName : propertiesName)
			putProperty(configurationMap, groupConfig, propertyName);
	}

	private static void putProperty(Map<String, String> configurationMap,
			JSONObject config, String name) throws InvalidConfigException {

		if (!config.has(name))
			throw new InvalidConfigException("Missing parameter \"" + name
					+ "\"");

		String value = config.getString(name);

		if (Validator.isNotNull(value))
			configurationMap.put(name, value);
	}

	private static final String[] CONFIG_PROPERTIES_SELLER_ACCOUNT = {
			"acct1.UserName", "acct1.Password", "acct1.Signature",
			"acct1.AppId", "acct1.Subject" };
	private static final String[] CONFIG_PROPERTIES_PLUGIN_CONNECTION = {
			"http.ConnectionTimeOut", "http.Retry", "http.ReadTimeOut",
			"http.MaxConnection", "http.IPAddress" };
	private static final String[] CONFIG_PROPERTIES_PLUGIN_PROXY = {
			"http.UseProxy", "http.ProxyPort", "http.ProxyHost",
			"http.ProxyUserName", "http.ProxyPassword" };
	private static final String[] CONFIG_PROPERTIES_PLUGIN_SERVICE = {
			"service.RedirectURL", "mode" };
	private static final String[] CONFIG_PROPERTIES_PLUGIN_MISC = {
			"http.GoogleAppEngine", "service.DevCentralURL" };

	private static Log _log = LogFactoryUtil.getLog(PayPalConfigUtil.class);
}
