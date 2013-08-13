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

/**
 * A condition that is satisfied if the subject has a parameter with the
 * specified key.
 * 
 * @author Sébastien Meunier
 */
public class HasParameterCondition implements
		Condition<ParametersConditionSubject> {

	public HasParameterCondition(String key) {
		_key = key;
	}

	public HasParameterCondition(JSONObject jsonObject) throws JSONException {
		_key = jsonObject.getString("key");
	}

	public boolean isSatisfiedBy(ParametersConditionSubject subject) {
		String parameter = subject.getParameter(_key);
		return parameter != null;
	}

	public String explain(ConditionExplainContext conditionExplainContext) {
		return conditionExplainContext.format("parameter-x-exists", _key);
	}

	public void serializeInto(JSONObject jsonObject) throws RuleException {
		try {
			jsonObject.put("key", _key);

		} catch (JSONException e) {
			throw new RuleException(e);
		}
	}

	@ConditionField(label = "parameter-exists", hint = "parameterKey")
	private String _key;
}
