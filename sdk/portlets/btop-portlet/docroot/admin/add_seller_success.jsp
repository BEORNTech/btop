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

<%@ include file="/admin/init.jsp" %>

<%
long sellerId = ParamUtil.getLong(request, "sellerId");
String flowType = ParamUtil.getString(request, "flowType");
boolean canAddPaymentPluginConfig = PaymentAppPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_PAYMENT_PLUGIN_CONFIG);
%>

<portlet:renderURL var="editSellerURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="/admin/edit_seller.jsp" />
	<portlet:param name="sellerId" value="<%= String.valueOf(sellerId) %>" />
	<portlet:param name="flowType" value="<%= flowType %>" />
</portlet:renderURL>

<portlet:renderURL var="associateSellerPluginsURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="/admin/associate_seller_plugins.jsp" />
	<portlet:param name="sellerId" value="<%= String.valueOf(sellerId) %>" />
	<portlet:param name="flowType" value="<%= flowType %>" />
</portlet:renderURL>

<liferay-util:include page="/admin/breadcrumb.jsp" servletContext="<%= servletConfig.getServletContext() %>">
	<liferay-util:param name="steps" value='<%= "edit-seller" + (canAddPaymentPluginConfig ? ",associate-seller-plugins" : "") %>' />
	<liferay-util:param name="stepUrls" value='<%= editSellerURL + StringPool.COMMA + associateSellerPluginsURL %>' />
	<liferay-util:param name="flowType" value="<%= flowType %>" />
	<liferay-util:param name="currentStep" value="2" />
</liferay-util:include>

<div class="message-success"><liferay-ui:message key="success" /></div>