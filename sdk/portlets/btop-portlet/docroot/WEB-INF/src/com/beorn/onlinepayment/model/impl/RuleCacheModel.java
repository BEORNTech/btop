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

import com.beorn.onlinepayment.model.Rule;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing Rule in entity cache.
 *
 * @author Sebastien Meunier
 * @see Rule
 * @generated
 */
public class RuleCacheModel implements CacheModel<Rule>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", ruleId=");
		sb.append(ruleId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", paymentPluginConfigId=");
		sb.append(paymentPluginConfigId);
		sb.append(", content=");
		sb.append(content);
		sb.append(", priority=");
		sb.append(priority);
		sb.append("}");

		return sb.toString();
	}

	public Rule toEntityModel() {
		RuleImpl ruleImpl = new RuleImpl();

		if (uuid == null) {
			ruleImpl.setUuid(StringPool.BLANK);
		}
		else {
			ruleImpl.setUuid(uuid);
		}

		ruleImpl.setCompanyId(companyId);
		ruleImpl.setGroupId(groupId);
		ruleImpl.setUserId(userId);
		ruleImpl.setRuleId(ruleId);

		if (createDate == Long.MIN_VALUE) {
			ruleImpl.setCreateDate(null);
		}
		else {
			ruleImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			ruleImpl.setModifiedDate(null);
		}
		else {
			ruleImpl.setModifiedDate(new Date(modifiedDate));
		}

		ruleImpl.setPaymentPluginConfigId(paymentPluginConfigId);

		if (content == null) {
			ruleImpl.setContent(StringPool.BLANK);
		}
		else {
			ruleImpl.setContent(content);
		}

		ruleImpl.setPriority(priority);

		ruleImpl.resetOriginalValues();

		return ruleImpl;
	}

	public String uuid;
	public long companyId;
	public long groupId;
	public long userId;
	public long ruleId;
	public long createDate;
	public long modifiedDate;
	public long paymentPluginConfigId;
	public String content;
	public int priority;
}