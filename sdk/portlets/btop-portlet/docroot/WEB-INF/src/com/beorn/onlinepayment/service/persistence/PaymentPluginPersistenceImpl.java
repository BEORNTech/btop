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

import com.beorn.onlinepayment.NoSuchPluginException;
import com.beorn.onlinepayment.model.PaymentPlugin;
import com.beorn.onlinepayment.model.impl.PaymentPluginImpl;
import com.beorn.onlinepayment.model.impl.PaymentPluginModelImpl;

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
 * The persistence implementation for the payment plugin service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Sebastien Meunier
 * @see PaymentPluginPersistence
 * @see PaymentPluginUtil
 * @generated
 */
public class PaymentPluginPersistenceImpl extends BasePersistenceImpl<PaymentPlugin>
	implements PaymentPluginPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PaymentPluginUtil} to access the payment plugin persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PaymentPluginImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(PaymentPluginModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginModelImpl.FINDER_CACHE_ENABLED,
			PaymentPluginImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(PaymentPluginModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginModelImpl.FINDER_CACHE_ENABLED,
			PaymentPluginImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid", new String[] { String.class.getName() },
			PaymentPluginModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(PaymentPluginModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(PaymentPluginModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginModelImpl.FINDER_CACHE_ENABLED,
			PaymentPluginImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			PaymentPluginModelImpl.UUID_COLUMN_BITMASK |
			PaymentPluginModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(PaymentPluginModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(PaymentPluginModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginModelImpl.FINDER_CACHE_ENABLED,
			PaymentPluginImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(PaymentPluginModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginModelImpl.FINDER_CACHE_ENABLED,
			PaymentPluginImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCompanyId", new String[] { Long.class.getName() },
			PaymentPluginModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(PaymentPluginModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_APPLICATIONID = new FinderPath(PaymentPluginModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginModelImpl.FINDER_CACHE_ENABLED,
			PaymentPluginImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByApplicationId", new String[] { String.class.getName() },
			PaymentPluginModelImpl.APPLICATIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_APPLICATIONID = new FinderPath(PaymentPluginModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByApplicationId",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PaymentPluginModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginModelImpl.FINDER_CACHE_ENABLED,
			PaymentPluginImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PaymentPluginModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginModelImpl.FINDER_CACHE_ENABLED,
			PaymentPluginImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PaymentPluginModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the payment plugin in the entity cache if it is enabled.
	 *
	 * @param paymentPlugin the payment plugin
	 */
	public void cacheResult(PaymentPlugin paymentPlugin) {
		EntityCacheUtil.putResult(PaymentPluginModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginImpl.class, paymentPlugin.getPrimaryKey(),
			paymentPlugin);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				paymentPlugin.getUuid(),
				Long.valueOf(paymentPlugin.getGroupId())
			}, paymentPlugin);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_APPLICATIONID,
			new Object[] { paymentPlugin.getApplicationId() }, paymentPlugin);

		paymentPlugin.resetOriginalValues();
	}

	/**
	 * Caches the payment plugins in the entity cache if it is enabled.
	 *
	 * @param paymentPlugins the payment plugins
	 */
	public void cacheResult(List<PaymentPlugin> paymentPlugins) {
		for (PaymentPlugin paymentPlugin : paymentPlugins) {
			if (EntityCacheUtil.getResult(
						PaymentPluginModelImpl.ENTITY_CACHE_ENABLED,
						PaymentPluginImpl.class, paymentPlugin.getPrimaryKey()) == null) {
				cacheResult(paymentPlugin);
			}
			else {
				paymentPlugin.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all payment plugins.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(PaymentPluginImpl.class.getName());
		}

		EntityCacheUtil.clearCache(PaymentPluginImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the payment plugin.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PaymentPlugin paymentPlugin) {
		EntityCacheUtil.removeResult(PaymentPluginModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginImpl.class, paymentPlugin.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(paymentPlugin);
	}

	@Override
	public void clearCache(List<PaymentPlugin> paymentPlugins) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PaymentPlugin paymentPlugin : paymentPlugins) {
			EntityCacheUtil.removeResult(PaymentPluginModelImpl.ENTITY_CACHE_ENABLED,
				PaymentPluginImpl.class, paymentPlugin.getPrimaryKey());

			clearUniqueFindersCache(paymentPlugin);
		}
	}

	protected void clearUniqueFindersCache(PaymentPlugin paymentPlugin) {
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				paymentPlugin.getUuid(),
				Long.valueOf(paymentPlugin.getGroupId())
			});

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_APPLICATIONID,
			new Object[] { paymentPlugin.getApplicationId() });
	}

	/**
	 * Creates a new payment plugin with the primary key. Does not add the payment plugin to the database.
	 *
	 * @param paymentPluginId the primary key for the new payment plugin
	 * @return the new payment plugin
	 */
	public PaymentPlugin create(long paymentPluginId) {
		PaymentPlugin paymentPlugin = new PaymentPluginImpl();

		paymentPlugin.setNew(true);
		paymentPlugin.setPrimaryKey(paymentPluginId);

		String uuid = PortalUUIDUtil.generate();

		paymentPlugin.setUuid(uuid);

		return paymentPlugin;
	}

	/**
	 * Removes the payment plugin with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param paymentPluginId the primary key of the payment plugin
	 * @return the payment plugin that was removed
	 * @throws com.beorn.onlinepayment.NoSuchPluginException if a payment plugin with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPlugin remove(long paymentPluginId)
		throws NoSuchPluginException, SystemException {
		return remove(Long.valueOf(paymentPluginId));
	}

	/**
	 * Removes the payment plugin with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the payment plugin
	 * @return the payment plugin that was removed
	 * @throws com.beorn.onlinepayment.NoSuchPluginException if a payment plugin with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentPlugin remove(Serializable primaryKey)
		throws NoSuchPluginException, SystemException {
		Session session = null;

		try {
			session = openSession();

			PaymentPlugin paymentPlugin = (PaymentPlugin)session.get(PaymentPluginImpl.class,
					primaryKey);

			if (paymentPlugin == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPluginException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(paymentPlugin);
		}
		catch (NoSuchPluginException nsee) {
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
	protected PaymentPlugin removeImpl(PaymentPlugin paymentPlugin)
		throws SystemException {
		paymentPlugin = toUnwrappedModel(paymentPlugin);

		try {
			clearPaymentMethods.clear(paymentPlugin.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(PaymentPluginModelImpl.MAPPING_TABLE_PAYMENT_PAYMENTPLUGIN_PAYMENTMETHOD_NAME);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, paymentPlugin);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(paymentPlugin);

		return paymentPlugin;
	}

	@Override
	public PaymentPlugin updateImpl(
		com.beorn.onlinepayment.model.PaymentPlugin paymentPlugin, boolean merge)
		throws SystemException {
		paymentPlugin = toUnwrappedModel(paymentPlugin);

		boolean isNew = paymentPlugin.isNew();

		PaymentPluginModelImpl paymentPluginModelImpl = (PaymentPluginModelImpl)paymentPlugin;

		if (Validator.isNull(paymentPlugin.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			paymentPlugin.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, paymentPlugin, merge);

			paymentPlugin.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !PaymentPluginModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((paymentPluginModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						paymentPluginModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { paymentPluginModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((paymentPluginModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(paymentPluginModelImpl.getOriginalCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] {
						Long.valueOf(paymentPluginModelImpl.getCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}
		}

		EntityCacheUtil.putResult(PaymentPluginModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginImpl.class, paymentPlugin.getPrimaryKey(),
			paymentPlugin);

		if (isNew) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					paymentPlugin.getUuid(),
					Long.valueOf(paymentPlugin.getGroupId())
				}, paymentPlugin);

			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_APPLICATIONID,
				new Object[] { paymentPlugin.getApplicationId() }, paymentPlugin);
		}
		else {
			if ((paymentPluginModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						paymentPluginModelImpl.getOriginalUuid(),
						Long.valueOf(paymentPluginModelImpl.getOriginalGroupId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);

				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
					new Object[] {
						paymentPlugin.getUuid(),
						Long.valueOf(paymentPlugin.getGroupId())
					}, paymentPlugin);
			}

			if ((paymentPluginModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_APPLICATIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						paymentPluginModelImpl.getOriginalApplicationId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_APPLICATIONID,
					args);

				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_APPLICATIONID,
					args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_APPLICATIONID,
					new Object[] { paymentPlugin.getApplicationId() },
					paymentPlugin);
			}
		}

		return paymentPlugin;
	}

	protected PaymentPlugin toUnwrappedModel(PaymentPlugin paymentPlugin) {
		if (paymentPlugin instanceof PaymentPluginImpl) {
			return paymentPlugin;
		}

		PaymentPluginImpl paymentPluginImpl = new PaymentPluginImpl();

		paymentPluginImpl.setNew(paymentPlugin.isNew());
		paymentPluginImpl.setPrimaryKey(paymentPlugin.getPrimaryKey());

		paymentPluginImpl.setUuid(paymentPlugin.getUuid());
		paymentPluginImpl.setCompanyId(paymentPlugin.getCompanyId());
		paymentPluginImpl.setGroupId(paymentPlugin.getGroupId());
		paymentPluginImpl.setUserId(paymentPlugin.getUserId());
		paymentPluginImpl.setPaymentPluginId(paymentPlugin.getPaymentPluginId());
		paymentPluginImpl.setCreateDate(paymentPlugin.getCreateDate());
		paymentPluginImpl.setModifiedDate(paymentPlugin.getModifiedDate());
		paymentPluginImpl.setApplicationId(paymentPlugin.getApplicationId());
		paymentPluginImpl.setName(paymentPlugin.getName());
		paymentPluginImpl.setPluginConfigParameters(paymentPlugin.getPluginConfigParameters());
		paymentPluginImpl.setSellerConfigParameters(paymentPlugin.getSellerConfigParameters());
		paymentPluginImpl.setPluginConfig(paymentPlugin.getPluginConfig());
		paymentPluginImpl.setConfigured(paymentPlugin.isConfigured());

		return paymentPluginImpl;
	}

	/**
	 * Returns the payment plugin with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the payment plugin
	 * @return the payment plugin
	 * @throws com.liferay.portal.NoSuchModelException if a payment plugin with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentPlugin findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the payment plugin with the primary key or throws a {@link com.beorn.onlinepayment.NoSuchPluginException} if it could not be found.
	 *
	 * @param paymentPluginId the primary key of the payment plugin
	 * @return the payment plugin
	 * @throws com.beorn.onlinepayment.NoSuchPluginException if a payment plugin with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPlugin findByPrimaryKey(long paymentPluginId)
		throws NoSuchPluginException, SystemException {
		PaymentPlugin paymentPlugin = fetchByPrimaryKey(paymentPluginId);

		if (paymentPlugin == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + paymentPluginId);
			}

			throw new NoSuchPluginException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				paymentPluginId);
		}

		return paymentPlugin;
	}

	/**
	 * Returns the payment plugin with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the payment plugin
	 * @return the payment plugin, or <code>null</code> if a payment plugin with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentPlugin fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the payment plugin with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param paymentPluginId the primary key of the payment plugin
	 * @return the payment plugin, or <code>null</code> if a payment plugin with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPlugin fetchByPrimaryKey(long paymentPluginId)
		throws SystemException {
		PaymentPlugin paymentPlugin = (PaymentPlugin)EntityCacheUtil.getResult(PaymentPluginModelImpl.ENTITY_CACHE_ENABLED,
				PaymentPluginImpl.class, paymentPluginId);

		if (paymentPlugin == _nullPaymentPlugin) {
			return null;
		}

		if (paymentPlugin == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				paymentPlugin = (PaymentPlugin)session.get(PaymentPluginImpl.class,
						Long.valueOf(paymentPluginId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (paymentPlugin != null) {
					cacheResult(paymentPlugin);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(PaymentPluginModelImpl.ENTITY_CACHE_ENABLED,
						PaymentPluginImpl.class, paymentPluginId,
						_nullPaymentPlugin);
				}

				closeSession(session);
			}
		}

		return paymentPlugin;
	}

	/**
	 * Returns all the payment plugins where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching payment plugins
	 * @throws SystemException if a system exception occurred
	 */
	public List<PaymentPlugin> findByUuid(String uuid)
		throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the payment plugins where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of payment plugins
	 * @param end the upper bound of the range of payment plugins (not inclusive)
	 * @return the range of matching payment plugins
	 * @throws SystemException if a system exception occurred
	 */
	public List<PaymentPlugin> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the payment plugins where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of payment plugins
	 * @param end the upper bound of the range of payment plugins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching payment plugins
	 * @throws SystemException if a system exception occurred
	 */
	public List<PaymentPlugin> findByUuid(String uuid, int start, int end,
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

		List<PaymentPlugin> list = (List<PaymentPlugin>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PaymentPlugin paymentPlugin : list) {
				if (!Validator.equals(uuid, paymentPlugin.getUuid())) {
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

			query.append(_SQL_SELECT_PAYMENTPLUGIN_WHERE);

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
				query.append(PaymentPluginModelImpl.ORDER_BY_JPQL);
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

				list = (List<PaymentPlugin>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first payment plugin in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment plugin
	 * @throws com.beorn.onlinepayment.NoSuchPluginException if a matching payment plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPlugin findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchPluginException, SystemException {
		PaymentPlugin paymentPlugin = fetchByUuid_First(uuid, orderByComparator);

		if (paymentPlugin != null) {
			return paymentPlugin;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPluginException(msg.toString());
	}

	/**
	 * Returns the first payment plugin in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment plugin, or <code>null</code> if a matching payment plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPlugin fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<PaymentPlugin> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last payment plugin in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment plugin
	 * @throws com.beorn.onlinepayment.NoSuchPluginException if a matching payment plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPlugin findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchPluginException, SystemException {
		PaymentPlugin paymentPlugin = fetchByUuid_Last(uuid, orderByComparator);

		if (paymentPlugin != null) {
			return paymentPlugin;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPluginException(msg.toString());
	}

	/**
	 * Returns the last payment plugin in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment plugin, or <code>null</code> if a matching payment plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPlugin fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		List<PaymentPlugin> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the payment plugins before and after the current payment plugin in the ordered set where uuid = &#63;.
	 *
	 * @param paymentPluginId the primary key of the current payment plugin
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next payment plugin
	 * @throws com.beorn.onlinepayment.NoSuchPluginException if a payment plugin with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPlugin[] findByUuid_PrevAndNext(long paymentPluginId,
		String uuid, OrderByComparator orderByComparator)
		throws NoSuchPluginException, SystemException {
		PaymentPlugin paymentPlugin = findByPrimaryKey(paymentPluginId);

		Session session = null;

		try {
			session = openSession();

			PaymentPlugin[] array = new PaymentPluginImpl[3];

			array[0] = getByUuid_PrevAndNext(session, paymentPlugin, uuid,
					orderByComparator, true);

			array[1] = paymentPlugin;

			array[2] = getByUuid_PrevAndNext(session, paymentPlugin, uuid,
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

	protected PaymentPlugin getByUuid_PrevAndNext(Session session,
		PaymentPlugin paymentPlugin, String uuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PAYMENTPLUGIN_WHERE);

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
			query.append(PaymentPluginModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(paymentPlugin);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PaymentPlugin> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the payment plugin where uuid = &#63; and groupId = &#63; or throws a {@link com.beorn.onlinepayment.NoSuchPluginException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching payment plugin
	 * @throws com.beorn.onlinepayment.NoSuchPluginException if a matching payment plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPlugin findByUUID_G(String uuid, long groupId)
		throws NoSuchPluginException, SystemException {
		PaymentPlugin paymentPlugin = fetchByUUID_G(uuid, groupId);

		if (paymentPlugin == null) {
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

			throw new NoSuchPluginException(msg.toString());
		}

		return paymentPlugin;
	}

	/**
	 * Returns the payment plugin where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching payment plugin, or <code>null</code> if a matching payment plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPlugin fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the payment plugin where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching payment plugin, or <code>null</code> if a matching payment plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPlugin fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof PaymentPlugin) {
			PaymentPlugin paymentPlugin = (PaymentPlugin)result;

			if (!Validator.equals(uuid, paymentPlugin.getUuid()) ||
					(groupId != paymentPlugin.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_PAYMENTPLUGIN_WHERE);

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

			query.append(PaymentPluginModelImpl.ORDER_BY_JPQL);

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

				List<PaymentPlugin> list = q.list();

				result = list;

				PaymentPlugin paymentPlugin = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					paymentPlugin = list.get(0);

					cacheResult(paymentPlugin);

					if ((paymentPlugin.getUuid() == null) ||
							!paymentPlugin.getUuid().equals(uuid) ||
							(paymentPlugin.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, paymentPlugin);
					}
				}

				return paymentPlugin;
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
				return (PaymentPlugin)result;
			}
		}
	}

	/**
	 * Returns all the payment plugins where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching payment plugins
	 * @throws SystemException if a system exception occurred
	 */
	public List<PaymentPlugin> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the payment plugins where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of payment plugins
	 * @param end the upper bound of the range of payment plugins (not inclusive)
	 * @return the range of matching payment plugins
	 * @throws SystemException if a system exception occurred
	 */
	public List<PaymentPlugin> findByCompanyId(long companyId, int start,
		int end) throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the payment plugins where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of payment plugins
	 * @param end the upper bound of the range of payment plugins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching payment plugins
	 * @throws SystemException if a system exception occurred
	 */
	public List<PaymentPlugin> findByCompanyId(long companyId, int start,
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

		List<PaymentPlugin> list = (List<PaymentPlugin>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PaymentPlugin paymentPlugin : list) {
				if ((companyId != paymentPlugin.getCompanyId())) {
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

			query.append(_SQL_SELECT_PAYMENTPLUGIN_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(PaymentPluginModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<PaymentPlugin>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first payment plugin in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment plugin
	 * @throws com.beorn.onlinepayment.NoSuchPluginException if a matching payment plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPlugin findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchPluginException, SystemException {
		PaymentPlugin paymentPlugin = fetchByCompanyId_First(companyId,
				orderByComparator);

		if (paymentPlugin != null) {
			return paymentPlugin;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPluginException(msg.toString());
	}

	/**
	 * Returns the first payment plugin in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment plugin, or <code>null</code> if a matching payment plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPlugin fetchByCompanyId_First(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<PaymentPlugin> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last payment plugin in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment plugin
	 * @throws com.beorn.onlinepayment.NoSuchPluginException if a matching payment plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPlugin findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchPluginException, SystemException {
		PaymentPlugin paymentPlugin = fetchByCompanyId_Last(companyId,
				orderByComparator);

		if (paymentPlugin != null) {
			return paymentPlugin;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPluginException(msg.toString());
	}

	/**
	 * Returns the last payment plugin in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment plugin, or <code>null</code> if a matching payment plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPlugin fetchByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByCompanyId(companyId);

		List<PaymentPlugin> list = findByCompanyId(companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the payment plugins before and after the current payment plugin in the ordered set where companyId = &#63;.
	 *
	 * @param paymentPluginId the primary key of the current payment plugin
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next payment plugin
	 * @throws com.beorn.onlinepayment.NoSuchPluginException if a payment plugin with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPlugin[] findByCompanyId_PrevAndNext(long paymentPluginId,
		long companyId, OrderByComparator orderByComparator)
		throws NoSuchPluginException, SystemException {
		PaymentPlugin paymentPlugin = findByPrimaryKey(paymentPluginId);

		Session session = null;

		try {
			session = openSession();

			PaymentPlugin[] array = new PaymentPluginImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, paymentPlugin,
					companyId, orderByComparator, true);

			array[1] = paymentPlugin;

			array[2] = getByCompanyId_PrevAndNext(session, paymentPlugin,
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

	protected PaymentPlugin getByCompanyId_PrevAndNext(Session session,
		PaymentPlugin paymentPlugin, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PAYMENTPLUGIN_WHERE);

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
			query.append(PaymentPluginModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(paymentPlugin);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PaymentPlugin> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the payment plugin where applicationId = &#63; or throws a {@link com.beorn.onlinepayment.NoSuchPluginException} if it could not be found.
	 *
	 * @param applicationId the application ID
	 * @return the matching payment plugin
	 * @throws com.beorn.onlinepayment.NoSuchPluginException if a matching payment plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPlugin findByApplicationId(String applicationId)
		throws NoSuchPluginException, SystemException {
		PaymentPlugin paymentPlugin = fetchByApplicationId(applicationId);

		if (paymentPlugin == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("applicationId=");
			msg.append(applicationId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchPluginException(msg.toString());
		}

		return paymentPlugin;
	}

	/**
	 * Returns the payment plugin where applicationId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param applicationId the application ID
	 * @return the matching payment plugin, or <code>null</code> if a matching payment plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPlugin fetchByApplicationId(String applicationId)
		throws SystemException {
		return fetchByApplicationId(applicationId, true);
	}

	/**
	 * Returns the payment plugin where applicationId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param applicationId the application ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching payment plugin, or <code>null</code> if a matching payment plugin could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPlugin fetchByApplicationId(String applicationId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { applicationId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_APPLICATIONID,
					finderArgs, this);
		}

		if (result instanceof PaymentPlugin) {
			PaymentPlugin paymentPlugin = (PaymentPlugin)result;

			if (!Validator.equals(applicationId,
						paymentPlugin.getApplicationId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_PAYMENTPLUGIN_WHERE);

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

			query.append(PaymentPluginModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (applicationId != null) {
					qPos.add(applicationId);
				}

				List<PaymentPlugin> list = q.list();

				result = list;

				PaymentPlugin paymentPlugin = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_APPLICATIONID,
						finderArgs, list);
				}
				else {
					paymentPlugin = list.get(0);

					cacheResult(paymentPlugin);

					if ((paymentPlugin.getApplicationId() == null) ||
							!paymentPlugin.getApplicationId()
											  .equals(applicationId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_APPLICATIONID,
							finderArgs, paymentPlugin);
					}
				}

				return paymentPlugin;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_APPLICATIONID,
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
				return (PaymentPlugin)result;
			}
		}
	}

	/**
	 * Returns all the payment plugins.
	 *
	 * @return the payment plugins
	 * @throws SystemException if a system exception occurred
	 */
	public List<PaymentPlugin> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the payment plugins.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of payment plugins
	 * @param end the upper bound of the range of payment plugins (not inclusive)
	 * @return the range of payment plugins
	 * @throws SystemException if a system exception occurred
	 */
	public List<PaymentPlugin> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the payment plugins.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of payment plugins
	 * @param end the upper bound of the range of payment plugins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of payment plugins
	 * @throws SystemException if a system exception occurred
	 */
	public List<PaymentPlugin> findAll(int start, int end,
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

		List<PaymentPlugin> list = (List<PaymentPlugin>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_PAYMENTPLUGIN);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PAYMENTPLUGIN.concat(PaymentPluginModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<PaymentPlugin>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<PaymentPlugin>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the payment plugins where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (PaymentPlugin paymentPlugin : findByUuid(uuid)) {
			remove(paymentPlugin);
		}
	}

	/**
	 * Removes the payment plugin where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the payment plugin that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPlugin removeByUUID_G(String uuid, long groupId)
		throws NoSuchPluginException, SystemException {
		PaymentPlugin paymentPlugin = findByUUID_G(uuid, groupId);

		return remove(paymentPlugin);
	}

	/**
	 * Removes all the payment plugins where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCompanyId(long companyId) throws SystemException {
		for (PaymentPlugin paymentPlugin : findByCompanyId(companyId)) {
			remove(paymentPlugin);
		}
	}

	/**
	 * Removes the payment plugin where applicationId = &#63; from the database.
	 *
	 * @param applicationId the application ID
	 * @return the payment plugin that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPlugin removeByApplicationId(String applicationId)
		throws NoSuchPluginException, SystemException {
		PaymentPlugin paymentPlugin = findByApplicationId(applicationId);

		return remove(paymentPlugin);
	}

	/**
	 * Removes all the payment plugins from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (PaymentPlugin paymentPlugin : findAll()) {
			remove(paymentPlugin);
		}
	}

	/**
	 * Returns the number of payment plugins where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching payment plugins
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PAYMENTPLUGIN_WHERE);

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
	 * Returns the number of payment plugins where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching payment plugins
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUUID_G(String uuid, long groupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID_G,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PAYMENTPLUGIN_WHERE);

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
	 * Returns the number of payment plugins where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching payment plugins
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PAYMENTPLUGIN_WHERE);

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
	 * Returns the number of payment plugins where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @return the number of matching payment plugins
	 * @throws SystemException if a system exception occurred
	 */
	public int countByApplicationId(String applicationId)
		throws SystemException {
		Object[] finderArgs = new Object[] { applicationId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_APPLICATIONID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PAYMENTPLUGIN_WHERE);

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
	 * Returns the number of payment plugins.
	 *
	 * @return the number of payment plugins
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PAYMENTPLUGIN);

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
	 * Returns all the payment plugin configs associated with the payment plugin.
	 *
	 * @param pk the primary key of the payment plugin
	 * @return the payment plugin configs associated with the payment plugin
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.beorn.onlinepayment.model.PaymentPluginConfig> getPaymentPluginConfigs(
		long pk) throws SystemException {
		return getPaymentPluginConfigs(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the payment plugin configs associated with the payment plugin.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the payment plugin
	 * @param start the lower bound of the range of payment plugins
	 * @param end the upper bound of the range of payment plugins (not inclusive)
	 * @return the range of payment plugin configs associated with the payment plugin
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.beorn.onlinepayment.model.PaymentPluginConfig> getPaymentPluginConfigs(
		long pk, int start, int end) throws SystemException {
		return getPaymentPluginConfigs(pk, start, end, null);
	}

	public static final FinderPath FINDER_PATH_GET_PAYMENTPLUGINCONFIGS = new FinderPath(com.beorn.onlinepayment.model.impl.PaymentPluginConfigModelImpl.ENTITY_CACHE_ENABLED,
			com.beorn.onlinepayment.model.impl.PaymentPluginConfigModelImpl.FINDER_CACHE_ENABLED,
			com.beorn.onlinepayment.model.impl.PaymentPluginConfigImpl.class,
			com.beorn.onlinepayment.service.persistence.PaymentPluginConfigPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"getPaymentPluginConfigs",
			new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});

	static {
		FINDER_PATH_GET_PAYMENTPLUGINCONFIGS.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns an ordered range of all the payment plugin configs associated with the payment plugin.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the payment plugin
	 * @param start the lower bound of the range of payment plugins
	 * @param end the upper bound of the range of payment plugins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of payment plugin configs associated with the payment plugin
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.beorn.onlinepayment.model.PaymentPluginConfig> getPaymentPluginConfigs(
		long pk, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, start, end, orderByComparator };

		List<com.beorn.onlinepayment.model.PaymentPluginConfig> list = (List<com.beorn.onlinepayment.model.PaymentPluginConfig>)FinderCacheUtil.getResult(FINDER_PATH_GET_PAYMENTPLUGINCONFIGS,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				String sql = null;

				if (orderByComparator != null) {
					sql = _SQL_GETPAYMENTPLUGINCONFIGS.concat(ORDER_BY_CLAUSE)
													  .concat(orderByComparator.getOrderBy());
				}
				else {
					sql = _SQL_GETPAYMENTPLUGINCONFIGS.concat(com.beorn.onlinepayment.model.impl.PaymentPluginConfigModelImpl.ORDER_BY_SQL);
				}

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("Payment_PaymentPluginConfig",
					com.beorn.onlinepayment.model.impl.PaymentPluginConfigImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				list = (List<com.beorn.onlinepayment.model.PaymentPluginConfig>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_GET_PAYMENTPLUGINCONFIGS,
						finderArgs);
				}
				else {
					paymentPluginConfigPersistence.cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_GET_PAYMENTPLUGINCONFIGS,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	public static final FinderPath FINDER_PATH_GET_PAYMENTPLUGINCONFIGS_SIZE = new FinderPath(com.beorn.onlinepayment.model.impl.PaymentPluginConfigModelImpl.ENTITY_CACHE_ENABLED,
			com.beorn.onlinepayment.model.impl.PaymentPluginConfigModelImpl.FINDER_CACHE_ENABLED,
			com.beorn.onlinepayment.model.impl.PaymentPluginConfigImpl.class,
			com.beorn.onlinepayment.service.persistence.PaymentPluginConfigPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"getPaymentPluginConfigsSize", new String[] { Long.class.getName() });

	static {
		FINDER_PATH_GET_PAYMENTPLUGINCONFIGS_SIZE.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns the number of payment plugin configs associated with the payment plugin.
	 *
	 * @param pk the primary key of the payment plugin
	 * @return the number of payment plugin configs associated with the payment plugin
	 * @throws SystemException if a system exception occurred
	 */
	public int getPaymentPluginConfigsSize(long pk) throws SystemException {
		Object[] finderArgs = new Object[] { pk };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_GET_PAYMENTPLUGINCONFIGS_SIZE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETPAYMENTPLUGINCONFIGSSIZE);

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

				FinderCacheUtil.putResult(FINDER_PATH_GET_PAYMENTPLUGINCONFIGS_SIZE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public static final FinderPath FINDER_PATH_CONTAINS_PAYMENTPLUGINCONFIG = new FinderPath(com.beorn.onlinepayment.model.impl.PaymentPluginConfigModelImpl.ENTITY_CACHE_ENABLED,
			com.beorn.onlinepayment.model.impl.PaymentPluginConfigModelImpl.FINDER_CACHE_ENABLED,
			com.beorn.onlinepayment.model.impl.PaymentPluginConfigImpl.class,
			com.beorn.onlinepayment.service.persistence.PaymentPluginConfigPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"containsPaymentPluginConfig",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns <code>true</code> if the payment plugin config is associated with the payment plugin.
	 *
	 * @param pk the primary key of the payment plugin
	 * @param paymentPluginConfigPK the primary key of the payment plugin config
	 * @return <code>true</code> if the payment plugin config is associated with the payment plugin; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsPaymentPluginConfig(long pk,
		long paymentPluginConfigPK) throws SystemException {
		Object[] finderArgs = new Object[] { pk, paymentPluginConfigPK };

		Boolean value = (Boolean)FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_PAYMENTPLUGINCONFIG,
				finderArgs, this);

		if (value == null) {
			try {
				value = Boolean.valueOf(containsPaymentPluginConfig.contains(
							pk, paymentPluginConfigPK));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (value == null) {
					value = Boolean.FALSE;
				}

				FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_PAYMENTPLUGINCONFIG,
					finderArgs, value);
			}
		}

		return value.booleanValue();
	}

	/**
	 * Returns <code>true</code> if the payment plugin has any payment plugin configs associated with it.
	 *
	 * @param pk the primary key of the payment plugin to check for associations with payment plugin configs
	 * @return <code>true</code> if the payment plugin has any payment plugin configs associated with it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsPaymentPluginConfigs(long pk)
		throws SystemException {
		if (getPaymentPluginConfigsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Returns all the payment methods associated with the payment plugin.
	 *
	 * @param pk the primary key of the payment plugin
	 * @return the payment methods associated with the payment plugin
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.beorn.onlinepayment.model.PaymentMethod> getPaymentMethods(
		long pk) throws SystemException {
		return getPaymentMethods(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the payment methods associated with the payment plugin.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the payment plugin
	 * @param start the lower bound of the range of payment plugins
	 * @param end the upper bound of the range of payment plugins (not inclusive)
	 * @return the range of payment methods associated with the payment plugin
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.beorn.onlinepayment.model.PaymentMethod> getPaymentMethods(
		long pk, int start, int end) throws SystemException {
		return getPaymentMethods(pk, start, end, null);
	}

	public static final FinderPath FINDER_PATH_GET_PAYMENTMETHODS = new FinderPath(com.beorn.onlinepayment.model.impl.PaymentMethodModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginModelImpl.FINDER_CACHE_ENABLED_PAYMENT_PAYMENTPLUGIN_PAYMENTMETHOD,
			com.beorn.onlinepayment.model.impl.PaymentMethodImpl.class,
			PaymentPluginModelImpl.MAPPING_TABLE_PAYMENT_PAYMENTPLUGIN_PAYMENTMETHOD_NAME,
			"getPaymentMethods",
			new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});

	static {
		FINDER_PATH_GET_PAYMENTMETHODS.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns an ordered range of all the payment methods associated with the payment plugin.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the payment plugin
	 * @param start the lower bound of the range of payment plugins
	 * @param end the upper bound of the range of payment plugins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of payment methods associated with the payment plugin
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.beorn.onlinepayment.model.PaymentMethod> getPaymentMethods(
		long pk, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, start, end, orderByComparator };

		List<com.beorn.onlinepayment.model.PaymentMethod> list = (List<com.beorn.onlinepayment.model.PaymentMethod>)FinderCacheUtil.getResult(FINDER_PATH_GET_PAYMENTMETHODS,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				String sql = null;

				if (orderByComparator != null) {
					sql = _SQL_GETPAYMENTMETHODS.concat(ORDER_BY_CLAUSE)
												.concat(orderByComparator.getOrderBy());
				}
				else {
					sql = _SQL_GETPAYMENTMETHODS.concat(com.beorn.onlinepayment.model.impl.PaymentMethodModelImpl.ORDER_BY_SQL);
				}

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("Payment_PaymentMethod",
					com.beorn.onlinepayment.model.impl.PaymentMethodImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				list = (List<com.beorn.onlinepayment.model.PaymentMethod>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_GET_PAYMENTMETHODS,
						finderArgs);
				}
				else {
					paymentMethodPersistence.cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_GET_PAYMENTMETHODS,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	public static final FinderPath FINDER_PATH_GET_PAYMENTMETHODS_SIZE = new FinderPath(com.beorn.onlinepayment.model.impl.PaymentMethodModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginModelImpl.FINDER_CACHE_ENABLED_PAYMENT_PAYMENTPLUGIN_PAYMENTMETHOD,
			Long.class,
			PaymentPluginModelImpl.MAPPING_TABLE_PAYMENT_PAYMENTPLUGIN_PAYMENTMETHOD_NAME,
			"getPaymentMethodsSize", new String[] { Long.class.getName() });

	static {
		FINDER_PATH_GET_PAYMENTMETHODS_SIZE.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns the number of payment methods associated with the payment plugin.
	 *
	 * @param pk the primary key of the payment plugin
	 * @return the number of payment methods associated with the payment plugin
	 * @throws SystemException if a system exception occurred
	 */
	public int getPaymentMethodsSize(long pk) throws SystemException {
		Object[] finderArgs = new Object[] { pk };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_GET_PAYMENTMETHODS_SIZE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETPAYMENTMETHODSSIZE);

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

				FinderCacheUtil.putResult(FINDER_PATH_GET_PAYMENTMETHODS_SIZE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public static final FinderPath FINDER_PATH_CONTAINS_PAYMENTMETHOD = new FinderPath(com.beorn.onlinepayment.model.impl.PaymentMethodModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginModelImpl.FINDER_CACHE_ENABLED_PAYMENT_PAYMENTPLUGIN_PAYMENTMETHOD,
			Boolean.class,
			PaymentPluginModelImpl.MAPPING_TABLE_PAYMENT_PAYMENTPLUGIN_PAYMENTMETHOD_NAME,
			"containsPaymentMethod",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns <code>true</code> if the payment method is associated with the payment plugin.
	 *
	 * @param pk the primary key of the payment plugin
	 * @param paymentMethodPK the primary key of the payment method
	 * @return <code>true</code> if the payment method is associated with the payment plugin; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsPaymentMethod(long pk, long paymentMethodPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, paymentMethodPK };

		Boolean value = (Boolean)FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_PAYMENTMETHOD,
				finderArgs, this);

		if (value == null) {
			try {
				value = Boolean.valueOf(containsPaymentMethod.contains(pk,
							paymentMethodPK));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (value == null) {
					value = Boolean.FALSE;
				}

				FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_PAYMENTMETHOD,
					finderArgs, value);
			}
		}

		return value.booleanValue();
	}

	/**
	 * Returns <code>true</code> if the payment plugin has any payment methods associated with it.
	 *
	 * @param pk the primary key of the payment plugin to check for associations with payment methods
	 * @return <code>true</code> if the payment plugin has any payment methods associated with it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsPaymentMethods(long pk) throws SystemException {
		if (getPaymentMethodsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the payment plugin and the payment method. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the payment plugin
	 * @param paymentMethodPK the primary key of the payment method
	 * @throws SystemException if a system exception occurred
	 */
	public void addPaymentMethod(long pk, long paymentMethodPK)
		throws SystemException {
		try {
			addPaymentMethod.add(pk, paymentMethodPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(PaymentPluginModelImpl.MAPPING_TABLE_PAYMENT_PAYMENTPLUGIN_PAYMENTMETHOD_NAME);
		}
	}

	/**
	 * Adds an association between the payment plugin and the payment method. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the payment plugin
	 * @param paymentMethod the payment method
	 * @throws SystemException if a system exception occurred
	 */
	public void addPaymentMethod(long pk,
		com.beorn.onlinepayment.model.PaymentMethod paymentMethod)
		throws SystemException {
		try {
			addPaymentMethod.add(pk, paymentMethod.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(PaymentPluginModelImpl.MAPPING_TABLE_PAYMENT_PAYMENTPLUGIN_PAYMENTMETHOD_NAME);
		}
	}

	/**
	 * Adds an association between the payment plugin and the payment methods. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the payment plugin
	 * @param paymentMethodPKs the primary keys of the payment methods
	 * @throws SystemException if a system exception occurred
	 */
	public void addPaymentMethods(long pk, long[] paymentMethodPKs)
		throws SystemException {
		try {
			for (long paymentMethodPK : paymentMethodPKs) {
				addPaymentMethod.add(pk, paymentMethodPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(PaymentPluginModelImpl.MAPPING_TABLE_PAYMENT_PAYMENTPLUGIN_PAYMENTMETHOD_NAME);
		}
	}

	/**
	 * Adds an association between the payment plugin and the payment methods. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the payment plugin
	 * @param paymentMethods the payment methods
	 * @throws SystemException if a system exception occurred
	 */
	public void addPaymentMethods(long pk,
		List<com.beorn.onlinepayment.model.PaymentMethod> paymentMethods)
		throws SystemException {
		try {
			for (com.beorn.onlinepayment.model.PaymentMethod paymentMethod : paymentMethods) {
				addPaymentMethod.add(pk, paymentMethod.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(PaymentPluginModelImpl.MAPPING_TABLE_PAYMENT_PAYMENTPLUGIN_PAYMENTMETHOD_NAME);
		}
	}

	/**
	 * Clears all associations between the payment plugin and its payment methods. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the payment plugin to clear the associated payment methods from
	 * @throws SystemException if a system exception occurred
	 */
	public void clearPaymentMethods(long pk) throws SystemException {
		try {
			clearPaymentMethods.clear(pk);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(PaymentPluginModelImpl.MAPPING_TABLE_PAYMENT_PAYMENTPLUGIN_PAYMENTMETHOD_NAME);
		}
	}

	/**
	 * Removes the association between the payment plugin and the payment method. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the payment plugin
	 * @param paymentMethodPK the primary key of the payment method
	 * @throws SystemException if a system exception occurred
	 */
	public void removePaymentMethod(long pk, long paymentMethodPK)
		throws SystemException {
		try {
			removePaymentMethod.remove(pk, paymentMethodPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(PaymentPluginModelImpl.MAPPING_TABLE_PAYMENT_PAYMENTPLUGIN_PAYMENTMETHOD_NAME);
		}
	}

	/**
	 * Removes the association between the payment plugin and the payment method. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the payment plugin
	 * @param paymentMethod the payment method
	 * @throws SystemException if a system exception occurred
	 */
	public void removePaymentMethod(long pk,
		com.beorn.onlinepayment.model.PaymentMethod paymentMethod)
		throws SystemException {
		try {
			removePaymentMethod.remove(pk, paymentMethod.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(PaymentPluginModelImpl.MAPPING_TABLE_PAYMENT_PAYMENTPLUGIN_PAYMENTMETHOD_NAME);
		}
	}

	/**
	 * Removes the association between the payment plugin and the payment methods. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the payment plugin
	 * @param paymentMethodPKs the primary keys of the payment methods
	 * @throws SystemException if a system exception occurred
	 */
	public void removePaymentMethods(long pk, long[] paymentMethodPKs)
		throws SystemException {
		try {
			for (long paymentMethodPK : paymentMethodPKs) {
				removePaymentMethod.remove(pk, paymentMethodPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(PaymentPluginModelImpl.MAPPING_TABLE_PAYMENT_PAYMENTPLUGIN_PAYMENTMETHOD_NAME);
		}
	}

	/**
	 * Removes the association between the payment plugin and the payment methods. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the payment plugin
	 * @param paymentMethods the payment methods
	 * @throws SystemException if a system exception occurred
	 */
	public void removePaymentMethods(long pk,
		List<com.beorn.onlinepayment.model.PaymentMethod> paymentMethods)
		throws SystemException {
		try {
			for (com.beorn.onlinepayment.model.PaymentMethod paymentMethod : paymentMethods) {
				removePaymentMethod.remove(pk, paymentMethod.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(PaymentPluginModelImpl.MAPPING_TABLE_PAYMENT_PAYMENTPLUGIN_PAYMENTMETHOD_NAME);
		}
	}

	/**
	 * Sets the payment methods associated with the payment plugin, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the payment plugin
	 * @param paymentMethodPKs the primary keys of the payment methods to be associated with the payment plugin
	 * @throws SystemException if a system exception occurred
	 */
	public void setPaymentMethods(long pk, long[] paymentMethodPKs)
		throws SystemException {
		try {
			Set<Long> paymentMethodPKSet = SetUtil.fromArray(paymentMethodPKs);

			List<com.beorn.onlinepayment.model.PaymentMethod> paymentMethods = getPaymentMethods(pk);

			for (com.beorn.onlinepayment.model.PaymentMethod paymentMethod : paymentMethods) {
				if (!paymentMethodPKSet.remove(paymentMethod.getPrimaryKey())) {
					removePaymentMethod.remove(pk, paymentMethod.getPrimaryKey());
				}
			}

			for (Long paymentMethodPK : paymentMethodPKSet) {
				addPaymentMethod.add(pk, paymentMethodPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(PaymentPluginModelImpl.MAPPING_TABLE_PAYMENT_PAYMENTPLUGIN_PAYMENTMETHOD_NAME);
		}
	}

	/**
	 * Sets the payment methods associated with the payment plugin, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the payment plugin
	 * @param paymentMethods the payment methods to be associated with the payment plugin
	 * @throws SystemException if a system exception occurred
	 */
	public void setPaymentMethods(long pk,
		List<com.beorn.onlinepayment.model.PaymentMethod> paymentMethods)
		throws SystemException {
		try {
			long[] paymentMethodPKs = new long[paymentMethods.size()];

			for (int i = 0; i < paymentMethods.size(); i++) {
				com.beorn.onlinepayment.model.PaymentMethod paymentMethod = paymentMethods.get(i);

				paymentMethodPKs[i] = paymentMethod.getPrimaryKey();
			}

			setPaymentMethods(pk, paymentMethodPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(PaymentPluginModelImpl.MAPPING_TABLE_PAYMENT_PAYMENTPLUGIN_PAYMENTMETHOD_NAME);
		}
	}

	/**
	 * Initializes the payment plugin persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.beorn.onlinepayment.model.PaymentPlugin")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<PaymentPlugin>> listenersList = new ArrayList<ModelListener<PaymentPlugin>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<PaymentPlugin>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		containsPaymentPluginConfig = new ContainsPaymentPluginConfig();

		containsPaymentMethod = new ContainsPaymentMethod();

		addPaymentMethod = new AddPaymentMethod();
		clearPaymentMethods = new ClearPaymentMethods();
		removePaymentMethod = new RemovePaymentMethod();
	}

	public void destroy() {
		EntityCacheUtil.removeCache(PaymentPluginImpl.class.getName());
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
	protected ContainsPaymentPluginConfig containsPaymentPluginConfig;
	protected ContainsPaymentMethod containsPaymentMethod;
	protected AddPaymentMethod addPaymentMethod;
	protected ClearPaymentMethods clearPaymentMethods;
	protected RemovePaymentMethod removePaymentMethod;

	protected class ContainsPaymentPluginConfig {
		protected ContainsPaymentPluginConfig() {
			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSPAYMENTPLUGINCONFIG,
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
					RowMapper.COUNT);
		}

		protected boolean contains(long paymentPluginId,
			long paymentPluginConfigId) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						new Long(paymentPluginId),
						new Long(paymentPluginConfigId)
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

	protected class ContainsPaymentMethod {
		protected ContainsPaymentMethod() {
			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSPAYMENTMETHOD,
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
					RowMapper.COUNT);
		}

		protected boolean contains(long paymentPluginId, long paymentMethodId) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						new Long(paymentPluginId), new Long(paymentMethodId)
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

	protected class AddPaymentMethod {
		protected AddPaymentMethod() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"INSERT INTO Payment_PaymentPlugin_PaymentMethod (paymentPluginId, paymentMethodId) VALUES (?, ?)",
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT });
		}

		protected void add(long paymentPluginId, long paymentMethodId)
			throws SystemException {
			if (!containsPaymentMethod.contains(paymentPluginId, paymentMethodId)) {
				ModelListener<com.beorn.onlinepayment.model.PaymentMethod>[] paymentMethodListeners =
					paymentMethodPersistence.getListeners();

				for (ModelListener<PaymentPlugin> listener : listeners) {
					listener.onBeforeAddAssociation(paymentPluginId,
						com.beorn.onlinepayment.model.PaymentMethod.class.getName(),
						paymentMethodId);
				}

				for (ModelListener<com.beorn.onlinepayment.model.PaymentMethod> listener : paymentMethodListeners) {
					listener.onBeforeAddAssociation(paymentMethodId,
						PaymentPlugin.class.getName(), paymentPluginId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(paymentPluginId), new Long(paymentMethodId)
					});

				for (ModelListener<PaymentPlugin> listener : listeners) {
					listener.onAfterAddAssociation(paymentPluginId,
						com.beorn.onlinepayment.model.PaymentMethod.class.getName(),
						paymentMethodId);
				}

				for (ModelListener<com.beorn.onlinepayment.model.PaymentMethod> listener : paymentMethodListeners) {
					listener.onAfterAddAssociation(paymentMethodId,
						PaymentPlugin.class.getName(), paymentPluginId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class ClearPaymentMethods {
		protected ClearPaymentMethods() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM Payment_PaymentPlugin_PaymentMethod WHERE paymentPluginId = ?",
					new int[] { java.sql.Types.BIGINT });
		}

		protected void clear(long paymentPluginId) throws SystemException {
			ModelListener<com.beorn.onlinepayment.model.PaymentMethod>[] paymentMethodListeners =
				paymentMethodPersistence.getListeners();

			List<com.beorn.onlinepayment.model.PaymentMethod> paymentMethods = null;

			if ((listeners.length > 0) || (paymentMethodListeners.length > 0)) {
				paymentMethods = getPaymentMethods(paymentPluginId);

				for (com.beorn.onlinepayment.model.PaymentMethod paymentMethod : paymentMethods) {
					for (ModelListener<PaymentPlugin> listener : listeners) {
						listener.onBeforeRemoveAssociation(paymentPluginId,
							com.beorn.onlinepayment.model.PaymentMethod.class.getName(),
							paymentMethod.getPrimaryKey());
					}

					for (ModelListener<com.beorn.onlinepayment.model.PaymentMethod> listener : paymentMethodListeners) {
						listener.onBeforeRemoveAssociation(paymentMethod.getPrimaryKey(),
							PaymentPlugin.class.getName(), paymentPluginId);
					}
				}
			}

			_sqlUpdate.update(new Object[] { new Long(paymentPluginId) });

			if ((listeners.length > 0) || (paymentMethodListeners.length > 0)) {
				for (com.beorn.onlinepayment.model.PaymentMethod paymentMethod : paymentMethods) {
					for (ModelListener<PaymentPlugin> listener : listeners) {
						listener.onAfterRemoveAssociation(paymentPluginId,
							com.beorn.onlinepayment.model.PaymentMethod.class.getName(),
							paymentMethod.getPrimaryKey());
					}

					for (ModelListener<com.beorn.onlinepayment.model.PaymentMethod> listener : paymentMethodListeners) {
						listener.onAfterRemoveAssociation(paymentMethod.getPrimaryKey(),
							PaymentPlugin.class.getName(), paymentPluginId);
					}
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class RemovePaymentMethod {
		protected RemovePaymentMethod() {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM Payment_PaymentPlugin_PaymentMethod WHERE paymentPluginId = ? AND paymentMethodId = ?",
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT });
		}

		protected void remove(long paymentPluginId, long paymentMethodId)
			throws SystemException {
			if (containsPaymentMethod.contains(paymentPluginId, paymentMethodId)) {
				ModelListener<com.beorn.onlinepayment.model.PaymentMethod>[] paymentMethodListeners =
					paymentMethodPersistence.getListeners();

				for (ModelListener<PaymentPlugin> listener : listeners) {
					listener.onBeforeRemoveAssociation(paymentPluginId,
						com.beorn.onlinepayment.model.PaymentMethod.class.getName(),
						paymentMethodId);
				}

				for (ModelListener<com.beorn.onlinepayment.model.PaymentMethod> listener : paymentMethodListeners) {
					listener.onBeforeRemoveAssociation(paymentMethodId,
						PaymentPlugin.class.getName(), paymentPluginId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(paymentPluginId), new Long(paymentMethodId)
					});

				for (ModelListener<PaymentPlugin> listener : listeners) {
					listener.onAfterRemoveAssociation(paymentPluginId,
						com.beorn.onlinepayment.model.PaymentMethod.class.getName(),
						paymentMethodId);
				}

				for (ModelListener<com.beorn.onlinepayment.model.PaymentMethod> listener : paymentMethodListeners) {
					listener.onAfterRemoveAssociation(paymentMethodId,
						PaymentPlugin.class.getName(), paymentPluginId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	private static final String _SQL_SELECT_PAYMENTPLUGIN = "SELECT paymentPlugin FROM PaymentPlugin paymentPlugin";
	private static final String _SQL_SELECT_PAYMENTPLUGIN_WHERE = "SELECT paymentPlugin FROM PaymentPlugin paymentPlugin WHERE ";
	private static final String _SQL_COUNT_PAYMENTPLUGIN = "SELECT COUNT(paymentPlugin) FROM PaymentPlugin paymentPlugin";
	private static final String _SQL_COUNT_PAYMENTPLUGIN_WHERE = "SELECT COUNT(paymentPlugin) FROM PaymentPlugin paymentPlugin WHERE ";
	private static final String _SQL_GETPAYMENTPLUGINCONFIGS = "SELECT {Payment_PaymentPluginConfig.*} FROM Payment_PaymentPluginConfig INNER JOIN Payment_PaymentPlugin ON (Payment_PaymentPlugin.paymentPluginId = Payment_PaymentPluginConfig.paymentPluginId) WHERE (Payment_PaymentPlugin.paymentPluginId = ?)";
	private static final String _SQL_GETPAYMENTPLUGINCONFIGSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM Payment_PaymentPluginConfig WHERE paymentPluginId = ?";
	private static final String _SQL_CONTAINSPAYMENTPLUGINCONFIG = "SELECT COUNT(*) AS COUNT_VALUE FROM Payment_PaymentPluginConfig WHERE paymentPluginId = ? AND paymentPluginConfigId = ?";
	private static final String _SQL_GETPAYMENTMETHODS = "SELECT {Payment_PaymentMethod.*} FROM Payment_PaymentMethod INNER JOIN Payment_PaymentPlugin_PaymentMethod ON (Payment_PaymentPlugin_PaymentMethod.paymentMethodId = Payment_PaymentMethod.paymentMethodId) WHERE (Payment_PaymentPlugin_PaymentMethod.paymentPluginId = ?)";
	private static final String _SQL_GETPAYMENTMETHODSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM Payment_PaymentPlugin_PaymentMethod WHERE paymentPluginId = ?";
	private static final String _SQL_CONTAINSPAYMENTMETHOD = "SELECT COUNT(*) AS COUNT_VALUE FROM Payment_PaymentPlugin_PaymentMethod WHERE paymentPluginId = ? AND paymentMethodId = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "paymentPlugin.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "paymentPlugin.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(paymentPlugin.uuid IS NULL OR paymentPlugin.uuid = ?)";
	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "paymentPlugin.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "paymentPlugin.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(paymentPlugin.uuid IS NULL OR paymentPlugin.uuid = ?) AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "paymentPlugin.groupId = ?";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "paymentPlugin.companyId = ?";
	private static final String _FINDER_COLUMN_APPLICATIONID_APPLICATIONID_1 = "paymentPlugin.applicationId IS NULL";
	private static final String _FINDER_COLUMN_APPLICATIONID_APPLICATIONID_2 = "paymentPlugin.applicationId = ?";
	private static final String _FINDER_COLUMN_APPLICATIONID_APPLICATIONID_3 = "(paymentPlugin.applicationId IS NULL OR paymentPlugin.applicationId = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "paymentPlugin.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PaymentPlugin exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PaymentPlugin exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(PaymentPluginPersistenceImpl.class);
	private static PaymentPlugin _nullPaymentPlugin = new PaymentPluginImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<PaymentPlugin> toCacheModel() {
				return _nullPaymentPluginCacheModel;
			}
		};

	private static CacheModel<PaymentPlugin> _nullPaymentPluginCacheModel = new CacheModel<PaymentPlugin>() {
			public PaymentPlugin toEntityModel() {
				return _nullPaymentPlugin;
			}
		};
}