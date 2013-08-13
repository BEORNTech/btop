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
String keywords = ParamUtil.getString(request, "keywords");

PortletURL portletURL = renderResponse.createRenderURL();
portletURL.setParameter("jspPage", "/admin/methods_list.jsp");
portletURL.setParameter("keywords", keywords);

SearchContainer searchContainer = new SearchContainer(
		renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, 
		SearchContainer.DEFAULT_DELTA, portletURL, null, null);

List<PaymentMethod> methods = PaymentMethodServiceUtil.search(
		company.getCompanyId(), keywords, searchContainer.getStart(), searchContainer.getEnd(), null);
int methodCount = PaymentMethodServiceUtil.searchCount(company.getCompanyId(), keywords);

searchContainer.setResults(methods);
searchContainer.setTotal(methodCount);
%>

<div data-refresh-url="<%= HtmlUtil.escape(searchContainer.getIteratorURL().toString()) %>"></div>

<c:choose>
	<c:when test="<%= !methods.isEmpty() %>">
		
		<div class="entity-container method-container">
			<div class="entity-container-header">
				<div class="entity-container-column-title">
					<liferay-ui:message key="payment-method" />
				</div>
				<div class="entity-container-column-title">
					<liferay-ui:message key="payment-plugin" />
				</div>
				<div class="entity-container-column-title">
					<liferay-ui:message key="status" />
				</div>
			</div>

			<div class="entity-container-list">
				<%
				for (PaymentMethod method : methods) {
					List<PaymentPlugin> plugins = PaymentPluginLocalServiceUtil
							.getPaymentMethodPaymentPlugins(method.getPaymentMethodId(),
									QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
				%>
				<portlet:renderURL var="methodEditURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
					<portlet:param name="jspPage" value="/admin/edit_method.jsp" />
					<portlet:param name="methodId" value="<%= String.valueOf(method.getPaymentMethodId()) %>" />
				</portlet:renderURL>
				
				<section class="entity">
					<div class="entity-summary">
						<h1><%= HtmlUtil.escape(method.getName(locale)) %></h1>
						<c:if test="<%= PaymentMethodPermission.contains(permissionChecker, method, ActionKeys.UPDATE) %>">
							<a href="<%= HtmlUtil.escape(methodEditURL.toString()) %>" class="edit-icon" data-popup="single">
								<liferay-ui:message key="edit" />
							</a>
						</c:if>
					</div>
					<div id="<portlet:namespace />methodDetails_<%= method.getPaymentMethodId() %>" class="entity-details">
						<c:if test="<%= plugins.size() > 3 %>">
						<div class="entity-details-mask">
						</c:if>
							<%
							for (PaymentPlugin plugin : plugins) {
							%>
							<portlet:renderURL var="pluginEditURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
								<portlet:param name="jspPage" value="/admin/edit_plugin.jsp" />
								<portlet:param name="pluginId" value="<%= String.valueOf(plugin.getPaymentPluginId()) %>" />
							</portlet:renderURL>
							
							<section class="entity-details-line">
								<h1><%= HtmlUtil.escape(plugin.getName(locale)) %></h1>
								<div class="entity-details-line-content">
									<c:choose>
										<c:when test="<%= plugin.isConfigured() %>">
											<span class="configured">
												<liferay-ui:message key="configured" />
											</span>
										</c:when>
										<c:otherwise>
											<span class="not-configured">
												<liferay-ui:message key="not-configured" />
											</span>
										</c:otherwise>
									</c:choose>
									<c:if test="<%= PaymentPluginPermission.contains(permissionChecker, plugin, ActionKeys.UPDATE) %>">
										<a href="<%= HtmlUtil.escape(pluginEditURL.toString()) %>" class="edit-icon" data-popup="single">
											<liferay-ui:message key="edit" />
										</a>
									</c:if>
								</div>
							</section>
							<%
							}
							%>
						<c:if test="<%= plugins.size() > 3 %>">
						</div>
							<div class="entity-details-toggler" data-toggle-target="#<portlet:namespace />methodDetails_<%= method.getPaymentMethodId() %>">
								<span></span>
							</div>
						</c:if>
					</div>
				</section>
				<%
				}
				%>
			</div>

			<liferay-ui:search-paginator type="aggregator" searchContainer="<%= searchContainer %>" />
		</div>
		
	</c:when>
	<c:otherwise>

		<div class="portlet-msg-info">
			<liferay-ui:message key="no-method-found" />
		</div>

	</c:otherwise>
</c:choose>
