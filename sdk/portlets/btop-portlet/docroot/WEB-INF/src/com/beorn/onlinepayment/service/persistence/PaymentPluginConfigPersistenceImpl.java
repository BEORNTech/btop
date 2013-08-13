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

import com.beorn.onlinepayment.NoSuchPluginConfigException;
import com.beorn.onlinepayment.model.PaymentPluginConfig;
import com.beorn.onlinepayment.model.impl.PaymentPluginConfigImpl;
import com.beorn.onlinepayment.model.impl.PaymentPluginConfigModelImpl;

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
 * The persistence implementation for the payment plugin config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Sebastien Meunier
 * @see PaymentPluginConfigPersistence
 * @see PaymentPluginConfigUtil
 * @generated
 */
public class PaymentPluginConfigPersistenceImpl extends BasePersistenceImpl<PaymentPluginConfig>
	implements PaymentPluginConfigPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PaymentPluginConfigUtil} to access the payment plugin config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PaymentPluginConfigImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(PaymentPluginConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginConfigModelImpl.FINDER_CACHE_ENABLED,
			PaymentPluginConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(PaymentPluginConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginConfigModelImpl.FINDER_CACHE_ENABLED,
			PaymentPluginConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			PaymentPluginConfigModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(PaymentPluginConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(PaymentPluginConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginConfigModelImpl.FINDER_CACHE_ENABLED,
			PaymentPluginConfigImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			PaymentPluginConfigModelImpl.UUID_COLUMN_BITMASK |
			PaymentPluginConfigModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(PaymentPluginConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(PaymentPluginConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginConfigModelImpl.FINDER_CACHE_ENABLED,
			PaymentPluginConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(PaymentPluginConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginConfigModelImpl.FINDER_CACHE_ENABLED,
			PaymentPluginConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			PaymentPluginConfigModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(PaymentPluginConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SELLERID = new FinderPath(PaymentPluginConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginConfigModelImpl.FINDER_CACHE_ENABLED,
			PaymentPluginConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySellerId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SELLERID =
		new FinderPath(PaymentPluginConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginConfigModelImpl.FINDER_CACHE_ENABLED,
			PaymentPluginConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySellerId",
			new String[] { Long.class.getName() },
			PaymentPluginConfigModelImpl.SELLERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SELLERID = new FinderPath(PaymentPluginConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySellerId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PAYMENTPLUGINID =
		new FinderPath(PaymentPluginConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginConfigModelImpl.FINDER_CACHE_ENABLED,
			PaymentPluginConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPaymentPluginId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PAYMENTPLUGINID =
		new FinderPath(PaymentPluginConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginConfigModelImpl.FINDER_CACHE_ENABLED,
			PaymentPluginConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPaymentPluginId",
			new String[] { Long.class.getName() },
			PaymentPluginConfigModelImpl.PAYMENTPLUGINID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PAYMENTPLUGINID = new FinderPath(PaymentPluginConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByPaymentPluginId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_SELLERIDANDPAYMENTPLUGINID =
		new FinderPath(PaymentPluginConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginConfigModelImpl.FINDER_CACHE_ENABLED,
			PaymentPluginConfigImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchBySellerIdAndPaymentPluginId",
			new String[] { Long.class.getName(), Long.class.getName() },
			PaymentPluginConfigModelImpl.SELLERID_COLUMN_BITMASK |
			PaymentPluginConfigModelImpl.PAYMENTPLUGINID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SELLERIDANDPAYMENTPLUGINID =
		new FinderPath(PaymentPluginConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBySellerIdAndPaymentPluginId",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_SELLERIDANDDEFAULTPLUGIN =
		new FinderPath(PaymentPluginConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginConfigModelImpl.FINDER_CACHE_ENABLED,
			PaymentPluginConfigImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchBySellerIdAndDefaultPlugin",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			PaymentPluginConfigModelImpl.SELLERID_COLUMN_BITMASK |
			PaymentPluginConfigModelImpl.DEFAULTPLUGIN_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SELLERIDANDDEFAULTPLUGIN =
		new FinderPath(PaymentPluginConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBySellerIdAndDefaultPlugin",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PaymentPluginConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginConfigModelImpl.FINDER_CACHE_ENABLED,
			PaymentPluginConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PaymentPluginConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginConfigModelImpl.FINDER_CACHE_ENABLED,
			PaymentPluginConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PaymentPluginConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the payment plugin config in the entity cache if it is enabled.
	 *
	 * @param paymentPluginConfig the payment plugin config
	 */
	public void cacheResult(PaymentPluginConfig paymentPluginConfig) {
		EntityCacheUtil.putResult(PaymentPluginConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginConfigImpl.class, paymentPluginConfig.getPrimaryKey(),
			paymentPluginConfig);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				paymentPluginConfig.getUuid(),
				Long.valueOf(paymentPluginConfig.getGroupId())
			}, paymentPluginConfig);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SELLERIDANDPAYMENTPLUGINID,
			new Object[] {
				Long.valueOf(paymentPluginConfig.getSellerId()),
				Long.valueOf(paymentPluginConfig.getPaymentPluginId())
			}, paymentPluginConfig);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SELLERIDANDDEFAULTPLUGIN,
			new Object[] {
				Long.valueOf(paymentPluginConfig.getSellerId()),
				Boolean.valueOf(paymentPluginConfig.getDefaultPlugin())
			}, paymentPluginConfig);

		paymentPluginConfig.resetOriginalValues();
	}

	/**
	 * Caches the payment plugin configs in the entity cache if it is enabled.
	 *
	 * @param paymentPluginConfigs the payment plugin configs
	 */
	public void cacheResult(List<PaymentPluginConfig> paymentPluginConfigs) {
		for (PaymentPluginConfig paymentPluginConfig : paymentPluginConfigs) {
			if (EntityCacheUtil.getResult(
						PaymentPluginConfigModelImpl.ENTITY_CACHE_ENABLED,
						PaymentPluginConfigImpl.class,
						paymentPluginConfig.getPrimaryKey()) == null) {
				cacheResult(paymentPluginConfig);
			}
			else {
				paymentPluginConfig.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all payment plugin configs.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(PaymentPluginConfigImpl.class.getName());
		}

		EntityCacheUtil.clearCache(PaymentPluginConfigImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the payment plugin config.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PaymentPluginConfig paymentPluginConfig) {
		EntityCacheUtil.removeResult(PaymentPluginConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginConfigImpl.class, paymentPluginConfig.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(paymentPluginConfig);
	}

	@Override
	public void clearCache(List<PaymentPluginConfig> paymentPluginConfigs) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PaymentPluginConfig paymentPluginConfig : paymentPluginConfigs) {
			EntityCacheUtil.removeResult(PaymentPluginConfigModelImpl.ENTITY_CACHE_ENABLED,
				PaymentPluginConfigImpl.class,
				paymentPluginConfig.getPrimaryKey());

			clearUniqueFindersCache(paymentPluginConfig);
		}
	}

	protected void clearUniqueFindersCache(
		PaymentPluginConfig paymentPluginConfig) {
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				paymentPluginConfig.getUuid(),
				Long.valueOf(paymentPluginConfig.getGroupId())
			});

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SELLERIDANDPAYMENTPLUGINID,
			new Object[] {
				Long.valueOf(paymentPluginConfig.getSellerId()),
				Long.valueOf(paymentPluginConfig.getPaymentPluginId())
			});

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SELLERIDANDDEFAULTPLUGIN,
			new Object[] {
				Long.valueOf(paymentPluginConfig.getSellerId()),
				Boolean.valueOf(paymentPluginConfig.getDefaultPlugin())
			});
	}

	/**
	 * Creates a new payment plugin config with the primary key. Does not add the payment plugin config to the database.
	 *
	 * @param paymentPluginConfigId the primary key for the new payment plugin config
	 * @return the new payment plugin config
	 */
	public PaymentPluginConfig create(long paymentPluginConfigId) {
		PaymentPluginConfig paymentPluginConfig = new PaymentPluginConfigImpl();

		paymentPluginConfig.setNew(true);
		paymentPluginConfig.setPrimaryKey(paymentPluginConfigId);

		String uuid = PortalUUIDUtil.generate();

		paymentPluginConfig.setUuid(uuid);

		return paymentPluginConfig;
	}

	/**
	 * Removes the payment plugin config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param paymentPluginConfigId the primary key of the payment plugin config
	 * @return the payment plugin config that was removed
	 * @throws com.beorn.onlinepayment.NoSuchPluginConfigException if a payment plugin config with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPluginConfig remove(long paymentPluginConfigId)
		throws NoSuchPluginConfigException, SystemException {
		return remove(Long.valueOf(paymentPluginConfigId));
	}

	/**
	 * Removes the payment plugin config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the payment plugin config
	 * @return the payment plugin config that was removed
	 * @throws com.beorn.onlinepayment.NoSuchPluginConfigException if a payment plugin config with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentPluginConfig remove(Serializable primaryKey)
		throws NoSuchPluginConfigException, SystemException {
		Session session = null;

		try {
			session = openSession();

			PaymentPluginConfig paymentPluginConfig = (PaymentPluginConfig)session.get(PaymentPluginConfigImpl.class,
					primaryKey);

			if (paymentPluginConfig == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPluginConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(paymentPluginConfig);
		}
		catch (NoSuchPluginConfigException nsee) {
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
	protected PaymentPluginConfig removeImpl(
		PaymentPluginConfig paymentPluginConfig) throws SystemException {
		paymentPluginConfig = toUnwrappedModel(paymentPluginConfig);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, paymentPluginConfig);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(paymentPluginConfig);

		return paymentPluginConfig;
	}

	@Override
	public PaymentPluginConfig updateImpl(
		com.beorn.onlinepayment.model.PaymentPluginConfig paymentPluginConfig,
		boolean merge) throws SystemException {
		paymentPluginConfig = toUnwrappedModel(paymentPluginConfig);

		boolean isNew = paymentPluginConfig.isNew();

		PaymentPluginConfigModelImpl paymentPluginConfigModelImpl = (PaymentPluginConfigModelImpl)paymentPluginConfig;

		if (Validator.isNull(paymentPluginConfig.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			paymentPluginConfig.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, paymentPluginConfig, merge);

			paymentPluginConfig.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !PaymentPluginConfigModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((paymentPluginConfigModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						paymentPluginConfigModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { paymentPluginConfigModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((paymentPluginConfigModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(paymentPluginConfigModelImpl.getOriginalCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] {
						Long.valueOf(paymentPluginConfigModelImpl.getCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((paymentPluginConfigModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SELLERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(paymentPluginConfigModelImpl.getOriginalSellerId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SELLERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SELLERID,
					args);

				args = new Object[] {
						Long.valueOf(paymentPluginConfigModelImpl.getSellerId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SELLERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SELLERID,
					args);
			}

			if ((paymentPluginConfigModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PAYMENTPLUGINID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(paymentPluginConfigModelImpl.getOriginalPaymentPluginId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PAYMENTPLUGINID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PAYMENTPLUGINID,
					args);

				args = new Object[] {
						Long.valueOf(paymentPluginConfigModelImpl.getPaymentPluginId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PAYMENTPLUGINID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PAYMENTPLUGINID,
					args);
			}
		}

		EntityCacheUtil.putResult(PaymentPluginConfigModelImpl.ENTITY_CACHE_ENABLED,
			PaymentPluginConfigImpl.class, paymentPluginConfig.getPrimaryKey(),
			paymentPluginConfig);

		if (isNew) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					paymentPluginConfig.getUuid(),
					Long.valueOf(paymentPluginConfig.getGroupId())
				}, paymentPluginConfig);

			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SELLERIDANDPAYMENTPLUGINID,
				new Object[] {
					Long.valueOf(paymentPluginConfig.getSellerId()),
					Long.valueOf(paymentPluginConfig.getPaymentPluginId())
				}, paymentPluginConfig);

			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SELLERIDANDDEFAULTPLUGIN,
				new Object[] {
					Long.valueOf(paymentPluginConfig.getSellerId()),
					Boolean.valueOf(paymentPluginConfig.getDefaultPlugin())
				}, paymentPluginConfig);
		}
		else {
			if ((paymentPluginConfigModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						paymentPluginConfigModelImpl.getOriginalUuid(),
						Long.valueOf(paymentPluginConfigModelImpl.getOriginalGroupId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);

				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
					new Object[] {
						paymentPluginConfig.getUuid(),
						Long.valueOf(paymentPluginConfig.getGroupId())
					}, paymentPluginConfig);
			}

			if ((paymentPluginConfigModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_SELLERIDANDPAYMENTPLUGINID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(paymentPluginConfigModelImpl.getOriginalSellerId()),
						Long.valueOf(paymentPluginConfigModelImpl.getOriginalPaymentPluginId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SELLERIDANDPAYMENTPLUGINID,
					args);

				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SELLERIDANDPAYMENTPLUGINID,
					args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SELLERIDANDPAYMENTPLUGINID,
					new Object[] {
						Long.valueOf(paymentPluginConfig.getSellerId()),
						Long.valueOf(paymentPluginConfig.getPaymentPluginId())
					}, paymentPluginConfig);
			}

			if ((paymentPluginConfigModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_SELLERIDANDDEFAULTPLUGIN.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(paymentPluginConfigModelImpl.getOriginalSellerId()),
						Boolean.valueOf(paymentPluginConfigModelImpl.getOriginalDefaultPlugin())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SELLERIDANDDEFAULTPLUGIN,
					args);

				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SELLERIDANDDEFAULTPLUGIN,
					args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SELLERIDANDDEFAULTPLUGIN,
					new Object[] {
						Long.valueOf(paymentPluginConfig.getSellerId()),
						Boolean.valueOf(paymentPluginConfig.getDefaultPlugin())
					}, paymentPluginConfig);
			}
		}

		return paymentPluginConfig;
	}

	protected PaymentPluginConfig toUnwrappedModel(
		PaymentPluginConfig paymentPluginConfig) {
		if (paymentPluginConfig instanceof PaymentPluginConfigImpl) {
			return paymentPluginConfig;
		}

		PaymentPluginConfigImpl paymentPluginConfigImpl = new PaymentPluginConfigImpl();

		paymentPluginConfigImpl.setNew(paymentPluginConfig.isNew());
		paymentPluginConfigImpl.setPrimaryKey(paymentPluginConfig.getPrimaryKey());

		paymentPluginConfigImpl.setUuid(paymentPluginConfig.getUuid());
		paymentPluginConfigImpl.setCompanyId(paymentPluginConfig.getCompanyId());
		paymentPluginConfigImpl.setGroupId(paymentPluginConfig.getGroupId());
		paymentPluginConfigImpl.setUserId(paymentPluginConfig.getUserId());
		paymentPluginConfigImpl.setPaymentPluginConfigId(paymentPluginConfig.getPaymentPluginConfigId());
		paymentPluginConfigImpl.setCreateDate(paymentPluginConfig.getCreateDate());
		paymentPluginConfigImpl.setModifiedDate(paymentPluginConfig.getModifiedDate());
		paymentPluginConfigImpl.setSellerId(paymentPluginConfig.getSellerId());
		paymentPluginConfigImpl.setPaymentPluginId(paymentPluginConfig.getPaymentPluginId());
		paymentPluginConfigImpl.setConfig(paymentPluginConfig.getConfig());
		paymentPluginConfigImpl.setConfigured(paymentPluginConfig.isConfigured());
		paymentPluginConfigImpl.setDefaultPlugin(paymentPluginConfig.isDefaultPlugin());

		return paymentPluginConfigImpl;
	}

	/**
	 * Returns the payment plugin config with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the payment plugin config
	 * @return the payment plugin config
	 * @throws com.liferay.portal.NoSuchModelException if a payment plugin config with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentPluginConfig findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the payment plugin config with the primary key or throws a {@link com.beorn.onlinepayment.NoSuchPluginConfigException} if it could not be found.
	 *
	 * @param paymentPluginConfigId the primary key of the payment plugin config
	 * @return the payment plugin config
	 * @throws com.beorn.onlinepayment.NoSuchPluginConfigException if a payment plugin config with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPluginConfig findByPrimaryKey(long paymentPluginConfigId)
		throws NoSuchPluginConfigException, SystemException {
		PaymentPluginConfig paymentPluginConfig = fetchByPrimaryKey(paymentPluginConfigId);

		if (paymentPluginConfig == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					paymentPluginConfigId);
			}

			throw new NoSuchPluginConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				paymentPluginConfigId);
		}

		return paymentPluginConfig;
	}

	/**
	 * Returns the payment plugin config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the payment plugin config
	 * @return the payment plugin config, or <code>null</code> if a payment plugin config with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PaymentPluginConfig fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the payment plugin config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param paymentPluginConfigId the primary key of the payment plugin config
	 * @return the payment plugin config, or <code>null</code> if a payment plugin config with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPluginConfig fetchByPrimaryKey(long paymentPluginConfigId)
		throws SystemException {
		PaymentPluginConfig paymentPluginConfig = (PaymentPluginConfig)EntityCacheUtil.getResult(PaymentPluginConfigModelImpl.ENTITY_CACHE_ENABLED,
				PaymentPluginConfigImpl.class, paymentPluginConfigId);

		if (paymentPluginConfig == _nullPaymentPluginConfig) {
			return null;
		}

		if (paymentPluginConfig == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				paymentPluginConfig = (PaymentPluginConfig)session.get(PaymentPluginConfigImpl.class,
						Long.valueOf(paymentPluginConfigId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (paymentPluginConfig != null) {
					cacheResult(paymentPluginConfig);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(PaymentPluginConfigModelImpl.ENTITY_CACHE_ENABLED,
						PaymentPluginConfigImpl.class, paymentPluginConfigId,
						_nullPaymentPluginConfig);
				}

				closeSession(session);
			}
		}

		return paymentPluginConfig;
	}

	/**
	 * Returns all the payment plugin configs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching payment plugin configs
	 * @throws SystemException if a system exception occurred
	 */
	public List<PaymentPluginConfig> findByUuid(String uuid)
		throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the payment plugin configs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of payment plugin configs
	 * @param end the upper bound of the range of payment plugin configs (not inclusive)
	 * @return the range of matching payment plugin configs
	 * @throws SystemException if a system exception occurred
	 */
	public List<PaymentPluginConfig> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the payment plugin configs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of payment plugin configs
	 * @param end the upper bound of the range of payment plugin configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching payment plugin configs
	 * @throws SystemException if a system exception occurred
	 */
	public List<PaymentPluginConfig> findByUuid(String uuid, int start,
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

		List<PaymentPluginConfig> list = (List<PaymentPluginConfig>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PaymentPluginConfig paymentPluginConfig : list) {
				if (!Validator.equals(uuid, paymentPluginConfig.getUuid())) {
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

			query.append(_SQL_SELECT_PAYMENTPLUGINCONFIG_WHERE);

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
				query.append(PaymentPluginConfigModelImpl.ORDER_BY_JPQL);
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

				list = (List<PaymentPluginConfig>)QueryUtil.list(q,
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
	 * Returns the first payment plugin config in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment plugin config
	 * @throws com.beorn.onlinepayment.NoSuchPluginConfigException if a matching payment plugin config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPluginConfig findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchPluginConfigException, SystemException {
		PaymentPluginConfig paymentPluginConfig = fetchByUuid_First(uuid,
				orderByComparator);

		if (paymentPluginConfig != null) {
			return paymentPluginConfig;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPluginConfigException(msg.toString());
	}

	/**
	 * Returns the first payment plugin config in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment plugin config, or <code>null</code> if a matching payment plugin config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPluginConfig fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<PaymentPluginConfig> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last payment plugin config in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment plugin config
	 * @throws com.beorn.onlinepayment.NoSuchPluginConfigException if a matching payment plugin config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPluginConfig findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchPluginConfigException, SystemException {
		PaymentPluginConfig paymentPluginConfig = fetchByUuid_Last(uuid,
				orderByComparator);

		if (paymentPluginConfig != null) {
			return paymentPluginConfig;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPluginConfigException(msg.toString());
	}

	/**
	 * Returns the last payment plugin config in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment plugin config, or <code>null</code> if a matching payment plugin config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPluginConfig fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		List<PaymentPluginConfig> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the payment plugin configs before and after the current payment plugin config in the ordered set where uuid = &#63;.
	 *
	 * @param paymentPluginConfigId the primary key of the current payment plugin config
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next payment plugin config
	 * @throws com.beorn.onlinepayment.NoSuchPluginConfigException if a payment plugin config with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPluginConfig[] findByUuid_PrevAndNext(
		long paymentPluginConfigId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchPluginConfigException, SystemException {
		PaymentPluginConfig paymentPluginConfig = findByPrimaryKey(paymentPluginConfigId);

		Session session = null;

		try {
			session = openSession();

			PaymentPluginConfig[] array = new PaymentPluginConfigImpl[3];

			array[0] = getByUuid_PrevAndNext(session, paymentPluginConfig,
					uuid, orderByComparator, true);

			array[1] = paymentPluginConfig;

			array[2] = getByUuid_PrevAndNext(session, paymentPluginConfig,
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

	protected PaymentPluginConfig getByUuid_PrevAndNext(Session session,
		PaymentPluginConfig paymentPluginConfig, String uuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PAYMENTPLUGINCONFIG_WHERE);

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
			query.append(PaymentPluginConfigModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(paymentPluginConfig);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PaymentPluginConfig> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the payment plugin config where uuid = &#63; and groupId = &#63; or throws a {@link com.beorn.onlinepayment.NoSuchPluginConfigException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching payment plugin config
	 * @throws com.beorn.onlinepayment.NoSuchPluginConfigException if a matching payment plugin config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPluginConfig findByUUID_G(String uuid, long groupId)
		throws NoSuchPluginConfigException, SystemException {
		PaymentPluginConfig paymentPluginConfig = fetchByUUID_G(uuid, groupId);

		if (paymentPluginConfig == null) {
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

			throw new NoSuchPluginConfigException(msg.toString());
		}

		return paymentPluginConfig;
	}

	/**
	 * Returns the payment plugin config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching payment plugin config, or <code>null</code> if a matching payment plugin config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPluginConfig fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the payment plugin config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching payment plugin config, or <code>null</code> if a matching payment plugin config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPluginConfig fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof PaymentPluginConfig) {
			PaymentPluginConfig paymentPluginConfig = (PaymentPluginConfig)result;

			if (!Validator.equals(uuid, paymentPluginConfig.getUuid()) ||
					(groupId != paymentPluginConfig.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_PAYMENTPLUGINCONFIG_WHERE);

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

			query.append(PaymentPluginConfigModelImpl.ORDER_BY_JPQL);

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

				List<PaymentPluginConfig> list = q.list();

				result = list;

				PaymentPluginConfig paymentPluginConfig = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					paymentPluginConfig = list.get(0);

					cacheResult(paymentPluginConfig);

					if ((paymentPluginConfig.getUuid() == null) ||
							!paymentPluginConfig.getUuid().equals(uuid) ||
							(paymentPluginConfig.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, paymentPluginConfig);
					}
				}

				return paymentPluginConfig;
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
				return (PaymentPluginConfig)result;
			}
		}
	}

	/**
	 * Returns all the payment plugin configs where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching payment plugin configs
	 * @throws SystemException if a system exception occurred
	 */
	public List<PaymentPluginConfig> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the payment plugin configs where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of payment plugin configs
	 * @param end the upper bound of the range of payment plugin configs (not inclusive)
	 * @return the range of matching payment plugin configs
	 * @throws SystemException if a system exception occurred
	 */
	public List<PaymentPluginConfig> findByCompanyId(long companyId, int start,
		int end) throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the payment plugin configs where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of payment plugin configs
	 * @param end the upper bound of the range of payment plugin configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching payment plugin configs
	 * @throws SystemException if a system exception occurred
	 */
	public List<PaymentPluginConfig> findByCompanyId(long companyId, int start,
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

		List<PaymentPluginConfig> list = (List<PaymentPluginConfig>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PaymentPluginConfig paymentPluginConfig : list) {
				if ((companyId != paymentPluginConfig.getCompanyId())) {
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

			query.append(_SQL_SELECT_PAYMENTPLUGINCONFIG_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(PaymentPluginConfigModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<PaymentPluginConfig>)QueryUtil.list(q,
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
	 * Returns the first payment plugin config in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment plugin config
	 * @throws com.beorn.onlinepayment.NoSuchPluginConfigException if a matching payment plugin config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPluginConfig findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchPluginConfigException, SystemException {
		PaymentPluginConfig paymentPluginConfig = fetchByCompanyId_First(companyId,
				orderByComparator);

		if (paymentPluginConfig != null) {
			return paymentPluginConfig;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPluginConfigException(msg.toString());
	}

	/**
	 * Returns the first payment plugin config in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment plugin config, or <code>null</code> if a matching payment plugin config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPluginConfig fetchByCompanyId_First(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<PaymentPluginConfig> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last payment plugin config in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment plugin config
	 * @throws com.beorn.onlinepayment.NoSuchPluginConfigException if a matching payment plugin config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPluginConfig findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchPluginConfigException, SystemException {
		PaymentPluginConfig paymentPluginConfig = fetchByCompanyId_Last(companyId,
				orderByComparator);

		if (paymentPluginConfig != null) {
			return paymentPluginConfig;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPluginConfigException(msg.toString());
	}

	/**
	 * Returns the last payment plugin config in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment plugin config, or <code>null</code> if a matching payment plugin config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPluginConfig fetchByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByCompanyId(companyId);

		List<PaymentPluginConfig> list = findByCompanyId(companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the payment plugin configs before and after the current payment plugin config in the ordered set where companyId = &#63;.
	 *
	 * @param paymentPluginConfigId the primary key of the current payment plugin config
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next payment plugin config
	 * @throws com.beorn.onlinepayment.NoSuchPluginConfigException if a payment plugin config with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPluginConfig[] findByCompanyId_PrevAndNext(
		long paymentPluginConfigId, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchPluginConfigException, SystemException {
		PaymentPluginConfig paymentPluginConfig = findByPrimaryKey(paymentPluginConfigId);

		Session session = null;

		try {
			session = openSession();

			PaymentPluginConfig[] array = new PaymentPluginConfigImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, paymentPluginConfig,
					companyId, orderByComparator, true);

			array[1] = paymentPluginConfig;

			array[2] = getByCompanyId_PrevAndNext(session, paymentPluginConfig,
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

	protected PaymentPluginConfig getByCompanyId_PrevAndNext(Session session,
		PaymentPluginConfig paymentPluginConfig, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PAYMENTPLUGINCONFIG_WHERE);

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
			query.append(PaymentPluginConfigModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(paymentPluginConfig);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PaymentPluginConfig> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the payment plugin configs where sellerId = &#63;.
	 *
	 * @param sellerId the seller ID
	 * @return the matching payment plugin configs
	 * @throws SystemException if a system exception occurred
	 */
	public List<PaymentPluginConfig> findBySellerId(long sellerId)
		throws SystemException {
		return findBySellerId(sellerId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the payment plugin configs where sellerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param sellerId the seller ID
	 * @param start the lower bound of the range of payment plugin configs
	 * @param end the upper bound of the range of payment plugin configs (not inclusive)
	 * @return the range of matching payment plugin configs
	 * @throws SystemException if a system exception occurred
	 */
	public List<PaymentPluginConfig> findBySellerId(long sellerId, int start,
		int end) throws SystemException {
		return findBySellerId(sellerId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the payment plugin configs where sellerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param sellerId the seller ID
	 * @param start the lower bound of the range of payment plugin configs
	 * @param end the upper bound of the range of payment plugin configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching payment plugin configs
	 * @throws SystemException if a system exception occurred
	 */
	public List<PaymentPluginConfig> findBySellerId(long sellerId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
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

		List<PaymentPluginConfig> list = (List<PaymentPluginConfig>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PaymentPluginConfig paymentPluginConfig : list) {
				if ((sellerId != paymentPluginConfig.getSellerId())) {
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

			query.append(_SQL_SELECT_PAYMENTPLUGINCONFIG_WHERE);

			query.append(_FINDER_COLUMN_SELLERID_SELLERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(PaymentPluginConfigModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(sellerId);

				list = (List<PaymentPluginConfig>)QueryUtil.list(q,
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
	 * Returns the first payment plugin config in the ordered set where sellerId = &#63;.
	 *
	 * @param sellerId the seller ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment plugin config
	 * @throws com.beorn.onlinepayment.NoSuchPluginConfigException if a matching payment plugin config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPluginConfig findBySellerId_First(long sellerId,
		OrderByComparator orderByComparator)
		throws NoSuchPluginConfigException, SystemException {
		PaymentPluginConfig paymentPluginConfig = fetchBySellerId_First(sellerId,
				orderByComparator);

		if (paymentPluginConfig != null) {
			return paymentPluginConfig;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("sellerId=");
		msg.append(sellerId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPluginConfigException(msg.toString());
	}

	/**
	 * Returns the first payment plugin config in the ordered set where sellerId = &#63;.
	 *
	 * @param sellerId the seller ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment plugin config, or <code>null</code> if a matching payment plugin config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPluginConfig fetchBySellerId_First(long sellerId,
		OrderByComparator orderByComparator) throws SystemException {
		List<PaymentPluginConfig> list = findBySellerId(sellerId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last payment plugin config in the ordered set where sellerId = &#63;.
	 *
	 * @param sellerId the seller ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment plugin config
	 * @throws com.beorn.onlinepayment.NoSuchPluginConfigException if a matching payment plugin config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPluginConfig findBySellerId_Last(long sellerId,
		OrderByComparator orderByComparator)
		throws NoSuchPluginConfigException, SystemException {
		PaymentPluginConfig paymentPluginConfig = fetchBySellerId_Last(sellerId,
				orderByComparator);

		if (paymentPluginConfig != null) {
			return paymentPluginConfig;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("sellerId=");
		msg.append(sellerId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPluginConfigException(msg.toString());
	}

	/**
	 * Returns the last payment plugin config in the ordered set where sellerId = &#63;.
	 *
	 * @param sellerId the seller ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment plugin config, or <code>null</code> if a matching payment plugin config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPluginConfig fetchBySellerId_Last(long sellerId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countBySellerId(sellerId);

		List<PaymentPluginConfig> list = findBySellerId(sellerId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the payment plugin configs before and after the current payment plugin config in the ordered set where sellerId = &#63;.
	 *
	 * @param paymentPluginConfigId the primary key of the current payment plugin config
	 * @param sellerId the seller ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next payment plugin config
	 * @throws com.beorn.onlinepayment.NoSuchPluginConfigException if a payment plugin config with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPluginConfig[] findBySellerId_PrevAndNext(
		long paymentPluginConfigId, long sellerId,
		OrderByComparator orderByComparator)
		throws NoSuchPluginConfigException, SystemException {
		PaymentPluginConfig paymentPluginConfig = findByPrimaryKey(paymentPluginConfigId);

		Session session = null;

		try {
			session = openSession();

			PaymentPluginConfig[] array = new PaymentPluginConfigImpl[3];

			array[0] = getBySellerId_PrevAndNext(session, paymentPluginConfig,
					sellerId, orderByComparator, true);

			array[1] = paymentPluginConfig;

			array[2] = getBySellerId_PrevAndNext(session, paymentPluginConfig,
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

	protected PaymentPluginConfig getBySellerId_PrevAndNext(Session session,
		PaymentPluginConfig paymentPluginConfig, long sellerId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PAYMENTPLUGINCONFIG_WHERE);

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
			query.append(PaymentPluginConfigModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(sellerId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(paymentPluginConfig);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PaymentPluginConfig> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the payment plugin configs where paymentPluginId = &#63;.
	 *
	 * @param paymentPluginId the payment plugin ID
	 * @return the matching payment plugin configs
	 * @throws SystemException if a system exception occurred
	 */
	public List<PaymentPluginConfig> findByPaymentPluginId(long paymentPluginId)
		throws SystemException {
		return findByPaymentPluginId(paymentPluginId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the payment plugin configs where paymentPluginId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param paymentPluginId the payment plugin ID
	 * @param start the lower bound of the range of payment plugin configs
	 * @param end the upper bound of the range of payment plugin configs (not inclusive)
	 * @return the range of matching payment plugin configs
	 * @throws SystemException if a system exception occurred
	 */
	public List<PaymentPluginConfig> findByPaymentPluginId(
		long paymentPluginId, int start, int end) throws SystemException {
		return findByPaymentPluginId(paymentPluginId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the payment plugin configs where paymentPluginId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param paymentPluginId the payment plugin ID
	 * @param start the lower bound of the range of payment plugin configs
	 * @param end the upper bound of the range of payment plugin configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching payment plugin configs
	 * @throws SystemException if a system exception occurred
	 */
	public List<PaymentPluginConfig> findByPaymentPluginId(
		long paymentPluginId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PAYMENTPLUGINID;
			finderArgs = new Object[] { paymentPluginId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PAYMENTPLUGINID;
			finderArgs = new Object[] {
					paymentPluginId,
					
					start, end, orderByComparator
				};
		}

		List<PaymentPluginConfig> list = (List<PaymentPluginConfig>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PaymentPluginConfig paymentPluginConfig : list) {
				if ((paymentPluginId != paymentPluginConfig.getPaymentPluginId())) {
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

			query.append(_SQL_SELECT_PAYMENTPLUGINCONFIG_WHERE);

			query.append(_FINDER_COLUMN_PAYMENTPLUGINID_PAYMENTPLUGINID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(PaymentPluginConfigModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(paymentPluginId);

				list = (List<PaymentPluginConfig>)QueryUtil.list(q,
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
	 * Returns the first payment plugin config in the ordered set where paymentPluginId = &#63;.
	 *
	 * @param paymentPluginId the payment plugin ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment plugin config
	 * @throws com.beorn.onlinepayment.NoSuchPluginConfigException if a matching payment plugin config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPluginConfig findByPaymentPluginId_First(
		long paymentPluginId, OrderByComparator orderByComparator)
		throws NoSuchPluginConfigException, SystemException {
		PaymentPluginConfig paymentPluginConfig = fetchByPaymentPluginId_First(paymentPluginId,
				orderByComparator);

		if (paymentPluginConfig != null) {
			return paymentPluginConfig;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("paymentPluginId=");
		msg.append(paymentPluginId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPluginConfigException(msg.toString());
	}

	/**
	 * Returns the first payment plugin config in the ordered set where paymentPluginId = &#63;.
	 *
	 * @param paymentPluginId the payment plugin ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment plugin config, or <code>null</code> if a matching payment plugin config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPluginConfig fetchByPaymentPluginId_First(
		long paymentPluginId, OrderByComparator orderByComparator)
		throws SystemException {
		List<PaymentPluginConfig> list = findByPaymentPluginId(paymentPluginId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last payment plugin config in the ordered set where paymentPluginId = &#63;.
	 *
	 * @param paymentPluginId the payment plugin ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment plugin config
	 * @throws com.beorn.onlinepayment.NoSuchPluginConfigException if a matching payment plugin config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPluginConfig findByPaymentPluginId_Last(
		long paymentPluginId, OrderByComparator orderByComparator)
		throws NoSuchPluginConfigException, SystemException {
		PaymentPluginConfig paymentPluginConfig = fetchByPaymentPluginId_Last(paymentPluginId,
				orderByComparator);

		if (paymentPluginConfig != null) {
			return paymentPluginConfig;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("paymentPluginId=");
		msg.append(paymentPluginId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPluginConfigException(msg.toString());
	}

	/**
	 * Returns the last payment plugin config in the ordered set where paymentPluginId = &#63;.
	 *
	 * @param paymentPluginId the payment plugin ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment plugin config, or <code>null</code> if a matching payment plugin config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPluginConfig fetchByPaymentPluginId_Last(
		long paymentPluginId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByPaymentPluginId(paymentPluginId);

		List<PaymentPluginConfig> list = findByPaymentPluginId(paymentPluginId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the payment plugin configs before and after the current payment plugin config in the ordered set where paymentPluginId = &#63;.
	 *
	 * @param paymentPluginConfigId the primary key of the current payment plugin config
	 * @param paymentPluginId the payment plugin ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next payment plugin config
	 * @throws com.beorn.onlinepayment.NoSuchPluginConfigException if a payment plugin config with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPluginConfig[] findByPaymentPluginId_PrevAndNext(
		long paymentPluginConfigId, long paymentPluginId,
		OrderByComparator orderByComparator)
		throws NoSuchPluginConfigException, SystemException {
		PaymentPluginConfig paymentPluginConfig = findByPrimaryKey(paymentPluginConfigId);

		Session session = null;

		try {
			session = openSession();

			PaymentPluginConfig[] array = new PaymentPluginConfigImpl[3];

			array[0] = getByPaymentPluginId_PrevAndNext(session,
					paymentPluginConfig, paymentPluginId, orderByComparator,
					true);

			array[1] = paymentPluginConfig;

			array[2] = getByPaymentPluginId_PrevAndNext(session,
					paymentPluginConfig, paymentPluginId, orderByComparator,
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

	protected PaymentPluginConfig getByPaymentPluginId_PrevAndNext(
		Session session, PaymentPluginConfig paymentPluginConfig,
		long paymentPluginId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PAYMENTPLUGINCONFIG_WHERE);

		query.append(_FINDER_COLUMN_PAYMENTPLUGINID_PAYMENTPLUGINID_2);

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
			query.append(PaymentPluginConfigModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(paymentPluginId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(paymentPluginConfig);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PaymentPluginConfig> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the payment plugin config where sellerId = &#63; and paymentPluginId = &#63; or throws a {@link com.beorn.onlinepayment.NoSuchPluginConfigException} if it could not be found.
	 *
	 * @param sellerId the seller ID
	 * @param paymentPluginId the payment plugin ID
	 * @return the matching payment plugin config
	 * @throws com.beorn.onlinepayment.NoSuchPluginConfigException if a matching payment plugin config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPluginConfig findBySellerIdAndPaymentPluginId(long sellerId,
		long paymentPluginId)
		throws NoSuchPluginConfigException, SystemException {
		PaymentPluginConfig paymentPluginConfig = fetchBySellerIdAndPaymentPluginId(sellerId,
				paymentPluginId);

		if (paymentPluginConfig == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("sellerId=");
			msg.append(sellerId);

			msg.append(", paymentPluginId=");
			msg.append(paymentPluginId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchPluginConfigException(msg.toString());
		}

		return paymentPluginConfig;
	}

	/**
	 * Returns the payment plugin config where sellerId = &#63; and paymentPluginId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param sellerId the seller ID
	 * @param paymentPluginId the payment plugin ID
	 * @return the matching payment plugin config, or <code>null</code> if a matching payment plugin config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPluginConfig fetchBySellerIdAndPaymentPluginId(
		long sellerId, long paymentPluginId) throws SystemException {
		return fetchBySellerIdAndPaymentPluginId(sellerId, paymentPluginId, true);
	}

	/**
	 * Returns the payment plugin config where sellerId = &#63; and paymentPluginId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param sellerId the seller ID
	 * @param paymentPluginId the payment plugin ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching payment plugin config, or <code>null</code> if a matching payment plugin config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPluginConfig fetchBySellerIdAndPaymentPluginId(
		long sellerId, long paymentPluginId, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { sellerId, paymentPluginId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_SELLERIDANDPAYMENTPLUGINID,
					finderArgs, this);
		}

		if (result instanceof PaymentPluginConfig) {
			PaymentPluginConfig paymentPluginConfig = (PaymentPluginConfig)result;

			if ((sellerId != paymentPluginConfig.getSellerId()) ||
					(paymentPluginId != paymentPluginConfig.getPaymentPluginId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_PAYMENTPLUGINCONFIG_WHERE);

			query.append(_FINDER_COLUMN_SELLERIDANDPAYMENTPLUGINID_SELLERID_2);

			query.append(_FINDER_COLUMN_SELLERIDANDPAYMENTPLUGINID_PAYMENTPLUGINID_2);

			query.append(PaymentPluginConfigModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(sellerId);

				qPos.add(paymentPluginId);

				List<PaymentPluginConfig> list = q.list();

				result = list;

				PaymentPluginConfig paymentPluginConfig = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SELLERIDANDPAYMENTPLUGINID,
						finderArgs, list);
				}
				else {
					paymentPluginConfig = list.get(0);

					cacheResult(paymentPluginConfig);

					if ((paymentPluginConfig.getSellerId() != sellerId) ||
							(paymentPluginConfig.getPaymentPluginId() != paymentPluginId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SELLERIDANDPAYMENTPLUGINID,
							finderArgs, paymentPluginConfig);
					}
				}

				return paymentPluginConfig;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SELLERIDANDPAYMENTPLUGINID,
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
				return (PaymentPluginConfig)result;
			}
		}
	}

	/**
	 * Returns the payment plugin config where sellerId = &#63; and defaultPlugin = &#63; or throws a {@link com.beorn.onlinepayment.NoSuchPluginConfigException} if it could not be found.
	 *
	 * @param sellerId the seller ID
	 * @param defaultPlugin the default plugin
	 * @return the matching payment plugin config
	 * @throws com.beorn.onlinepayment.NoSuchPluginConfigException if a matching payment plugin config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPluginConfig findBySellerIdAndDefaultPlugin(long sellerId,
		boolean defaultPlugin)
		throws NoSuchPluginConfigException, SystemException {
		PaymentPluginConfig paymentPluginConfig = fetchBySellerIdAndDefaultPlugin(sellerId,
				defaultPlugin);

		if (paymentPluginConfig == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("sellerId=");
			msg.append(sellerId);

			msg.append(", defaultPlugin=");
			msg.append(defaultPlugin);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchPluginConfigException(msg.toString());
		}

		return paymentPluginConfig;
	}

	/**
	 * Returns the payment plugin config where sellerId = &#63; and defaultPlugin = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param sellerId the seller ID
	 * @param defaultPlugin the default plugin
	 * @return the matching payment plugin config, or <code>null</code> if a matching payment plugin config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPluginConfig fetchBySellerIdAndDefaultPlugin(long sellerId,
		boolean defaultPlugin) throws SystemException {
		return fetchBySellerIdAndDefaultPlugin(sellerId, defaultPlugin, true);
	}

	/**
	 * Returns the payment plugin config where sellerId = &#63; and defaultPlugin = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param sellerId the seller ID
	 * @param defaultPlugin the default plugin
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching payment plugin config, or <code>null</code> if a matching payment plugin config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPluginConfig fetchBySellerIdAndDefaultPlugin(long sellerId,
		boolean defaultPlugin, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { sellerId, defaultPlugin };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_SELLERIDANDDEFAULTPLUGIN,
					finderArgs, this);
		}

		if (result instanceof PaymentPluginConfig) {
			PaymentPluginConfig paymentPluginConfig = (PaymentPluginConfig)result;

			if ((sellerId != paymentPluginConfig.getSellerId()) ||
					(defaultPlugin != paymentPluginConfig.getDefaultPlugin())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_PAYMENTPLUGINCONFIG_WHERE);

			query.append(_FINDER_COLUMN_SELLERIDANDDEFAULTPLUGIN_SELLERID_2);

			query.append(_FINDER_COLUMN_SELLERIDANDDEFAULTPLUGIN_DEFAULTPLUGIN_2);

			query.append(PaymentPluginConfigModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(sellerId);

				qPos.add(defaultPlugin);

				List<PaymentPluginConfig> list = q.list();

				result = list;

				PaymentPluginConfig paymentPluginConfig = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SELLERIDANDDEFAULTPLUGIN,
						finderArgs, list);
				}
				else {
					paymentPluginConfig = list.get(0);

					cacheResult(paymentPluginConfig);

					if ((paymentPluginConfig.getSellerId() != sellerId) ||
							(paymentPluginConfig.getDefaultPlugin() != defaultPlugin)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SELLERIDANDDEFAULTPLUGIN,
							finderArgs, paymentPluginConfig);
					}
				}

				return paymentPluginConfig;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SELLERIDANDDEFAULTPLUGIN,
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
				return (PaymentPluginConfig)result;
			}
		}
	}

	/**
	 * Returns all the payment plugin configs.
	 *
	 * @return the payment plugin configs
	 * @throws SystemException if a system exception occurred
	 */
	public List<PaymentPluginConfig> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the payment plugin configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of payment plugin configs
	 * @param end the upper bound of the range of payment plugin configs (not inclusive)
	 * @return the range of payment plugin configs
	 * @throws SystemException if a system exception occurred
	 */
	public List<PaymentPluginConfig> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the payment plugin configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of payment plugin configs
	 * @param end the upper bound of the range of payment plugin configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of payment plugin configs
	 * @throws SystemException if a system exception occurred
	 */
	public List<PaymentPluginConfig> findAll(int start, int end,
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

		List<PaymentPluginConfig> list = (List<PaymentPluginConfig>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_PAYMENTPLUGINCONFIG);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PAYMENTPLUGINCONFIG.concat(PaymentPluginConfigModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<PaymentPluginConfig>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<PaymentPluginConfig>)QueryUtil.list(q,
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
	 * Removes all the payment plugin configs where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (PaymentPluginConfig paymentPluginConfig : findByUuid(uuid)) {
			remove(paymentPluginConfig);
		}
	}

	/**
	 * Removes the payment plugin config where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the payment plugin config that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPluginConfig removeByUUID_G(String uuid, long groupId)
		throws NoSuchPluginConfigException, SystemException {
		PaymentPluginConfig paymentPluginConfig = findByUUID_G(uuid, groupId);

		return remove(paymentPluginConfig);
	}

	/**
	 * Removes all the payment plugin configs where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCompanyId(long companyId) throws SystemException {
		for (PaymentPluginConfig paymentPluginConfig : findByCompanyId(
				companyId)) {
			remove(paymentPluginConfig);
		}
	}

	/**
	 * Removes all the payment plugin configs where sellerId = &#63; from the database.
	 *
	 * @param sellerId the seller ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBySellerId(long sellerId) throws SystemException {
		for (PaymentPluginConfig paymentPluginConfig : findBySellerId(sellerId)) {
			remove(paymentPluginConfig);
		}
	}

	/**
	 * Removes all the payment plugin configs where paymentPluginId = &#63; from the database.
	 *
	 * @param paymentPluginId the payment plugin ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPaymentPluginId(long paymentPluginId)
		throws SystemException {
		for (PaymentPluginConfig paymentPluginConfig : findByPaymentPluginId(
				paymentPluginId)) {
			remove(paymentPluginConfig);
		}
	}

	/**
	 * Removes the payment plugin config where sellerId = &#63; and paymentPluginId = &#63; from the database.
	 *
	 * @param sellerId the seller ID
	 * @param paymentPluginId the payment plugin ID
	 * @return the payment plugin config that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPluginConfig removeBySellerIdAndPaymentPluginId(
		long sellerId, long paymentPluginId)
		throws NoSuchPluginConfigException, SystemException {
		PaymentPluginConfig paymentPluginConfig = findBySellerIdAndPaymentPluginId(sellerId,
				paymentPluginId);

		return remove(paymentPluginConfig);
	}

	/**
	 * Removes the payment plugin config where sellerId = &#63; and defaultPlugin = &#63; from the database.
	 *
	 * @param sellerId the seller ID
	 * @param defaultPlugin the default plugin
	 * @return the payment plugin config that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public PaymentPluginConfig removeBySellerIdAndDefaultPlugin(long sellerId,
		boolean defaultPlugin)
		throws NoSuchPluginConfigException, SystemException {
		PaymentPluginConfig paymentPluginConfig = findBySellerIdAndDefaultPlugin(sellerId,
				defaultPlugin);

		return remove(paymentPluginConfig);
	}

	/**
	 * Removes all the payment plugin configs from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (PaymentPluginConfig paymentPluginConfig : findAll()) {
			remove(paymentPluginConfig);
		}
	}

	/**
	 * Returns the number of payment plugin configs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching payment plugin configs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PAYMENTPLUGINCONFIG_WHERE);

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
	 * Returns the number of payment plugin configs where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching payment plugin configs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUUID_G(String uuid, long groupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID_G,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PAYMENTPLUGINCONFIG_WHERE);

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
	 * Returns the number of payment plugin configs where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching payment plugin configs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PAYMENTPLUGINCONFIG_WHERE);

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
	 * Returns the number of payment plugin configs where sellerId = &#63;.
	 *
	 * @param sellerId the seller ID
	 * @return the number of matching payment plugin configs
	 * @throws SystemException if a system exception occurred
	 */
	public int countBySellerId(long sellerId) throws SystemException {
		Object[] finderArgs = new Object[] { sellerId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_SELLERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PAYMENTPLUGINCONFIG_WHERE);

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
	 * Returns the number of payment plugin configs where paymentPluginId = &#63;.
	 *
	 * @param paymentPluginId the payment plugin ID
	 * @return the number of matching payment plugin configs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPaymentPluginId(long paymentPluginId)
		throws SystemException {
		Object[] finderArgs = new Object[] { paymentPluginId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PAYMENTPLUGINID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PAYMENTPLUGINCONFIG_WHERE);

			query.append(_FINDER_COLUMN_PAYMENTPLUGINID_PAYMENTPLUGINID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(paymentPluginId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PAYMENTPLUGINID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of payment plugin configs where sellerId = &#63; and paymentPluginId = &#63;.
	 *
	 * @param sellerId the seller ID
	 * @param paymentPluginId the payment plugin ID
	 * @return the number of matching payment plugin configs
	 * @throws SystemException if a system exception occurred
	 */
	public int countBySellerIdAndPaymentPluginId(long sellerId,
		long paymentPluginId) throws SystemException {
		Object[] finderArgs = new Object[] { sellerId, paymentPluginId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_SELLERIDANDPAYMENTPLUGINID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PAYMENTPLUGINCONFIG_WHERE);

			query.append(_FINDER_COLUMN_SELLERIDANDPAYMENTPLUGINID_SELLERID_2);

			query.append(_FINDER_COLUMN_SELLERIDANDPAYMENTPLUGINID_PAYMENTPLUGINID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(sellerId);

				qPos.add(paymentPluginId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SELLERIDANDPAYMENTPLUGINID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of payment plugin configs where sellerId = &#63; and defaultPlugin = &#63;.
	 *
	 * @param sellerId the seller ID
	 * @param defaultPlugin the default plugin
	 * @return the number of matching payment plugin configs
	 * @throws SystemException if a system exception occurred
	 */
	public int countBySellerIdAndDefaultPlugin(long sellerId,
		boolean defaultPlugin) throws SystemException {
		Object[] finderArgs = new Object[] { sellerId, defaultPlugin };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_SELLERIDANDDEFAULTPLUGIN,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PAYMENTPLUGINCONFIG_WHERE);

			query.append(_FINDER_COLUMN_SELLERIDANDDEFAULTPLUGIN_SELLERID_2);

			query.append(_FINDER_COLUMN_SELLERIDANDDEFAULTPLUGIN_DEFAULTPLUGIN_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(sellerId);

				qPos.add(defaultPlugin);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SELLERIDANDDEFAULTPLUGIN,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of payment plugin configs.
	 *
	 * @return the number of payment plugin configs
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PAYMENTPLUGINCONFIG);

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
	 * Returns all the rules associated with the payment plugin config.
	 *
	 * @param pk the primary key of the payment plugin config
	 * @return the rules associated with the payment plugin config
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.beorn.onlinepayment.model.Rule> getRules(long pk)
		throws SystemException {
		return getRules(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the rules associated with the payment plugin config.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the payment plugin config
	 * @param start the lower bound of the range of payment plugin configs
	 * @param end the upper bound of the range of payment plugin configs (not inclusive)
	 * @return the range of rules associated with the payment plugin config
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.beorn.onlinepayment.model.Rule> getRules(long pk,
		int start, int end) throws SystemException {
		return getRules(pk, start, end, null);
	}

	public static final FinderPath FINDER_PATH_GET_RULES = new FinderPath(com.beorn.onlinepayment.model.impl.RuleModelImpl.ENTITY_CACHE_ENABLED,
			com.beorn.onlinepayment.model.impl.RuleModelImpl.FINDER_CACHE_ENABLED,
			com.beorn.onlinepayment.model.impl.RuleImpl.class,
			com.beorn.onlinepayment.service.persistence.RulePersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"getRules",
			new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});

	static {
		FINDER_PATH_GET_RULES.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns an ordered range of all the rules associated with the payment plugin config.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the payment plugin config
	 * @param start the lower bound of the range of payment plugin configs
	 * @param end the upper bound of the range of payment plugin configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of rules associated with the payment plugin config
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.beorn.onlinepayment.model.Rule> getRules(long pk,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, start, end, orderByComparator };

		List<com.beorn.onlinepayment.model.Rule> list = (List<com.beorn.onlinepayment.model.Rule>)FinderCacheUtil.getResult(FINDER_PATH_GET_RULES,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				String sql = null;

				if (orderByComparator != null) {
					sql = _SQL_GETRULES.concat(ORDER_BY_CLAUSE)
									   .concat(orderByComparator.getOrderBy());
				}
				else {
					sql = _SQL_GETRULES.concat(com.beorn.onlinepayment.model.impl.RuleModelImpl.ORDER_BY_SQL);
				}

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("Payment_Rule",
					com.beorn.onlinepayment.model.impl.RuleImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				list = (List<com.beorn.onlinepayment.model.Rule>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_GET_RULES,
						finderArgs);
				}
				else {
					rulePersistence.cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_GET_RULES,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	public static final FinderPath FINDER_PATH_GET_RULES_SIZE = new FinderPath(com.beorn.onlinepayment.model.impl.RuleModelImpl.ENTITY_CACHE_ENABLED,
			com.beorn.onlinepayment.model.impl.RuleModelImpl.FINDER_CACHE_ENABLED,
			com.beorn.onlinepayment.model.impl.RuleImpl.class,
			com.beorn.onlinepayment.service.persistence.RulePersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"getRulesSize", new String[] { Long.class.getName() });

	static {
		FINDER_PATH_GET_RULES_SIZE.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns the number of rules associated with the payment plugin config.
	 *
	 * @param pk the primary key of the payment plugin config
	 * @return the number of rules associated with the payment plugin config
	 * @throws SystemException if a system exception occurred
	 */
	public int getRulesSize(long pk) throws SystemException {
		Object[] finderArgs = new Object[] { pk };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_GET_RULES_SIZE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETRULESSIZE);

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

				FinderCacheUtil.putResult(FINDER_PATH_GET_RULES_SIZE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public static final FinderPath FINDER_PATH_CONTAINS_RULE = new FinderPath(com.beorn.onlinepayment.model.impl.RuleModelImpl.ENTITY_CACHE_ENABLED,
			com.beorn.onlinepayment.model.impl.RuleModelImpl.FINDER_CACHE_ENABLED,
			com.beorn.onlinepayment.model.impl.RuleImpl.class,
			com.beorn.onlinepayment.service.persistence.RulePersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"containsRule",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns <code>true</code> if the rule is associated with the payment plugin config.
	 *
	 * @param pk the primary key of the payment plugin config
	 * @param rulePK the primary key of the rule
	 * @return <code>true</code> if the rule is associated with the payment plugin config; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsRule(long pk, long rulePK) throws SystemException {
		Object[] finderArgs = new Object[] { pk, rulePK };

		Boolean value = (Boolean)FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_RULE,
				finderArgs, this);

		if (value == null) {
			try {
				value = Boolean.valueOf(containsRule.contains(pk, rulePK));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (value == null) {
					value = Boolean.FALSE;
				}

				FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_RULE,
					finderArgs, value);
			}
		}

		return value.booleanValue();
	}

	/**
	 * Returns <code>true</code> if the payment plugin config has any rules associated with it.
	 *
	 * @param pk the primary key of the payment plugin config to check for associations with rules
	 * @return <code>true</code> if the payment plugin config has any rules associated with it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsRules(long pk) throws SystemException {
		if (getRulesSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Initializes the payment plugin config persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.beorn.onlinepayment.model.PaymentPluginConfig")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<PaymentPluginConfig>> listenersList = new ArrayList<ModelListener<PaymentPluginConfig>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<PaymentPluginConfig>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		containsRule = new ContainsRule();
	}

	public void destroy() {
		EntityCacheUtil.removeCache(PaymentPluginConfigImpl.class.getName());
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
	protected ContainsRule containsRule;

	protected class ContainsRule {
		protected ContainsRule() {
			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSRULE,
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
					RowMapper.COUNT);
		}

		protected boolean contains(long paymentPluginConfigId, long ruleId) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						new Long(paymentPluginConfigId), new Long(ruleId)
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

	private static final String _SQL_SELECT_PAYMENTPLUGINCONFIG = "SELECT paymentPluginConfig FROM PaymentPluginConfig paymentPluginConfig";
	private static final String _SQL_SELECT_PAYMENTPLUGINCONFIG_WHERE = "SELECT paymentPluginConfig FROM PaymentPluginConfig paymentPluginConfig WHERE ";
	private static final String _SQL_COUNT_PAYMENTPLUGINCONFIG = "SELECT COUNT(paymentPluginConfig) FROM PaymentPluginConfig paymentPluginConfig";
	private static final String _SQL_COUNT_PAYMENTPLUGINCONFIG_WHERE = "SELECT COUNT(paymentPluginConfig) FROM PaymentPluginConfig paymentPluginConfig WHERE ";
	private static final String _SQL_GETRULES = "SELECT {Payment_Rule.*} FROM Payment_Rule INNER JOIN Payment_PaymentPluginConfig ON (Payment_PaymentPluginConfig.paymentPluginConfigId = Payment_Rule.paymentPluginConfigId) WHERE (Payment_PaymentPluginConfig.paymentPluginConfigId = ?)";
	private static final String _SQL_GETRULESSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM Payment_Rule WHERE paymentPluginConfigId = ?";
	private static final String _SQL_CONTAINSRULE = "SELECT COUNT(*) AS COUNT_VALUE FROM Payment_Rule WHERE paymentPluginConfigId = ? AND ruleId = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "paymentPluginConfig.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "paymentPluginConfig.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(paymentPluginConfig.uuid IS NULL OR paymentPluginConfig.uuid = ?)";
	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "paymentPluginConfig.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "paymentPluginConfig.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(paymentPluginConfig.uuid IS NULL OR paymentPluginConfig.uuid = ?) AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "paymentPluginConfig.groupId = ?";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "paymentPluginConfig.companyId = ?";
	private static final String _FINDER_COLUMN_SELLERID_SELLERID_2 = "paymentPluginConfig.sellerId = ?";
	private static final String _FINDER_COLUMN_PAYMENTPLUGINID_PAYMENTPLUGINID_2 =
		"paymentPluginConfig.paymentPluginId = ?";
	private static final String _FINDER_COLUMN_SELLERIDANDPAYMENTPLUGINID_SELLERID_2 =
		"paymentPluginConfig.sellerId = ? AND ";
	private static final String _FINDER_COLUMN_SELLERIDANDPAYMENTPLUGINID_PAYMENTPLUGINID_2 =
		"paymentPluginConfig.paymentPluginId = ?";
	private static final String _FINDER_COLUMN_SELLERIDANDDEFAULTPLUGIN_SELLERID_2 =
		"paymentPluginConfig.sellerId = ? AND ";
	private static final String _FINDER_COLUMN_SELLERIDANDDEFAULTPLUGIN_DEFAULTPLUGIN_2 =
		"paymentPluginConfig.defaultPlugin = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "paymentPluginConfig.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PaymentPluginConfig exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PaymentPluginConfig exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(PaymentPluginConfigPersistenceImpl.class);
	private static PaymentPluginConfig _nullPaymentPluginConfig = new PaymentPluginConfigImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<PaymentPluginConfig> toCacheModel() {
				return _nullPaymentPluginConfigCacheModel;
			}
		};

	private static CacheModel<PaymentPluginConfig> _nullPaymentPluginConfigCacheModel =
		new CacheModel<PaymentPluginConfig>() {
			public PaymentPluginConfig toEntityModel() {
				return _nullPaymentPluginConfig;
			}
		};
}