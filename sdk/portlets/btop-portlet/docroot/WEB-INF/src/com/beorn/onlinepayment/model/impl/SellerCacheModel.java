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

package com.beorn.onlinepayment.model.impl;

import com.beorn.onlinepayment.model.Seller;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing Seller in entity cache.
 *
 * @author Sebastien Meunier
 * @see Seller
 * @generated
 */
public class SellerCacheModel implements CacheModel<Seller>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", sellerId=");
		sb.append(sellerId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", name=");
		sb.append(name);
		sb.append(", active=");
		sb.append(active);
		sb.append("}");

		return sb.toString();
	}

	public Seller toEntityModel() {
		SellerImpl sellerImpl = new SellerImpl();

		if (uuid == null) {
			sellerImpl.setUuid(StringPool.BLANK);
		}
		else {
			sellerImpl.setUuid(uuid);
		}

		sellerImpl.setCompanyId(companyId);
		sellerImpl.setGroupId(groupId);
		sellerImpl.setUserId(userId);
		sellerImpl.setSellerId(sellerId);

		if (createDate == Long.MIN_VALUE) {
			sellerImpl.setCreateDate(null);
		}
		else {
			sellerImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			sellerImpl.setModifiedDate(null);
		}
		else {
			sellerImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			sellerImpl.setName(StringPool.BLANK);
		}
		else {
			sellerImpl.setName(name);
		}

		sellerImpl.setActive(active);

		sellerImpl.resetOriginalValues();

		return sellerImpl;
	}

	public String uuid;
	public long companyId;
	public long groupId;
	public long userId;
	public long sellerId;
	public long createDate;
	public long modifiedDate;
	public String name;
	public boolean active;
}