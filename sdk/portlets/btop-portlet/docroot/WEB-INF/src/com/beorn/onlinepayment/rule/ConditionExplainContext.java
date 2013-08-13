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

package com.beorn.onlinepayment.rule;

/**
 * A translation context used to explain conditions by a localized string.
 * 
 * @author Sébastien Meunier
 */
public interface ConditionExplainContext {

	/**
	 * @param key
	 *            the key to translate
	 * @return the translation corresponding to a key
	 */
	String get(String key);

	/**
	 * @param key
	 *            the key to translate
	 * @param arguments
	 *            arguments to replace inside the translated string
	 * @return the translation corresponding to a key, replacing markers with
	 *         the arguments
	 */
	String format(String key, Object... arguments);
}
