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
SellerPermission.check(permissionChecker, sellerId, ActionKeys.VIEW);
%>

<portlet:renderURL var="rulesConfigurationURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="/admin/rules_configuration.jsp" />
	<portlet:param name="sellerId" value="<%= String.valueOf(sellerId) %>" />
</portlet:renderURL>

<portlet:renderURL var="addRuleURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="/admin/edit_rule.jsp" />
	<portlet:param name="sellerId" value="<%= String.valueOf(sellerId) %>" />
</portlet:renderURL>

<portlet:renderURL var="searchURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="/admin/rules_list.jsp" />
</portlet:renderURL>

<section class="page page-rules">
	<header class="page-header">
		<h1 class="page-title">
			<a href="<%= HtmlUtil.escape(rulesConfigurationURL.toString()) %>">
				<liferay-ui:message key="rules" />
			</a>
		</h1>
	</header>
	<a class="add-entity-button" href="<%= HtmlUtil.escape(addRuleURL.toString()) %>" data-load-target="#<portlet:namespace />pageContainer"> 
		<liferay-ui:message key="add-rule" />
	</a>
	<aui:form 
		action="<%= searchURL.toString() %>"
		cssClass="search-form"
		onSubmit="return false;"
		name="<%= randomNamespace %>"
		data-load-target='<%= "#" + portletResponse.getNamespace() + "ruleListContainer" %>'
	>
	<%--
		<div class="input-search">
			<aui:input name="keywords" label="" inlineLabel="true" inlineField="true" placeholder="search" />
			<aui:button type="submit" value="ok" />
		</div>
	 --%>
	</aui:form>

	<div id="<portlet:namespace />ruleListContainer" data-load-inside="">
		<liferay-util:include page="/admin/rules_list.jsp" servletContext="<%= servletConfig.getServletContext() %>">
			<liferay-util:param name="sellerId" value="<%= String.valueOf(sellerId) %>" />
		</liferay-util:include>
	</div>
</section>
