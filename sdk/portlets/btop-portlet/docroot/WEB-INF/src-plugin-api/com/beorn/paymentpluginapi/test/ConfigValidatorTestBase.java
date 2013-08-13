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

package com.beorn.paymentpluginapi.test;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.beorn.paymentpluginapi.validator.ConfigValidator;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

@PrepareForTest({ JSONFactoryUtil.class })
@RunWith(PowerMockRunner.class)
public abstract class ConfigValidatorTestBase extends PowerMockito {

	@Before
	public void setUp() {
	}

	protected abstract ConfigValidator getValidator();

	@Test
	public void testEmptyConfig() throws Exception {
		String configString = "configString";

		mockStatic(JSONFactoryUtil.class);

		JSONObject config = mock(JSONObject.class);
		when(JSONFactoryUtil.createJSONObject(configString)).thenReturn(config);

		ConfigValidator validator = getValidator();

		Assert.assertFalse(validator.isValid(configString));
	}

	@Test
	public void testNullConfig() throws Exception {
		String configString = "configString";

		mockStatic(JSONFactoryUtil.class);

		JSONObject config = mock(JSONObject.class);

		when(JSONFactoryUtil.createJSONObject(configString)).thenReturn(config);
		when(config.has(Mockito.anyString())).thenReturn(true);
		when(config.getJSONObject(Mockito.anyString())).thenReturn(config);

		ConfigValidator validator = getValidator();

		Assert.assertTrue(validator.isValid(configString));
	}
}
