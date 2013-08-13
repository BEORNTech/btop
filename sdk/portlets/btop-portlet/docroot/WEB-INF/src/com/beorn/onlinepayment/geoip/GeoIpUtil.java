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

package com.beorn.onlinepayment.geoip;

import java.io.IOException;
import java.text.MessageFormat;

import org.json.JSONException;
import org.json.JSONObject;

import com.beorn.onlinepayment.util.PropsValues;
import com.liferay.portal.kernel.cache.MultiVMPoolUtil;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Http.Options;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Sébastien Meunier
 */
public class GeoIpUtil {

	/**
	 * Geolocalize an ip using a json service.
	 * 
	 * The url defined in the porlet.properties by geoip.url will be called,
	 * replacing the {0} marker by the ip, and the
	 * geoip.response.country-code-parameter key of the returned json response
	 * will be extracted and returned as the country code.
	 * 
	 * @param ip
	 *            the ip to geolocalize
	 * @return a country code corresponding to the ip parameter
	 * @throws IOException
	 *             when there is an error calling the json service
	 * @throws JSONException
	 *             when the response isn't a valid json string
	 */
	public static String geolocalizeIp(String ip) throws IOException,
			JSONException {

		ip = StringUtil.trim(ip);

		String key = ip;
		String countryCode = (String) _portalCache.get(key);
		if (countryCode == null) {
			Options options = new Options();
			options.setLocation(MessageFormat.format(PropsValues.GEOIP_URL, ip));

			String response = HttpUtil.URLtoString(options);
			JSONObject jsonResponse = new JSONObject(response);

			countryCode = jsonResponse
					.getString(PropsValues.GEOIP_RESPONSE_COUNTRY_CODE_PARAMETER);
			_log.info("Found country code " + countryCode + " for ip " + ip);

			_portalCache.put(key, countryCode);
		}

		return countryCode;
	}

	private static final PortalCache _portalCache = MultiVMPoolUtil
			.getCache(GeoIpUtil.class.getName());

	private static Log _log = LogFactoryUtil.getLog(GeoIpUtil.class);
}
