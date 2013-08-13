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

import java.util.Date;
import java.util.List;

import com.beorn.onlinepayment.DuplicatePaymentPluginConfigException;
import com.beorn.onlinepayment.model.PaymentPlugin;
import com.beorn.onlinepayment.model.PaymentPluginConfig;
import com.beorn.onlinepayment.model.Rule;
import com.beorn.onlinepayment.service.PaymentPluginLocalServiceUtil;
import com.beorn.onlinepayment.service.base.PaymentPluginConfigLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * The implementation of the payment plugin config local service.
 * 
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.beorn.onlinepayment.service.PaymentPluginConfigLocalService}
 * interface.
 * 
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 * 
 * @author Sébastien Meunier
 * @see com.beorn.onlinepayment.service.base.PaymentPluginConfigLocalServiceBaseImpl
 * @see com.beorn.onlinepayment.service.PaymentPluginConfigLocalServiceUtil
 */
public class PaymentPluginConfigLocalServiceImpl extends
		PaymentPluginConfigLocalServiceBaseImpl {

	public PaymentPluginConfig addPaymentPluginConfig(long userId,
			long sellerId, long paymentPluginId, ServiceContext serviceContext)
			throws PortalException, SystemException {

		PaymentPlugin paymentPlugin = PaymentPluginLocalServiceUtil
				.getPaymentPlugin(paymentPluginId);

		return addPaymentPluginConfig(userId, sellerId, paymentPluginId, null,
				Validator.isNull(paymentPlugin.getSellerConfigParameters()),
				serviceContext);
	}

	public PaymentPluginConfig addPaymentPluginConfig(long userId,
			long sellerId, long paymentPluginId, String config,
			boolean configured, ServiceContext serviceContext)
			throws PortalException, SystemException {

		User user = UserLocalServiceUtil.getUser(userId);
		long groupId = serviceContext.getScopeGroupId();
		Date now = new Date();

		validate(null, sellerId, paymentPluginId, config, serviceContext);

		long paymentPluginConfigId = CounterLocalServiceUtil.increment();
		PaymentPluginConfig paymentPluginConfig = paymentPluginConfigPersistence
				.create(paymentPluginConfigId);

		paymentPluginConfig.setGroupId(groupId);
		paymentPluginConfig.setUserId(userId);
		paymentPluginConfig.setCompanyId(user.getCompanyId());
		paymentPluginConfig.setCreateDate(now);
		paymentPluginConfig.setModifiedDate(now);

		paymentPluginConfig.setSellerId(sellerId);
		paymentPluginConfig.setPaymentPluginId(paymentPluginId);
		paymentPluginConfig.setConfig(config);
		paymentPluginConfig.setConfigured(configured);

		paymentPluginConfigPersistence.update(paymentPluginConfig, false);

		// Resources

		if (serviceContext.isAddGroupPermissions()
				|| serviceContext.isAddGuestPermissions()) {

			addPaymentPluginConfigResources(paymentPluginConfig,
					serviceContext.isAddGroupPermissions(),
					serviceContext.isAddGuestPermissions());

		} else {
			addPaymentPluginConfigResources(paymentPluginConfig,
					serviceContext.getGroupPermissions(),
					serviceContext.getGuestPermissions());
		}

		return paymentPluginConfig;
	}

	public List<PaymentPluginConfig> getSellerPaymentPluginConfigs(
			long sellerId, int start, int end,
			OrderByComparator orderByComparator) throws PortalException,
			SystemException {

		return paymentPluginConfigPersistence.findBySellerId(sellerId, start,
				end, orderByComparator);
	}

	public List<PaymentPluginConfig> getSellerPaymentPluginConfigs(long sellerId)
			throws PortalException, SystemException {

		return paymentPluginConfigPersistence.findBySellerId(sellerId);
	}

	public int getSellerPaymentPluginConfigsCount(long sellerId)
			throws PortalException, SystemException {

		return paymentPluginConfigPersistence.countBySellerId(sellerId);
	}

	public List<PaymentPluginConfig> getPaymentPluginPaymentPluginConfigs(
			long paymentPluginId) throws PortalException, SystemException {

		return paymentPluginConfigPersistence
				.findByPaymentPluginId(paymentPluginId);
	}

	public int getPaymentPluginPaymentPluginConfigsCount(long paymentPluginId)
			throws PortalException, SystemException {

		return paymentPluginConfigPersistence
				.countByPaymentPluginId(paymentPluginId);
	}

	public boolean hasPaymentPluginConfigBySellerIdAndPaymentPluginId(
			long sellerId, long paymentPluginId) throws PortalException,
			SystemException {

		return paymentPluginConfigPersistence
				.countBySellerIdAndPaymentPluginId(sellerId, paymentPluginId) > 0;
	}

	public PaymentPluginConfig getPaymentPluginConfigBySellerIdAndPaymentPluginId(
			long sellerId, long paymentPluginId) throws PortalException,
			SystemException {

		return paymentPluginConfigPersistence.findBySellerIdAndPaymentPluginId(
				sellerId, paymentPluginId);
	}

	public PaymentPluginConfig getSellerDefaultPaymentPluginConfig(long sellerId)
			throws PortalException, SystemException {

		return paymentPluginConfigPersistence.fetchBySellerIdAndDefaultPlugin(
				sellerId, true);
	}

	public PaymentPluginConfig updatePaymentPluginConfig(
			long paymentPluginConfigId, long sellerId, long paymentPluginId,
			String config, boolean configured, ServiceContext serviceContext)
			throws SystemException, PortalException {

		Date now = new Date();

		PaymentPluginConfig paymentPluginConfig = paymentPluginConfigPersistence
				.findByPrimaryKey(paymentPluginConfigId);

		validate(paymentPluginConfig, sellerId, paymentPluginId, config,
				serviceContext);

		paymentPluginConfig.setModifiedDate(now);

		paymentPluginConfig.setSellerId(sellerId);
		paymentPluginConfig.setPaymentPluginId(paymentPluginId);
		paymentPluginConfig.setConfig(config);
		paymentPluginConfig.setConfigured(configured);

		paymentPluginConfigPersistence.update(paymentPluginConfig, false);

		// Resources

		if (serviceContext.isAddGroupPermissions()
				|| serviceContext.isAddGuestPermissions()) {

			addPaymentPluginConfigResources(paymentPluginConfig,
					serviceContext.isAddGroupPermissions(),
					serviceContext.isAddGuestPermissions());

		} else {
			addPaymentPluginConfigResources(paymentPluginConfig,
					serviceContext.getGroupPermissions(),
					serviceContext.getGuestPermissions());
		}

		return paymentPluginConfig;
	}

	public void updateDefaultPluginConfig(long sellerId,
			long defaultPaymentPluginConfigId) throws SystemException,
			PortalException {

		for (PaymentPluginConfig paymentPluginConfig : paymentPluginConfigPersistence
				.findBySellerId(sellerId)) {

			if (!paymentPluginConfig.isDefaultPlugin()
					&& paymentPluginConfig.getPaymentPluginConfigId() == defaultPaymentPluginConfigId) {

				paymentPluginConfig.setDefaultPlugin(true);
				paymentPluginConfigPersistence.update(paymentPluginConfig,
						false);

			} else if (paymentPluginConfig.isDefaultPlugin()
					&& paymentPluginConfig.getPaymentPluginConfigId() != defaultPaymentPluginConfigId) {

				paymentPluginConfig.setDefaultPlugin(false);
				paymentPluginConfigPersistence.update(paymentPluginConfig,
						false);
			}
		}
	}

	public PaymentPluginConfig deletePaymentPluginConfig(
			long paymentPluginConfigId) throws PortalException, SystemException {

		return deletePaymentPluginConfig(paymentPluginConfigPersistence
				.findByPrimaryKey(paymentPluginConfigId));
	}

	public PaymentPluginConfig deletePaymentPluginConfig(
			PaymentPluginConfig paymentPluginConfig) throws SystemException,
			PortalException {

		// Rules
		for (Rule rule : paymentPluginConfigPersistence
				.getRules(paymentPluginConfig.getPaymentPluginConfigId())) {
			ruleLocalService.deleteRule(rule);
		}

		// Resources
		resourceLocalService.deleteResource(paymentPluginConfig.getCompanyId(),
				PaymentPluginConfig.class.getName(),
				ResourceConstants.SCOPE_INDIVIDUAL,
				paymentPluginConfig.getPaymentPluginConfigId());

		// PaymentPluginConfig
		return paymentPluginConfigPersistence.remove(paymentPluginConfig);
	}

	protected void addPaymentPluginConfigResources(
			PaymentPluginConfig paymentPluginConfig,
			boolean addCommunityPermissions, boolean addGuestPermissions)
			throws PortalException, SystemException {

		resourceLocalService.addResources(paymentPluginConfig.getCompanyId(),
				paymentPluginConfig.getGroupId(),
				paymentPluginConfig.getUserId(),
				PaymentPluginConfig.class.getName(),
				paymentPluginConfig.getPaymentPluginConfigId(), false,
				addCommunityPermissions, addGuestPermissions);
	}

	protected void addPaymentPluginConfigResources(
			PaymentPluginConfig paymentPluginConfig,
			String[] communityPermissions, String[] guestPermissions)
			throws PortalException, SystemException {

		resourceLocalService.addModelResources(
				paymentPluginConfig.getCompanyId(),
				paymentPluginConfig.getGroupId(),
				paymentPluginConfig.getUserId(),
				PaymentPluginConfig.class.getName(),
				paymentPluginConfig.getPaymentPluginConfigId(),
				communityPermissions, guestPermissions);
	}

	protected void addPaymentPluginConfigResources(long paymentPluginConfigId,
			boolean addCommunityPermissions, boolean addGuestPermissions)
			throws PortalException, SystemException {

		PaymentPluginConfig paymentPluginConfig = paymentPluginConfigPersistence
				.findByPrimaryKey(paymentPluginConfigId);
		addPaymentPluginConfigResources(paymentPluginConfig,
				addCommunityPermissions, addGuestPermissions);
	}

	protected void addPaymentPluginConfigResources(long paymentPluginConfigId,
			String[] communityPermissions, String[] guestPermissions)
			throws PortalException, SystemException {

		addPaymentPluginConfigResources(paymentPluginConfigId,
				communityPermissions, guestPermissions);
	}

	private void validate(PaymentPluginConfig paymentPluginConfig,
			long sellerId, long paymentPluginId, String config,
			ServiceContext serviceContext) throws PortalException,
			SystemException {

		sellerPersistence.findByPrimaryKey(sellerId);
		paymentPluginPersistence.findByPrimaryKey(paymentPluginId);

		PaymentPluginConfig samePaymentPluginConfig = paymentPluginConfigPersistence
				.fetchBySellerIdAndPaymentPluginId(sellerId, paymentPluginId);

		if (paymentPluginConfig == null && samePaymentPluginConfig != null
				|| paymentPluginConfig != null
				&& samePaymentPluginConfig != null
				&& !paymentPluginConfig.equals(samePaymentPluginConfig)) {

			throw new DuplicatePaymentPluginConfigException(
					"There is already a payment plugin config for seller \""
							+ sellerId + "\" and payment plugin "
							+ paymentPluginId);
		}
	}
}