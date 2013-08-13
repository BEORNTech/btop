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
 * A condition that is satisfied if any of its sub-conditions is satisfied.
 * 
 * Will be satisfied if it doesn't contain sub-conditions.
 * 
 * @author Sébastien Meunier
 */
public class OrCondition<T> extends CompositeCondition<T> {

	public OrCondition(Condition<T>... conditions) {
		super(conditions);
	}

	public OrCondition(JSONObject jsonObject) throws RuleException,
			JSONException {
		super(jsonObject);
	}

	public boolean isSatisfiedBy(T subject) {
		for (Condition<T> condition : _conditions) {
			if (condition.isSatisfiedBy(subject))
				return true;
		}

		return _conditions.length == 0;
	}

	protected String getExplainOperator(
			ConditionExplainContext conditionExplainContext) {

		return conditionExplainContext.get("or");
	}
}
