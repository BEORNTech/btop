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

package com.beorn.paymentapi.messaging;

/**
 * Names of the messages destinations
 * 
 * @author Sébastien Meunier
 */
public class DestinationNames {

	/**
	 * The payment system, from a shop plugin
	 */
	public static String PAYMENT_SYSTEM_APPS = "Beorn.Payment.System.Apps";

	/**
	 * The payment system, from a payment plugin
	 */
	public static String PAYMENT_SYSTEM_PLUGINS = "Beorn.Payment.System.Plugins";

	/**
	 * Prefix for a destination to a specific shop plugin
	 */
	public static String PAYMENT_PLUGIN_PREFIX = "Beorn.Payment.Plugin.";

	/**
	 * Prefix for a destination to a specific payment plugin
	 */
	public static String PAYMENT_APP_PREFIX = "Beorn.Payment.App.";

}