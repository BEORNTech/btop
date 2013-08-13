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

import com.beorn.onlinepayment.NoSuchTransactionLogException;
import com.beorn.onlinepayment.model.TransactionLog;
import com.beorn.onlinepayment.model.impl.TransactionLogImpl;
import com.beorn.onlinepayment.model.impl.TransactionLogModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
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
 * The persistence implementation for the transaction log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Sebastien Meunier
 * @see TransactionLogPersistence
 * @see TransactionLogUtil
 * @generated
 */
public class TransactionLogPersistenceImpl extends BasePersistenceImpl<TransactionLog>
	implements TransactionLogPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TransactionLogUtil} to access the transaction log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TransactionLogImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(TransactionLogModelImpl.ENTITY_CACHE_ENABLED,
			TransactionLogModelImpl.FINDER_CACHE_ENABLED,
			TransactionLogImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(TransactionLogModelImpl.ENTITY_CACHE_ENABLED,
			TransactionLogModelImpl.FINDER_CACHE_ENABLED,
			TransactionLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			TransactionLogModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(TransactionLogModelImpl.ENTITY_CACHE_ENABLED,
			TransactionLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(TransactionLogModelImpl.ENTITY_CACHE_ENABLED,
			TransactionLogModelImpl.FINDER_CACHE_ENABLED,
			TransactionLogImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			TransactionLogModelImpl.UUID_COLUMN_BITMASK |
			TransactionLogModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(TransactionLogModelImpl.ENTITY_CACHE_ENABLED,
			TransactionLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PAYMENTAPPLICATIONID =
		new FinderPath(TransactionLogModelImpl.ENTITY_CACHE_ENABLED,
			TransactionLogModelImpl.FINDER_CACHE_ENABLED,
			TransactionLogImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByPaymentApplicationId",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PAYMENTAPPLICATIONID =
		new FinderPath(TransactionLogModelImpl.ENTITY_CACHE_ENABLED,
			TransactionLogModelImpl.FINDER_CACHE_ENABLED,
			TransactionLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByPaymentApplicationId",
			new String[] { String.class.getName() },
			TransactionLogModelImpl.PAYMENTAPPLICATIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PAYMENTAPPLICATIONID = new FinderPath(TransactionLogModelImpl.ENTITY_CACHE_ENABLED,
			TransactionLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByPaymentApplicationId",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_PAYMENTAPPLICATIONIDANDREMOTEID =
		new FinderPath(TransactionLogModelImpl.ENTITY_CACHE_ENABLED,
			TransactionLogModelImpl.FINDER_CACHE_ENABLED,
			TransactionLogImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByPaymentApplicationIdAndRemoteId",
			new String[] { String.class.getName(), String.class.getName() },
			TransactionLogModelImpl.PAYMENTAPPLICATIONID_COLUMN_BITMASK |
			TransactionLogModelImpl.REMOTEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PAYMENTAPPLICATIONIDANDREMOTEID =
		new FinderPath(TransactionLogModelImpl.ENTITY_CACHE_ENABLED,
			TransactionLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByPaymentApplicationIdAndRemoteId",
			new String[] { String.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TransactionLogModelImpl.ENTITY_CACHE_ENABLED,
			TransactionLogModelImpl.FINDER_CACHE_ENABLED,
			TransactionLogImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TransactionLogModelImpl.ENTITY_CACHE_ENABLED,
			TransactionLogModelImpl.FINDER_CACHE_ENABLED,
			TransactionLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TransactionLogModelImpl.ENTITY_CACHE_ENABLED,
			TransactionLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the transaction log in the entity cache if it is enabled.
	 *
	 * @param transactionLog the transaction log
	 */
	public void cacheResult(TransactionLog transactionLog) {
		EntityCacheUtil.putResult(TransactionLogModelImpl.ENTITY_CACHE_ENABLED,
			TransactionLogImpl.class, transactionLog.getPrimaryKey(),
			transactionLog);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				transactionLog.getUuid(),
				Long.valueOf(transactionLog.getGroupId())
			}, transactionLog);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PAYMENTAPPLICATIONIDANDREMOTEID,
			new Object[] {
				transactionLog.getPaymentApplicationId(),
				
			transactionLog.getRemoteId()
			}, transactionLog);

		transactionLog.resetOriginalValues();
	}

	/**
	 * Caches the transaction logs in the entity cache if it is enabled.
	 *
	 * @param transactionLogs the transaction logs
	 */
	public void cacheResult(List<TransactionLog> transactionLogs) {
		for (TransactionLog transactionLog : transactionLogs) {
			if (EntityCacheUtil.getResult(
						TransactionLogModelImpl.ENTITY_CACHE_ENABLED,
						TransactionLogImpl.class, transactionLog.getPrimaryKey()) == null) {
				cacheResult(transactionLog);
			}
			else {
				transactionLog.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all transaction logs.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(TransactionLogImpl.class.getName());
		}

		EntityCacheUtil.clearCache(TransactionLogImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the transaction log.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TransactionLog transactionLog) {
		EntityCacheUtil.removeResult(TransactionLogModelImpl.ENTITY_CACHE_ENABLED,
			TransactionLogImpl.class, transactionLog.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(transactionLog);
	}

	@Override
	public void clearCache(List<TransactionLog> transactionLogs) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TransactionLog transactionLog : transactionLogs) {
			EntityCacheUtil.removeResult(TransactionLogModelImpl.ENTITY_CACHE_ENABLED,
				TransactionLogImpl.class, transactionLog.getPrimaryKey());

			clearUniqueFindersCache(transactionLog);
		}
	}

	protected void clearUniqueFindersCache(TransactionLog transactionLog) {
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				transactionLog.getUuid(),
				Long.valueOf(transactionLog.getGroupId())
			});

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PAYMENTAPPLICATIONIDANDREMOTEID,
			new Object[] {
				transactionLog.getPaymentApplicationId(),
				
			transactionLog.getRemoteId()
			});
	}

	/**
	 * Creates a new transaction log with the primary key. Does not add the transaction log to the database.
	 *
	 * @param transactionLogId the primary key for the new transaction log
	 * @return the new transaction log
	 */
	public TransactionLog create(long transactionLogId) {
		TransactionLog transactionLog = new TransactionLogImpl();

		transactionLog.setNew(true);
		transactionLog.setPrimaryKey(transactionLogId);

		String uuid = PortalUUIDUtil.generate();

		transactionLog.setUuid(uuid);

		return transactionLog;
	}

	/**
	 * Removes the transaction log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param transactionLogId the primary key of the transaction log
	 * @return the transaction log that was removed
	 * @throws com.beorn.onlinepayment.NoSuchTransactionLogException if a transaction log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TransactionLog remove(long transactionLogId)
		throws NoSuchTransactionLogException, SystemException {
		return remove(Long.valueOf(transactionLogId));
	}

	/**
	 * Removes the transaction log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the transaction log
	 * @return the transaction log that was removed
	 * @throws com.beorn.onlinepayment.NoSuchTransactionLogException if a transaction log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TransactionLog remove(Serializable primaryKey)
		throws NoSuchTransactionLogException, SystemException {
		Session session = null;

		try {
			session = openSession();

			TransactionLog transactionLog = (TransactionLog)session.get(TransactionLogImpl.class,
					primaryKey);

			if (transactionLog == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTransactionLogException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(transactionLog);
		}
		catch (NoSuchTransactionLogException nsee) {
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
	protected TransactionLog removeImpl(TransactionLog transactionLog)
		throws SystemException {
		transactionLog = toUnwrappedModel(transactionLog);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, transactionLog);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(transactionLog);

		return transactionLog;
	}

	@Override
	public TransactionLog updateImpl(
		com.beorn.onlinepayment.model.TransactionLog transactionLog,
		boolean merge) throws SystemException {
		transactionLog = toUnwrappedModel(transactionLog);

		boolean isNew = transactionLog.isNew();

		TransactionLogModelImpl transactionLogModelImpl = (TransactionLogModelImpl)transactionLog;

		if (Validator.isNull(transactionLog.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			transactionLog.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, transactionLog, merge);

			transactionLog.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !TransactionLogModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((transactionLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						transactionLogModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { transactionLogModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((transactionLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PAYMENTAPPLICATIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						transactionLogModelImpl.getOriginalPaymentApplicationId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PAYMENTAPPLICATIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PAYMENTAPPLICATIONID,
					args);

				args = new Object[] {
						transactionLogModelImpl.getPaymentApplicationId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PAYMENTAPPLICATIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PAYMENTAPPLICATIONID,
					args);
			}
		}

		EntityCacheUtil.putResult(TransactionLogModelImpl.ENTITY_CACHE_ENABLED,
			TransactionLogImpl.class, transactionLog.getPrimaryKey(),
			transactionLog);

		if (isNew) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					transactionLog.getUuid(),
					Long.valueOf(transactionLog.getGroupId())
				}, transactionLog);

			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PAYMENTAPPLICATIONIDANDREMOTEID,
				new Object[] {
					transactionLog.getPaymentApplicationId(),
					
				transactionLog.getRemoteId()
				}, transactionLog);
		}
		else {
			if ((transactionLogModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						transactionLogModelImpl.getOriginalUuid(),
						Long.valueOf(transactionLogModelImpl.getOriginalGroupId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);

				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
					new Object[] {
						transactionLog.getUuid(),
						Long.valueOf(transactionLog.getGroupId())
					}, transactionLog);
			}

			if ((transactionLogModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_PAYMENTAPPLICATIONIDANDREMOTEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						transactionLogModelImpl.getOriginalPaymentApplicationId(),
						
						transactionLogModelImpl.getOriginalRemoteId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PAYMENTAPPLICATIONIDANDREMOTEID,
					args);

				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PAYMENTAPPLICATIONIDANDREMOTEID,
					args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PAYMENTAPPLICATIONIDANDREMOTEID,
					new Object[] {
						transactionLog.getPaymentApplicationId(),
						
					transactionLog.getRemoteId()
					}, transactionLog);
			}
		}

		return transactionLog;
	}

	protected TransactionLog toUnwrappedModel(TransactionLog transactionLog) {
		if (transactionLog instanceof TransactionLogImpl) {
			return transactionLog;
		}

		TransactionLogImpl transactionLogImpl = new TransactionLogImpl();

		transactionLogImpl.setNew(transactionLog.isNew());
		transactionLogImpl.setPrimaryKey(transactionLog.getPrimaryKey());

		transactionLogImpl.setUuid(transactionLog.getUuid());
		transactionLogImpl.setCompanyId(transactionLog.getCompanyId());
		transactionLogImpl.setGroupId(transactionLog.getGroupId());
		transactionLogImpl.setUserId(transactionLog.getUserId());
		transactionLogImpl.setTransactionLogId(transactionLog.getTransactionLogId());
		transactionLogImpl.setCreateDate(transactionLog.getCreateDate());
		transactionLogImpl.setModifiedDate(transactionLog.getModifiedDate());
		transactionLogImpl.setTransactionId(transactionLog.getTransactionId());
		transactionLogImpl.setPaymentApplicationId(transactionLog.getPaymentApplicationId());
		transactionLogImpl.setRemoteId(transactionLog.getRemoteId());
		transactionLogImpl.setAmount(transactionLog.getAmount());
		transactionLogImpl.setType(transactionLog.getType());

		return transactionLogImpl;
	}

	/**
	 * Returns the transaction log with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the transaction log
	 * @return the transaction log
	 * @throws com.liferay.portal.NoSuchModelException if a transaction log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TransactionLog findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the transaction log with the primary key or throws a {@link com.beorn.onlinepayment.NoSuchTransactionLogException} if it could not be found.
	 *
	 * @param transactionLogId the primary key of the transaction log
	 * @return the transaction log
	 * @throws com.beorn.onlinepayment.NoSuchTransactionLogException if a transaction log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TransactionLog findByPrimaryKey(long transactionLogId)
		throws NoSuchTransactionLogException, SystemException {
		TransactionLog transactionLog = fetchByPrimaryKey(transactionLogId);

		if (transactionLog == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + transactionLogId);
			}

			throw new NoSuchTransactionLogException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				transactionLogId);
		}

		return transactionLog;
	}

	/**
	 * Returns the transaction log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the transaction log
	 * @return the transaction log, or <code>null</code> if a transaction log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TransactionLog fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the transaction log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param transactionLogId the primary key of the transaction log
	 * @return the transaction log, or <code>null</code> if a transaction log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TransactionLog fetchByPrimaryKey(long transactionLogId)
		throws SystemException {
		TransactionLog transactionLog = (TransactionLog)EntityCacheUtil.getResult(TransactionLogModelImpl.ENTITY_CACHE_ENABLED,
				TransactionLogImpl.class, transactionLogId);

		if (transactionLog == _nullTransactionLog) {
			return null;
		}

		if (transactionLog == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				transactionLog = (TransactionLog)session.get(TransactionLogImpl.class,
						Long.valueOf(transactionLogId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (transactionLog != null) {
					cacheResult(transactionLog);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(TransactionLogModelImpl.ENTITY_CACHE_ENABLED,
						TransactionLogImpl.class, transactionLogId,
						_nullTransactionLog);
				}

				closeSession(session);
			}
		}

		return transactionLog;
	}

	/**
	 * Returns all the transaction logs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching transaction logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<TransactionLog> findByUuid(String uuid)
		throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the transaction logs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of transaction logs
	 * @param end the upper bound of the range of transaction logs (not inclusive)
	 * @return the range of matching transaction logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<TransactionLog> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the transaction logs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of transaction logs
	 * @param end the upper bound of the range of transaction logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching transaction logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<TransactionLog> findByUuid(String uuid, int start, int end,
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

		List<TransactionLog> list = (List<TransactionLog>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TransactionLog transactionLog : list) {
				if (!Validator.equals(uuid, transactionLog.getUuid())) {
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

			query.append(_SQL_SELECT_TRANSACTIONLOG_WHERE);

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
				query.append(TransactionLogModelImpl.ORDER_BY_JPQL);
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

				list = (List<TransactionLog>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first transaction log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching transaction log
	 * @throws com.beorn.onlinepayment.NoSuchTransactionLogException if a matching transaction log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TransactionLog findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchTransactionLogException, SystemException {
		TransactionLog transactionLog = fetchByUuid_First(uuid,
				orderByComparator);

		if (transactionLog != null) {
			return transactionLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTransactionLogException(msg.toString());
	}

	/**
	 * Returns the first transaction log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching transaction log, or <code>null</code> if a matching transaction log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TransactionLog fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<TransactionLog> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last transaction log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching transaction log
	 * @throws com.beorn.onlinepayment.NoSuchTransactionLogException if a matching transaction log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TransactionLog findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchTransactionLogException, SystemException {
		TransactionLog transactionLog = fetchByUuid_Last(uuid, orderByComparator);

		if (transactionLog != null) {
			return transactionLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTransactionLogException(msg.toString());
	}

	/**
	 * Returns the last transaction log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching transaction log, or <code>null</code> if a matching transaction log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TransactionLog fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		List<TransactionLog> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the transaction logs before and after the current transaction log in the ordered set where uuid = &#63;.
	 *
	 * @param transactionLogId the primary key of the current transaction log
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next transaction log
	 * @throws com.beorn.onlinepayment.NoSuchTransactionLogException if a transaction log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TransactionLog[] findByUuid_PrevAndNext(long transactionLogId,
		String uuid, OrderByComparator orderByComparator)
		throws NoSuchTransactionLogException, SystemException {
		TransactionLog transactionLog = findByPrimaryKey(transactionLogId);

		Session session = null;

		try {
			session = openSession();

			TransactionLog[] array = new TransactionLogImpl[3];

			array[0] = getByUuid_PrevAndNext(session, transactionLog, uuid,
					orderByComparator, true);

			array[1] = transactionLog;

			array[2] = getByUuid_PrevAndNext(session, transactionLog, uuid,
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

	protected TransactionLog getByUuid_PrevAndNext(Session session,
		TransactionLog transactionLog, String uuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TRANSACTIONLOG_WHERE);

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
			query.append(TransactionLogModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(transactionLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TransactionLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the transaction log where uuid = &#63; and groupId = &#63; or throws a {@link com.beorn.onlinepayment.NoSuchTransactionLogException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching transaction log
	 * @throws com.beorn.onlinepayment.NoSuchTransactionLogException if a matching transaction log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TransactionLog findByUUID_G(String uuid, long groupId)
		throws NoSuchTransactionLogException, SystemException {
		TransactionLog transactionLog = fetchByUUID_G(uuid, groupId);

		if (transactionLog == null) {
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

			throw new NoSuchTransactionLogException(msg.toString());
		}

		return transactionLog;
	}

	/**
	 * Returns the transaction log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching transaction log, or <code>null</code> if a matching transaction log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TransactionLog fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the transaction log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching transaction log, or <code>null</code> if a matching transaction log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TransactionLog fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof TransactionLog) {
			TransactionLog transactionLog = (TransactionLog)result;

			if (!Validator.equals(uuid, transactionLog.getUuid()) ||
					(groupId != transactionLog.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_TRANSACTIONLOG_WHERE);

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

			query.append(TransactionLogModelImpl.ORDER_BY_JPQL);

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

				List<TransactionLog> list = q.list();

				result = list;

				TransactionLog transactionLog = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					transactionLog = list.get(0);

					cacheResult(transactionLog);

					if ((transactionLog.getUuid() == null) ||
							!transactionLog.getUuid().equals(uuid) ||
							(transactionLog.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, transactionLog);
					}
				}

				return transactionLog;
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
				return (TransactionLog)result;
			}
		}
	}

	/**
	 * Returns all the transaction logs where paymentApplicationId = &#63;.
	 *
	 * @param paymentApplicationId the payment application ID
	 * @return the matching transaction logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<TransactionLog> findByPaymentApplicationId(
		String paymentApplicationId) throws SystemException {
		return findByPaymentApplicationId(paymentApplicationId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the transaction logs where paymentApplicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param paymentApplicationId the payment application ID
	 * @param start the lower bound of the range of transaction logs
	 * @param end the upper bound of the range of transaction logs (not inclusive)
	 * @return the range of matching transaction logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<TransactionLog> findByPaymentApplicationId(
		String paymentApplicationId, int start, int end)
		throws SystemException {
		return findByPaymentApplicationId(paymentApplicationId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the transaction logs where paymentApplicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param paymentApplicationId the payment application ID
	 * @param start the lower bound of the range of transaction logs
	 * @param end the upper bound of the range of transaction logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching transaction logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<TransactionLog> findByPaymentApplicationId(
		String paymentApplicationId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PAYMENTAPPLICATIONID;
			finderArgs = new Object[] { paymentApplicationId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PAYMENTAPPLICATIONID;
			finderArgs = new Object[] {
					paymentApplicationId,
					
					start, end, orderByComparator
				};
		}

		List<TransactionLog> list = (List<TransactionLog>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TransactionLog transactionLog : list) {
				if (!Validator.equals(paymentApplicationId,
							transactionLog.getPaymentApplicationId())) {
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

			query.append(_SQL_SELECT_TRANSACTIONLOG_WHERE);

			if (paymentApplicationId == null) {
				query.append(_FINDER_COLUMN_PAYMENTAPPLICATIONID_PAYMENTAPPLICATIONID_1);
			}
			else {
				if (paymentApplicationId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PAYMENTAPPLICATIONID_PAYMENTAPPLICATIONID_3);
				}
				else {
					query.append(_FINDER_COLUMN_PAYMENTAPPLICATIONID_PAYMENTAPPLICATIONID_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TransactionLogModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (paymentApplicationId != null) {
					qPos.add(paymentApplicationId);
				}

				list = (List<TransactionLog>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first transaction log in the ordered set where paymentApplicationId = &#63;.
	 *
	 * @param paymentApplicationId the payment application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching transaction log
	 * @throws com.beorn.onlinepayment.NoSuchTransactionLogException if a matching transaction log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TransactionLog findByPaymentApplicationId_First(
		String paymentApplicationId, OrderByComparator orderByComparator)
		throws NoSuchTransactionLogException, SystemException {
		TransactionLog transactionLog = fetchByPaymentApplicationId_First(paymentApplicationId,
				orderByComparator);

		if (transactionLog != null) {
			return transactionLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("paymentApplicationId=");
		msg.append(paymentApplicationId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTransactionLogException(msg.toString());
	}

	/**
	 * Returns the first transaction log in the ordered set where paymentApplicationId = &#63;.
	 *
	 * @param paymentApplicationId the payment application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching transaction log, or <code>null</code> if a matching transaction log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TransactionLog fetchByPaymentApplicationId_First(
		String paymentApplicationId, OrderByComparator orderByComparator)
		throws SystemException {
		List<TransactionLog> list = findByPaymentApplicationId(paymentApplicationId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last transaction log in the ordered set where paymentApplicationId = &#63;.
	 *
	 * @param paymentApplicationId the payment application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching transaction log
	 * @throws com.beorn.onlinepayment.NoSuchTransactionLogException if a matching transaction log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TransactionLog findByPaymentApplicationId_Last(
		String paymentApplicationId, OrderByComparator orderByComparator)
		throws NoSuchTransactionLogException, SystemException {
		TransactionLog transactionLog = fetchByPaymentApplicationId_Last(paymentApplicationId,
				orderByComparator);

		if (transactionLog != null) {
			return transactionLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("paymentApplicationId=");
		msg.append(paymentApplicationId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTransactionLogException(msg.toString());
	}

	/**
	 * Returns the last transaction log in the ordered set where paymentApplicationId = &#63;.
	 *
	 * @param paymentApplicationId the payment application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching transaction log, or <code>null</code> if a matching transaction log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TransactionLog fetchByPaymentApplicationId_Last(
		String paymentApplicationId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByPaymentApplicationId(paymentApplicationId);

		List<TransactionLog> list = findByPaymentApplicationId(paymentApplicationId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the transaction logs before and after the current transaction log in the ordered set where paymentApplicationId = &#63;.
	 *
	 * @param transactionLogId the primary key of the current transaction log
	 * @param paymentApplicationId the payment application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next transaction log
	 * @throws com.beorn.onlinepayment.NoSuchTransactionLogException if a transaction log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TransactionLog[] findByPaymentApplicationId_PrevAndNext(
		long transactionLogId, String paymentApplicationId,
		OrderByComparator orderByComparator)
		throws NoSuchTransactionLogException, SystemException {
		TransactionLog transactionLog = findByPrimaryKey(transactionLogId);

		Session session = null;

		try {
			session = openSession();

			TransactionLog[] array = new TransactionLogImpl[3];

			array[0] = getByPaymentApplicationId_PrevAndNext(session,
					transactionLog, paymentApplicationId, orderByComparator,
					true);

			array[1] = transactionLog;

			array[2] = getByPaymentApplicationId_PrevAndNext(session,
					transactionLog, paymentApplicationId, orderByComparator,
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

	protected TransactionLog getByPaymentApplicationId_PrevAndNext(
		Session session, TransactionLog transactionLog,
		String paymentApplicationId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TRANSACTIONLOG_WHERE);

		if (paymentApplicationId == null) {
			query.append(_FINDER_COLUMN_PAYMENTAPPLICATIONID_PAYMENTAPPLICATIONID_1);
		}
		else {
			if (paymentApplicationId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PAYMENTAPPLICATIONID_PAYMENTAPPLICATIONID_3);
			}
			else {
				query.append(_FINDER_COLUMN_PAYMENTAPPLICATIONID_PAYMENTAPPLICATIONID_2);
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
			query.append(TransactionLogModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (paymentApplicationId != null) {
			qPos.add(paymentApplicationId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(transactionLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TransactionLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the transaction log where paymentApplicationId = &#63; and remoteId = &#63; or throws a {@link com.beorn.onlinepayment.NoSuchTransactionLogException} if it could not be found.
	 *
	 * @param paymentApplicationId the payment application ID
	 * @param remoteId the remote ID
	 * @return the matching transaction log
	 * @throws com.beorn.onlinepayment.NoSuchTransactionLogException if a matching transaction log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TransactionLog findByPaymentApplicationIdAndRemoteId(
		String paymentApplicationId, String remoteId)
		throws NoSuchTransactionLogException, SystemException {
		TransactionLog transactionLog = fetchByPaymentApplicationIdAndRemoteId(paymentApplicationId,
				remoteId);

		if (transactionLog == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("paymentApplicationId=");
			msg.append(paymentApplicationId);

			msg.append(", remoteId=");
			msg.append(remoteId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchTransactionLogException(msg.toString());
		}

		return transactionLog;
	}

	/**
	 * Returns the transaction log where paymentApplicationId = &#63; and remoteId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param paymentApplicationId the payment application ID
	 * @param remoteId the remote ID
	 * @return the matching transaction log, or <code>null</code> if a matching transaction log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TransactionLog fetchByPaymentApplicationIdAndRemoteId(
		String paymentApplicationId, String remoteId) throws SystemException {
		return fetchByPaymentApplicationIdAndRemoteId(paymentApplicationId,
			remoteId, true);
	}

	/**
	 * Returns the transaction log where paymentApplicationId = &#63; and remoteId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param paymentApplicationId the payment application ID
	 * @param remoteId the remote ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching transaction log, or <code>null</code> if a matching transaction log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TransactionLog fetchByPaymentApplicationIdAndRemoteId(
		String paymentApplicationId, String remoteId, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { paymentApplicationId, remoteId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_PAYMENTAPPLICATIONIDANDREMOTEID,
					finderArgs, this);
		}

		if (result instanceof TransactionLog) {
			TransactionLog transactionLog = (TransactionLog)result;

			if (!Validator.equals(paymentApplicationId,
						transactionLog.getPaymentApplicationId()) ||
					!Validator.equals(remoteId, transactionLog.getRemoteId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_TRANSACTIONLOG_WHERE);

			if (paymentApplicationId == null) {
				query.append(_FINDER_COLUMN_PAYMENTAPPLICATIONIDANDREMOTEID_PAYMENTAPPLICATIONID_1);
			}
			else {
				if (paymentApplicationId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PAYMENTAPPLICATIONIDANDREMOTEID_PAYMENTAPPLICATIONID_3);
				}
				else {
					query.append(_FINDER_COLUMN_PAYMENTAPPLICATIONIDANDREMOTEID_PAYMENTAPPLICATIONID_2);
				}
			}

			if (remoteId == null) {
				query.append(_FINDER_COLUMN_PAYMENTAPPLICATIONIDANDREMOTEID_REMOTEID_1);
			}
			else {
				if (remoteId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PAYMENTAPPLICATIONIDANDREMOTEID_REMOTEID_3);
				}
				else {
					query.append(_FINDER_COLUMN_PAYMENTAPPLICATIONIDANDREMOTEID_REMOTEID_2);
				}
			}

			query.append(TransactionLogModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (paymentApplicationId != null) {
					qPos.add(paymentApplicationId);
				}

				if (remoteId != null) {
					qPos.add(remoteId);
				}

				List<TransactionLog> list = q.list();

				result = list;

				TransactionLog transactionLog = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PAYMENTAPPLICATIONIDANDREMOTEID,
						finderArgs, list);
				}
				else {
					transactionLog = list.get(0);

					cacheResult(transactionLog);

					if ((transactionLog.getPaymentApplicationId() == null) ||
							!transactionLog.getPaymentApplicationId()
											   .equals(paymentApplicationId) ||
							(transactionLog.getRemoteId() == null) ||
							!transactionLog.getRemoteId().equals(remoteId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PAYMENTAPPLICATIONIDANDREMOTEID,
							finderArgs, transactionLog);
					}
				}

				return transactionLog;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PAYMENTAPPLICATIONIDANDREMOTEID,
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
				return (TransactionLog)result;
			}
		}
	}

	/**
	 * Returns all the transaction logs.
	 *
	 * @return the transaction logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<TransactionLog> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the transaction logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of transaction logs
	 * @param end the upper bound of the range of transaction logs (not inclusive)
	 * @return the range of transaction logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<TransactionLog> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the transaction logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of transaction logs
	 * @param end the upper bound of the range of transaction logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of transaction logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<TransactionLog> findAll(int start, int end,
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

		List<TransactionLog> list = (List<TransactionLog>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TRANSACTIONLOG);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TRANSACTIONLOG.concat(TransactionLogModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<TransactionLog>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<TransactionLog>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Removes all the transaction logs where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (TransactionLog transactionLog : findByUuid(uuid)) {
			remove(transactionLog);
		}
	}

	/**
	 * Removes the transaction log where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the transaction log that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public TransactionLog removeByUUID_G(String uuid, long groupId)
		throws NoSuchTransactionLogException, SystemException {
		TransactionLog transactionLog = findByUUID_G(uuid, groupId);

		return remove(transactionLog);
	}

	/**
	 * Removes all the transaction logs where paymentApplicationId = &#63; from the database.
	 *
	 * @param paymentApplicationId the payment application ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPaymentApplicationId(String paymentApplicationId)
		throws SystemException {
		for (TransactionLog transactionLog : findByPaymentApplicationId(
				paymentApplicationId)) {
			remove(transactionLog);
		}
	}

	/**
	 * Removes the transaction log where paymentApplicationId = &#63; and remoteId = &#63; from the database.
	 *
	 * @param paymentApplicationId the payment application ID
	 * @param remoteId the remote ID
	 * @return the transaction log that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public TransactionLog removeByPaymentApplicationIdAndRemoteId(
		String paymentApplicationId, String remoteId)
		throws NoSuchTransactionLogException, SystemException {
		TransactionLog transactionLog = findByPaymentApplicationIdAndRemoteId(paymentApplicationId,
				remoteId);

		return remove(transactionLog);
	}

	/**
	 * Removes all the transaction logs from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (TransactionLog transactionLog : findAll()) {
			remove(transactionLog);
		}
	}

	/**
	 * Returns the number of transaction logs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching transaction logs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TRANSACTIONLOG_WHERE);

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
	 * Returns the number of transaction logs where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching transaction logs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUUID_G(String uuid, long groupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID_G,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TRANSACTIONLOG_WHERE);

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
	 * Returns the number of transaction logs where paymentApplicationId = &#63;.
	 *
	 * @param paymentApplicationId the payment application ID
	 * @return the number of matching transaction logs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPaymentApplicationId(String paymentApplicationId)
		throws SystemException {
		Object[] finderArgs = new Object[] { paymentApplicationId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PAYMENTAPPLICATIONID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TRANSACTIONLOG_WHERE);

			if (paymentApplicationId == null) {
				query.append(_FINDER_COLUMN_PAYMENTAPPLICATIONID_PAYMENTAPPLICATIONID_1);
			}
			else {
				if (paymentApplicationId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PAYMENTAPPLICATIONID_PAYMENTAPPLICATIONID_3);
				}
				else {
					query.append(_FINDER_COLUMN_PAYMENTAPPLICATIONID_PAYMENTAPPLICATIONID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (paymentApplicationId != null) {
					qPos.add(paymentApplicationId);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PAYMENTAPPLICATIONID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of transaction logs where paymentApplicationId = &#63; and remoteId = &#63;.
	 *
	 * @param paymentApplicationId the payment application ID
	 * @param remoteId the remote ID
	 * @return the number of matching transaction logs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPaymentApplicationIdAndRemoteId(
		String paymentApplicationId, String remoteId) throws SystemException {
		Object[] finderArgs = new Object[] { paymentApplicationId, remoteId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PAYMENTAPPLICATIONIDANDREMOTEID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TRANSACTIONLOG_WHERE);

			if (paymentApplicationId == null) {
				query.append(_FINDER_COLUMN_PAYMENTAPPLICATIONIDANDREMOTEID_PAYMENTAPPLICATIONID_1);
			}
			else {
				if (paymentApplicationId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PAYMENTAPPLICATIONIDANDREMOTEID_PAYMENTAPPLICATIONID_3);
				}
				else {
					query.append(_FINDER_COLUMN_PAYMENTAPPLICATIONIDANDREMOTEID_PAYMENTAPPLICATIONID_2);
				}
			}

			if (remoteId == null) {
				query.append(_FINDER_COLUMN_PAYMENTAPPLICATIONIDANDREMOTEID_REMOTEID_1);
			}
			else {
				if (remoteId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PAYMENTAPPLICATIONIDANDREMOTEID_REMOTEID_3);
				}
				else {
					query.append(_FINDER_COLUMN_PAYMENTAPPLICATIONIDANDREMOTEID_REMOTEID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (paymentApplicationId != null) {
					qPos.add(paymentApplicationId);
				}

				if (remoteId != null) {
					qPos.add(remoteId);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PAYMENTAPPLICATIONIDANDREMOTEID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of transaction logs.
	 *
	 * @return the number of transaction logs
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TRANSACTIONLOG);

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
	 * Initializes the transaction log persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.beorn.onlinepayment.model.TransactionLog")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<TransactionLog>> listenersList = new ArrayList<ModelListener<TransactionLog>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<TransactionLog>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(TransactionLogImpl.class.getName());
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
	private static final String _SQL_SELECT_TRANSACTIONLOG = "SELECT transactionLog FROM TransactionLog transactionLog";
	private static final String _SQL_SELECT_TRANSACTIONLOG_WHERE = "SELECT transactionLog FROM TransactionLog transactionLog WHERE ";
	private static final String _SQL_COUNT_TRANSACTIONLOG = "SELECT COUNT(transactionLog) FROM TransactionLog transactionLog";
	private static final String _SQL_COUNT_TRANSACTIONLOG_WHERE = "SELECT COUNT(transactionLog) FROM TransactionLog transactionLog WHERE ";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "transactionLog.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "transactionLog.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(transactionLog.uuid IS NULL OR transactionLog.uuid = ?)";
	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "transactionLog.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "transactionLog.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(transactionLog.uuid IS NULL OR transactionLog.uuid = ?) AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "transactionLog.groupId = ?";
	private static final String _FINDER_COLUMN_PAYMENTAPPLICATIONID_PAYMENTAPPLICATIONID_1 =
		"transactionLog.paymentApplicationId IS NULL";
	private static final String _FINDER_COLUMN_PAYMENTAPPLICATIONID_PAYMENTAPPLICATIONID_2 =
		"transactionLog.paymentApplicationId = ?";
	private static final String _FINDER_COLUMN_PAYMENTAPPLICATIONID_PAYMENTAPPLICATIONID_3 =
		"(transactionLog.paymentApplicationId IS NULL OR transactionLog.paymentApplicationId = ?)";
	private static final String _FINDER_COLUMN_PAYMENTAPPLICATIONIDANDREMOTEID_PAYMENTAPPLICATIONID_1 =
		"transactionLog.paymentApplicationId IS NULL AND ";
	private static final String _FINDER_COLUMN_PAYMENTAPPLICATIONIDANDREMOTEID_PAYMENTAPPLICATIONID_2 =
		"transactionLog.paymentApplicationId = ? AND ";
	private static final String _FINDER_COLUMN_PAYMENTAPPLICATIONIDANDREMOTEID_PAYMENTAPPLICATIONID_3 =
		"(transactionLog.paymentApplicationId IS NULL OR transactionLog.paymentApplicationId = ?) AND ";
	private static final String _FINDER_COLUMN_PAYMENTAPPLICATIONIDANDREMOTEID_REMOTEID_1 =
		"transactionLog.remoteId IS NULL";
	private static final String _FINDER_COLUMN_PAYMENTAPPLICATIONIDANDREMOTEID_REMOTEID_2 =
		"transactionLog.remoteId = ?";
	private static final String _FINDER_COLUMN_PAYMENTAPPLICATIONIDANDREMOTEID_REMOTEID_3 =
		"(transactionLog.remoteId IS NULL OR transactionLog.remoteId = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "transactionLog.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TransactionLog exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TransactionLog exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(TransactionLogPersistenceImpl.class);
	private static TransactionLog _nullTransactionLog = new TransactionLogImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<TransactionLog> toCacheModel() {
				return _nullTransactionLogCacheModel;
			}
		};

	private static CacheModel<TransactionLog> _nullTransactionLogCacheModel = new CacheModel<TransactionLog>() {
			public TransactionLog toEntityModel() {
				return _nullTransactionLog;
			}
		};
}