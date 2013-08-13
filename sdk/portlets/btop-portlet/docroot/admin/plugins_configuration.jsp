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

<portlet:renderURL var="pluginsConfigurationURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="/admin/plugins_configuration.jsp" />
</portlet:renderURL>

<portlet:renderURL var="searchURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="/admin/plugins_list.jsp" />
</portlet:renderURL>

<section class="page page-plugins">
	<header class="page-header">
		<h1 class="page-title">
			<a href="<%= HtmlUtil.escape(pluginsConfigurationURL.toString()) %>">
				<liferay-ui:message key="plugins-configuration" />
			</a>
		</h1>
		<aui:form 
			action="<%= searchURL.toString() %>"
			cssClass="search-form"
			onSubmit="return false;"
			name="<%= randomNamespace %>"
			data-load-target='<%= "#" + portletResponse.getNamespace() + "pluginListContainer" %>'
		>
			<div class="input-search"> 
				<aui:input name="keywords" label="" inlineLabel="true" inlineField="true" placeholder="search" />
				<aui:button type="submit" value="ok" />
			</div>
			<div class="filter">
				<aui:field-wrapper label="filter-by" cssClass="filter-by">
					<div data-checkbox-group="">
						<input name="configured" type="checkbox" value="true" /><span class="input-label"><liferay-ui:message key="configured" /></span>
						<input name="configured" type="checkbox" value="false" /><span class="input-label"><liferay-ui:message key="not-configured" /></span>
					</div>
				</aui:field-wrapper>
				<aui:select name="methodId" showEmptyOption="true" label="" cssClass="method-select">
					<%
					for (PaymentMethod method : PaymentMethodLocalServiceUtil.getPaymentMethods(QueryUtil.ALL_POS, QueryUtil.ALL_POS)) {
					%>
					<aui:option label="<%= method.getName(locale) %>" value="<%= method.getPaymentMethodId() %>" />
					<%
					}
					%>
				</aui:select>
			</div>
		</aui:form>
	</header>

	<div id="<portlet:namespace />pluginListContainer" data-load-inside="">
		<liferay-util:include page="/admin/plugins_list.jsp" servletContext="<%= servletConfig.getServletContext() %>" />
	</div>
</section>
