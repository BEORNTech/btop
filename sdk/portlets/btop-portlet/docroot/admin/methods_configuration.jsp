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

<portlet:renderURL var="methodsConfigurationURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="/admin/methods_configuration.jsp" />
</portlet:renderURL>

<portlet:renderURL var="searchURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="/admin/methods_list.jsp" />
</portlet:renderURL>

<section class="page page-methods">
	<header class="page-header">
		<h1 class="page-title">
			<a href="<%= HtmlUtil.escape(methodsConfigurationURL.toString()) %>">
				<liferay-ui:message key="methods-configuration" />
			</a>
		</h1>
		<aui:form 
			action="<%= searchURL.toString() %>"
			cssClass="search-form"
			onSubmit="return false;"
			name="<%= randomNamespace %>"
			data-load-target='<%= "#" + portletResponse.getNamespace() + "methodListContainer" %>'
		>
			<div class="input-search"> 
				<aui:input name="keywords" label="" inlineLabel="true" inlineField="true" placeholder="search" />
				<aui:button type="submit" value="ok" />
			</div>
		</aui:form>
	</header>

	<div id="<portlet:namespace />methodListContainer" data-load-inside="">
		<liferay-util:include page="/admin/methods_list.jsp" servletContext="<%= servletConfig.getServletContext() %>" />
	</div>
</section>
