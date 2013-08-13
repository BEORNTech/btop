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

Seller seller = null;
if (sellerId != 0) {
	SellerPermission.check(permissionChecker, sellerId, ActionKeys.UPDATE);
	
	try {
		seller = SellerLocalServiceUtil.getSeller(sellerId);
	} catch (NoSuchSellerException e) {
	}
	
} else {
	PaymentAppPermission.check(permissionChecker, scopeGroupId, ActionKeys.ADD_SELLER);
}

String flowType = ParamUtil.getString(request, "flowType", seller == null ? "sequence" : "single");

boolean canAddPaymentPluginConfig = PaymentAppPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_PAYMENT_PLUGIN_CONFIG);
%>

<portlet:renderURL var="associateSellerPluginsURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="/admin/associate_seller_plugins.jsp" />
	<portlet:param name="sellerId" value="<%= String.valueOf(sellerId) %>" />
	<portlet:param name="flowType" value="<%= flowType %>" />
</portlet:renderURL>

<portlet:actionURL var="editSellerURL" name="editSeller" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="/admin/edit_seller.jsp" />
	<portlet:param name="sellerId" value="<%= String.valueOf(sellerId) %>" />
	<portlet:param name="flowType" value="<%= flowType %>" />
</portlet:actionURL>

<h1 class="popup-title">
	<liferay-ui:message key='<%= seller == null ? "add-seller" : "edit-seller" %>' />
</h1>

<aui:form action="<%= editSellerURL %>" cssClass="edit-seller" method="post" onSubmit="return false;" name="<%= randomNamespace %>">
	<aui:model-context bean="<%= seller %>" model="<%= Seller.class %>" />
	<c:choose>
		<c:when test='<%= flowType.equals("single") %>'>
			<aui:input name="successURL" type="hidden" value="<%= currentURL %>" />
		</c:when>
		<c:when test="<%= canAddPaymentPluginConfig %>">
			<portlet:renderURL var="successURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
				<portlet:param name="jspPage" value="/admin/associate_seller_plugins.jsp" />
				<portlet:param name="sellerId" value="<%= String.valueOf(sellerId) %>" />
				<portlet:param name="flowType" value="<%= flowType %>" />
			</portlet:renderURL>
			<aui:input name="successURL" type="hidden" value="<%= successURL %>" />
		</c:when>
		<c:otherwise>
			<portlet:renderURL var="successURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
				<portlet:param name="jspPage" value="/admin/add_seller_success.jsp" />
				<portlet:param name="sellerId" value="<%= String.valueOf(sellerId) %>" />
				<portlet:param name="flowType" value="<%= flowType %>" />
			</portlet:renderURL>
			<aui:input name="successURL" type="hidden" value="<%= successURL %>" />
		</c:otherwise>
	</c:choose>

	<aui:input name="name">
		<aui:validator name="required" />
	</aui:input>
	<liferay-ui:error key="name-required" message="please-enter-a-valid-name" />
	<liferay-ui:error exception="<%= DuplicateSellerException.class %>" message="there-is-already-a-seller-with-this-name" />

	<aui:input name="active" type="checkbox" value="<%= seller != null ? seller.isActive() : false %>" />
		
	<aui:button-row>
		<aui:button type="submit" value="save" data-popup-button-next-event="click" />
		<aui:button value="cancel" cssClass="popup-button-previous" />
	</aui:button-row>

	<liferay-util:include page="/admin/breadcrumb.jsp" servletContext="<%= servletConfig.getServletContext() %>">
		<liferay-util:param name="steps" value='<%= (seller == null ? "add-seller" : "edit-seller") + (canAddPaymentPluginConfig ? ",associate-seller-plugins" : "") %>' />
		<liferay-util:param name="stepUrls" value='<%= seller != null ? currentURL + StringPool.COMMA + associateSellerPluginsURL : "" %>' />
		<liferay-util:param name="flowType" value="<%= flowType %>" />
		<liferay-util:param name="currentStep" value="0" />
	</liferay-util:include>

	<c:if test='<%= flowType.equals("single") %>'>
		<div class="edit-actions aui-helper-hidden">
			<a href="<%= currentURL %>" class="cancel-button"><liferay-ui:message key="cancel" /></a>
			<aui:button cssClass="confirm-button" type="submit" value="confirm" />
		</div>
	</c:if>
</aui:form>

<%@ include file="/admin/simple_form_modified_event_script.jspf" %>

<%@ include file="/admin/edit_focus_script.jspf" %>
