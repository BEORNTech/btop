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

package com.beorn.paymentpluginapi.method;

import javax.servlet.ServletContext;

import com.beorn.paymentapi.model.ApiTransaction;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * A method handler represents one payment method that the plugin supports. They must be declared in
 * payment-plugin.xml.
 * 
 * @author Sébastien Meunier
 */
public interface MethodHandler {

	/**
	 * Initialize the method handler
	 * 
	 * @param servletContext
	 *            the servlet context of this webapp
	 * @throws PortalException
	 * @throws SystemException
	 */
	void initialize(ServletContext servletContext) throws PortalException, SystemException;

	/**
	 * Retrieves the url that the user must use to pay a transaction using that payment method
	 * 
	 * @param transaction
	 *            the transaction to pay
	 * @param languageId
	 *            the language in which the payment page must be displayed
	 * @param backUrl
	 *            the url to go to if the user cancels the transaction
	 * @param successUrl
	 *            the url to go to if the transaction is paid successfully
	 * @param errorUrl
	 *            the url to go to if there is an error while paying the transaction
	 * @return an url to pay the transaction using that payment method
	 * @throws PortalException
	 * @throws SystemException
	 */
	String getPaymentUrl(ApiTransaction transaction, String languageId, String backUrl, String successUrl, String errorUrl)
			throws PortalException, SystemException;

}
