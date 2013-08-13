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

package com.beorn.onlinepayment.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.beorn.onlinepayment.service.http.SellerServiceSoap}.
 *
 * @author    Sebastien Meunier
 * @see       com.beorn.onlinepayment.service.http.SellerServiceSoap
 * @generated
 */
public class SellerSoap implements Serializable {
	public static SellerSoap toSoapModel(Seller model) {
		SellerSoap soapModel = new SellerSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setSellerId(model.getSellerId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setActive(model.getActive());

		return soapModel;
	}

	public static SellerSoap[] toSoapModels(Seller[] models) {
		SellerSoap[] soapModels = new SellerSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SellerSoap[][] toSoapModels(Seller[][] models) {
		SellerSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SellerSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SellerSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SellerSoap[] toSoapModels(List<Seller> models) {
		List<SellerSoap> soapModels = new ArrayList<SellerSoap>(models.size());

		for (Seller model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SellerSoap[soapModels.size()]);
	}

	public SellerSoap() {
	}

	public long getPrimaryKey() {
		return _sellerId;
	}

	public void setPrimaryKey(long pk) {
		setSellerId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public long getSellerId() {
		return _sellerId;
	}

	public void setSellerId(long sellerId) {
		_sellerId = sellerId;
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

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public boolean getActive() {
		return _active;
	}

	public boolean isActive() {
		return _active;
	}

	public void setActive(boolean active) {
		_active = active;
	}

	private String _uuid;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private long _sellerId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private boolean _active;
}