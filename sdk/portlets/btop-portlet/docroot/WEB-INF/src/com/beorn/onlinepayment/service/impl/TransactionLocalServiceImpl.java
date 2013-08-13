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

import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.beorn.onlinepayment.DuplicateTransactionLogException;
import com.beorn.onlinepayment.TransactionAmountException;
import com.beorn.onlinepayment.TransactionAmountPaidException;
import com.beorn.onlinepayment.TransactionAmountRefundedException;
import com.beorn.onlinepayment.TransactionApplicationIdException;
import com.beorn.onlinepayment.TransactionCurrencyCodeException;
import com.beorn.onlinepayment.TransactionPaymentApplicationIdException;
import com.beorn.onlinepayment.messaging.PaymentSystemAppSender;
import com.beorn.onlinepayment.model.Transaction;
import com.beorn.onlinepayment.model.TransactionLog;
import com.beorn.onlinepayment.model.TransactionParameter;
import com.beorn.onlinepayment.service.base.TransactionLocalServiceBaseImpl;
import com.beorn.onlinepayment.util.PaymentSystemUtil;
import com.beorn.onlinepayment.util.TransactionLogTypes;
import com.beorn.paymentapi.util.TransactionStatus;
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
 * The implementation of the transaction local service.
 * 
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.beorn.onlinepayment.service.TransactionLocalService} interface.
 * 
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 * 
 * @author Sébastien Meunier
 * @see com.beorn.onlinepayment.service.base.TransactionLocalServiceBaseImpl
 * @see com.beorn.onlinepayment.service.TransactionLocalServiceUtil
 */
public class TransactionLocalServiceImpl extends
		TransactionLocalServiceBaseImpl {

	public Transaction addTransaction(long userId, String applicationId,
			long sellerId, double amount, String currencyCode,
			Map<String, String> parameters, ServiceContext serviceContext)
			throws PortalException, SystemException {

		User user = UserLocalServiceUtil.getUser(userId);
		long groupId = serviceContext.getScopeGroupId();
		Date now = new Date();

		String paymentApplicationId = null;
		double amountPaid = 0;
		double amountRefunded = 0;

		validate(null, applicationId, sellerId, amount, currencyCode,
				paymentApplicationId, amountPaid, amountRefunded,
				serviceContext);

		long transactionId = CounterLocalServiceUtil.increment();
		Transaction transaction = transactionPersistence.create(transactionId);

		transaction.setGroupId(groupId);
		transaction.setUserId(userId);
		transaction.setCompanyId(user.getCompanyId());
		transaction.setCreateDate(now);
		transaction.setModifiedDate(now);

		transaction.setApplicationId(applicationId);
		transaction.setSellerId(sellerId);
		transaction.setAmount(amount);
		transaction.setCurrencyCode(currencyCode);
		transaction.setStatus(TransactionStatus.NEW);

		transaction.setPaymentApplicationId(paymentApplicationId);
		transaction.setAmountPaid(amountPaid);
		transaction.setAmountRefunded(amountRefunded);

		transactionPersistence.update(transaction, false);

		if (parameters != null) {
			for (Entry<String, String> parameter : parameters.entrySet()) {
				transactionParameterLocalService.addTransactionParameter(
						userId, transactionId, parameter.getKey(),
						parameter.getValue(), serviceContext);
			}
		}

		// Resources

		if (serviceContext.isAddGroupPermissions()
				|| serviceContext.isAddGuestPermissions()) {

			addTransactionResources(transaction,
					serviceContext.isAddGroupPermissions(),
					serviceContext.isAddGuestPermissions());

		} else {
			addTransactionResources(transaction,
					serviceContext.getGroupPermissions(),
					serviceContext.getGuestPermissions());
		}

		return transaction;
	}

	public List<Transaction> getTransactions(int start, int end,
			OrderByComparator orderByComparator) throws SystemException {
		return transactionPersistence.findAll(start, end, orderByComparator);
	}

	public List<Transaction> getApplicationTransactions(String applicationId,
			int start, int end, OrderByComparator orderByComparator)
			throws SystemException {

		return transactionPersistence.findByApplicationId(applicationId, start,
				end, orderByComparator);
	}

	public int getApplicationTransactionsCount(String applicationId)
			throws SystemException {

		return transactionPersistence.countByApplicationId(applicationId);
	}

	public List<Transaction> getSellerTransactions(long sellerId, int start,
			int end, OrderByComparator orderByComparator)
			throws SystemException {

		return transactionPersistence.findBySellerId(sellerId, start, end,
				orderByComparator);
	}

	public int getSellerTransactionsCount(long sellerId) throws SystemException {
		return transactionPersistence.countBySellerId(sellerId);
	}

	public List<Transaction> getUserTransactions(long userId, int start,
			int end, OrderByComparator orderByComparator)
			throws SystemException {

		return transactionPersistence.findByUserId(userId, start, end,
				orderByComparator);
	}

	public int getUserTransactionsCount(long userId) throws SystemException {
		return transactionPersistence.countByUserId(userId);
	}

	public List<Transaction> getApplicationTransactionsBySellerId(
			String applicationId, long sellerId, int start, int end,
			OrderByComparator orderByComparator) throws SystemException {

		return transactionPersistence.findByApplicationIdAndSellerId(
				applicationId, sellerId, start, end, orderByComparator);
	}

	public int getApplicationTransactionsBySellerIdCount(String applicationId,
			long sellerId) throws SystemException {

		return transactionPersistence.countByApplicationIdAndSellerId(
				applicationId, sellerId);
	}

	public List<Transaction> getApplicationTransactionsByUserId(
			String applicationId, long userId, int start, int end,
			OrderByComparator orderByComparator) throws SystemException {

		return transactionPersistence.findByApplicationIdAndSellerId(
				applicationId, userId, start, end, orderByComparator);
	}

	public int getApplicationTransactionsByUserIdCount(String applicationId,
			long userId) throws SystemException {

		return transactionPersistence.countByApplicationIdAndSellerId(
				applicationId, userId);
	}

	public List<Transaction> search(long companyId, long groupId, Long userId,
			String keywords, Long sellerId, Long methodId,
			String applicationId, String paymentApplicationId, Long status,
			Double amountMin, Double amountMax, String currencyCode,
			Date dateMin, Date dateMax, String dateType, boolean isAndOperator,
			int start, int end, OrderByComparator obc) throws SystemException {

		return transactionFinder.search(companyId, groupId, userId, keywords,
				sellerId, methodId, applicationId, paymentApplicationId,
				status, amountMin, amountMax, currencyCode, dateMin, dateMax,
				dateType, isAndOperator, start, end, obc, false);
	}

	public int searchCount(long companyId, long groupId, Long userId,
			String keywords, Long sellerId, Long methodId,
			String applicationId, String paymentApplicationId, Long status,
			Double amountMin, Double amountMax, String currencyCode,
			Date dateMin, Date dateMax, String dateType, boolean isAndOperator)
			throws SystemException {

		return transactionFinder.searchCount(companyId, groupId, userId,
				keywords, sellerId, methodId, applicationId,
				paymentApplicationId, status, amountMin, amountMax,
				currencyCode, dateMin, dateMax, dateType, isAndOperator, false);
	}

	public Map<String, String> getTransactionParametersMap(long transactionId)
			throws SystemException {
		List<TransactionParameter> transactionParameters = transactionPersistence
				.getTransactionParameters(transactionId);
		Map<String, String> parameters = new HashMap<String, String>(
				transactionParameters.size());
		for (TransactionParameter transactionParameter : transactionParameters)
			parameters.put(transactionParameter.getKey(),
					transactionParameter.getValue());
		return parameters;
	}

	public Transaction addPayment(long transactionId, String remoteId,
			String paymentApplicationId, double amountPaid,
			ServiceContext serviceContext) throws PortalException,
			SystemException {

		// Use paymentId to avoid handling the same message multiple times.
		// It should not throw if the message has already been handled.

		Transaction transaction = transactionPersistence
				.findByPrimaryKey(transactionId);

		try {
			transactionLogLocalService.addTransactionLog(
					transaction.getUserId(), transactionId,
					paymentApplicationId, remoteId, amountPaid,
					TransactionLogTypes.PAYMENT, serviceContext);

			return updateTransaction(transactionId,
					transaction.getApplicationId(), transaction.getSellerId(),
					transaction.getAmount(), transaction.getCurrencyCode(),
					paymentApplicationId, transaction.getAmountPaid()
							+ amountPaid, transaction.getAmountRefunded(),
					serviceContext);

		} catch (DuplicateTransactionLogException e) {
			return transaction;
		}
	}

	public Transaction addRefund(long transactionId, String refundId,
			String paymentApplicationId, double amountRefunded,
			ServiceContext serviceContext) throws PortalException,
			SystemException {

		// TODO add a remoteId to avoid handling the same message multiple times
		// It should not throw if the message has already been handled

		Transaction transaction = transactionPersistence
				.findByPrimaryKey(transactionId);

		return updateTransaction(transactionId, transaction.getApplicationId(),
				transaction.getSellerId(), transaction.getAmount(),
				transaction.getCurrencyCode(), paymentApplicationId,
				transaction.getAmountPaid(), transaction.getAmountRefunded()
						+ amountRefunded, serviceContext);
	}

	public Transaction updateTransaction(long transactionId,
			String applicationId, long sellerId, double amount,
			String currencyCode, String paymentApplicationId,
			double amountPaid, double amountRefunded,
			ServiceContext serviceContext) throws SystemException,
			PortalException {

		Date now = new Date();

		Transaction transaction = transactionPersistence
				.findByPrimaryKey(transactionId);

		validate(transaction, applicationId, sellerId, amount, currencyCode,
				paymentApplicationId, amountPaid, amountRefunded,
				serviceContext);

		transaction.setModifiedDate(now);

		transaction.setApplicationId(applicationId);
		transaction.setSellerId(sellerId);
		transaction.setAmount(amount);
		transaction.setCurrencyCode(currencyCode);

		transaction.setPaymentApplicationId(paymentApplicationId);
		transaction.setAmountPaid(amountPaid);
		transaction.setAmountRefunded(amountRefunded);

		if (transaction.getStatus() == TransactionStatus.NEW
				&& transaction.getAmountPaid() >= amount) {

			transaction.setStatus(TransactionStatus.PAID);
			_log.debug("transaction " + transactionId + " is completely paid");
		}

		transactionPersistence.update(transaction, false);

		// Resources

		if (serviceContext.isAddGroupPermissions()
				|| serviceContext.isAddGuestPermissions()) {

			addTransactionResources(transaction,
					serviceContext.isAddGroupPermissions(),
					serviceContext.isAddGuestPermissions());

		} else {
			addTransactionResources(transaction,
					serviceContext.getGroupPermissions(),
					serviceContext.getGuestPermissions());
		}

		PaymentSystemUtil.getMessagingContext().getPaymentSystemAppSender()
				.updateTransaction(transactionId);

		return transaction;
	}

	public Transaction deleteTransaction(long transactionId)
			throws PortalException, SystemException {

		return deleteTransaction(transactionPersistence
				.findByPrimaryKey(transactionId));
	}

	public Transaction deleteTransaction(Transaction transaction)
			throws SystemException, PortalException {

		// Transaction Logs
		for (TransactionLog transactionLog : transactionLogLocalService
				.getTransactionTransactionLogs(transaction.getTransactionId(),
						QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			transactionLogLocalService.deleteTransactionLog(transactionLog);
		}

		// Transaction Parameters
		for (TransactionParameter transactionParameter : transactionParameterLocalService
				.getTransactionTransactionParameters(
						transaction.getTransactionId(), QueryUtil.ALL_POS,
						QueryUtil.ALL_POS, null)) {

			transactionParameterLocalService
					.deleteTransactionParameter(transactionParameter);
		}

		// Resources
		resourceLocalService.deleteResource(transaction.getCompanyId(),
				Transaction.class.getName(),
				ResourceConstants.SCOPE_INDIVIDUAL,
				transaction.getTransactionId());

		// Transaction
		return transactionPersistence.remove(transaction);
	}

	protected void addTransactionResources(Transaction transaction,
			boolean addCommunityPermissions, boolean addGuestPermissions)
			throws PortalException, SystemException {

		resourceLocalService.addResources(transaction.getCompanyId(),
				transaction.getGroupId(), transaction.getUserId(),
				Transaction.class.getName(), transaction.getTransactionId(),
				false, addCommunityPermissions, addGuestPermissions);
	}

	protected void addTransactionResources(Transaction transaction,
			String[] communityPermissions, String[] guestPermissions)
			throws PortalException, SystemException {

		resourceLocalService.addModelResources(transaction.getCompanyId(),
				transaction.getGroupId(), transaction.getUserId(),
				Transaction.class.getName(), transaction.getTransactionId(),
				communityPermissions, guestPermissions);
	}

	protected void addTransactionResources(long transactionId,
			boolean addCommunityPermissions, boolean addGuestPermissions)
			throws PortalException, SystemException {

		Transaction transaction = transactionPersistence
				.findByPrimaryKey(transactionId);
		addTransactionResources(transaction, addCommunityPermissions,
				addGuestPermissions);
	}

	protected void addTransactionResources(long transactionId,
			String[] communityPermissions, String[] guestPermissions)
			throws PortalException, SystemException {

		addTransactionResources(transactionId, communityPermissions,
				guestPermissions);
	}

	private void validate(Transaction transaction, String applicationId,
			long sellerId, double amount, String currencyCode,
			String paymentApplicationId, double amountPaid,
			double amountRefunded, ServiceContext serviceContext)
			throws PortalException, SystemException {

		if (transaction != null) {
			// Modification check

			if (Validator.isNotNull(transaction.getPaymentApplicationId())
					&& !transaction.getPaymentApplicationId().equals(
							paymentApplicationId)) {

				throw new TransactionPaymentApplicationIdException(
						"Payment plugin cannot change once set");
			}

			if (amountPaid < transaction.getAmountPaid()) {
				throw new TransactionAmountPaidException(
						"Amount paid cannot decrease");
			}

			if (amountRefunded < transaction.getAmountRefunded()) {
				throw new TransactionAmountRefundedException(
						"Amount refunded cannot decrease");
			}

			if (!currencyCode.equals(transaction.getCurrencyCode())) {
				throw new TransactionCurrencyCodeException(
						"Currency code cannot change");
			}
		}

		if (Validator.isNull(applicationId))
			throw new TransactionApplicationIdException();

		sellerPersistence.findByPrimaryKey(sellerId);

		if (amount < 0)
			throw new TransactionAmountException(
					"Transaction amount cannot be negative");

		if (amountPaid < 0)
			throw new TransactionAmountPaidException(
					"Amount paid cannot be negative");

		if (amountRefunded < 0)
			throw new TransactionAmountRefundedException(
					"Amount refunded cannot be negative");

		if (amountRefunded > amountPaid)
			throw new TransactionAmountRefundedException(
					"Cannot refund more than what has been paid");

		try {
			Currency.getInstance(currencyCode);

		} catch (Exception e) {
			throw new TransactionCurrencyCodeException("Invalid currency code",
					e);
		}
	}

	private static Log _log = LogFactoryUtil
			.getLog(TransactionLocalServiceImpl.class);
}