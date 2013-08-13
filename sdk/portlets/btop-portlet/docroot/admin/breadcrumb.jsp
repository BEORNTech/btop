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
String[] steps = StringUtil.split(ParamUtil.getString(request, "steps"));
String[] stepUrls = StringUtil.split(ParamUtil.getString(request, "stepUrls"));
String flowType = ParamUtil.getString(request, "flowType", stepUrls.length >= 1 ? "single" : "sequence");
int currentStep = ParamUtil.getInteger(request, "currentStep");
%>

<div class="breadcrumb">
	<c:if test='<%= flowType.equals("single") && stepUrls.length > 1 %>'>
		<div class="breadcrumb-hint">
			<liferay-ui:message key="select-the-step-you-want-to-modify"/>
		</div>
	</c:if>
	<div class="breadcrumb-content">
		<div class="breadcrumb-line">&nbsp;</div>
		<div class="breadcrumb-steps">
			<%
			for (int i = 0; i < steps.length; ++i) {
				boolean stepCompleted = SessionMessages.contains(portletSession, steps[i] + ".success");
			%>
			<div class="breadcrumb-step <%= i == currentStep ? "current-step" : "" %> <%= stepCompleted ? "completed-step" : "" %> <%= i + 1 == currentStep ? "previous-step" : "" %>" >
				<div class="breadcrumb-step-content">
					<c:choose>
						<c:when test='<%= stepUrls.length > i && (flowType.equals("single") && i != currentStep || flowType.equals("sequence") && i < currentStep) %>'>
							<a href="<%= HtmlUtil.escape(stepUrls[i]) %>" class="breadcrumb-step-name"><liferay-ui:message key='<%= "breadcrumb." + steps[i] %>' /></a>
						</c:when>
						<c:otherwise>
							<span class="breadcrumb-step-name"><liferay-ui:message key='<%= "breadcrumb." + steps[i] %>' /></span>
						</c:otherwise>
					</c:choose>
					<c:if test="<%= stepCompleted %>">
						<span class="completed-step-icon"><liferay-ui:message key="completed-step"/></span>
					</c:if>
				</div>
			</div>
			<%
			}
			%>
		</div>
	</div>
</div>