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
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.beorn.onlinepayment.DuplicatePaymentPluginException;
import com.beorn.onlinepayment.PaymentMethodKeyException;
import com.beorn.onlinepayment.PaymentMethodNameException;
import com.beorn.onlinepayment.RequiredPaymentMethodException;
import com.beorn.onlinepayment.model.PaymentMethod;
import com.beorn.onlinepayment.model.PaymentPlugin;
import com.beorn.onlinepayment.model.Transaction;
import com.beorn.onlinepayment.service.PaymentPluginLocalServiceUtil;
import com.beorn.onlinepayment.service.base.PaymentMethodLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * The implementation of the payment method local service.
 * 
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun
 * ServiceBuilder to copy their definitions into the
 * {@link com.beorn.onlinepayment.service.PaymentMethodLocalService} interface.
 * 
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the
 * propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 * 
 * @author Sébastien Meunier
 * @see com.beorn.onlinepayment.service.base.PaymentMethodLocalServiceBaseImpl
 * @see com.beorn.onlinepayment.service.PaymentMethodLocalServiceUtil
 */
public class PaymentMethodLocalServiceImpl extends PaymentMethodLocalServiceBaseImpl {

	public PaymentMethod addPaymentMethod(long userId, String key, Map<Locale, String> nameMap, ServiceContext serviceContext)
			throws PortalException, SystemException {

		User user = UserLocalServiceUtil.getUser(userId);
		long groupId = serviceContext.getScopeGroupId();
		Date now = new Date();

		validate(null, key, nameMap, serviceContext);

		long paymentMethodId = CounterLocalServiceUtil.increment();
		PaymentMethod paymentMethod = paymentMethodPersistence.create(paymentMethodId);

		paymentMethod.setGroupId(groupId);
		paymentMethod.setUserId(userId);
		paymentMethod.setCompanyId(user.getCompanyId());
		paymentMethod.setCreateDate(now);
		paymentMethod.setModifiedDate(now);

		paymentMethod.setKey(key);
		paymentMethod.setNameMap(nameMap, serviceContext.getLocale());

		paymentMethodPersistence.update(paymentMethod, false);

		// Resources

		if (serviceContext.isAddGroupPermissions() || serviceContext.isAddGuestPermissions()) {

			addPaymentMethodResources(paymentMethod, serviceContext.isAddGroupPermissions(),
					serviceContext.isAddGuestPermissions());

		} else {
			addPaymentMethodResources(paymentMethod, serviceContext.getGroupPermissions(),
					serviceContext.getGuestPermissions());
		}

		return paymentMethod;
	}

	public PaymentMethod getPaymentMethodByKey(String key) throws SystemException, PortalException {

		return paymentMethodPersistence.findByKey(key);
	}

	public List<PaymentMethod> getSellerPaymentMethods(long sellerId, Boolean configured, int start, int end,
			OrderByComparator orderByComparator) throws SystemException {

		return paymentMethodFinder.findBySellerId(sellerId, configured, start, end, orderByComparator);
	}

	public int getSellerPaymentMethodsCount(long sellerId, Boolean configured, int start, int end,
			OrderByComparator orderByComparator) throws SystemException {

		return paymentMethodFinder.countBySellerId(sellerId, configured);
	}

	public List<PaymentMethod> getTransactionAvailablePaymentMethods(Transaction transaction, String countryCode)
			throws SystemException, PortalException {

		List<PaymentMethod> paymentMethods = getSellerPaymentMethods(transaction.getSellerId(), true, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		List<PaymentMethod> filteredPaymentMethods = new ArrayList<PaymentMethod>(paymentMethods.size());
		for (PaymentMethod paymentMethod : paymentMethods) {
			List<PaymentPlugin> availablePaymentPlugins = PaymentPluginLocalServiceUtil.getAvailablePaymentPlugins(
					transaction.getTransactionId(), paymentMethod.getPaymentMethodId(), countryCode);

			if (!availablePaymentPlugins.isEmpty())
				filteredPaymentMethods.add(paymentMethod);
		}

		return filteredPaymentMethods;
	}

	public List<PaymentMethod> getPaymentPluginPaymentMethods(long paymentPluginId, int start, int end,
			OrderByComparator orderByComparator) throws SystemException {

		return paymentPluginPersistence.getPaymentMethods(paymentPluginId, start, end, orderByComparator);
	}

	public int getPaymentPluginPaymentMethodsCount(long paymentPluginId, int start, int end,
			OrderByComparator orderByComparator) throws SystemException {

		return paymentPluginPersistence.getPaymentMethodsSize(paymentPluginId);
	}

	public List<PaymentMethod> search(long companyId, String keywords, int start, int end,
			OrderByComparator orderByComparator) throws SystemException {

		return paymentMethodFinder.search(companyId, keywords, start, end, orderByComparator, false);
	}

	public int searchCount(long companyId, String keywords) throws SystemException {
		return paymentMethodFinder.searchCount(companyId, keywords, false);
	}

	public PaymentMethod updatePaymentMethod(long paymentMethodId, String key, Map<Locale, String> nameMap,
			ServiceContext serviceContext) throws SystemException, PortalException {

		Date now = new Date();

		PaymentMethod paymentMethod = paymentMethodPersistence.findByPrimaryKey(paymentMethodId);

		validate(paymentMethod, key, nameMap, serviceContext);

		paymentMethod.setModifiedDate(now);

		paymentMethod.setKey(key);
		paymentMethod.setNameMap(nameMap, serviceContext.getLocale());

		paymentMethodPersistence.update(paymentMethod, false);

		// Resources

		if (serviceContext.isAddGroupPermissions() || serviceContext.isAddGuestPermissions()) {

			addPaymentMethodResources(paymentMethod, serviceContext.isAddGroupPermissions(),
					serviceContext.isAddGuestPermissions());

		} else {
			addPaymentMethodResources(paymentMethod, serviceContext.getGroupPermissions(),
					serviceContext.getGuestPermissions());
		}

		return paymentMethod;
	}

	public PaymentMethod deletePaymentMethod(long paymentMethodId) throws PortalException, SystemException {

		return deletePaymentMethod(paymentMethodPersistence.findByPrimaryKey(paymentMethodId));
	}

	public PaymentMethod deletePaymentMethod(PaymentMethod paymentMethod) throws SystemException, PortalException {

		// Payment Plugins
		if (!paymentMethodPersistence.getPaymentPlugins(paymentMethod.getPaymentMethodId()).isEmpty())
			throw new RequiredPaymentMethodException();

		// Resources
		resourceLocalService.deleteResource(paymentMethod.getCompanyId(), PaymentMethod.class.getName(),
				ResourceConstants.SCOPE_INDIVIDUAL, paymentMethod.getPaymentMethodId());

		// PaymentMethod
		return paymentMethodPersistence.remove(paymentMethod);
	}

	protected void addPaymentMethodResources(PaymentMethod paymentMethod, boolean addCommunityPermissions,
			boolean addGuestPermissions) throws PortalException, SystemException {

		resourceLocalService.addResources(paymentMethod.getCompanyId(), paymentMethod.getGroupId(),
				paymentMethod.getUserId(), PaymentMethod.class.getName(), paymentMethod.getPaymentMethodId(), false,
				addCommunityPermissions, addGuestPermissions);
	}

	protected void addPaymentMethodResources(PaymentMethod paymentMethod, String[] communityPermissions,
			String[] guestPermissions) throws PortalException, SystemException {

		resourceLocalService.addModelResources(paymentMethod.getCompanyId(), paymentMethod.getGroupId(),
				paymentMethod.getUserId(), PaymentMethod.class.getName(), paymentMethod.getPaymentMethodId(),
				communityPermissions, guestPermissions);
	}

	protected void addPaymentMethodResources(long paymentMethodId, boolean addCommunityPermissions,
			boolean addGuestPermissions) throws PortalException, SystemException {

		PaymentMethod paymentMethod = paymentMethodPersistence.findByPrimaryKey(paymentMethodId);
		addPaymentMethodResources(paymentMethod, addCommunityPermissions, addGuestPermissions);
	}

	protected void addPaymentMethodResources(long paymentMethodId, String[] communityPermissions, String[] guestPermissions)
			throws PortalException, SystemException {

		addPaymentMethodResources(paymentMethodId, communityPermissions, guestPermissions);
	}

	private void validate(PaymentMethod paymentMethod, String key, Map<Locale, String> nameMap, ServiceContext serviceContext)
			throws PortalException, SystemException {

		if (Validator.isNull(key))
			throw new PaymentMethodKeyException();

		PaymentMethod samePaymentMethod = paymentMethodPersistence.fetchByKey(key);

		if (paymentMethod == null && samePaymentMethod != null || paymentMethod != null && samePaymentMethod != null
				&& !paymentMethod.equals(samePaymentMethod)) {

			throw new DuplicatePaymentPluginException("There is already a payment method with the key \"" + key + "\"");
		}

		if (Validator.isNull(nameMap.get(serviceContext.getLocale())))
			throw new PaymentMethodNameException();
	}
}