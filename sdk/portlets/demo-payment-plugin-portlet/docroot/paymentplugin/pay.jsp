<%--
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
--%>

<%@page import="java.util.Currency"%>
<%@ include file="/paymentplugin/init.jsp"%>

<%
long transactionId = ParamUtil.getLong(request, "transactionId");
double amount = ParamUtil.getDouble(request, "amount");
String currencyCode = ParamUtil.getString(request, "currencyCode");
//String languageId = ParamUtil.getString(request, "languageId");
String backUrl = ParamUtil.getString(request, "backUrl");
String successUrl = ParamUtil.getString(request, "successUrl");
String errorUrl = ParamUtil.getString(request, "errorUrl");

Currency currency = Currency.getInstance(currencyCode);
%>

<portlet:actionURL name="completePayment" var="completePaymentURL">
</portlet:actionURL>

<aui:form action="<%= completePaymentURL %>" method="post" name="fm">

	<span class="message">
		<liferay-ui:message key="you-must-pay-x" arguments='<%= (Math.round(amount*100)/100.0d) + " " + currency.getSymbol(locale) %>' />
	</span>

	<aui:input type="hidden" name="transactionId" value="<%= transactionId %>" />
	<aui:input type="hidden" name="amountPaid" value="<%= amount %>" />
	<aui:input type="hidden" name="successUrl" value="<%= successUrl %>" />
	<aui:input type="hidden" name="errorUrl" value="<%= errorUrl %>" />
	
	<aui:button-row>
		<aui:button type="cancel" onClick="<%= backUrl %>" />
		<aui:button type="submit" value="confirm" />
	</aui:button-row>

</aui:form>
