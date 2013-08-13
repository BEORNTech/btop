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

package com.beorn.paypalpaymentplugin.model.impl;

import com.beorn.paypalpaymentplugin.model.Token;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing Token in entity cache.
 *
 * @author Sebastien Meunier
 * @see Token
 * @generated
 */
public class TokenCacheModel implements CacheModel<Token>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", tokenId=");
		sb.append(tokenId);
		sb.append(", transactionId=");
		sb.append(transactionId);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	public Token toEntityModel() {
		TokenImpl tokenImpl = new TokenImpl();

		if (uuid == null) {
			tokenImpl.setUuid(StringPool.BLANK);
		}
		else {
			tokenImpl.setUuid(uuid);
		}

		if (createDate == Long.MIN_VALUE) {
			tokenImpl.setCreateDate(null);
		}
		else {
			tokenImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			tokenImpl.setModifiedDate(null);
		}
		else {
			tokenImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (tokenId == null) {
			tokenImpl.setTokenId(StringPool.BLANK);
		}
		else {
			tokenImpl.setTokenId(tokenId);
		}

		tokenImpl.setTransactionId(transactionId);
		tokenImpl.setStatus(status);

		tokenImpl.resetOriginalValues();

		return tokenImpl;
	}

	public String uuid;
	public long createDate;
	public long modifiedDate;
	public String tokenId;
	public long transactionId;
	public long status;
}