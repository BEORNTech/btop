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

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

import javax.portlet.PortletConfig;

import org.json.JSONException;
import org.json.JSONObject;

import com.beorn.onlinepayment.rule.exception.RuleException;
import com.beorn.onlinepayment.rule.parameter.HasParameterCondition;
import com.beorn.onlinepayment.rule.parameter.NumberParameterEqualsCondition;
import com.beorn.onlinepayment.rule.parameter.NumberParameterLargerCondition;
import com.beorn.onlinepayment.rule.parameter.NumberParameterSmallerCondition;
import com.beorn.onlinepayment.rule.parameter.ParameterContainsCondition;
import com.beorn.onlinepayment.rule.parameter.ParameterEqualsCondition;
import com.beorn.onlinepayment.rule.parameter.ParameterListContainsCondition;
import com.beorn.onlinepayment.rule.parameter.ParameterMatchesCondition;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Sébastien Meunier
 */
public class RuleUtil {

	/**
	 * Deserialize a condition from a json string.
	 * 
	 * Shortcut to fromJSONObject(new JSONObject(json)).
	 * 
	 * @param json
	 *            the json string to deserialize the condition from
	 * @return a condition corresponding to the json string
	 * @throws RuleException
	 *             if the json string isn't valid or the condition fails to be
	 *             deserialized
	 */
	public static <T> Condition<T> fromString(String json) throws RuleException {
		try {
			return fromJSONObject(new JSONObject(json));

		} catch (JSONException e) {
			throw new RuleException(e);
		}
	}

	/**
	 * Deserialize a condition from a json object.
	 * 
	 * @param jsonObject
	 *            the json object to deserialize the condition from
	 * @return a condition corresponding to the json object
	 * @throws RuleException
	 *             if the condition fails to be deserialized
	 */
	@SuppressWarnings("unchecked")
	public static <T> Condition<T> fromJSONObject(JSONObject jsonObject)
			throws RuleException {

		try {
			String conditionClassName = jsonObject
					.getString(CONDITION_CLASS_NAME_KEY);
			Class<?> conditionClass = Class.forName(conditionClassName);

			if (!getConditionRegistry().hasConditionClass(conditionClass))
				throw new RuleException(conditionClassName
						+ " isn't registered as a condition class");

			try {
				return (Condition<T>) conditionClass.getConstructor(
						JSONObject.class).newInstance(jsonObject);

			} catch (NoSuchMethodException e) {
				return (Condition<T>) conditionClass.getConstructor()
						.newInstance();
			}

		} catch (InvocationTargetException e) {
			Throwable cause = e.getCause();
			if (cause instanceof RuleException) {
				throw (RuleException) cause;

			} else {
				throw new RuleException(cause);
			}

		} catch (RuleException e) {
			throw e;

		} catch (Exception e) {
			throw new RuleException(e);
		}
	}

	/**
	 * Serializes a condition into a json object.
	 * 
	 * @param condition
	 *            the condition to serialize
	 * @return the serialized condition
	 * @throws RuleException
	 *             if the condition contains a value that cannot be serialized
	 */
	public static <T> JSONObject toJSONObject(Condition<T> condition)
			throws RuleException {

		try {
			JSONObject jsonObject = new JSONObject();
			Class<? extends Condition> conditionClass = condition.getClass();

			jsonObject.put(CONDITION_CLASS_NAME_KEY, conditionClass.getName());
			condition.serializeInto(jsonObject);

			return jsonObject;

		} catch (Exception e) {
			throw new RuleException(e);
		}
	}

	/**
	 * Serializes a condition into a json string.
	 * 
	 * Shortcut to toJSONObject(condition).toString().
	 * 
	 * @param condition
	 *            the condition to serialize
	 * @return the serialized condition
	 * @throws RuleException
	 *             if the condition contains a value that cannot be serialized
	 */
	public static <T> String toString(Condition<T> condition)
			throws RuleException {

		return toJSONObject(condition).toString();
	}

	/**
	 * Helper method to get an localized message explaining the condition as a
	 * json string.
	 * 
	 * @param json
	 *            a json string representing the condition to explain
	 * @param portletConfig
	 *            context to find key / translation correspondences
	 * @param locale
	 *            the locale in which the explanation will be translated
	 * @return a localized string explaining the condition
	 * @throws RuleException
	 */
	public static <T> String explain(String json,
			final PortletConfig portletConfig, final Locale locale)
			throws RuleException {

		return explain(fromString(json), portletConfig, locale);
	}

	/**
	 * Helper method to get an localized message explaining the condition.
	 * 
	 * @param condition
	 *            the condition to explain
	 * @param portletConfig
	 *            context to find key / translation correspondences
	 * @param locale
	 *            the locale in which the explanation will be translated
	 * @return a localized string explaining the condition
	 * @throws RuleException
	 */
	public static <T> String explain(Condition<T> condition,
			final PortletConfig portletConfig, final Locale locale)
			throws RuleException {

		return condition.explain(new ConditionExplainContext() {
			public String get(String key) {
				return LanguageUtil.get(portletConfig, locale, key);
			}

			public String format(String key, Object... arguments) {
				return LanguageUtil.format(portletConfig, locale, key,
						arguments);
			}
		});

	}

	/**
	 * @return the registry containing all the available condition classes
	 */
	public static ConditionRegistry getConditionRegistry() {
		if (_conditionRegistry == null) {
			_conditionRegistry = new ConditionRegistry();
			for (Class<?> conditionClass : _CONDITION_CLASSES)
				_conditionRegistry.registerConditionClass(conditionClass);
		}
		return _conditionRegistry;
	}

	public static final String CONDITION_CLASS_NAME_KEY = "_conditionCN";

	private static ConditionRegistry _conditionRegistry = null;
	private static final Class<?>[] _CONDITION_CLASSES = { OrCondition.class,
			AndCondition.class, NotCondition.class,
			HasParameterCondition.class, NumberParameterEqualsCondition.class,
			NumberParameterLargerCondition.class,
			NumberParameterSmallerCondition.class,
			ParameterContainsCondition.class, ParameterEqualsCondition.class,
			ParameterListContainsCondition.class,
			ParameterMatchesCondition.class };

	private static Log _log = LogFactoryUtil.getLog(RuleUtil.class);
}
