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
 * This class is a wrapper for {@link Rule}.
 * </p>
 *
 * @author    Sebastien Meunier
 * @see       Rule
 * @generated
 */
public class RuleWrapper implements Rule, ModelWrapper<Rule> {
	public RuleWrapper(Rule rule) {
		_rule = rule;
	}

	public Class<?> getModelClass() {
		return Rule.class;
	}

	public String getModelClassName() {
		return Rule.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("ruleId", getRuleId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("paymentPluginConfigId", getPaymentPluginConfigId());
		attributes.put("content", getContent());
		attributes.put("priority", getPriority());

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

		Long ruleId = (Long)attributes.get("ruleId");

		if (ruleId != null) {
			setRuleId(ruleId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long paymentPluginConfigId = (Long)attributes.get(
				"paymentPluginConfigId");

		if (paymentPluginConfigId != null) {
			setPaymentPluginConfigId(paymentPluginConfigId);
		}

		String content = (String)attributes.get("content");

		if (content != null) {
			setContent(content);
		}

		Integer priority = (Integer)attributes.get("priority");

		if (priority != null) {
			setPriority(priority);
		}
	}

	/**
	* Returns the primary key of this rule.
	*
	* @return the primary key of this rule
	*/
	public long getPrimaryKey() {
		return _rule.getPrimaryKey();
	}

	/**
	* Sets the primary key of this rule.
	*
	* @param primaryKey the primary key of this rule
	*/
	public void setPrimaryKey(long primaryKey) {
		_rule.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this rule.
	*
	* @return the uuid of this rule
	*/
	public java.lang.String getUuid() {
		return _rule.getUuid();
	}

	/**
	* Sets the uuid of this rule.
	*
	* @param uuid the uuid of this rule
	*/
	public void setUuid(java.lang.String uuid) {
		_rule.setUuid(uuid);
	}

	/**
	* Returns the company ID of this rule.
	*
	* @return the company ID of this rule
	*/
	public long getCompanyId() {
		return _rule.getCompanyId();
	}

	/**
	* Sets the company ID of this rule.
	*
	* @param companyId the company ID of this rule
	*/
	public void setCompanyId(long companyId) {
		_rule.setCompanyId(companyId);
	}

	/**
	* Returns the group ID of this rule.
	*
	* @return the group ID of this rule
	*/
	public long getGroupId() {
		return _rule.getGroupId();
	}

	/**
	* Sets the group ID of this rule.
	*
	* @param groupId the group ID of this rule
	*/
	public void setGroupId(long groupId) {
		_rule.setGroupId(groupId);
	}

	/**
	* Returns the user ID of this rule.
	*
	* @return the user ID of this rule
	*/
	public long getUserId() {
		return _rule.getUserId();
	}

	/**
	* Sets the user ID of this rule.
	*
	* @param userId the user ID of this rule
	*/
	public void setUserId(long userId) {
		_rule.setUserId(userId);
	}

	/**
	* Returns the user uuid of this rule.
	*
	* @return the user uuid of this rule
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _rule.getUserUuid();
	}

	/**
	* Sets the user uuid of this rule.
	*
	* @param userUuid the user uuid of this rule
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_rule.setUserUuid(userUuid);
	}

	/**
	* Returns the rule ID of this rule.
	*
	* @return the rule ID of this rule
	*/
	public long getRuleId() {
		return _rule.getRuleId();
	}

	/**
	* Sets the rule ID of this rule.
	*
	* @param ruleId the rule ID of this rule
	*/
	public void setRuleId(long ruleId) {
		_rule.setRuleId(ruleId);
	}

	/**
	* Returns the create date of this rule.
	*
	* @return the create date of this rule
	*/
	public java.util.Date getCreateDate() {
		return _rule.getCreateDate();
	}

	/**
	* Sets the create date of this rule.
	*
	* @param createDate the create date of this rule
	*/
	public void setCreateDate(java.util.Date createDate) {
		_rule.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this rule.
	*
	* @return the modified date of this rule
	*/
	public java.util.Date getModifiedDate() {
		return _rule.getModifiedDate();
	}

	/**
	* Sets the modified date of this rule.
	*
	* @param modifiedDate the modified date of this rule
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_rule.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the payment plugin config ID of this rule.
	*
	* @return the payment plugin config ID of this rule
	*/
	public long getPaymentPluginConfigId() {
		return _rule.getPaymentPluginConfigId();
	}

	/**
	* Sets the payment plugin config ID of this rule.
	*
	* @param paymentPluginConfigId the payment plugin config ID of this rule
	*/
	public void setPaymentPluginConfigId(long paymentPluginConfigId) {
		_rule.setPaymentPluginConfigId(paymentPluginConfigId);
	}

	/**
	* Returns the content of this rule.
	*
	* @return the content of this rule
	*/
	public java.lang.String getContent() {
		return _rule.getContent();
	}

	/**
	* Sets the content of this rule.
	*
	* @param content the content of this rule
	*/
	public void setContent(java.lang.String content) {
		_rule.setContent(content);
	}

	/**
	* Returns the priority of this rule.
	*
	* @return the priority of this rule
	*/
	public int getPriority() {
		return _rule.getPriority();
	}

	/**
	* Sets the priority of this rule.
	*
	* @param priority the priority of this rule
	*/
	public void setPriority(int priority) {
		_rule.setPriority(priority);
	}

	public boolean isNew() {
		return _rule.isNew();
	}

	public void setNew(boolean n) {
		_rule.setNew(n);
	}

	public boolean isCachedModel() {
		return _rule.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_rule.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _rule.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _rule.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_rule.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _rule.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_rule.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new RuleWrapper((Rule)_rule.clone());
	}

	public int compareTo(com.beorn.onlinepayment.model.Rule rule) {
		return _rule.compareTo(rule);
	}

	@Override
	public int hashCode() {
		return _rule.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.beorn.onlinepayment.model.Rule> toCacheModel() {
		return _rule.toCacheModel();
	}

	public com.beorn.onlinepayment.model.Rule toEscapedModel() {
		return new RuleWrapper(_rule.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _rule.toString();
	}

	public java.lang.String toXmlString() {
		return _rule.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_rule.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public Rule getWrappedRule() {
		return _rule;
	}

	public Rule getWrappedModel() {
		return _rule;
	}

	public void resetOriginalValues() {
		_rule.resetOriginalValues();
	}

	private Rule _rule;
}