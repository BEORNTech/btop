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

package com.beorn.onlinepayment.portlet;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.beorn.onlinepayment.config.ConfigUtil;
import com.beorn.onlinepayment.messaging.PaymentSystemPluginSender;
import com.beorn.onlinepayment.model.PaymentMethod;
import com.beorn.onlinepayment.model.PaymentPlugin;
import com.beorn.onlinepayment.model.PaymentPluginConfig;
import com.beorn.onlinepayment.model.Rule;
import com.beorn.onlinepayment.model.Seller;
import com.beorn.onlinepayment.service.PaymentMethodLocalServiceUtil;
import com.beorn.onlinepayment.service.PaymentPluginConfigLocalServiceUtil;
import com.beorn.onlinepayment.service.PaymentPluginLocalServiceUtil;
import com.beorn.onlinepayment.service.PaymentPluginServiceUtil;
import com.beorn.onlinepayment.service.RuleLocalServiceUtil;
import com.beorn.onlinepayment.service.SellerLocalServiceUtil;
import com.beorn.onlinepayment.service.SellerServiceUtil;
import com.beorn.onlinepayment.service.permission.PaymentAppPermission;
import com.beorn.onlinepayment.service.permission.PaymentMethodPermission;
import com.beorn.onlinepayment.service.permission.PaymentPluginConfigPermission;
import com.beorn.onlinepayment.service.permission.PaymentPluginPermission;
import com.beorn.onlinepayment.service.permission.SellerPermission;
import com.beorn.onlinepayment.util.ActionKeys;
import com.beorn.onlinepayment.util.PaymentSystemUtil;
import com.beorn.onlinepayment.util.comparator.PaymentPluginCreateDateComparator;
import com.beorn.onlinepayment.util.comparator.SellerCreateDateComparator;
import com.beorn.paymentpluginapi.config.ConfigDescriptionUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * @author Sébastien Meunier
 */
public class AdminPortlet extends MVCPortlet {

	public void editSeller(ActionRequest actionRequest,
			ActionResponse actionResponse) throws PortletException, IOException {

		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);
			PermissionChecker permissionChecker = themeDisplay
					.getPermissionChecker();

			long sellerId = ParamUtil.getLong(actionRequest, "sellerId");
			String name = ParamUtil.getString(actionRequest, "name");
			boolean active = ParamUtil.getBoolean(actionRequest, "active");

			if (Validator.isNull(name))
				SessionErrors.add(actionRequest, "name-required");

			if (!SessionErrors.isEmpty(actionRequest)) {
				actionResponse.setRenderParameters(actionRequest
						.getParameterMap());
				return;
			}

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
					Seller.class.getName(), actionRequest);

			Seller seller;
			if (sellerId == 0) {
				PaymentAppPermission.check(permissionChecker,
						themeDisplay.getScopeGroupId(), ActionKeys.ADD_SELLER);
				seller = SellerLocalServiceUtil.addSeller(
						themeDisplay.getUserId(), name, active, serviceContext);

			} else {
				SellerPermission.check(permissionChecker, sellerId,
						ActionKeys.UPDATE);
				seller = SellerLocalServiceUtil.updateSeller(sellerId, name,
						active, serviceContext);
			}

			String redirect = ParamUtil.getString(actionRequest, "successURL");
			redirect = HttpUtil.setParameter(redirect,
					"_1_WAR_onlinepaymentportlet_sellerId",
					seller.getSellerId());
			actionResponse.sendRedirect(redirect);

			SessionMessages.add(actionRequest, "edit-seller.success");

		} catch (Exception e) {
			_log.error(e);
			actionResponse.setRenderParameters(actionRequest.getParameterMap());
			SessionErrors.add(actionRequest, e.getClass().getName());
		}
	}

	public void editRule(ActionRequest actionRequest,
			ActionResponse actionResponse) throws PortletException, IOException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);
			PermissionChecker permissionChecker = themeDisplay
					.getPermissionChecker();

			long ruleId = ParamUtil.getLong(actionRequest, "ruleId");
			long paymentPluginConfigId = ParamUtil.getLong(actionRequest,
					"paymentPluginConfigId");
			String content = ParamUtil.getString(actionRequest, "content");

			PaymentPluginConfigPermission.check(permissionChecker,
					paymentPluginConfigId, ActionKeys.UPDATE);

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
					Rule.class.getName(), actionRequest);

			Rule rule;
			if (ruleId == 0) {
				rule = RuleLocalServiceUtil.addRule(themeDisplay.getUserId(),
						paymentPluginConfigId, content, 0, serviceContext);

			} else {
				// Check that the user can update the previous config
				rule = RuleLocalServiceUtil.getRule(ruleId);
				PaymentPluginConfigPermission.check(permissionChecker,
						rule.getPaymentPluginConfigId(), ActionKeys.UPDATE);

				rule = RuleLocalServiceUtil.updateRule(ruleId,
						paymentPluginConfigId, content, rule.getPriority(),
						serviceContext);
			}

			String redirect = ParamUtil.getString(actionRequest, "successURL");
			redirect = HttpUtil.setParameter(redirect,
					"_1_WAR_onlinepaymentportlet_ruleId", rule.getRuleId());
			actionResponse.sendRedirect(redirect);

			SessionMessages.add(actionRequest, "edit-rule.success");

		} catch (Exception e) {
			_log.error(e);
			actionResponse.setRenderParameters(actionRequest.getParameterMap());
			SessionErrors.add(actionRequest, e.getClass().getName());
		}
	}

	public void deleteRule(ActionRequest actionRequest,
			ActionResponse actionResponse) throws PortletException, IOException {

		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);
			PermissionChecker permissionChecker = themeDisplay
					.getPermissionChecker();

			long ruleId = ParamUtil.getLong(actionRequest, "ruleId");

			Rule rule = RuleLocalServiceUtil.getRule(ruleId);

			PaymentPluginConfigPermission.check(permissionChecker,
					rule.getPaymentPluginConfigId(), ActionKeys.UPDATE);

			RuleLocalServiceUtil.deleteRule(rule);

			String redirect = ParamUtil.getString(actionRequest, "successURL");
			actionResponse.sendRedirect(redirect);

			SessionMessages.add(actionRequest, "delete-rule.success");

		} catch (Exception e) {
			_log.error(e);
			actionResponse.setRenderParameters(actionRequest.getParameterMap());
			SessionErrors.add(actionRequest, e.getClass().getName());
		}
	}

	public void editPlugin(ActionRequest actionRequest,
			ActionResponse actionResponse) throws PortletException, IOException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);
			PermissionChecker permissionChecker = themeDisplay
					.getPermissionChecker();
			HttpServletRequest httpServletRequest = PortalUtil
					.getHttpServletRequest(actionRequest);
			ServletContext servletContext = httpServletRequest.getSession()
					.getServletContext();

			long paymentPluginId = ParamUtil.getLong(actionRequest, "pluginId");
			PaymentPluginPermission.check(permissionChecker, paymentPluginId,
					ActionKeys.UPDATE);

			Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(
					actionRequest, "name");

			PaymentPlugin paymentPlugin = PaymentPluginLocalServiceUtil
					.getPaymentPlugin(paymentPluginId);
			String pluginConfig = ConfigUtil.getConfig(
					actionRequest,
					ConfigDescriptionUtil.parseConfigDescription(paymentPlugin
							.getPluginConfigParameters())).toString();

			if (Validator.isNull(nameMap) && nameMap.isEmpty())
				SessionErrors.add(actionRequest, "name-required");

			if (Validator.isNotNull(paymentPlugin.getPluginConfigParameters())
					&& !isConfigValid(servletContext, paymentPlugin,
							pluginConfig, false))
				SessionErrors.add(actionRequest, "invalid-config");

			if (!SessionErrors.isEmpty(actionRequest)) {
				actionResponse.setRenderParameters(actionRequest
						.getParameterMap());
				return;
			}

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
					PaymentPlugin.class.getName(), actionRequest);

			paymentPlugin = PaymentPluginLocalServiceUtil.updatePaymentPlugin(
					paymentPluginId, paymentPlugin.getApplicationId(), nameMap,
					null, null, pluginConfig, true, serviceContext);

			String redirect = ParamUtil.getString(actionRequest, "successURL");
			actionResponse.sendRedirect(redirect);

			SessionMessages.add(actionRequest, "edit-plugin.success");

		} catch (Exception e) {
			_log.error(e);
			actionResponse.setRenderParameters(actionRequest.getParameterMap());
			SessionErrors.add(actionRequest, e.getClass().getName());
		}
	}

	public void editSellerPlugin(ActionRequest actionRequest,
			ActionResponse actionResponse) throws PortletException, IOException {

		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);
			PermissionChecker permissionChecker = themeDisplay
					.getPermissionChecker();
			HttpServletRequest httpServletRequest = PortalUtil
					.getHttpServletRequest(actionRequest);
			ServletContext servletContext = httpServletRequest.getSession()
					.getServletContext();

			long pluginConfigId = ParamUtil.getLong(actionRequest,
					"pluginConfigId");
			PaymentPluginConfigPermission.check(permissionChecker,
					pluginConfigId, ActionKeys.UPDATE);

			PaymentPluginConfig pluginConfig = PaymentPluginConfigLocalServiceUtil
					.getPaymentPluginConfig(pluginConfigId);

			PaymentPlugin paymentPlugin = PaymentPluginLocalServiceUtil
					.getPaymentPlugin(pluginConfig.getPaymentPluginId());
			String config = ConfigUtil.getConfig(
					actionRequest,
					ConfigDescriptionUtil.parseConfigDescription(paymentPlugin
							.getSellerConfigParameters())).toString();

			if (Validator.isNotNull(paymentPlugin.getSellerConfigParameters())) {
				if (!isConfigValid(servletContext, paymentPlugin, config, true))
					SessionErrors.add(actionRequest, "invalid-config");

			} else {
				config = StringPool.BLANK;
			}

			if (!SessionErrors.isEmpty(actionRequest)) {
				actionResponse.setRenderParameters(actionRequest
						.getParameterMap());
				return;
			}

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
					PaymentPluginConfig.class.getName(), actionRequest);

			pluginConfig = PaymentPluginConfigLocalServiceUtil
					.updatePaymentPluginConfig(pluginConfigId,
							pluginConfig.getSellerId(),
							pluginConfig.getPaymentPluginId(), config, true,
							serviceContext);

			String redirect = ParamUtil.getString(actionRequest, "successURL");
			actionResponse.sendRedirect(redirect);

			SessionMessages.add(actionRequest, "edit-seller-plugin.success");

		} catch (Exception e) {
			_log.error(e);
			actionResponse.setRenderParameters(actionRequest.getParameterMap());
			SessionErrors.add(actionRequest, e.getClass().getName());
		}
	}

	public void editMethod(ActionRequest actionRequest,
			ActionResponse actionResponse) throws PortletException, IOException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);
			PermissionChecker permissionChecker = themeDisplay
					.getPermissionChecker();

			long paymentMethodId = ParamUtil.getLong(actionRequest, "methodId");
			PaymentMethodPermission.check(permissionChecker, paymentMethodId,
					ActionKeys.UPDATE);

			Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(
					actionRequest, "name");

			if (Validator.isNull(nameMap) && nameMap.isEmpty())
				SessionErrors.add(actionRequest, "name-required");

			if (!SessionErrors.isEmpty(actionRequest)) {
				actionResponse.setRenderParameters(actionRequest
						.getParameterMap());
				return;
			}

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
					Seller.class.getName(), actionRequest);

			PaymentMethod paymentMethod = PaymentMethodLocalServiceUtil
					.getPaymentMethod(paymentMethodId);
			paymentMethod = PaymentMethodLocalServiceUtil.updatePaymentMethod(
					paymentMethodId, paymentMethod.getKey(), nameMap,
					serviceContext);

			String redirect = ParamUtil.getString(actionRequest, "successURL");
			actionResponse.sendRedirect(redirect);

			SessionMessages.add(actionRequest, "edit-method.success");

		} catch (Exception e) {
			_log.error(e);
			actionResponse.setRenderParameters(actionRequest.getParameterMap());
			SessionErrors.add(actionRequest, e.getClass().getName());
		}
	}

	public void associatePluginSellers(ActionRequest actionRequest,
			ActionResponse actionResponse) throws PortletException, IOException {

		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);
			PermissionChecker permissionChecker = themeDisplay
					.getPermissionChecker();

			long pluginId = ParamUtil.getLong(actionRequest, "pluginId");
			PaymentAppPermission.check(permissionChecker,
					themeDisplay.getScopeGroupId(),
					ActionKeys.ADD_PAYMENT_PLUGIN_CONFIG);

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
					PaymentPluginConfig.class.getName(), actionRequest);

			List<Seller> sellers = SellerServiceUtil.search(
					themeDisplay.getCompanyId(), null, true, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, new SellerCreateDateComparator());

			for (Seller seller : sellers) {
				String paramName = "seller_" + seller.getSellerId();
				boolean sellerEnabled = ParamUtil.getBoolean(actionRequest,
						paramName);
				boolean sellerWasEnabled = PaymentPluginConfigLocalServiceUtil
						.hasPaymentPluginConfigBySellerIdAndPaymentPluginId(
								seller.getSellerId(), pluginId);

				if (!sellerWasEnabled && sellerEnabled) {
					PaymentPluginConfigLocalServiceUtil.addPaymentPluginConfig(
							seller.getUserId(), seller.getSellerId(), pluginId,
							serviceContext);

				} else if (sellerWasEnabled && !sellerEnabled) {
					PaymentPluginConfig paymentPluginConfig = PaymentPluginConfigLocalServiceUtil
							.getPaymentPluginConfigBySellerIdAndPaymentPluginId(
									seller.getSellerId(), pluginId);

					PaymentPluginConfigLocalServiceUtil
							.deletePaymentPluginConfig(paymentPluginConfig);
				}
			}

			String redirect = ParamUtil.getString(actionRequest, "successURL");
			actionResponse.sendRedirect(redirect);

			SessionMessages.add(actionRequest,
					"associate-plugin-sellers.success");

		} catch (Exception e) {
			_log.error(e);
			actionResponse.setRenderParameters(actionRequest.getParameterMap());
			SessionErrors.add(actionRequest, e.getClass().getName());
		}
	}

	public void associateSellerPlugins(ActionRequest actionRequest,
			ActionResponse actionResponse) throws PortletException, IOException {

		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);
			PermissionChecker permissionChecker = themeDisplay
					.getPermissionChecker();

			long sellerId = ParamUtil.getLong(actionRequest, "sellerId");
			PaymentAppPermission.check(permissionChecker,
					themeDisplay.getScopeGroupId(),
					ActionKeys.ADD_PAYMENT_PLUGIN_CONFIG);

			Seller seller = SellerLocalServiceUtil.getSeller(sellerId);

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
					PaymentPluginConfig.class.getName(), actionRequest);

			List<PaymentPlugin> paymentPlugins = PaymentPluginServiceUtil
					.search(themeDisplay.getCompanyId(), null, null, null,
							QueryUtil.ALL_POS, QueryUtil.ALL_POS,
							new PaymentPluginCreateDateComparator());

			for (PaymentPlugin paymentPlugin : paymentPlugins) {
				String paramName = "paymentPlugin_"
						+ paymentPlugin.getPaymentPluginId();
				boolean paymentPluginEnabled = ParamUtil.getBoolean(
						actionRequest, paramName);
				boolean paymentPluginWasEnabled = PaymentPluginConfigLocalServiceUtil
						.hasPaymentPluginConfigBySellerIdAndPaymentPluginId(
								sellerId, paymentPlugin.getPaymentPluginId());

				if (!paymentPluginWasEnabled && paymentPluginEnabled) {
					PaymentPluginConfigLocalServiceUtil.addPaymentPluginConfig(
							seller.getUserId(), sellerId,
							paymentPlugin.getPaymentPluginId(), serviceContext);

				} else if (paymentPluginWasEnabled && !paymentPluginEnabled) {
					PaymentPluginConfig paymentPluginConfig = PaymentPluginConfigLocalServiceUtil
							.getPaymentPluginConfigBySellerIdAndPaymentPluginId(
									sellerId,
									paymentPlugin.getPaymentPluginId());

					PaymentPluginConfigLocalServiceUtil
							.deletePaymentPluginConfig(paymentPluginConfig);
				}
			}

			String redirect = ParamUtil.getString(actionRequest, "successURL");
			actionResponse.sendRedirect(redirect);

			SessionMessages.add(actionRequest,
					"associate-seller-plugins.success");

		} catch (Exception e) {
			_log.error(e);
			actionResponse.setRenderParameters(actionRequest.getParameterMap());
			SessionErrors.add(actionRequest, e.getClass().getName());
		}
	}

	public void editRulesPriority(ActionRequest actionRequest,
			ActionResponse actionResponse) throws PortletException, IOException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);
			PermissionChecker permissionChecker = themeDisplay
					.getPermissionChecker();

			long sellerId = ParamUtil.getLong(actionRequest, "sellerId");

			SellerPermission.check(permissionChecker, sellerId,
					ActionKeys.UPDATE);

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
					Rule.class.getName(), actionRequest);

			for (Rule rule : RuleLocalServiceUtil.getSellerRules(sellerId,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

				int priority = ParamUtil.getInteger(actionRequest,
						"rulePriority_" + rule.getRuleId());

				RuleLocalServiceUtil.updateRule(rule.getRuleId(),
						rule.getPaymentPluginConfigId(), rule.getContent(),
						priority, serviceContext);
			}

			SessionMessages.add(actionRequest, "edit-rules-priority.success");

		} catch (Exception e) {
			_log.error(e);
			actionResponse.setRenderParameters(actionRequest.getParameterMap());
			SessionErrors.add(actionRequest, e.getClass().getName());
		}
	}

	public void editDefaultPluginConfig(ActionRequest actionRequest,
			ActionResponse actionResponse) throws PortletException, IOException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
					.getAttribute(WebKeys.THEME_DISPLAY);
			PermissionChecker permissionChecker = themeDisplay
					.getPermissionChecker();

			long sellerId = ParamUtil.getLong(actionRequest, "sellerId");
			long defaultPaymentPluginConfigId = ParamUtil.getLong(
					actionRequest, "defaultPaymentPluginConfigId");

			SellerPermission.check(permissionChecker, sellerId,
					ActionKeys.UPDATE);

			PaymentPluginConfigLocalServiceUtil.updateDefaultPluginConfig(
					sellerId, defaultPaymentPluginConfigId);

			SessionMessages.add(actionRequest,
					"edit-default-plugin-config.success");

		} catch (Exception e) {
			_log.error(e);
			actionResponse.setRenderParameters(actionRequest.getParameterMap());
			SessionErrors.add(actionRequest, e.getClass().getName());
		}
	}

	private boolean isConfigValid(ServletContext servletContext,
			PaymentPlugin paymentPlugin, String pluginConfig,
			boolean isSellerConfig) throws PortalException, SystemException {

		PaymentSystemPluginSender paymentSystemPluginSender = PaymentSystemUtil
				.getMessagingContext().getPaymentSystemPluginSender();

		return paymentSystemPluginSender.validateConfig(
				paymentPlugin.getApplicationId(), pluginConfig, isSellerConfig);
	}

	private static final Log _log = LogFactoryUtil.getLog(AdminPortlet.class);
}
