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

package com.beorn.onlinepayment.util;

import com.liferay.util.portlet.PortletProps;

/**
 * @author Sébastien Meunier
 */
public class PropsValues {

	public static final String GEOIP_URL = PortletProps.get(PropsKeys.GEOIP_URL);
	public static final String GEOIP_RESPONSE_COUNTRY_CODE_PARAMETER = PortletProps
			.get(PropsKeys.GEOIP_RESPONSE_COUNTRY_CODE_PARAMETER);
	public static final String HELP_URL = PortletProps
			.get(PropsKeys.HELP_URL);
	public static final String DOWNLOAD_PLUGIN_URL = PortletProps
			.get(PropsKeys.DOWNLOAD_PLUGIN_URL);
}