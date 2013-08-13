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

import com.beorn.onlinepayment.DuplicateTransactionLogException;
import com.beorn.onlinepayment.TransactionLogTypeException;
import com.beorn.onlinepayment.model.TransactionLog;
import com.beorn.onlinepayment.service.base.TransactionLogLocalServiceBaseImpl;
import com.beorn.onlinepayment.util.TransactionLogTypes;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * The implementation of the transaction log local service.
 * 
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.beorn.onlinepayment.service.TransactionLogLocalService} interface.
 * 
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 * 
 * @author Sébastien Meunier
 * @see com.beorn.onlinepayment.service.base.TransactionLogLocalServiceBaseImpl
 * @see com.beorn.onlinepayment.service.TransactionLogLocalServiceUtil
 */
public class TransactionLogLocalServiceImpl extends
		TransactionLogLocalServiceBaseImpl {

	public TransactionLog addTransactionLog(long userId, long transactionId,
			String paymentApplicationId, String remoteId, double amount,
			long type, ServiceContext serviceContext) throws PortalException,
			SystemException {

		User user = UserLocalServiceUtil.getUser(userId);
		long groupId = serviceContext.getScopeGroupId();
		Date now = new Date();

		validate(null, transactionId, paymentApplicationId, remoteId, amount,
				type, serviceContext);

		long transactionLogId = CounterLocalServiceUtil.increment();
		TransactionLog transactionLog = transactionLogPersistence
				.create(transactionLogId);

		transactionLog.setGroupId(groupId);
		transactionLog.setUserId(userId);
		transactionLog.setCompanyId(user.getCompanyId());
		transactionLog.setCreateDate(now);
		transactionLog.setModifiedDate(now);

		transactionLog.setTransactionId(transactionId);
		transactionLog.setPaymentApplicationId(paymentApplicationId);
		transactionLog.setRemoteId(remoteId);
		transactionLog.setAmount(amount);
		transactionLog.setType(type);

		transactionLogPersistence.update(transactionLog, false);

		return transactionLog;
	}

	public List<TransactionLog> getTransactionTransactionLogs(
			long transactionId, int start, int end,
			OrderByComparator orderByComparator) throws PortalException,
			SystemException {

		return transactionPersistence.getTransactionLogs(transactionId, start,
				end, orderByComparator);
	}

	public int getTransactionTransactionLogsCount(long transactionId)
			throws PortalException, SystemException {

		return transactionPersistence.getTransactionLogsSize(transactionId);
	}

	public TransactionLog updateTransactionLog(long transactionLogId,
			long transactionId, String paymentApplicationId, String remoteId,
			double amount, long type, ServiceContext serviceContext)
			throws SystemException, PortalException {

		Date now = new Date();

		TransactionLog transactionLog = transactionLogPersistence
				.findByPrimaryKey(transactionLogId);

		validate(transactionLog, transactionId, paymentApplicationId, remoteId,
				amount, type, serviceContext);

		transactionLog.setModifiedDate(now);

		transactionLog.setTransactionId(transactionId);
		transactionLog.setPaymentApplicationId(paymentApplicationId);
		transactionLog.setRemoteId(remoteId);
		transactionLog.setAmount(amount);
		transactionLog.setType(type);

		transactionLogPersistence.update(transactionLog, false);

		return transactionLog;
	}

	public TransactionLog deleteTransactionLog(long transactionLogId)
			throws PortalException, SystemException {

		return deleteTransactionLog(transactionLogPersistence
				.findByPrimaryKey(transactionLogId));
	}

	public TransactionLog deleteTransactionLog(TransactionLog transactionLog)
			throws SystemException, PortalException {

		return transactionLogPersistence.remove(transactionLog);
	}

	private void validate(TransactionLog transactionLog, long transactionId,
			String paymentApplicationId, String remoteId, double amount,
			long type, ServiceContext serviceContext) throws SystemException,
			PortalException {

		transactionPersistence.findByPrimaryKey(transactionId);

		if (!ArrayUtil.contains(TransactionLogTypes.VALID_TYPES, type))
			throw new TransactionLogTypeException("type " + type
					+ " is invalid");

		// This must be checked last because it will be ignored by the caller in
		// some cases, as long as there is no other errors

		TransactionLog sameNameTransactionLog = transactionLogPersistence
				.fetchByPaymentApplicationIdAndRemoteId(paymentApplicationId,
						remoteId);

		if (transactionLog == null && sameNameTransactionLog != null
				|| transactionLog != null && sameNameTransactionLog != null
				&& !transactionLog.equals(sameNameTransactionLog)) {

			throw new DuplicateTransactionLogException(
					"There is already a transactionLog for application \""
							+ paymentApplicationId + " and remote id "
							+ remoteId + "\"");
		}
	}
}