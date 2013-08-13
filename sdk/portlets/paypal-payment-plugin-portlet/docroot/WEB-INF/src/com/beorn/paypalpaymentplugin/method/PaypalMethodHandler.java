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

package com.beorn.paypalpaymentplugin.method;

import javax.servlet.ServletContext;

import com.beorn.paymentapi.model.ApiTransaction;
import com.beorn.paymentpluginapi.method.MethodHandler;
import com.beorn.paypalpaymentplugin.util.PayPalUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public class PaypalMethodHandler implements MethodHandler {

	public void initialize(ServletContext servletContext) throws PortalException, SystemException {
		_servletContext = servletContext;
	}

	public String getPaymentUrl(ApiTransaction transaction, String languageId, String backUrl, String successUrl,
			String errorUrl) throws PortalException, SystemException {

		return PayPalUtil.getPaymentUrl(_servletContext, transaction, languageId, backUrl, successUrl, errorUrl);
	}

	private ServletContext _servletContext;
}
