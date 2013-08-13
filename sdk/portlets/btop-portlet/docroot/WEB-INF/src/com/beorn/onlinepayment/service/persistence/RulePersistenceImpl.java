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

import com.beorn.onlinepayment.NoSuchRuleException;
import com.beorn.onlinepayment.model.Rule;
import com.beorn.onlinepayment.model.impl.RuleImpl;
import com.beorn.onlinepayment.model.impl.RuleModelImpl;

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
 * The persistence implementation for the rule service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Sebastien Meunier
 * @see RulePersistence
 * @see RuleUtil
 * @generated
 */
public class RulePersistenceImpl extends BasePersistenceImpl<Rule>
	implements RulePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link RuleUtil} to access the rule persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = RuleImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(RuleModelImpl.ENTITY_CACHE_ENABLED,
			RuleModelImpl.FINDER_CACHE_ENABLED, RuleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(RuleModelImpl.ENTITY_CACHE_ENABLED,
			RuleModelImpl.FINDER_CACHE_ENABLED, RuleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			RuleModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(RuleModelImpl.ENTITY_CACHE_ENABLED,
			RuleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(RuleModelImpl.ENTITY_CACHE_ENABLED,
			RuleModelImpl.FINDER_CACHE_ENABLED, RuleImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			RuleModelImpl.UUID_COLUMN_BITMASK |
			RuleModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(RuleModelImpl.ENTITY_CACHE_ENABLED,
			RuleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(RuleModelImpl.ENTITY_CACHE_ENABLED,
			RuleModelImpl.FINDER_CACHE_ENABLED, RuleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(RuleModelImpl.ENTITY_CACHE_ENABLED,
			RuleModelImpl.FINDER_CACHE_ENABLED, RuleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			RuleModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(RuleModelImpl.ENTITY_CACHE_ENABLED,
			RuleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PAYMENTPLUGINCONFIGIDID =
		new FinderPath(RuleModelImpl.ENTITY_CACHE_ENABLED,
			RuleModelImpl.FINDER_CACHE_ENABLED, RuleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByPaymentPluginConfigIdId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PAYMENTPLUGINCONFIGIDID =
		new FinderPath(RuleModelImpl.ENTITY_CACHE_ENABLED,
			RuleModelImpl.FINDER_CACHE_ENABLED, RuleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByPaymentPluginConfigIdId",
			new String[] { Long.class.getName() },
			RuleModelImpl.PAYMENTPLUGINCONFIGID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PAYMENTPLUGINCONFIGIDID = new FinderPath(RuleModelImpl.ENTITY_CACHE_ENABLED,
			RuleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByPaymentPluginConfigIdId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(RuleModelImpl.ENTITY_CACHE_ENABLED,
			RuleModelImpl.FINDER_CACHE_ENABLED, RuleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(RuleModelImpl.ENTITY_CACHE_ENABLED,
			RuleModelImpl.FINDER_CACHE_ENABLED, RuleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(RuleModelImpl.ENTITY_CACHE_ENABLED,
			RuleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the rule in the entity cache if it is enabled.
	 *
	 * @param rule the rule
	 */
	public void cacheResult(Rule rule) {
		EntityCacheUtil.putResult(RuleModelImpl.ENTITY_CACHE_ENABLED,
			RuleImpl.class, rule.getPrimaryKey(), rule);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { rule.getUuid(), Long.valueOf(rule.getGroupId()) },
			rule);

		rule.resetOriginalValues();
	}

	/**
	 * Caches the rules in the entity cache if it is enabled.
	 *
	 * @param rules the rules
	 */
	public void cacheResult(List<Rule> rules) {
		for (Rule rule : rules) {
			if (EntityCacheUtil.getResult(RuleModelImpl.ENTITY_CACHE_ENABLED,
						RuleImpl.class, rule.getPrimaryKey()) == null) {
				cacheResult(rule);
			}
			else {
				rule.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all rules.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(RuleImpl.class.getName());
		}

		EntityCacheUtil.clearCache(RuleImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the rule.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Rule rule) {
		EntityCacheUtil.removeResult(RuleModelImpl.ENTITY_CACHE_ENABLED,
			RuleImpl.class, rule.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(rule);
	}

	@Override
	public void clearCache(List<Rule> rules) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Rule rule : rules) {
			EntityCacheUtil.removeResult(RuleModelImpl.ENTITY_CACHE_ENABLED,
				RuleImpl.class, rule.getPrimaryKey());

			clearUniqueFindersCache(rule);
		}
	}

	protected void clearUniqueFindersCache(Rule rule) {
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { rule.getUuid(), Long.valueOf(rule.getGroupId()) });
	}

	/**
	 * Creates a new rule with the primary key. Does not add the rule to the database.
	 *
	 * @param ruleId the primary key for the new rule
	 * @return the new rule
	 */
	public Rule create(long ruleId) {
		Rule rule = new RuleImpl();

		rule.setNew(true);
		rule.setPrimaryKey(ruleId);

		String uuid = PortalUUIDUtil.generate();

		rule.setUuid(uuid);

		return rule;
	}

	/**
	 * Removes the rule with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ruleId the primary key of the rule
	 * @return the rule that was removed
	 * @throws com.beorn.onlinepayment.NoSuchRuleException if a rule with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Rule remove(long ruleId) throws NoSuchRuleException, SystemException {
		return remove(Long.valueOf(ruleId));
	}

	/**
	 * Removes the rule with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the rule
	 * @return the rule that was removed
	 * @throws com.beorn.onlinepayment.NoSuchRuleException if a rule with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Rule remove(Serializable primaryKey)
		throws NoSuchRuleException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Rule rule = (Rule)session.get(RuleImpl.class, primaryKey);

			if (rule == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRuleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(rule);
		}
		catch (NoSuchRuleException nsee) {
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
	protected Rule removeImpl(Rule rule) throws SystemException {
		rule = toUnwrappedModel(rule);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, rule);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(rule);

		return rule;
	}

	@Override
	public Rule updateImpl(com.beorn.onlinepayment.model.Rule rule,
		boolean merge) throws SystemException {
		rule = toUnwrappedModel(rule);

		boolean isNew = rule.isNew();

		RuleModelImpl ruleModelImpl = (RuleModelImpl)rule;

		if (Validator.isNull(rule.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			rule.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, rule, merge);

			rule.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !RuleModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((ruleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { ruleModelImpl.getOriginalUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { ruleModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((ruleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(ruleModelImpl.getOriginalCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] { Long.valueOf(ruleModelImpl.getCompanyId()) };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((ruleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PAYMENTPLUGINCONFIGIDID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(ruleModelImpl.getOriginalPaymentPluginConfigId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PAYMENTPLUGINCONFIGIDID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PAYMENTPLUGINCONFIGIDID,
					args);

				args = new Object[] {
						Long.valueOf(ruleModelImpl.getPaymentPluginConfigId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PAYMENTPLUGINCONFIGIDID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PAYMENTPLUGINCONFIGIDID,
					args);
			}
		}

		EntityCacheUtil.putResult(RuleModelImpl.ENTITY_CACHE_ENABLED,
			RuleImpl.class, rule.getPrimaryKey(), rule);

		if (isNew) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] { rule.getUuid(), Long.valueOf(rule.getGroupId()) },
				rule);
		}
		else {
			if ((ruleModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ruleModelImpl.getOriginalUuid(),
						Long.valueOf(ruleModelImpl.getOriginalGroupId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);

				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
					new Object[] { rule.getUuid(), Long.valueOf(
							rule.getGroupId()) }, rule);
			}
		}

		return rule;
	}

	protected Rule toUnwrappedModel(Rule rule) {
		if (rule instanceof RuleImpl) {
			return rule;
		}

		RuleImpl ruleImpl = new RuleImpl();

		ruleImpl.setNew(rule.isNew());
		ruleImpl.setPrimaryKey(rule.getPrimaryKey());

		ruleImpl.setUuid(rule.getUuid());
		ruleImpl.setCompanyId(rule.getCompanyId());
		ruleImpl.setGroupId(rule.getGroupId());
		ruleImpl.setUserId(rule.getUserId());
		ruleImpl.setRuleId(rule.getRuleId());
		ruleImpl.setCreateDate(rule.getCreateDate());
		ruleImpl.setModifiedDate(rule.getModifiedDate());
		ruleImpl.setPaymentPluginConfigId(rule.getPaymentPluginConfigId());
		ruleImpl.setContent(rule.getContent());
		ruleImpl.setPriority(rule.getPriority());

		return ruleImpl;
	}

	/**
	 * Returns the rule with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the rule
	 * @return the rule
	 * @throws com.liferay.portal.NoSuchModelException if a rule with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Rule findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the rule with the primary key or throws a {@link com.beorn.onlinepayment.NoSuchRuleException} if it could not be found.
	 *
	 * @param ruleId the primary key of the rule
	 * @return the rule
	 * @throws com.beorn.onlinepayment.NoSuchRuleException if a rule with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Rule findByPrimaryKey(long ruleId)
		throws NoSuchRuleException, SystemException {
		Rule rule = fetchByPrimaryKey(ruleId);

		if (rule == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + ruleId);
			}

			throw new NoSuchRuleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				ruleId);
		}

		return rule;
	}

	/**
	 * Returns the rule with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the rule
	 * @return the rule, or <code>null</code> if a rule with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Rule fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the rule with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ruleId the primary key of the rule
	 * @return the rule, or <code>null</code> if a rule with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Rule fetchByPrimaryKey(long ruleId) throws SystemException {
		Rule rule = (Rule)EntityCacheUtil.getResult(RuleModelImpl.ENTITY_CACHE_ENABLED,
				RuleImpl.class, ruleId);

		if (rule == _nullRule) {
			return null;
		}

		if (rule == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				rule = (Rule)session.get(RuleImpl.class, Long.valueOf(ruleId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (rule != null) {
					cacheResult(rule);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(RuleModelImpl.ENTITY_CACHE_ENABLED,
						RuleImpl.class, ruleId, _nullRule);
				}

				closeSession(session);
			}
		}

		return rule;
	}

	/**
	 * Returns all the rules where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching rules
	 * @throws SystemException if a system exception occurred
	 */
	public List<Rule> findByUuid(String uuid) throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the rules where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of rules
	 * @param end the upper bound of the range of rules (not inclusive)
	 * @return the range of matching rules
	 * @throws SystemException if a system exception occurred
	 */
	public List<Rule> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the rules where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of rules
	 * @param end the upper bound of the range of rules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rules
	 * @throws SystemException if a system exception occurred
	 */
	public List<Rule> findByUuid(String uuid, int start, int end,
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

		List<Rule> list = (List<Rule>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Rule rule : list) {
				if (!Validator.equals(uuid, rule.getUuid())) {
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

			query.append(_SQL_SELECT_RULE_WHERE);

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
				query.append(RuleModelImpl.ORDER_BY_JPQL);
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

				list = (List<Rule>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first rule in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rule
	 * @throws com.beorn.onlinepayment.NoSuchRuleException if a matching rule could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Rule findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchRuleException, SystemException {
		Rule rule = fetchByUuid_First(uuid, orderByComparator);

		if (rule != null) {
			return rule;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRuleException(msg.toString());
	}

	/**
	 * Returns the first rule in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rule, or <code>null</code> if a matching rule could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Rule fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<Rule> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last rule in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rule
	 * @throws com.beorn.onlinepayment.NoSuchRuleException if a matching rule could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Rule findByUuid_Last(String uuid, OrderByComparator orderByComparator)
		throws NoSuchRuleException, SystemException {
		Rule rule = fetchByUuid_Last(uuid, orderByComparator);

		if (rule != null) {
			return rule;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRuleException(msg.toString());
	}

	/**
	 * Returns the last rule in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rule, or <code>null</code> if a matching rule could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Rule fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		List<Rule> list = findByUuid(uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the rules before and after the current rule in the ordered set where uuid = &#63;.
	 *
	 * @param ruleId the primary key of the current rule
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next rule
	 * @throws com.beorn.onlinepayment.NoSuchRuleException if a rule with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Rule[] findByUuid_PrevAndNext(long ruleId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchRuleException, SystemException {
		Rule rule = findByPrimaryKey(ruleId);

		Session session = null;

		try {
			session = openSession();

			Rule[] array = new RuleImpl[3];

			array[0] = getByUuid_PrevAndNext(session, rule, uuid,
					orderByComparator, true);

			array[1] = rule;

			array[2] = getByUuid_PrevAndNext(session, rule, uuid,
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

	protected Rule getByUuid_PrevAndNext(Session session, Rule rule,
		String uuid, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_RULE_WHERE);

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
			query.append(RuleModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(rule);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Rule> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the rule where uuid = &#63; and groupId = &#63; or throws a {@link com.beorn.onlinepayment.NoSuchRuleException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching rule
	 * @throws com.beorn.onlinepayment.NoSuchRuleException if a matching rule could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Rule findByUUID_G(String uuid, long groupId)
		throws NoSuchRuleException, SystemException {
		Rule rule = fetchByUUID_G(uuid, groupId);

		if (rule == null) {
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

			throw new NoSuchRuleException(msg.toString());
		}

		return rule;
	}

	/**
	 * Returns the rule where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching rule, or <code>null</code> if a matching rule could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Rule fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the rule where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching rule, or <code>null</code> if a matching rule could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Rule fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof Rule) {
			Rule rule = (Rule)result;

			if (!Validator.equals(uuid, rule.getUuid()) ||
					(groupId != rule.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_RULE_WHERE);

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

			query.append(RuleModelImpl.ORDER_BY_JPQL);

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

				List<Rule> list = q.list();

				result = list;

				Rule rule = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					rule = list.get(0);

					cacheResult(rule);

					if ((rule.getUuid() == null) ||
							!rule.getUuid().equals(uuid) ||
							(rule.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, rule);
					}
				}

				return rule;
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
				return (Rule)result;
			}
		}
	}

	/**
	 * Returns all the rules where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching rules
	 * @throws SystemException if a system exception occurred
	 */
	public List<Rule> findByCompanyId(long companyId) throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the rules where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of rules
	 * @param end the upper bound of the range of rules (not inclusive)
	 * @return the range of matching rules
	 * @throws SystemException if a system exception occurred
	 */
	public List<Rule> findByCompanyId(long companyId, int start, int end)
		throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the rules where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of rules
	 * @param end the upper bound of the range of rules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rules
	 * @throws SystemException if a system exception occurred
	 */
	public List<Rule> findByCompanyId(long companyId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
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

		List<Rule> list = (List<Rule>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Rule rule : list) {
				if ((companyId != rule.getCompanyId())) {
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

			query.append(_SQL_SELECT_RULE_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(RuleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<Rule>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first rule in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rule
	 * @throws com.beorn.onlinepayment.NoSuchRuleException if a matching rule could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Rule findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchRuleException, SystemException {
		Rule rule = fetchByCompanyId_First(companyId, orderByComparator);

		if (rule != null) {
			return rule;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRuleException(msg.toString());
	}

	/**
	 * Returns the first rule in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rule, or <code>null</code> if a matching rule could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Rule fetchByCompanyId_First(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Rule> list = findByCompanyId(companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last rule in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rule
	 * @throws com.beorn.onlinepayment.NoSuchRuleException if a matching rule could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Rule findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchRuleException, SystemException {
		Rule rule = fetchByCompanyId_Last(companyId, orderByComparator);

		if (rule != null) {
			return rule;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRuleException(msg.toString());
	}

	/**
	 * Returns the last rule in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rule, or <code>null</code> if a matching rule could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Rule fetchByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByCompanyId(companyId);

		List<Rule> list = findByCompanyId(companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the rules before and after the current rule in the ordered set where companyId = &#63;.
	 *
	 * @param ruleId the primary key of the current rule
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next rule
	 * @throws com.beorn.onlinepayment.NoSuchRuleException if a rule with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Rule[] findByCompanyId_PrevAndNext(long ruleId, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchRuleException, SystemException {
		Rule rule = findByPrimaryKey(ruleId);

		Session session = null;

		try {
			session = openSession();

			Rule[] array = new RuleImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, rule, companyId,
					orderByComparator, true);

			array[1] = rule;

			array[2] = getByCompanyId_PrevAndNext(session, rule, companyId,
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

	protected Rule getByCompanyId_PrevAndNext(Session session, Rule rule,
		long companyId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_RULE_WHERE);

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
			query.append(RuleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(rule);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Rule> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the rules where paymentPluginConfigId = &#63;.
	 *
	 * @param paymentPluginConfigId the payment plugin config ID
	 * @return the matching rules
	 * @throws SystemException if a system exception occurred
	 */
	public List<Rule> findByPaymentPluginConfigIdId(long paymentPluginConfigId)
		throws SystemException {
		return findByPaymentPluginConfigIdId(paymentPluginConfigId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the rules where paymentPluginConfigId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param paymentPluginConfigId the payment plugin config ID
	 * @param start the lower bound of the range of rules
	 * @param end the upper bound of the range of rules (not inclusive)
	 * @return the range of matching rules
	 * @throws SystemException if a system exception occurred
	 */
	public List<Rule> findByPaymentPluginConfigIdId(
		long paymentPluginConfigId, int start, int end)
		throws SystemException {
		return findByPaymentPluginConfigIdId(paymentPluginConfigId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the rules where paymentPluginConfigId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param paymentPluginConfigId the payment plugin config ID
	 * @param start the lower bound of the range of rules
	 * @param end the upper bound of the range of rules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rules
	 * @throws SystemException if a system exception occurred
	 */
	public List<Rule> findByPaymentPluginConfigIdId(
		long paymentPluginConfigId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PAYMENTPLUGINCONFIGIDID;
			finderArgs = new Object[] { paymentPluginConfigId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PAYMENTPLUGINCONFIGIDID;
			finderArgs = new Object[] {
					paymentPluginConfigId,
					
					start, end, orderByComparator
				};
		}

		List<Rule> list = (List<Rule>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Rule rule : list) {
				if ((paymentPluginConfigId != rule.getPaymentPluginConfigId())) {
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

			query.append(_SQL_SELECT_RULE_WHERE);

			query.append(_FINDER_COLUMN_PAYMENTPLUGINCONFIGIDID_PAYMENTPLUGINCONFIGID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(RuleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(paymentPluginConfigId);

				list = (List<Rule>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first rule in the ordered set where paymentPluginConfigId = &#63;.
	 *
	 * @param paymentPluginConfigId the payment plugin config ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rule
	 * @throws com.beorn.onlinepayment.NoSuchRuleException if a matching rule could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Rule findByPaymentPluginConfigIdId_First(
		long paymentPluginConfigId, OrderByComparator orderByComparator)
		throws NoSuchRuleException, SystemException {
		Rule rule = fetchByPaymentPluginConfigIdId_First(paymentPluginConfigId,
				orderByComparator);

		if (rule != null) {
			return rule;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("paymentPluginConfigId=");
		msg.append(paymentPluginConfigId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRuleException(msg.toString());
	}

	/**
	 * Returns the first rule in the ordered set where paymentPluginConfigId = &#63;.
	 *
	 * @param paymentPluginConfigId the payment plugin config ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rule, or <code>null</code> if a matching rule could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Rule fetchByPaymentPluginConfigIdId_First(
		long paymentPluginConfigId, OrderByComparator orderByComparator)
		throws SystemException {
		List<Rule> list = findByPaymentPluginConfigIdId(paymentPluginConfigId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last rule in the ordered set where paymentPluginConfigId = &#63;.
	 *
	 * @param paymentPluginConfigId the payment plugin config ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rule
	 * @throws com.beorn.onlinepayment.NoSuchRuleException if a matching rule could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Rule findByPaymentPluginConfigIdId_Last(long paymentPluginConfigId,
		OrderByComparator orderByComparator)
		throws NoSuchRuleException, SystemException {
		Rule rule = fetchByPaymentPluginConfigIdId_Last(paymentPluginConfigId,
				orderByComparator);

		if (rule != null) {
			return rule;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("paymentPluginConfigId=");
		msg.append(paymentPluginConfigId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRuleException(msg.toString());
	}

	/**
	 * Returns the last rule in the ordered set where paymentPluginConfigId = &#63;.
	 *
	 * @param paymentPluginConfigId the payment plugin config ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rule, or <code>null</code> if a matching rule could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Rule fetchByPaymentPluginConfigIdId_Last(
		long paymentPluginConfigId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByPaymentPluginConfigIdId(paymentPluginConfigId);

		List<Rule> list = findByPaymentPluginConfigIdId(paymentPluginConfigId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the rules before and after the current rule in the ordered set where paymentPluginConfigId = &#63;.
	 *
	 * @param ruleId the primary key of the current rule
	 * @param paymentPluginConfigId the payment plugin config ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next rule
	 * @throws com.beorn.onlinepayment.NoSuchRuleException if a rule with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Rule[] findByPaymentPluginConfigIdId_PrevAndNext(long ruleId,
		long paymentPluginConfigId, OrderByComparator orderByComparator)
		throws NoSuchRuleException, SystemException {
		Rule rule = findByPrimaryKey(ruleId);

		Session session = null;

		try {
			session = openSession();

			Rule[] array = new RuleImpl[3];

			array[0] = getByPaymentPluginConfigIdId_PrevAndNext(session, rule,
					paymentPluginConfigId, orderByComparator, true);

			array[1] = rule;

			array[2] = getByPaymentPluginConfigIdId_PrevAndNext(session, rule,
					paymentPluginConfigId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Rule getByPaymentPluginConfigIdId_PrevAndNext(Session session,
		Rule rule, long paymentPluginConfigId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_RULE_WHERE);

		query.append(_FINDER_COLUMN_PAYMENTPLUGINCONFIGIDID_PAYMENTPLUGINCONFIGID_2);

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
			query.append(RuleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(paymentPluginConfigId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(rule);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Rule> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the rules.
	 *
	 * @return the rules
	 * @throws SystemException if a system exception occurred
	 */
	public List<Rule> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the rules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of rules
	 * @param end the upper bound of the range of rules (not inclusive)
	 * @return the range of rules
	 * @throws SystemException if a system exception occurred
	 */
	public List<Rule> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the rules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of rules
	 * @param end the upper bound of the range of rules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of rules
	 * @throws SystemException if a system exception occurred
	 */
	public List<Rule> findAll(int start, int end,
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

		List<Rule> list = (List<Rule>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_RULE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_RULE.concat(RuleModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<Rule>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Rule>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Removes all the rules where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (Rule rule : findByUuid(uuid)) {
			remove(rule);
		}
	}

	/**
	 * Removes the rule where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the rule that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public Rule removeByUUID_G(String uuid, long groupId)
		throws NoSuchRuleException, SystemException {
		Rule rule = findByUUID_G(uuid, groupId);

		return remove(rule);
	}

	/**
	 * Removes all the rules where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCompanyId(long companyId) throws SystemException {
		for (Rule rule : findByCompanyId(companyId)) {
			remove(rule);
		}
	}

	/**
	 * Removes all the rules where paymentPluginConfigId = &#63; from the database.
	 *
	 * @param paymentPluginConfigId the payment plugin config ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPaymentPluginConfigIdId(long paymentPluginConfigId)
		throws SystemException {
		for (Rule rule : findByPaymentPluginConfigIdId(paymentPluginConfigId)) {
			remove(rule);
		}
	}

	/**
	 * Removes all the rules from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (Rule rule : findAll()) {
			remove(rule);
		}
	}

	/**
	 * Returns the number of rules where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching rules
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_RULE_WHERE);

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
	 * Returns the number of rules where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching rules
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUUID_G(String uuid, long groupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID_G,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_RULE_WHERE);

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
	 * Returns the number of rules where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching rules
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_RULE_WHERE);

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
	 * Returns the number of rules where paymentPluginConfigId = &#63;.
	 *
	 * @param paymentPluginConfigId the payment plugin config ID
	 * @return the number of matching rules
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPaymentPluginConfigIdId(long paymentPluginConfigId)
		throws SystemException {
		Object[] finderArgs = new Object[] { paymentPluginConfigId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PAYMENTPLUGINCONFIGIDID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_RULE_WHERE);

			query.append(_FINDER_COLUMN_PAYMENTPLUGINCONFIGIDID_PAYMENTPLUGINCONFIGID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(paymentPluginConfigId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PAYMENTPLUGINCONFIGIDID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of rules.
	 *
	 * @return the number of rules
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_RULE);

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
	 * Initializes the rule persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.beorn.onlinepayment.model.Rule")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Rule>> listenersList = new ArrayList<ModelListener<Rule>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Rule>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(RuleImpl.class.getName());
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
	private static final String _SQL_SELECT_RULE = "SELECT rule FROM Rule rule";
	private static final String _SQL_SELECT_RULE_WHERE = "SELECT rule FROM Rule rule WHERE ";
	private static final String _SQL_COUNT_RULE = "SELECT COUNT(rule) FROM Rule rule";
	private static final String _SQL_COUNT_RULE_WHERE = "SELECT COUNT(rule) FROM Rule rule WHERE ";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "rule.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "rule.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(rule.uuid IS NULL OR rule.uuid = ?)";
	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "rule.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "rule.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(rule.uuid IS NULL OR rule.uuid = ?) AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "rule.groupId = ?";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "rule.companyId = ?";
	private static final String _FINDER_COLUMN_PAYMENTPLUGINCONFIGIDID_PAYMENTPLUGINCONFIGID_2 =
		"rule.paymentPluginConfigId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "rule.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Rule exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Rule exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(RulePersistenceImpl.class);
	private static Rule _nullRule = new RuleImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Rule> toCacheModel() {
				return _nullRuleCacheModel;
			}
		};

	private static CacheModel<Rule> _nullRuleCacheModel = new CacheModel<Rule>() {
			public Rule toEntityModel() {
				return _nullRule;
			}
		};
}