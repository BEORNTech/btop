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

import java.util.Locale;

import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Sébastien Meunier
 */
public class ConfigParameter {

	public ConfigParameter(String key, String name, String type,
			String defaultValue, String helpMessage) {

		_key = key;
		_name = name;
		_type = type;
		_defaultValue = defaultValue;
		_helpMessage = helpMessage;
	}

	public String getKey() {
		return _key;
	}

	public String getName() {
		return _name;
	}

	public String getName(Locale locale) {
		if (Validator.isNull(_name))
			return StringPool.BLANK;

		return LocalizationUtil.getLocalization(_name,
				LocaleUtil.toLanguageId(locale));
	}

	public String getType() {
		return _type;
	}

	public String getDefault() {
		return _defaultValue;
	}

	public String getHelpMessage() {
		return _helpMessage;
	}

	public String getHelpMessage(Locale locale) {
		if (Validator.isNull(_helpMessage))
			return StringPool.BLANK;

		return LocalizationUtil.getLocalization(_helpMessage,
				LocaleUtil.toLanguageId(locale));
	}

	private String _key;
	private String _name;
	private String _type;
	private String _defaultValue;
	private String _helpMessage;
}
