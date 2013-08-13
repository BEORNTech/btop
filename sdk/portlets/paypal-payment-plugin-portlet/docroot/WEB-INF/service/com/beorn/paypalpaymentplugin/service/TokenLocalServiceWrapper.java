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

package com.beorn.paypalpaymentplugin.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link TokenLocalService}.
 * </p>
 *
 * @author    Sebastien Meunier
 * @see       TokenLocalService
 * @generated
 */
public class TokenLocalServiceWrapper implements TokenLocalService,
	ServiceWrapper<TokenLocalService> {
	public TokenLocalServiceWrapper(TokenLocalService tokenLocalService) {
		_tokenLocalService = tokenLocalService;
	}

	/**
	* Adds the token to the database. Also notifies the appropriate model listeners.
	*
	* @param token the token
	* @return the token that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.paypalpaymentplugin.model.Token addToken(
		com.beorn.paypalpaymentplugin.model.Token token)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tokenLocalService.addToken(token);
	}

	/**
	* Creates a new token with the primary key. Does not add the token to the database.
	*
	* @param tokenId the primary key for the new token
	* @return the new token
	*/
	public com.beorn.paypalpaymentplugin.model.Token createToken(
		java.lang.String tokenId) {
		return _tokenLocalService.createToken(tokenId);
	}

	/**
	* Deletes the token with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param tokenId the primary key of the token
	* @return the token that was removed
	* @throws PortalException if a token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.paypalpaymentplugin.model.Token deleteToken(
		java.lang.String tokenId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _tokenLocalService.deleteToken(tokenId);
	}

	/**
	* Deletes the token from the database. Also notifies the appropriate model listeners.
	*
	* @param token the token
	* @return the token that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.paypalpaymentplugin.model.Token deleteToken(
		com.beorn.paypalpaymentplugin.model.Token token)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tokenLocalService.deleteToken(token);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _tokenLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tokenLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _tokenLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tokenLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tokenLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.beorn.paypalpaymentplugin.model.Token fetchToken(
		java.lang.String tokenId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tokenLocalService.fetchToken(tokenId);
	}

	/**
	* Returns the token with the primary key.
	*
	* @param tokenId the primary key of the token
	* @return the token
	* @throws PortalException if a token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.paypalpaymentplugin.model.Token getToken(
		java.lang.String tokenId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _tokenLocalService.getToken(tokenId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _tokenLocalService.getPersistedModel(primaryKeyObj);
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
	public java.util.List<com.beorn.paypalpaymentplugin.model.Token> getTokens(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tokenLocalService.getTokens(start, end);
	}

	/**
	* Returns the number of tokens.
	*
	* @return the number of tokens
	* @throws SystemException if a system exception occurred
	*/
	public int getTokensCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tokenLocalService.getTokensCount();
	}

	/**
	* Updates the token in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param token the token
	* @return the token that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.paypalpaymentplugin.model.Token updateToken(
		com.beorn.paypalpaymentplugin.model.Token token)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tokenLocalService.updateToken(token);
	}

	/**
	* Updates the token in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param token the token
	* @param merge whether to merge the token with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the token that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.paypalpaymentplugin.model.Token updateToken(
		com.beorn.paypalpaymentplugin.model.Token token, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _tokenLocalService.updateToken(token, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _tokenLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_tokenLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _tokenLocalService.invokeMethod(name, parameterTypes, arguments);
	}

	public com.beorn.paypalpaymentplugin.model.Token addToken(
		java.lang.String tokenId, long transactionId, long status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _tokenLocalService.addToken(tokenId, transactionId, status);
	}

	public com.beorn.paypalpaymentplugin.model.Token updateToken(
		java.lang.String tokenId, long transactionId, long status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _tokenLocalService.updateToken(tokenId, transactionId, status);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public TokenLocalService getWrappedTokenLocalService() {
		return _tokenLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedTokenLocalService(TokenLocalService tokenLocalService) {
		_tokenLocalService = tokenLocalService;
	}

	public TokenLocalService getWrappedService() {
		return _tokenLocalService;
	}

	public void setWrappedService(TokenLocalService tokenLocalService) {
		_tokenLocalService = tokenLocalService;
	}

	private TokenLocalService _tokenLocalService;
}