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
import java.util.Locale;

import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;

/**
 * A config group contains a description of the configuration parameters
 * 
 * @author Sébastien Meunier
 */
public class ConfigGroup {

	public ConfigGroup(String key, String name,
			List<ConfigParameter> configParameters, boolean isRepeatable) {

		_key = key;
		_name = name;
		_configParameters = new UnmodifiableList<ConfigParameter>(
				configParameters);
		_isRepeatable = isRepeatable;
	}

	public String getKey() {
		return _key;
	}

	public String getName() {
		return _name;
	}

	public String getName(Locale locale) {
		return LocalizationUtil.getLocalization(_name,
				LocaleUtil.toLanguageId(locale));
	}

	public boolean isRepeatable() {
		return _isRepeatable;
	}

	public List<ConfigParameter> getConfigParameters() {
		return _configParameters;
	}

	private String _key;
	private String _name;
	private List<ConfigParameter> _configParameters;
	private boolean _isRepeatable;
}
