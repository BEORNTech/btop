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
long methodId = ParamUtil.getLong(request, "methodId");

PaymentMethod method = null;
if (methodId != 0) {
	PaymentMethodPermission.check(permissionChecker, methodId, ActionKeys.UPDATE);

	try {
		method = PaymentMethodLocalServiceUtil.getPaymentMethod(methodId);
	} catch (NoSuchSellerException e) {
	}
}
%>

<portlet:actionURL var="editMethodURL" name="editMethod" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="/admin/edit_method.jsp" />
	<portlet:param name="methodId" value="<%= String.valueOf(methodId) %>" />
</portlet:actionURL>

<portlet:renderURL var="successURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="/admin/success.jsp" />
</portlet:renderURL>

<h1 class="popup-title">
	<liferay-ui:message key="edit-method" />
</h1>

<aui:form action="<%= editMethodURL %>" method="post" onSubmit="return false;" name="<%= randomNamespace %>">
	<aui:model-context bean="<%= method %>" model="<%= PaymentMethod.class %>" />
	<aui:input name="successURL" type="hidden" value="<%= currentURL %>" />

	<aui:input name="name">
		<aui:validator name="required" />
	</aui:input>

	<aui:button-row>
		<aui:button type="submit" value="save" data-popup-button-next-event="click" />
		<aui:button value="cancel" cssClass="popup-button-previous" />
	</aui:button-row>

	<liferay-util:include page="/admin/breadcrumb.jsp" servletContext="<%= servletConfig.getServletContext() %>">
		<liferay-util:param name="steps" value="edit-method" />
		<liferay-util:param name="currentStep" value="0" />
	</liferay-util:include>

	<div class="edit-actions aui-helper-hidden">
		<a href="<%= currentURL %>" class="cancel-button"><liferay-ui:message key="cancel" /></a>
		<aui:button cssClass="confirm-button" type="submit" value="confirm" />
	</div>
</aui:form>

<%@ include file="/admin/simple_form_modified_event_script.jspf" %>

<%@ include file="/admin/edit_focus_script.jspf" %>
