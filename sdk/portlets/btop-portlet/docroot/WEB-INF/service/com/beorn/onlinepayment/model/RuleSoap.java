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
 * This class is used by SOAP remote services.
 *
 * @author    Sebastien Meunier
 * @generated
 */
public class RuleSoap implements Serializable {
	public static RuleSoap toSoapModel(Rule model) {
		RuleSoap soapModel = new RuleSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setRuleId(model.getRuleId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setPaymentPluginConfigId(model.getPaymentPluginConfigId());
		soapModel.setContent(model.getContent());
		soapModel.setPriority(model.getPriority());

		return soapModel;
	}

	public static RuleSoap[] toSoapModels(Rule[] models) {
		RuleSoap[] soapModels = new RuleSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static RuleSoap[][] toSoapModels(Rule[][] models) {
		RuleSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new RuleSoap[models.length][models[0].length];
		}
		else {
			soapModels = new RuleSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static RuleSoap[] toSoapModels(List<Rule> models) {
		List<RuleSoap> soapModels = new ArrayList<RuleSoap>(models.size());

		for (Rule model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new RuleSoap[soapModels.size()]);
	}

	public RuleSoap() {
	}

	public long getPrimaryKey() {
		return _ruleId;
	}

	public void setPrimaryKey(long pk) {
		setRuleId(pk);
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

	public long getRuleId() {
		return _ruleId;
	}

	public void setRuleId(long ruleId) {
		_ruleId = ruleId;
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

	public long getPaymentPluginConfigId() {
		return _paymentPluginConfigId;
	}

	public void setPaymentPluginConfigId(long paymentPluginConfigId) {
		_paymentPluginConfigId = paymentPluginConfigId;
	}

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	public int getPriority() {
		return _priority;
	}

	public void setPriority(int priority) {
		_priority = priority;
	}

	private String _uuid;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private long _ruleId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _paymentPluginConfigId;
	private String _content;
	private int _priority;
}