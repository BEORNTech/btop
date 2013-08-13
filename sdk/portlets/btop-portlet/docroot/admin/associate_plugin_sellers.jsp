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

long pluginId = ParamUtil.getLong(request, "pluginId");

PaymentPlugin plugin = null;
if (pluginId != 0) {
	PaymentPluginPermission.check(permissionChecker, pluginId, ActionKeys.VIEW);

	try {
		plugin = PaymentPluginLocalServiceUtil.getPaymentPlugin(pluginId);
	} catch (NoSuchPluginException e) {
	}
}

List<Seller> sellers = SellerServiceUtil.search(company.getCompanyId(), null, true, 
		QueryUtil.ALL_POS, QueryUtil.ALL_POS, new SellerCreateDateComparator());
%>

<portlet:renderURL var="editPluginURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="/admin/edit_plugin.jsp" />
	<portlet:param name="pluginId" value="<%= String.valueOf(pluginId) %>" />
</portlet:renderURL>

<portlet:actionURL var="associatePluginSellersURL" name="associatePluginSellers" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="/admin/associate_plugin_sellers.jsp" />
	<portlet:param name="pluginId" value="<%= String.valueOf(pluginId) %>" />
</portlet:actionURL>

<portlet:renderURL var="successURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="/admin/success.jsp" />
</portlet:renderURL>

<h1 class="popup-title">
	<liferay-ui:message key="associate-sellers-to-plugin" />
</h1>

<aui:form action="<%= associatePluginSellersURL %>" method="post" onSubmit="return false;" name="<%= randomNamespace %>">
	<aui:input name="successURL" type="hidden" value="<%= currentURL %>" />

	<div class="associate-entity-container" id="<portlet:namespace /><%= randomNamespace %>dndContainer">
		<div class="associate-entity">
			<div class="plugins"><%= HtmlUtil.escape(plugin.getName(locale)) %></div>
		</div>
		<div class="associated-background">
		</div>
		<div id="<portlet:namespace /><%= randomNamespace %>associatedListContainer" class="associated-list">
		</div>

		<div class="associate-entity-drop-area">
			<span>
				<liferay-ui:message key="drag-and-drop-seller" />
			</span>
		</div>
		<div class="associate-list-container">
			<div class="associate-list-title"><liferay-ui:message key="available-sellers" /></div>
			<aui:input id='<%= randomNamespace + "search" %>' name="search" type="text" cssClass="associate-list-search" placeholder="search" label="" />
			<div class="clear"></div>
			<div id="<portlet:namespace /><%= randomNamespace %>associateListContainer" class="associate-list">
				<%
				for (Seller seller : sellers) {
					boolean associated = false;
					if (plugin != null) {
						associated = PaymentPluginConfigLocalServiceUtil
							.hasPaymentPluginConfigBySellerIdAndPaymentPluginId(
									seller.getSellerId(), plugin.getPaymentPluginId());
					}
				%>
				<div class="associate-list-element seller <%= associated ? "aui-helper-hidden" : "" %>">
					<%= HtmlUtil.escape(seller.getName()) %>
					<span class="associate-list-element-remove"><liferay-ui:message key="remove" /></span>
					<aui:input name='<%= "seller_" + seller.getSellerId() %>' type="hidden" value="<%= associated %>" />
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
		<liferay-util:param name="steps" value='<%= "edit-plugin,associate-plugin-sellers" %>' />
		<liferay-util:param name="stepUrls" value='<%= editPluginURL + StringPool.COMMA + associatePluginSellersURL %>' />
		<liferay-util:param name="currentStep" value="1" />
	</liferay-util:include>

	<div class="edit-actions aui-helper-hidden">
		<a href="<%= currentURL %>" class="cancel-button"><liferay-ui:message key="cancel" /></a>
		<aui:button cssClass="confirm-button" type="submit" value="confirm" />
	</div>
</aui:form>

<%@ include file="/admin/associate_script.jspf" %>

<%@ include file="/admin/edit_focus_script.jspf" %>
