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

import com.beorn.onlinepayment.model.TransactionParameter;
import com.beorn.onlinepayment.service.base.TransactionParameterLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * The implementation of the transaction parameter local service.
 * 
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.beorn.onlinepayment.service.TransactionParameterLocalService}
 * interface.
 * 
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 * 
 * @author Sébastien Meunier
 * @see com.beorn.onlinepayment.service.base.TransactionParameterLocalServiceBaseImpl
 * @see com.beorn.onlinepayment.service.TransactionParameterLocalServiceUtil
 */
public class TransactionParameterLocalServiceImpl extends
		TransactionParameterLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 * 
	 * Never reference this interface directly. Always use {@link
	 * com.beorn.onlinepayment.service.TransactionParameterLocalServiceUtil} to
	 * access the transaction parameter local service.
	 */

	public TransactionParameter addTransactionParameter(long userId,
			long transactionId, String key, String value,
			ServiceContext serviceContext) throws SystemException,
			PortalException {

		User user = UserLocalServiceUtil.getUser(userId);
		long groupId = serviceContext.getScopeGroupId();
		Date now = new Date();

		validate(null, transactionId, key, value, serviceContext);

		long transactionParameterId = CounterLocalServiceUtil.increment();
		TransactionParameter transactionParameter = transactionParameterPersistence
				.create(transactionParameterId);

		transactionParameter.setGroupId(groupId);
		transactionParameter.setUserId(userId);
		transactionParameter.setCompanyId(user.getCompanyId());
		transactionParameter.setCreateDate(now);
		transactionParameter.setModifiedDate(now);

		transactionParameter.setTransactionId(transactionId);
		transactionParameter.setKey(key);
		transactionParameter.setValue(value);

		transactionParameterPersistence.update(transactionParameter, false);

		return transactionParameter;
	}

	public List<TransactionParameter> getTransactionTransactionParameters(
			long transactionId, int start, int end,
			OrderByComparator orderByComparator) throws SystemException {

		return transactionPersistence.getTransactionParameters(transactionId,
				start, end, orderByComparator);
	}

	public List<String> getSellerTransactionParameterKeys(long sellerId)
			throws SystemException {

		return transactionParameterFinder
				.findParameterKeysForSellerId(sellerId);
	}

	public TransactionParameter deleteTransactionParameter(
			long transactionParameterId) throws PortalException,
			SystemException {

		return deleteTransactionParameter(transactionParameterPersistence
				.findByPrimaryKey(transactionParameterId));
	}

	public TransactionParameter deleteTransactionParameter(
			TransactionParameter transactionParameter) throws SystemException {
		return super.deleteTransactionParameter(transactionParameter);
	}

	private void validate(TransactionParameter transactionParameter,
			long transactionId, String key, String value,
			ServiceContext serviceContext) {

	}
}