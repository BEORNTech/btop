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

package com.beorn.paypalpaymentplugin.model;

import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Token}.
 * </p>
 *
 * @author    Sebastien Meunier
 * @see       Token
 * @generated
 */
public class TokenWrapper implements Token, ModelWrapper<Token> {
	public TokenWrapper(Token token) {
		_token = token;
	}

	public Class<?> getModelClass() {
		return Token.class;
	}

	public String getModelClassName() {
		return Token.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("tokenId", getTokenId());
		attributes.put("transactionId", getTransactionId());
		attributes.put("status", getStatus());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String tokenId = (String)attributes.get("tokenId");

		if (tokenId != null) {
			setTokenId(tokenId);
		}

		Long transactionId = (Long)attributes.get("transactionId");

		if (transactionId != null) {
			setTransactionId(transactionId);
		}

		Long status = (Long)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	/**
	* Returns the primary key of this token.
	*
	* @return the primary key of this token
	*/
	public java.lang.String getPrimaryKey() {
		return _token.getPrimaryKey();
	}

	/**
	* Sets the primary key of this token.
	*
	* @param primaryKey the primary key of this token
	*/
	public void setPrimaryKey(java.lang.String primaryKey) {
		_token.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this token.
	*
	* @return the uuid of this token
	*/
	public java.lang.String getUuid() {
		return _token.getUuid();
	}

	/**
	* Sets the uuid of this token.
	*
	* @param uuid the uuid of this token
	*/
	public void setUuid(java.lang.String uuid) {
		_token.setUuid(uuid);
	}

	/**
	* Returns the create date of this token.
	*
	* @return the create date of this token
	*/
	public java.util.Date getCreateDate() {
		return _token.getCreateDate();
	}

	/**
	* Sets the create date of this token.
	*
	* @param createDate the create date of this token
	*/
	public void setCreateDate(java.util.Date createDate) {
		_token.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this token.
	*
	* @return the modified date of this token
	*/
	public java.util.Date getModifiedDate() {
		return _token.getModifiedDate();
	}

	/**
	* Sets the modified date of this token.
	*
	* @param modifiedDate the modified date of this token
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_token.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the token ID of this token.
	*
	* @return the token ID of this token
	*/
	public java.lang.String getTokenId() {
		return _token.getTokenId();
	}

	/**
	* Sets the token ID of this token.
	*
	* @param tokenId the token ID of this token
	*/
	public void setTokenId(java.lang.String tokenId) {
		_token.setTokenId(tokenId);
	}

	/**
	* Returns the transaction ID of this token.
	*
	* @return the transaction ID of this token
	*/
	public long getTransactionId() {
		return _token.getTransactionId();
	}

	/**
	* Sets the transaction ID of this token.
	*
	* @param transactionId the transaction ID of this token
	*/
	public void setTransactionId(long transactionId) {
		_token.setTransactionId(transactionId);
	}

	/**
	* Returns the status of this token.
	*
	* @return the status of this token
	*/
	public long getStatus() {
		return _token.getStatus();
	}

	/**
	* Sets the status of this token.
	*
	* @param status the status of this token
	*/
	public void setStatus(long status) {
		_token.setStatus(status);
	}

	public boolean isNew() {
		return _token.isNew();
	}

	public void setNew(boolean n) {
		_token.setNew(n);
	}

	public boolean isCachedModel() {
		return _token.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_token.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _token.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _token.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_token.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _token.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_token.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new TokenWrapper((Token)_token.clone());
	}

	public int compareTo(com.beorn.paypalpaymentplugin.model.Token token) {
		return _token.compareTo(token);
	}

	@Override
	public int hashCode() {
		return _token.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.beorn.paypalpaymentplugin.model.Token> toCacheModel() {
		return _token.toCacheModel();
	}

	public com.beorn.paypalpaymentplugin.model.Token toEscapedModel() {
		return new TokenWrapper(_token.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _token.toString();
	}

	public java.lang.String toXmlString() {
		return _token.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_token.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public Token getWrappedToken() {
		return _token;
	}

	public Token getWrappedModel() {
		return _token;
	}

	public void resetOriginalValues() {
		_token.resetOriginalValues();
	}

	private Token _token;
}