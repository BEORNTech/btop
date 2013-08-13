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

import org.json.JSONException;
import org.json.JSONObject;

import com.beorn.onlinepayment.rule.exception.RuleException;

/**
 * A condition that is satisfied if it's sub-condition isn't.
 * 
 * @author Sébastien Meunier
 */
public class NotCondition<T> implements Condition<T> {

	public NotCondition(Condition<T> condition) {
		_condition = condition;
	}

	public NotCondition(JSONObject jsonObject) throws RuleException,
			JSONException {

		_condition = RuleUtil.fromJSONObject(jsonObject
				.getJSONObject("condition"));
	}

	public boolean isSatisfiedBy(T subject) {
		return !_condition.isSatisfiedBy(subject);
	}

	public String explain(ConditionExplainContext conditionExplainContext) {
		return conditionExplainContext.format("not-x",
				_condition.explain(conditionExplainContext));
	}

	public void serializeInto(JSONObject jsonObject) throws RuleException {
		try {
			jsonObject.put("condition", RuleUtil.toJSONObject(_condition));

		} catch (JSONException e) {
			throw new RuleException(e);
		}
	}

	@ConditionField(label = "does-not-satisfy-condition")
	protected Condition<T> _condition;
}
