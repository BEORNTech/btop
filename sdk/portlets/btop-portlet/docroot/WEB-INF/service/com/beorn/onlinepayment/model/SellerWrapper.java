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

import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Seller}.
 * </p>
 *
 * @author    Sebastien Meunier
 * @see       Seller
 * @generated
 */
public class SellerWrapper implements Seller, ModelWrapper<Seller> {
	public SellerWrapper(Seller seller) {
		_seller = seller;
	}

	public Class<?> getModelClass() {
		return Seller.class;
	}

	public String getModelClassName() {
		return Seller.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("sellerId", getSellerId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("active", getActive());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long sellerId = (Long)attributes.get("sellerId");

		if (sellerId != null) {
			setSellerId(sellerId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Boolean active = (Boolean)attributes.get("active");

		if (active != null) {
			setActive(active);
		}
	}

	/**
	* Returns the primary key of this seller.
	*
	* @return the primary key of this seller
	*/
	public long getPrimaryKey() {
		return _seller.getPrimaryKey();
	}

	/**
	* Sets the primary key of this seller.
	*
	* @param primaryKey the primary key of this seller
	*/
	public void setPrimaryKey(long primaryKey) {
		_seller.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this seller.
	*
	* @return the uuid of this seller
	*/
	public java.lang.String getUuid() {
		return _seller.getUuid();
	}

	/**
	* Sets the uuid of this seller.
	*
	* @param uuid the uuid of this seller
	*/
	public void setUuid(java.lang.String uuid) {
		_seller.setUuid(uuid);
	}

	/**
	* Returns the company ID of this seller.
	*
	* @return the company ID of this seller
	*/
	public long getCompanyId() {
		return _seller.getCompanyId();
	}

	/**
	* Sets the company ID of this seller.
	*
	* @param companyId the company ID of this seller
	*/
	public void setCompanyId(long companyId) {
		_seller.setCompanyId(companyId);
	}

	/**
	* Returns the group ID of this seller.
	*
	* @return the group ID of this seller
	*/
	public long getGroupId() {
		return _seller.getGroupId();
	}

	/**
	* Sets the group ID of this seller.
	*
	* @param groupId the group ID of this seller
	*/
	public void setGroupId(long groupId) {
		_seller.setGroupId(groupId);
	}

	/**
	* Returns the user ID of this seller.
	*
	* @return the user ID of this seller
	*/
	public long getUserId() {
		return _seller.getUserId();
	}

	/**
	* Sets the user ID of this seller.
	*
	* @param userId the user ID of this seller
	*/
	public void setUserId(long userId) {
		_seller.setUserId(userId);
	}

	/**
	* Returns the user uuid of this seller.
	*
	* @return the user uuid of this seller
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _seller.getUserUuid();
	}

	/**
	* Sets the user uuid of this seller.
	*
	* @param userUuid the user uuid of this seller
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_seller.setUserUuid(userUuid);
	}

	/**
	* Returns the seller ID of this seller.
	*
	* @return the seller ID of this seller
	*/
	public long getSellerId() {
		return _seller.getSellerId();
	}

	/**
	* Sets the seller ID of this seller.
	*
	* @param sellerId the seller ID of this seller
	*/
	public void setSellerId(long sellerId) {
		_seller.setSellerId(sellerId);
	}

	/**
	* Returns the create date of this seller.
	*
	* @return the create date of this seller
	*/
	public java.util.Date getCreateDate() {
		return _seller.getCreateDate();
	}

	/**
	* Sets the create date of this seller.
	*
	* @param createDate the create date of this seller
	*/
	public void setCreateDate(java.util.Date createDate) {
		_seller.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this seller.
	*
	* @return the modified date of this seller
	*/
	public java.util.Date getModifiedDate() {
		return _seller.getModifiedDate();
	}

	/**
	* Sets the modified date of this seller.
	*
	* @param modifiedDate the modified date of this seller
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_seller.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the name of this seller.
	*
	* @return the name of this seller
	*/
	public java.lang.String getName() {
		return _seller.getName();
	}

	/**
	* Sets the name of this seller.
	*
	* @param name the name of this seller
	*/
	public void setName(java.lang.String name) {
		_seller.setName(name);
	}

	/**
	* Returns the active of this seller.
	*
	* @return the active of this seller
	*/
	public boolean getActive() {
		return _seller.getActive();
	}

	/**
	* Returns <code>true</code> if this seller is active.
	*
	* @return <code>true</code> if this seller is active; <code>false</code> otherwise
	*/
	public boolean isActive() {
		return _seller.isActive();
	}

	/**
	* Sets whether this seller is active.
	*
	* @param active the active of this seller
	*/
	public void setActive(boolean active) {
		_seller.setActive(active);
	}

	public boolean isNew() {
		return _seller.isNew();
	}

	public void setNew(boolean n) {
		_seller.setNew(n);
	}

	public boolean isCachedModel() {
		return _seller.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_seller.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _seller.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _seller.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_seller.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _seller.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_seller.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SellerWrapper((Seller)_seller.clone());
	}

	public int compareTo(com.beorn.onlinepayment.model.Seller seller) {
		return _seller.compareTo(seller);
	}

	@Override
	public int hashCode() {
		return _seller.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.beorn.onlinepayment.model.Seller> toCacheModel() {
		return _seller.toCacheModel();
	}

	public com.beorn.onlinepayment.model.Seller toEscapedModel() {
		return new SellerWrapper(_seller.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _seller.toString();
	}

	public java.lang.String toXmlString() {
		return _seller.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_seller.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public Seller getWrappedSeller() {
		return _seller;
	}

	public Seller getWrappedModel() {
		return _seller;
	}

	public void resetOriginalValues() {
		_seller.resetOriginalValues();
	}

	private Seller _seller;
}