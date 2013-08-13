
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

package com.beorn.onlinepayment.config;

import javax.portlet.PortletRequest;

import com.beorn.paymentpluginapi.config.ConfigDescription;
import com.beorn.paymentpluginapi.config.ConfigGroup;
import com.beorn.paymentpluginapi.config.ConfigParameter;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ParamUtil;

/**
 * @author Sébastien Meunier
 */
public class ConfigUtil {

	/**
	 * @param request
	 *            the request to retreive the configuration from
	 * @param configDescription
	 *            the description of the configuration to retreive
	 * @return a JSONObject corresponding to the configuration defined by a
	 *         ConfigDescription from a PortletRequest
	 */
	public static JSONObject getConfig(PortletRequest request,
			ConfigDescription configDescription) {

		JSONObject config = JSONFactoryUtil.createJSONObject();
		for (ConfigGroup configGroupDescription : configDescription
				.getConfigGroups()) {

			JSONObject configGroup = JSONFactoryUtil.createJSONObject();
			for (ConfigParameter configParameterDescription : configGroupDescription
					.getConfigParameters()) {

				String parameterName = getParameterName(
						configGroupDescription.getKey(),
						configParameterDescription.getKey());

				String parameterValue = ParamUtil.getString(request,
						parameterName, configParameterDescription.getDefault());

				configGroup.put(configParameterDescription.getKey(),
						parameterValue);
			}
			config.put(configGroupDescription.getKey(), configGroup);
		}

		return config;
	}

	/**
	 * @param groupKey
	 * @param parameterKey
	 * @return the form parameter name to use to have the getConfig method
	 *         extract the parameter from the request
	 */
	public static String getParameterName(String groupKey, String parameterKey) {
		return "config--" + groupKey + "--" + parameterKey;
	}
}
