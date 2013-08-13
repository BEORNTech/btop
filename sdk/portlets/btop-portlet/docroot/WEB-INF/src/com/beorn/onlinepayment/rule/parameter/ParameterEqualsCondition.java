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

package com.beorn.onlinepayment.rule.parameter;

import org.json.JSONException;
import org.json.JSONObject;

import com.beorn.onlinepayment.rule.Condition;
import com.beorn.onlinepayment.rule.ConditionExplainContext;
import com.beorn.onlinepayment.rule.ConditionField;
import com.beorn.onlinepayment.rule.exception.RuleException;
import com.liferay.portal.kernel.util.StringPool;

/**
 * A condition that is satisfied if the subject has a parameter with the
 * specified key and this parameter is equal (as a string) to the specified
 * value.
 * 
 * @author Sébastien Meunier
 */
public class ParameterEqualsCondition implements
		Condition<ParametersConditionSubject> {

	public ParameterEqualsCondition(String key, String value, boolean ignoreCase) {
		_key = key;
		_value = value;
		_ignoreCase = ignoreCase;
	}

	public ParameterEqualsCondition(JSONObject jsonObject) throws JSONException {
		_key = jsonObject.getString("key");
		_value = jsonObject.getString("value");
	}

	public boolean isSatisfiedBy(ParametersConditionSubject subject) {
		String parameter = subject.getParameter(_key);

		if (parameter == null)
			return false;

		if (_ignoreCase)
			return parameter.equalsIgnoreCase(_value);

		return parameter.equals(_value);
	}

	public String explain(ConditionExplainContext conditionExplainContext) {
		return conditionExplainContext.format("parameter-x-is-equals-to-x",
				_key, _value)
				+ (_ignoreCase ? " ("
						+ conditionExplainContext.get("ignoring-case") + ")"
						: StringPool.BLANK);
	}

	public void serializeInto(JSONObject jsonObject) throws RuleException {
		try {
			jsonObject.put("key", _key);
			jsonObject.put("value", _value);

		} catch (JSONException e) {
			throw new RuleException(e);
		}
	}

	@ConditionField(label = "parameter", hint = "parameterKey")
	private String _key;
	@ConditionField(label = "is-equals-to", hint = "parameterValue")
	private String _value;
	@ConditionField(label = "ignore-case")
	private boolean _ignoreCase;
}
