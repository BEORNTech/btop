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

import com.beorn.onlinepayment.NoSuchOrderTransactionException;
import com.beorn.onlinepayment.model.OrderTransaction;
import com.beorn.onlinepayment.model.impl.OrderTransactionImpl;
import com.beorn.onlinepayment.model.impl.OrderTransactionModelImpl;

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
 * The persistence implementation for the order transaction service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Sebastien Meunier
 * @see OrderTransactionPersistence
 * @see OrderTransactionUtil
 * @generated
 */
public class OrderTransactionPersistenceImpl extends BasePersistenceImpl<OrderTransaction>
	implements OrderTransactionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link OrderTransactionUtil} to access the order transaction persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = OrderTransactionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(OrderTransactionModelImpl.ENTITY_CACHE_ENABLED,
			OrderTransactionModelImpl.FINDER_CACHE_ENABLED,
			OrderTransactionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(OrderTransactionModelImpl.ENTITY_CACHE_ENABLED,
			OrderTransactionModelImpl.FINDER_CACHE_ENABLED,
			OrderTransactionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			OrderTransactionModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(OrderTransactionModelImpl.ENTITY_CACHE_ENABLED,
			OrderTransactionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_TRANSACTIONID = new FinderPath(OrderTransactionModelImpl.ENTITY_CACHE_ENABLED,
			OrderTransactionModelImpl.FINDER_CACHE_ENABLED,
			OrderTransactionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByTransactionId", new String[] { Long.class.getName() },
			OrderTransactionModelImpl.TRANSACTIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TRANSACTIONID = new FinderPath(OrderTransactionModelImpl.ENTITY_CACHE_ENABLED,
			OrderTransactionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTransactionId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(OrderTransactionModelImpl.ENTITY_CACHE_ENABLED,
			OrderTransactionModelImpl.FINDER_CACHE_ENABLED,
			OrderTransactionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(OrderTransactionModelImpl.ENTITY_CACHE_ENABLED,
			OrderTransactionModelImpl.FINDER_CACHE_ENABLED,
			OrderTransactionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OrderTransactionModelImpl.ENTITY_CACHE_ENABLED,
			OrderTransactionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the order transaction in the entity cache if it is enabled.
	 *
	 * @param orderTransaction the order transaction
	 */
	public void cacheResult(OrderTransaction orderTransaction) {
		EntityCacheUtil.putResult(OrderTransactionModelImpl.ENTITY_CACHE_ENABLED,
			OrderTransactionImpl.class, orderTransaction.getPrimaryKey(),
			orderTransaction);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TRANSACTIONID,
			new Object[] { Long.valueOf(orderTransaction.getTransactionId()) },
			orderTransaction);

		orderTransaction.resetOriginalValues();
	}

	/**
	 * Caches the order transactions in the entity cache if it is enabled.
	 *
	 * @param orderTransactions the order transactions
	 */
	public void cacheResult(List<OrderTransaction> orderTransactions) {
		for (OrderTransaction orderTransaction : orderTransactions) {
			if (EntityCacheUtil.getResult(
						OrderTransactionModelImpl.ENTITY_CACHE_ENABLED,
						OrderTransactionImpl.class,
						orderTransaction.getPrimaryKey()) == null) {
				cacheResult(orderTransaction);
			}
			else {
				orderTransaction.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all order transactions.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(OrderTransactionImpl.class.getName());
		}

		EntityCacheUtil.clearCache(OrderTransactionImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the order transaction.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(OrderTransaction orderTransaction) {
		EntityCacheUtil.removeResult(OrderTransactionModelImpl.ENTITY_CACHE_ENABLED,
			OrderTransactionImpl.class, orderTransaction.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(orderTransaction);
	}

	@Override
	public void clearCache(List<OrderTransaction> orderTransactions) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (OrderTransaction orderTransaction : orderTransactions) {
			EntityCacheUtil.removeResult(OrderTransactionModelImpl.ENTITY_CACHE_ENABLED,
				OrderTransactionImpl.class, orderTransaction.getPrimaryKey());

			clearUniqueFindersCache(orderTransaction);
		}
	}

	protected void clearUniqueFindersCache(OrderTransaction orderTransaction) {
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_TRANSACTIONID,
			new Object[] { Long.valueOf(orderTransaction.getTransactionId()) });
	}

	/**
	 * Creates a new order transaction with the primary key. Does not add the order transaction to the database.
	 *
	 * @param orderId the primary key for the new order transaction
	 * @return the new order transaction
	 */
	public OrderTransaction create(long orderId) {
		OrderTransaction orderTransaction = new OrderTransactionImpl();

		orderTransaction.setNew(true);
		orderTransaction.setPrimaryKey(orderId);

		String uuid = PortalUUIDUtil.generate();

		orderTransaction.setUuid(uuid);

		return orderTransaction;
	}

	/**
	 * Removes the order transaction with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param orderId the primary key of the order transaction
	 * @return the order transaction that was removed
	 * @throws com.beorn.onlinepayment.NoSuchOrderTransactionException if a order transaction with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OrderTransaction remove(long orderId)
		throws NoSuchOrderTransactionException, SystemException {
		return remove(Long.valueOf(orderId));
	}

	/**
	 * Removes the order transaction with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the order transaction
	 * @return the order transaction that was removed
	 * @throws com.beorn.onlinepayment.NoSuchOrderTransactionException if a order transaction with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OrderTransaction remove(Serializable primaryKey)
		throws NoSuchOrderTransactionException, SystemException {
		Session session = null;

		try {
			session = openSession();

			OrderTransaction orderTransaction = (OrderTransaction)session.get(OrderTransactionImpl.class,
					primaryKey);

			if (orderTransaction == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOrderTransactionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(orderTransaction);
		}
		catch (NoSuchOrderTransactionException nsee) {
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
	protected OrderTransaction removeImpl(OrderTransaction orderTransaction)
		throws SystemException {
		orderTransaction = toUnwrappedModel(orderTransaction);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, orderTransaction);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(orderTransaction);

		return orderTransaction;
	}

	@Override
	public OrderTransaction updateImpl(
		com.beorn.onlinepayment.model.OrderTransaction orderTransaction,
		boolean merge) throws SystemException {
		orderTransaction = toUnwrappedModel(orderTransaction);

		boolean isNew = orderTransaction.isNew();

		OrderTransactionModelImpl orderTransactionModelImpl = (OrderTransactionModelImpl)orderTransaction;

		if (Validator.isNull(orderTransaction.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			orderTransaction.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, orderTransaction, merge);

			orderTransaction.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !OrderTransactionModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((orderTransactionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						orderTransactionModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { orderTransactionModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}
		}

		EntityCacheUtil.putResult(OrderTransactionModelImpl.ENTITY_CACHE_ENABLED,
			OrderTransactionImpl.class, orderTransaction.getPrimaryKey(),
			orderTransaction);

		if (isNew) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TRANSACTIONID,
				new Object[] { Long.valueOf(orderTransaction.getTransactionId()) },
				orderTransaction);
		}
		else {
			if ((orderTransactionModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_TRANSACTIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(orderTransactionModelImpl.getOriginalTransactionId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TRANSACTIONID,
					args);

				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_TRANSACTIONID,
					args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TRANSACTIONID,
					new Object[] {
						Long.valueOf(orderTransaction.getTransactionId())
					}, orderTransaction);
			}
		}

		return orderTransaction;
	}

	protected OrderTransaction toUnwrappedModel(
		OrderTransaction orderTransaction) {
		if (orderTransaction instanceof OrderTransactionImpl) {
			return orderTransaction;
		}

		OrderTransactionImpl orderTransactionImpl = new OrderTransactionImpl();

		orderTransactionImpl.setNew(orderTransaction.isNew());
		orderTransactionImpl.setPrimaryKey(orderTransaction.getPrimaryKey());

		orderTransactionImpl.setUuid(orderTransaction.getUuid());
		orderTransactionImpl.setCreateDate(orderTransaction.getCreateDate());
		orderTransactionImpl.setModifiedDate(orderTransaction.getModifiedDate());
		orderTransactionImpl.setOrderId(orderTransaction.getOrderId());
		orderTransactionImpl.setTransactionId(orderTransaction.getTransactionId());

		return orderTransactionImpl;
	}

	/**
	 * Returns the order transaction with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the order transaction
	 * @return the order transaction
	 * @throws com.liferay.portal.NoSuchModelException if a order transaction with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OrderTransaction findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the order transaction with the primary key or throws a {@link com.beorn.onlinepayment.NoSuchOrderTransactionException} if it could not be found.
	 *
	 * @param orderId the primary key of the order transaction
	 * @return the order transaction
	 * @throws com.beorn.onlinepayment.NoSuchOrderTransactionException if a order transaction with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OrderTransaction findByPrimaryKey(long orderId)
		throws NoSuchOrderTransactionException, SystemException {
		OrderTransaction orderTransaction = fetchByPrimaryKey(orderId);

		if (orderTransaction == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + orderId);
			}

			throw new NoSuchOrderTransactionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				orderId);
		}

		return orderTransaction;
	}

	/**
	 * Returns the order transaction with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the order transaction
	 * @return the order transaction, or <code>null</code> if a order transaction with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OrderTransaction fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the order transaction with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param orderId the primary key of the order transaction
	 * @return the order transaction, or <code>null</code> if a order transaction with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OrderTransaction fetchByPrimaryKey(long orderId)
		throws SystemException {
		OrderTransaction orderTransaction = (OrderTransaction)EntityCacheUtil.getResult(OrderTransactionModelImpl.ENTITY_CACHE_ENABLED,
				OrderTransactionImpl.class, orderId);

		if (orderTransaction == _nullOrderTransaction) {
			return null;
		}

		if (orderTransaction == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				orderTransaction = (OrderTransaction)session.get(OrderTransactionImpl.class,
						Long.valueOf(orderId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (orderTransaction != null) {
					cacheResult(orderTransaction);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(OrderTransactionModelImpl.ENTITY_CACHE_ENABLED,
						OrderTransactionImpl.class, orderId,
						_nullOrderTransaction);
				}

				closeSession(session);
			}
		}

		return orderTransaction;
	}

	/**
	 * Returns all the order transactions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching order transactions
	 * @throws SystemException if a system exception occurred
	 */
	public List<OrderTransaction> findByUuid(String uuid)
		throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the order transactions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of order transactions
	 * @param end the upper bound of the range of order transactions (not inclusive)
	 * @return the range of matching order transactions
	 * @throws SystemException if a system exception occurred
	 */
	public List<OrderTransaction> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the order transactions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of order transactions
	 * @param end the upper bound of the range of order transactions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching order transactions
	 * @throws SystemException if a system exception occurred
	 */
	public List<OrderTransaction> findByUuid(String uuid, int start, int end,
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

		List<OrderTransaction> list = (List<OrderTransaction>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (OrderTransaction orderTransaction : list) {
				if (!Validator.equals(uuid, orderTransaction.getUuid())) {
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

			query.append(_SQL_SELECT_ORDERTRANSACTION_WHERE);

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
				query.append(OrderTransactionModelImpl.ORDER_BY_JPQL);
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

				list = (List<OrderTransaction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first order transaction in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching order transaction
	 * @throws com.beorn.onlinepayment.NoSuchOrderTransactionException if a matching order transaction could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OrderTransaction findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchOrderTransactionException, SystemException {
		OrderTransaction orderTransaction = fetchByUuid_First(uuid,
				orderByComparator);

		if (orderTransaction != null) {
			return orderTransaction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOrderTransactionException(msg.toString());
	}

	/**
	 * Returns the first order transaction in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching order transaction, or <code>null</code> if a matching order transaction could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OrderTransaction fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<OrderTransaction> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last order transaction in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching order transaction
	 * @throws com.beorn.onlinepayment.NoSuchOrderTransactionException if a matching order transaction could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OrderTransaction findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchOrderTransactionException, SystemException {
		OrderTransaction orderTransaction = fetchByUuid_Last(uuid,
				orderByComparator);

		if (orderTransaction != null) {
			return orderTransaction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOrderTransactionException(msg.toString());
	}

	/**
	 * Returns the last order transaction in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching order transaction, or <code>null</code> if a matching order transaction could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OrderTransaction fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		List<OrderTransaction> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the order transactions before and after the current order transaction in the ordered set where uuid = &#63;.
	 *
	 * @param orderId the primary key of the current order transaction
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next order transaction
	 * @throws com.beorn.onlinepayment.NoSuchOrderTransactionException if a order transaction with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OrderTransaction[] findByUuid_PrevAndNext(long orderId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchOrderTransactionException, SystemException {
		OrderTransaction orderTransaction = findByPrimaryKey(orderId);

		Session session = null;

		try {
			session = openSession();

			OrderTransaction[] array = new OrderTransactionImpl[3];

			array[0] = getByUuid_PrevAndNext(session, orderTransaction, uuid,
					orderByComparator, true);

			array[1] = orderTransaction;

			array[2] = getByUuid_PrevAndNext(session, orderTransaction, uuid,
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

	protected OrderTransaction getByUuid_PrevAndNext(Session session,
		OrderTransaction orderTransaction, String uuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ORDERTRANSACTION_WHERE);

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
			query.append(OrderTransactionModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(orderTransaction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OrderTransaction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the order transaction where transactionId = &#63; or throws a {@link com.beorn.onlinepayment.NoSuchOrderTransactionException} if it could not be found.
	 *
	 * @param transactionId the transaction ID
	 * @return the matching order transaction
	 * @throws com.beorn.onlinepayment.NoSuchOrderTransactionException if a matching order transaction could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OrderTransaction findByTransactionId(long transactionId)
		throws NoSuchOrderTransactionException, SystemException {
		OrderTransaction orderTransaction = fetchByTransactionId(transactionId);

		if (orderTransaction == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("transactionId=");
			msg.append(transactionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchOrderTransactionException(msg.toString());
		}

		return orderTransaction;
	}

	/**
	 * Returns the order transaction where transactionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param transactionId the transaction ID
	 * @return the matching order transaction, or <code>null</code> if a matching order transaction could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OrderTransaction fetchByTransactionId(long transactionId)
		throws SystemException {
		return fetchByTransactionId(transactionId, true);
	}

	/**
	 * Returns the order transaction where transactionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param transactionId the transaction ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching order transaction, or <code>null</code> if a matching order transaction could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OrderTransaction fetchByTransactionId(long transactionId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { transactionId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_TRANSACTIONID,
					finderArgs, this);
		}

		if (result instanceof OrderTransaction) {
			OrderTransaction orderTransaction = (OrderTransaction)result;

			if ((transactionId != orderTransaction.getTransactionId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_ORDERTRANSACTION_WHERE);

			query.append(_FINDER_COLUMN_TRANSACTIONID_TRANSACTIONID_2);

			query.append(OrderTransactionModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(transactionId);

				List<OrderTransaction> list = q.list();

				result = list;

				OrderTransaction orderTransaction = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TRANSACTIONID,
						finderArgs, list);
				}
				else {
					orderTransaction = list.get(0);

					cacheResult(orderTransaction);

					if ((orderTransaction.getTransactionId() != transactionId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TRANSACTIONID,
							finderArgs, orderTransaction);
					}
				}

				return orderTransaction;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_TRANSACTIONID,
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
				return (OrderTransaction)result;
			}
		}
	}

	/**
	 * Returns all the order transactions.
	 *
	 * @return the order transactions
	 * @throws SystemException if a system exception occurred
	 */
	public List<OrderTransaction> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the order transactions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of order transactions
	 * @param end the upper bound of the range of order transactions (not inclusive)
	 * @return the range of order transactions
	 * @throws SystemException if a system exception occurred
	 */
	public List<OrderTransaction> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the order transactions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of order transactions
	 * @param end the upper bound of the range of order transactions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of order transactions
	 * @throws SystemException if a system exception occurred
	 */
	public List<OrderTransaction> findAll(int start, int end,
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

		List<OrderTransaction> list = (List<OrderTransaction>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ORDERTRANSACTION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ORDERTRANSACTION.concat(OrderTransactionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<OrderTransaction>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<OrderTransaction>)QueryUtil.list(q,
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
	 * Removes all the order transactions where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (OrderTransaction orderTransaction : findByUuid(uuid)) {
			remove(orderTransaction);
		}
	}

	/**
	 * Removes the order transaction where transactionId = &#63; from the database.
	 *
	 * @param transactionId the transaction ID
	 * @return the order transaction that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public OrderTransaction removeByTransactionId(long transactionId)
		throws NoSuchOrderTransactionException, SystemException {
		OrderTransaction orderTransaction = findByTransactionId(transactionId);

		return remove(orderTransaction);
	}

	/**
	 * Removes all the order transactions from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (OrderTransaction orderTransaction : findAll()) {
			remove(orderTransaction);
		}
	}

	/**
	 * Returns the number of order transactions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching order transactions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ORDERTRANSACTION_WHERE);

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
	 * Returns the number of order transactions where transactionId = &#63;.
	 *
	 * @param transactionId the transaction ID
	 * @return the number of matching order transactions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTransactionId(long transactionId)
		throws SystemException {
		Object[] finderArgs = new Object[] { transactionId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TRANSACTIONID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ORDERTRANSACTION_WHERE);

			query.append(_FINDER_COLUMN_TRANSACTIONID_TRANSACTIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(transactionId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TRANSACTIONID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of order transactions.
	 *
	 * @return the number of order transactions
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ORDERTRANSACTION);

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
	 * Initializes the order transaction persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.beorn.onlinepayment.model.OrderTransaction")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<OrderTransaction>> listenersList = new ArrayList<ModelListener<OrderTransaction>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<OrderTransaction>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(OrderTransactionImpl.class.getName());
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
	private static final String _SQL_SELECT_ORDERTRANSACTION = "SELECT orderTransaction FROM OrderTransaction orderTransaction";
	private static final String _SQL_SELECT_ORDERTRANSACTION_WHERE = "SELECT orderTransaction FROM OrderTransaction orderTransaction WHERE ";
	private static final String _SQL_COUNT_ORDERTRANSACTION = "SELECT COUNT(orderTransaction) FROM OrderTransaction orderTransaction";
	private static final String _SQL_COUNT_ORDERTRANSACTION_WHERE = "SELECT COUNT(orderTransaction) FROM OrderTransaction orderTransaction WHERE ";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "orderTransaction.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "orderTransaction.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(orderTransaction.uuid IS NULL OR orderTransaction.uuid = ?)";
	private static final String _FINDER_COLUMN_TRANSACTIONID_TRANSACTIONID_2 = "orderTransaction.transactionId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "orderTransaction.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OrderTransaction exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No OrderTransaction exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(OrderTransactionPersistenceImpl.class);
	private static OrderTransaction _nullOrderTransaction = new OrderTransactionImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<OrderTransaction> toCacheModel() {
				return _nullOrderTransactionCacheModel;
			}
		};

	private static CacheModel<OrderTransaction> _nullOrderTransactionCacheModel = new CacheModel<OrderTransaction>() {
			public OrderTransaction toEntityModel() {
				return _nullOrderTransaction;
			}
		};
}