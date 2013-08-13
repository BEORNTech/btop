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

package com.beorn.paymentpluginapi.config;

import java.util.ArrayList;
import java.util.List;

import com.beorn.paymentpluginapi.exception.InvalidConfigDescriptionException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

/**
 * Parses a configuration description from xml into a simpler object model
 * 
 * @author Sébastien Meunier
 */
public class ConfigDescriptionUtil {

	public static ConfigDescription parseConfigDescription(String config) throws SystemException,
			InvalidConfigDescriptionException {

		if (Validator.isNull(config))
			return new ConfigDescription(new ArrayList<ConfigGroup>(0));

		try {
			Document document = SAXReaderUtil.read(config);
			return parseConfigDescription(document.selectSingleNode("/*"));

		} catch (DocumentException e) {
			throw new SystemException(e);
		}
	}

	public static ConfigDescription parseConfigDescription(Node configNode) throws InvalidConfigDescriptionException {
		List<ConfigGroup> configGroups = parseConfigGroups(configNode);
		return new ConfigDescription(configGroups);
	}

	private static List<ConfigGroup> parseConfigGroups(Node configNode) throws InvalidConfigDescriptionException {
		List<Node> configGroupNodes = configNode.selectNodes("group");
		List<ConfigGroup> configGroups = new ArrayList<ConfigGroup>(configGroupNodes.size());

		for (Node configGroupNode : configGroupNodes) {
			String key = getRequiredValue(configGroupNode, "key");
			String name = getRequiredValue(configGroupNode, "name");
			List<ConfigParameter> configParameters = parseGroupParameters(configGroupNode);
			boolean isRepeatable = GetterUtil.getBoolean(getOptionalValue(configGroupNode, "repeatable", "false"));

			configGroups.add(new ConfigGroup(key, name, configParameters, isRepeatable));
		}
		return configGroups;
	}

	private static List<ConfigParameter> parseGroupParameters(Node configGroupNode) throws InvalidConfigDescriptionException {
		List<Node> configParameterNodes = configGroupNode.selectNodes("parameter");
		List<ConfigParameter> configParameters = new ArrayList<ConfigParameter>(configParameterNodes.size());

		for (Node configParameterNode : configParameterNodes) {
			String key = getRequiredValue(configParameterNode, "key");
			String name = getRequiredValue(configParameterNode, "name");
			String type = getRequiredValue(configParameterNode, "type");
			String defaultValue = getOptionalValue(configParameterNode, "default");
			String helpMessage = getOptionalValue(configParameterNode, "helpMessage");
			configParameters.add(new ConfigParameter(key, name, type, defaultValue, helpMessage));
		}
		return configParameters;
	}

	private static String getRequiredValue(Node node, String key) throws InvalidConfigDescriptionException {
		Node valueNode = node.selectSingleNode(key);
		if (valueNode == null)
			throw new InvalidConfigDescriptionException("Missing key \"" + key + "\"");

		return valueNode.getStringValue();
	}

	private static String getOptionalValue(Node node, String key) {
		return getOptionalValue(node, key, null);
	}

	private static String getOptionalValue(Node node, String key, String defaultValue) {
		Node valueNode = node.selectSingleNode(key);
		if (valueNode == null)
			return defaultValue;

		return valueNode.getStringValue();
	}

	private static Log _log = LogFactoryUtil.getLog(ConfigDescriptionUtil.class);
}
