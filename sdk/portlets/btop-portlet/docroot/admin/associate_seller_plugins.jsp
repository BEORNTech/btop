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
PaymentAppPermission.check(permissionChecker, scopeGroupId,
		ActionKeys.ADD_PAYMENT_PLUGIN_CONFIG);

long sellerId = ParamUtil.getLong(request, "sellerId");

Seller seller = null;
if (sellerId != 0) {
	SellerPermission.check(permissionChecker, sellerId, ActionKeys.VIEW);

	try {
		seller = SellerLocalServiceUtil.getSeller(sellerId);
	} catch (NoSuchSellerException e) {
	}
}

String flowType = ParamUtil.getString(request, "flowType", seller == null ? "sequence" : "single");

List<PaymentPlugin> paymentPlugins = PaymentPluginServiceUtil.search(company.getCompanyId(), 
		null, null, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new PaymentPluginCreateDateComparator());
%>

<portlet:renderURL var="editSellerURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="/admin/edit_seller.jsp" />
	<portlet:param name="sellerId" value="<%= String.valueOf(sellerId) %>" />
	<portlet:param name="flowType" value="<%= flowType %>" />
</portlet:renderURL>

<portlet:actionURL var="associateSellerPluginsURL" name="associateSellerPlugins" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="/admin/associate_seller_plugins.jsp" />
	<portlet:param name="sellerId" value="<%= String.valueOf(sellerId) %>" />
	<portlet:param name="flowType" value="<%= flowType %>" />
</portlet:actionURL>

<portlet:renderURL var="successURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="/admin/add_seller_success.jsp" />
	<portlet:param name="flowType" value="<%= flowType %>" />
</portlet:renderURL>

<h1 class="popup-title">
	<liferay-ui:message key="associate-plugins-to-seller" />
</h1>

<aui:form action="<%= associateSellerPluginsURL %>" method="post" onSubmit="return false;" name="<%= randomNamespace %>">
	<c:choose>
		<c:when test='<%= flowType.equals("single") %>'>
			<aui:input name="successURL" type="hidden" value="<%= currentURL %>" />
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

	<div class="associate-entity-container" id="<portlet:namespace /><%= randomNamespace %>dndContainer">
		<div class="associate-entity">
			<div class="profile"><%= HtmlUtil.escape(seller.getName()) %></div>
		</div>
		<div class="associated-background">
		</div>
		<div id="<portlet:namespace /><%= randomNamespace %>associatedListContainer" class="associated-list">
		</div>
		
		<div class="associate-entity-drop-area">
			<span>
				<liferay-ui:message key="drag-and-drop-plugin" />
			</span>
		</div>
		<div class="associate-list-container">
			<div class="associate-list-title"><liferay-ui:message key="available-plugins" /></div>
			<aui:input id='<%= randomNamespace + "search" %>' name="search" type="text" cssClass="associate-list-search" placeholder="search" label="" />
			<div class="clear"></div>
			<div id="<portlet:namespace /><%= randomNamespace %>associateListContainer" class="associate-list">
				<%
				for (PaymentPlugin paymentPlugin : paymentPlugins) {
					boolean associated = false;
					if (seller != null) {
						associated = PaymentPluginConfigLocalServiceUtil
							.hasPaymentPluginConfigBySellerIdAndPaymentPluginId(
									seller.getSellerId(), paymentPlugin.getPaymentPluginId());
					}
				%>
				<div class="associate-list-element <%= associated ? "aui-helper-hidden" : "" %>">
					<%= HtmlUtil.escape(paymentPlugin.getName(locale)) %>
					<span class="associate-list-element-remove"><liferay-ui:message key="remove" /></span>
					<aui:input name='<%= "paymentPlugin_" + paymentPlugin.getPaymentPluginId() %>' type="hidden" value="<%= associated %>" />
				</div>
				<%
				}
				%>
			</div>
		</div>
	</div>

	<aui:button-row>
		<aui:button type="submit" value="save" data-popup-button-next-event="click" />
		<aui:button value="cancel" cssClass="popup-button-previous" />
	</aui:button-row>

	<liferay-util:include page="/admin/breadcrumb.jsp" servletContext="<%= servletConfig.getServletContext() %>">
		<liferay-util:param name="steps" value="edit-seller,associate-seller-plugins" />
		<liferay-util:param name="stepUrls" value='<%= editSellerURL + StringPool.COMMA + associateSellerPluginsURL %>' />
		<liferay-util:param name="flowType" value="<%= flowType %>" />
		<liferay-util:param name="currentStep" value="1" />
	</liferay-util:include>

	<c:if test='<%= flowType.equals("single") %>'>
		<div class="edit-actions aui-helper-hidden">
			<a href="<%= currentURL %>" class="cancel-button"><liferay-ui:message key="cancel" /></a>
			<aui:button cssClass="confirm-button" type="submit" value="confirm" />
		</div>
	</c:if>
</aui:form>

<%@ include file="/admin/associate_script.jspf" %>

<%@ include file="/admin/edit_focus_script.jspf" %>
