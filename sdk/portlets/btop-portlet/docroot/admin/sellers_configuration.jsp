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

<portlet:renderURL var="sellersConfigurationURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="/admin/sellers_configuration.jsp" />
</portlet:renderURL>

<portlet:renderURL var="addSellerURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="/admin/edit_seller.jsp" />
</portlet:renderURL>

<portlet:renderURL var="searchURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="/admin/sellers_list.jsp" />
</portlet:renderURL>

<section class="page page-sellers">
	<header class="page-header">
		<h1 class="page-title">
			<a href="<%= HtmlUtil.escape(sellersConfigurationURL.toString()) %>">
				<liferay-ui:message key="sellers-configuration" />
			</a>
		</h1>
		<c:if test="<%= PaymentAppPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_SELLER) %>">
			<a class="add-entity-button" href="<%= HtmlUtil.escape(addSellerURL.toString()) %>" data-popup="sequence"> 
				<liferay-ui:message key="add-seller" />
			</a>
		</c:if>
		<aui:form 
			action="<%= searchURL.toString() %>"
			cssClass="search-form"
			onSubmit="return false;"
			name="<%= randomNamespace %>"
			data-load-target='<%= "#" + portletResponse.getNamespace() + "sellerListContainer" %>'
		>
			<div class="input-search"> 
				<aui:input name="keywords" label="" inlineLabel="true" inlineField="true" placeholder="search" />
				<aui:button type="submit" value="ok" />
			</div>
			<aui:field-wrapper label="filter-by" cssClass="filter-by">
				<div data-checkbox-group="">
					<input name="active" type="checkbox" value="true" checked="checked" /><span class="input-label"><liferay-ui:message key="active" /></span>
					<input name="active" type="checkbox" value="false" /><span class="input-label"><liferay-ui:message key="inactive" /></span>
				</div>
			</aui:field-wrapper>
		</aui:form>
	</header>

	<div id="<portlet:namespace />sellerListContainer" data-load-inside="">
		<liferay-util:include page="/admin/sellers_list.jsp" servletContext="<%= servletConfig.getServletContext() %>">
			<liferay-util:param name="active" value="true" />
		</liferay-util:include>
	</div>
</section>
