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
String configuredString = ParamUtil.getString(request, "configured");
Boolean configured = Validator.isNotNull(configuredString) ? GetterUtil.getBoolean(configuredString) : null;
long methodId = ParamUtil.getLong(request, "methodId");

PortletURL portletURL = renderResponse.createRenderURL();
portletURL.setParameter("jspPage", "/admin/plugins_list.jsp");
portletURL.setParameter("keywords", keywords);
portletURL.setParameter("configured", configuredString);
portletURL.setParameter("methodId", String.valueOf(methodId));

SearchContainer searchContainer = new SearchContainer(
		renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, 
		SearchContainer.DEFAULT_DELTA, portletURL, null, null);

List<PaymentPlugin> plugins = PaymentPluginServiceUtil.search(
		company.getCompanyId(), keywords, configured, methodId, 
		searchContainer.getStart(), searchContainer.getEnd(), null);
int pluginCount = PaymentPluginServiceUtil.searchCount(company.getCompanyId(), keywords, configured, methodId);

searchContainer.setResults(plugins);
searchContainer.setTotal(pluginCount);
%>

<div data-refresh-url="<%= HtmlUtil.escape(searchContainer.getIteratorURL().toString()) %>"></div>

<c:choose>
	<c:when test="<%= !plugins.isEmpty() %>">
		
		<div class="entity-container plugin-container">
			<div class="entity-container-header">
				<div class="entity-container-column-title">
					<liferay-ui:message key="payment-plugin" />
				</div>
				<div class="entity-container-column-title">
					<liferay-ui:message key="seller.name" />
				</div>
				<div class="entity-container-column-title">
					<liferay-ui:message key="status" />
				</div>
			</div>

			<div class="entity-container-list">
				<%
				for (PaymentPlugin plugin : plugins) {
					List<PaymentPluginConfig> pluginConfigs = PaymentPluginConfigLocalServiceUtil
							.getPaymentPluginPaymentPluginConfigs(plugin.getPaymentPluginId());
				%>
				<portlet:renderURL var="pluginEditURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
					<portlet:param name="jspPage" value="/admin/edit_plugin.jsp" />
					<portlet:param name="pluginId" value="<%= String.valueOf(plugin.getPaymentPluginId()) %>" />
				</portlet:renderURL>
				
				<section class="entity">
					<div class="entity-summary">
						<h1><%= HtmlUtil.escape(plugin.getName(locale)) %></h1>
						<c:if test="<%= PaymentPluginPermission.contains(permissionChecker, plugin, ActionKeys.UPDATE) %>">
							<a href="<%= HtmlUtil.escape(pluginEditURL.toString()) %>" class="edit-icon" data-popup="single">
								<liferay-ui:message key="edit" />
							</a>
						</c:if>
						<c:choose>
							<c:when test="<%= plugin.isConfigured() %>">
								<div class="configured">
									<liferay-ui:message key="configured" />
								</div>
							</c:when>
							<c:otherwise>
								<div class="not-configured">
									<liferay-ui:message key="not-configured" />
								</div>
							</c:otherwise>
						</c:choose>
					</div>
					<div id="<portlet:namespace />pluginDetails_<%= plugin.getPaymentPluginId() %>" class="entity-details">
						<c:if test="<%= pluginConfigs.size() > 3 %>">
						<div class="entity-details-mask">
						</c:if>
							<%
							for (PaymentPluginConfig pluginConfig : pluginConfigs) {
								Seller seller = SellerLocalServiceUtil
										.getSeller(pluginConfig.getSellerId());
							%>
							<portlet:renderURL var="pluginConfigEditURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
								<portlet:param name="jspPage" value="/admin/edit_seller_plugin.jsp" />
								<portlet:param name="pluginConfigId" value="<%= String.valueOf(pluginConfig.getPaymentPluginConfigId()) %>" />
							</portlet:renderURL>
							
							<section class="entity-details-line">
								<h1><%= HtmlUtil.escape(seller.getName()) %></h1>
								<div class="entity-details-line-content">
									<c:choose>
										<c:when test="<%= pluginConfig.isConfigured() %>">
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
									<c:if test="<%= PaymentPluginConfigPermission.contains(permissionChecker, pluginConfig, ActionKeys.UPDATE) %>">
										<c:choose>
											<c:when test="<%= Validator.isNull(plugin.getSellerConfigParameters()) %>">
												<span class="edit-icon">
													<liferay-ui:message key="not-editable" />
												</span>
											</c:when>
											<c:otherwise>
												<a href="<%= HtmlUtil.escape(pluginConfigEditURL.toString()) %>" class="edit-icon" data-popup="single">
													<liferay-ui:message key="edit" />
												</a>
											</c:otherwise>
										</c:choose>
									</c:if>
								</div>
							</section>
							<%
							}
							%>
						<c:if test="<%= pluginConfigs.size() > 3 %>">
						</div>
							<div class="entity-details-toggler" data-toggle-target="#<portlet:namespace />pluginDetails_<%= plugin.getPaymentPluginId() %>">
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
			<liferay-ui:message key="no-plugin-found" />
		</div>

	</c:otherwise>
</c:choose>
