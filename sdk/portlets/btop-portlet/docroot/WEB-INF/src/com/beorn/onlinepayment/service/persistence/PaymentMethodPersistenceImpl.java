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

import com.beorn.onlinepayment.NoSuchMethodException;
import com.beorn.onlinepayment.model.PaymentMethod;
import com.beorn.onlinepayment.model.impl.PaymentMethodImpl;
import com.beorn.onlinepayment.model.impl.PaymentMethodModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQuery;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQueryFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.RowMapper;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
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
import com.liferay.portal.kernel.util.SetUtil;
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
import java.util.Set;

/**
 * The persistence implementation for the payment method service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Sebastien Meunier
 * @see PaymentMethodPersistence
 * @see PaymentMethodUtil
 * @generated
 */
public class PaymentMethodPersistenceImpl extends BasePersistenceImpl<PaymentMethod>
	implements PaymentMethodPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PaymentMethodUtil} to access the payment method persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PaymentMethodImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(PaymentMethodModelImpl.ENTITY_CACHE_ENABLED,
			PaymentMethodModelImpl.FINDER_CACHE_ENABLED,
			PaymentMethodImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(PaymentMethodModelImpl.ENTITY_CACHE_ENABLED,
			PaymentMethodModelImpl.FINDER_CACHE_ENABLED,
			PaymentMethodImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid", new String[] { String.class.getName() },
			PaymentMethodModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(PaymentMethodModelImpl.ENTITY_CACHE_ENABLED,
			PaymentMethodModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(PaymentMethodModelImpl.ENTITY_CACHE_ENABLED,
			PaymentMethodModelImpl.FINDER_CACHE_ENABLED,
			PaymentMethodImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			PaymentMethodModelImpl.UUID_COLUMN_BITMASK |
			PaymentMethodModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(PaymentMethodModelImpl.ENTITY_CACHE_ENABLED,
			PaymentMethodModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(PaymentMethodModelImpl.ENTITY_CACHE_ENABLED,
			PaymentMethodModelImpl.FINDER_CACHE_ENABLED,
			PaymentMethodImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(PaymentMethodModelImpl.ENTITY_CACHE_ENABLED,
			PaymentMethodModelImpl.FINDER_CACHE_ENABLED,
			PaymentMethodImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCompanyId", new String[] { Long.class.getName() },
			PaymentMethodModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(PaymentMethodModelImpl.ENTITY_CACHE_ENABLED,
			PaymentMethodModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_KEY = new FinderPath(PaymentMethodModelImpl.ENTITY_CACHE_ENABLED,
			PaymentMethodModelImpl.FINDER_CACHE_ENABLED,
			PaymentMethodImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByKey",
			new String[] { String.class.getName() },
			PaymentMethodModelImpl.KEY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KEY = new FinderPath(PaymentMethodModelImpl.ENTITY_CACHE_ENABLED,
			PaymentMethodModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByKey",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PaymentMethodModelImpl.ENTITY_CACHE_ENABLED,
			PaymentMethodModelImpl.FINDER_CACHE_ENABLED,
			PaymentMethodImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PaymentMethodModelImpl.ENTITY_CACHE_ENABLED,
			PaymentMethodModelImpl.FINDER_CACHE_ENABLED,
			PaymentMethodImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PaymentMethodModelImpl.ENTITY_CACHE_ENABLED,
			PaymentMethodModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the payment method in the entity cache if it is enabled.
	 *
	 * @param paymentMethod the payment method
	 */
	public void cacheResult(PaymentMethod paymentMethod) {
		EntityCacheUtil.putResult(PaymentMethodModelImpl.ENTITY_CACHE_ENABLED,
			PaymentMethodImpl.class, paymentMethod.getPrimaryKey(),
			paymentMethod);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				paymentMethod.getUuid(),
				Long.valueOf(paymentMethod.getGroupId())
			}, paymentMethod);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KEY,
			new Object[] { paymentMethod.getKey() }, paymentMethod);

		paymentMethod.resetOriginalValues();
	}

	/**
	 * Caches the payment methods in the entity cache if it is enabled.
	 *
	 * @param paymentMethods the payment methods
	 */
	public void cacheResult(List<PaymentMethod> paymentMethods) {
		for (PaymentMethod paymentMethod : paymentMethods) {
			if (EntityCacheUtil.getResult(
						PaymentMethodModelImpl.ENTITY_CACHE_ENABLED,
						PaymentMethodImpl.class, paymentMethod.getPrimaryKey()) == null) {
				cacheResult(paymentMethod);
			}
			else {
				paymentMethod.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all payment methods.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(PaymentMethodImpl.class.getName());
		}

		EntityCacheUtil.clearCache(PaymentMethodImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the payment method.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PaymentMethod paymentMethod) {
		EntityCacheUtil.removeResult(PaymentMethodModelImpl.ENTITY_CACHE_ENABLED,
			PaymentMethodImpl.class, paymentMethod.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(paymentMethod);
	}

	@Override
	public void clearCache(List<PaymentMethod> paymentMethods) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PaymentMethod paymentMethod : paymentMethods) {
			EntityCacheUtil.removeResult(PaymentMethodModelImpl.ENTITY_CACHE_ENABLED,
				PaymentMethodImpl.class, paymentMethod.getPrimaryKey());

			clearUniqueFindersCache(paymentMethod);
		}
	}

	protected void clearUniqueFindersCache(PaymentMethod paymentMethod) {
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				paymentMethod.getUuid(),
				Long.valueOf(paymentMethod.getGroupId())
			});

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KEY,
			new Object[] { paymentMethod.getKey() });
	}

	/**
	 * Creates a new payment method with the primary key. Does not add the payment method to the database.
	 *
	 * @param paymentMethodId the primary key for the new payment method
	 * @return the new payment method
	 */
	public PaymentMethod create(long paymentMethodId) {
		PaymentMethod paymentMethod = new PaymentMethodImpl();

		paymentMethod.setNew(true);
		paymentMethod.setPrimaryKey(paymentMethodId);

		String uuid = PortalUUIDUtil.generate();

		paymentMethod.setUuid(uuid);

		return paymentMethod;
	}

	/**
	 * Removes the payment method with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param paymentMethodId the primary key of the payment method
	 * @return the payment method that was removed
	 * @throws com.beorn.onlinepayment.NoSuchMethodException if a payment method with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentMethod remove(long paymentMethodId)
		throws NoSuchMethodException, SystemException {
		return remove(Long.valueOf(paymentMethodId));
	}

	/**
	 * Removes the payment method with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the payment method
	 * @return the payment method that was removed
	 * @throws com.beorn.onlinepayment.NoSuchMethodException if a payment method with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentMethod remove(Serializable primaryKey)
		throws NoSuchMethodException, SystemException {
		Session session = null;

		try {
			session = openSession();

			PaymentMethod paymentMethod = (PaymentMethod)session.get(PaymentMethodImpl.class,
					primaryKey);

			if (paymentMethod == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMethodException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(paymentMethod);
		}
		catch (NoSuchMethodException nsee) {
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
	protected PaymentMethod removeImpl(PaymentMethod paymentMethod)
		throws SystemException {
		paymentMethod = toUnwrappedModel(paymentMethod);

		try {
			clearPaymentPlugins.clear(paymentMethod.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(PaymentMethodModelImpl.MAPPING_TABLE_PAYMENT_PAYMENTPLUGIN_PAYMENTMETHOD_NAME);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, paymentMethod);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(paymentMethod);

		return paymentMethod;
	}

	@Override
	public PaymentMethod updateImpl(
		com.beorn.onlinepayment.model.PaymentMethod paymentMethod, boolean merge)
		throws SystemException {
		paymentMethod = toUnwrappedModel(paymentMethod);

		boolean isNew = paymentMethod.isNew();

		PaymentMethodModelImpl paymentMethodModelImpl = (PaymentMethodModelImpl)paymentMethod;

		if (Validator.isNull(paymentMethod.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			paymentMethod.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, paymentMethod, merge);

			paymentMethod.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !PaymentMethodModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((paymentMethodModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						paymentMethodModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { paymentMethodModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((paymentMethodModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(paymentMethodModelImpl.getOriginalCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] {
						Long.valueOf(paymentMethodModelImpl.getCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}
		}

		EntityCacheUtil.putResult(PaymentMethodModelImpl.ENTITY_CACHE_ENABLED,
			PaymentMethodImpl.class, paymentMethod.getPrimaryKey(),
			paymentMethod);

		if (isNew) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					paymentMethod.getUuid(),
					Long.valueOf(paymentMethod.getGroupId())
				}, paymentMethod);

			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KEY,
				new Object[] { paymentMethod.getKey() }, paymentMethod);
		}
		else {
			if ((paymentMethodModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						paymentMethodModelImpl.getOriginalUuid(),
						Long.valueOf(paymentMethodModelImpl.getOriginalGroupId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);

				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
					new Object[] {
						paymentMethod.getUuid(),
						Long.valueOf(paymentMethod.getGroupId())
					}, paymentMethod);
			}

			if ((paymentMethodModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_KEY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						paymentMethodModelImpl.getOriginalKey()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KEY, args);

				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KEY, args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KEY,
					new Object[] { paymentMethod.getKey() }, paymentMethod);
			}
		}

		return paymentMethod;
	}

	protected PaymentMethod toUnwrappedModel(PaymentMethod paymentMethod) {
		if (paymentMethod instanceof PaymentMethodImpl) {
			return paymentMethod;
		}

		PaymentMethodImpl paymentMethodImpl = new PaymentMethodImpl();

		paymentMethodImpl.setNew(paymentMethod.isNew());
		paymentMethodImpl.setPrimaryKey(paymentMethod.getPrimaryKey());

		paymentMethodImpl.setUuid(paymentMethod.getUuid());
		paymentMethodImpl.setCompanyId(paymentMethod.getCompanyId());
		paymentMethodImpl.setGroupId(paymentMethod.getGroupId());
		paymentMethodImpl.setUserId(paymentMethod.getUserId());
		paymentMethodImpl.setPaymentMethodId(paymentMethod.getPaymentMethodId());
		paymentMethodImpl.setCreateDate(paymentMethod.getCreateDate());
		paymentMethodImpl.setModifiedDate(paymentMethod.getModifiedDate());
		paymentMethodImpl.setKey(paymentMethod.getKey());
		paymentMethodImpl.setName(paymentMethod.getName());

		return paymentMethodImpl;
	}

	/**
	 * Returns the payment method with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the payment method
	 * @return the payment method
	 * @throws com.liferay.portal.NoSuchModelException if a payment method with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentMethod findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the payment method with the primary key or throws a {@link com.beorn.onlinepayment.NoSuchMethodException} if it could not be found.
	 *
	 * @param paymentMethodId the primary key of the payment method
	 * @return the payment method
	 * @throws com.beorn.onlinepayment.NoSuchMethodException if a payment method with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentMethod findByPrimaryKey(long paymentMethodId)
		throws NoSuchMethodException, SystemException {
		PaymentMethod paymentMethod = fetchByPrimaryKey(paymentMethodId);

		if (paymentMethod == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + paymentMethodId);
			}

			throw new NoSuchMethodException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				paymentMethodId);
		}

		return paymentMethod;
	}

	/**
	 * Returns the payment method with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the payment method
	 * @return the payment method, or <code>null</code> if a payment method with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentMethod fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the payment method with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param paymentMethodId the primary key of the payment method
	 * @return the payment method, or <code>null</code> if a payment method with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentMethod fetchByPrimaryKey(long paymentMethodId)
		throws SystemException {
		PaymentMethod paymentMethod = (PaymentMethod)EntityCacheUtil.getResult(PaymentMethodModelImpl.ENTITY_CACHE_ENABLED,
				PaymentMethodImpl.class, paymentMethodId);

		if (paymentMethod == _nullPaymentMethod) {
			return null;
		}

		if (paymentMethod == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				paymentMethod = (PaymentMethod)session.get(PaymentMethodImpl.class,
						Long.valueOf(paymentMethodId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (paymentMethod != null) {
					cacheResult(paymentMethod);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(PaymentMethodModelImpl.ENTITY_CACHE_ENABLED,
						PaymentMethodImpl.class, paymentMethodId,
						_nullPaymentMethod);
				}

				closeSession(session);
			}
		}

		return paymentMethod;
	}

	/**
	 * Returns all the payment methods where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching payment methods
	 * @throws SystemException if a system exception occurred
	 */
	public List<PaymentMethod> findByUuid(String uuid)
		throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the payment methods where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of payment methods
	 * @param end the upper bound of the range of payment methods (not inclusive)
	 * @return the range of matching payment methods
	 * @throws SystemException if a system exception occurred
	 */
	public List<PaymentMethod> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the payment methods where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of payment methods
	 * @param end the upper bound of the range of payment methods (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching payment methods
	 * @throws SystemException if a system exception occurred
	 */
	public List<PaymentMethod> findByUuid(String uuid, int start, int end,
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

		List<PaymentMethod> list = (List<PaymentMethod>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PaymentMethod paymentMethod : list) {
				if (!Validator.equals(uuid, paymentMethod.getUuid())) {
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

			query.append(_SQL_SELECT_PAYMENTMETHOD_WHERE);

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
				query.append(PaymentMethodModelImpl.ORDER_BY_JPQL);
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

				list = (List<PaymentMethod>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first payment method in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment method
	 * @throws com.beorn.onlinepayment.NoSuchMethodException if a matching payment method could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentMethod findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchMethodException, SystemException {
		PaymentMethod paymentMethod = fetchByUuid_First(uuid, orderByComparator);

		if (paymentMethod != null) {
			return paymentMethod;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMethodException(msg.toString());
	}

	/**
	 * Returns the first payment method in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment method, or <code>null</code> if a matching payment method could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentMethod fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<PaymentMethod> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last payment method in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment method
	 * @throws com.beorn.onlinepayment.NoSuchMethodException if a matching payment method could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentMethod findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchMethodException, SystemException {
		PaymentMethod paymentMethod = fetchByUuid_Last(uuid, orderByComparator);

		if (paymentMethod != null) {
			return paymentMethod;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMethodException(msg.toString());
	}

	/**
	 * Returns the last payment method in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment method, or <code>null</code> if a matching payment method could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentMethod fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		List<PaymentMethod> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the payment methods before and after the current payment method in the ordered set where uuid = &#63;.
	 *
	 * @param paymentMethodId the primary key of the current payment method
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next payment method
	 * @throws com.beorn.onlinepayment.NoSuchMethodException if a payment method with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentMethod[] findByUuid_PrevAndNext(long paymentMethodId,
		String uuid, OrderByComparator orderByComparator)
		throws NoSuchMethodException, SystemException {
		PaymentMethod paymentMethod = findByPrimaryKey(paymentMethodId);

		Session session = null;

		try {
			session = openSession();

			PaymentMethod[] array = new PaymentMethodImpl[3];

			array[0] = getByUuid_PrevAndNext(session, paymentMethod, uuid,
					orderByComparator, true);

			array[1] = paymentMethod;

			array[2] = getByUuid_PrevAndNext(session, paymentMethod, uuid,
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

	protected PaymentMethod getByUuid_PrevAndNext(Session session,
		PaymentMethod paymentMethod, String uuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PAYMENTMETHOD_WHERE);

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
			query.append(PaymentMethodModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(paymentMethod);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PaymentMethod> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the payment method where uuid = &#63; and groupId = &#63; or throws a {@link com.beorn.onlinepayment.NoSuchMethodException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching payment method
	 * @throws com.beorn.onlinepayment.NoSuchMethodException if a matching payment method could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentMethod findByUUID_G(String uuid, long groupId)
		throws NoSuchMethodException, SystemException {
		PaymentMethod paymentMethod = fetchByUUID_G(uuid, groupId);

		if (paymentMethod == null) {
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

			throw new NoSuchMethodException(msg.toString());
		}

		return paymentMethod;
	}

	/**
	 * Returns the payment method where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching payment method, or <code>null</code> if a matching payment method could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentMethod fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the payment method where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching payment method, or <code>null</code> if a matching payment method could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentMethod fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof PaymentMethod) {
			PaymentMethod paymentMethod = (PaymentMethod)result;

			if (!Validator.equals(uuid, paymentMethod.getUuid()) ||
					(groupId != paymentMethod.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_PAYMENTMETHOD_WHERE);

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

			query.append(PaymentMethodModelImpl.ORDER_BY_JPQL);

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

				List<PaymentMethod> list = q.list();

				result = list;

				PaymentMethod paymentMethod = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					paymentMethod = list.get(0);

					cacheResult(paymentMethod);

					if ((paymentMethod.getUuid() == null) ||
							!paymentMethod.getUuid().equals(uuid) ||
							(paymentMethod.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, paymentMethod);
					}
				}

				return paymentMethod;
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
				return (PaymentMethod)result;
			}
		}
	}

	/**
	 * Returns all the payment methods where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching payment methods
	 * @throws SystemException if a system exception occurred
	 */
	public List<PaymentMethod> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the payment methods where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of payment methods
	 * @param end the upper bound of the range of payment methods (not inclusive)
	 * @return the range of matching payment methods
	 * @throws SystemException if a system exception occurred
	 */
	public List<PaymentMethod> findByCompanyId(long companyId, int start,
		int end) throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the payment methods where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of payment methods
	 * @param end the upper bound of the range of payment methods (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching payment methods
	 * @throws SystemException if a system exception occurred
	 */
	public List<PaymentMethod> findByCompanyId(long companyId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId, start, end, orderByComparator };
		}

		List<PaymentMethod> list = (List<PaymentMethod>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PaymentMethod paymentMethod : list) {
				if ((companyId != paymentMethod.getCompanyId())) {
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

			query.append(_SQL_SELECT_PAYMENTMETHOD_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(PaymentMethodModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<PaymentMethod>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first payment method in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment method
	 * @throws com.beorn.onlinepayment.NoSuchMethodException if a matching payment method could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentMethod findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchMethodException, SystemException {
		PaymentMethod paymentMethod = fetchByCompanyId_First(companyId,
				orderByComparator);

		if (paymentMethod != null) {
			return paymentMethod;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMethodException(msg.toString());
	}

	/**
	 * Returns the first payment method in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment method, or <code>null</code> if a matching payment method could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentMethod fetchByCompanyId_First(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<PaymentMethod> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last payment method in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment method
	 * @throws com.beorn.onlinepayment.NoSuchMethodException if a matching payment method could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentMethod findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchMethodException, SystemException {
		PaymentMethod paymentMethod = fetchByCompanyId_Last(companyId,
				orderByComparator);

		if (paymentMethod != null) {
			return paymentMethod;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMethodException(msg.toString());
	}

	/**
	 * Returns the last payment method in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment method, or <code>null</code> if a matching payment method could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentMethod fetchByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByCompanyId(companyId);

		List<PaymentMethod> list = findByCompanyId(companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the payment methods before and after the current payment method in the ordered set where companyId = &#63;.
	 *
	 * @param paymentMethodId the primary key of the current payment method
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next payment method
	 * @throws com.beorn.onlinepayment.NoSuchMethodException if a payment method with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentMethod[] findByCompanyId_PrevAndNext(long paymentMethodId,
		long companyId, OrderByComparator orderByComparator)
		throws NoSuchMethodException, SystemException {
		PaymentMethod paymentMethod = findByPrimaryKey(paymentMethodId);

		Session session = null;

		try {
			session = openSession();

			PaymentMethod[] array = new PaymentMethodImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, paymentMethod,
					companyId, orderByComparator, true);

			array[1] = paymentMethod;

			array[2] = getByCompanyId_PrevAndNext(session, paymentMethod,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PaymentMethod getByCompanyId_PrevAndNext(Session session,
		PaymentMethod paymentMethod, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PAYMENTMETHOD_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

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
			query.append(PaymentMethodModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(paymentMethod);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PaymentMethod> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the payment method where key = &#63; or throws a {@link com.beorn.onlinepayment.NoSuchMethodException} if it could not be found.
	 *
	 * @param key the key
	 * @return the matching payment method
	 * @throws com.beorn.onlinepayment.NoSuchMethodException if a matching payment method could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentMethod findByKey(String key)
		throws NoSuchMethodException, SystemException {
		PaymentMethod paymentMethod = fetchByKey(key);

		if (paymentMethod == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("key=");
			msg.append(key);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchMethodException(msg.toString());
		}

		return paymentMethod;
	}

	/**
	 * Returns the payment method where key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param key the key
	 * @return the matching payment method, or <code>null</code> if a matching payment method could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentMethod fetchByKey(String key) throws SystemException {
		return fetchByKey(key, true);
	}

	/**
	 * Returns the payment method where key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param key the key
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching payment method, or <code>null</code> if a matching payment method could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentMethod fetchByKey(String key, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { key };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_KEY,
					finderArgs, this);
		}

		if (result instanceof PaymentMethod) {
			PaymentMethod paymentMethod = (PaymentMethod)result;

			if (!Validator.equals(key, paymentMethod.getKey())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_PAYMENTMETHOD_WHERE);

			if (key == null) {
				query.append(_FINDER_COLUMN_KEY_KEY_1);
			}
			else {
				if (key.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_KEY_KEY_3);
				}
				else {
					query.append(_FINDER_COLUMN_KEY_KEY_2);
				}
			}

			query.append(PaymentMethodModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (key != null) {
					qPos.add(key);
				}

				List<PaymentMethod> list = q.list();

				result = list;

				PaymentMethod paymentMethod = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KEY,
						finderArgs, list);
				}
				else {
					paymentMethod = list.get(0);

					cacheResult(paymentMethod);

					if ((paymentMethod.getKey() == null) ||
							!paymentMethod.getKey().equals(key)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KEY,
							finderArgs, paymentMethod);
					}
				}

				return paymentMethod;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KEY,
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
				return (PaymentMethod)result;
			}
		}
	}

	/**
	 * Returns all the payment methods.
	 *
	 * @return the payment methods
	 * @throws SystemException if a system exception occurred
	 */
	public List<PaymentMethod> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the payment methods.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of payment methods
	 * @param end the upper bound of the range of payment methods (not inclusive)
	 * @return the range of payment methods
	 * @throws SystemException if a system exception occurred
	 */
	public List<PaymentMethod> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the payment methods.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of payment methods
	 * @param end the upper bound of the range of payment methods (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of payment methods
	 * @throws SystemException if a system exception occurred
	 */
	public List<PaymentMethod> findAll(int start, int end,
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

		List<PaymentMethod> list = (List<PaymentMethod>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_PAYMENTMETHOD);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PAYMENTMETHOD.concat(PaymentMethodModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<PaymentMethod>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<PaymentMethod>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the payment methods where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (PaymentMethod paymentMethod : findByUuid(uuid)) {
			remove(paymentMethod);
		}
	}

	/**
	 * Removes the payment method where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the payment method that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentMethod removeByUUID_G(String uuid, long groupId)
		throws NoSuchMethodException, SystemException {
		PaymentMethod paymentMethod = findByUUID_G(uuid, groupId);

		return remove(paymentMethod);
	}

	/**
	 * Removes all the payment methods where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCompanyId(long companyId) throws SystemException {
		for (PaymentMethod paymentMethod : findByCompanyId(companyId)) {
			remove(paymentMethod);
		}
	}

	/**
	 * Removes the payment method where key = &#63; from the database.
	 *
	 * @param key the key
	 * @return the payment method that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentMethod removeByKey(String key)
		throws NoSuchMethodException, SystemException {
		PaymentMethod paymentMethod = findByKey(key);

		return remove(paymentMethod);
	}

	/**
	 * Removes all the payment methods from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (PaymentMethod paymentMethod : findAll()) {
			remove(paymentMethod);
		}
	}

	/**
	 * Returns the number of payment methods where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching payment methods
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PAYMENTMETHOD_WHERE);

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
	 * Returns the number of payment methods where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching payment methods
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUUID_G(String uuid, long groupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID_G,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PAYMENTMETHOD_WHERE);

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
	 * Returns the number of payment methods where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching payment methods
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PAYMENTMETHOD_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COMPANYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of payment methods where key = &#63;.
	 *
	 * @param key the key
	 * @return the number of matching payment methods
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKey(String key) throws SystemException {
		Object[] finderArgs = new Object[] { key };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KEY,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PAYMENTMETHOD_WHERE);

			if (key == null) {
				query.append(_FINDER_COLUMN_KEY_KEY_1);
			}
			else {
				if (key.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_KEY_KEY_3);
				}
				else {
					query.append(_FINDER_COLUMN_KEY_KEY_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KEY, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of payment methods.
	 *
	 * @return the number of payment methods
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PAYMENTMETHOD);

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
	 * Returns all the payment plugins associated with the payment method.
	 *
	 * @param pk the primary key of the payment method
	 * @return the payment plugins associated with the payment method
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.beorn.onlinepayment.model.PaymentPlugin> getPaymentPlugins(
		long pk) throws SystemException {
		return getPaymentPlugins(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the payment plugins associated with the payment method.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the payment method
	 * @param start the lower bound of the range of payment methods
	 * @param end the upper bound of the range of payment methods (not inclusive)
	 * @return the range of payment plugins associated with the payment method
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.beorn.onlinepayment.model.PaymentPlugin> getPaymentPlugins(
		long pk, int start, int end) throws SystemException {
		return getPaymentPlugins(pk, start, end, null);
	}

	public static final FinderPath FINDER_PATH_GET_PAYMENTPLUGINS = new FinderPath(com.beorn.onlinepayment.model.impl.PaymentPluginModelImpl.ENTITY_CACHE_ENABLED,
			PaymentMethodModelImpl.FINDER_CACHE_ENABLED_PAYMENT_PAYMENTPLUGIN_PAYMENTMETHOD,
			com.beorn.onlinepayment.model.impl.PaymentPluginImpl.class,
			PaymentMethodModelImpl.MAPPING_TABLE_PAYMENT_PAYMENTPLUGIN_PAYMENTMETHOD_NAME,
			"getPaymentPlugins",
			new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});

	static {
		FINDER_PATH_GET_PAYMENTPLUGINS.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns an ordered range of all the payment plugins associated with the payment method.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the payment method
	 * @param start the lower bound of the range of payment methods
	 * @param end the upper bound of the range of payment methods (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of payment plugins associated with the payment method
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.beorn.onlinepayment.model.PaymentPlugin> getPaymentPlugins(
		long pk, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, start, end, orderByComparator };

		List<com.beorn.onlinepayment.model.PaymentPlugin> list = (List<com.beorn.onlinepayment.model.PaymentPlugin>)FinderCacheUtil.getResult(FINDER_PATH_GET_PAYMENTPLUGINS,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				String sql = null;

				if (orderByComparator != null) {
					sql = _SQL_GETPAYMENTPLUGINS.concat(ORDER_BY_CLAUSE)
												.concat(orderByComparator.getOrderBy());
				}
				else {
					sql = _SQL_GETPAYMENTPLUGINS.concat(com.beorn.onlinepayment.model.impl.PaymentPluginModelImpl.ORDER_BY_SQL);
				}

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("Payment_PaymentPlugin",
					com.beorn.onlinepayment.model.impl.PaymentPluginImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				list = (List<com.beorn.onlinepayment.model.PaymentPlugin>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_GET_PAYMENTPLUGINS,
						finderArgs);
				}
				else {
					paymentPluginPersistence.cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_GET_PAYMENTPLUGINS,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	public static final FinderPath FINDER_PATH_GET_PAYMENTPLUGINS_SIZE = new FinderPath(com.beorn.onlinepayment.model.impl.PaymentPluginModelImpl.ENTITY_CACHE_ENABLED,
			PaymentMethodModelImpl.FINDER_CACHE_ENABLED_PAYMENT_PAYMENTPLUGIN_PAYMENTMETHOD,
			Long.class,
			PaymentMethodModelImpl.MAPPING_TABLE_PAYMENT_PAYMENTPLUGIN_PAYMENTMETHOD_NAME,
			"getPaymentPluginsSize", new String[] { Long.class.getName() });

	static {
		FINDER_PATH_GET_PAYMENTPLUGINS_SIZE.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns the number of payment plugins associated with the payment method.
	 *
	 * @param pk the primary key of the payment method
	 * @return the number of payment plugins associated with the payment method
	 * @throws SystemException if a system exception occurred
	 */
	public int getPaymentPluginsSize(long pk) throws SystemException {
		Object[] finderArgs = new Object[] { pk };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_GET_PAYMENTPLUGINS_SIZE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETPAYMENTPLUGINSSIZE);

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

				FinderCacheUtil.putResult(FINDER_PATH_GET_PAYMENTPLUGINS_SIZE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public static final FinderPath FINDER_PATH_CONTAINS_PAYMENTPLUGIN = new FinderPath(com.beorn.onlinepayment.model.impl.PaymentPluginModelImpl.ENTITY_CACHE_ENABLED,
			PaymentMethodModelImpl.FINDER_CACHE_ENABLED_PAYMENT_PAYMENTPLUGIN_PAYMENTMETHOD,
			Boolean.class,
			PaymentMethodModelImpl.MAPPING_TABLE_PAYMENT_PAYMENTPLUGIN_PAYMENTMETHOD_NAME,
			"containsPaymentPlugin",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns <code>true</code> if the payment plugin is associated with the payment method.
	 *
	 * @param pk the primary key of the payment method
	 * @param paymentPluginPK the primary key of the payment plugin
	 * @return <code>true</code> if the payment plugin is associated with the payment method; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsPaymentPlugin(long pk, long paymentPluginPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, paymentPluginPK };

		Boolean value = (Boolean)FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_PAYMENTPLUGIN,
				finderArgs, this);

		if (value == null) {
			try {
				value = Boolean.valueOf(containsPaymentPlugin.contains(pk,
							paymentPluginPK));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (value == null) {
					value = Boolean.FALSE;
				}

				FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_PAYMENTPLUGIN,
					finderArgs, value);
			}
		}

		return value.booleanValue();
	}

	/**
	 * Returns <code>true</code> if the payment method has any payment plugins associated with it.
	 *
	 * @param pk the primary key of the payment method to check for associations with payment plugins
	 * @return <code>true</code> if the payment method has any payment plugins associated with it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsPaymentPlugins(long pk) throws SystemException {
		if (getPaymentPluginsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the payment method and the payment plugin. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the payment method
	 * @param paymentPluginPK the primary key of the payment plugin
	 * @throws SystemException if a system exception occurred
	 */
	public void addPaymentPlugin(long pk, long paymentPluginPK)
		throws SystemException {
		try {
			addPaymentPlugin.add(pk, paymentPluginPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(PaymentMethodModelImpl.MAPPING_TABLE_PAYMENT_PAYMENTPLUGIN_PAYMENTMETHOD_NAME);
		}
	}

	/**
	 * Adds an association between the payment method and the payment plugin. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the payment method
	 * @param paymentPlugin the payment plugin
	 * @throws SystemException if a system exception occurred
	 */
	public void addPaymentPlugin(long pk,
		com.beorn.onlinepayment.model.PaymentPlugin paymentPlugin)
		throws SystemException {
		try {
			addPaymentPlugin.add(pk, paymentPlugin.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(PaymentMethodModelImpl.MAPPING_TABLE_PAYMENT_PAYMENTPLUGIN_PAYMENTMETHOD_NAME);
		}
	}

	/**
	 * Adds an association between the payment method and the payment plugins. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the payment method
	 * @param paymentPluginPKs the primary keys of the payment plugins
	 * @throws SystemException if a system exception occurred
	 */
	public void addPaymentPlugins(long pk, long[] paymentPluginPKs)
		throws SystemException {
		try {
			for (long paymentPluginPK : paymentPluginPKs) {
				addPaymentPlugin.add(pk, paymentPluginPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(PaymentMethodModelImpl.MAPPING_TABLE_PAYMENT_PAYMENTPLUGIN_PAYMENTMETHOD_NAME);
		}
	}

	/**
	 * Adds an association between the payment method and the payment plugins. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the payment method
	 * @param paymentPlugins the payment plugins
	 * @throws SystemException if a system exception occurred
	 */
	public void addPaymentPlugins(long pk,
		List<com.beorn.onlinepayment.model.PaymentPlugin> paymentPlugins)
		throws SystemException {
		try {
			for (com.beorn.onlinepayment.model.PaymentPlugin paymentPlugin : paymentPlugins) {
				addPaymentPlugin.add(pk, paymentPlugin.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(PaymentMethodModelImpl.MAPPING_TABLE_PAYMENT_PAYMENTPLUGIN_PAYMENTMETHOD_NAME);
		}
	}

	/**
	 * Clears all associations between the payment method and its payment plugins. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the payment method to clear the associated payment plugins from
	 * @throws SystemException if a system exception occurred
	 */
	public void clearPaymentPlugins(long pk) throws SystemException {
		try {
			clearPaymentPlugins.clear(pk);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(PaymentMethodModelImpl.MAPPING_TABLE_PAYMENT_PAYMENTPLUGIN_PAYMENTMETHOD_NAME);
		}
	}

	/**
	 * Removes the association between the payment method and the payment plugin. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the payment method
	 * @param paymentPluginPK the primary key of the payment plugin
	 * @throws SystemException if a system exception occurred
	 */
	public void removePaymentPlugin(long pk, long paymentPluginPK)
		throws SystemException {
		try {
			removePaymentPlugin.remove(pk, paymentPluginPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(PaymentMethodModelImpl.MAPPING_TABLE_PAYMENT_PAYMENTPLUGIN_PAYMENTMETHOD_NAME);
		}
	}

	/**
	 * Removes the association between the payment method and the payment plugin. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the payment method
	 * @param paymentPlugin the payment plugin
	 * @throws SystemException if a system exception occurred
	 */
	public void removePaymentPlugin(long pk,
		com.beorn.onlinepayment.model.PaymentPlugin paymentPlugin)
		throws SystemException {
		try {
			removePaymentPlugin.remove(pk, paymentPlugin.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(PaymentMethodModelImpl.MAPPING_TABLE_PAYMENT_PAYMENTPLUGIN_PAYMENTMETHOD_NAME);
		}
	}

	/**
	 * Removes the association between the payment method and the payment plugins. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the payment method
	 * @param paymentPluginPKs the primary keys of the payment plugins
	 * @throws SystemException if a system exception occurred
	 */
	public void removePaymentPlugins(long pk, long[] paymentPluginPKs)
		throws SystemException {
		try {
			for (long paymentPluginPK : paymentPluginPKs) {
				removePaymentPlugin.remove(pk, paymentPluginPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(PaymentMethodModelImpl.MAPPING_TABLE_PAYMENT_PAYMENTPLUGIN_PAYMENTMETHOD_NAME);
		}
	}

	/**
	 * Removes the association between the payment method and the payment plugins. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the payment method
	 * @param paymentPlugins the payment plugins
	 * @throws SystemException if a system exception occurred
	 */
	public void removePaymentPlugins(long pk,
		List<com.beorn.onlinepayment.model.PaymentPlugin> paymentPlugins)
		throws SystemException {
		try {
			for (com.beorn.onlinepayment.model.PaymentPlugin paymentPlugin : paymentPlugins) {
				removePaymentPlugin.remove(pk, paymentPlugin.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(PaymentMethodModelImpl.MAPPING_TABLE_PAYMENT_PAYMENTPLUGIN_PAYMENTMETHOD_NAME);
		}
	}

	/**
	 * Sets the payment plugins associated with the payment method, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the payment method
	 * @param paymentPluginPKs the primary keys of the payment plugins to be associated with the payment method
	 * @throws SystemException if a system exception occurred
	 */
	public void setPaymentPlugins(long pk, long[] paymentPluginPKs)
		throws SystemException {
		try {
			Set<Long> paymentPluginPKSet = SetUtil.fromArray(paymentPluginPKs);

			List<com.beorn.onlinepayment.model.PaymentPlugin> paymentPlugins = getPaymentPlugins(pk);

			for (com.beorn.onlinepayment.model.PaymentPlugin paymentPlugin : paymentPlugins) {
				if (!paymentPluginPKSet.remove(paymentPlugin.getPrimaryKey())) {
					removePaymentPlugin.remove(pk, paymentPlugin.getPrimaryKey());
				}
			}

			for (Long paymentPluginPK : paymentPluginPKSet) {
				addPaymentPlugin.add(pk, paymentPluginPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(PaymentMethodModelImpl.MAPPING_TABLE_PAYMENT_PAYMENTPLUGIN_PAYMENTMETHOD_NAME);
		}
	}

	/**
	 * Sets the payment plugins associated with the payment method, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the payment method
	 * @param paymentPlugins the payment plugins to be associated with the payment method
	 * @throws SystemException if a system exception occurred
	 */
	public void setPaymentPlugins(long pk,
		List<com.beorn.onlinepayment.model.PaymentPlugin> paymentPlugins)
		throws SystemException {
		try {
			long[] paymentPluginPKs = new long[paymentPlugins.size()];

			for (int i = 0; i < paymentPlugins.size(); i++) {
				com.beorn.onlinepayment.model.PaymentPlugin paymentPlugin = paymentPlugins.get(i);

				paymentPluginPKs[i] = paymentPlugin.getPrimaryKey();
			}

			setPaymentPlugins(pk, paymentPluginPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(PaymentMethodModelImpl.MAPPING_TABLE_PAYMENT_PAYMENTPLUGIN_PAYMENTMETHOD_NAME);
		}
	}

	/**
	 * Initializes the payment method persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.beorn.onlinepayment.model.PaymentMethod")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<PaymentMethod>> listenersList = new ArrayList<ModelListener<PaymentMethod>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<PaymentMethod>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		containsPaymentPlugin = new ContainsPaymentPlugin();

		addPaymentPlugin = new AddPaymentPlugin();
		clearPaymentPlugins = new ClearPaymentPlugins();
		removePaymentPlugin = new RemovePaymentPlugin();
	}

	public void destroy() {
		EntityCacheUtil.removeCache(PaymentMethodImpl.class.getName());
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
	protected ContainsPaymentPlugin containsPaymentPlugin;
	protected AddPaymentPlugin addPaymentPlugin;
	protected ClearPaymentPlugins clearPaymentPlugins;
	protected RemovePaymentPlugin removePaymentPlugin;

	protected class ContainsPaymentPlugin {
		protected ContainsPaymentPlugin() {
			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSPAYMENTPLUGIN,
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
					RowMapper.COUNT);
		}

		protected boolean contains(long paymentMethodId, long paymentPluginId) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						new Long(paymentMethodId), new Long(paymentPluginId)
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

	protected class AddPaymentPlugin {
		protected AddPaymentPlugin() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"INSERT INTO Payment_PaymentPlugin_PaymentMethod (paymentMethodId, paymentPluginId) VALUES (?, ?)",
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT });
		}

		protected void add(long paymentMethodId, long paymentPluginId)
			throws SystemException {
			if (!containsPaymentPlugin.contains(paymentMethodId, paymentPluginId)) {
				ModelListener<com.beorn.onlinepayment.model.PaymentPlugin>[] paymentPluginListeners =
					paymentPluginPersistence.getListeners();

				for (ModelListener<PaymentMethod> listener : listeners) {
					listener.onBeforeAddAssociation(paymentMethodId,
						com.beorn.onlinepayment.model.PaymentPlugin.class.getName(),
						paymentPluginId);
				}

				for (ModelListener<com.beorn.onlinepayment.model.PaymentPlugin> listener : paymentPluginListeners) {
					listener.onBeforeAddAssociation(paymentPluginId,
						PaymentMethod.class.getName(), paymentMethodId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(paymentMethodId), new Long(paymentPluginId)
					});

				for (ModelListener<PaymentMethod> listener : listeners) {
					listener.onAfterAddAssociation(paymentMethodId,
						com.beorn.onlinepayment.model.PaymentPlugin.class.getName(),
						paymentPluginId);
				}

				for (ModelListener<com.beorn.onlinepayment.model.PaymentPlugin> listener : paymentPluginListeners) {
					listener.onAfterAddAssociation(paymentPluginId,
						PaymentMethod.class.getName(), paymentMethodId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class ClearPaymentPlugins {
		protected ClearPaymentPlugins() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM Payment_PaymentPlugin_PaymentMethod WHERE paymentMethodId = ?",
					new int[] { java.sql.Types.BIGINT });
		}

		protected void clear(long paymentMethodId) throws SystemException {
			ModelListener<com.beorn.onlinepayment.model.PaymentPlugin>[] paymentPluginListeners =
				paymentPluginPersistence.getListeners();

			List<com.beorn.onlinepayment.model.PaymentPlugin> paymentPlugins = null;

			if ((listeners.length > 0) || (paymentPluginListeners.length > 0)) {
				paymentPlugins = getPaymentPlugins(paymentMethodId);

				for (com.beorn.onlinepayment.model.PaymentPlugin paymentPlugin : paymentPlugins) {
					for (ModelListener<PaymentMethod> listener : listeners) {
						listener.onBeforeRemoveAssociation(paymentMethodId,
							com.beorn.onlinepayment.model.PaymentPlugin.class.getName(),
							paymentPlugin.getPrimaryKey());
					}

					for (ModelListener<com.beorn.onlinepayment.model.PaymentPlugin> listener : paymentPluginListeners) {
						listener.onBeforeRemoveAssociation(paymentPlugin.getPrimaryKey(),
							PaymentMethod.class.getName(), paymentMethodId);
					}
				}
			}

			_sqlUpdate.update(new Object[] { new Long(paymentMethodId) });

			if ((listeners.length > 0) || (paymentPluginListeners.length > 0)) {
				for (com.beorn.onlinepayment.model.PaymentPlugin paymentPlugin : paymentPlugins) {
					for (ModelListener<PaymentMethod> listener : listeners) {
						listener.onAfterRemoveAssociation(paymentMethodId,
							com.beorn.onlinepayment.model.PaymentPlugin.class.getName(),
							paymentPlugin.getPrimaryKey());
					}

					for (ModelListener<com.beorn.onlinepayment.model.PaymentPlugin> listener : paymentPluginListeners) {
						listener.onAfterRemoveAssociation(paymentPlugin.getPrimaryKey(),
							PaymentMethod.class.getName(), paymentMethodId);
					}
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class RemovePaymentPlugin {
		protected RemovePaymentPlugin() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM Payment_PaymentPlugin_PaymentMethod WHERE paymentMethodId = ? AND paymentPluginId = ?",
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT });
		}

		protected void remove(long paymentMethodId, long paymentPluginId)
			throws SystemException {
			if (containsPaymentPlugin.contains(paymentMethodId, paymentPluginId)) {
				ModelListener<com.beorn.onlinepayment.model.PaymentPlugin>[] paymentPluginListeners =
					paymentPluginPersistence.getListeners();

				for (ModelListener<PaymentMethod> listener : listeners) {
					listener.onBeforeRemoveAssociation(paymentMethodId,
						com.beorn.onlinepayment.model.PaymentPlugin.class.getName(),
						paymentPluginId);
				}

				for (ModelListener<com.beorn.onlinepayment.model.PaymentPlugin> listener : paymentPluginListeners) {
					listener.onBeforeRemoveAssociation(paymentPluginId,
						PaymentMethod.class.getName(), paymentMethodId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(paymentMethodId), new Long(paymentPluginId)
					});

				for (ModelListener<PaymentMethod> listener : listeners) {
					listener.onAfterRemoveAssociation(paymentMethodId,
						com.beorn.onlinepayment.model.PaymentPlugin.class.getName(),
						paymentPluginId);
				}

				for (ModelListener<com.beorn.onlinepayment.model.PaymentPlugin> listener : paymentPluginListeners) {
					listener.onAfterRemoveAssociation(paymentPluginId,
						PaymentMethod.class.getName(), paymentMethodId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	private static final String _SQL_SELECT_PAYMENTMETHOD = "SELECT paymentMethod FROM PaymentMethod paymentMethod";
	private static final String _SQL_SELECT_PAYMENTMETHOD_WHERE = "SELECT paymentMethod FROM PaymentMethod paymentMethod WHERE ";
	private static final String _SQL_COUNT_PAYMENTMETHOD = "SELECT COUNT(paymentMethod) FROM PaymentMethod paymentMethod";
	private static final String _SQL_COUNT_PAYMENTMETHOD_WHERE = "SELECT COUNT(paymentMethod) FROM PaymentMethod paymentMethod WHERE ";
	private static final String _SQL_GETPAYMENTPLUGINS = "SELECT {Payment_PaymentPlugin.*} FROM Payment_PaymentPlugin INNER JOIN Payment_PaymentPlugin_PaymentMethod ON (Payment_PaymentPlugin_PaymentMethod.paymentPluginId = Payment_PaymentPlugin.paymentPluginId) WHERE (Payment_PaymentPlugin_PaymentMethod.paymentMethodId = ?)";
	private static final String _SQL_GETPAYMENTPLUGINSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM Payment_PaymentPlugin_PaymentMethod WHERE paymentMethodId = ?";
	private static final String _SQL_CONTAINSPAYMENTPLUGIN = "SELECT COUNT(*) AS COUNT_VALUE FROM Payment_PaymentPlugin_PaymentMethod WHERE paymentMethodId = ? AND paymentPluginId = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "paymentMethod.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "paymentMethod.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(paymentMethod.uuid IS NULL OR paymentMethod.uuid = ?)";
	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "paymentMethod.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "paymentMethod.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(paymentMethod.uuid IS NULL OR paymentMethod.uuid = ?) AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "paymentMethod.groupId = ?";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "paymentMethod.companyId = ?";
	private static final String _FINDER_COLUMN_KEY_KEY_1 = "paymentMethod.key IS NULL";
	private static final String _FINDER_COLUMN_KEY_KEY_2 = "paymentMethod.key = ?";
	private static final String _FINDER_COLUMN_KEY_KEY_3 = "(paymentMethod.key IS NULL OR paymentMethod.key = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "paymentMethod.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PaymentMethod exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PaymentMethod exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(PaymentMethodPersistenceImpl.class);
	private static PaymentMethod _nullPaymentMethod = new PaymentMethodImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<PaymentMethod> toCacheModel() {
				return _nullPaymentMethodCacheModel;
			}
		};

	private static CacheModel<PaymentMethod> _nullPaymentMethodCacheModel = new CacheModel<PaymentMethod>() {
			public PaymentMethod toEntityModel() {
				return _nullPaymentMethod;
			}
		};
}