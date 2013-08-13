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
long pluginConfigId = ParamUtil.getLong(request, "pluginConfigId");

PaymentPluginConfig pluginConfig = null;
if (pluginConfigId != 0) {
	PaymentPluginConfigPermission.check(permissionChecker, pluginConfigId, ActionKeys.UPDATE);
	
	try {
		pluginConfig = PaymentPluginConfigLocalServiceUtil.getPaymentPluginConfig(pluginConfigId);
	} catch (NoSuchPluginException e) {
	}
}

PaymentPlugin plugin = null;
if (pluginConfig != null)
	plugin = PaymentPluginLocalServiceUtil.getPaymentPlugin(pluginConfig.getPaymentPluginId());

%>

<portlet:actionURL var="editSellerPluginURL" name="editSellerPlugin" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="/admin/edit_seller_plugin.jsp" />
	<portlet:param name="pluginConfigId" value="<%= String.valueOf(pluginConfigId) %>" />
</portlet:actionURL>

<portlet:renderURL var="successURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="/admin/success.jsp" />
</portlet:renderURL>

<h1 class="popup-title">
	<liferay-ui:message key="edit-seller-plugin" />
</h1>

<aui:form action="<%= editSellerPluginURL %>" method="post" onSubmit="return false;" name="<%= randomNamespace %>">
	<aui:model-context bean="<%= pluginConfig %>" model="<%= PaymentPluginConfig.class %>" />
	<aui:input name="successURL" type="hidden" value="<%= currentURL %>" />

	<%
	{
		String dynamicConfigXml = plugin == null ? StringPool.BLANK : plugin.getSellerConfigParameters();
		String dynamicConfig = pluginConfig == null ? StringPool.BLANK : pluginConfig.getConfig();
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
		<liferay-util:param name="steps" value="edit-seller-plugin" />
		<liferay-util:param name="currentStep" value="0" />
	</liferay-util:include>

	<div class="edit-actions aui-helper-hidden">
		<a href="<%= currentURL %>" class="cancel-button"><liferay-ui:message key="cancel" /></a>
		<aui:button cssClass="confirm-button" type="submit" value="confirm" />
	</div>
</aui:form>

<%@ include file="/admin/simple_form_modified_event_script.jspf" %>

<%@ include file="/admin/edit_focus_script.jspf" %>
