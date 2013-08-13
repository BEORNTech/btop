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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.beorn.onlinepayment.rule.exception.RuleException;
import com.beorn.onlinepayment.rule.parameter.HasParameterCondition;
import com.beorn.onlinepayment.rule.parameter.NumberParameterLargerCondition;
import com.beorn.onlinepayment.rule.parameter.NumberParameterSmallerCondition;
import com.beorn.onlinepayment.rule.parameter.ParameterEqualsCondition;
import com.beorn.onlinepayment.rule.parameter.ParametersConditionSubject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

@PrepareForTest({ LogFactoryUtil.class })
@RunWith(PowerMockRunner.class)
public class RuleUtilTest extends PowerMockito {

	@Before
	public void setUp() {
		mockStatic(LogFactoryUtil.class);

		Log log = mock(Log.class);

		when(LogFactoryUtil.getLog(Mockito.any(Class.class))).thenReturn(log);

		_emptySubject = new ParametersConditionSubject(
				new HashMap<String, String>());
	}

	/**
	 * Verifies that a condition that is serialized and then deserialized is
	 * equivalent to the initial condition
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSerializeDeserializeConditions() throws RuleException {
		Map<String, String> validParameters = new HashMap<String, String>();
		validParameters.put("test", "42");
		ParametersConditionSubject validSubject = new ParametersConditionSubject(
				validParameters);

		Map<String, String> tooLowParameters = new HashMap<String, String>();
		tooLowParameters.put("test", "40");
		ParametersConditionSubject tooLowSubject = new ParametersConditionSubject(
				tooLowParameters);

		Map<String, String> tooHighParameters = new HashMap<String, String>();
		tooHighParameters.put("test", "50");
		ParametersConditionSubject tooHighSubject = new ParametersConditionSubject(
				tooHighParameters);

		Condition<ParametersConditionSubject> originalRule = new AndCondition<ParametersConditionSubject>(
				new NotCondition<ParametersConditionSubject>(
						new NumberParameterSmallerCondition("test", 41)),
				new NumberParameterSmallerCondition("test", 43));

		assertTrue(originalRule.isSatisfiedBy(validSubject));
		assertFalse(originalRule.isSatisfiedBy(tooLowSubject));
		assertFalse(originalRule.isSatisfiedBy(tooHighSubject));

		String serializedRule = RuleUtil.toString(originalRule);

		Condition<ParametersConditionSubject> deserializedRule = RuleUtil
				.fromString(serializedRule);

		assertTrue(deserializedRule.isSatisfiedBy(validSubject));
		assertFalse(deserializedRule.isSatisfiedBy(tooLowSubject));
		assertFalse(deserializedRule.isSatisfiedBy(tooHighSubject));
	}

	/**
	 * Verifies that an empty composite condition is always satified
	 * 
	 * @throws Exception
	 */
	@Test
	public void testEmptyAndConditions() {
		AndCondition<ParametersConditionSubject> emptyAndRule = new AndCondition<ParametersConditionSubject>();
		Condition<ParametersConditionSubject> emptyOrRule = new OrCondition<ParametersConditionSubject>();

		assertTrue(emptyAndRule.isSatisfiedBy(_emptySubject));
		assertTrue(emptyOrRule.isSatisfiedBy(_emptySubject));
	}

	/**
	 * Verifies HasParameterCondition behavior
	 * 
	 * @throws Exception
	 */
	@Test
	public void testHasParametersConditions() {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("test", "42");
		ParametersConditionSubject subject = new ParametersConditionSubject(
				parameters);

		Condition<ParametersConditionSubject> hasParameterRule = new HasParameterCondition(
				"test");
		Condition<ParametersConditionSubject> wrongParametersRule = new HasParameterCondition(
				"wrongparameter");

		assertTrue(hasParameterRule.isSatisfiedBy(subject));
		assertFalse(wrongParametersRule.isSatisfiedBy(subject));
	}

	/**
	 * Verifies ParameterEqualsCondition behavior
	 * 
	 * @throws Exception
	 */
	@Test
	public void testParameterEqualsConditions() {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("test", "toto");
		ParametersConditionSubject subject = new ParametersConditionSubject(
				parameters);

		Condition<ParametersConditionSubject> parameterEqualsRule = new ParameterEqualsCondition(
				"test", "toto", false);
		Condition<ParametersConditionSubject> wrongParameterEqualsRule = new ParameterEqualsCondition(
				"test", "toTO", false);
		Condition<ParametersConditionSubject> parameterEqualsIgnoreCaseRule = new ParameterEqualsCondition(
				"test", "toTO", true);

		assertTrue(parameterEqualsRule.isSatisfiedBy(subject));
		assertFalse(wrongParameterEqualsRule.isSatisfiedBy(subject));
		assertTrue(parameterEqualsIgnoreCaseRule.isSatisfiedBy(subject));
	}

	/**
	 * Verifies that parameter-based rules aren't satisfied when the parameter
	 * is missing.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testNonExistingParameterRules() throws RuleException,
			JSONException {

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("someParameter", "6.99");
		ParametersConditionSubject subject = new ParametersConditionSubject(
				parameters);

		Condition<ParametersConditionSubject> smallerRule = new NumberParameterSmallerCondition(
				"parameterThatDoesntExist", 10);
		Condition<ParametersConditionSubject> notSmallerRule = new NotCondition<ParametersConditionSubject>(
				smallerRule);

		Condition<ParametersConditionSubject> largerRule = new NumberParameterLargerCondition(
				"parameterThatDoesntExist", 10);
		Condition<ParametersConditionSubject> notLargerRule = new NotCondition<ParametersConditionSubject>(
				largerRule);

		assertFalse(smallerRule.isSatisfiedBy(subject));
		assertTrue(notSmallerRule.isSatisfiedBy(subject));

		assertFalse(largerRule.isSatisfiedBy(subject));
		assertTrue(notLargerRule.isSatisfiedBy(subject));
	}

	/**
	 * Verifies that opposite rules return opposite results
	 * 
	 * @throws Exception
	 */
	@Test
	public void testOppositeSmallerRules() throws RuleException, JSONException {
		Map<String, String> parameters1 = new HashMap<String, String>();
		parameters1.put("transactionAmount", "6.99");
		ParametersConditionSubject subject1 = new ParametersConditionSubject(
				parameters1);

		Map<String, String> parameters2 = new HashMap<String, String>();
		parameters2.put("transactionAmount", "24.99");
		ParametersConditionSubject subject2 = new ParametersConditionSubject(
				parameters2);

		Condition<ParametersConditionSubject> smallerRule = new NumberParameterSmallerCondition(
				"transactionAmount", 10);
		Condition<ParametersConditionSubject> notSmallerRule = new NotCondition<ParametersConditionSubject>(
				smallerRule);

		assertTrue(smallerRule.isSatisfiedBy(subject1));
		assertFalse(notSmallerRule.isSatisfiedBy(subject1));
		assertFalse(smallerRule.isSatisfiedBy(subject2));
		assertTrue(notSmallerRule.isSatisfiedBy(subject2));
	}

	private ParametersConditionSubject _emptySubject;
}
