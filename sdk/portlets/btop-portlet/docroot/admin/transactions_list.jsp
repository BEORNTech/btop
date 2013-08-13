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
long methodId = ParamUtil.getLong(request, "methodId");
long sellerId = ParamUtil.getLong(request, "sellerId");

long groupId = 0;
Long userId = null;
String applicationId = null;
String paymentApplicationId = null;
Long status = null;
Double amountMin = null;
Double amountMax = null;
String currencyCode = null;
Date dateMin = null;
Date dateMax = null;
String dateType = null;
boolean isAndOperator = true;

PortletURL portletURL = renderResponse.createRenderURL();
portletURL.setParameter("jspPage", "/admin/transactions_list.jsp");
portletURL.setParameter("keywords", keywords);
portletURL.setParameter("methodId", String.valueOf(methodId));
portletURL.setParameter("sellerId", String.valueOf(sellerId));

SearchContainer searchContainer = new SearchContainer(
		renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, 
		SearchContainer.DEFAULT_DELTA, portletURL, null, null);

List<Transaction> transactions = TransactionServiceUtil
	.search(companyId, groupId, userId, keywords, sellerId, methodId, applicationId, paymentApplicationId, status, 
			amountMin, amountMax, currencyCode, dateMin, dateMax, dateType, isAndOperator, 
			searchContainer.getStart(), searchContainer.getEnd(), null);

int transactionCount = TransactionServiceUtil
	.searchCount(companyId, groupId, userId, keywords, sellerId, methodId, applicationId, paymentApplicationId, status, 
			amountMin, amountMax, currencyCode, dateMin, dateMax, dateType, isAndOperator);

searchContainer.setResults(transactions);
searchContainer.setTotal(transactionCount);
%>

<div data-refresh-url="<%= HtmlUtil.escape(searchContainer.getIteratorURL().toString()) %>"></div>

<c:choose>
	<c:when test="<%= !transactions.isEmpty() %>">
		
		<div class="entity-container transaction-container">
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
					<span class="created-date">
						<%= dateTimeFormat.format(transaction.getCreateDate()) %>
					</span>
					<div class="entity-summary">
						<span><%= transaction.getTransactionId() %></span>
						<span><%= plugin != null ? HtmlUtil.escape(plugin.getName(locale)) : "---" %></span>
						<span><%= HtmlUtil.escape(seller.getName()) %></span>
						<div class="entity-status">
							<span class="amount-summary"><liferay-ui:message key="transaction-amount-summary" arguments="<%= new Object[] {currencySymbol, transaction.getAmount(), transaction.getAmountPaid(), transaction.getAmountRefunded()} %>" /></span>
							<span class="status"> - <liferay-ui:message key='<%= "transaction-status-" + transaction.getStatus() %>' /></span>
							<c:if test="<%= !transactionLogs.isEmpty() %>">
								<span class="entity-details-toggler" data-toggle-target="#<portlet:namespace />transactionDetails_<%= transaction.getTransactionId() %>">
									<span></span>
								</span>
							</c:if>
						</div>
					</div>
					<c:if test="<%= !transactionLogs.isEmpty() %>">
						<div id="<portlet:namespace />transactionDetails_<%= transaction.getTransactionId() %>" class="entity-details">
							<table class="entity-history">
								<caption><liferay-ui:message key="history" /></caption>
								<thead>
									<tr>
										<th><liferay-ui:message key="remote-id" /></th>
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
											<td><%= HtmlUtil.escape(transactionLog.getRemoteId()) %></td>
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
			<liferay-ui:search-paginator type="aggregator" searchContainer="<%= searchContainer %>" />
		</div>
		
	</c:when>
	<c:otherwise>

		<div class="portlet-msg-info">
			<liferay-ui:message key="no-transaction-found" />
		</div>

	</c:otherwise>
</c:choose>
