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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.beorn.onlinepayment.rule.exception.RuleException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

/**
 * Base class for composite conditions (and, or, ...).
 * 
 * @author Sébastien Meunier
 */
public abstract class CompositeCondition<T> implements Condition<T> {

	public CompositeCondition(Condition<T>... conditions) {
		_conditions = conditions;
	}

	@SuppressWarnings("unchecked")
	public CompositeCondition(JSONObject jsonObject) throws JSONException,
			RuleException {

		JSONArray conditions = jsonObject.getJSONArray("conditions");
		_conditions = new Condition[conditions.length()];
		for (int i = 0; i < conditions.length(); ++i) {

			_conditions[i] = RuleUtil.fromJSONObject(conditions
					.getJSONObject(i));
		}
	}

	public abstract boolean isSatisfiedBy(T subject);

	public String explain(ConditionExplainContext conditionExplainContext) {
		if (_conditions.length == 0)
			return conditionExplainContext.get("always");

		if (_conditions.length == 1)
			return _conditions[0].explain(conditionExplainContext);

		StringBundler sb = new StringBundler(2 + (_conditions.length - 1) * 3
				+ _conditions.length);
		boolean first = true;
		sb.append(StringPool.OPEN_PARENTHESIS);
		for (Condition<T> condition : _conditions) {
			if (!first) {
				sb.append(StringPool.SPACE);
				sb.append(getExplainOperator(conditionExplainContext));
				sb.append(StringPool.SPACE);
			}
			sb.append(condition.explain(conditionExplainContext));
			first = false;
		}
		sb.append(StringPool.CLOSE_PARENTHESIS);
		return sb.toString();
	}

	public void serializeInto(JSONObject jsonObject) throws RuleException {
		try {
			JSONArray conditions = new JSONArray();
			for (Condition<T> condition : _conditions)
				conditions.put(RuleUtil.toJSONObject(condition));

			jsonObject.put("conditions", conditions);

		} catch (Exception e) {
			throw new RuleException(e);
		}
	}

	/**
	 * @param conditionExplainContext
	 * @return the translated word used to group child conditions
	 */
	protected abstract String getExplainOperator(
			ConditionExplainContext conditionExplainContext);

	@ConditionField(label = "satisfies-conditions")
	protected Condition<T>[] _conditions;
}
