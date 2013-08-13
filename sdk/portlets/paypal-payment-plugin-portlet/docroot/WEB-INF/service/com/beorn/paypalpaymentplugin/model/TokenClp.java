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

import com.beorn.paypalpaymentplugin.service.TokenLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sebastien Meunier
 */
public class TokenClp extends BaseModelImpl<Token> implements Token {
	public TokenClp() {
	}

	public Class<?> getModelClass() {
		return Token.class;
	}

	public String getModelClassName() {
		return Token.class.getName();
	}

	public String getPrimaryKey() {
		return _tokenId;
	}

	public void setPrimaryKey(String primaryKey) {
		setTokenId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return _tokenId;
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((String)primaryKeyObj);
	}

	@Override
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

	@Override
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

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getTokenId() {
		return _tokenId;
	}

	public void setTokenId(String tokenId) {
		_tokenId = tokenId;
	}

	public long getTransactionId() {
		return _transactionId;
	}

	public void setTransactionId(long transactionId) {
		_transactionId = transactionId;
	}

	public long getStatus() {
		return _status;
	}

	public void setStatus(long status) {
		_status = status;
	}

	public BaseModel<?> getTokenRemoteModel() {
		return _tokenRemoteModel;
	}

	public void setTokenRemoteModel(BaseModel<?> tokenRemoteModel) {
		_tokenRemoteModel = tokenRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			TokenLocalServiceUtil.addToken(this);
		}
		else {
			TokenLocalServiceUtil.updateToken(this);
		}
	}

	@Override
	public Token toEscapedModel() {
		return (Token)Proxy.newProxyInstance(Token.class.getClassLoader(),
			new Class[] { Token.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		TokenClp clone = new TokenClp();

		clone.setUuid(getUuid());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setTokenId(getTokenId());
		clone.setTransactionId(getTransactionId());
		clone.setStatus(getStatus());

		return clone;
	}

	public int compareTo(Token token) {
		int value = 0;

		if (getTransactionId() < token.getTransactionId()) {
			value = -1;
		}
		else if (getTransactionId() > token.getTransactionId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		TokenClp token = null;

		try {
			token = (TokenClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		String primaryKey = token.getPrimaryKey();

		if (getPrimaryKey().equals(primaryKey)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return getPrimaryKey().hashCode();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", tokenId=");
		sb.append(getTokenId());
		sb.append(", transactionId=");
		sb.append(getTransactionId());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(22);

		sb.append("<model><model-name>");
		sb.append("com.beorn.paypalpaymentplugin.model.Token");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>tokenId</column-name><column-value><![CDATA[");
		sb.append(getTokenId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>transactionId</column-name><column-value><![CDATA[");
		sb.append(getTransactionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _uuid;
	private Date _createDate;
	private Date _modifiedDate;
	private String _tokenId;
	private long _transactionId;
	private long _status;
	private BaseModel<?> _tokenRemoteModel;
}