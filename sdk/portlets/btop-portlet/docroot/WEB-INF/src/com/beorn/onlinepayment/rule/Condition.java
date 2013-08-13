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

import org.json.JSONObject;

import com.beorn.onlinepayment.rule.exception.RuleException;

/**
 * @author Sébastien Meunier
 */
public interface Condition<T> {

	/**
	 * Verifies if a subject satisfies this condition.
	 * 
	 * @param subject
	 *            the subject to test
	 * @return true if the subject satisfies the condition
	 */
	boolean isSatisfiedBy(T subject);

	/**
	 * Explains the condition by a localized string.
	 * 
	 * @see RuleUtil.explain(String json, final PortletConfig portletConfig,
	 *      final Locale locale)
	 * 
	 * @param conditionExplainContext
	 *            context used to translate the explanation
	 * @return a localized explanation for this condition
	 */
	String explain(ConditionExplainContext conditionExplainContext);

	/**
	 * Serialize this condition into a jsonObject.
	 * 
	 * @see RuleUtil.toJSONObject(Condition<T> condition)
	 * 
	 * @param jsonObject
	 *            the json object to serialize this condition into
	 * @throws RuleException
	 *             if the serialization fails
	 */
	void serializeInto(JSONObject jsonObject) throws RuleException;
}
