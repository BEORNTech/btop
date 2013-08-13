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

package com.beorn.onlinepayment.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.beorn.onlinepayment.DuplicatePaymentPluginException;
import com.beorn.onlinepayment.PaymentPluginApplicationIdException;
import com.beorn.onlinepayment.PaymentPluginNameException;
import com.beorn.onlinepayment.model.PaymentMethod;
import com.beorn.onlinepayment.model.PaymentPlugin;
import com.beorn.onlinepayment.model.PaymentPluginConfig;
import com.beorn.onlinepayment.model.Rule;
import com.beorn.onlinepayment.model.Transaction;
import com.beorn.onlinepayment.rule.Condition;
import com.beorn.onlinepayment.rule.RuleUtil;
import com.beorn.onlinepayment.rule.payment.TransactionConditionSubject;
import com.beorn.onlinepayment.service.PaymentPluginConfigLocalServiceUtil;
import com.beorn.onlinepayment.service.PaymentPluginLocalServiceUtil;
import com.beorn.onlinepayment.service.TransactionLocalServiceUtil;
import com.beorn.onlinepayment.service.base.PaymentPluginLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * The implementation of the payment plugin local service.
 * 
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.beorn.onlinepayment.service.PaymentPluginLocalService} interface.
 * 
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 * 
 * @author Sébastien Meunier
 * @see com.beorn.onlinepayment.service.base.PaymentPluginLocalServiceBaseImpl
 * @see com.beorn.onlinepayment.service.PaymentPluginLocalServiceUtil
 */
public class PaymentPluginLocalServiceImpl extends
		PaymentPluginLocalServiceBaseImpl {

	public PaymentPlugin addPaymentPlugin(long userId, String applicationId,
			Map<Locale, String> nameMap, String pluginConfigParameters,
			String sellerConfigParameters, ServiceContext serviceContext)
			throws PortalException, SystemException {

		User user = UserLocalServiceUtil.getUser(userId);
		long groupId = serviceContext.getScopeGroupId();
		Date now = new Date();

		boolean configured = Validator.isNull(pluginConfigParameters);

		validate(null, applicationId, nameMap, pluginConfigParameters,
				sellerConfigParameters, null, configured, serviceContext);

		long paymentPluginId = CounterLocalServiceUtil.increment();
		PaymentPlugin paymentPlugin = paymentPluginPersistence
				.create(paymentPluginId);

		paymentPlugin.setGroupId(groupId);
		paymentPlugin.setUserId(userId);
		paymentPlugin.setCompanyId(user.getCompanyId());
		paymentPlugin.setCreateDate(now);
		paymentPlugin.setModifiedDate(now);

		paymentPlugin.setApplicationId(applicationId);
		paymentPlugin.setNameMap(nameMap, serviceContext.getLocale());
		paymentPlugin.setPluginConfigParameters(pluginConfigParameters);
		paymentPlugin.setSellerConfigParameters(sellerConfigParameters);
		paymentPlugin.setPluginConfig(null);
		paymentPlugin.setConfigured(configured);

		paymentPluginPersistence.update(paymentPlugin, false);

		// Resources

		if (serviceContext.isAddGroupPermissions()
				|| serviceContext.isAddGuestPermissions()) {
			addPaymentPluginResources(paymentPlugin,
					serviceContext.isAddGroupPermissions(),
					serviceContext.isAddGuestPermissions());

		} else {
			addPaymentPluginResources(paymentPlugin,
					serviceContext.getGroupPermissions(),
					serviceContext.getGuestPermissions());
		}

		return paymentPlugin;
	}

	public List<PaymentPlugin> getPaymentPlugins(int start, int end,
			OrderByComparator orderByComparator) throws PortalException,
			SystemException {

		return paymentPluginPersistence.findAll(start, end, orderByComparator);
	}

	public List<PaymentPlugin> getSellerPaymentPlugins(long sellerId,
			Boolean configured, int start, int end,
			OrderByComparator orderByComparator) throws PortalException,
			SystemException {

		return paymentPluginFinder.findBySellerId(sellerId, configured, start,
				end, orderByComparator);
	}

	public int getSellerPaymentPluginsCount(long sellerId, Boolean configured)
			throws PortalException, SystemException {
		return paymentPluginFinder.countBySellerId(sellerId, configured);
	}

	public List<PaymentPlugin> getPaymentMethodPaymentPlugins(
			long paymentMethodId, int start, int end,
			OrderByComparator orderByComparator) throws PortalException,
			SystemException {

		return paymentMethodPersistence.getPaymentPlugins(paymentMethodId,
				start, end, orderByComparator);
	}

	public int getPaymentMethodPaymentPluginsCount(long paymentMethodId)
			throws PortalException, SystemException {
		return paymentMethodPersistence.getPaymentPluginsSize(paymentMethodId);
	}

	public PaymentPlugin getPaymentPluginByApplicationId(String applicationId)
			throws PortalException, SystemException {
		return paymentPluginPersistence.findByApplicationId(applicationId);
	}

	public List<PaymentPlugin> getAvailablePaymentPlugins(long transactionId,
			long paymentMethodId, String countryCode) throws PortalException,
			SystemException {

		List<PaymentPlugin> paymentPlugins = new ArrayList<PaymentPlugin>();

		Transaction transaction = TransactionLocalServiceUtil
				.getTransaction(transactionId);

		// Plugins from rules

		List<Rule> rules = ruleFinder.findByTransactionAndPaymentMethod(
				transactionId, paymentMethodId, true, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		if (!rules.isEmpty()) {
			TransactionConditionSubject subject = new TransactionConditionSubject(
					transaction, countryCode);

			for (Rule rule : rules) {
				String serializedRule = rule.getContent();

				if (Validator.isNotNull(serializedRule)) {
					try {
						Condition<TransactionConditionSubject> condition = RuleUtil
								.fromString(serializedRule);

						if (!condition.isSatisfiedBy(subject))
							continue;

					} catch (Throwable t) {
						_log.error("Error while checking rule \""
								+ serializedRule + "\" from seller "
								+ transaction.getSellerId(), t);
						continue;
					}
				}

				PaymentPluginConfig paymentPluginConfig = PaymentPluginConfigLocalServiceUtil
						.getPaymentPluginConfig(rule.getPaymentPluginConfigId());

				PaymentPlugin paymentPlugin = PaymentPluginLocalServiceUtil
						.getPaymentPlugin(paymentPluginConfig
								.getPaymentPluginId());

				paymentPlugins.add(paymentPlugin);
			}
		}

		// Default plugin

		PaymentPluginConfig defaultPaymentPluginConfig = paymentPluginConfigLocalService
				.getSellerDefaultPaymentPluginConfig(transaction.getSellerId());

		if (defaultPaymentPluginConfig != null
				&& paymentPluginPersistence.containsPaymentMethod(
						defaultPaymentPluginConfig.getPaymentPluginId(),
						paymentMethodId)) {

			paymentPlugins.add(paymentPluginLocalService
					.getPaymentPlugin(defaultPaymentPluginConfig
							.getPaymentPluginId()));
		}

		return paymentPlugins;
	}

	public List<PaymentPlugin> getPaymentPluginsByTransactionAndPaymentMethod(
			long transactionId, long paymentMethodId, Boolean configured,
			int start, int end, OrderByComparator obc) throws PortalException,
			SystemException {

		return paymentPluginFinder.findByTransactionAndPaymentMethod(
				transactionId, paymentMethodId, configured, start, end, obc);
	}

	public int getPaymentPluginsByTransactionAndPaymentMethodCount(
			long transactionId, long paymentMethodId, Boolean configured)
			throws PortalException, SystemException {

		return paymentPluginFinder.countByTransactionAndPaymentMethod(
				transactionId, paymentMethodId, configured);
	}

	public List<PaymentPlugin> search(long companyId, String keywords,
			Boolean active, Long paymentMethodId, int start, int end,
			OrderByComparator orderByComparator) throws SystemException {

		return paymentPluginFinder.search(companyId, keywords, active,
				paymentMethodId, start, end, orderByComparator, false);
	}

	public int searchCount(long companyId, String keywords, Boolean active,
			Long paymentMethodId) throws SystemException {
		return paymentPluginFinder.searchCount(companyId, keywords, active,
				paymentMethodId, false);
	}

	public PaymentPlugin updatePaymentPlugin(long paymentPluginId,
			String applicationId, Map<Locale, String> nameMap,
			String pluginConfigParameters, String sellerConfigParameters,
			String pluginConfig, boolean configured,
			ServiceContext serviceContext) throws SystemException,
			PortalException {

		Date now = new Date();

		PaymentPlugin paymentPlugin = paymentPluginPersistence
				.findByPrimaryKey(paymentPluginId);

		validate(paymentPlugin, applicationId, nameMap, pluginConfigParameters,
				sellerConfigParameters, pluginConfig, configured,
				serviceContext);

		paymentPlugin.setModifiedDate(now);

		paymentPlugin.setApplicationId(applicationId);
		paymentPlugin.setNameMap(nameMap, serviceContext.getLocale());
		if (pluginConfigParameters != null)
			paymentPlugin.setPluginConfigParameters(pluginConfigParameters);
		if (sellerConfigParameters != null)
			paymentPlugin.setSellerConfigParameters(sellerConfigParameters);
		paymentPlugin.setPluginConfig(pluginConfig);
		paymentPlugin.setConfigured(configured);

		paymentPluginPersistence.update(paymentPlugin, false);

		// Resources

		if (serviceContext.isAddGroupPermissions()
				|| serviceContext.isAddGuestPermissions()) {
			addPaymentPluginResources(paymentPlugin,
					serviceContext.isAddGroupPermissions(),
					serviceContext.isAddGuestPermissions());

		} else {
			addPaymentPluginResources(paymentPlugin,
					serviceContext.getGroupPermissions(),
					serviceContext.getGuestPermissions());
		}

		return paymentPlugin;
	}

	public void updatePaymentPluginPaymentMethods(long paymentPluginId,
			List<PaymentMethod> paymentMethods) throws SystemException,
			PortalException {

		paymentPluginPersistence.setPaymentMethods(paymentPluginId,
				paymentMethods);
	}

	public PaymentPlugin deletePaymentPlugin(long paymentPluginId)
			throws PortalException, SystemException {
		return deletePaymentPlugin(paymentPluginPersistence
				.findByPrimaryKey(paymentPluginId));
	}

	public PaymentPlugin deletePaymentPlugin(PaymentPlugin paymentPlugin)
			throws SystemException, PortalException {

		// Payment Plugin Configs
		for (PaymentPluginConfig paymentPluginConfig : paymentPluginPersistence
				.getPaymentPluginConfigs(paymentPlugin.getPaymentPluginId())) {

			paymentPluginConfigLocalService
					.deletePaymentPluginConfig(paymentPluginConfig);
		}

		// Resources
		resourceLocalService.deleteResource(paymentPlugin.getCompanyId(),
				PaymentPlugin.class.getName(),
				ResourceConstants.SCOPE_INDIVIDUAL,
				paymentPlugin.getPaymentPluginId());

		// PaymentPlugin
		return paymentPluginPersistence.remove(paymentPlugin);
	}

	protected void addPaymentPluginResources(PaymentPlugin paymentPlugin,
			boolean addCommunityPermissions, boolean addGuestPermissions)
			throws PortalException, SystemException {

		resourceLocalService.addResources(paymentPlugin.getCompanyId(),
				paymentPlugin.getGroupId(), paymentPlugin.getUserId(),
				PaymentPlugin.class.getName(),
				paymentPlugin.getPaymentPluginId(), false,
				addCommunityPermissions, addGuestPermissions);
	}

	protected void addPaymentPluginResources(PaymentPlugin paymentPlugin,
			String[] communityPermissions, String[] guestPermissions)
			throws PortalException, SystemException {

		resourceLocalService.addModelResources(paymentPlugin.getCompanyId(),
				paymentPlugin.getGroupId(), paymentPlugin.getUserId(),
				PaymentPlugin.class.getName(),
				paymentPlugin.getPaymentPluginId(), communityPermissions,
				guestPermissions);
	}

	protected void addPaymentPluginResources(long paymentPluginId,
			boolean addCommunityPermissions, boolean addGuestPermissions)
			throws PortalException, SystemException {

		PaymentPlugin paymentPlugin = paymentPluginPersistence
				.findByPrimaryKey(paymentPluginId);
		addPaymentPluginResources(paymentPlugin, addCommunityPermissions,
				addGuestPermissions);
	}

	protected void addPaymentPluginResources(long paymentPluginId,
			String[] communityPermissions, String[] guestPermissions)
			throws PortalException, SystemException {

		addPaymentPluginResources(paymentPluginId, communityPermissions,
				guestPermissions);
	}

	private void validate(PaymentPlugin paymentPlugin, String applicationId,
			Map<Locale, String> nameMap, String pluginConfigParameters,
			String sellerConfigParameters, String pluginConfig,
			boolean configured, ServiceContext serviceContext)
			throws PortalException, SystemException {

		if (Validator.isNull(applicationId))
			throw new PaymentPluginApplicationIdException();

		PaymentPlugin samePaymentPlugin = paymentPluginPersistence
				.fetchByApplicationId(applicationId);

		if (paymentPlugin == null && samePaymentPlugin != null
				|| paymentPlugin != null && samePaymentPlugin != null
				&& !paymentPlugin.equals(samePaymentPlugin)) {

			throw new DuplicatePaymentPluginException(
					"There is already a payment plugin named \""
							+ applicationId + "\"");
		}

		if (Validator.isNull(nameMap.get(serviceContext.getLocale())))
			throw new PaymentPluginNameException();
	}

	private static Log _log = LogFactoryUtil
			.getLog(PaymentPluginLocalServiceImpl.class);
}