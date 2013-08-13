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

import java.util.List;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.UnmodifiableList;

/**
 * Represents a configuration description from a shop plugin
 * 
 * @author Sébastien Meunier
 */
public class ConfigDescription {
	public ConfigDescription(List<ConfigGroup> configGroups) {
		_configGroups = new UnmodifiableList<ConfigGroup>(configGroups);
	}

	/**
	 * @return the description's config groups
	 */
	public List<ConfigGroup> getConfigGroups() {
		return _configGroups;
	}

	/**
	 * Verifies if a configuration is valid for the description
	 * 
	 * @param configObject
	 *            the configuration to check against the description
	 * @return a boolean indicating if the configuration is valid or not for the
	 *         description
	 */
	public boolean isValid(JSONObject configObject) {
		for (ConfigGroup configGroup : _configGroups) {
			if (!configObject.has(configGroup.getKey()))
				return false;

			JSONObject configObjectGroup = configObject
					.getJSONObject(configGroup.getKey());

			for (ConfigParameter configParameter : configGroup
					.getConfigParameters()) {
				if (!configObjectGroup.has(configParameter.getKey()))
					return false;

				// Extra validation can be added here (required parameters,
				// etc...)
			}
		}
		return true;
	}

	private List<ConfigGroup> _configGroups;
}
