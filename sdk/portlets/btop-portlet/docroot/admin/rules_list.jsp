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

<%@page import="com.beorn.onlinepayment.rule.exception.RuleException"%>
<%@page import="org.json.JSONObject"%>
<%@ include file="/admin/init.jsp" %>

<%
long sellerId = ParamUtil.getLong(request, "sellerId");
SellerPermission.check(permissionChecker, sellerId, ActionKeys.VIEW);

String keywords = ParamUtil.getString(request, "keywords");

PortletURL portletURL = renderResponse.createRenderURL();
portletURL.setParameter("jspPage", "/admin/rules_list.jsp");
portletURL.setParameter("keywords", keywords);
portletURL.setParameter("sellerId", String.valueOf(sellerId));

SearchContainer searchContainer = new SearchContainer(
		renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, 
		100, portletURL, null, null);

List<Rule> rules = RuleLocalServiceUtil
		.getSellerRules(sellerId, searchContainer.getStart(), searchContainer.getEnd(), null);

int rulesCount = RuleLocalServiceUtil
		.getSellerRulesCount(sellerId);

searchContainer.setResults(rules);
searchContainer.setTotal(rulesCount);
%>

<div data-refresh-url="<%= HtmlUtil.escape(searchContainer.getIteratorURL().toString()) %>"></div>

<div class="entity-container rule-container">
	<div class="entity-container-header">
		<div class="entity-container-column-title">
			<liferay-ui:message key="payment-plugin" />
		</div>
		<div class="entity-container-column-title">
			<liferay-ui:message key="rules" />
		</div>
	</div>

	<div id="<portlet:namespace />sortableRuleContainer" class="entity-container-list">
	<%
	for (Rule rule : rules) {
		PaymentPluginConfig pluginConfig = PaymentPluginConfigLocalServiceUtil
				.getPaymentPluginConfig(rule.getPaymentPluginConfigId());

		PaymentPlugin plugin = PaymentPluginLocalServiceUtil
					.getPaymentPlugin(pluginConfig.getPaymentPluginId());
	%>
		<section class="entity sortable-entity" data-rule-id="<%= rule.getRuleId() %>" data-priority="<%= rule.getPriority() %>">
			<div class="rules">
				<span class="rules-summary">
					<%
					try {
						String ruleExplanation = RuleUtil.explain(rule.getContent(), portletConfig, locale);
					%>
						<%= ruleExplanation %>
					<%
					} catch (RuleException e) {
					%>
						<div class="portlet-msg-error"><liferay-ui:message key="invalid-rule" /></div>
					<%
					}
					%>
				</span>
			</div>
			<div class="entity-summary">
				<span><%= HtmlUtil.escape(plugin.getName(locale)) %></span>
			</div>
			<c:if test="<%= SellerPermission.contains(permissionChecker, sellerId, ActionKeys.UPDATE) %>">
				<div class="entity-actions">
					<span class="drag-handle">
						<liferay-ui:message key="drag-to-sort" />
					</span>
					<portlet:renderURL var="editRuleURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
						<portlet:param name="jspPage" value="/admin/edit_rule.jsp" />
						<portlet:param name="sellerId" value="<%= String.valueOf(sellerId) %>" />
						<portlet:param name="ruleId" value="<%= String.valueOf(rule.getRuleId()) %>" />
					</portlet:renderURL>
					<a href="<%= HtmlUtil.escape(editRuleURL.toString()) %>" class="edit-icon" data-load-target="#<portlet:namespace />pageContainer">
						<liferay-ui:message key="edit" />
					</a>
					<portlet:actionURL name="deleteRule" var="deleteRuleURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
						<portlet:param name="jspPage" value="/admin/rules_list.jsp" />
						<portlet:param name="ruleId" value="<%= String.valueOf(rule.getRuleId()) %>" />
						<portlet:param name="successURL" value="<%= portletURL.toString() %>" />
					</portlet:actionURL>
					<a href="<%= HtmlUtil.escape(deleteRuleURL.toString()) %>" class="delete-icon">
						<liferay-ui:message key="delete" />
					</a>
				</div>
			</c:if>
		</section>
	<%
	}
	%>
		<section class="entity default-entity">
			<div class="rules">
				<span class="rules-summary">
					<liferay-ui:message key="default-plugin" />
				</span>
			</div>
			<div class="entity-summary">
				<c:choose>
					<c:when test="<%= SellerPermission.contains(permissionChecker, sellerId, ActionKeys.UPDATE) %>">
						<%
						List<PaymentPluginConfig> paymentPluginConfigs = PaymentPluginConfigLocalServiceUtil.getSellerPaymentPluginConfigs(sellerId);
						PaymentPluginConfig defaultPaymentPluginConfig = PaymentPluginConfigLocalServiceUtil.getSellerDefaultPaymentPluginConfig(sellerId);
						%>
						<aui:select name="defaultPaymentPluginConfigId" label="payment-plugin" showEmptyOption="true" cssClass="default-payment-plugin-selector">
							<%
							for (PaymentPluginConfig paymentPluginConfig : paymentPluginConfigs) {
								PaymentPlugin paymentPlugin = PaymentPluginLocalServiceUtil.getPaymentPlugin(paymentPluginConfig.getPaymentPluginId());
							%>
							<aui:option 
								label="<%= paymentPlugin.getName(locale) %>" 
								value="<%= paymentPluginConfig.getPaymentPluginConfigId() %>" 
								selected="<%= defaultPaymentPluginConfig != null && defaultPaymentPluginConfig.getPaymentPluginConfigId() == paymentPluginConfig.getPaymentPluginConfigId() %>"
							/>
							<%	
							}
							%>
						</aui:select>
					</c:when>
					<c:otherwise>
						<liferay-ui:message key="default-plugin" />
					</c:otherwise>
				</c:choose>
			</div>
			<c:if test="<%= SellerPermission.contains(permissionChecker, sellerId, ActionKeys.UPDATE) %>">
				<div class="entity-actions">
				</div>
			</c:if>
		</section>
	</div>
	<liferay-ui:search-paginator type="aggregator" searchContainer="<%= searchContainer %>" />
</div>

<c:if test="<%= SellerPermission.contains(permissionChecker, sellerId, ActionKeys.UPDATE) %>">
	<aui:script use="aui-base,dd-constrain,sortable,aui-io" position="inline">
		<portlet:actionURL name="editRulesPriority" var="editRulesPriorityURL">
			<portlet:param name="sellerId" value='<%= String.valueOf(sellerId) %>' />
		</portlet:actionURL>

		var sortUrl = '<%= HtmlUtil.escapeJS(editRulesPriorityURL.toString()) %>';
		var container = A.one('#<portlet:namespace />sortableRuleContainer');
		var nodeSelector = '.entity.sortable-entity';
		var dragHandleSelector = '.drag-handle';
		
		var sortable = new A.Sortable({
			container: container,
			nodes: nodeSelector,
			opacity: '0.2',
			handles: [dragHandleSelector]
		});
		
		var sortableDD = sortable.delegate.dd;
		
		sortableDD.after({
			'drag:end': function(event) {
				var drag = event.target;
				var dragNode = drag.get('dragNode');

				var nodes = container.all(nodeSelector);
				var data = {};

				nodes.each(
					function(item, index, collection) {
						var priority = nodes.size() - index;
						data['rulePriority_' + item.attr('data-rule-id')] = priority;
						item.attr('data-priority', priority);
					}
				);
				
				disableSort();
				A.io.request(sortUrl, {
					cache: false,
					data: data,
					on: {
						success: function(event, id, obj) {
							enableSort();
						}
					}
				});
			}
		});

		sortableDD.plug(
			A.Plugin.DDConstrained,
			{
				constrain: container,
				stickY: true
			}
		);
		
		function disableSort() {
			container.all(dragHandleSelector).hide();
		}
		
		function enableSort() {
			container.all(dragHandleSelector).show();
		}
		
		<portlet:actionURL name="editDefaultPluginConfig" var="editDefaultPluginConfigURL">
			<portlet:param name="sellerId" value='<%= String.valueOf(sellerId) %>' />
		</portlet:actionURL>
		var defaultPaymentPluginConfigSelect = A.one('#<portlet:namespace />defaultPaymentPluginConfigId');
		var defaultPluginConfigUrl = '<%= HtmlUtil.escapeJS(editDefaultPluginConfigURL.toString()) %>';
		
		defaultPaymentPluginConfigSelect.on('change', function(event) {
			disableSort();
			A.io.request(defaultPluginConfigUrl, {
				cache: false,
				data: {
					defaultPaymentPluginConfigId: defaultPaymentPluginConfigSelect.val()
				},
				on: {
					success: function(event, id, obj) {
						enableSort();
					}
				}
			});
		});

	</aui:script>
</c:if>
