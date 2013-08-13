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

import com.beorn.onlinepayment.NoSuchSellerException;
import com.beorn.onlinepayment.model.Seller;
import com.beorn.onlinepayment.model.impl.SellerImpl;
import com.beorn.onlinepayment.model.impl.SellerModelImpl;

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
 * The persistence implementation for the seller service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Sebastien Meunier
 * @see SellerPersistence
 * @see SellerUtil
 * @generated
 */
public class SellerPersistenceImpl extends BasePersistenceImpl<Seller>
	implements SellerPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SellerUtil} to access the seller persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SellerImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(SellerModelImpl.ENTITY_CACHE_ENABLED,
			SellerModelImpl.FINDER_CACHE_ENABLED, SellerImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(SellerModelImpl.ENTITY_CACHE_ENABLED,
			SellerModelImpl.FINDER_CACHE_ENABLED, SellerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			SellerModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(SellerModelImpl.ENTITY_CACHE_ENABLED,
			SellerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(SellerModelImpl.ENTITY_CACHE_ENABLED,
			SellerModelImpl.FINDER_CACHE_ENABLED, SellerImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			SellerModelImpl.UUID_COLUMN_BITMASK |
			SellerModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(SellerModelImpl.ENTITY_CACHE_ENABLED,
			SellerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(SellerModelImpl.ENTITY_CACHE_ENABLED,
			SellerModelImpl.FINDER_CACHE_ENABLED, SellerImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(SellerModelImpl.ENTITY_CACHE_ENABLED,
			SellerModelImpl.FINDER_CACHE_ENABLED, SellerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			SellerModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(SellerModelImpl.ENTITY_CACHE_ENABLED,
			SellerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_COMPANYIDANDNAME = new FinderPath(SellerModelImpl.ENTITY_CACHE_ENABLED,
			SellerModelImpl.FINDER_CACHE_ENABLED, SellerImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByCompanyIdAndName",
			new String[] { Long.class.getName(), String.class.getName() },
			SellerModelImpl.COMPANYID_COLUMN_BITMASK |
			SellerModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYIDANDNAME = new FinderPath(SellerModelImpl.ENTITY_CACHE_ENABLED,
			SellerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCompanyIdAndName",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SellerModelImpl.ENTITY_CACHE_ENABLED,
			SellerModelImpl.FINDER_CACHE_ENABLED, SellerImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SellerModelImpl.ENTITY_CACHE_ENABLED,
			SellerModelImpl.FINDER_CACHE_ENABLED, SellerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SellerModelImpl.ENTITY_CACHE_ENABLED,
			SellerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the seller in the entity cache if it is enabled.
	 *
	 * @param seller the seller
	 */
	public void cacheResult(Seller seller) {
		EntityCacheUtil.putResult(SellerModelImpl.ENTITY_CACHE_ENABLED,
			SellerImpl.class, seller.getPrimaryKey(), seller);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { seller.getUuid(), Long.valueOf(seller.getGroupId()) },
			seller);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COMPANYIDANDNAME,
			new Object[] { Long.valueOf(seller.getCompanyId()), seller.getName() },
			seller);

		seller.resetOriginalValues();
	}

	/**
	 * Caches the sellers in the entity cache if it is enabled.
	 *
	 * @param sellers the sellers
	 */
	public void cacheResult(List<Seller> sellers) {
		for (Seller seller : sellers) {
			if (EntityCacheUtil.getResult(
						SellerModelImpl.ENTITY_CACHE_ENABLED, SellerImpl.class,
						seller.getPrimaryKey()) == null) {
				cacheResult(seller);
			}
			else {
				seller.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all sellers.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(SellerImpl.class.getName());
		}

		EntityCacheUtil.clearCache(SellerImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the seller.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Seller seller) {
		EntityCacheUtil.removeResult(SellerModelImpl.ENTITY_CACHE_ENABLED,
			SellerImpl.class, seller.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(seller);
	}

	@Override
	public void clearCache(List<Seller> sellers) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Seller seller : sellers) {
			EntityCacheUtil.removeResult(SellerModelImpl.ENTITY_CACHE_ENABLED,
				SellerImpl.class, seller.getPrimaryKey());

			clearUniqueFindersCache(seller);
		}
	}

	protected void clearUniqueFindersCache(Seller seller) {
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { seller.getUuid(), Long.valueOf(seller.getGroupId()) });

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_COMPANYIDANDNAME,
			new Object[] { Long.valueOf(seller.getCompanyId()), seller.getName() });
	}

	/**
	 * Creates a new seller with the primary key. Does not add the seller to the database.
	 *
	 * @param sellerId the primary key for the new seller
	 * @return the new seller
	 */
	public Seller create(long sellerId) {
		Seller seller = new SellerImpl();

		seller.setNew(true);
		seller.setPrimaryKey(sellerId);

		String uuid = PortalUUIDUtil.generate();

		seller.setUuid(uuid);

		return seller;
	}

	/**
	 * Removes the seller with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param sellerId the primary key of the seller
	 * @return the seller that was removed
	 * @throws com.beorn.onlinepayment.NoSuchSellerException if a seller with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Seller remove(long sellerId)
		throws NoSuchSellerException, SystemException {
		return remove(Long.valueOf(sellerId));
	}

	/**
	 * Removes the seller with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the seller
	 * @return the seller that was removed
	 * @throws com.beorn.onlinepayment.NoSuchSellerException if a seller with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Seller remove(Serializable primaryKey)
		throws NoSuchSellerException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Seller seller = (Seller)session.get(SellerImpl.class, primaryKey);

			if (seller == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSellerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(seller);
		}
		catch (NoSuchSellerException nsee) {
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
	protected Seller removeImpl(Seller seller) throws SystemException {
		seller = toUnwrappedModel(seller);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, seller);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(seller);

		return seller;
	}

	@Override
	public Seller updateImpl(com.beorn.onlinepayment.model.Seller seller,
		boolean merge) throws SystemException {
		seller = toUnwrappedModel(seller);

		boolean isNew = seller.isNew();

		SellerModelImpl sellerModelImpl = (SellerModelImpl)seller;

		if (Validator.isNull(seller.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			seller.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, seller, merge);

			seller.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !SellerModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((sellerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { sellerModelImpl.getOriginalUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { sellerModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((sellerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(sellerModelImpl.getOriginalCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] { Long.valueOf(sellerModelImpl.getCompanyId()) };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}
		}

		EntityCacheUtil.putResult(SellerModelImpl.ENTITY_CACHE_ENABLED,
			SellerImpl.class, seller.getPrimaryKey(), seller);

		if (isNew) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] { seller.getUuid(), Long.valueOf(
						seller.getGroupId()) }, seller);

			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COMPANYIDANDNAME,
				new Object[] {
					Long.valueOf(seller.getCompanyId()),
					
				seller.getName()
				}, seller);
		}
		else {
			if ((sellerModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						sellerModelImpl.getOriginalUuid(),
						Long.valueOf(sellerModelImpl.getOriginalGroupId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);

				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
					new Object[] {
						seller.getUuid(), Long.valueOf(seller.getGroupId())
					}, seller);
			}

			if ((sellerModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_COMPANYIDANDNAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(sellerModelImpl.getOriginalCompanyId()),
						
						sellerModelImpl.getOriginalName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYIDANDNAME,
					args);

				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_COMPANYIDANDNAME,
					args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COMPANYIDANDNAME,
					new Object[] {
						Long.valueOf(seller.getCompanyId()),
						
					seller.getName()
					}, seller);
			}
		}

		return seller;
	}

	protected Seller toUnwrappedModel(Seller seller) {
		if (seller instanceof SellerImpl) {
			return seller;
		}

		SellerImpl sellerImpl = new SellerImpl();

		sellerImpl.setNew(seller.isNew());
		sellerImpl.setPrimaryKey(seller.getPrimaryKey());

		sellerImpl.setUuid(seller.getUuid());
		sellerImpl.setCompanyId(seller.getCompanyId());
		sellerImpl.setGroupId(seller.getGroupId());
		sellerImpl.setUserId(seller.getUserId());
		sellerImpl.setSellerId(seller.getSellerId());
		sellerImpl.setCreateDate(seller.getCreateDate());
		sellerImpl.setModifiedDate(seller.getModifiedDate());
		sellerImpl.setName(seller.getName());
		sellerImpl.setActive(seller.isActive());

		return sellerImpl;
	}

	/**
	 * Returns the seller with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the seller
	 * @return the seller
	 * @throws com.liferay.portal.NoSuchModelException if a seller with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Seller findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the seller with the primary key or throws a {@link com.beorn.onlinepayment.NoSuchSellerException} if it could not be found.
	 *
	 * @param sellerId the primary key of the seller
	 * @return the seller
	 * @throws com.beorn.onlinepayment.NoSuchSellerException if a seller with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Seller findByPrimaryKey(long sellerId)
		throws NoSuchSellerException, SystemException {
		Seller seller = fetchByPrimaryKey(sellerId);

		if (seller == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + sellerId);
			}

			throw new NoSuchSellerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				sellerId);
		}

		return seller;
	}

	/**
	 * Returns the seller with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the seller
	 * @return the seller, or <code>null</code> if a seller with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Seller fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the seller with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param sellerId the primary key of the seller
	 * @return the seller, or <code>null</code> if a seller with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Seller fetchByPrimaryKey(long sellerId) throws SystemException {
		Seller seller = (Seller)EntityCacheUtil.getResult(SellerModelImpl.ENTITY_CACHE_ENABLED,
				SellerImpl.class, sellerId);

		if (seller == _nullSeller) {
			return null;
		}

		if (seller == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				seller = (Seller)session.get(SellerImpl.class,
						Long.valueOf(sellerId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (seller != null) {
					cacheResult(seller);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(SellerModelImpl.ENTITY_CACHE_ENABLED,
						SellerImpl.class, sellerId, _nullSeller);
				}

				closeSession(session);
			}
		}

		return seller;
	}

	/**
	 * Returns all the sellers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching sellers
	 * @throws SystemException if a system exception occurred
	 */
	public List<Seller> findByUuid(String uuid) throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sellers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of sellers
	 * @param end the upper bound of the range of sellers (not inclusive)
	 * @return the range of matching sellers
	 * @throws SystemException if a system exception occurred
	 */
	public List<Seller> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sellers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of sellers
	 * @param end the upper bound of the range of sellers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sellers
	 * @throws SystemException if a system exception occurred
	 */
	public List<Seller> findByUuid(String uuid, int start, int end,
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

		List<Seller> list = (List<Seller>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Seller seller : list) {
				if (!Validator.equals(uuid, seller.getUuid())) {
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

			query.append(_SQL_SELECT_SELLER_WHERE);

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
				query.append(SellerModelImpl.ORDER_BY_JPQL);
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

				list = (List<Seller>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first seller in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching seller
	 * @throws com.beorn.onlinepayment.NoSuchSellerException if a matching seller could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Seller findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchSellerException, SystemException {
		Seller seller = fetchByUuid_First(uuid, orderByComparator);

		if (seller != null) {
			return seller;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSellerException(msg.toString());
	}

	/**
	 * Returns the first seller in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching seller, or <code>null</code> if a matching seller could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Seller fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<Seller> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last seller in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching seller
	 * @throws com.beorn.onlinepayment.NoSuchSellerException if a matching seller could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Seller findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchSellerException, SystemException {
		Seller seller = fetchByUuid_Last(uuid, orderByComparator);

		if (seller != null) {
			return seller;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSellerException(msg.toString());
	}

	/**
	 * Returns the last seller in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching seller, or <code>null</code> if a matching seller could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Seller fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		List<Seller> list = findByUuid(uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sellers before and after the current seller in the ordered set where uuid = &#63;.
	 *
	 * @param sellerId the primary key of the current seller
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next seller
	 * @throws com.beorn.onlinepayment.NoSuchSellerException if a seller with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Seller[] findByUuid_PrevAndNext(long sellerId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchSellerException, SystemException {
		Seller seller = findByPrimaryKey(sellerId);

		Session session = null;

		try {
			session = openSession();

			Seller[] array = new SellerImpl[3];

			array[0] = getByUuid_PrevAndNext(session, seller, uuid,
					orderByComparator, true);

			array[1] = seller;

			array[2] = getByUuid_PrevAndNext(session, seller, uuid,
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

	protected Seller getByUuid_PrevAndNext(Session session, Seller seller,
		String uuid, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SELLER_WHERE);

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
			query.append(SellerModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(seller);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Seller> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the seller where uuid = &#63; and groupId = &#63; or throws a {@link com.beorn.onlinepayment.NoSuchSellerException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching seller
	 * @throws com.beorn.onlinepayment.NoSuchSellerException if a matching seller could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Seller findByUUID_G(String uuid, long groupId)
		throws NoSuchSellerException, SystemException {
		Seller seller = fetchByUUID_G(uuid, groupId);

		if (seller == null) {
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

			throw new NoSuchSellerException(msg.toString());
		}

		return seller;
	}

	/**
	 * Returns the seller where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching seller, or <code>null</code> if a matching seller could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Seller fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the seller where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching seller, or <code>null</code> if a matching seller could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Seller fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof Seller) {
			Seller seller = (Seller)result;

			if (!Validator.equals(uuid, seller.getUuid()) ||
					(groupId != seller.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SELLER_WHERE);

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

			query.append(SellerModelImpl.ORDER_BY_JPQL);

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

				List<Seller> list = q.list();

				result = list;

				Seller seller = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					seller = list.get(0);

					cacheResult(seller);

					if ((seller.getUuid() == null) ||
							!seller.getUuid().equals(uuid) ||
							(seller.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, seller);
					}
				}

				return seller;
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
				return (Seller)result;
			}
		}
	}

	/**
	 * Returns all the sellers where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching sellers
	 * @throws SystemException if a system exception occurred
	 */
	public List<Seller> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the sellers where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of sellers
	 * @param end the upper bound of the range of sellers (not inclusive)
	 * @return the range of matching sellers
	 * @throws SystemException if a system exception occurred
	 */
	public List<Seller> findByCompanyId(long companyId, int start, int end)
		throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sellers where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of sellers
	 * @param end the upper bound of the range of sellers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sellers
	 * @throws SystemException if a system exception occurred
	 */
	public List<Seller> findByCompanyId(long companyId, int start, int end,
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

		List<Seller> list = (List<Seller>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Seller seller : list) {
				if ((companyId != seller.getCompanyId())) {
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

			query.append(_SQL_SELECT_SELLER_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(SellerModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<Seller>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first seller in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching seller
	 * @throws com.beorn.onlinepayment.NoSuchSellerException if a matching seller could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Seller findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchSellerException, SystemException {
		Seller seller = fetchByCompanyId_First(companyId, orderByComparator);

		if (seller != null) {
			return seller;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSellerException(msg.toString());
	}

	/**
	 * Returns the first seller in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching seller, or <code>null</code> if a matching seller could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Seller fetchByCompanyId_First(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Seller> list = findByCompanyId(companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last seller in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching seller
	 * @throws com.beorn.onlinepayment.NoSuchSellerException if a matching seller could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Seller findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchSellerException, SystemException {
		Seller seller = fetchByCompanyId_Last(companyId, orderByComparator);

		if (seller != null) {
			return seller;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSellerException(msg.toString());
	}

	/**
	 * Returns the last seller in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching seller, or <code>null</code> if a matching seller could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Seller fetchByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByCompanyId(companyId);

		List<Seller> list = findByCompanyId(companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sellers before and after the current seller in the ordered set where companyId = &#63;.
	 *
	 * @param sellerId the primary key of the current seller
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next seller
	 * @throws com.beorn.onlinepayment.NoSuchSellerException if a seller with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Seller[] findByCompanyId_PrevAndNext(long sellerId, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchSellerException, SystemException {
		Seller seller = findByPrimaryKey(sellerId);

		Session session = null;

		try {
			session = openSession();

			Seller[] array = new SellerImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, seller, companyId,
					orderByComparator, true);

			array[1] = seller;

			array[2] = getByCompanyId_PrevAndNext(session, seller, companyId,
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

	protected Seller getByCompanyId_PrevAndNext(Session session, Seller seller,
		long companyId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SELLER_WHERE);

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
			query.append(SellerModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(seller);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Seller> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the seller where companyId = &#63; and name = &#63; or throws a {@link com.beorn.onlinepayment.NoSuchSellerException} if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the matching seller
	 * @throws com.beorn.onlinepayment.NoSuchSellerException if a matching seller could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Seller findByCompanyIdAndName(long companyId, String name)
		throws NoSuchSellerException, SystemException {
		Seller seller = fetchByCompanyIdAndName(companyId, name);

		if (seller == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", name=");
			msg.append(name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchSellerException(msg.toString());
		}

		return seller;
	}

	/**
	 * Returns the seller where companyId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the matching seller, or <code>null</code> if a matching seller could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Seller fetchByCompanyIdAndName(long companyId, String name)
		throws SystemException {
		return fetchByCompanyIdAndName(companyId, name, true);
	}

	/**
	 * Returns the seller where companyId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching seller, or <code>null</code> if a matching seller could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Seller fetchByCompanyIdAndName(long companyId, String name,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { companyId, name };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_COMPANYIDANDNAME,
					finderArgs, this);
		}

		if (result instanceof Seller) {
			Seller seller = (Seller)result;

			if ((companyId != seller.getCompanyId()) ||
					!Validator.equals(name, seller.getName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SELLER_WHERE);

			query.append(_FINDER_COLUMN_COMPANYIDANDNAME_COMPANYID_2);

			if (name == null) {
				query.append(_FINDER_COLUMN_COMPANYIDANDNAME_NAME_1);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_COMPANYIDANDNAME_NAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_COMPANYIDANDNAME_NAME_2);
				}
			}

			query.append(SellerModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (name != null) {
					qPos.add(name);
				}

				List<Seller> list = q.list();

				result = list;

				Seller seller = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COMPANYIDANDNAME,
						finderArgs, list);
				}
				else {
					seller = list.get(0);

					cacheResult(seller);

					if ((seller.getCompanyId() != companyId) ||
							(seller.getName() == null) ||
							!seller.getName().equals(name)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COMPANYIDANDNAME,
							finderArgs, seller);
					}
				}

				return seller;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_COMPANYIDANDNAME,
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
				return (Seller)result;
			}
		}
	}

	/**
	 * Returns all the sellers.
	 *
	 * @return the sellers
	 * @throws SystemException if a system exception occurred
	 */
	public List<Seller> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sellers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of sellers
	 * @param end the upper bound of the range of sellers (not inclusive)
	 * @return the range of sellers
	 * @throws SystemException if a system exception occurred
	 */
	public List<Seller> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the sellers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of sellers
	 * @param end the upper bound of the range of sellers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of sellers
	 * @throws SystemException if a system exception occurred
	 */
	public List<Seller> findAll(int start, int end,
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

		List<Seller> list = (List<Seller>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SELLER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SELLER.concat(SellerModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<Seller>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Seller>)QueryUtil.list(q, getDialect(), start,
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
	 * Removes all the sellers where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (Seller seller : findByUuid(uuid)) {
			remove(seller);
		}
	}

	/**
	 * Removes the seller where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the seller that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public Seller removeByUUID_G(String uuid, long groupId)
		throws NoSuchSellerException, SystemException {
		Seller seller = findByUUID_G(uuid, groupId);

		return remove(seller);
	}

	/**
	 * Removes all the sellers where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCompanyId(long companyId) throws SystemException {
		for (Seller seller : findByCompanyId(companyId)) {
			remove(seller);
		}
	}

	/**
	 * Removes the seller where companyId = &#63; and name = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the seller that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public Seller removeByCompanyIdAndName(long companyId, String name)
		throws NoSuchSellerException, SystemException {
		Seller seller = findByCompanyIdAndName(companyId, name);

		return remove(seller);
	}

	/**
	 * Removes all the sellers from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (Seller seller : findAll()) {
			remove(seller);
		}
	}

	/**
	 * Returns the number of sellers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching sellers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SELLER_WHERE);

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
	 * Returns the number of sellers where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching sellers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUUID_G(String uuid, long groupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID_G,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SELLER_WHERE);

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
	 * Returns the number of sellers where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching sellers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SELLER_WHERE);

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
	 * Returns the number of sellers where companyId = &#63; and name = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the number of matching sellers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCompanyIdAndName(long companyId, String name)
		throws SystemException {
		Object[] finderArgs = new Object[] { companyId, name };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYIDANDNAME,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SELLER_WHERE);

			query.append(_FINDER_COLUMN_COMPANYIDANDNAME_COMPANYID_2);

			if (name == null) {
				query.append(_FINDER_COLUMN_COMPANYIDANDNAME_NAME_1);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_COMPANYIDANDNAME_NAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_COMPANYIDANDNAME_NAME_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (name != null) {
					qPos.add(name);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COMPANYIDANDNAME,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of sellers.
	 *
	 * @return the number of sellers
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SELLER);

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
	 * Returns all the payment plugin configs associated with the seller.
	 *
	 * @param pk the primary key of the seller
	 * @return the payment plugin configs associated with the seller
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.beorn.onlinepayment.model.PaymentPluginConfig> getPaymentPluginConfigs(
		long pk) throws SystemException {
		return getPaymentPluginConfigs(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the payment plugin configs associated with the seller.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the seller
	 * @param start the lower bound of the range of sellers
	 * @param end the upper bound of the range of sellers (not inclusive)
	 * @return the range of payment plugin configs associated with the seller
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
	 * Returns an ordered range of all the payment plugin configs associated with the seller.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the seller
	 * @param start the lower bound of the range of sellers
	 * @param end the upper bound of the range of sellers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of payment plugin configs associated with the seller
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
	 * Returns the number of payment plugin configs associated with the seller.
	 *
	 * @param pk the primary key of the seller
	 * @return the number of payment plugin configs associated with the seller
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
	 * Returns <code>true</code> if the payment plugin config is associated with the seller.
	 *
	 * @param pk the primary key of the seller
	 * @param paymentPluginConfigPK the primary key of the payment plugin config
	 * @return <code>true</code> if the payment plugin config is associated with the seller; <code>false</code> otherwise
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
	 * Returns <code>true</code> if the seller has any payment plugin configs associated with it.
	 *
	 * @param pk the primary key of the seller to check for associations with payment plugin configs
	 * @return <code>true</code> if the seller has any payment plugin configs associated with it; <code>false</code> otherwise
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
	 * Returns all the transactions associated with the seller.
	 *
	 * @param pk the primary key of the seller
	 * @return the transactions associated with the seller
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.beorn.onlinepayment.model.Transaction> getTransactions(
		long pk) throws SystemException {
		return getTransactions(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the transactions associated with the seller.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the seller
	 * @param start the lower bound of the range of sellers
	 * @param end the upper bound of the range of sellers (not inclusive)
	 * @return the range of transactions associated with the seller
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.beorn.onlinepayment.model.Transaction> getTransactions(
		long pk, int start, int end) throws SystemException {
		return getTransactions(pk, start, end, null);
	}

	public static final FinderPath FINDER_PATH_GET_TRANSACTIONS = new FinderPath(com.beorn.onlinepayment.model.impl.TransactionModelImpl.ENTITY_CACHE_ENABLED,
			com.beorn.onlinepayment.model.impl.TransactionModelImpl.FINDER_CACHE_ENABLED,
			com.beorn.onlinepayment.model.impl.TransactionImpl.class,
			com.beorn.onlinepayment.service.persistence.TransactionPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"getTransactions",
			new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});

	static {
		FINDER_PATH_GET_TRANSACTIONS.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns an ordered range of all the transactions associated with the seller.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the seller
	 * @param start the lower bound of the range of sellers
	 * @param end the upper bound of the range of sellers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of transactions associated with the seller
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.beorn.onlinepayment.model.Transaction> getTransactions(
		long pk, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, start, end, orderByComparator };

		List<com.beorn.onlinepayment.model.Transaction> list = (List<com.beorn.onlinepayment.model.Transaction>)FinderCacheUtil.getResult(FINDER_PATH_GET_TRANSACTIONS,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				String sql = null;

				if (orderByComparator != null) {
					sql = _SQL_GETTRANSACTIONS.concat(ORDER_BY_CLAUSE)
											  .concat(orderByComparator.getOrderBy());
				}
				else {
					sql = _SQL_GETTRANSACTIONS.concat(com.beorn.onlinepayment.model.impl.TransactionModelImpl.ORDER_BY_SQL);
				}

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("Payment_Transaction",
					com.beorn.onlinepayment.model.impl.TransactionImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				list = (List<com.beorn.onlinepayment.model.Transaction>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_GET_TRANSACTIONS,
						finderArgs);
				}
				else {
					transactionPersistence.cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_GET_TRANSACTIONS,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	public static final FinderPath FINDER_PATH_GET_TRANSACTIONS_SIZE = new FinderPath(com.beorn.onlinepayment.model.impl.TransactionModelImpl.ENTITY_CACHE_ENABLED,
			com.beorn.onlinepayment.model.impl.TransactionModelImpl.FINDER_CACHE_ENABLED,
			com.beorn.onlinepayment.model.impl.TransactionImpl.class,
			com.beorn.onlinepayment.service.persistence.TransactionPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"getTransactionsSize", new String[] { Long.class.getName() });

	static {
		FINDER_PATH_GET_TRANSACTIONS_SIZE.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns the number of transactions associated with the seller.
	 *
	 * @param pk the primary key of the seller
	 * @return the number of transactions associated with the seller
	 * @throws SystemException if a system exception occurred
	 */
	public int getTransactionsSize(long pk) throws SystemException {
		Object[] finderArgs = new Object[] { pk };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_GET_TRANSACTIONS_SIZE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETTRANSACTIONSSIZE);

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

				FinderCacheUtil.putResult(FINDER_PATH_GET_TRANSACTIONS_SIZE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public static final FinderPath FINDER_PATH_CONTAINS_TRANSACTION = new FinderPath(com.beorn.onlinepayment.model.impl.TransactionModelImpl.ENTITY_CACHE_ENABLED,
			com.beorn.onlinepayment.model.impl.TransactionModelImpl.FINDER_CACHE_ENABLED,
			com.beorn.onlinepayment.model.impl.TransactionImpl.class,
			com.beorn.onlinepayment.service.persistence.TransactionPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"containsTransaction",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns <code>true</code> if the transaction is associated with the seller.
	 *
	 * @param pk the primary key of the seller
	 * @param transactionPK the primary key of the transaction
	 * @return <code>true</code> if the transaction is associated with the seller; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsTransaction(long pk, long transactionPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, transactionPK };

		Boolean value = (Boolean)FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_TRANSACTION,
				finderArgs, this);

		if (value == null) {
			try {
				value = Boolean.valueOf(containsTransaction.contains(pk,
							transactionPK));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (value == null) {
					value = Boolean.FALSE;
				}

				FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_TRANSACTION,
					finderArgs, value);
			}
		}

		return value.booleanValue();
	}

	/**
	 * Returns <code>true</code> if the seller has any transactions associated with it.
	 *
	 * @param pk the primary key of the seller to check for associations with transactions
	 * @return <code>true</code> if the seller has any transactions associated with it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsTransactions(long pk) throws SystemException {
		if (getTransactionsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Initializes the seller persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.beorn.onlinepayment.model.Seller")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Seller>> listenersList = new ArrayList<ModelListener<Seller>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Seller>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		containsPaymentPluginConfig = new ContainsPaymentPluginConfig();

		containsTransaction = new ContainsTransaction();
	}

	public void destroy() {
		EntityCacheUtil.removeCache(SellerImpl.class.getName());
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
	protected ContainsTransaction containsTransaction;

	protected class ContainsPaymentPluginConfig {
		protected ContainsPaymentPluginConfig() {
			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSPAYMENTPLUGINCONFIG,
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
					RowMapper.COUNT);
		}

		protected boolean contains(long sellerId, long paymentPluginConfigId) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						new Long(sellerId), new Long(paymentPluginConfigId)
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

	protected class ContainsTransaction {
		protected ContainsTransaction() {
			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSTRANSACTION,
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
					RowMapper.COUNT);
		}

		protected boolean contains(long sellerId, long transactionId) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						new Long(sellerId), new Long(transactionId)
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

	private static final String _SQL_SELECT_SELLER = "SELECT seller FROM Seller seller";
	private static final String _SQL_SELECT_SELLER_WHERE = "SELECT seller FROM Seller seller WHERE ";
	private static final String _SQL_COUNT_SELLER = "SELECT COUNT(seller) FROM Seller seller";
	private static final String _SQL_COUNT_SELLER_WHERE = "SELECT COUNT(seller) FROM Seller seller WHERE ";
	private static final String _SQL_GETPAYMENTPLUGINCONFIGS = "SELECT {Payment_PaymentPluginConfig.*} FROM Payment_PaymentPluginConfig INNER JOIN Payment_Seller ON (Payment_Seller.sellerId = Payment_PaymentPluginConfig.sellerId) WHERE (Payment_Seller.sellerId = ?)";
	private static final String _SQL_GETPAYMENTPLUGINCONFIGSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM Payment_PaymentPluginConfig WHERE sellerId = ?";
	private static final String _SQL_CONTAINSPAYMENTPLUGINCONFIG = "SELECT COUNT(*) AS COUNT_VALUE FROM Payment_PaymentPluginConfig WHERE sellerId = ? AND paymentPluginConfigId = ?";
	private static final String _SQL_GETTRANSACTIONS = "SELECT {Payment_Transaction.*} FROM Payment_Transaction INNER JOIN Payment_Seller ON (Payment_Seller.sellerId = Payment_Transaction.sellerId) WHERE (Payment_Seller.sellerId = ?)";
	private static final String _SQL_GETTRANSACTIONSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM Payment_Transaction WHERE sellerId = ?";
	private static final String _SQL_CONTAINSTRANSACTION = "SELECT COUNT(*) AS COUNT_VALUE FROM Payment_Transaction WHERE sellerId = ? AND transactionId = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "seller.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "seller.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(seller.uuid IS NULL OR seller.uuid = ?)";
	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "seller.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "seller.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(seller.uuid IS NULL OR seller.uuid = ?) AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "seller.groupId = ?";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "seller.companyId = ?";
	private static final String _FINDER_COLUMN_COMPANYIDANDNAME_COMPANYID_2 = "seller.companyId = ? AND ";
	private static final String _FINDER_COLUMN_COMPANYIDANDNAME_NAME_1 = "seller.name IS NULL";
	private static final String _FINDER_COLUMN_COMPANYIDANDNAME_NAME_2 = "seller.name = ?";
	private static final String _FINDER_COLUMN_COMPANYIDANDNAME_NAME_3 = "(seller.name IS NULL OR seller.name = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "seller.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Seller exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Seller exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(SellerPersistenceImpl.class);
	private static Seller _nullSeller = new SellerImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Seller> toCacheModel() {
				return _nullSellerCacheModel;
			}
		};

	private static CacheModel<Seller> _nullSellerCacheModel = new CacheModel<Seller>() {
			public Seller toEntityModel() {
				return _nullSeller;
			}
		};
}