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

import com.beorn.onlinepayment.model.OrderTransaction;
import com.beorn.onlinepayment.model.OrderTransactionModel;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the OrderTransaction service. Represents a row in the &quot;Payment_OrderTransaction&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.beorn.onlinepayment.model.OrderTransactionModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link OrderTransactionImpl}.
 * </p>
 *
 * @author Sebastien Meunier
 * @see OrderTransactionImpl
 * @see com.beorn.onlinepayment.model.OrderTransaction
 * @see com.beorn.onlinepayment.model.OrderTransactionModel
 * @generated
 */
public class OrderTransactionModelImpl extends BaseModelImpl<OrderTransaction>
	implements OrderTransactionModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a order transaction model instance should use the {@link com.beorn.onlinepayment.model.OrderTransaction} interface instead.
	 */
	public static final String TABLE_NAME = "Payment_OrderTransaction";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "orderId", Types.BIGINT },
			{ "transactionId", Types.BIGINT }
		};
	public static final String TABLE_SQL_CREATE = "create table Payment_OrderTransaction (uuid_ VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,orderId LONG not null primary key,transactionId LONG)";
	public static final String TABLE_SQL_DROP = "drop table Payment_OrderTransaction";
	public static final String ORDER_BY_JPQL = " ORDER BY orderTransaction.orderId DESC";
	public static final String ORDER_BY_SQL = " ORDER BY Payment_OrderTransaction.orderId DESC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.beorn.onlinepayment.model.OrderTransaction"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.beorn.onlinepayment.model.OrderTransaction"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.beorn.onlinepayment.model.OrderTransaction"),
			true);
	public static long TRANSACTIONID_COLUMN_BITMASK = 1L;
	public static long UUID_COLUMN_BITMASK = 2L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.beorn.onlinepayment.model.OrderTransaction"));

	public OrderTransactionModelImpl() {
	}

	public long getPrimaryKey() {
		return _orderId;
	}

	public void setPrimaryKey(long primaryKey) {
		setOrderId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_orderId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public Class<?> getModelClass() {
		return OrderTransaction.class;
	}

	public String getModelClassName() {
		return OrderTransaction.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("orderId", getOrderId());
		attributes.put("transactionId", getTransactionId());

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

		Long orderId = (Long)attributes.get("orderId");

		if (orderId != null) {
			setOrderId(orderId);
		}

		Long transactionId = (Long)attributes.get("transactionId");

		if (transactionId != null) {
			setTransactionId(transactionId);
		}
	}

	public String getUuid() {
		if (_uuid == null) {
			return StringPool.BLANK;
		}
		else {
			return _uuid;
		}
	}

	public void setUuid(String uuid) {
		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
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

	public long getOrderId() {
		return _orderId;
	}

	public void setOrderId(long orderId) {
		_columnBitmask = -1L;

		_orderId = orderId;
	}

	public long getTransactionId() {
		return _transactionId;
	}

	public void setTransactionId(long transactionId) {
		_columnBitmask |= TRANSACTIONID_COLUMN_BITMASK;

		if (!_setOriginalTransactionId) {
			_setOriginalTransactionId = true;

			_originalTransactionId = _transactionId;
		}

		_transactionId = transactionId;
	}

	public long getOriginalTransactionId() {
		return _originalTransactionId;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			OrderTransaction.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public OrderTransaction toEscapedModel() {
		if (_escapedModelProxy == null) {
			_escapedModelProxy = (OrderTransaction)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelProxyInterfaces,
					new AutoEscapeBeanHandler(this));
		}

		return _escapedModelProxy;
	}

	@Override
	public Object clone() {
		OrderTransactionImpl orderTransactionImpl = new OrderTransactionImpl();

		orderTransactionImpl.setUuid(getUuid());
		orderTransactionImpl.setCreateDate(getCreateDate());
		orderTransactionImpl.setModifiedDate(getModifiedDate());
		orderTransactionImpl.setOrderId(getOrderId());
		orderTransactionImpl.setTransactionId(getTransactionId());

		orderTransactionImpl.resetOriginalValues();

		return orderTransactionImpl;
	}

	public int compareTo(OrderTransaction orderTransaction) {
		int value = 0;

		if (getOrderId() < orderTransaction.getOrderId()) {
			value = -1;
		}
		else if (getOrderId() > orderTransaction.getOrderId()) {
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

		OrderTransaction orderTransaction = null;

		try {
			orderTransaction = (OrderTransaction)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = orderTransaction.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public void resetOriginalValues() {
		OrderTransactionModelImpl orderTransactionModelImpl = this;

		orderTransactionModelImpl._originalUuid = orderTransactionModelImpl._uuid;

		orderTransactionModelImpl._originalTransactionId = orderTransactionModelImpl._transactionId;

		orderTransactionModelImpl._setOriginalTransactionId = false;

		orderTransactionModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<OrderTransaction> toCacheModel() {
		OrderTransactionCacheModel orderTransactionCacheModel = new OrderTransactionCacheModel();

		orderTransactionCacheModel.uuid = getUuid();

		String uuid = orderTransactionCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			orderTransactionCacheModel.uuid = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			orderTransactionCacheModel.createDate = createDate.getTime();
		}
		else {
			orderTransactionCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			orderTransactionCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			orderTransactionCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		orderTransactionCacheModel.orderId = getOrderId();

		orderTransactionCacheModel.transactionId = getTransactionId();

		return orderTransactionCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", orderId=");
		sb.append(getOrderId());
		sb.append(", transactionId=");
		sb.append(getTransactionId());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("com.beorn.onlinepayment.model.OrderTransaction");
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
			"<column><column-name>orderId</column-name><column-value><![CDATA[");
		sb.append(getOrderId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>transactionId</column-name><column-value><![CDATA[");
		sb.append(getTransactionId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = OrderTransaction.class.getClassLoader();
	private static Class<?>[] _escapedModelProxyInterfaces = new Class[] {
			OrderTransaction.class
		};
	private String _uuid;
	private String _originalUuid;
	private Date _createDate;
	private Date _modifiedDate;
	private long _orderId;
	private long _transactionId;
	private long _originalTransactionId;
	private boolean _setOriginalTransactionId;
	private long _columnBitmask;
	private OrderTransaction _escapedModelProxy;
}