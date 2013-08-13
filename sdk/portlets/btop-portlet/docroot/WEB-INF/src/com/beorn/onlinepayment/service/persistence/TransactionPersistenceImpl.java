/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.beorn.onlinepayment.service.persistence;

import com.beorn.onlinepayment.NoSuchTransactionException;
import com.beorn.onlinepayment.model.Transaction;
import com.beorn.onlinepayment.model.impl.TransactionImpl;
import com.beorn.onlinepayment.model.impl.TransactionModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQuery;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQueryFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.RowMapper;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the transaction service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Sebastien Meunier
 * @see TransactionPersistence
 * @see TransactionUtil
 * @generated
 */
public class TransactionPersistenceImpl extends BasePersistenceImpl<Transaction>
	implements TransactionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TransactionUtil} to access the transaction persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TransactionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(TransactionModelImpl.ENTITY_CACHE_ENABLED,
			TransactionModelImpl.FINDER_CACHE_ENABLED, TransactionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(TransactionModelImpl.ENTITY_CACHE_ENABLED,
			TransactionModelImpl.FINDER_CACHE_ENABLED, TransactionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			TransactionModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(TransactionModelImpl.ENTITY_CACHE_ENABLED,
			TransactionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(TransactionModelImpl.ENTITY_CACHE_ENABLED,
			TransactionModelImpl.FINDER_CACHE_ENABLED, TransactionImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			TransactionModelImpl.UUID_COLUMN_BITMASK |
			TransactionModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(TransactionModelImpl.ENTITY_CACHE_ENABLED,
			TransactionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_APPLICATIONID =
		new FinderPath(TransactionModelImpl.ENTITY_CACHE_ENABLED,
			TransactionModelImpl.FINDER_CACHE_ENABLED, TransactionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByApplicationId",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPLICATIONID =
		new FinderPath(TransactionModelImpl.ENTITY_CACHE_ENABLED,
			TransactionModelImpl.FINDER_CACHE_ENABLED, TransactionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByApplicationId",
			new String[] { String.class.getName() },
			TransactionModelImpl.APPLICATIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_APPLICATIONID = new FinderPath(TransactionModelImpl.ENTITY_CACHE_ENABLED,
			TransactionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByApplicationId",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SELLERID = new FinderPath(TransactionModelImpl.ENTITY_CACHE_ENABLED,
			TransactionModelImpl.FINDER_CACHE_ENABLED, TransactionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySellerId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SELLERID =
		new FinderPath(TransactionModelImpl.ENTITY_CACHE_ENABLED,
			TransactionModelImpl.FINDER_CACHE_ENABLED, TransactionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySellerId",
			new String[] { Long.class.getName() },
			TransactionModelImpl.SELLERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SELLERID = new FinderPath(TransactionModelImpl.ENTITY_CACHE_ENABLED,
			TransactionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySellerId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(TransactionModelImpl.ENTITY_CACHE_ENABLED,
			TransactionModelImpl.FINDER_CACHE_ENABLED, TransactionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(TransactionModelImpl.ENTITY_CACHE_ENABLED,
			TransactionModelImpl.FINDER_CACHE_ENABLED, TransactionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			TransactionModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(TransactionModelImpl.ENTITY_CACHE_ENABLED,
			TransactionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_APPLICATIONIDANDSELLERID =
		new FinderPath(TransactionModelImpl.ENTITY_CACHE_ENABLED,
			TransactionModelImpl.FINDER_CACHE_ENABLED, TransactionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByApplicationIdAndSellerId",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPLICATIONIDANDSELLERID =
		new FinderPath(TransactionModelImpl.ENTITY_CACHE_ENABLED,
			TransactionModelImpl.FINDER_CACHE_ENABLED, TransactionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByApplicationIdAndSellerId",
			new String[] { String.class.getName(), Long.class.getName() },
			TransactionModelImpl.APPLICATIONID_COLUMN_BITMASK |
			TransactionModelImpl.SELLERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_APPLICATIONIDANDSELLERID =
		new FinderPath(TransactionModelImpl.ENTITY_CACHE_ENABLED,
			TransactionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByApplicationIdAndSellerId",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_APPLICATIONIDANDUSERID =
		new FinderPath(TransactionModelImpl.ENTITY_CACHE_ENABLED,
			TransactionModelImpl.FINDER_CACHE_ENABLED, TransactionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByApplicationIdAndUserId",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPLICATIONIDANDUSERID =
		new FinderPath(TransactionModelImpl.ENTITY_CACHE_ENABLED,
			TransactionModelImpl.FINDER_CACHE_ENABLED, TransactionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByApplicationIdAndUserId",
			new String[] { String.class.getName(), Long.class.getName() },
			TransactionModelImpl.APPLICATIONID_COLUMN_BITMASK |
			TransactionModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_APPLICATIONIDANDUSERID = new FinderPath(TransactionModelImpl.ENTITY_CACHE_ENABLED,
			TransactionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByApplicationIdAndUserId",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TransactionModelImpl.ENTITY_CACHE_ENABLED,
			TransactionModelImpl.FINDER_CACHE_ENABLED, TransactionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TransactionModelImpl.ENTITY_CACHE_ENABLED,
			TransactionModelImpl.FINDER_CACHE_ENABLED, TransactionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TransactionModelImpl.ENTITY_CACHE_ENABLED,
			TransactionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the transaction in the entity cache if it is enabled.
	 *
	 * @param transaction the transaction
	 */
	public void cacheResult(Transaction transaction) {
		EntityCacheUtil.putResult(TransactionModelImpl.ENTITY_CACHE_ENABLED,
			TransactionImpl.class, transaction.getPrimaryKey(), transaction);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				transaction.getUuid(), Long.valueOf(transaction.getGroupId())
			}, transaction);

		transaction.resetOriginalValues();
	}

	/**
	 * Caches the transactions in the entity cache if it is enabled.
	 *
	 * @param transactions the transactions
	 */
	public void cacheResult(List<Transaction> transactions) {
		for (Transaction transaction : transactions) {
			if (EntityCacheUtil.getResult(
						TransactionModelImpl.ENTITY_CACHE_ENABLED,
						TransactionImpl.class, transaction.getPrimaryKey()) == null) {
				cacheResult(transaction);
			}
			else {
				transaction.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all transactions.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(TransactionImpl.class.getName());
		}

		EntityCacheUtil.clearCache(TransactionImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the transaction.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Transaction transaction) {
		EntityCacheUtil.removeResult(TransactionModelImpl.ENTITY_CACHE_ENABLED,
			TransactionImpl.class, transaction.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(transaction);
	}

	@Override
	public void clearCache(List<Transaction> transactions) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Transaction transaction : transactions) {
			EntityCacheUtil.removeResult(TransactionModelImpl.ENTITY_CACHE_ENABLED,
				TransactionImpl.class, transaction.getPrimaryKey());

			clearUniqueFindersCache(transaction);
		}
	}

	protected void clearUniqueFindersCache(Transaction transaction) {
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				transaction.getUuid(), Long.valueOf(transaction.getGroupId())
			});
	}

	/**
	 * Creates a new transaction with the primary key. Does not add the transaction to the database.
	 *
	 * @param transactionId the primary key for the new transaction
	 * @return the new transaction
	 */
	public Transaction create(long transactionId) {
		Transaction transaction = new TransactionImpl();

		transaction.setNew(true);
		transaction.setPrimaryKey(transactionId);

		String uuid = PortalUUIDUtil.generate();

		transaction.setUuid(uuid);

		return transaction;
	}

	/**
	 * Removes the transaction with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param transactionId the primary key of the transaction
	 * @return the transaction that was removed
	 * @throws com.beorn.onlinepayment.NoSuchTransactionException if a transaction with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Transaction remove(long transactionId)
		throws NoSuchTransactionException, SystemException {
		return remove(Long.valueOf(transactionId));
	}

	/**
	 * Removes the transaction with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the transaction
	 * @return the transaction that was removed
	 * @throws com.beorn.onlinepayment.NoSuchTransactionException if a transaction with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Transaction remove(Serializable primaryKey)
		throws NoSuchTransactionException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Transaction transaction = (Transaction)session.get(TransactionImpl.class,
					primaryKey);

			if (transaction == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTransactionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(transaction);
		}
		catch (NoSuchTransactionException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Transaction removeImpl(Transaction transaction)
		throws SystemException {
		transaction = toUnwrappedModel(transaction);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, transaction);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(transaction);

		return transaction;
	}

	@Override
	public Transaction updateImpl(
		com.beorn.onlinepayment.model.Transaction transaction, boolean merge)
		throws SystemException {
		transaction = toUnwrappedModel(transaction);

		boolean isNew = transaction.isNew();

		TransactionModelImpl transactionModelImpl = (TransactionModelImpl)transaction;

		if (Validator.isNull(transaction.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			transaction.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, transaction, merge);

			transaction.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !TransactionModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((transactionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						transactionModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { transactionModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((transactionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPLICATIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						transactionModelImpl.getOriginalApplicationId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_APPLICATIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPLICATIONID,
					args);

				args = new Object[] { transactionModelImpl.getApplicationId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_APPLICATIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPLICATIONID,
					args);
			}

			if ((transactionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SELLERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(transactionModelImpl.getOriginalSellerId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SELLERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SELLERID,
					args);

				args = new Object[] {
						Long.valueOf(transactionModelImpl.getSellerId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SELLERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SELLERID,
					args);
			}

			if ((transactionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(transactionModelImpl.getOriginalUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] {
						Long.valueOf(transactionModelImpl.getUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((transactionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPLICATIONIDANDSELLERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						transactionModelImpl.getOriginalApplicationId(),
						Long.valueOf(transactionModelImpl.getOriginalSellerId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_APPLICATIONIDANDSELLERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPLICATIONIDANDSELLERID,
					args);

				args = new Object[] {
						transactionModelImpl.getApplicationId(),
						Long.valueOf(transactionModelImpl.getSellerId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_APPLICATIONIDANDSELLERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPLICATIONIDANDSELLERID,
					args);
			}

			if ((transactionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPLICATIONIDANDUSERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						transactionModelImpl.getOriginalApplicationId(),
						Long.valueOf(transactionModelImpl.getOriginalUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_APPLICATIONIDANDUSERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPLICATIONIDANDUSERID,
					args);

				args = new Object[] {
						transactionModelImpl.getApplicationId(),
						Long.valueOf(transactionModelImpl.getUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_APPLICATIONIDANDUSERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPLICATIONIDANDUSERID,
					args);
			}
		}

		EntityCacheUtil.putResult(TransactionModelImpl.ENTITY_CACHE_ENABLED,
			TransactionImpl.class, transaction.getPrimaryKey(), transaction);

		if (isNew) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					transaction.getUuid(),
					Long.valueOf(transaction.getGroupId())
				}, transaction);
		}
		else {
			if ((transactionModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						transactionModelImpl.getOriginalUuid(),
						Long.valueOf(transactionModelImpl.getOriginalGroupId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);

				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
					new Object[] {
						transaction.getUuid(),
						Long.valueOf(transaction.getGroupId())
					}, transaction);
			}
		}

		return transaction;
	}

	protected Transaction toUnwrappedModel(Transaction transaction) {
		if (transaction instanceof TransactionImpl) {
			return transaction;
		}

		TransactionImpl transactionImpl = new TransactionImpl();

		transactionImpl.setNew(transaction.isNew());
		transactionImpl.setPrimaryKey(transaction.getPrimaryKey());

		transactionImpl.setUuid(transaction.getUuid());
		transactionImpl.setCompanyId(transaction.getCompanyId());
		transactionImpl.setGroupId(transaction.getGroupId());
		transactionImpl.setUserId(transaction.getUserId());
		transactionImpl.setTransactionId(transaction.getTransactionId());
		transactionImpl.setCreateDate(transaction.getCreateDate());
		transactionImpl.setModifiedDate(transaction.getModifiedDate());
		transactionImpl.setApplicationId(transaction.getApplicationId());
		transactionImpl.setSellerId(transaction.getSellerId());
		transactionImpl.setAmount(transaction.getAmount());
		transactionImpl.setCurrencyCode(transaction.getCurrencyCode());
		transactionImpl.setStatus(transaction.getStatus());
		transactionImpl.setPaymentApplicationId(transaction.getPaymentApplicationId());
		transactionImpl.setAmountPaid(transaction.getAmountPaid());
		transactionImpl.setAmountRefunded(transaction.getAmountRefunded());

		return transactionImpl;
	}

	/**
	 * Returns the transaction with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the transaction
	 * @return the transaction
	 * @throws com.liferay.portal.NoSuchModelException if a transaction with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Transaction findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the transaction with the primary key or throws a {@link com.beorn.onlinepayment.NoSuchTransactionException} if it could not be found.
	 *
	 * @param transactionId the primary key of the transaction
	 * @return the transaction
	 * @throws com.beorn.onlinepayment.NoSuchTransactionException if a transaction with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Transaction findByPrimaryKey(long transactionId)
		throws NoSuchTransactionException, SystemException {
		Transaction transaction = fetchByPrimaryKey(transactionId);

		if (transaction == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + transactionId);
			}

			throw new NoSuchTransactionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				transactionId);
		}

		return transaction;
	}

	/**
	 * Returns the transaction with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the transaction
	 * @return the transaction, or <code>null</code> if a transaction with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Transaction fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the transaction with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param transactionId the primary key of the transaction
	 * @return the transaction, or <code>null</code> if a transaction with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Transaction fetchByPrimaryKey(long transactionId)
		throws SystemException {
		Transaction transaction = (Transaction)EntityCacheUtil.getResult(TransactionModelImpl.ENTITY_CACHE_ENABLED,
				TransactionImpl.class, transactionId);

		if (transaction == _nullTransaction) {
			return null;
		}

		if (transaction == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				transaction = (Transaction)session.get(TransactionImpl.class,
						Long.valueOf(transactionId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (transaction != null) {
					cacheResult(transaction);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(TransactionModelImpl.ENTITY_CACHE_ENABLED,
						TransactionImpl.class, transactionId, _nullTransaction);
				}

				closeSession(session);
			}
		}

		return transaction;
	}

	/**
	 * Returns all the transactions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching transactions
	 * @throws SystemException if a system exception occurred
	 */
	public List<Transaction> findByUuid(String uuid) throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the transactions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of transactions
	 * @param end the upper bound of the range of transactions (not inclusive)
	 * @return the range of matching transactions
	 * @throws SystemException if a system exception occurred
	 */
	public List<Transaction> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the transactions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of transactions
	 * @param end the upper bound of the range of transactions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching transactions
	 * @throws SystemException if a system exception occurred
	 */
	public List<Transaction> findByUuid(String uuid, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<Transaction> list = (List<Transaction>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Transaction transaction : list) {
				if (!Validator.equals(uuid, transaction.getUuid())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TRANSACTION_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_UUID_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TransactionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				list = (List<Transaction>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first transaction in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching transaction
	 * @throws com.beorn.onlinepayment.NoSuchTransactionException if a matching transaction could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Transaction findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchTransactionException, SystemException {
		Transaction transaction = fetchByUuid_First(uuid, orderByComparator);

		if (transaction != null) {
			return transaction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTransactionException(msg.toString());
	}

	/**
	 * Returns the first transaction in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching transaction, or <code>null</code> if a matching transaction could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Transaction fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<Transaction> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last transaction in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching transaction
	 * @throws com.beorn.onlinepayment.NoSuchTransactionException if a matching transaction could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Transaction findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchTransactionException, SystemException {
		Transaction transaction = fetchByUuid_Last(uuid, orderByComparator);

		if (transaction != null) {
			return transaction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTransactionException(msg.toString());
	}

	/**
	 * Returns the last transaction in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching transaction, or <code>null</code> if a matching transaction could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Transaction fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		List<Transaction> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the transactions before and after the current transaction in the ordered set where uuid = &#63;.
	 *
	 * @param transactionId the primary key of the current transaction
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next transaction
	 * @throws com.beorn.onlinepayment.NoSuchTransactionException if a transaction with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Transaction[] findByUuid_PrevAndNext(long transactionId,
		String uuid, OrderByComparator orderByComparator)
		throws NoSuchTransactionException, SystemException {
		Transaction transaction = findByPrimaryKey(transactionId);

		Session session = null;

		try {
			session = openSession();

			Transaction[] array = new TransactionImpl[3];

			array[0] = getByUuid_PrevAndNext(session, transaction, uuid,
					orderByComparator, true);

			array[1] = transaction;

			array[2] = getByUuid_PrevAndNext(session, transaction, uuid,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Transaction getByUuid_PrevAndNext(Session session,
		Transaction transaction, String uuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TRANSACTION_WHERE);

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else {
			if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(TransactionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (uuid != null) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(transaction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Transaction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the transaction where uuid = &#63; and groupId = &#63; or throws a {@link com.beorn.onlinepayment.NoSuchTransactionException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching transaction
	 * @throws com.beorn.onlinepayment.NoSuchTransactionException if a matching transaction could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Transaction findByUUID_G(String uuid, long groupId)
		throws NoSuchTransactionException, SystemException {
		Transaction transaction = fetchByUUID_G(uuid, groupId);

		if (transaction == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchTransactionException(msg.toString());
		}

		return transaction;
	}

	/**
	 * Returns the transaction where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching transaction, or <code>null</code> if a matching transaction could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Transaction fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the transaction where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching transaction, or <code>null</code> if a matching transaction could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Transaction fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof Transaction) {
			Transaction transaction = (Transaction)result;

			if (!Validator.equals(uuid, transaction.getUuid()) ||
					(groupId != transaction.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_TRANSACTION_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_G_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_G_UUID_2);
				}
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			query.append(TransactionModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<Transaction> list = q.list();

				result = list;

				Transaction transaction = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					transaction = list.get(0);

					cacheResult(transaction);

					if ((transaction.getUuid() == null) ||
							!transaction.getUuid().equals(uuid) ||
							(transaction.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, transaction);
					}
				}

				return transaction;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs);
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (Transaction)result;
			}
		}
	}

	/**
	 * Returns all the transactions where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @return the matching transactions
	 * @throws SystemException if a system exception occurred
	 */
	public List<Transaction> findByApplicationId(String applicationId)
		throws SystemException {
		return findByApplicationId(applicationId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the transactions where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param start the lower bound of the range of transactions
	 * @param end the upper bound of the range of transactions (not inclusive)
	 * @return the range of matching transactions
	 * @throws SystemException if a system exception occurred
	 */
	public List<Transaction> findByApplicationId(String applicationId,
		int start, int end) throws SystemException {
		return findByApplicationId(applicationId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the transactions where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param start the lower bound of the range of transactions
	 * @param end the upper bound of the range of transactions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching transactions
	 * @throws SystemException if a system exception occurred
	 */
	public List<Transaction> findByApplicationId(String applicationId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPLICATIONID;
			finderArgs = new Object[] { applicationId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_APPLICATIONID;
			finderArgs = new Object[] {
					applicationId,
					
					start, end, orderByComparator
				};
		}

		List<Transaction> list = (List<Transaction>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Transaction transaction : list) {
				if (!Validator.equals(applicationId,
							transaction.getApplicationId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TRANSACTION_WHERE);

			if (applicationId == null) {
				query.append(_FINDER_COLUMN_APPLICATIONID_APPLICATIONID_1);
			}
			else {
				if (applicationId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_APPLICATIONID_APPLICATIONID_3);
				}
				else {
					query.append(_FINDER_COLUMN_APPLICATIONID_APPLICATIONID_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TransactionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (applicationId != null) {
					qPos.add(applicationId);
				}

				list = (List<Transaction>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first transaction in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching transaction
	 * @throws com.beorn.onlinepayment.NoSuchTransactionException if a matching transaction could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Transaction findByApplicationId_First(String applicationId,
		OrderByComparator orderByComparator)
		throws NoSuchTransactionException, SystemException {
		Transaction transaction = fetchByApplicationId_First(applicationId,
				orderByComparator);

		if (transaction != null) {
			return transaction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("applicationId=");
		msg.append(applicationId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTransactionException(msg.toString());
	}

	/**
	 * Returns the first transaction in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching transaction, or <code>null</code> if a matching transaction could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Transaction fetchByApplicationId_First(String applicationId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Transaction> list = findByApplicationId(applicationId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last transaction in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching transaction
	 * @throws com.beorn.onlinepayment.NoSuchTransactionException if a matching transaction could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Transaction findByApplicationId_Last(String applicationId,
		OrderByComparator orderByComparator)
		throws NoSuchTransactionException, SystemException {
		Transaction transaction = fetchByApplicationId_Last(applicationId,
				orderByComparator);

		if (transaction != null) {
			return transaction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("applicationId=");
		msg.append(applicationId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTransactionException(msg.toString());
	}

	/**
	 * Returns the last transaction in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching transaction, or <code>null</code> if a matching transaction could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Transaction fetchByApplicationId_Last(String applicationId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByApplicationId(applicationId);

		List<Transaction> list = findByApplicationId(applicationId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the transactions before and after the current transaction in the ordered set where applicationId = &#63;.
	 *
	 * @param transactionId the primary key of the current transaction
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next transaction
	 * @throws com.beorn.onlinepayment.NoSuchTransactionException if a transaction with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Transaction[] findByApplicationId_PrevAndNext(long transactionId,
		String applicationId, OrderByComparator orderByComparator)
		throws NoSuchTransactionException, SystemException {
		Transaction transaction = findByPrimaryKey(transactionId);

		Session session = null;

		try {
			session = openSession();

			Transaction[] array = new TransactionImpl[3];

			array[0] = getByApplicationId_PrevAndNext(session, transaction,
					applicationId, orderByComparator, true);

			array[1] = transaction;

			array[2] = getByApplicationId_PrevAndNext(session, transaction,
					applicationId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Transaction getByApplicationId_PrevAndNext(Session session,
		Transaction transaction, String applicationId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TRANSACTION_WHERE);

		if (applicationId == null) {
			query.append(_FINDER_COLUMN_APPLICATIONID_APPLICATIONID_1);
		}
		else {
			if (applicationId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_APPLICATIONID_APPLICATIONID_3);
			}
			else {
				query.append(_FINDER_COLUMN_APPLICATIONID_APPLICATIONID_2);
			}
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(TransactionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (applicationId != null) {
			qPos.add(applicationId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(transaction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Transaction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the transactions where sellerId = &#63;.
	 *
	 * @param sellerId the seller ID
	 * @return the matching transactions
	 * @throws SystemException if a system exception occurred
	 */
	public List<Transaction> findBySellerId(long sellerId)
		throws SystemException {
		return findBySellerId(sellerId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the transactions where sellerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param sellerId the seller ID
	 * @param start the lower bound of the range of transactions
	 * @param end the upper bound of the range of transactions (not inclusive)
	 * @return the range of matching transactions
	 * @throws SystemException if a system exception occurred
	 */
	public List<Transaction> findBySellerId(long sellerId, int start, int end)
		throws SystemException {
		return findBySellerId(sellerId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the transactions where sellerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param sellerId the seller ID
	 * @param start the lower bound of the range of transactions
	 * @param end the upper bound of the range of transactions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching transactions
	 * @throws SystemException if a system exception occurred
	 */
	public List<Transaction> findBySellerId(long sellerId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SELLERID;
			finderArgs = new Object[] { sellerId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SELLERID;
			finderArgs = new Object[] { sellerId, start, end, orderByComparator };
		}

		List<Transaction> list = (List<Transaction>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Transaction transaction : list) {
				if ((sellerId != transaction.getSellerId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TRANSACTION_WHERE);

			query.append(_FINDER_COLUMN_SELLERID_SELLERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TransactionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(sellerId);

				list = (List<Transaction>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first transaction in the ordered set where sellerId = &#63;.
	 *
	 * @param sellerId the seller ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching transaction
	 * @throws com.beorn.onlinepayment.NoSuchTransactionException if a matching transaction could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Transaction findBySellerId_First(long sellerId,
		OrderByComparator orderByComparator)
		throws NoSuchTransactionException, SystemException {
		Transaction transaction = fetchBySellerId_First(sellerId,
				orderByComparator);

		if (transaction != null) {
			return transaction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("sellerId=");
		msg.append(sellerId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTransactionException(msg.toString());
	}

	/**
	 * Returns the first transaction in the ordered set where sellerId = &#63;.
	 *
	 * @param sellerId the seller ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching transaction, or <code>null</code> if a matching transaction could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Transaction fetchBySellerId_First(long sellerId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Transaction> list = findBySellerId(sellerId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last transaction in the ordered set where sellerId = &#63;.
	 *
	 * @param sellerId the seller ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching transaction
	 * @throws com.beorn.onlinepayment.NoSuchTransactionException if a matching transaction could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Transaction findBySellerId_Last(long sellerId,
		OrderByComparator orderByComparator)
		throws NoSuchTransactionException, SystemException {
		Transaction transaction = fetchBySellerId_Last(sellerId,
				orderByComparator);

		if (transaction != null) {
			return transaction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("sellerId=");
		msg.append(sellerId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTransactionException(msg.toString());
	}

	/**
	 * Returns the last transaction in the ordered set where sellerId = &#63;.
	 *
	 * @param sellerId the seller ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching transaction, or <code>null</code> if a matching transaction could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Transaction fetchBySellerId_Last(long sellerId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countBySellerId(sellerId);

		List<Transaction> list = findBySellerId(sellerId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the transactions before and after the current transaction in the ordered set where sellerId = &#63;.
	 *
	 * @param transactionId the primary key of the current transaction
	 * @param sellerId the seller ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next transaction
	 * @throws com.beorn.onlinepayment.NoSuchTransactionException if a transaction with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Transaction[] findBySellerId_PrevAndNext(long transactionId,
		long sellerId, OrderByComparator orderByComparator)
		throws NoSuchTransactionException, SystemException {
		Transaction transaction = findByPrimaryKey(transactionId);

		Session session = null;

		try {
			session = openSession();

			Transaction[] array = new TransactionImpl[3];

			array[0] = getBySellerId_PrevAndNext(session, transaction,
					sellerId, orderByComparator, true);

			array[1] = transaction;

			array[2] = getBySellerId_PrevAndNext(session, transaction,
					sellerId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Transaction getBySellerId_PrevAndNext(Session session,
		Transaction transaction, long sellerId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TRANSACTION_WHERE);

		query.append(_FINDER_COLUMN_SELLERID_SELLERID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(TransactionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(sellerId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(transaction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Transaction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the transactions where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching transactions
	 * @throws SystemException if a system exception occurred
	 */
	public List<Transaction> findByUserId(long userId)
		throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the transactions where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of transactions
	 * @param end the upper bound of the range of transactions (not inclusive)
	 * @return the range of matching transactions
	 * @throws SystemException if a system exception occurred
	 */
	public List<Transaction> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the transactions where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of transactions
	 * @param end the upper bound of the range of transactions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching transactions
	 * @throws SystemException if a system exception occurred
	 */
	public List<Transaction> findByUserId(long userId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId, start, end, orderByComparator };
		}

		List<Transaction> list = (List<Transaction>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Transaction transaction : list) {
				if ((userId != transaction.getUserId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TRANSACTION_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TransactionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				list = (List<Transaction>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first transaction in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching transaction
	 * @throws com.beorn.onlinepayment.NoSuchTransactionException if a matching transaction could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Transaction findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchTransactionException, SystemException {
		Transaction transaction = fetchByUserId_First(userId, orderByComparator);

		if (transaction != null) {
			return transaction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTransactionException(msg.toString());
	}

	/**
	 * Returns the first transaction in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching transaction, or <code>null</code> if a matching transaction could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Transaction fetchByUserId_First(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Transaction> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last transaction in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching transaction
	 * @throws com.beorn.onlinepayment.NoSuchTransactionException if a matching transaction could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Transaction findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchTransactionException, SystemException {
		Transaction transaction = fetchByUserId_Last(userId, orderByComparator);

		if (transaction != null) {
			return transaction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTransactionException(msg.toString());
	}

	/**
	 * Returns the last transaction in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching transaction, or <code>null</code> if a matching transaction could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Transaction fetchByUserId_Last(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUserId(userId);

		List<Transaction> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the transactions before and after the current transaction in the ordered set where userId = &#63;.
	 *
	 * @param transactionId the primary key of the current transaction
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next transaction
	 * @throws com.beorn.onlinepayment.NoSuchTransactionException if a transaction with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Transaction[] findByUserId_PrevAndNext(long transactionId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchTransactionException, SystemException {
		Transaction transaction = findByPrimaryKey(transactionId);

		Session session = null;

		try {
			session = openSession();

			Transaction[] array = new TransactionImpl[3];

			array[0] = getByUserId_PrevAndNext(session, transaction, userId,
					orderByComparator, true);

			array[1] = transaction;

			array[2] = getByUserId_PrevAndNext(session, transaction, userId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Transaction getByUserId_PrevAndNext(Session session,
		Transaction transaction, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TRANSACTION_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(TransactionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(transaction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Transaction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the transactions where applicationId = &#63; and sellerId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param sellerId the seller ID
	 * @return the matching transactions
	 * @throws SystemException if a system exception occurred
	 */
	public List<Transaction> findByApplicationIdAndSellerId(
		String applicationId, long sellerId) throws SystemException {
		return findByApplicationIdAndSellerId(applicationId, sellerId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the transactions where applicationId = &#63; and sellerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param sellerId the seller ID
	 * @param start the lower bound of the range of transactions
	 * @param end the upper bound of the range of transactions (not inclusive)
	 * @return the range of matching transactions
	 * @throws SystemException if a system exception occurred
	 */
	public List<Transaction> findByApplicationIdAndSellerId(
		String applicationId, long sellerId, int start, int end)
		throws SystemException {
		return findByApplicationIdAndSellerId(applicationId, sellerId, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the transactions where applicationId = &#63; and sellerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param sellerId the seller ID
	 * @param start the lower bound of the range of transactions
	 * @param end the upper bound of the range of transactions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching transactions
	 * @throws SystemException if a system exception occurred
	 */
	public List<Transaction> findByApplicationIdAndSellerId(
		String applicationId, long sellerId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPLICATIONIDANDSELLERID;
			finderArgs = new Object[] { applicationId, sellerId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_APPLICATIONIDANDSELLERID;
			finderArgs = new Object[] {
					applicationId, sellerId,
					
					start, end, orderByComparator
				};
		}

		List<Transaction> list = (List<Transaction>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Transaction transaction : list) {
				if (!Validator.equals(applicationId,
							transaction.getApplicationId()) ||
						(sellerId != transaction.getSellerId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_TRANSACTION_WHERE);

			if (applicationId == null) {
				query.append(_FINDER_COLUMN_APPLICATIONIDANDSELLERID_APPLICATIONID_1);
			}
			else {
				if (applicationId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_APPLICATIONIDANDSELLERID_APPLICATIONID_3);
				}
				else {
					query.append(_FINDER_COLUMN_APPLICATIONIDANDSELLERID_APPLICATIONID_2);
				}
			}

			query.append(_FINDER_COLUMN_APPLICATIONIDANDSELLERID_SELLERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TransactionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (applicationId != null) {
					qPos.add(applicationId);
				}

				qPos.add(sellerId);

				list = (List<Transaction>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first transaction in the ordered set where applicationId = &#63; and sellerId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param sellerId the seller ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching transaction
	 * @throws com.beorn.onlinepayment.NoSuchTransactionException if a matching transaction could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Transaction findByApplicationIdAndSellerId_First(
		String applicationId, long sellerId, OrderByComparator orderByComparator)
		throws NoSuchTransactionException, SystemException {
		Transaction transaction = fetchByApplicationIdAndSellerId_First(applicationId,
				sellerId, orderByComparator);

		if (transaction != null) {
			return transaction;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("applicationId=");
		msg.append(applicationId);

		msg.append(", sellerId=");
		msg.append(sellerId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTransactionException(msg.toString());
	}

	/**
	 * Returns the first transaction in the ordered set where applicationId = &#63; and sellerId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param sellerId the seller ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching transaction, or <code>null</code> if a matching transaction could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Transaction fetchByApplicationIdAndSellerId_First(
		String applicationId, long sellerId, OrderByComparator orderByComparator)
		throws SystemException {
		List<Transaction> list = findByApplicationIdAndSellerId(applicationId,
				sellerId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last transaction in the ordered set where applicationId = &#63; and sellerId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param sellerId the seller ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching transaction
	 * @throws com.beorn.onlinepayment.NoSuchTransactionException if a matching transaction could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Transaction findByApplicationIdAndSellerId_Last(
		String applicationId, long sellerId, OrderByComparator orderByComparator)
		throws NoSuchTransactionException, SystemException {
		Transaction transaction = fetchByApplicationIdAndSellerId_Last(applicationId,
				sellerId, orderByComparator);

		if (transaction != null) {
			return transaction;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("applicationId=");
		msg.append(applicationId);

		msg.append(", sellerId=");
		msg.append(sellerId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTransactionException(msg.toString());
	}

	/**
	 * Returns the last transaction in the ordered set where applicationId = &#63; and sellerId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param sellerId the seller ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching transaction, or <code>null</code> if a matching transaction could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Transaction fetchByApplicationIdAndSellerId_Last(
		String applicationId, long sellerId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByApplicationIdAndSellerId(applicationId, sellerId);

		List<Transaction> list = findByApplicationIdAndSellerId(applicationId,
				sellerId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the transactions before and after the current transaction in the ordered set where applicationId = &#63; and sellerId = &#63;.
	 *
	 * @param transactionId the primary key of the current transaction
	 * @param applicationId the application ID
	 * @param sellerId the seller ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next transaction
	 * @throws com.beorn.onlinepayment.NoSuchTransactionException if a transaction with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Transaction[] findByApplicationIdAndSellerId_PrevAndNext(
		long transactionId, String applicationId, long sellerId,
		OrderByComparator orderByComparator)
		throws NoSuchTransactionException, SystemException {
		Transaction transaction = findByPrimaryKey(transactionId);

		Session session = null;

		try {
			session = openSession();

			Transaction[] array = new TransactionImpl[3];

			array[0] = getByApplicationIdAndSellerId_PrevAndNext(session,
					transaction, applicationId, sellerId, orderByComparator,
					true);

			array[1] = transaction;

			array[2] = getByApplicationIdAndSellerId_PrevAndNext(session,
					transaction, applicationId, sellerId, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Transaction getByApplicationIdAndSellerId_PrevAndNext(
		Session session, Transaction transaction, String applicationId,
		long sellerId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TRANSACTION_WHERE);

		if (applicationId == null) {
			query.append(_FINDER_COLUMN_APPLICATIONIDANDSELLERID_APPLICATIONID_1);
		}
		else {
			if (applicationId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_APPLICATIONIDANDSELLERID_APPLICATIONID_3);
			}
			else {
				query.append(_FINDER_COLUMN_APPLICATIONIDANDSELLERID_APPLICATIONID_2);
			}
		}

		query.append(_FINDER_COLUMN_APPLICATIONIDANDSELLERID_SELLERID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(TransactionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (applicationId != null) {
			qPos.add(applicationId);
		}

		qPos.add(sellerId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(transaction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Transaction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the transactions where applicationId = &#63; and userId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param userId the user ID
	 * @return the matching transactions
	 * @throws SystemException if a system exception occurred
	 */
	public List<Transaction> findByApplicationIdAndUserId(
		String applicationId, long userId) throws SystemException {
		return findByApplicationIdAndUserId(applicationId, userId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the transactions where applicationId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of transactions
	 * @param end the upper bound of the range of transactions (not inclusive)
	 * @return the range of matching transactions
	 * @throws SystemException if a system exception occurred
	 */
	public List<Transaction> findByApplicationIdAndUserId(
		String applicationId, long userId, int start, int end)
		throws SystemException {
		return findByApplicationIdAndUserId(applicationId, userId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the transactions where applicationId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of transactions
	 * @param end the upper bound of the range of transactions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching transactions
	 * @throws SystemException if a system exception occurred
	 */
	public List<Transaction> findByApplicationIdAndUserId(
		String applicationId, long userId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPLICATIONIDANDUSERID;
			finderArgs = new Object[] { applicationId, userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_APPLICATIONIDANDUSERID;
			finderArgs = new Object[] {
					applicationId, userId,
					
					start, end, orderByComparator
				};
		}

		List<Transaction> list = (List<Transaction>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Transaction transaction : list) {
				if (!Validator.equals(applicationId,
							transaction.getApplicationId()) ||
						(userId != transaction.getUserId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_TRANSACTION_WHERE);

			if (applicationId == null) {
				query.append(_FINDER_COLUMN_APPLICATIONIDANDUSERID_APPLICATIONID_1);
			}
			else {
				if (applicationId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_APPLICATIONIDANDUSERID_APPLICATIONID_3);
				}
				else {
					query.append(_FINDER_COLUMN_APPLICATIONIDANDUSERID_APPLICATIONID_2);
				}
			}

			query.append(_FINDER_COLUMN_APPLICATIONIDANDUSERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TransactionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (applicationId != null) {
					qPos.add(applicationId);
				}

				qPos.add(userId);

				list = (List<Transaction>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first transaction in the ordered set where applicationId = &#63; and userId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching transaction
	 * @throws com.beorn.onlinepayment.NoSuchTransactionException if a matching transaction could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Transaction findByApplicationIdAndUserId_First(
		String applicationId, long userId, OrderByComparator orderByComparator)
		throws NoSuchTransactionException, SystemException {
		Transaction transaction = fetchByApplicationIdAndUserId_First(applicationId,
				userId, orderByComparator);

		if (transaction != null) {
			return transaction;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("applicationId=");
		msg.append(applicationId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTransactionException(msg.toString());
	}

	/**
	 * Returns the first transaction in the ordered set where applicationId = &#63; and userId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching transaction, or <code>null</code> if a matching transaction could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Transaction fetchByApplicationIdAndUserId_First(
		String applicationId, long userId, OrderByComparator orderByComparator)
		throws SystemException {
		List<Transaction> list = findByApplicationIdAndUserId(applicationId,
				userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last transaction in the ordered set where applicationId = &#63; and userId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching transaction
	 * @throws com.beorn.onlinepayment.NoSuchTransactionException if a matching transaction could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Transaction findByApplicationIdAndUserId_Last(String applicationId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchTransactionException, SystemException {
		Transaction transaction = fetchByApplicationIdAndUserId_Last(applicationId,
				userId, orderByComparator);

		if (transaction != null) {
			return transaction;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("applicationId=");
		msg.append(applicationId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTransactionException(msg.toString());
	}

	/**
	 * Returns the last transaction in the ordered set where applicationId = &#63; and userId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching transaction, or <code>null</code> if a matching transaction could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Transaction fetchByApplicationIdAndUserId_Last(
		String applicationId, long userId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByApplicationIdAndUserId(applicationId, userId);

		List<Transaction> list = findByApplicationIdAndUserId(applicationId,
				userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the transactions before and after the current transaction in the ordered set where applicationId = &#63; and userId = &#63;.
	 *
	 * @param transactionId the primary key of the current transaction
	 * @param applicationId the application ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next transaction
	 * @throws com.beorn.onlinepayment.NoSuchTransactionException if a transaction with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Transaction[] findByApplicationIdAndUserId_PrevAndNext(
		long transactionId, String applicationId, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchTransactionException, SystemException {
		Transaction transaction = findByPrimaryKey(transactionId);

		Session session = null;

		try {
			session = openSession();

			Transaction[] array = new TransactionImpl[3];

			array[0] = getByApplicationIdAndUserId_PrevAndNext(session,
					transaction, applicationId, userId, orderByComparator, true);

			array[1] = transaction;

			array[2] = getByApplicationIdAndUserId_PrevAndNext(session,
					transaction, applicationId, userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Transaction getByApplicationIdAndUserId_PrevAndNext(
		Session session, Transaction transaction, String applicationId,
		long userId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TRANSACTION_WHERE);

		if (applicationId == null) {
			query.append(_FINDER_COLUMN_APPLICATIONIDANDUSERID_APPLICATIONID_1);
		}
		else {
			if (applicationId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_APPLICATIONIDANDUSERID_APPLICATIONID_3);
			}
			else {
				query.append(_FINDER_COLUMN_APPLICATIONIDANDUSERID_APPLICATIONID_2);
			}
		}

		query.append(_FINDER_COLUMN_APPLICATIONIDANDUSERID_USERID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(TransactionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (applicationId != null) {
			qPos.add(applicationId);
		}

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(transaction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Transaction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the transactions.
	 *
	 * @return the transactions
	 * @throws SystemException if a system exception occurred
	 */
	public List<Transaction> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the transactions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of transactions
	 * @param end the upper bound of the range of transactions (not inclusive)
	 * @return the range of transactions
	 * @throws SystemException if a system exception occurred
	 */
	public List<Transaction> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the transactions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of transactions
	 * @param end the upper bound of the range of transactions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of transactions
	 * @throws SystemException if a system exception occurred
	 */
	public List<Transaction> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<Transaction> list = (List<Transaction>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TRANSACTION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TRANSACTION.concat(TransactionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<Transaction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Transaction>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the transactions where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (Transaction transaction : findByUuid(uuid)) {
			remove(transaction);
		}
	}

	/**
	 * Removes the transaction where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the transaction that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public Transaction removeByUUID_G(String uuid, long groupId)
		throws NoSuchTransactionException, SystemException {
		Transaction transaction = findByUUID_G(uuid, groupId);

		return remove(transaction);
	}

	/**
	 * Removes all the transactions where applicationId = &#63; from the database.
	 *
	 * @param applicationId the application ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByApplicationId(String applicationId)
		throws SystemException {
		for (Transaction transaction : findByApplicationId(applicationId)) {
			remove(transaction);
		}
	}

	/**
	 * Removes all the transactions where sellerId = &#63; from the database.
	 *
	 * @param sellerId the seller ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBySellerId(long sellerId) throws SystemException {
		for (Transaction transaction : findBySellerId(sellerId)) {
			remove(transaction);
		}
	}

	/**
	 * Removes all the transactions where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUserId(long userId) throws SystemException {
		for (Transaction transaction : findByUserId(userId)) {
			remove(transaction);
		}
	}

	/**
	 * Removes all the transactions where applicationId = &#63; and sellerId = &#63; from the database.
	 *
	 * @param applicationId the application ID
	 * @param sellerId the seller ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByApplicationIdAndSellerId(String applicationId,
		long sellerId) throws SystemException {
		for (Transaction transaction : findByApplicationIdAndSellerId(
				applicationId, sellerId)) {
			remove(transaction);
		}
	}

	/**
	 * Removes all the transactions where applicationId = &#63; and userId = &#63; from the database.
	 *
	 * @param applicationId the application ID
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByApplicationIdAndUserId(String applicationId, long userId)
		throws SystemException {
		for (Transaction transaction : findByApplicationIdAndUserId(
				applicationId, userId)) {
			remove(transaction);
		}
	}

	/**
	 * Removes all the transactions from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (Transaction transaction : findAll()) {
			remove(transaction);
		}
	}

	/**
	 * Returns the number of transactions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching transactions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TRANSACTION_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_UUID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of transactions where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching transactions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUUID_G(String uuid, long groupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID_G,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TRANSACTION_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_G_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_G_UUID_2);
				}
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of transactions where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @return the number of matching transactions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByApplicationId(String applicationId)
		throws SystemException {
		Object[] finderArgs = new Object[] { applicationId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_APPLICATIONID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TRANSACTION_WHERE);

			if (applicationId == null) {
				query.append(_FINDER_COLUMN_APPLICATIONID_APPLICATIONID_1);
			}
			else {
				if (applicationId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_APPLICATIONID_APPLICATIONID_3);
				}
				else {
					query.append(_FINDER_COLUMN_APPLICATIONID_APPLICATIONID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (applicationId != null) {
					qPos.add(applicationId);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_APPLICATIONID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of transactions where sellerId = &#63;.
	 *
	 * @param sellerId the seller ID
	 * @return the number of matching transactions
	 * @throws SystemException if a system exception occurred
	 */
	public int countBySellerId(long sellerId) throws SystemException {
		Object[] finderArgs = new Object[] { sellerId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_SELLERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TRANSACTION_WHERE);

			query.append(_FINDER_COLUMN_SELLERID_SELLERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(sellerId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SELLERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of transactions where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching transactions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TRANSACTION_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of transactions where applicationId = &#63; and sellerId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param sellerId the seller ID
	 * @return the number of matching transactions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByApplicationIdAndSellerId(String applicationId,
		long sellerId) throws SystemException {
		Object[] finderArgs = new Object[] { applicationId, sellerId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_APPLICATIONIDANDSELLERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TRANSACTION_WHERE);

			if (applicationId == null) {
				query.append(_FINDER_COLUMN_APPLICATIONIDANDSELLERID_APPLICATIONID_1);
			}
			else {
				if (applicationId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_APPLICATIONIDANDSELLERID_APPLICATIONID_3);
				}
				else {
					query.append(_FINDER_COLUMN_APPLICATIONIDANDSELLERID_APPLICATIONID_2);
				}
			}

			query.append(_FINDER_COLUMN_APPLICATIONIDANDSELLERID_SELLERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (applicationId != null) {
					qPos.add(applicationId);
				}

				qPos.add(sellerId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_APPLICATIONIDANDSELLERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of transactions where applicationId = &#63; and userId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param userId the user ID
	 * @return the number of matching transactions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByApplicationIdAndUserId(String applicationId, long userId)
		throws SystemException {
		Object[] finderArgs = new Object[] { applicationId, userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_APPLICATIONIDANDUSERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TRANSACTION_WHERE);

			if (applicationId == null) {
				query.append(_FINDER_COLUMN_APPLICATIONIDANDUSERID_APPLICATIONID_1);
			}
			else {
				if (applicationId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_APPLICATIONIDANDUSERID_APPLICATIONID_3);
				}
				else {
					query.append(_FINDER_COLUMN_APPLICATIONIDANDUSERID_APPLICATIONID_2);
				}
			}

			query.append(_FINDER_COLUMN_APPLICATIONIDANDUSERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (applicationId != null) {
					qPos.add(applicationId);
				}

				qPos.add(userId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_APPLICATIONIDANDUSERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of transactions.
	 *
	 * @return the number of transactions
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TRANSACTION);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns all the transaction logs associated with the transaction.
	 *
	 * @param pk the primary key of the transaction
	 * @return the transaction logs associated with the transaction
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.beorn.onlinepayment.model.TransactionLog> getTransactionLogs(
		long pk) throws SystemException {
		return getTransactionLogs(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the transaction logs associated with the transaction.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the transaction
	 * @param start the lower bound of the range of transactions
	 * @param end the upper bound of the range of transactions (not inclusive)
	 * @return the range of transaction logs associated with the transaction
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.beorn.onlinepayment.model.TransactionLog> getTransactionLogs(
		long pk, int start, int end) throws SystemException {
		return getTransactionLogs(pk, start, end, null);
	}

	public static final FinderPath FINDER_PATH_GET_TRANSACTIONLOGS = new FinderPath(com.beorn.onlinepayment.model.impl.TransactionLogModelImpl.ENTITY_CACHE_ENABLED,
			com.beorn.onlinepayment.model.impl.TransactionLogModelImpl.FINDER_CACHE_ENABLED,
			com.beorn.onlinepayment.model.impl.TransactionLogImpl.class,
			com.beorn.onlinepayment.service.persistence.TransactionLogPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"getTransactionLogs",
			new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});

	static {
		FINDER_PATH_GET_TRANSACTIONLOGS.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns an ordered range of all the transaction logs associated with the transaction.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the transaction
	 * @param start the lower bound of the range of transactions
	 * @param end the upper bound of the range of transactions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of transaction logs associated with the transaction
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.beorn.onlinepayment.model.TransactionLog> getTransactionLogs(
		long pk, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, start, end, orderByComparator };

		List<com.beorn.onlinepayment.model.TransactionLog> list = (List<com.beorn.onlinepayment.model.TransactionLog>)FinderCacheUtil.getResult(FINDER_PATH_GET_TRANSACTIONLOGS,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				String sql = null;

				if (orderByComparator != null) {
					sql = _SQL_GETTRANSACTIONLOGS.concat(ORDER_BY_CLAUSE)
												 .concat(orderByComparator.getOrderBy());
				}
				else {
					sql = _SQL_GETTRANSACTIONLOGS.concat(com.beorn.onlinepayment.model.impl.TransactionLogModelImpl.ORDER_BY_SQL);
				}

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("Payment_TransactionLog",
					com.beorn.onlinepayment.model.impl.TransactionLogImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				list = (List<com.beorn.onlinepayment.model.TransactionLog>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_GET_TRANSACTIONLOGS,
						finderArgs);
				}
				else {
					transactionLogPersistence.cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_GET_TRANSACTIONLOGS,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	public static final FinderPath FINDER_PATH_GET_TRANSACTIONLOGS_SIZE = new FinderPath(com.beorn.onlinepayment.model.impl.TransactionLogModelImpl.ENTITY_CACHE_ENABLED,
			com.beorn.onlinepayment.model.impl.TransactionLogModelImpl.FINDER_CACHE_ENABLED,
			com.beorn.onlinepayment.model.impl.TransactionLogImpl.class,
			com.beorn.onlinepayment.service.persistence.TransactionLogPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"getTransactionLogsSize", new String[] { Long.class.getName() });

	static {
		FINDER_PATH_GET_TRANSACTIONLOGS_SIZE.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns the number of transaction logs associated with the transaction.
	 *
	 * @param pk the primary key of the transaction
	 * @return the number of transaction logs associated with the transaction
	 * @throws SystemException if a system exception occurred
	 */
	public int getTransactionLogsSize(long pk) throws SystemException {
		Object[] finderArgs = new Object[] { pk };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_GET_TRANSACTIONLOGS_SIZE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETTRANSACTIONLOGSSIZE);

				q.addScalar(COUNT_COLUMN_NAME,
					com.liferay.portal.kernel.dao.orm.Type.LONG);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_GET_TRANSACTIONLOGS_SIZE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public static final FinderPath FINDER_PATH_CONTAINS_TRANSACTIONLOG = new FinderPath(com.beorn.onlinepayment.model.impl.TransactionLogModelImpl.ENTITY_CACHE_ENABLED,
			com.beorn.onlinepayment.model.impl.TransactionLogModelImpl.FINDER_CACHE_ENABLED,
			com.beorn.onlinepayment.model.impl.TransactionLogImpl.class,
			com.beorn.onlinepayment.service.persistence.TransactionLogPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"containsTransactionLog",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns <code>true</code> if the transaction log is associated with the transaction.
	 *
	 * @param pk the primary key of the transaction
	 * @param transactionLogPK the primary key of the transaction log
	 * @return <code>true</code> if the transaction log is associated with the transaction; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsTransactionLog(long pk, long transactionLogPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, transactionLogPK };

		Boolean value = (Boolean)FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_TRANSACTIONLOG,
				finderArgs, this);

		if (value == null) {
			try {
				value = Boolean.valueOf(containsTransactionLog.contains(pk,
							transactionLogPK));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (value == null) {
					value = Boolean.FALSE;
				}

				FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_TRANSACTIONLOG,
					finderArgs, value);
			}
		}

		return value.booleanValue();
	}

	/**
	 * Returns <code>true</code> if the transaction has any transaction logs associated with it.
	 *
	 * @param pk the primary key of the transaction to check for associations with transaction logs
	 * @return <code>true</code> if the transaction has any transaction logs associated with it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsTransactionLogs(long pk) throws SystemException {
		if (getTransactionLogsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Returns all the transaction parameters associated with the transaction.
	 *
	 * @param pk the primary key of the transaction
	 * @return the transaction parameters associated with the transaction
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.beorn.onlinepayment.model.TransactionParameter> getTransactionParameters(
		long pk) throws SystemException {
		return getTransactionParameters(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the transaction parameters associated with the transaction.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the transaction
	 * @param start the lower bound of the range of transactions
	 * @param end the upper bound of the range of transactions (not inclusive)
	 * @return the range of transaction parameters associated with the transaction
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.beorn.onlinepayment.model.TransactionParameter> getTransactionParameters(
		long pk, int start, int end) throws SystemException {
		return getTransactionParameters(pk, start, end, null);
	}

	public static final FinderPath FINDER_PATH_GET_TRANSACTIONPARAMETERS = new FinderPath(com.beorn.onlinepayment.model.impl.TransactionParameterModelImpl.ENTITY_CACHE_ENABLED,
			com.beorn.onlinepayment.model.impl.TransactionParameterModelImpl.FINDER_CACHE_ENABLED,
			com.beorn.onlinepayment.model.impl.TransactionParameterImpl.class,
			com.beorn.onlinepayment.service.persistence.TransactionParameterPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"getTransactionParameters",
			new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});

	static {
		FINDER_PATH_GET_TRANSACTIONPARAMETERS.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns an ordered range of all the transaction parameters associated with the transaction.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the transaction
	 * @param start the lower bound of the range of transactions
	 * @param end the upper bound of the range of transactions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of transaction parameters associated with the transaction
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.beorn.onlinepayment.model.TransactionParameter> getTransactionParameters(
		long pk, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, start, end, orderByComparator };

		List<com.beorn.onlinepayment.model.TransactionParameter> list = (List<com.beorn.onlinepayment.model.TransactionParameter>)FinderCacheUtil.getResult(FINDER_PATH_GET_TRANSACTIONPARAMETERS,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				String sql = null;

				if (orderByComparator != null) {
					sql = _SQL_GETTRANSACTIONPARAMETERS.concat(ORDER_BY_CLAUSE)
													   .concat(orderByComparator.getOrderBy());
				}
				else {
					sql = _SQL_GETTRANSACTIONPARAMETERS.concat(com.beorn.onlinepayment.model.impl.TransactionParameterModelImpl.ORDER_BY_SQL);
				}

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("Payment_TransactionParameter",
					com.beorn.onlinepayment.model.impl.TransactionParameterImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				list = (List<com.beorn.onlinepayment.model.TransactionParameter>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_GET_TRANSACTIONPARAMETERS,
						finderArgs);
				}
				else {
					transactionParameterPersistence.cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_GET_TRANSACTIONPARAMETERS,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	public static final FinderPath FINDER_PATH_GET_TRANSACTIONPARAMETERS_SIZE = new FinderPath(com.beorn.onlinepayment.model.impl.TransactionParameterModelImpl.ENTITY_CACHE_ENABLED,
			com.beorn.onlinepayment.model.impl.TransactionParameterModelImpl.FINDER_CACHE_ENABLED,
			com.beorn.onlinepayment.model.impl.TransactionParameterImpl.class,
			com.beorn.onlinepayment.service.persistence.TransactionParameterPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"getTransactionParametersSize",
			new String[] { Long.class.getName() });

	static {
		FINDER_PATH_GET_TRANSACTIONPARAMETERS_SIZE.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns the number of transaction parameters associated with the transaction.
	 *
	 * @param pk the primary key of the transaction
	 * @return the number of transaction parameters associated with the transaction
	 * @throws SystemException if a system exception occurred
	 */
	public int getTransactionParametersSize(long pk) throws SystemException {
		Object[] finderArgs = new Object[] { pk };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_GET_TRANSACTIONPARAMETERS_SIZE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETTRANSACTIONPARAMETERSSIZE);

				q.addScalar(COUNT_COLUMN_NAME,
					com.liferay.portal.kernel.dao.orm.Type.LONG);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_GET_TRANSACTIONPARAMETERS_SIZE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public static final FinderPath FINDER_PATH_CONTAINS_TRANSACTIONPARAMETER = new FinderPath(com.beorn.onlinepayment.model.impl.TransactionParameterModelImpl.ENTITY_CACHE_ENABLED,
			com.beorn.onlinepayment.model.impl.TransactionParameterModelImpl.FINDER_CACHE_ENABLED,
			com.beorn.onlinepayment.model.impl.TransactionParameterImpl.class,
			com.beorn.onlinepayment.service.persistence.TransactionParameterPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"containsTransactionParameter",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns <code>true</code> if the transaction parameter is associated with the transaction.
	 *
	 * @param pk the primary key of the transaction
	 * @param transactionParameterPK the primary key of the transaction parameter
	 * @return <code>true</code> if the transaction parameter is associated with the transaction; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsTransactionParameter(long pk,
		long transactionParameterPK) throws SystemException {
		Object[] finderArgs = new Object[] { pk, transactionParameterPK };

		Boolean value = (Boolean)FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_TRANSACTIONPARAMETER,
				finderArgs, this);

		if (value == null) {
			try {
				value = Boolean.valueOf(containsTransactionParameter.contains(
							pk, transactionParameterPK));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (value == null) {
					value = Boolean.FALSE;
				}

				FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_TRANSACTIONPARAMETER,
					finderArgs, value);
			}
		}

		return value.booleanValue();
	}

	/**
	 * Returns <code>true</code> if the transaction has any transaction parameters associated with it.
	 *
	 * @param pk the primary key of the transaction to check for associations with transaction parameters
	 * @return <code>true</code> if the transaction has any transaction parameters associated with it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsTransactionParameters(long pk)
		throws SystemException {
		if (getTransactionParametersSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Initializes the transaction persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.beorn.onlinepayment.model.Transaction")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Transaction>> listenersList = new ArrayList<ModelListener<Transaction>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Transaction>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		containsTransactionLog = new ContainsTransactionLog();

		containsTransactionParameter = new ContainsTransactionParameter();
	}

	public void destroy() {
		EntityCacheUtil.removeCache(TransactionImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = OrderTransactionPersistence.class)
	protected OrderTransactionPersistence orderTransactionPersistence;
	@BeanReference(type = PaymentMethodPersistence.class)
	protected PaymentMethodPersistence paymentMethodPersistence;
	@BeanReference(type = PaymentPluginPersistence.class)
	protected PaymentPluginPersistence paymentPluginPersistence;
	@BeanReference(type = PaymentPluginConfigPersistence.class)
	protected PaymentPluginConfigPersistence paymentPluginConfigPersistence;
	@BeanReference(type = RulePersistence.class)
	protected RulePersistence rulePersistence;
	@BeanReference(type = SellerPersistence.class)
	protected SellerPersistence sellerPersistence;
	@BeanReference(type = TransactionPersistence.class)
	protected TransactionPersistence transactionPersistence;
	@BeanReference(type = TransactionLogPersistence.class)
	protected TransactionLogPersistence transactionLogPersistence;
	@BeanReference(type = TransactionParameterPersistence.class)
	protected TransactionParameterPersistence transactionParameterPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	protected ContainsTransactionLog containsTransactionLog;
	protected ContainsTransactionParameter containsTransactionParameter;

	protected class ContainsTransactionLog {
		protected ContainsTransactionLog() {
			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSTRANSACTIONLOG,
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
					RowMapper.COUNT);
		}

		protected boolean contains(long transactionId, long transactionLogId) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						new Long(transactionId), new Long(transactionLogId)
					});

			if (results.size() > 0) {
				Integer count = results.get(0);

				if (count.intValue() > 0) {
					return true;
				}
			}

			return false;
		}

		private MappingSqlQuery<Integer> _mappingSqlQuery;
	}

	protected class ContainsTransactionParameter {
		protected ContainsTransactionParameter() {
			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSTRANSACTIONPARAMETER,
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
					RowMapper.COUNT);
		}

		protected boolean contains(long transactionId,
			long transactionParameterId) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						new Long(transactionId),
						new Long(transactionParameterId)
					});

			if (results.size() > 0) {
				Integer count = results.get(0);

				if (count.intValue() > 0) {
					return true;
				}
			}

			return false;
		}

		private MappingSqlQuery<Integer> _mappingSqlQuery;
	}

	private static final String _SQL_SELECT_TRANSACTION = "SELECT transaction FROM Transaction transaction";
	private static final String _SQL_SELECT_TRANSACTION_WHERE = "SELECT transaction FROM Transaction transaction WHERE ";
	private static final String _SQL_COUNT_TRANSACTION = "SELECT COUNT(transaction) FROM Transaction transaction";
	private static final String _SQL_COUNT_TRANSACTION_WHERE = "SELECT COUNT(transaction) FROM Transaction transaction WHERE ";
	private static final String _SQL_GETTRANSACTIONLOGS = "SELECT {Payment_TransactionLog.*} FROM Payment_TransactionLog INNER JOIN Payment_Transaction ON (Payment_Transaction.transactionId = Payment_TransactionLog.transactionId) WHERE (Payment_Transaction.transactionId = ?)";
	private static final String _SQL_GETTRANSACTIONLOGSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM Payment_TransactionLog WHERE transactionId = ?";
	private static final String _SQL_CONTAINSTRANSACTIONLOG = "SELECT COUNT(*) AS COUNT_VALUE FROM Payment_TransactionLog WHERE transactionId = ? AND transactionLogId = ?";
	private static final String _SQL_GETTRANSACTIONPARAMETERS = "SELECT {Payment_TransactionParameter.*} FROM Payment_TransactionParameter INNER JOIN Payment_Transaction ON (Payment_Transaction.transactionId = Payment_TransactionParameter.transactionId) WHERE (Payment_Transaction.transactionId = ?)";
	private static final String _SQL_GETTRANSACTIONPARAMETERSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM Payment_TransactionParameter WHERE transactionId = ?";
	private static final String _SQL_CONTAINSTRANSACTIONPARAMETER = "SELECT COUNT(*) AS COUNT_VALUE FROM Payment_TransactionParameter WHERE transactionId = ? AND transactionParameterId = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "transaction.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "transaction.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(transaction.uuid IS NULL OR transaction.uuid = ?)";
	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "transaction.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "transaction.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(transaction.uuid IS NULL OR transaction.uuid = ?) AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "transaction.groupId = ?";
	private static final String _FINDER_COLUMN_APPLICATIONID_APPLICATIONID_1 = "transaction.applicationId IS NULL";
	private static final String _FINDER_COLUMN_APPLICATIONID_APPLICATIONID_2 = "transaction.applicationId = ?";
	private static final String _FINDER_COLUMN_APPLICATIONID_APPLICATIONID_3 = "(transaction.applicationId IS NULL OR transaction.applicationId = ?)";
	private static final String _FINDER_COLUMN_SELLERID_SELLERID_2 = "transaction.sellerId = ?";
	private static final String _FINDER_COLUMN_USERID_USERID_2 = "transaction.userId = ?";
	private static final String _FINDER_COLUMN_APPLICATIONIDANDSELLERID_APPLICATIONID_1 =
		"transaction.applicationId IS NULL AND ";
	private static final String _FINDER_COLUMN_APPLICATIONIDANDSELLERID_APPLICATIONID_2 =
		"transaction.applicationId = ? AND ";
	private static final String _FINDER_COLUMN_APPLICATIONIDANDSELLERID_APPLICATIONID_3 =
		"(transaction.applicationId IS NULL OR transaction.applicationId = ?) AND ";
	private static final String _FINDER_COLUMN_APPLICATIONIDANDSELLERID_SELLERID_2 =
		"transaction.sellerId = ?";
	private static final String _FINDER_COLUMN_APPLICATIONIDANDUSERID_APPLICATIONID_1 =
		"transaction.applicationId IS NULL AND ";
	private static final String _FINDER_COLUMN_APPLICATIONIDANDUSERID_APPLICATIONID_2 =
		"transaction.applicationId = ? AND ";
	private static final String _FINDER_COLUMN_APPLICATIONIDANDUSERID_APPLICATIONID_3 =
		"(transaction.applicationId IS NULL OR transaction.applicationId = ?) AND ";
	private static final String _FINDER_COLUMN_APPLICATIONIDANDUSERID_USERID_2 = "transaction.userId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "transaction.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Transaction exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Transaction exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(TransactionPersistenceImpl.class);
	private static Transaction _nullTransaction = new TransactionImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Transaction> toCacheModel() {
				return _nullTransactionCacheModel;
			}
		};

	private static CacheModel<Transaction> _nullTransactionCacheModel = new CacheModel<Transaction>() {
			public Transaction toEntityModel() {
				return _nullTransaction;
			}
		};
}