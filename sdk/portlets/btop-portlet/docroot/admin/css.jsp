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

<%@ include file="/css_init.jsp" %>

.yui3-dropdown {position:absolute;}
.yui3-dropdown-hidden {visibility:hidden;}

body {background:#fff !important;}
	#breadcrumbs {display:none;}
	#wrapper div.panel-page-body {padding:0 0 0 244px !important;}

.payment-admin-portlet {}

	.payment-admin-portlet .portlet-msg-info {width:20%; min-width:250px; border:0 none; color:#155B86; background:transparent; padding:20px; font-size:200%; text-align:center; border-radius:10px; box-shadow:0 1px 3px rgba(0, 0, 0, 0.4); background:-moz-linear-gradient(top, #ffffff 0%, #e5e5e5); background:-webkit-gradient(linear, left top, left bottom, from(#ffffff), to(#e5e5e5)); background-image:-ms-linear-gradient(top, #FFFFFF 0%, #E5E5E5 100%); background:linear-gradient(top, #ffffff, #e5e5e5);}
	.payment-admin-portlet .portlet-msg-error {border:0 none; color:#FF0000; background:url('/btop-portlet/admin/images/error.png') no-repeat scroll 6px 50% transparent;}
		.payment-admin-portlet .portlet-msg-soon {padding-top:40px; background:url('/btop-portlet/admin/images/icon-coming-soon.png') scroll no-repeat top center transparent;}

	.payment-admin-portlet .portlet-topper {background:#FDFDFD; border-bottom:1px solid #CECECE; height:96px; padding:44px 0 0 35px; border-radius:0;}
		.payment-admin-portlet .portlet-title {height: 55px; background:url('/btop-portlet/admin/images/logo.png') scroll no-repeat left center transparent;}
			.payment-admin-portlet .portlet-title-text {font-size:0;}
	.payment-admin-portlet .portlet-content {padding:0;}
	
	.payment-admin-portlet .help-link {position:absolute; top:50px; right:50px; width:40px; height:40px; background:url('/btop-portlet/admin/images/btn-help.png') scroll no-repeat center center transparent;}
		.payment-admin-portlet .help-link span {display:none; background:#fff; border:1px solid #888888; border-radius:4px; padding:5px 10px; position:absolute; right:-20px; top:45px; white-space:nowrap;}
		.payment-admin-portlet .help-link:hover span {display:block;}

	.payment-admin-portlet .layout-container {display:table; width:100%;}
		
		.payment-admin-portlet .sidebar,
		.payment-admin-portlet .page-container {display:table-cell; vertical-align: top;}
		
		.payment-admin-portlet .sidebar {background:#292929; width:120px; padding:25px 0;}
			.payment-admin-portlet .sidebar ul {width:120px; margin:0; list-style:none;}
				.payment-admin-portlet .sidebar li {padding:25px 0; position:relative;}
					.payment-admin-portlet .sidebar .sidebar-button {display:block; color:#155b86; width:86px; margin:auto; text-align:center; text-decoration:none; border-radius:10px; box-shadow:0 2px 2px rgba(0, 0, 0, 0.3); background:-moz-linear-gradient(top, #ffffff 0%, #e5e5e5); background:-webkit-gradient(linear, left top, left bottom, from(#ffffff), to(#e5e5e5)); background-image:-ms-linear-gradient(top, #FFFFFF 0%, #E5E5E5 100%); background:linear-gradient(top, #ffffff, #e5e5e5);}
					.payment-admin-portlet .sidebar .sidebar-button span {display:block; height:24px; padding-top:70px; background-position:center center; background-repeat:no-repeat;}
					.payment-admin-portlet .sidebar .sidebar-button:hover,
					.payment-admin-portlet .sidebar .sidebar-button-active {color:#fff; background:-moz-linear-gradient(top, #2499d3 0%, #145882); background:-webkit-gradient(linear, left top, left bottom, from(#2499d3), to(#145882)); background-image:-ms-linear-gradient(top, #2499d3 0%, #145882 100%); background:linear-gradient(top, #2499d3, #145882);}
					.payment-admin-portlet .sidebar .sidebar-button-home span {background-image:url('/btop-portlet/admin/images/btn-home.png');}
					.payment-admin-portlet .sidebar .sidebar-button-home:hover span,
					.payment-admin-portlet .sidebar .sidebar-button-home.sidebar-button-active span {background-image:url('/btop-portlet/admin/images/btn-home-hover.png');}
					.payment-admin-portlet .sidebar .sidebar-button-transactions span {background-image:url('/btop-portlet/admin/images/btn-transaction.png');}
					.payment-admin-portlet .sidebar .sidebar-button-transactions:hover span,
					.payment-admin-portlet .sidebar .sidebar-button-transactions.sidebar-button-active span {background-image:url('/btop-portlet/admin/images/btn-transaction-hover.png');}
					.payment-admin-portlet .sidebar .sidebar-button-configuration span {background-image:url('/btop-portlet/admin/images/btn-conf.png');}
					.payment-admin-portlet .sidebar .sidebar-button-configuration:hover span,
					.payment-admin-portlet .sidebar .sidebar-button-configuration.sidebar-button-active span {background-image:url('/btop-portlet/admin/images/btn-conf-hover.png');}
					.payment-admin-portlet .sidebar .sidebar-button-sellers-configuration span {background-image:url('/btop-portlet/admin/images/btn-conf-seller.png');}
					.payment-admin-portlet .sidebar .sidebar-button-sellers-configuration:hover span,
					.payment-admin-portlet .sidebar .sidebar-button-sellers-configuration.sidebar-button-active span {background-image:url('/btop-portlet/admin/images/btn-conf-seller-hover.png');}
					.payment-admin-portlet .sidebar .sidebar-plugins-configuration span {background-image:url('/btop-portlet/admin/images/btn-conf-plugin.png');}
					.payment-admin-portlet .sidebar .sidebar-plugins-configuration:hover span,
					.payment-admin-portlet .sidebar .sidebar-plugins-configuration.sidebar-button-active span {background-image:url('/btop-portlet/admin/images/btn-conf-plugin-hover.png');}
					.payment-admin-portlet .sidebar .sidebar-methods-configuration span {background-image:url('/btop-portlet/admin/images/btn-conf-payment.png');}
					.payment-admin-portlet .sidebar .sidebar-methods-configuration:hover span,
					.payment-admin-portlet .sidebar .sidebar-methods-configuration.sidebar-button-active span {background-image:url('/btop-portlet/admin/images/btn-conf-payment-hover.png');}
					.payment-admin-portlet .sidebar .sidebar-button-analytics span {background-image:url('/btop-portlet/admin/images/btn-analytics.png');}
					.payment-admin-portlet .sidebar .sidebar-button-analytics:hover span,
					.payment-admin-portlet .sidebar .sidebar-button-analytics.sidebar-button-active span {background-image:url('/btop-portlet/admin/images/btn-analytics-hover.png');}
			.payment-admin-portlet .sidebar ul ul {display:none; background:#BCBCBC; position:absolute; right:-120px; bottom:-145px; z-index:1;}
			.payment-admin-portlet .sidebar ul li:hover ul {display:block;}

		.payment-admin-portlet .breadcrumb {margin:40px auto 0; width:580px;}
			.payment-admin-portlet .breadcrumb.editing a {cursor:default;}
			.payment-admin-portlet .breadcrumb .breadcrumb-hint {text-align:center; color:#155B86; padding-bottom:10px;}
			.payment-admin-portlet .breadcrumb.editing .breadcrumb-hint {visibility:hidden;}
			.payment-admin-portlet .breadcrumb .breadcrumb-content {height:62px; position:relative;}
				.payment-admin-portlet .breadcrumb .breadcrumb-line {background-color:#ccc; border-radius:5px; bottom:8px; display:block; height:5px; left:0; position:absolute; width:580px;}
				.payment-admin-portlet .breadcrumb .breadcrumb-steps {display:table; width:100%;}
					.payment-admin-portlet .breadcrumb .breadcrumb-step {display:table-cell; width:50%; background:url("/btop-portlet/admin/images/step.png") no-repeat scroll center bottom transparent; height:62px; position:relative; text-align:center;}
					.payment-admin-portlet .breadcrumb .breadcrumb-step.current-step {z-index:1;}
						.payment-admin-portlet .breadcrumb .breadcrumb-step .breadcrumb-step-content {position:relative;}
							.payment-admin-portlet .breadcrumb .breadcrumb-step .breadcrumb-step-name {text-decoration:none; background:linear-gradient(#FFFFFF, #EEEEEE) repeat scroll 0 0 transparent; border:1px solid #CCC; border-radius:6px; color:#bbb; font-size:13px; padding:4px 11px; display:inline-block; white-space:nowrap; transition:color 0.5s ease 0s;}
							.payment-admin-portlet .breadcrumb .breadcrumb-step.current-step .breadcrumb-step-name {color:#333;}
							.payment-admin-portlet .breadcrumb .breadcrumb-step .completed-step-icon {margin-left:-16px; bottom:-65px; left:50%; position:absolute; background:url("/btop-portlet/admin/images/icon-check.png") no-repeat scroll left center transparent; display:inline-block; font-size:0; height:26px; width:32px;}

		.payment-admin-portlet .page-container {padding:40px 40px 0 60px;}
			.payment-admin-portlet .page-container .page {position:relative;}
				.payment-admin-portlet .page-container .page-header {min-height:115px; color:#155b86;}
					.payment-admin-portlet .page-title {padding-left:60px; margin:0 0 15px; line-height:50px; background-repeat:no-repeat;}
						.payment-admin-portlet .page-title a {text-decoration:none;}
					.payment-admin-portlet .page-home .page-title {background-image:url('/btop-portlet/admin/images/icon-page-home.png');}
					.payment-admin-portlet .page-transactions .page-title {background-image:url('/btop-portlet/admin/images/icon-page-transaction.png');}
					.payment-admin-portlet .page-sellers .page-title {background-image:url('/btop-portlet/admin/images/icon-page-sellers.png');}
					.payment-admin-portlet .page-plugins .page-title {background-image:url('/btop-portlet/admin/images/icon-page-plugin.png');}
					.payment-admin-portlet .page-methods .page-title {background-image:url('/btop-portlet/admin/images/icon-page-payment.png');}
					.payment-admin-portlet .page-rules .page-title {background-image:url('/btop-portlet/admin/images/icon-page-rules.png');}
					.payment-admin-portlet .page-analytics .page-title {background-image:url('/btop-portlet/admin/images/icon-page-analytics.png');}
					.payment-admin-portlet .add-entity-button {display:inline-block; line-height:35px; padding-left:40px; text-decoration:none; background:url('/btop-portlet/admin/images/btn-add.png') scroll no-repeat left center transparent; font-style:italic; font-weight:bold;}
					.payment-admin-portlet .search-form {position:absolute; top:0; right:0;}
					
					.payment-admin-portlet .search-form {width:215px;}
						.payment-admin-portlet .search-form .filter-by {padding-top:20px;}
						.payment-admin-portlet .search-form .input-search {float:right;}
						.payment-admin-portlet .search-form .aui-field-label {float:left; margin-right:15px;}
						.payment-admin-portlet .search-form .input-label {margin-right:10px; margin-left:2px;}
						.payment-admin-portlet .search-form .aui-field-inline {line-height:11px;}
						.payment-admin-portlet .search-form .aui-field-inline .aui-text-placeholder {color:#155B86; font-size:11px; font-weight:bold;}
						.payment-admin-portlet .search-form .aui-button .aui-button-input {background: none repeat scroll 0 0 #155B86; border: medium none; color: #FFFFFF; text-shadow: none;}
					
					.payment-admin-portlet .page-plugins .search-form {width:405px;}
					.payment-admin-portlet .page-plugins .search-form .filter {float: right;}
						.payment-admin-portlet .page-plugins .search-form .filter .filter-by {float:left; float: left; padding-top: 20px; width:230px;}
						.payment-admin-portlet .page-plugins .search-form .filter .method-select {float: right; padding-top:25px;}
					
					.payment-admin-portlet .page-transactions .search-form {width:295px;}
						.payment-admin-portlet .page-transactions .search-form .filter {float:left; padding-top:20px;}
							.payment-admin-portlet .page-transactions .search-form .method-select {float:left; margin-right:15px;}
							.payment-admin-portlet .page-transactions .search-form .seller-select {float:left;}
					 
			.payment-admin-portlet .entity-container {clear:both; border-radius:2px; box-shadow:0 0 2px rgba(0, 0, 0, 0.5); background:-moz-linear-gradient(top, #e5e5e5 0%, #ffffff); background:-webkit-gradient(linear, left top, left bottom, from(#ffffff), to(#e5e5e5)); background-image:-ms-linear-gradient(top, #FFFFFF 0%, #E5E5E5 100%); background:linear-gradient(top, #ffffff, #e5e5e5);}
				.payment-admin-portlet .entity-container-header {display:table; padding:0 50px 0 24px; width:100%; background: url("/btop-portlet/admin/images/ombre.png") no-repeat scroll center bottom transparent;}
					.payment-admin-portlet .entity-container-column-title {display:table-cell; line-height:55px; whitespace:nowrap; font-size:14px; font-weight:bold;}
						.payment-admin-portlet .entity-container-column-title:first-child {padding-left:24px; width:240px;}
						.payment-admin-portlet .entity-container-column-title:last-child {padding-right:50px; text-align:right; }
				.payment-admin-portlet .entity-container .entity-container-list {padding:1px 24px;}
					.payment-admin-portlet .entity-container .entity {display:table; width:100%; margin:20px 0; padding:10px 15px 5px; border-radius:10px; box-shadow:0 2px 2px rgba(0, 0, 0, 0.3); background:-moz-linear-gradient(top, #ffffff 0%, #e5e5e5); background:-webkit-gradient(linear, left top, left bottom, from(#ffffff), to(#e5e5e5)); background-image:-ms-linear-gradient(top, #FFFFFF 0%, #E5E5E5 100%); background:linear-gradient(top, #ffffff, #e5e5e5);}
					.payment-admin-portlet .entity-container .entity.default-entity {background:-moz-linear-gradient(center top, #2499D3 0%, #145882) repeat scroll 0 0 transparent; background:-webkit-gradient(linear, left top, left bottom, from(#2499D3), to(#145882)); background-image:-ms-linear-gradient(top, #2499D3 0%, #145882 100%); background:linear-gradient(top, #2499D3, #145882); color:#fff;}
						.payment-admin-portlet .entity-container .entity-summary {display:table-cell; vertical-align:top; padding:10px 5px 5px 15px; width:210px; font-size:12px;}
							.payment-admin-portlet .entity-container .entity-summary h1 {margin:0; display:inline; font-size:16px;}
							.payment-admin-portlet .entity-container .entity-summary .entity-id {display:block; font-size:12px; color:#888; font-weight:normal;}
						.payment-admin-portlet .entity-container .entity-details {display:table-cell; vertical-align:top; padding:10px 15px 5px 5px;}
							.payment-admin-portlet .entity-container .entity-details .entity-details-mask {overflow:hidden; max-height:75px;}
							.payment-admin-portlet .entity-container .entity-details.toggled .entity-details-mask {max-height:none;}
								.payment-admin-portlet .entity-container .entity-details-line {display:table; width:100%; line-height:25px; border-top:1px solid #B3B3B3;}
								.payment-admin-portlet .entity-container .entity-details-line:first-child {border-top:none;}
									.payment-admin-portlet .entity-container .entity-details-line h1 {display:table-cell; margin:0; font-weight:normal; padding-right:20px; white-space:nowrap; font-size:14px; font-weight:bold;}
									.payment-admin-portlet .entity-container .entity-details-line-content {display:table-cell; text-align:right;}
						.payment-admin-portlet .entity-container .entity-details-toggler {height:24px; cursor:pointer; background:url('/btop-portlet/admin/images/entity-details-toggler.png') scroll no-repeat top center transparent;}
							.payment-admin-portlet .entity-container .entity-details-toggler span {display:block; height:100%; border-top:1px solid #ccc; background:url('/btop-portlet/admin/images/entity-details-toggler-open.png') scroll no-repeat center bottom transparent;}
							.payment-admin-portlet .entity-container .entity-details-toggler.toggled span {background-image:url('/btop-portlet/admin/images/entity-details-toggler-close.png');}

					.payment-admin-portlet .entity-container.transaction-container .entity {display:block; width:auto;}
						.payment-admin-portlet .entity-container.transaction-container .entity .created-date {color:#6E6E6E;}
						.payment-admin-portlet .entity-container.transaction-container .entity .entity-summary {display:table; width:100%; }
							.payment-admin-portlet .entity-container.transaction-container .entity:not(.has-details) .entity-summary .entity-status{margin-right:30px; }
							.payment-admin-portlet .entity-container.transaction-container .entity .entity-summary > span {/*display:table-cell;*/display:block;float:left;width:15%;}
							.payment-admin-portlet .entity-container.transaction-container .entity .entity-summary .entity-status {float:right; width:auto;}
								.payment-admin-portlet .entity-container.transaction-container .entity .entity-summary span.status {margin-left:10px; display:block; float:left; /*height:21px; line-height:32px;*/}
								.payment-admin-portlet .entity-container.transaction-container .entity .entity-summary span.amount-summary {display:block; float:left;/* height:21px; line-height:32px;*/}

						.payment-admin-portlet .entity-container.transaction-container .entity .entity-details {display:none;border-top: 1px solid #BBBBBB;padding:10px 5px 5px;}
						.payment-admin-portlet .entity-container.transaction-container .entity .entity-details.toggled {display:block;}
							.payment-admin-portlet .entity-container.transaction-container .entity .entity-details hr {border:solid 1px #000;}
							.payment-admin-portlet .entity-container.transaction-container .entity .entity-details table {width:100%;}
							.payment-admin-portlet .entity-container.transaction-container .entity .entity-details table thead{border-bottom:solid 1px #CCC;}
							.payment-admin-portlet .entity-container.transaction-container .entity .entity-details table caption{font-weight:bold;font-size:14px;margin-bottom:20px;}
							.payment-admin-portlet .entity-container.transaction-container .entity .entity-details table th{padding-bottom:10px;}
							.payment-admin-portlet .entity-container.transaction-container .entity .entity-details table tbody tr:first-child td{padding-top:10px;}
							.payment-admin-portlet .entity-container.transaction-container .entity .entity-details table td{width:20%;}
						.payment-admin-portlet .entity-container.transaction-container .entity .entity-details-toggler {background:none; /*height:25px;*/ width:30px; display:block; float:right; /*margin-top:8px;*/}
							.payment-admin-portlet .entity-container.transaction-container .entity .entity-details-toggler span {cursor:pointer; background:url('/btop-portlet/admin/images/arrow-off.png') scroll no-repeat center top transparent; border:0px none transparent;}
								.payment-admin-portlet .entity-container.transaction-container .entity .entity-details-toggler.toggled span {background:url('/btop-portlet/admin/images/arrow-on.png') scroll no-repeat center top transparent;}
			
		.payment-admin-portlet .page-container .page-home .page-summary {display:inline-block; vertical-align:top; width:450px; margin:20px 60px 20px 0; padding:10px 15px; box-shadow:0 2px 2px rgba(0, 0, 0, 0.3); background:-moz-linear-gradient(bottom, #ffffff 0%, #e5e5e5); background:-webkit-gradient(linear, left top, left bottom, from(#ffffff), to(#e5e5e5)); background-image:-ms-linear-gradient(top, #FFFFFF 0%, #E5E5E5 100%); background:linear-gradient(top, #ffffff, #e5e5e5);}
			.payment-admin-portlet .page-container .page-home .page-summary h1 {font-weight:normal; font-size:19px; font-style:italic; float:left;}
			.payment-admin-portlet .page-container .page-home .page-summary .add-entity-button {float: right; margin-top:15px;}
			.payment-admin-portlet .page-container .page-home .page-summary .entity-container-list {min-height:149px;}
				.payment-admin-portlet .page-container .page-home .page-summary .entity-container-list .entity {margin:20px 0; padding:10px 15px 5px; border-radius:10px; box-shadow:0 2px 2px rgba(0, 0, 0, 0.3); background:-moz-linear-gradient(top, #ffffff 0%, #e5e5e5); background:-webkit-gradient(linear, left top, left bottom, from(#ffffff), to(#e5e5e5)); background-image:-ms-linear-gradient(top, #FFFFFF 0%, #E5E5E5 100%); background:linear-gradient(top, #ffffff, #e5e5e5);}
					
					.payment-admin-portlet .page-container .page-home .page-summary .entity-container-list .entity .entity-summary {}
						.payment-admin-portlet .page-container .page-home .page-summary .entity-container-list .entity .entity-summary > span {float:left;}
						.payment-admin-portlet .page-container .page-home .page-summary .entity-container-list .entity .entity-summary .entity-status {float:right;}
						
					.payment-admin-portlet .page-container .page-home .page-summary .entity-container-list .entity .entity-summary .entity-details-toggler {background: none repeat scroll 0 0 transparent; display: block; float: right; height: 20px; width: 27px;}
							.payment-admin-portlet .page-container .page-home .page-summary .entity-container-list .entity .entity-summary .entity-details-toggler span {background: url("/btop-portlet/admin/images/arrow-off.png") no-repeat scroll right center transparent; border: 0 none transparent; cursor: pointer; display: block; height: 16px; padding-left: 10px; width: 16px;}
								.payment-admin-portlet .page-container .page-home .page-summary .entity-container-list .entity .entity-summary .entity-details-toggler.toggled span {background:url('/btop-portlet/admin/images/arrow-on.png') scroll no-repeat center top transparent;  border: 0 none transparent; cursor: pointer; display: block; height: 16px; padding-left: 10px; width: 16px;}
					.payment-admin-portlet .page-container .page-home .page-summary .entity-container-list .entity .entity-details {display:none; border-top: 1px solid #bbb; padding:10px 5px 5px;}
						.payment-admin-portlet .page-container .page-home .page-summary .entity-container-list .entity .entity-details.toggled {display:block;}
					
			.payment-admin-portlet .page-container .page-home .page-summary .show-more-button {margin:auto; width:25px; cursor:pointer; background:url("/btop-portlet/admin/images/entity-details-toggler-open.png") no-repeat scroll center bottom transparent; display:block; font-size:0; height:25px;}
			.payment-admin-portlet .page-container .page-home .page-summary {}
			.payment-admin-portlet .page-container .page-home .page-summary {}

	.payment-admin-portlet .edit-icon {display:inline-block; font-size:0; width:20px; height:18px; vertical-align:text-bottom; background:url('/btop-portlet/admin/images/icon-conf-small.png');}
	.payment-admin-portlet span.edit-icon {opacity:0.3;}
	.payment-admin-portlet .configured {color:#89ca17; margin-right:5px;}
	.payment-admin-portlet .not-configured {color:#c90202;}

	#content .payment-admin-portlet .taglib-page-iterator {margin-right: 30px;}
		#content .payment-admin-portlet .taglib-page-iterator .search-pages {}
		#content .payment-admin-portlet .taglib-page-iterator .btn-previous {background: url("/btop-portlet/admin/images/btn-previous.png") no-repeat scroll 0 0 transparent; height: 30px; line-height:20px; padding-left:30px; position: absolute; right:265px; text-decoration: none; width: 39px; border:none; font-family: arial; font-style: italic; font-weight: bold;}
			#content .payment-admin-portlet .taglib-page-iterator span.btn-previous {background:none;}
		#content .payment-admin-portlet .taglib-page-iterator .search-results {font-family: arial; font-style: italic; font-weight: bold; position:absolute; right:90px;}
			#content .payment-admin-portlet .taglib-page-iterator .search-results .journal-article-page-number {padding: 2px 11px; line-height: 18px; text-decoration: none; border:none;}
		#content .payment-admin-portlet .taglib-page-iterator .btn-next {background: url("/btop-portlet/admin/images/btn-next.png") no-repeat scroll 55px 0 transparent; height: 30px; line-height:20px; padding-right: 20px; right:0; text-decoration: none; width: 39px; border:none; font-family: arial; font-style: italic; font-weight: bold;}
			#content .payment-admin-portlet .taglib-page-iterator span.btn-next {background:none;} 
		#content .payment-admin-portlet .taglib-page-iterator {}

	.payment-admin-portlet .popup-wrapper {}
		.payment-admin-portlet .popup-wrapper .popup-mask {position: fixed; background:#000; opacity: 0.5; width: 100%; height: 100%; top: 0px; left: 0px; z-index: 0;}
		.payment-admin-portlet .popup-wrapper .container {background: none repeat scroll 0 0 #eee; left:25%; position: absolute; top: 10%; z-index: 100;}
			.payment-admin-portlet .popup-wrapper .popup-button-close {cursor:pointer; background: url("/btop-portlet/admin/images/close.png") no-repeat scroll center center transparent; position:absolute; right:-64px; top:0; width:64px; height:69px; font-size: 0;}
			.payment-admin-portlet .popup-wrapper .container > .popup-button-previous {cursor:pointer; background: url("/btop-portlet/admin/images/previous.png") no-repeat scroll center center transparent; position:absolute; left:-64px; top:50%; width:64px; height:69px; font-size:0;}
			.payment-admin-portlet .popup-wrapper .container > .popup-button-next {cursor:pointer; background: url("/btop-portlet/admin/images/next.png") no-repeat scroll center center transparent; position:absolute; right:-64px; top:50%; width:64px; height:69px; font-size:0;}
			.payment-admin-portlet .popup-wrapper .popup-container {min-height:250px; min-width:250px; overflow: auto; padding:75px; position: relative;}
				.payment-admin-portlet .popup-wrapper .popup-container .aui-form {margin-top:60px; position:relative;}
					.payment-admin-portlet .popup-wrapper .popup-container .aui-form .dynamic-config {max-height:350px; overflow:auto;}
					.payment-admin-portlet .popup-wrapper .popup-container .edit-actions {height:0; margin:auto; text-align:right; width:580px;}
						.payment-admin-portlet .popup-wrapper .popup-container .edit-actions .cancel-button,
						.payment-admin-portlet .popup-wrapper .popup-container .edit-actions .confirm-button {text-decoration:none; padding-top:30px; font-weight:bold; margin:5px;}
						.payment-admin-portlet .popup-wrapper .popup-container .edit-actions .cancel-button {background:url("/btop-portlet/admin/images/icon-cancel.png") no-repeat scroll center top transparent; color:#D10000; text-transform:lowercase;}
						.payment-admin-portlet .popup-wrapper .popup-container .edit-actions .confirm-button {background:url("/btop-portlet/admin/images/icon-confirm.png") no-repeat scroll center top transparent;}
							.payment-admin-portlet .popup-wrapper .popup-container .edit-actions .confirm-button input,
							.payment-admin-portlet .popup-wrapper .popup-container .edit-actions .confirm-button input:hover {background:none; border:none; text-shadow:none; color:#85CE00; padding:30px 0 0 0; text-transform:lowercase;}
				.payment-admin-portlet .popup-wrapper .popup-container .message-success,
				.payment-admin-portlet .popup-wrapper .popup-container .message-alert {text-align:center; margin:auto; font-size:28px; min-height:100px; padding-top:80px; width:500px; color:#555555;}
				.payment-admin-portlet .popup-wrapper .popup-container .message-alert {background:url("/btop-portlet/admin/images/icon-alert.png") scroll no-repeat top center transparent; padding-top:120px;}
					.payment-admin-portlet .popup-wrapper .popup-container .download-link {display:block; background:url("/btop-portlet/admin/images/btn-external-link.png") scroll no-repeat top center transparent; width:70px; height:70px; margin:40px auto 0;}
						.payment-admin-portlet .popup-wrapper .popup-container .download-link span {display:none;}
				.payment-admin-portlet .popup-wrapper .popup-container h1 {color:#155B86; margin:0;}
					.payment-admin-portlet .popup-wrapper .popup-container form.edit-seller {background:url("/btop-portlet/admin/images/icon-sellers.png") no-repeat scroll left top transparent; padding-left:60px;}

					.payment-admin-portlet .popup-wrapper .popup-container .aui-form .associate-entity-container {width:730px;}
						.payment-admin-portlet .popup-wrapper .popup-container .aui-form .associate-entity-container .associate-entity {float:left; padding:10px 15px 5px; border-radius:10px; box-shadow:0 2px 2px rgba(0, 0, 0, 0.3); background:-moz-linear-gradient(bottom, #ffffff 0%, #e5e5e5); background:-webkit-gradient(linear, left bottom, left bottom, from(#ffffff), to(#e5e5e5)); background-image:-ms-linear-gradient(top, #FFFFFF 0%, #E5E5E5 100%); background:linear-gradient(top, #ffffff, #e5e5e5);}
							.payment-admin-portlet .popup-wrapper .popup-container .aui-form .associate-entity-container .associate-entity .profile {background:url("/btop-portlet/admin/images/icon-sellers.png") no-repeat scroll left top transparent; padding-left:60px; color: #155B86; font-size: 14px; font-weight: bold; height: 50px; width:155px; padding-top:10px;}
							.payment-admin-portlet .popup-wrapper .popup-container .aui-form .associate-entity-container .associate-entity .plugins {background:url("/btop-portlet/admin/images/btn-conf-plugin.png") no-repeat scroll left top transparent; padding-left:60px; color: #155B86; font-size: 14px; font-weight: bold; height: 50px; width:155px; padding-top:10px;}
						.payment-admin-portlet .popup-wrapper .popup-container .aui-form .associate-entity-container .associated-background {margin:0 30px; float:left; background:url("/btop-portlet/admin/images/icon-associate.png") no-repeat scroll center center transparent; width:73px; height:54px;}
						
						.payment-admin-portlet .popup-wrapper .popup-container .aui-form .associate-entity-container .associated-list {padding:10px; float:left; width:320px; overflow:auto; height:180px; border:1px solid #aaa; background:-moz-linear-gradient(top, #ffffff 0%, #e5e5e5); background:-webkit-gradient(linear, left top, left bottom, from(#ffffff), to(#e5e5e5)); background-image:-ms-linear-gradient(top, #FFFFFF 0%, #E5E5E5 100%); background:linear-gradient(top, #ffffff, #e5e5e5);}
							.payment-admin-portlet .popup-wrapper .popup-container .aui-form .associate-entity-container .associated-list .associate-list-element {line-height:35px; height:40px; color: #155B86; font-size: 14px; font-weight: bold;margin:10px; width:200px; padding:10px 15px 5px 55px; border-radius:10px; box-shadow:0 2px 2px rgba(0, 0, 0, 0.3); background:-moz-linear-gradient(top, #ffffff 0%, #e5e5e5); background:-webkit-gradient(linear, left top, left bottom, from(#ffffff), to(#e5e5e5)); background-image:-ms-linear-gradient(top, #FFFFFF 0%, #E5E5E5 100%); background:linear-gradient(top, #ffffff, #e5e5e5); background:url('/btop-portlet/admin/images/btn-conf-plugin.png') no-repeat scroll 10px 10px transparent;}
							.payment-admin-portlet .popup-wrapper .popup-container .aui-form .associate-entity-container .associated-list .associate-list-element.seller {background:url('/btop-portlet/admin/images/icon-sellers.png') no-repeat scroll 10px 10px transparent;}
								.payment-admin-portlet .popup-wrapper .popup-container .aui-form .associate-entity-container .associated-list .associate-list-element .associate-list-element-remove {margin-top:12px; display: block; float: right; font-size: 0; background:url('/btop-portlet/admin/images/entity-details-toggler-close.png') no-repeat scroll center center transparent; width:15px; height:15px;}
						
						.payment-admin-portlet .popup-wrapper .popup-container .aui-form .associate-entity-container .associate-entity-drop-area {background:url('/btop-portlet/admin/images/drag-drop-zone.png') no-repeat scroll center center transparent; width:320px; height:120px; margin:20px; font-size: 15px; color: rgb(85, 85, 85); font-weight: bold; float: right;}
							.payment-admin-portlet .popup-wrapper .popup-container .aui-form .associate-entity-container .associate-entity-drop-area span {display: block; font-size: 19px; text-align: center; margin-top: 35px; margin-left: 103px; width:183px;}
						
						.payment-admin-portlet .popup-wrapper .popup-container .aui-form .associate-entity-container .associate-list-container {width:730px; display: inline-block;}
							.payment-admin-portlet .popup-wrapper .popup-container .aui-form .associate-entity-container .associate-list-title {color:#155B86; font-size:18px; float:left; margin-top:10px;}
							.payment-admin-portlet .popup-wrapper .popup-container .aui-form .associate-entity-container .associate-list-search {color:#155B86; font-size:13px; float:right;}
								.payment-admin-portlet .popup-wrapper .popup-container .aui-form .associate-entity-container .associate-list-container .associate-list {border:1px solid #ddd; max-height: 97px; overflow-x: scroll; white-space: nowrap;}
									.payment-admin-portlet .popup-wrapper .popup-container .aui-form .associate-entity-container .associate-list-container .associate-list-element {display: inline-block; margin:4px 10px 6px 3px; line-height:43px; height:50px; color: #155B86; font-size: 14px; font-weight: bold; width:175px; padding:10px 10px 5px 55px; border-radius:10px; box-shadow:0 2px 2px rgba(0, 0, 0, 0.3); background:-moz-linear-gradient(top, #ffffff 0%, #e5e5e5); background:-webkit-gradient(linear, left top, left bottom, from(#ffffff), to(#e5e5e5)); background-image:-ms-linear-gradient(top, #FFFFFF 0%, #E5E5E5 100%); background:linear-gradient(top, #ffffff, #e5e5e5); background:url('/btop-portlet/admin/images/btn-conf-plugin.png') no-repeat scroll 10px 10px transparent;}
										.payment-admin-portlet .popup-wrapper .popup-container .aui-form .associate-entity-container .associate-list-container .associate-list-element.seller {background:url('/btop-portlet/admin/images/icon-sellers.png') no-repeat scroll 10px 10px transparent;}
										.payment-admin-portlet .popup-wrapper .popup-container .aui-form .associate-entity-container .associate-list-container .associate-list span {display:none;}
					
					.payment-admin-portlet .popup-wrapper .popup-container .aui-form .associate-entity-container .associate-list-container .associate-list-element.associate-list-element-search-hidden {display:none;}
					
					.payment-admin-portlet input[type="text"], 
					.payment-admin-portlet input[type="password"], 
					.payment-admin-portlet input[type="file"], 
					.payment-admin-portlet textarea, 
					.payment-admin-portlet .textarea, 
					.payment-admin-portlet .aui-field-input-text {padding:8px 10px; color:#155B86; font-weight:bold; border:1px solid #bbb; margin:10px 0px; box-shadow:none; border-radius:0;}
				.payment-admin-portlet .popup-wrapper .aui-button-holder {display:none;}

	/* Rules editor */

	.payment-admin-portlet .rule-container .entity .rules {display:table-cell; padding:10px 5px 5px 15px; vertical-align:middle;}
	.payment-admin-portlet .rule-container .entity .rules em {color:#888;}
	.payment-admin-portlet .rule-container .entity-summary {padding:10px 5px 5px 5px;}
	.payment-admin-portlet .rule-container .entity-actions {display:table-cell; width:100px; padding:10px 15px 5px 5px; text-align:right;}
	
	.payment-admin-portlet .rule-container .default-payment-plugin-selector {}
		.payment-admin-portlet .rule-container .default-payment-plugin-selector label {display:none;}
	
	.payment-admin-portlet .rule-container .portlet-msg-error {margin:0 0 5px;}
	
	.payment-admin-portlet .rules-icon {display:inline-block; font-size:0; width:20px; height:18px; vertical-align:text-bottom; background:url('/btop-portlet/admin/images/icon-rules-small.png') scroll no-repeat center center transparent;}
	.payment-admin-portlet .delete-icon {display:inline-block; font-size:0; width:20px; height:18px; vertical-align:text-bottom; background:url('/btop-portlet/admin/images/entity-details-toggler-close.png') scroll no-repeat center center transparent;}
	.payment-admin-portlet .drag-handle {display:inline-block; font-size:0; width:20px; height:18px; vertical-align:text-bottom; background:url('/btop-portlet/admin/images/icon-move-small.png') scroll no-repeat center center transparent;}
	.payment-admin-portlet .rule-editor-layout {display:table; width:100%;}
		.payment-admin-portlet .rule-editor-layout-column {display:table-cell;}
		.payment-admin-portlet .rule-editor-layout-column.left {}
		.payment-admin-portlet .rule-editor-layout-column.right {width:300px; padding-top:30px; vertical-align:middle; text-align:center;}

			.payment-admin-portlet .payment-plugin-selector {background:url("/btop-portlet/admin/images/icon-associate.png") no-repeat scroll 10px center transparent; display:inline-block; line-height:54px; padding-left:83px;}
				.payment-admin-portlet .payment-plugin-selector .aui-field-content {padding-left:10px;}
					.payment-admin-portlet .payment-plugin-selector .aui-field-label {display:none;}

	.yui3-ruleeditorbasecondition {}
		.yui3-ruleeditorbasecondition .button-container {text-align:right; padding:0 20px; line-height:0;}
			.yui3-ruleeditorbasecondition .button-container .add-button, 
			.yui3-ruleeditorbasecondition .button-container .remove-button {cursor:pointer; font-size:0; display:inline-block; margin-left:5px; width:27px; height:25px;}
			.yui3-ruleeditorbasecondition .button-container .add-button {background:url("/btop-portlet/admin/images/add-rule.png") no-repeat scroll center center transparent;}
			.yui3-ruleeditorbasecondition .button-container .remove-button {background:url("/btop-portlet/admin/images/remove-rule.png") no-repeat scroll center center transparent;}
		.yui3-ruleeditorbasecondition .yui3-ruleeditor-conditionbody {}
		.yui3-ruleeditorbasecondition .yui3-ruleeditor-conditionbody > .inner {display:table; width:100%; position:relative;}
	
	.yui3-ruleeditorgroupcondition {}
		.yui3-ruleeditorgroupcondition .yui3-ruleeditor-conditionbody {}
			.yui3-ruleeditorgroupcondition .yui3-ruleeditor-conditionbody > .inner {}
				.yui3-ruleeditorgroupcondition .logic-operator-selector-container {display:table-cell; width:80px; padding:30px 0; text-align:right; width:85px;}
					.yui3-ruleeditorgroupcondition .logic-operator-selector {border-radius:4px 0 0 4px; border-right:none;}
				.yui3-ruleeditorgroupcondition .yui3-ruleeditor-conditioncontainer {display:table-cell; vertical-align:top;}
					.yui3-ruleeditorgroupcondition .yui3-ruleeditor-conditioncontainer > .inner {padding:10px; min-height:70px; border-radius:10px; box-shadow:0 1px 2px rgba(0, 0, 0, 0.5); background:-moz-linear-gradient(top, #ffffff 0%, #e5e5e5); background:-webkit-gradient(linear, left top, left bottom, from(#ffffff), to(#e5e5e5)); background-image:-ms-linear-gradient(top, #FFFFFF 0%, #E5E5E5 100%); background:linear-gradient(top, #ffffff, #e5e5e5);}
						.yui3-ruleeditorgroupcondition .yui3-ruleeditor-conditioncontainer > .inner > * {margin-top:10px;}
						.yui3-ruleeditorgroupcondition .yui3-ruleeditor-conditioncontainer > .inner > *:first-child {margin-top:0;}

	.yui3-ruleeditorcondition {}
		.yui3-ruleeditorcondition .yui3-ruleeditor-conditionbody {}
		.yui3-btopruleeditorcondition-content .yui3-ruleeditor-conditionbody {padding:10px; border-radius:10px; box-shadow:0 1px 2px rgba(0, 0, 0, 0.5); background:-moz-linear-gradient(top, #ffffff 0%, #e5e5e5); background:-webkit-gradient(linear, left top, left bottom, from(#ffffff), to(#e5e5e5)); background-image:-ms-linear-gradient(top, #FFFFFF 0%, #E5E5E5 100%); background:linear-gradient(top, #ffffff, #e5e5e5);}
			.yui3-ruleeditorcondition .yui3-ruleeditor-conditionbody > .inner {}
				.yui3-ruleeditorcondition .negation-toggle-container {display:table-cell; width:0; padding:25px 50px; font-weight:bold; line-height:20px; position:relative; vertical-align:top;} 
					.yui3-ruleeditorcondition .negation-toggle-container .if {color:#105E8E; left:10px; position:absolute; top:5px;}
					.yui3-ruleeditorcondition .negation-toggle-container.negated .if {color:#D9D9D9;}
					.yui3-ruleeditorcondition .negation-toggle-container .if-not {color:#D9D9D9; left:10px; position:absolute; top:25px;}
					.yui3-ruleeditorcondition .negation-toggle-container.negated .if-not {color:#FE0000;}
					.yui3-ruleeditorcondition .negation-toggle-container .negation-toggle {cursor:pointer; display:block; font-size:0; height:42px; left:50px; position:absolute; top:5px; width:21px; background:url('/btop-portlet/admin/images/toggle-up.png') scroll no-repeat left center transparent;}
					.yui3-ruleeditorcondition .negation-toggle-container.negated .negation-toggle {background-image:url('/btop-portlet/admin/images/toggle-down.png');}
				.yui3-ruleeditorcondition .description {position:absolute; right:0; top:0; opacity:0.2;}
				.yui3-ruleeditorcondition .yui3-ruleeditor-fieldcontainer {display:table-cell;}
					.yui3-ruleeditorcondition .yui3-ruleeditor-fieldcontainer > .inner {}

	.yui3-ruleeditorfield {display:inline-block; margin:0 10px; color:#555;}
	
	.yui3-ruleeditortextfield {}
		.yui3-ruleeditortextfield label > span {display:block;}
		.yui3-ruleeditortextfield label > input {margin:0 !important;}
	
	.yui3-ruleeditorcheckbox {}
		.yui3-ruleeditorcheckbox label > span {margin-right:10px;}
		.yui3-ruleeditorcheckbox label > input {}
	
	.yui3-dropdown {background:rgba(0, 0, 0, 0.3); border-radius:4px;}
		.yui3-dropdown .yui3-dropdown-option {cursor:pointer; padding:0 20px; margin:2px; line-height:32px; color:#B3B3B3; background:-moz-linear-gradient(top, #ffffff 0%, #e5e5e5); background:-webkit-gradient(linear, left top, left bottom, from(#ffffff), to(#e5e5e5)); background-image:-ms-linear-gradient(top, #FFFFFF 0%, #E5E5E5 100%); background:linear-gradient(top, #ffffff, #e5e5e5);}
		.yui3-dropdown .yui3-dropdown-option:hover {color:#333;}
	