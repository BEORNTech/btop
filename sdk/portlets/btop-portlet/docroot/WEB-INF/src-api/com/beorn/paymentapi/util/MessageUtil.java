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

package com.beorn.paymentapi.util;

import java.util.Enumeration;

import javax.jms.JMSException;
import javax.jms.Message;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Sébastien Meunier
 */
public class MessageUtil {

	/**
	 * Utility method used to return a short description of a message's contents
	 * 
	 * @param message
	 * @return a short description of a message's contents
	 */
	public static String messagePropertiesToString(Message message) {

		try {
			StringBundler sb = new StringBundler();
			for (Enumeration<String> propertyNames = message.getPropertyNames(); propertyNames.hasMoreElements();) {

				String name = propertyNames.nextElement();
				Object value = message.getObjectProperty(name);

				if (value == null)
					_log.warn(name + " is null");

				sb.append(name);
				sb.append(" : ");
				sb.append(value);
				sb.append(" | ");
			}
			return sb.toString();

		} catch (JMSException e) {
			return e.toString();
		}
	}

	private static Log _log = LogFactoryUtil.getLog(MessageUtil.class);
}