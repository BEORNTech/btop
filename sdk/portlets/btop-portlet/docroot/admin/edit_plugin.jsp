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
long pluginId = ParamUtil.getLong(request, "pluginId");

PaymentPlugin plugin = null;
if (pluginId != 0) {
	PaymentPluginPermission.check(permissionChecker, pluginId, ActionKeys.UPDATE);
	
	try {
		plugin = PaymentPluginLocalServiceUtil.getPaymentPlugin(pluginId);
	} catch (NoSuchPluginException e) {
	}
}

boolean canAddPaymentPluginConfig = PaymentAppPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_PAYMENT_PLUGIN_CONFIG);
%>

<portlet:renderURL var="associatePluginSellersURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="/admin/associate_plugin_sellers.jsp" />
	<portlet:param name="pluginId" value="<%= String.valueOf(pluginId) %>" />
</portlet:renderURL>

<portlet:actionURL var="editPluginURL" name="editPlugin" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="/admin/edit_plugin.jsp" />
	<portlet:param name="pluginId" value="<%= String.valueOf(pluginId) %>" />
</portlet:actionURL>

<h1 class="popup-title">
	<liferay-ui:message key="edit-plugin" />
</h1>

<aui:form action="<%= editPluginURL %>" method="post" onSubmit="return false;" name="<%= randomNamespace %>">
	<aui:model-context bean="<%= plugin %>" model="<%= PaymentPlugin.class %>" />
	<aui:input name="successURL" type="hidden" value="<%= currentURL %>" />

	<aui:input name="name">
		<aui:validator name="required" />
	</aui:input>
	<liferay-ui:error exception="<%= PaymentPluginNameException.class %>" message="please-enter-a-valid-name" />

	<%
	{
		String dynamicConfigXml = plugin == null ? StringPool.BLANK : plugin.getPluginConfigParameters();
		String dynamicConfig = plugin == null ? StringPool.BLANK : plugin.getPluginConfig();
	%>
	<%@ include file="/admin/dynamic_config.jspf" %>
	<%
	}
	%>

	<aui:button-row>
		<aui:button type="submit" value="save" data-popup-button-next-event="click" />
		<aui:button value="cancel" cssClass="popup-button-previous" />
	</aui:button-row>

	<liferay-util:include page="/admin/breadcrumb.jsp" servletContext="<%= servletConfig.getServletContext() %>">
		<liferay-util:param name="steps" value='<%= "edit-plugin" + (canAddPaymentPluginConfig ? ",associate-plugin-sellers" : "") %>' />
		<liferay-util:param name="stepUrls" value='<%= currentURL + StringPool.COMMA + associatePluginSellersURL %>' />
		<liferay-util:param name="currentStep" value="0" />
	</liferay-util:include>

	<div class="edit-actions aui-helper-hidden">
		<a href="<%= currentURL %>" class="cancel-button"><liferay-ui:message key="cancel" /></a>
		<aui:button cssClass="confirm-button" type="submit" value="confirm" />
	</div>
</aui:form>

<%@ include file="/admin/simple_form_modified_event_script.jspf" %>

<%@ include file="/admin/edit_focus_script.jspf" %>
