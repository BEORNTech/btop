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

<portlet:renderURL var="homeURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="/admin/home.jsp" />
</portlet:renderURL>

<div data-refresh-url="<%= HtmlUtil.escape(currentURL) %>"></div>

<section class="page page-home">
	<header class="page-header">
		<h1 class="page-title">
			<a href="<%= HtmlUtil.escape(homeURL.toString()) %>">
				<liferay-ui:message key="home" />
			</a>
		</h1>
	</header>

	<%
	List<Transaction> transactions = TransactionServiceUtil.search(company.getCompanyId(), 0, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, 
			true, 0, 3, new TransactionCreateDateComparator());
	%>
	<c:if test="<%= !transactions.isEmpty() %>">
		<section class="page-summary">
			<header>
				<h1>
					<liferay-ui:message key="last-transactions" />
				</h1>
			</header>
			<div class="clear"></div>
			<div class="entity-container-list">
			<%
			for (Transaction transaction : transactions) {
				PaymentPlugin plugin = null;
				try {
					plugin = PaymentPluginLocalServiceUtil
							.getPaymentPluginByApplicationId(transaction.getPaymentApplicationId());
				} catch (NoSuchPluginException e) {
				}
				Seller seller = SellerLocalServiceUtil
						.getSeller(transaction.getSellerId());
				
				List<TransactionLog> transactionLogs = TransactionLogLocalServiceUtil
						.getTransactionTransactionLogs(transaction.getTransactionId(), 
								QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	
				Currency currency = Currency.getInstance(transaction.getCurrencyCode());
				String currencySymbol = currency.getSymbol();
			%>
				<section class="entity <%= !transactionLogs.isEmpty() ? "has-details" : "" %>">
					<div class="entity-summary">
						<span><%= HtmlUtil.escape(seller.getName()) %></span>
						<div class="entity-status">
							<span class="amount-summary"><%= transaction.getAmount() %> <%= currencySymbol %></span>
							<span class="created-date"> - <%= dateTimeFormat.format(transaction.getCreateDate()) %></span>
							<c:if test="<%= !transactionLogs.isEmpty() %>">
								<span class="entity-details-toggler" data-toggle-target="#<portlet:namespace />transactionDetails_<%= transaction.getTransactionId() %>">
									<span></span>
								</span>
							</c:if>
						</div>
						<div class="clear"></div>
					</div>
					<c:if test="<%= !transactionLogs.isEmpty() %>">
						<div id="<portlet:namespace />transactionDetails_<%= transaction.getTransactionId() %>" class="entity-details">
							<table class="entity-history">
								<thead>
									<tr>
										<th><liferay-ui:message key="action-type" /></th>
										<th><liferay-ui:message key="amount" /></th>
										<th><liferay-ui:message key="date" /></th>
									</tr>
								</thead>
								<tbody>
									<%
									for (TransactionLog transactionLog : transactionLogs) {
									%>
										<tr>
											<td><liferay-ui:message key='<%= "transaction-log-type-" + transactionLog.getType() %>' /></td>
											<td><%= transactionLog.getAmount() + StringPool.SPACE + currencySymbol %></td>
											<td><%= dateTimeFormat.format(transactionLog.getCreateDate()) %></td>
										</tr>
									<%
									}
									%>
								</tbody>
							</table>
						</div>
					</c:if>
				</section>
			<%
			}
			%>
			</div>
			<span class="show-more-button" data-sidebar-button-proxy=".sidebar-button-transactions">
				<liferay-ui:message key="transactions" />
			</span>
		</section>
	</c:if>

	<%
	List<PaymentPlugin> plugins = PaymentPluginServiceUtil.search(company.getCompanyId(), 
			null, null, null, 0, 3, new PaymentPluginCreateDateComparator());
	%>
	<c:if test="<%= !plugins.isEmpty() %>">
		<section class="page-summary">
			<header>
				<h1>
					<liferay-ui:message key="last-plugins" />
				</h1>
			</header>
			<div class="clear"></div>
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
						<span><%= HtmlUtil.escape(plugin.getName(locale)) %></span>
						<div class="entity-status">
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
						<div class="clear"></div>
					</div>
				</section>
				<%
				}
				%>
			</div>
			<span class="show-more-button" data-sidebar-button-proxy=".sidebar-plugins-configuration">
				<liferay-ui:message key="plugins-configuration" />
			</span>
		</section>
	</c:if>

	<%
	List<Seller> sellers = SellerServiceUtil.search(company.getCompanyId(), null, true, 
			0, 3, new SellerCreateDateComparator());
	%>
	<c:if test="<%= !sellers.isEmpty() %>">
		<section class="page-summary">
			<portlet:renderURL var="addSellerURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
				<portlet:param name="jspPage" value="/admin/edit_seller.jsp" />
			</portlet:renderURL>
			<header>
				<h1>
					<liferay-ui:message key="last-sellers" />
				</h1>
				<c:if test="<%= PaymentAppPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_SELLER) %>">
					<a class="add-entity-button" href="<%= HtmlUtil.escape(addSellerURL.toString()) %>" data-popup="sequence"> 
						<liferay-ui:message key="add-seller" />
					</a>
				</c:if>
				<div class="clear"></div>
			</header>
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
	
				<section class="entity">
					<div class="entity-summary">
						<span><%= HtmlUtil.escape(seller.getName()) %></span>
						<div class="entity-status">
							<c:choose>
								<c:when test="<%= seller.isActive() %>">
									<span class="configured">
										<liferay-ui:message key="active" />
									</span>
								</c:when>
								<c:otherwise>
									<span class="not-configured">
										<liferay-ui:message key="inactive" />
									</span>
								</c:otherwise>
							</c:choose>
						
							<c:if test="<%= SellerPermission.contains(permissionChecker, seller, ActionKeys.UPDATE) %>">
								<a href="<%= HtmlUtil.escape(sellerEditURL.toString()) %>" class="edit-icon" data-popup="single">
									<liferay-ui:message key="edit" />
								</a>
							</c:if>
						</div>
						<div class="clear"></div>
					</div>
				</section>
				<%
				}
				%>
			</div>
			<span class="show-more-button" data-sidebar-button-proxy=".sidebar-button-sellers-configuration">
				<liferay-ui:message key="sellers-configuration" />
			</span>
		</section>
	</c:if>

	<section class="page-summary">
		<header>
			<h1>
				<liferay-ui:message key="analytics" />
			</h1>
		</header>
		<div class="clear"></div>
		<img src="/btop-portlet/admin/images/img-analytics.png" alt="" />
		<span class="show-more-button" data-sidebar-button-proxy=".sidebar-button-analytics">
			<liferay-ui:message key="analytics" />
		</span>
	</section>
</section>