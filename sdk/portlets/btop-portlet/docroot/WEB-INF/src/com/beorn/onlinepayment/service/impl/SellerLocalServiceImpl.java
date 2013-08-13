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

import com.beorn.onlinepayment.DuplicateSellerException;
import com.beorn.onlinepayment.NoSuchPluginConfigException;
import com.beorn.onlinepayment.NoSuchSellerException;
import com.beorn.onlinepayment.RequiredSellerException;
import com.beorn.onlinepayment.SellerNameException;
import com.beorn.onlinepayment.model.PaymentPlugin;
import com.beorn.onlinepayment.model.PaymentPluginConfig;
import com.beorn.onlinepayment.model.Seller;
import com.beorn.onlinepayment.service.SellerLocalServiceUtil;
import com.beorn.onlinepayment.service.base.SellerLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
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
 * The implementation of the seller local service.
 * 
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun
 * ServiceBuilder to copy their definitions into the
 * {@link com.beorn.onlinepayment.service.SellerLocalService} interface.
 * 
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the
 * propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 * 
 * @author Sébastien Meunier
 * @author Olivier Bonnet-Torres
 * @see com.beorn.onlinepayment.service.base.SellerLocalServiceBaseImpl
 * @see com.beorn.onlinepayment.service.SellerLocalServiceUtil
 */
public class SellerLocalServiceImpl extends SellerLocalServiceBaseImpl {

	public Seller addSeller(long userId, String name, boolean active, ServiceContext serviceContext) throws PortalException,
			SystemException {

		User user = UserLocalServiceUtil.getUser(userId);
		long groupId = serviceContext.getScopeGroupId();
		Date now = new Date();

		validate(null, name, active, serviceContext);

		long sellerId = CounterLocalServiceUtil.increment();
		Seller seller = sellerPersistence.create(sellerId);

		seller.setGroupId(groupId);
		seller.setUserId(userId);
		seller.setCompanyId(user.getCompanyId());
		seller.setCreateDate(now);
		seller.setModifiedDate(now);

		seller.setName(name);
		seller.setActive(active);

		sellerPersistence.update(seller, false);

		// Resources

		if (serviceContext.isAddGroupPermissions() || serviceContext.isAddGuestPermissions()) {

			addSellerResources(seller, serviceContext.isAddGroupPermissions(), serviceContext.isAddGuestPermissions());

		} else {
			addSellerResources(seller, serviceContext.getGroupPermissions(), serviceContext.getGuestPermissions());
		}

		return seller;
	}

	public List<Seller> getSellers(int start, int end, OrderByComparator orderByComparator) throws PortalException,
			SystemException {

		return sellerPersistence.findAll(start, end, orderByComparator);
	}

	public Seller getDefaultSeller(ServiceContext serviceContext) throws PortalException, SystemException {
		long defaultUserId = UserLocalServiceUtil.getDefaultUserId(serviceContext.getCompanyId());

		Seller seller;

		String defaultSellerName = "defaultSeller";
		try {
			seller = SellerLocalServiceUtil.getSellerByCompanyIdAndName(serviceContext.getCompanyId(), defaultSellerName);

		} catch (NoSuchSellerException e) {

			seller = SellerLocalServiceUtil.addSeller(defaultUserId, defaultSellerName, true, serviceContext);
		}

		// Give it all plugins
		for (PaymentPlugin paymentPlugin : paymentPluginPersistence.findByCompanyId(serviceContext.getCompanyId())) {
			try {
				paymentPluginConfigLocalService.getPaymentPluginConfigBySellerIdAndPaymentPluginId(seller.getSellerId(),
						paymentPlugin.getPaymentPluginId());

			} catch (NoSuchPluginConfigException e) {
				paymentPluginConfigLocalService.addPaymentPluginConfig(defaultUserId, seller.getSellerId(),
						paymentPlugin.getPaymentPluginId(), null, true, serviceContext);
			}
		}

		return seller;
	}

	public List<Seller> getCompanySellers(long companyId, int start, int end, OrderByComparator orderByComparator)
			throws PortalException, SystemException {

		return sellerPersistence.findByCompanyId(companyId, start, end, orderByComparator);
	}

	public int getCompanySellersCount(long companyId) throws PortalException, SystemException {

		return sellerPersistence.countByCompanyId(companyId);
	}

	public Seller getSellerByCompanyIdAndName(long companyId, String name) throws PortalException, SystemException {

		return sellerPersistence.findByCompanyIdAndName(companyId, name);
	}

	public List<Seller> search(long companyId, String keywords, Boolean active, int start, int end,
			OrderByComparator orderByComparator) throws SystemException {

		return sellerFinder.search(companyId, keywords, active, start, end, orderByComparator, false);
	}

	public int searchCount(long companyId, String keywords, Boolean active) throws SystemException {
		return sellerFinder.searchCount(companyId, keywords, active, false);
	}

	public Seller updateSeller(long sellerId, String name, boolean active, ServiceContext serviceContext)
			throws SystemException, PortalException {

		Date now = new Date();

		Seller seller = sellerPersistence.findByPrimaryKey(sellerId);

		validate(seller, name, active, serviceContext);

		seller.setModifiedDate(now);

		seller.setName(name);
		seller.setActive(active);

		sellerPersistence.update(seller, false);

		// Resources

		if (serviceContext.isAddGroupPermissions() || serviceContext.isAddGuestPermissions()) {

			addSellerResources(seller, serviceContext.isAddGroupPermissions(), serviceContext.isAddGuestPermissions());

		} else {
			addSellerResources(seller, serviceContext.getGroupPermissions(), serviceContext.getGuestPermissions());
		}

		return seller;
	}

	public Seller deleteSeller(long sellerId) throws PortalException, SystemException {

		return deleteSeller(sellerPersistence.findByPrimaryKey(sellerId));
	}

	public Seller deleteSeller(Seller seller) throws SystemException, PortalException {

		// Transactions
		if (!sellerPersistence.getTransactions(seller.getSellerId()).isEmpty())
			throw new RequiredSellerException();

		// Payment Plugin Configs
		for (PaymentPluginConfig paymentPluginConfig : sellerPersistence.getPaymentPluginConfigs(seller.getSellerId())) {

			paymentPluginConfigLocalService.deletePaymentPluginConfig(paymentPluginConfig);
		}

		// Resources
		resourceLocalService.deleteResource(seller.getCompanyId(), Seller.class.getName(),
				ResourceConstants.SCOPE_INDIVIDUAL, seller.getSellerId());

		// Seller
		return sellerPersistence.remove(seller);
	}

	protected void addSellerResources(Seller seller, boolean addCommunityPermissions, boolean addGuestPermissions)
			throws PortalException, SystemException {

		resourceLocalService.addResources(seller.getCompanyId(), seller.getGroupId(), seller.getUserId(),
				Seller.class.getName(), seller.getSellerId(), false, addCommunityPermissions, addGuestPermissions);
	}

	protected void addSellerResources(Seller seller, String[] communityPermissions, String[] guestPermissions)
			throws PortalException, SystemException {

		resourceLocalService.addModelResources(seller.getCompanyId(), seller.getGroupId(), seller.getUserId(),
				Seller.class.getName(), seller.getSellerId(), communityPermissions, guestPermissions);
	}

	protected void addSellerResources(long sellerId, boolean addCommunityPermissions, boolean addGuestPermissions)
			throws PortalException, SystemException {

		Seller seller = sellerPersistence.findByPrimaryKey(sellerId);
		addSellerResources(seller, addCommunityPermissions, addGuestPermissions);
	}

	protected void addSellerResources(long sellerId, String[] communityPermissions, String[] guestPermissions)
			throws PortalException, SystemException {

		addSellerResources(sellerId, communityPermissions, guestPermissions);
	}

	private void validate(Seller seller, String name, boolean active, ServiceContext serviceContext) throws PortalException,
			SystemException {

		if (Validator.isNull(name))
			throw new SellerNameException();

		Seller sameNameSeller = sellerPersistence.fetchByCompanyIdAndName(serviceContext.getCompanyId(), name);

		if (seller == null && sameNameSeller != null || seller != null && sameNameSeller != null
				&& !seller.equals(sameNameSeller)) {

			throw new DuplicateSellerException("There is already a seller named \"" + name + "\"");
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(SellerLocalServiceImpl.class);
}