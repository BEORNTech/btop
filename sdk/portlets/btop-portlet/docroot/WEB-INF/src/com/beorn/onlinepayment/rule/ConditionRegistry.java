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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Sébastien Meunier
 */
public class ConditionRegistry {

	public void registerConditionClass(Class<?> conditionClass) {
		List<FieldDeclaration> fieldDeclarations = new ArrayList<FieldDeclaration>();
		buildFieldDeclarations(fieldDeclarations, conditionClass);
		_conditionClassDeclarations.add(new ConditionClassDeclaration(
				conditionClass, fieldDeclarations));
	}

	public boolean hasConditionClass(Class<?> conditionClass) {
		for (ConditionClassDeclaration conditionClassDeclaration : _conditionClassDeclarations) {
			if (conditionClassDeclaration.getConditionClass().equals(
					conditionClass)) {

				return true;
			}
		}
		return false;
	}

	public JSONObject toJSONObject(PortletConfig portletConfig, Locale locale)
			throws JSONException {
		JSONObject result = new JSONObject();

		for (ConditionClassDeclaration conditionClassDeclaration : _conditionClassDeclarations) {
			Class<?> entryClass = conditionClassDeclaration.getConditionClass();
			List<FieldDeclaration> entryFieldDeclarations = conditionClassDeclaration
					.getFieldDeclarations();

			JSONArray fields = new JSONArray();
			for (FieldDeclaration entryFieldDeclaration : entryFieldDeclarations) {
				String fieldName = entryFieldDeclaration.getName();
				if (fieldName.startsWith(StringPool.UNDERLINE))
					fieldName = fieldName.substring(1);

				ConditionField conditionField = entryFieldDeclaration
						.getConditionField();

				String label = LanguageUtil.get(portletConfig, locale,
						conditionField.label());

				JSONObject field = new JSONObject();
				field.put("type", entryFieldDeclaration.getTypeName());
				field.put("label", label);
				field.put("name", fieldName);
				field.put("hint", conditionField.hint());

				fields.put(field);
			}

			JSONObject conditionClass = new JSONObject();
			conditionClass.put("fields", fields);
			result.put(entryClass.getName(), conditionClass);
		}

		return result;
	}

	private void buildFieldDeclarations(
			List<FieldDeclaration> fieldDeclarations, Class<?> conditionClass) {
		Class<?> superclass = conditionClass.getSuperclass();
		if (superclass != null)
			buildFieldDeclarations(fieldDeclarations, superclass);

		Field[] fields = conditionClass.getDeclaredFields();
		for (Field field : fields) {
			ConditionField conditionField = field
					.getAnnotation(ConditionField.class);
			if (conditionField == null)
				continue;

			fieldDeclarations.add(new FieldDeclaration(field, conditionField));
		}
	}

	private static class ConditionClassDeclaration {
		public ConditionClassDeclaration(Class<?> conditionClass,
				List<FieldDeclaration> fieldDeclarations) {

			_conditionClass = conditionClass;
			_fieldDeclarations = fieldDeclarations;
		}

		public Class<?> getConditionClass() {
			return _conditionClass;
		}

		public List<FieldDeclaration> getFieldDeclarations() {
			return _fieldDeclarations;
		}

		private Class<?> _conditionClass;
		private List<FieldDeclaration> _fieldDeclarations;
	}

	private static class FieldDeclaration {
		public FieldDeclaration(Field field, ConditionField conditionField) {
			_field = field;
			_conditionField = conditionField;
		}

		public Class<?> getType() {
			return _field.getType();
		}

		public String getTypeName() {
			return getType().getName();
		}

		public String getName() {
			return _field.getName();
		}

		public ConditionField getConditionField() {
			return _conditionField;
		}

		private Field _field;
		private ConditionField _conditionField;
	}

	private List<ConditionClassDeclaration> _conditionClassDeclarations = new ArrayList<ConditionClassDeclaration>();

}
