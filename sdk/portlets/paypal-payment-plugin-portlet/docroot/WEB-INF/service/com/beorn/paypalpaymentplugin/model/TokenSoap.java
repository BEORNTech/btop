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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author    Sebastien Meunier
 * @generated
 */
public class TokenSoap implements Serializable {
	public static TokenSoap toSoapModel(Token model) {
		TokenSoap soapModel = new TokenSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTokenId(model.getTokenId());
		soapModel.setTransactionId(model.getTransactionId());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static TokenSoap[] toSoapModels(Token[] models) {
		TokenSoap[] soapModels = new TokenSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TokenSoap[][] toSoapModels(Token[][] models) {
		TokenSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TokenSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TokenSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TokenSoap[] toSoapModels(List<Token> models) {
		List<TokenSoap> soapModels = new ArrayList<TokenSoap>(models.size());

		for (Token model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TokenSoap[soapModels.size()]);
	}

	public TokenSoap() {
	}

	public String getPrimaryKey() {
		return _tokenId;
	}

	public void setPrimaryKey(String pk) {
		setTokenId(pk);
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

	private String _uuid;
	private Date _createDate;
	private Date _modifiedDate;
	private String _tokenId;
	private long _transactionId;
	private long _status;
}