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

package com.beorn.paypalpaymentplugin.service.persistence;

import com.beorn.paypalpaymentplugin.model.Token;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the token service. This utility wraps {@link TokenPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Sebastien Meunier
 * @see TokenPersistence
 * @see TokenPersistenceImpl
 * @generated
 */
public class TokenUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(Token token) {
		getPersistence().clearCache(token);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Token> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Token> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Token> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static Token update(Token token, boolean merge)
		throws SystemException {
		return getPersistence().update(token, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static Token update(Token token, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(token, merge, serviceContext);
	}

	/**
	* Caches the token in the entity cache if it is enabled.
	*
	* @param token the token
	*/
	public static void cacheResult(
		com.beorn.paypalpaymentplugin.model.Token token) {
		getPersistence().cacheResult(token);
	}

	/**
	* Caches the tokens in the entity cache if it is enabled.
	*
	* @param tokens the tokens
	*/
	public static void cacheResult(
		java.util.List<com.beorn.paypalpaymentplugin.model.Token> tokens) {
		getPersistence().cacheResult(tokens);
	}

	/**
	* Creates a new token with the primary key. Does not add the token to the database.
	*
	* @param tokenId the primary key for the new token
	* @return the new token
	*/
	public static com.beorn.paypalpaymentplugin.model.Token create(
		java.lang.String tokenId) {
		return getPersistence().create(tokenId);
	}

	/**
	* Removes the token with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param tokenId the primary key of the token
	* @return the token that was removed
	* @throws com.beorn.paypalpaymentplugin.NoSuchTokenException if a token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.paypalpaymentplugin.model.Token remove(
		java.lang.String tokenId)
		throws com.beorn.paypalpaymentplugin.NoSuchTokenException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(tokenId);
	}

	public static com.beorn.paypalpaymentplugin.model.Token updateImpl(
		com.beorn.paypalpaymentplugin.model.Token token, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(token, merge);
	}

	/**
	* Returns the token with the primary key or throws a {@link com.beorn.paypalpaymentplugin.NoSuchTokenException} if it could not be found.
	*
	* @param tokenId the primary key of the token
	* @return the token
	* @throws com.beorn.paypalpaymentplugin.NoSuchTokenException if a token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.paypalpaymentplugin.model.Token findByPrimaryKey(
		java.lang.String tokenId)
		throws com.beorn.paypalpaymentplugin.NoSuchTokenException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(tokenId);
	}

	/**
	* Returns the token with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param tokenId the primary key of the token
	* @return the token, or <code>null</code> if a token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.paypalpaymentplugin.model.Token fetchByPrimaryKey(
		java.lang.String tokenId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(tokenId);
	}

	/**
	* Returns all the tokens where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching tokens
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.paypalpaymentplugin.model.Token> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
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
	public static java.util.List<com.beorn.paypalpaymentplugin.model.Token> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
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
	public static java.util.List<com.beorn.paypalpaymentplugin.model.Token> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
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
	public static com.beorn.paypalpaymentplugin.model.Token findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.paypalpaymentplugin.NoSuchTokenException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first token in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching token, or <code>null</code> if a matching token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.paypalpaymentplugin.model.Token fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
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
	public static com.beorn.paypalpaymentplugin.model.Token findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.paypalpaymentplugin.NoSuchTokenException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last token in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching token, or <code>null</code> if a matching token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.paypalpaymentplugin.model.Token fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
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
	public static com.beorn.paypalpaymentplugin.model.Token[] findByUuid_PrevAndNext(
		java.lang.String tokenId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.paypalpaymentplugin.NoSuchTokenException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_PrevAndNext(tokenId, uuid, orderByComparator);
	}

	/**
	* Returns all the tokens where transactionId = &#63;.
	*
	* @param transactionId the transaction ID
	* @return the matching tokens
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.paypalpaymentplugin.model.Token> findByTransactionId(
		long transactionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTransactionId(transactionId);
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
	public static java.util.List<com.beorn.paypalpaymentplugin.model.Token> findByTransactionId(
		long transactionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTransactionId(transactionId, start, end);
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
	public static java.util.List<com.beorn.paypalpaymentplugin.model.Token> findByTransactionId(
		long transactionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTransactionId(transactionId, start, end,
			orderByComparator);
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
	public static com.beorn.paypalpaymentplugin.model.Token findByTransactionId_First(
		long transactionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.paypalpaymentplugin.NoSuchTokenException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTransactionId_First(transactionId, orderByComparator);
	}

	/**
	* Returns the first token in the ordered set where transactionId = &#63;.
	*
	* @param transactionId the transaction ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching token, or <code>null</code> if a matching token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.paypalpaymentplugin.model.Token fetchByTransactionId_First(
		long transactionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByTransactionId_First(transactionId, orderByComparator);
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
	public static com.beorn.paypalpaymentplugin.model.Token findByTransactionId_Last(
		long transactionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.paypalpaymentplugin.NoSuchTokenException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTransactionId_Last(transactionId, orderByComparator);
	}

	/**
	* Returns the last token in the ordered set where transactionId = &#63;.
	*
	* @param transactionId the transaction ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching token, or <code>null</code> if a matching token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.paypalpaymentplugin.model.Token fetchByTransactionId_Last(
		long transactionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByTransactionId_Last(transactionId, orderByComparator);
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
	public static com.beorn.paypalpaymentplugin.model.Token[] findByTransactionId_PrevAndNext(
		java.lang.String tokenId, long transactionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.paypalpaymentplugin.NoSuchTokenException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByTransactionId_PrevAndNext(tokenId, transactionId,
			orderByComparator);
	}

	/**
	* Returns all the tokens where status = &#63;.
	*
	* @param status the status
	* @return the matching tokens
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.paypalpaymentplugin.model.Token> findByStatus(
		long status) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByStatus(status);
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
	public static java.util.List<com.beorn.paypalpaymentplugin.model.Token> findByStatus(
		long status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByStatus(status, start, end);
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
	public static java.util.List<com.beorn.paypalpaymentplugin.model.Token> findByStatus(
		long status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByStatus(status, start, end, orderByComparator);
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
	public static com.beorn.paypalpaymentplugin.model.Token findByStatus_First(
		long status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.paypalpaymentplugin.NoSuchTokenException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByStatus_First(status, orderByComparator);
	}

	/**
	* Returns the first token in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching token, or <code>null</code> if a matching token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.paypalpaymentplugin.model.Token fetchByStatus_First(
		long status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByStatus_First(status, orderByComparator);
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
	public static com.beorn.paypalpaymentplugin.model.Token findByStatus_Last(
		long status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.paypalpaymentplugin.NoSuchTokenException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByStatus_Last(status, orderByComparator);
	}

	/**
	* Returns the last token in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching token, or <code>null</code> if a matching token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.beorn.paypalpaymentplugin.model.Token fetchByStatus_Last(
		long status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByStatus_Last(status, orderByComparator);
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
	public static com.beorn.paypalpaymentplugin.model.Token[] findByStatus_PrevAndNext(
		java.lang.String tokenId, long status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.beorn.paypalpaymentplugin.NoSuchTokenException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByStatus_PrevAndNext(tokenId, status, orderByComparator);
	}

	/**
	* Returns all the tokens.
	*
	* @return the tokens
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.beorn.paypalpaymentplugin.model.Token> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
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
	public static java.util.List<com.beorn.paypalpaymentplugin.model.Token> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
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
	public static java.util.List<com.beorn.paypalpaymentplugin.model.Token> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the tokens where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Removes all the tokens where transactionId = &#63; from the database.
	*
	* @param transactionId the transaction ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByTransactionId(long transactionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByTransactionId(transactionId);
	}

	/**
	* Removes all the tokens where status = &#63; from the database.
	*
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByStatus(long status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByStatus(status);
	}

	/**
	* Removes all the tokens from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of tokens where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching tokens
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the number of tokens where transactionId = &#63;.
	*
	* @param transactionId the transaction ID
	* @return the number of matching tokens
	* @throws SystemException if a system exception occurred
	*/
	public static int countByTransactionId(long transactionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByTransactionId(transactionId);
	}

	/**
	* Returns the number of tokens where status = &#63;.
	*
	* @param status the status
	* @return the number of matching tokens
	* @throws SystemException if a system exception occurred
	*/
	public static int countByStatus(long status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByStatus(status);
	}

	/**
	* Returns the number of tokens.
	*
	* @return the number of tokens
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static TokenPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (TokenPersistence)PortletBeanLocatorUtil.locate(com.beorn.paypalpaymentplugin.service.ClpSerializer.getServletContextName(),
					TokenPersistence.class.getName());

			ReferenceRegistry.registerReference(TokenUtil.class, "_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(TokenPersistence persistence) {
	}

	private static TokenPersistence _persistence;
}