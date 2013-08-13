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
boolean showSellerNavigation =
	PaymentPluginServiceUtil.searchCount(company.getCompanyId(), null, null, null) == 0
		&& PaymentMethodServiceUtil.searchCount(company.getCompanyId(), null) == 0;
%>

<portlet:renderURL var="homeURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="/admin/home.jsp" />
</portlet:renderURL>
<portlet:renderURL var="transactionsURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="/admin/transactions.jsp" />
</portlet:renderURL>
<portlet:renderURL var="sellersConfigurationURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="/admin/sellers_configuration.jsp" />
</portlet:renderURL>
<portlet:renderURL var="pluginsConfigurationURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="/admin/plugins_configuration.jsp" />
</portlet:renderURL>
<portlet:renderURL var="methodsConfigurationURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="/admin/methods_configuration.jsp" />
</portlet:renderURL>
<portlet:renderURL var="analyticsURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="jspPage" value="/admin/analytics.jsp" />
</portlet:renderURL>

<a class="help-link" href="<%= HtmlUtil.escape(PropsValues.HELP_URL) %>" target="_blank">
	<span><liferay-ui:message key="read-the-documentation" /></span>
</a>

<div id="<portlet:namespace />layoutContainer" class="layout-container">
	<nav id="<portlet:namespace />navigationContainer" class="sidebar">
		<ul>
			<li>
				<a href="<%= HtmlUtil.escape(homeURL.toString()) %>" 
					class="sidebar-button sidebar-button-home">
					<span><liferay-ui:message key="home" /></span>
				</a>
			</li>
			<li>
				<a href="<%= HtmlUtil.escape(transactionsURL.toString()) %>" 
					class="sidebar-button sidebar-button-transactions">
					<span><liferay-ui:message key="transactions" /></span>
				</a>
			</li>
			<li>
				<c:choose>
					<c:when test="<%= showSellerNavigation %>">
						<a href="<%= HtmlUtil.escape(sellersConfigurationURL.toString()) %>" 
							class="sidebar-button sidebar-button-configuration">
							<span><liferay-ui:message key="configuration" /></span>
						</a>
					</c:when>
					<c:otherwise>
						<span class="sidebar-button sidebar-button-configuration">
							<span><liferay-ui:message key="configuration" /></span>
						</span>
						<ul>
							<li>
								<a href="<%= HtmlUtil.escape(sellersConfigurationURL.toString()) %>" 
									class="sidebar-button sidebar-button-sellers-configuration">
									<span><liferay-ui:message key="sellers-configuration" /></span>
								</a>
							</li>
							<li>
								<a href="<%= HtmlUtil.escape(pluginsConfigurationURL.toString()) %>" 
									class="sidebar-button sidebar-plugins-configuration">
									<span><liferay-ui:message key="plugins-configuration" /></span>
								</a>
							</li>
							<li>
								<a href="<%= HtmlUtil.escape(methodsConfigurationURL.toString()) %>" 
									class="sidebar-button sidebar-methods-configuration">
									<span><liferay-ui:message key="methods-configuration" /></span>
								</a>
							</li>
						</ul>
					</c:otherwise>
				</c:choose>
			</li>
			<li>
				<a href="<%= HtmlUtil.escape(analyticsURL.toString()) %>" 
					class="sidebar-button sidebar-button-analytics">
					<span><liferay-ui:message key="analytics" /></span>
				</a>
			</li>
		</ul>
	</nav>
	
	<div id="<portlet:namespace />pageContainer" class="page-container" data-load-inside="">
	</div>
</div>

<div id="<portlet:namespace />popupWrapper" class="popup-wrapper aui-helper-hidden">
	<div class="popup-mask"></div>
	<div class="container">
		<div class="popup-button-close"><liferay-ui:message key="close" /></div>
		<div class="popup-button-previous"><liferay-ui:message key="previous" /></div>
		<div class="popup-button-next"><liferay-ui:message key="next" /></div>
		<div id="<portlet:namespace />popupContainer" class="popup-container">
		</div>
	</div>
</div>

<aui:script use="aui-base,aui-io,node-event-simulate,aui-loading-mask,gallery-dispatcher">

	var attrLoadTarget = 'data-load-target';
	var attrLoadInside = 'data-load-inside';
	var attrToggleTarget = 'data-toggle-target';
	var attrPopup = 'data-popup';
	var attrPopupButtonNextEvent = 'data-popup-button-next-event';
	var attrCheckboxGroup = 'data-checkbox-group';
	var attrRefreshUrl = 'data-refresh-url';
	var attrSidebarButtonProxyUrl = 'data-sidebar-button-proxy';

	var layoutContainer = A.one('#<portlet:namespace />layoutContainer');
	var navigationContainer = A.one('#<portlet:namespace />navigationContainer');
	var pageContainer = A.one('#<portlet:namespace />pageContainer');
	var popupWrapper = A.one('#<portlet:namespace />popupWrapper');
	var popupContainer = A.one('#<portlet:namespace />popupContainer');
	
	var loadLevel = 0;

	function beginLoading() {
		if (loadLevel == 0)
			layoutContainer.loadingmask.show();
		++loadLevel;
	}

	function endLoading() {
		--loadLevel;
		if (loadLevel == 0)
			layoutContainer.loadingmask.hide();
	}

	function loadArea(targetNode, url, method) {
		if (method == undefined)
			method = 'GET';

		beginLoading();

		new A.Dispatcher ({
			node: targetNode,
			uri: url,
			ioConfig: {
				on: {
					'end': function() {
						endLoading();
					}
				}
			}
		});
	}
	
	function loadAreaFromForm(targetNode, formNode) {
		var url = formNode.attr('action');
		var method = formNode.attr('method');
		if (method == undefined)
			method = 'POST';

		beginLoading();

		A.io.request(
			url,
			{
				cache: false,
				form: {
		            id: formNode,
		            useDisabled: true
		        },
				on: {
					success: function(event, id, obj) {
						var responseData = this.get('responseData');
						new A.Dispatcher ({
							node: targetNode,
							content: responseData
						});
						endLoading();
					}
				}
			}
		);
	}
	
	function autoRefresh() {
		layoutContainer.all('[' + attrRefreshUrl + ']').each(function(node) {
			var targetNode = node.ancestor('[' + attrLoadInside + ']');
			if (targetNode == null)
				return;

			var url = node.attr(attrRefreshUrl);

			beginLoading();
			new A.Dispatcher ({
				node: targetNode,
				uri: url,
				ioConfig: {
					on: {
						'end': function() {
							endLoading();
						}
					}
				}
			});
		});
	}

	function changePageNode(node) {
		var href = node.attr('href');
		navigationContainer.all('.sidebar-button-active').removeClass('sidebar-button-active');
		loadArea(pageContainer, href);
		node.addClass('sidebar-button-active');
		closePopup();
	}
	
	function openPopup(url, popupType) {
		if (popupType == 'single') {
			popupWrapper.all('.popup-button-previous, .popup-button-next').hide();

		} else if (popupType == 'sequence') {
			popupWrapper.all('.popup-button-previous, .popup-button-next').show();

		}
		popupWrapper.show();
		loadArea(popupContainer, url);
	}
	
	function nextPopupPage(url, method) {
		loadArea(popupContainer, url, method);
	}

	function nextPopupPageFromForm(formNode) {
		loadAreaFromForm(popupContainer, formNode);
	}

	function previousPopupPage() {
		var breadcrumbPreviousPageLink = popupContainer.one('.breadcrumb .previous-step a');
		if (breadcrumbPreviousPageLink != null) {
			var url = breadcrumbPreviousPageLink.attr('href');
			loadArea(popupContainer, url, 'GET');

		} else {
			closePopup();
		}
	}

	function closePopup() {
		if (popupWrapper.hasClass('aui-helper-hidden'))
			return;

		popupWrapper.hide();
		popupContainer.html('');
		autoRefresh();
	}

	// --- Navigation events ---

	// Change page when navigation is clicked
	navigationContainer.delegate('click', function(event) {
		var node = event.currentTarget;
		changePageNode(node);
		event.preventDefault();
	}, 'a.sidebar-button');

	// Navigation button proxies
	pageContainer.delegate('click', function(event) {
		var node = event.currentTarget;
		var target = node.attr(attrSidebarButtonProxyUrl);
		var targetNode = navigationContainer.one(target);
		if (targetNode == null)
			return;

		changePageNode(targetNode);
		event.preventDefault();
	}, '[' + attrSidebarButtonProxyUrl + ']');

	// --- Page events ---

	// Load links url in target node
	pageContainer.delegate('click', function(event) {
		var node = event.currentTarget;
		var href = node.attr('href');
		var target = node.attr(attrLoadTarget);
		loadArea(layoutContainer.one(target), href);
		event.preventDefault();
	}, 'a[' + attrLoadTarget + ']');

	// Load links url inside the parent node
	pageContainer.delegate('click', function(event) {
		var node = event.currentTarget;
		var targetNode = node.ancestor('[' + attrLoadInside + ']');
		if (targetNode == undefined)
			return;

		var href = node.attr('href');
		loadArea(targetNode, href);
		event.preventDefault();
	}, 'a:not([' + attrLoadTarget + ']):not([' + attrPopup + '])');

	// Load form result in target node
	pageContainer.delegate('submit', function(event) {
		var node = event.currentTarget;
		var target = node.attr(attrLoadTarget);
		loadAreaFromForm(layoutContainer.one(target), node);
		event.preventDefault();
	}, 'form[' + attrLoadTarget + ']');

	// Toggle class on target node
	pageContainer.delegate('click', function(event) {
		var node = event.currentTarget;
		var target = node.attr(attrToggleTarget);
		var targetNode = pageContainer.one(target);
		targetNode.toggleClass('toggled');
		node.toggleClass('toggled');
	}, '[' + attrToggleTarget + ']');

	// --- Popup events ---

	// Open popup with url
	pageContainer.delegate('click', function(event) {
		var node = event.currentTarget;
		var href = node.attr('href');
		var popupType = node.attr(attrPopup);
		openPopup(href, popupType);
		event.preventDefault();
	}, 'a[' + attrPopup + ']');

	// Close popup
	popupWrapper.delegate('click', function(event) {
		closePopup();
		event.preventDefault();
	}, '.popup-button-close');

	// Move popup to previous page
	popupWrapper.delegate('click', function(event) {
		previousPopupPage();
		event.preventDefault();
	}, '.popup-button-previous');

	// Move popup to next page
	popupWrapper.delegate('click', function(event) {
		var next = popupContainer.one('[' + attrPopupButtonNextEvent + ']');
		if (next != undefined) {
			var eventName = next.attr(attrPopupButtonNextEvent);
			next.simulate(eventName);

		} else {
			closePopup();
		}
		event.preventDefault();
	}, '.popup-button-next');

	// Load links from popup inside it
	popupContainer.delegate('click', function(event) {
		var node = event.currentTarget;
		var href = node.attr('href');
		var target = node.attr('target');
		if (target != '')
			return;
		nextPopupPage(href);
		event.preventDefault();
	}, 'a');
	
	// Load form result from popup inside it
	popupContainer.delegate('submit', function(event) {
		var node = event.currentTarget;
		nextPopupPageFromForm(node);
		event.preventDefault();
	}, 'form');

	// --- Misc ---
	
	// Only keep one checkbox checked, but allow unchecking them all
	pageContainer.delegate('click', function(event) {
		var node = event.currentTarget;
		var checked = node.getDOMNode().checked;
		if (!checked)
			return;

		node.siblings('input[type=checkbox]').each(function(siblingNode){
			siblingNode.getDOMNode().checked = false;
		});
	}, '[' + attrCheckboxGroup + '] input[type=checkbox]');

	// Initialize

	layoutContainer.plug(A.LoadingMask, {
		target: layoutContainer
	});

	changePageNode(navigationContainer.one('.sidebar-button-home'));
	
	<c:if test="<%= PaymentPluginServiceUtil.searchCount(company.getCompanyId(), null, null, null) == 0 %>">
		<portlet:renderURL var="noPluginFoundURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
			<portlet:param name="jspPage" value="/admin/no_plugin_found.jsp" />
		</portlet:renderURL>
		
		openPopup('<%= HtmlUtil.escapeJS(noPluginFoundURL.toString()) %>', 'single');
	</c:if>
</aui:script>