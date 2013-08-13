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
String activeString = ParamUtil.getString(request, "active");
Boolean active = Validator.isNotNull(activeString) ? GetterUtil.getBoolean(activeString) : null;

PortletURL portletURL = renderResponse.createRenderURL();
portletURL.setParameter("jspPage", "/admin/sellers_list.jsp");
portletURL.setParameter("keywords", keywords);
portletURL.setParameter("active", activeString);

SearchContainer searchContainer = new SearchContainer(
		renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, 
		SearchContainer.DEFAULT_DELTA, portletURL, null, null);

List<Seller> sellers = SellerServiceUtil.search(
		company.getCompanyId(), keywords, active, searchContainer.getStart(), searchContainer.getEnd(), null);
int sellerCount = SellerServiceUtil.searchCount(company.getCompanyId(), keywords, active);

searchContainer.setResults(sellers);
searchContainer.setTotal(sellerCount);
%>

<div data-refresh-url="<%= HtmlUtil.escape(searchContainer.getIteratorURL().toString()) %>"></div>

<c:choose>
	<c:when test="<%= !sellers.isEmpty() %>">
		
		<div class="entity-container seller-container">
			<div class="entity-container-header">
				<div class="entity-container-column-title">
					<liferay-ui:message key="seller.name" />
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
				for (Seller seller : sellers) {
					List<PaymentPluginConfig> pluginConfigs = PaymentPluginConfigLocalServiceUtil
							.getSellerPaymentPluginConfigs(seller.getSellerId());
				%>
				<portlet:renderURL var="sellerEditURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
					<portlet:param name="jspPage" value="/admin/edit_seller.jsp" />
					<portlet:param name="sellerId" value="<%= String.valueOf(seller.getSellerId()) %>" />
				</portlet:renderURL>
				<portlet:renderURL var="sellerRulesURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
					<portlet:param name="jspPage" value="/admin/rules_configuration.jsp" />
					<portlet:param name="sellerId" value="<%= String.valueOf(seller.getSellerId()) %>" />
				</portlet:renderURL>
			
				<section class="entity">
					<div class="entity-summary">
						<h1>
							<%= HtmlUtil.escape(seller.getName()) %>
						</h1>
						<c:if test="<%= SellerPermission.contains(permissionChecker, seller, ActionKeys.UPDATE) %>">
							<a href="<%= HtmlUtil.escape(sellerEditURL.toString()) %>" class="edit-icon" data-popup="single">
								<liferay-ui:message key="edit" />
							</a>
							<a href="<%= HtmlUtil.escape(sellerRulesURL.toString()) %>" class="edit-icon rules-icon" data-load-target="#<portlet:namespace />pageContainer">
								<liferay-ui:message key="rules" />
							</a>
						</c:if>
						<span class="entity-id">#<%= seller.getSellerId() %></span>
						<c:choose>
							<c:when test="<%= seller.isActive() %>">
								<div class="configured">
									<liferay-ui:message key="active" />
								</div>
							</c:when>
							<c:otherwise>
								<div class="not-configured">
									<liferay-ui:message key="inactive" />
								</div>
							</c:otherwise>
						</c:choose>
					</div>
					<div id="<portlet:namespace />sellerDetails_<%= seller.getSellerId() %>" class="entity-details">
						<c:if test="<%= pluginConfigs.size() > 3 %>">
						<div class="entity-details-mask">
						</c:if>
							<%
							for (PaymentPluginConfig pluginConfig : pluginConfigs) {
								PaymentPlugin paymentPlugin = PaymentPluginLocalServiceUtil
										.getPaymentPlugin(pluginConfig.getPaymentPluginId());
							%>
							<portlet:renderURL var="pluginConfigEditURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
								<portlet:param name="jspPage" value="/admin/edit_seller_plugin.jsp" />
								<portlet:param name="pluginConfigId" value="<%= String.valueOf(pluginConfig.getPaymentPluginConfigId()) %>" />
							</portlet:renderURL>
	
							<section class="entity-details-line">
								<h1><%= HtmlUtil.escape(paymentPlugin.getName(locale)) %></h1>
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
											<c:when test="<%= Validator.isNull(paymentPlugin.getSellerConfigParameters()) %>">
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
							<div class="entity-details-toggler" data-toggle-target="#<portlet:namespace />sellerDetails_<%= seller.getSellerId() %>">
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
			<liferay-ui:message key="no-seller-found" />
		</div>

	</c:otherwise>
</c:choose>
