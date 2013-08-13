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

package com.beorn.paypalpaymentplugin.service.persistence;

import com.beorn.paypalpaymentplugin.NoSuchTokenException;
import com.beorn.paypalpaymentplugin.model.Token;
import com.beorn.paypalpaymentplugin.model.impl.TokenImpl;
import com.beorn.paypalpaymentplugin.model.impl.TokenModelImpl;

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
 * The persistence implementation for the token service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Sebastien Meunier
 * @see TokenPersistence
 * @see TokenUtil
 * @generated
 */
public class TokenPersistenceImpl extends BasePersistenceImpl<Token>
	implements TokenPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TokenUtil} to access the token persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TokenImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(TokenModelImpl.ENTITY_CACHE_ENABLED,
			TokenModelImpl.FINDER_CACHE_ENABLED, TokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(TokenModelImpl.ENTITY_CACHE_ENABLED,
			TokenModelImpl.FINDER_CACHE_ENABLED, TokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			TokenModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(TokenModelImpl.ENTITY_CACHE_ENABLED,
			TokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TRANSACTIONID =
		new FinderPath(TokenModelImpl.ENTITY_CACHE_ENABLED,
			TokenModelImpl.FINDER_CACHE_ENABLED, TokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTransactionId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TRANSACTIONID =
		new FinderPath(TokenModelImpl.ENTITY_CACHE_ENABLED,
			TokenModelImpl.FINDER_CACHE_ENABLED, TokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTransactionId",
			new String[] { Long.class.getName() },
			TokenModelImpl.TRANSACTIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TRANSACTIONID = new FinderPath(TokenModelImpl.ENTITY_CACHE_ENABLED,
			TokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTransactionId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_STATUS = new FinderPath(TokenModelImpl.ENTITY_CACHE_ENABLED,
			TokenModelImpl.FINDER_CACHE_ENABLED, TokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByStatus",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS =
		new FinderPath(TokenModelImpl.ENTITY_CACHE_ENABLED,
			TokenModelImpl.FINDER_CACHE_ENABLED, TokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByStatus",
			new String[] { Long.class.getName() },
			TokenModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_STATUS = new FinderPath(TokenModelImpl.ENTITY_CACHE_ENABLED,
			TokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByStatus",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TokenModelImpl.ENTITY_CACHE_ENABLED,
			TokenModelImpl.FINDER_CACHE_ENABLED, TokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TokenModelImpl.ENTITY_CACHE_ENABLED,
			TokenModelImpl.FINDER_CACHE_ENABLED, TokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TokenModelImpl.ENTITY_CACHE_ENABLED,
			TokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the token in the entity cache if it is enabled.
	 *
	 * @param token the token
	 */
	public void cacheResult(Token token) {
		EntityCacheUtil.putResult(TokenModelImpl.ENTITY_CACHE_ENABLED,
			TokenImpl.class, token.getPrimaryKey(), token);

		token.resetOriginalValues();
	}

	/**
	 * Caches the tokens in the entity cache if it is enabled.
	 *
	 * @param tokens the tokens
	 */
	public void cacheResult(List<Token> tokens) {
		for (Token token : tokens) {
			if (EntityCacheUtil.getResult(TokenModelImpl.ENTITY_CACHE_ENABLED,
						TokenImpl.class, token.getPrimaryKey()) == null) {
				cacheResult(token);
			}
			else {
				token.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all tokens.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(TokenImpl.class.getName());
		}

		EntityCacheUtil.clearCache(TokenImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the token.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Token token) {
		EntityCacheUtil.removeResult(TokenModelImpl.ENTITY_CACHE_ENABLED,
			TokenImpl.class, token.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Token> tokens) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Token token : tokens) {
			EntityCacheUtil.removeResult(TokenModelImpl.ENTITY_CACHE_ENABLED,
				TokenImpl.class, token.getPrimaryKey());
		}
	}

	/**
	 * Creates a new token with the primary key. Does not add the token to the database.
	 *
	 * @param tokenId the primary key for the new token
	 * @return the new token
	 */
	public Token create(String tokenId) {
		Token token = new TokenImpl();

		token.setNew(true);
		token.setPrimaryKey(tokenId);

		String uuid = PortalUUIDUtil.generate();

		token.setUuid(uuid);

		return token;
	}

	/**
	 * Removes the token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param tokenId the primary key of the token
	 * @return the token that was removed
	 * @throws com.beorn.paypalpaymentplugin.NoSuchTokenException if a token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Token remove(String tokenId)
		throws NoSuchTokenException, SystemException {
		return remove((Serializable)tokenId);
	}

	/**
	 * Removes the token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the token
	 * @return the token that was removed
	 * @throws com.beorn.paypalpaymentplugin.NoSuchTokenException if a token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Token remove(Serializable primaryKey)
		throws NoSuchTokenException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Token token = (Token)session.get(TokenImpl.class, primaryKey);

			if (token == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTokenException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(token);
		}
		catch (NoSuchTokenException nsee) {
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
	protected Token removeImpl(Token token) throws SystemException {
		token = toUnwrappedModel(token);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, token);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(token);

		return token;
	}

	@Override
	public Token updateImpl(com.beorn.paypalpaymentplugin.model.Token token,
		boolean merge) throws SystemException {
		token = toUnwrappedModel(token);

		boolean isNew = token.isNew();

		TokenModelImpl tokenModelImpl = (TokenModelImpl)token;

		if (Validator.isNull(token.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			token.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, token, merge);

			token.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !TokenModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((tokenModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { tokenModelImpl.getOriginalUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { tokenModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((tokenModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TRANSACTIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(tokenModelImpl.getOriginalTransactionId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TRANSACTIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TRANSACTIONID,
					args);

				args = new Object[] {
						Long.valueOf(tokenModelImpl.getTransactionId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TRANSACTIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TRANSACTIONID,
					args);
			}

			if ((tokenModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(tokenModelImpl.getOriginalStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_STATUS, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS,
					args);

				args = new Object[] { Long.valueOf(tokenModelImpl.getStatus()) };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_STATUS, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS,
					args);
			}
		}

		EntityCacheUtil.putResult(TokenModelImpl.ENTITY_CACHE_ENABLED,
			TokenImpl.class, token.getPrimaryKey(), token);

		return token;
	}

	protected Token toUnwrappedModel(Token token) {
		if (token instanceof TokenImpl) {
			return token;
		}

		TokenImpl tokenImpl = new TokenImpl();

		tokenImpl.setNew(token.isNew());
		tokenImpl.setPrimaryKey(token.getPrimaryKey());

		tokenImpl.setUuid(token.getUuid());
		tokenImpl.setCreateDate(token.getCreateDate());
		tokenImpl.setModifiedDate(token.getModifiedDate());
		tokenImpl.setTokenId(token.getTokenId());
		tokenImpl.setTransactionId(token.getTransactionId());
		tokenImpl.setStatus(token.getStatus());

		return tokenImpl;
	}

	/**
	 * Returns the token with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the token
	 * @return the token
	 * @throws com.liferay.portal.NoSuchModelException if a token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Token findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey((String)primaryKey);
	}

	/**
	 * Returns the token with the primary key or throws a {@link com.beorn.paypalpaymentplugin.NoSuchTokenException} if it could not be found.
	 *
	 * @param tokenId the primary key of the token
	 * @return the token
	 * @throws com.beorn.paypalpaymentplugin.NoSuchTokenException if a token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Token findByPrimaryKey(String tokenId)
		throws NoSuchTokenException, SystemException {
		Token token = fetchByPrimaryKey(tokenId);

		if (token == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + tokenId);
			}

			throw new NoSuchTokenException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				tokenId);
		}

		return token;
	}

	/**
	 * Returns the token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the token
	 * @return the token, or <code>null</code> if a token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Token fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey((String)primaryKey);
	}

	/**
	 * Returns the token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param tokenId the primary key of the token
	 * @return the token, or <code>null</code> if a token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Token fetchByPrimaryKey(String tokenId) throws SystemException {
		Token token = (Token)EntityCacheUtil.getResult(TokenModelImpl.ENTITY_CACHE_ENABLED,
				TokenImpl.class, tokenId);

		if (token == _nullToken) {
			return null;
		}

		if (token == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				token = (Token)session.get(TokenImpl.class, tokenId);
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (token != null) {
					cacheResult(token);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(TokenModelImpl.ENTITY_CACHE_ENABLED,
						TokenImpl.class, tokenId, _nullToken);
				}

				closeSession(session);
			}
		}

		return token;
	}

	/**
	 * Returns all the tokens where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<Token> findByUuid(String uuid) throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tokens where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of tokens
	 * @param end the upper bound of the range of tokens (not inclusive)
	 * @return the range of matching tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<Token> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tokens where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of tokens
	 * @param end the upper bound of the range of tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<Token> findByUuid(String uuid, int start, int end,
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

		List<Token> list = (List<Token>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Token token : list) {
				if (!Validator.equals(uuid, token.getUuid())) {
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

			query.append(_SQL_SELECT_TOKEN_WHERE);

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
				query.append(TokenModelImpl.ORDER_BY_JPQL);
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

				list = (List<Token>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first token in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching token
	 * @throws com.beorn.paypalpaymentplugin.NoSuchTokenException if a matching token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Token findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchTokenException, SystemException {
		Token token = fetchByUuid_First(uuid, orderByComparator);

		if (token != null) {
			return token;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTokenException(msg.toString());
	}

	/**
	 * Returns the first token in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching token, or <code>null</code> if a matching token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Token fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<Token> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last token in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching token
	 * @throws com.beorn.paypalpaymentplugin.NoSuchTokenException if a matching token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Token findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchTokenException, SystemException {
		Token token = fetchByUuid_Last(uuid, orderByComparator);

		if (token != null) {
			return token;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTokenException(msg.toString());
	}

	/**
	 * Returns the last token in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching token, or <code>null</code> if a matching token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Token fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		List<Token> list = findByUuid(uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the tokens before and after the current token in the ordered set where uuid = &#63;.
	 *
	 * @param tokenId the primary key of the current token
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next token
	 * @throws com.beorn.paypalpaymentplugin.NoSuchTokenException if a token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Token[] findByUuid_PrevAndNext(String tokenId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchTokenException, SystemException {
		Token token = findByPrimaryKey(tokenId);

		Session session = null;

		try {
			session = openSession();

			Token[] array = new TokenImpl[3];

			array[0] = getByUuid_PrevAndNext(session, token, uuid,
					orderByComparator, true);

			array[1] = token;

			array[2] = getByUuid_PrevAndNext(session, token, uuid,
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

	protected Token getByUuid_PrevAndNext(Session session, Token token,
		String uuid, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TOKEN_WHERE);

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
			query.append(TokenModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(token);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Token> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the tokens where transactionId = &#63;.
	 *
	 * @param transactionId the transaction ID
	 * @return the matching tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<Token> findByTransactionId(long transactionId)
		throws SystemException {
		return findByTransactionId(transactionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tokens where transactionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param transactionId the transaction ID
	 * @param start the lower bound of the range of tokens
	 * @param end the upper bound of the range of tokens (not inclusive)
	 * @return the range of matching tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<Token> findByTransactionId(long transactionId, int start,
		int end) throws SystemException {
		return findByTransactionId(transactionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tokens where transactionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param transactionId the transaction ID
	 * @param start the lower bound of the range of tokens
	 * @param end the upper bound of the range of tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<Token> findByTransactionId(long transactionId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TRANSACTIONID;
			finderArgs = new Object[] { transactionId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TRANSACTIONID;
			finderArgs = new Object[] {
					transactionId,
					
					start, end, orderByComparator
				};
		}

		List<Token> list = (List<Token>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Token token : list) {
				if ((transactionId != token.getTransactionId())) {
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

			query.append(_SQL_SELECT_TOKEN_WHERE);

			query.append(_FINDER_COLUMN_TRANSACTIONID_TRANSACTIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TokenModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(transactionId);

				list = (List<Token>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first token in the ordered set where transactionId = &#63;.
	 *
	 * @param transactionId the transaction ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching token
	 * @throws com.beorn.paypalpaymentplugin.NoSuchTokenException if a matching token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Token findByTransactionId_First(long transactionId,
		OrderByComparator orderByComparator)
		throws NoSuchTokenException, SystemException {
		Token token = fetchByTransactionId_First(transactionId,
				orderByComparator);

		if (token != null) {
			return token;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("transactionId=");
		msg.append(transactionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTokenException(msg.toString());
	}

	/**
	 * Returns the first token in the ordered set where transactionId = &#63;.
	 *
	 * @param transactionId the transaction ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching token, or <code>null</code> if a matching token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Token fetchByTransactionId_First(long transactionId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Token> list = findByTransactionId(transactionId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last token in the ordered set where transactionId = &#63;.
	 *
	 * @param transactionId the transaction ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching token
	 * @throws com.beorn.paypalpaymentplugin.NoSuchTokenException if a matching token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Token findByTransactionId_Last(long transactionId,
		OrderByComparator orderByComparator)
		throws NoSuchTokenException, SystemException {
		Token token = fetchByTransactionId_Last(transactionId, orderByComparator);

		if (token != null) {
			return token;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("transactionId=");
		msg.append(transactionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTokenException(msg.toString());
	}

	/**
	 * Returns the last token in the ordered set where transactionId = &#63;.
	 *
	 * @param transactionId the transaction ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching token, or <code>null</code> if a matching token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Token fetchByTransactionId_Last(long transactionId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByTransactionId(transactionId);

		List<Token> list = findByTransactionId(transactionId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the tokens before and after the current token in the ordered set where transactionId = &#63;.
	 *
	 * @param tokenId the primary key of the current token
	 * @param transactionId the transaction ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next token
	 * @throws com.beorn.paypalpaymentplugin.NoSuchTokenException if a token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Token[] findByTransactionId_PrevAndNext(String tokenId,
		long transactionId, OrderByComparator orderByComparator)
		throws NoSuchTokenException, SystemException {
		Token token = findByPrimaryKey(tokenId);

		Session session = null;

		try {
			session = openSession();

			Token[] array = new TokenImpl[3];

			array[0] = getByTransactionId_PrevAndNext(session, token,
					transactionId, orderByComparator, true);

			array[1] = token;

			array[2] = getByTransactionId_PrevAndNext(session, token,
					transactionId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Token getByTransactionId_PrevAndNext(Session session,
		Token token, long transactionId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TOKEN_WHERE);

		query.append(_FINDER_COLUMN_TRANSACTIONID_TRANSACTIONID_2);

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
			query.append(TokenModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(transactionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(token);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Token> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the tokens where status = &#63;.
	 *
	 * @param status the status
	 * @return the matching tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<Token> findByStatus(long status) throws SystemException {
		return findByStatus(status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tokens where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of tokens
	 * @param end the upper bound of the range of tokens (not inclusive)
	 * @return the range of matching tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<Token> findByStatus(long status, int start, int end)
		throws SystemException {
		return findByStatus(status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tokens where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of tokens
	 * @param end the upper bound of the range of tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<Token> findByStatus(long status, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS;
			finderArgs = new Object[] { status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_STATUS;
			finderArgs = new Object[] { status, start, end, orderByComparator };
		}

		List<Token> list = (List<Token>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Token token : list) {
				if ((status != token.getStatus())) {
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

			query.append(_SQL_SELECT_TOKEN_WHERE);

			query.append(_FINDER_COLUMN_STATUS_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TokenModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(status);

				list = (List<Token>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first token in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching token
	 * @throws com.beorn.paypalpaymentplugin.NoSuchTokenException if a matching token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Token findByStatus_First(long status,
		OrderByComparator orderByComparator)
		throws NoSuchTokenException, SystemException {
		Token token = fetchByStatus_First(status, orderByComparator);

		if (token != null) {
			return token;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTokenException(msg.toString());
	}

	/**
	 * Returns the first token in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching token, or <code>null</code> if a matching token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Token fetchByStatus_First(long status,
		OrderByComparator orderByComparator) throws SystemException {
		List<Token> list = findByStatus(status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last token in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching token
	 * @throws com.beorn.paypalpaymentplugin.NoSuchTokenException if a matching token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Token findByStatus_Last(long status,
		OrderByComparator orderByComparator)
		throws NoSuchTokenException, SystemException {
		Token token = fetchByStatus_Last(status, orderByComparator);

		if (token != null) {
			return token;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTokenException(msg.toString());
	}

	/**
	 * Returns the last token in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching token, or <code>null</code> if a matching token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Token fetchByStatus_Last(long status,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByStatus(status);

		List<Token> list = findByStatus(status, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the tokens before and after the current token in the ordered set where status = &#63;.
	 *
	 * @param tokenId the primary key of the current token
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next token
	 * @throws com.beorn.paypalpaymentplugin.NoSuchTokenException if a token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Token[] findByStatus_PrevAndNext(String tokenId, long status,
		OrderByComparator orderByComparator)
		throws NoSuchTokenException, SystemException {
		Token token = findByPrimaryKey(tokenId);

		Session session = null;

		try {
			session = openSession();

			Token[] array = new TokenImpl[3];

			array[0] = getByStatus_PrevAndNext(session, token, status,
					orderByComparator, true);

			array[1] = token;

			array[2] = getByStatus_PrevAndNext(session, token, status,
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

	protected Token getByStatus_PrevAndNext(Session session, Token token,
		long status, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TOKEN_WHERE);

		query.append(_FINDER_COLUMN_STATUS_STATUS_2);

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
			query.append(TokenModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(token);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Token> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the tokens.
	 *
	 * @return the tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<Token> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of tokens
	 * @param end the upper bound of the range of tokens (not inclusive)
	 * @return the range of tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<Token> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of tokens
	 * @param end the upper bound of the range of tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<Token> findAll(int start, int end,
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

		List<Token> list = (List<Token>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TOKEN);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TOKEN.concat(TokenModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<Token>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Token>)QueryUtil.list(q, getDialect(), start,
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
	 * Removes all the tokens where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (Token token : findByUuid(uuid)) {
			remove(token);
		}
	}

	/**
	 * Removes all the tokens where transactionId = &#63; from the database.
	 *
	 * @param transactionId the transaction ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByTransactionId(long transactionId)
		throws SystemException {
		for (Token token : findByTransactionId(transactionId)) {
			remove(token);
		}
	}

	/**
	 * Removes all the tokens where status = &#63; from the database.
	 *
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByStatus(long status) throws SystemException {
		for (Token token : findByStatus(status)) {
			remove(token);
		}
	}

	/**
	 * Removes all the tokens from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (Token token : findAll()) {
			remove(token);
		}
	}

	/**
	 * Returns the number of tokens where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching tokens
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TOKEN_WHERE);

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
	 * Returns the number of tokens where transactionId = &#63;.
	 *
	 * @param transactionId the transaction ID
	 * @return the number of matching tokens
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTransactionId(long transactionId)
		throws SystemException {
		Object[] finderArgs = new Object[] { transactionId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TRANSACTIONID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TOKEN_WHERE);

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
	 * Returns the number of tokens where status = &#63;.
	 *
	 * @param status the status
	 * @return the number of matching tokens
	 * @throws SystemException if a system exception occurred
	 */
	public int countByStatus(long status) throws SystemException {
		Object[] finderArgs = new Object[] { status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_STATUS,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TOKEN_WHERE);

			query.append(_FINDER_COLUMN_STATUS_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(status);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_STATUS,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of tokens.
	 *
	 * @return the number of tokens
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TOKEN);

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
	 * Initializes the token persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.beorn.paypalpaymentplugin.model.Token")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Token>> listenersList = new ArrayList<ModelListener<Token>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Token>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(TokenImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = TokenPersistence.class)
	protected TokenPersistence tokenPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_TOKEN = "SELECT token FROM Token token";
	private static final String _SQL_SELECT_TOKEN_WHERE = "SELECT token FROM Token token WHERE ";
	private static final String _SQL_COUNT_TOKEN = "SELECT COUNT(token) FROM Token token";
	private static final String _SQL_COUNT_TOKEN_WHERE = "SELECT COUNT(token) FROM Token token WHERE ";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "token.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "token.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(token.uuid IS NULL OR token.uuid = ?)";
	private static final String _FINDER_COLUMN_TRANSACTIONID_TRANSACTIONID_2 = "token.transactionId = ?";
	private static final String _FINDER_COLUMN_STATUS_STATUS_2 = "token.status = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "token.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Token exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Token exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(TokenPersistenceImpl.class);
	private static Token _nullToken = new TokenImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Token> toCacheModel() {
				return _nullTokenCacheModel;
			}
		};

	private static CacheModel<Token> _nullTokenCacheModel = new CacheModel<Token>() {
			public Token toEntityModel() {
				return _nullToken;
			}
		};
}