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

package com.beorn.paypalpaymentplugin.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beorn.paypalpaymentplugin.util.PayPalUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;

/**
 * @author Sébastien Meunier
 */

public class PayPalReturnServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		ServletContext servletContext = request.getSession()
				.getServletContext();

		String token = ParamUtil.getString(request, "token");
		String payerID = ParamUtil.getString(request, "PayerID");
		String successUrl = ParamUtil.getString(request, "successUrl");
		String errorUrl = ParamUtil.getString(request, "errorUrl");

		try {
			PayPalUtil.confirmTransaction(servletContext, token, payerID,
					successUrl, errorUrl);

			response.sendRedirect(successUrl);

		} catch (Throwable e) {
			_log.error(e);
			response.sendRedirect(errorUrl);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(PayPalReturnServlet.class);
}