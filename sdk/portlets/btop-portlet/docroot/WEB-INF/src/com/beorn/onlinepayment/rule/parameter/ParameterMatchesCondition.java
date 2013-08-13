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

import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import com.beorn.onlinepayment.rule.Condition;
import com.beorn.onlinepayment.rule.ConditionExplainContext;
import com.beorn.onlinepayment.rule.ConditionField;
import com.beorn.onlinepayment.rule.exception.RuleException;

/**
 * A condition that is satisfied if the subject has a parameter with the
 * specified key and this parameter matches the specified regex.
 * 
 * @author Sébastien Meunier
 */
public class ParameterMatchesCondition implements
		Condition<ParametersConditionSubject> {

	public ParameterMatchesCondition(String key, String regex) {
		_key = key;
		_regex = regex;
	}

	public ParameterMatchesCondition(JSONObject jsonObject)
			throws JSONException {
		_key = jsonObject.getString("key");
		_regex = jsonObject.getString("regex");
	}

	public boolean isSatisfiedBy(ParametersConditionSubject subject) {
		String parameter = subject.getParameter(_key);

		if (parameter == null)
			return false;

		if (_pattern == null)
			_pattern = Pattern.compile(_regex);

		return _pattern.matcher(parameter).matches();
	}

	public String explain(ConditionExplainContext conditionExplainContext) {
		return conditionExplainContext.format("parameter-x-matches-regex-x",
				_key, _regex);
	}

	public void serializeInto(JSONObject jsonObject) throws RuleException {
		try {
			jsonObject.put("key", _key);
			jsonObject.put("regex", _regex);

		} catch (JSONException e) {
			throw new RuleException(e);
		}
	}

	@ConditionField(label = "parameter", hint = "parameterKey")
	private String _key;
	@ConditionField(label = "matches-regex")
	private String _regex;
	private Pattern _pattern;
}
