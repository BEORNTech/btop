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

package com.beorn.paymentpluginapi.validator;

import com.beorn.paymentpluginapi.config.ConfigDescription;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * Base implementation for a ConfigValidator, converting the configuration
 * String into a JSONObject. More validations might be added later.
 * 
 * @author Sébastien Meunier
 */
public abstract class BaseConfigValidator implements ConfigValidator {

	public void setConfigDescription(ConfigDescription configDescription) {
		_configDescription = configDescription;
	}

	public boolean isValid(String config) {
		try {
			JSONObject configObject = JSONFactoryUtil.createJSONObject(config);

			if (_configDescription != null
					&& !_configDescription.isValid(configObject))
				return false;

			return isValid(configObject);

		} catch (JSONException e) {
			_log.error(e);
			return false;
		}
	}

	protected abstract boolean isValid(JSONObject config);

	private ConfigDescription _configDescription;

	private static Log _log = LogFactoryUtil.getLog(BaseConfigValidator.class);
}
