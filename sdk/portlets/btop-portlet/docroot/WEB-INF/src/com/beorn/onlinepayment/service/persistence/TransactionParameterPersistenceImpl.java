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

import com.beorn.onlinepayment.NoSuchTransactionParameterException;
import com.beorn.onlinepayment.model.TransactionParameter;
import com.beorn.onlinepayment.model.impl.TransactionParameterImpl;
import com.beorn.onlinepayment.model.impl.TransactionParameterModelImpl;

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
 * The persistence implementation for the transaction parameter service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Sebastien Meunier
 * @see TransactionParameterPersistence
 * @see TransactionParameterUtil
 * @generated
 */
public class TransactionParameterPersistenceImpl extends BasePersistenceImpl<TransactionParameter>
	implements TransactionParameterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TransactionParameterUtil} to access the transaction parameter persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TransactionParameterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(TransactionParameterModelImpl.ENTITY_CACHE_ENABLED,
			TransactionParameterModelImpl.FINDER_CACHE_ENABLED,
			TransactionParameterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(TransactionParameterModelImpl.ENTITY_CACHE_ENABLED,
			TransactionParameterModelImpl.FINDER_CACHE_ENABLED,
			TransactionParameterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			TransactionParameterModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(TransactionParameterModelImpl.ENTITY_CACHE_ENABLED,
			TransactionParameterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(TransactionParameterModelImpl.ENTITY_CACHE_ENABLED,
			TransactionParameterModelImpl.FINDER_CACHE_ENABLED,
			TransactionParameterImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			TransactionParameterModelImpl.UUID_COLUMN_BITMASK |
			TransactionParameterModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(TransactionParameterModelImpl.ENTITY_CACHE_ENABLED,
			TransactionParameterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_TRANSACTIONIDANDKEY = new FinderPath(TransactionParameterModelImpl.ENTITY_CACHE_ENABLED,
			TransactionParameterModelImpl.FINDER_CACHE_ENABLED,
			TransactionParameterImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByTransactionIdAndKey",
			new String[] { Long.class.getName(), String.class.getName() },
			TransactionParameterModelImpl.TRANSACTIONID_COLUMN_BITMASK |
			TransactionParameterModelImpl.KEY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TRANSACTIONIDANDKEY = new FinderPath(TransactionParameterModelImpl.ENTITY_CACHE_ENABLED,
			TransactionParameterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByTransactionIdAndKey",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TransactionParameterModelImpl.ENTITY_CACHE_ENABLED,
			TransactionParameterModelImpl.FINDER_CACHE_ENABLED,
			TransactionParameterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TransactionParameterModelImpl.ENTITY_CACHE_ENABLED,
			TransactionParameterModelImpl.FINDER_CACHE_ENABLED,
			TransactionParameterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TransactionParameterModelImpl.ENTITY_CACHE_ENABLED,
			TransactionParameterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the transaction parameter in the entity cache if it is enabled.
	 *
	 * @param transactionParameter the transaction parameter
	 */
	public void cacheResult(TransactionParameter transactionParameter) {
		EntityCacheUtil.putResult(TransactionParameterModelImpl.ENTITY_CACHE_ENABLED,
			TransactionParameterImpl.class,
			transactionParameter.getPrimaryKey(), transactionParameter);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				transactionParameter.getUuid(),
				Long.valueOf(transactionParameter.getGroupId())
			}, transactionParameter);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TRANSACTIONIDANDKEY,
			new Object[] {
				Long.valueOf(transactionParameter.getTransactionId()),
				
			transactionParameter.getKey()
			}, transactionParameter);

		transactionParameter.resetOriginalValues();
	}

	/**
	 * Caches the transaction parameters in the entity cache if it is enabled.
	 *
	 * @param transactionParameters the transaction parameters
	 */
	public void cacheResult(List<TransactionParameter> transactionParameters) {
		for (TransactionParameter transactionParameter : transactionParameters) {
			if (EntityCacheUtil.getResult(
						TransactionParameterModelImpl.ENTITY_CACHE_ENABLED,
						TransactionParameterImpl.class,
						transactionParameter.getPrimaryKey()) == null) {
				cacheResult(transactionParameter);
			}
			else {
				transactionParameter.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all transaction parameters.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(TransactionParameterImpl.class.getName());
		}

		EntityCacheUtil.clearCache(TransactionParameterImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the transaction parameter.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TransactionParameter transactionParameter) {
		EntityCacheUtil.removeResult(TransactionParameterModelImpl.ENTITY_CACHE_ENABLED,
			TransactionParameterImpl.class, transactionParameter.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(transactionParameter);
	}

	@Override
	public void clearCache(List<TransactionParameter> transactionParameters) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TransactionParameter transactionParameter : transactionParameters) {
			EntityCacheUtil.removeResult(TransactionParameterModelImpl.ENTITY_CACHE_ENABLED,
				TransactionParameterImpl.class,
				transactionParameter.getPrimaryKey());

			clearUniqueFindersCache(transactionParameter);
		}
	}

	protected void clearUniqueFindersCache(
		TransactionParameter transactionParameter) {
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				transactionParameter.getUuid(),
				Long.valueOf(transactionParameter.getGroupId())
			});

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_TRANSACTIONIDANDKEY,
			new Object[] {
				Long.valueOf(transactionParameter.getTransactionId()),
				
			transactionParameter.getKey()
			});
	}

	/**
	 * Creates a new transaction parameter with the primary key. Does not add the transaction parameter to the database.
	 *
	 * @param transactionParameterId the primary key for the new transaction parameter
	 * @return the new transaction parameter
	 */
	public TransactionParameter create(long transactionParameterId) {
		TransactionParameter transactionParameter = new TransactionParameterImpl();

		transactionParameter.setNew(true);
		transactionParameter.setPrimaryKey(transactionParameterId);

		String uuid = PortalUUIDUtil.generate();

		transactionParameter.setUuid(uuid);

		return transactionParameter;
	}

	/**
	 * Removes the transaction parameter with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param transactionParameterId the primary key of the transaction parameter
	 * @return the transaction parameter that was removed
	 * @throws com.beorn.onlinepayment.NoSuchTransactionParameterException if a transaction parameter with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TransactionParameter remove(long transactionParameterId)
		throws NoSuchTransactionParameterException, SystemException {
		return remove(Long.valueOf(transactionParameterId));
	}

	/**
	 * Removes the transaction parameter with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the transaction parameter
	 * @return the transaction parameter that was removed
	 * @throws com.beorn.onlinepayment.NoSuchTransactionParameterException if a transaction parameter with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TransactionParameter remove(Serializable primaryKey)
		throws NoSuchTransactionParameterException, SystemException {
		Session session = null;

		try {
			session = openSession();

			TransactionParameter transactionParameter = (TransactionParameter)session.get(TransactionParameterImpl.class,
					primaryKey);

			if (transactionParameter == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTransactionParameterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(transactionParameter);
		}
		catch (NoSuchTransactionParameterException nsee) {
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
	protected TransactionParameter removeImpl(
		TransactionParameter transactionParameter) throws SystemException {
		transactionParameter = toUnwrappedModel(transactionParameter);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, transactionParameter);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(transactionParameter);

		return transactionParameter;
	}

	@Override
	public TransactionParameter updateImpl(
		com.beorn.onlinepayment.model.TransactionParameter transactionParameter,
		boolean merge) throws SystemException {
		transactionParameter = toUnwrappedModel(transactionParameter);

		boolean isNew = transactionParameter.isNew();

		TransactionParameterModelImpl transactionParameterModelImpl = (TransactionParameterModelImpl)transactionParameter;

		if (Validator.isNull(transactionParameter.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			transactionParameter.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, transactionParameter, merge);

			transactionParameter.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !TransactionParameterModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((transactionParameterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						transactionParameterModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { transactionParameterModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}
		}

		EntityCacheUtil.putResult(TransactionParameterModelImpl.ENTITY_CACHE_ENABLED,
			TransactionParameterImpl.class,
			transactionParameter.getPrimaryKey(), transactionParameter);

		if (isNew) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					transactionParameter.getUuid(),
					Long.valueOf(transactionParameter.getGroupId())
				}, transactionParameter);

			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TRANSACTIONIDANDKEY,
				new Object[] {
					Long.valueOf(transactionParameter.getTransactionId()),
					
				transactionParameter.getKey()
				}, transactionParameter);
		}
		else {
			if ((transactionParameterModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						transactionParameterModelImpl.getOriginalUuid(),
						Long.valueOf(transactionParameterModelImpl.getOriginalGroupId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);

				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
					new Object[] {
						transactionParameter.getUuid(),
						Long.valueOf(transactionParameter.getGroupId())
					}, transactionParameter);
			}

			if ((transactionParameterModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_TRANSACTIONIDANDKEY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(transactionParameterModelImpl.getOriginalTransactionId()),
						
						transactionParameterModelImpl.getOriginalKey()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TRANSACTIONIDANDKEY,
					args);

				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_TRANSACTIONIDANDKEY,
					args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TRANSACTIONIDANDKEY,
					new Object[] {
						Long.valueOf(transactionParameter.getTransactionId()),
						
					transactionParameter.getKey()
					}, transactionParameter);
			}
		}

		return transactionParameter;
	}

	protected TransactionParameter toUnwrappedModel(
		TransactionParameter transactionParameter) {
		if (transactionParameter instanceof TransactionParameterImpl) {
			return transactionParameter;
		}

		TransactionParameterImpl transactionParameterImpl = new TransactionParameterImpl();

		transactionParameterImpl.setNew(transactionParameter.isNew());
		transactionParameterImpl.setPrimaryKey(transactionParameter.getPrimaryKey());

		transactionParameterImpl.setUuid(transactionParameter.getUuid());
		transactionParameterImpl.setCompanyId(transactionParameter.getCompanyId());
		transactionParameterImpl.setGroupId(transactionParameter.getGroupId());
		transactionParameterImpl.setUserId(transactionParameter.getUserId());
		transactionParameterImpl.setTransactionParameterId(transactionParameter.getTransactionParameterId());
		transactionParameterImpl.setCreateDate(transactionParameter.getCreateDate());
		transactionParameterImpl.setModifiedDate(transactionParameter.getModifiedDate());
		transactionParameterImpl.setTransactionId(transactionParameter.getTransactionId());
		transactionParameterImpl.setKey(transactionParameter.getKey());
		transactionParameterImpl.setValue(transactionParameter.getValue());

		return transactionParameterImpl;
	}

	/**
	 * Returns the transaction parameter with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the transaction parameter
	 * @return the transaction parameter
	 * @throws com.liferay.portal.NoSuchModelException if a transaction parameter with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TransactionParameter findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the transaction parameter with the primary key or throws a {@link com.beorn.onlinepayment.NoSuchTransactionParameterException} if it could not be found.
	 *
	 * @param transactionParameterId the primary key of the transaction parameter
	 * @return the transaction parameter
	 * @throws com.beorn.onlinepayment.NoSuchTransactionParameterException if a transaction parameter with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TransactionParameter findByPrimaryKey(long transactionParameterId)
		throws NoSuchTransactionParameterException, SystemException {
		TransactionParameter transactionParameter = fetchByPrimaryKey(transactionParameterId);

		if (transactionParameter == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					transactionParameterId);
			}

			throw new NoSuchTransactionParameterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				transactionParameterId);
		}

		return transactionParameter;
	}

	/**
	 * Returns the transaction parameter with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the transaction parameter
	 * @return the transaction parameter, or <code>null</code> if a transaction parameter with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TransactionParameter fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the transaction parameter with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param transactionParameterId the primary key of the transaction parameter
	 * @return the transaction parameter, or <code>null</code> if a transaction parameter with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TransactionParameter fetchByPrimaryKey(long transactionParameterId)
		throws SystemException {
		TransactionParameter transactionParameter = (TransactionParameter)EntityCacheUtil.getResult(TransactionParameterModelImpl.ENTITY_CACHE_ENABLED,
				TransactionParameterImpl.class, transactionParameterId);

		if (transactionParameter == _nullTransactionParameter) {
			return null;
		}

		if (transactionParameter == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				transactionParameter = (TransactionParameter)session.get(TransactionParameterImpl.class,
						Long.valueOf(transactionParameterId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (transactionParameter != null) {
					cacheResult(transactionParameter);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(TransactionParameterModelImpl.ENTITY_CACHE_ENABLED,
						TransactionParameterImpl.class, transactionParameterId,
						_nullTransactionParameter);
				}

				closeSession(session);
			}
		}

		return transactionParameter;
	}

	/**
	 * Returns all the transaction parameters where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching transaction parameters
	 * @throws SystemException if a system exception occurred
	 */
	public List<TransactionParameter> findByUuid(String uuid)
		throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the transaction parameters where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of transaction parameters
	 * @param end the upper bound of the range of transaction parameters (not inclusive)
	 * @return the range of matching transaction parameters
	 * @throws SystemException if a system exception occurred
	 */
	public List<TransactionParameter> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the transaction parameters where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of transaction parameters
	 * @param end the upper bound of the range of transaction parameters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching transaction parameters
	 * @throws SystemException if a system exception occurred
	 */
	public List<TransactionParameter> findByUuid(String uuid, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
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

		List<TransactionParameter> list = (List<TransactionParameter>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TransactionParameter transactionParameter : list) {
				if (!Validator.equals(uuid, transactionParameter.getUuid())) {
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

			query.append(_SQL_SELECT_TRANSACTIONPARAMETER_WHERE);

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
				query.append(TransactionParameterModelImpl.ORDER_BY_JPQL);
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

				list = (List<TransactionParameter>)QueryUtil.list(q,
						getDialect(), start, end);
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
	 * Returns the first transaction parameter in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching transaction parameter
	 * @throws com.beorn.onlinepayment.NoSuchTransactionParameterException if a matching transaction parameter could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TransactionParameter findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchTransactionParameterException, SystemException {
		TransactionParameter transactionParameter = fetchByUuid_First(uuid,
				orderByComparator);

		if (transactionParameter != null) {
			return transactionParameter;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTransactionParameterException(msg.toString());
	}

	/**
	 * Returns the first transaction parameter in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching transaction parameter, or <code>null</code> if a matching transaction parameter could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TransactionParameter fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<TransactionParameter> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last transaction parameter in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching transaction parameter
	 * @throws com.beorn.onlinepayment.NoSuchTransactionParameterException if a matching transaction parameter could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TransactionParameter findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchTransactionParameterException, SystemException {
		TransactionParameter transactionParameter = fetchByUuid_Last(uuid,
				orderByComparator);

		if (transactionParameter != null) {
			return transactionParameter;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTransactionParameterException(msg.toString());
	}

	/**
	 * Returns the last transaction parameter in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching transaction parameter, or <code>null</code> if a matching transaction parameter could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TransactionParameter fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		List<TransactionParameter> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the transaction parameters before and after the current transaction parameter in the ordered set where uuid = &#63;.
	 *
	 * @param transactionParameterId the primary key of the current transaction parameter
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next transaction parameter
	 * @throws com.beorn.onlinepayment.NoSuchTransactionParameterException if a transaction parameter with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TransactionParameter[] findByUuid_PrevAndNext(
		long transactionParameterId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchTransactionParameterException, SystemException {
		TransactionParameter transactionParameter = findByPrimaryKey(transactionParameterId);

		Session session = null;

		try {
			session = openSession();

			TransactionParameter[] array = new TransactionParameterImpl[3];

			array[0] = getByUuid_PrevAndNext(session, transactionParameter,
					uuid, orderByComparator, true);

			array[1] = transactionParameter;

			array[2] = getByUuid_PrevAndNext(session, transactionParameter,
					uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TransactionParameter getByUuid_PrevAndNext(Session session,
		TransactionParameter transactionParameter, String uuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TRANSACTIONPARAMETER_WHERE);

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
			query.append(TransactionParameterModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(transactionParameter);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TransactionParameter> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the transaction parameter where uuid = &#63; and groupId = &#63; or throws a {@link com.beorn.onlinepayment.NoSuchTransactionParameterException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching transaction parameter
	 * @throws com.beorn.onlinepayment.NoSuchTransactionParameterException if a matching transaction parameter could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TransactionParameter findByUUID_G(String uuid, long groupId)
		throws NoSuchTransactionParameterException, SystemException {
		TransactionParameter transactionParameter = fetchByUUID_G(uuid, groupId);

		if (transactionParameter == null) {
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

			throw new NoSuchTransactionParameterException(msg.toString());
		}

		return transactionParameter;
	}

	/**
	 * Returns the transaction parameter where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching transaction parameter, or <code>null</code> if a matching transaction parameter could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TransactionParameter fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the transaction parameter where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching transaction parameter, or <code>null</code> if a matching transaction parameter could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TransactionParameter fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof TransactionParameter) {
			TransactionParameter transactionParameter = (TransactionParameter)result;

			if (!Validator.equals(uuid, transactionParameter.getUuid()) ||
					(groupId != transactionParameter.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_TRANSACTIONPARAMETER_WHERE);

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

			query.append(TransactionParameterModelImpl.ORDER_BY_JPQL);

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

				List<TransactionParameter> list = q.list();

				result = list;

				TransactionParameter transactionParameter = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					transactionParameter = list.get(0);

					cacheResult(transactionParameter);

					if ((transactionParameter.getUuid() == null) ||
							!transactionParameter.getUuid().equals(uuid) ||
							(transactionParameter.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, transactionParameter);
					}
				}

				return transactionParameter;
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
				return (TransactionParameter)result;
			}
		}
	}

	/**
	 * Returns the transaction parameter where transactionId = &#63; and key = &#63; or throws a {@link com.beorn.onlinepayment.NoSuchTransactionParameterException} if it could not be found.
	 *
	 * @param transactionId the transaction ID
	 * @param key the key
	 * @return the matching transaction parameter
	 * @throws com.beorn.onlinepayment.NoSuchTransactionParameterException if a matching transaction parameter could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TransactionParameter findByTransactionIdAndKey(long transactionId,
		String key) throws NoSuchTransactionParameterException, SystemException {
		TransactionParameter transactionParameter = fetchByTransactionIdAndKey(transactionId,
				key);

		if (transactionParameter == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("transactionId=");
			msg.append(transactionId);

			msg.append(", key=");
			msg.append(key);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchTransactionParameterException(msg.toString());
		}

		return transactionParameter;
	}

	/**
	 * Returns the transaction parameter where transactionId = &#63; and key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param transactionId the transaction ID
	 * @param key the key
	 * @return the matching transaction parameter, or <code>null</code> if a matching transaction parameter could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TransactionParameter fetchByTransactionIdAndKey(long transactionId,
		String key) throws SystemException {
		return fetchByTransactionIdAndKey(transactionId, key, true);
	}

	/**
	 * Returns the transaction parameter where transactionId = &#63; and key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param transactionId the transaction ID
	 * @param key the key
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching transaction parameter, or <code>null</code> if a matching transaction parameter could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TransactionParameter fetchByTransactionIdAndKey(long transactionId,
		String key, boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { transactionId, key };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_TRANSACTIONIDANDKEY,
					finderArgs, this);
		}

		if (result instanceof TransactionParameter) {
			TransactionParameter transactionParameter = (TransactionParameter)result;

			if ((transactionId != transactionParameter.getTransactionId()) ||
					!Validator.equals(key, transactionParameter.getKey())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_TRANSACTIONPARAMETER_WHERE);

			query.append(_FINDER_COLUMN_TRANSACTIONIDANDKEY_TRANSACTIONID_2);

			if (key == null) {
				query.append(_FINDER_COLUMN_TRANSACTIONIDANDKEY_KEY_1);
			}
			else {
				if (key.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_TRANSACTIONIDANDKEY_KEY_3);
				}
				else {
					query.append(_FINDER_COLUMN_TRANSACTIONIDANDKEY_KEY_2);
				}
			}

			query.append(TransactionParameterModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(transactionId);

				if (key != null) {
					qPos.add(key);
				}

				List<TransactionParameter> list = q.list();

				result = list;

				TransactionParameter transactionParameter = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TRANSACTIONIDANDKEY,
						finderArgs, list);
				}
				else {
					transactionParameter = list.get(0);

					cacheResult(transactionParameter);

					if ((transactionParameter.getTransactionId() != transactionId) ||
							(transactionParameter.getKey() == null) ||
							!transactionParameter.getKey().equals(key)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TRANSACTIONIDANDKEY,
							finderArgs, transactionParameter);
					}
				}

				return transactionParameter;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_TRANSACTIONIDANDKEY,
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
				return (TransactionParameter)result;
			}
		}
	}

	/**
	 * Returns all the transaction parameters.
	 *
	 * @return the transaction parameters
	 * @throws SystemException if a system exception occurred
	 */
	public List<TransactionParameter> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the transaction parameters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of transaction parameters
	 * @param end the upper bound of the range of transaction parameters (not inclusive)
	 * @return the range of transaction parameters
	 * @throws SystemException if a system exception occurred
	 */
	public List<TransactionParameter> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the transaction parameters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of transaction parameters
	 * @param end the upper bound of the range of transaction parameters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of transaction parameters
	 * @throws SystemException if a system exception occurred
	 */
	public List<TransactionParameter> findAll(int start, int end,
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

		List<TransactionParameter> list = (List<TransactionParameter>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TRANSACTIONPARAMETER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TRANSACTIONPARAMETER.concat(TransactionParameterModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<TransactionParameter>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<TransactionParameter>)QueryUtil.list(q,
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
	 * Removes all the transaction parameters where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (TransactionParameter transactionParameter : findByUuid(uuid)) {
			remove(transactionParameter);
		}
	}

	/**
	 * Removes the transaction parameter where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the transaction parameter that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public TransactionParameter removeByUUID_G(String uuid, long groupId)
		throws NoSuchTransactionParameterException, SystemException {
		TransactionParameter transactionParameter = findByUUID_G(uuid, groupId);

		return remove(transactionParameter);
	}

	/**
	 * Removes the transaction parameter where transactionId = &#63; and key = &#63; from the database.
	 *
	 * @param transactionId the transaction ID
	 * @param key the key
	 * @return the transaction parameter that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public TransactionParameter removeByTransactionIdAndKey(
		long transactionId, String key)
		throws NoSuchTransactionParameterException, SystemException {
		TransactionParameter transactionParameter = findByTransactionIdAndKey(transactionId,
				key);

		return remove(transactionParameter);
	}

	/**
	 * Removes all the transaction parameters from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (TransactionParameter transactionParameter : findAll()) {
			remove(transactionParameter);
		}
	}

	/**
	 * Returns the number of transaction parameters where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching transaction parameters
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TRANSACTIONPARAMETER_WHERE);

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
	 * Returns the number of transaction parameters where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching transaction parameters
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUUID_G(String uuid, long groupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID_G,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TRANSACTIONPARAMETER_WHERE);

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
	 * Returns the number of transaction parameters where transactionId = &#63; and key = &#63;.
	 *
	 * @param transactionId the transaction ID
	 * @param key the key
	 * @return the number of matching transaction parameters
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTransactionIdAndKey(long transactionId, String key)
		throws SystemException {
		Object[] finderArgs = new Object[] { transactionId, key };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TRANSACTIONIDANDKEY,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TRANSACTIONPARAMETER_WHERE);

			query.append(_FINDER_COLUMN_TRANSACTIONIDANDKEY_TRANSACTIONID_2);

			if (key == null) {
				query.append(_FINDER_COLUMN_TRANSACTIONIDANDKEY_KEY_1);
			}
			else {
				if (key.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_TRANSACTIONIDANDKEY_KEY_3);
				}
				else {
					query.append(_FINDER_COLUMN_TRANSACTIONIDANDKEY_KEY_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(transactionId);

				if (key != null) {
					qPos.add(key);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TRANSACTIONIDANDKEY,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of transaction parameters.
	 *
	 * @return the number of transaction parameters
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TRANSACTIONPARAMETER);

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
	 * Initializes the transaction parameter persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.beorn.onlinepayment.model.TransactionParameter")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<TransactionParameter>> listenersList = new ArrayList<ModelListener<TransactionParameter>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<TransactionParameter>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(TransactionParameterImpl.class.getName());
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
	private static final String _SQL_SELECT_TRANSACTIONPARAMETER = "SELECT transactionParameter FROM TransactionParameter transactionParameter";
	private static final String _SQL_SELECT_TRANSACTIONPARAMETER_WHERE = "SELECT transactionParameter FROM TransactionParameter transactionParameter WHERE ";
	private static final String _SQL_COUNT_TRANSACTIONPARAMETER = "SELECT COUNT(transactionParameter) FROM TransactionParameter transactionParameter";
	private static final String _SQL_COUNT_TRANSACTIONPARAMETER_WHERE = "SELECT COUNT(transactionParameter) FROM TransactionParameter transactionParameter WHERE ";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "transactionParameter.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "transactionParameter.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(transactionParameter.uuid IS NULL OR transactionParameter.uuid = ?)";
	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "transactionParameter.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "transactionParameter.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(transactionParameter.uuid IS NULL OR transactionParameter.uuid = ?) AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "transactionParameter.groupId = ?";
	private static final String _FINDER_COLUMN_TRANSACTIONIDANDKEY_TRANSACTIONID_2 =
		"transactionParameter.transactionId = ? AND ";
	private static final String _FINDER_COLUMN_TRANSACTIONIDANDKEY_KEY_1 = "transactionParameter.key IS NULL";
	private static final String _FINDER_COLUMN_TRANSACTIONIDANDKEY_KEY_2 = "transactionParameter.key = ?";
	private static final String _FINDER_COLUMN_TRANSACTIONIDANDKEY_KEY_3 = "(transactionParameter.key IS NULL OR transactionParameter.key = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "transactionParameter.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TransactionParameter exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TransactionParameter exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(TransactionParameterPersistenceImpl.class);
	private static TransactionParameter _nullTransactionParameter = new TransactionParameterImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<TransactionParameter> toCacheModel() {
				return _nullTransactionParameterCacheModel;
			}
		};

	private static CacheModel<TransactionParameter> _nullTransactionParameterCacheModel =
		new CacheModel<TransactionParameter>() {
			public TransactionParameter toEntityModel() {
				return _nullTransactionParameter;
			}
		};
}