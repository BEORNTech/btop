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

<portlet:renderURL var="analyticsURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="/admin/analytics.jsp" />
</portlet:renderURL>

<section class="page page-analytics">
	<header class="page-header">
		<h1 class="page-title">
			<a href="<%= HtmlUtil.escape(analyticsURL.toString()) %>">
				<liferay-ui:message key="analytics" />
			</a>
		</h1>
	</header>

	<div class="portlet-msg-info">
		<div class="portlet-msg-soon">
			<liferay-ui:message key="coming-soon" />
		</div>
	</div>
</section>
