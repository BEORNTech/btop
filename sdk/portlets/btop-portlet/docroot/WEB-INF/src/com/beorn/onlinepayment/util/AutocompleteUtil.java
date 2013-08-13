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

package com.beorn.onlinepayment.util;

import java.util.Currency;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Sébastien Meunier
 */
public class AutocompleteUtil {

	public static Set<String> getCountryCodes() throws PortalException,
			SystemException {

		Set<String> results = new HashSet<String>();
		for (String countryCode : Locale.getISOCountries())
			results.add(countryCode);
		return results;
	}

	public static Set<String> getCurrencyCodes() throws PortalException,
			SystemException {

		Set<String> results = new HashSet<String>();
		for (Locale locale : Locale.getAvailableLocales()) {
			try {
				results.add(Currency.getInstance(locale).getCurrencyCode());

			} catch (Exception e) {
			}
		}
		return results;
	}

}