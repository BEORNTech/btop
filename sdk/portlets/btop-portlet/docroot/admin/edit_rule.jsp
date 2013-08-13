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

<%@page import="com.beorn.onlinepayment.rule.payment.TransactionConditionSubject"%>
<%@page import="com.beorn.onlinepayment.rule.payment.TransactionParameterUtil"%>
<%@page import="org.json.JSONObject"%>

<%@ include file="/admin/init.jsp" %>

<%
long sellerId = ParamUtil.getLong(request, "sellerId");
long ruleId = ParamUtil.getLong(request, "ruleId");

Rule rule = null;
if (ruleId != 0) {
	try {
		rule = RuleLocalServiceUtil.getRule(ruleId);

		PaymentPluginConfig paymentPluginConfig = PaymentPluginConfigLocalServiceUtil
				.getPaymentPluginConfig(rule.getPaymentPluginConfigId());

		if (paymentPluginConfig.getSellerId() != sellerId)
			rule = null;
		
	} catch (NoSuchPluginException e) {
	}
}

if (rule == null) {
	SellerPermission.check(permissionChecker, sellerId, ActionKeys.UPDATE);
}

List<PaymentPluginConfig> paymentPluginConfigs = PaymentPluginConfigLocalServiceUtil.getSellerPaymentPluginConfigs(sellerId);
%>

<portlet:renderURL var="rulesConfigurationURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="/admin/rules_configuration.jsp" />
	<portlet:param name="sellerId" value="<%= String.valueOf(sellerId) %>" />
</portlet:renderURL>

<portlet:actionURL var="editRuleURL" name="editRule" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="/admin/edit_rule.jsp" />
	<portlet:param name="sellerId" value="<%= String.valueOf(sellerId) %>" />
	<portlet:param name="ruleId" value="<%= String.valueOf(ruleId) %>" />
</portlet:actionURL>

<section class="page page-rules">
	<header class="page-header">
		<h1 class="page-title">
			<a href="<%= HtmlUtil.escape(rulesConfigurationURL.toString()) %>">
				<liferay-ui:message key="rules" />
			</a>
		</h1>
	</header>

	<div class="entity-container rule-container">
		<div class="entity-container-header">
			<div class="entity-container-column-title">
				<liferay-ui:message key="rule-configuration" />
			</div>
		</div>
		<div class="entity-container-list">

			<aui:form action="<%= editRuleURL %>" cssClass="edit-rule" method="post" onSubmit="return false;" name="<%= randomNamespace %>" data-load-target='<%= "#" + portletResponse.getNamespace() + "pageContainer" %>'>
				<aui:model-context bean="<%= rule %>" model="<%= Rule.class %>" />
				<portlet:renderURL var="successURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
					<portlet:param name="jspPage" value="/admin/rules_configuration.jsp" />
					<portlet:param name="sellerId" value="<%= String.valueOf(sellerId) %>" />
				</portlet:renderURL>
				<aui:input name="successURL" type="hidden" value="<%= successURL %>" />

				<div class="rule-editor-layout">
					<div class="rule-editor-layout-column left">

						<aui:input type="hidden" name="content" />
						<div id="<portlet:namespace />ruleEditorContainer">
						</div>

					</div>
					<div class="rule-editor-layout-column right">
					
						<aui:select name="paymentPluginConfigId" label="payment-plugin" cssClass="payment-plugin-selector">
							<%
							for (PaymentPluginConfig paymentPluginConfig : paymentPluginConfigs) {
								PaymentPlugin paymentPlugin = PaymentPluginLocalServiceUtil.getPaymentPlugin(paymentPluginConfig.getPaymentPluginId());
							%>
							<aui:option 
								label="<%= paymentPlugin.getName(locale) %>" 
								value="<%= paymentPluginConfig.getPaymentPluginConfigId() %>" 
								selected="<%= rule != null && paymentPluginConfig.getPaymentPluginConfigId() == rule.getPaymentPluginConfigId() %>"
							/>
							<%	
							}
							%>
						</aui:select>
						
					</div>
				</div>

				<aui:button-row>
					<aui:button type="submit" />
					<a href="<%= HtmlUtil.escape(successURL.toString()) %>"><liferay-ui:message key="back" /></a>
				</aui:button-row>
			</aui:form>
	
	</div>
</section>

<aui:script position="inline">

	<%
	JSONObject conditionsDeclaration = RuleUtil.getConditionRegistry().toJSONObject(portletConfig, themeDisplay.getLocale());
	JSONObject translations = new JSONObject();
    for (Iterator<String> conditionsDeclarationIterator = conditionsDeclaration.keys(); conditionsDeclarationIterator.hasNext();) {
        String key = conditionsDeclarationIterator.next() + ".name";
        String value = LanguageUtil.get(pageContext, key);
        translations.put(key, value);
	}
    translations.put("if", LanguageUtil.get(pageContext, "condition.if"));
    translations.put("if-not", LanguageUtil.get(pageContext, "condition.if-not"));
    translations.put("any", LanguageUtil.get(pageContext, "condition.any"));
    translations.put("all", LanguageUtil.get(pageContext, "condition.all"));
    translations.put("not-all", LanguageUtil.get(pageContext, "condition.not-all"));
    translations.put("none", LanguageUtil.get(pageContext, "condition.none"));
	%>

	YUI().use('btop-rule-editor', 'json-stringify', function(Y) {
		var B = Y.Beorn;

		var editor = new B.BtopRuleEditor({
			boundingBox: '#<portlet:namespace />ruleEditorContainer',
			conditionsDeclaration: <%= conditionsDeclaration.toString() %>,
			translations: <%= translations.toString() %>,
			conditionClassNameKey: '<%= RuleUtil.CONDITION_CLASS_NAME_KEY %>',
			conditionListType: '<%= Condition[].class.getName() %>',
			conditionType: '<%= Condition.class.getName() %>',
			fieldMapping: {
				'<%= String.class.getName() %>': B.RuleEditor.TextField,
				'double': B.RuleEditor.TextField,
				'boolean': B.RuleEditor.Checkbox
			},
			fieldTypeToOperator: {
				'<%= AndCondition.class.getName() %>': 'all',
				'<%= OrCondition.class.getName() %>': 'any'
			},
			negatedFieldTypeToOperator: {
				'<%= AndCondition.class.getName() %>': 'not-all',
				'<%= OrCondition.class.getName() %>': 'none'
			},
			operatorToFieldType: {
				'all': {type:'<%= AndCondition.class.getName() %>', negated:false},
				'any': {type:'<%= OrCondition.class.getName() %>', negated:false},
				'not-all': {type:'<%= AndCondition.class.getName() %>', negated:true},
				'none': {type:'<%= OrCondition.class.getName() %>', negated:true}
			},
			negationBuilder: function(condition) {
				return {
					'<%= RuleUtil.CONDITION_CLASS_NAME_KEY %>': '<%= NotCondition.class.getName() %>', 
					condition: condition
				};
			},
			autocompletes: {
				parameterKey: {
					source: 'parameters'
				},
				parameterValue: {
					dependsOn: 'key',
					source: {
						'<%= TransactionConditionSubject.TRANSACTION_CURRENCY_CODE %>': 'currencyCodes',
						'<%= TransactionConditionSubject.COUNTRY_CODE %>': 'countryCodes'
					}
				}
			},
			autocompleteSources: {
				parameters: ['<%= StringUtil.merge(TransactionParameterUtil.getAutocompletableParameterKeys(sellerId), "','") %>'],
				currencyCodes: ['<%= StringUtil.merge(AutocompleteUtil.getCurrencyCodes(), "','") %>'],
				countryCodes: ['<%= StringUtil.merge(AutocompleteUtil.getCountryCodes(), "','") %>']
			}
		});

		editor.render();
		<c:choose>
			<c:when test="<%= rule != null && Validator.isNotNull(rule.getContent()) %>">
				editor.importCondition(<%= rule.getContent() %>);
			</c:when>
			<c:otherwise>
				editor.add(editor.createDefaultCondition());
			</c:otherwise>
		</c:choose>

		var form = Y.one('#<portlet:namespace /><%= randomNamespace %>');
		var contentField = form.one('#<portlet:namespace />content');
		
		Y.one('#<portlet:namespace /><%= randomNamespace %>').on('submit', function() {
			contentField.set('value', Y.JSON.stringify(editor.getCondition()));
			return false;
		});

	});
</aui:script>
