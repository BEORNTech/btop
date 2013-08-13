/**
 * Copyright (c) 2007-2013 BEORN Technologies, SARL. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */

package com.beorn.onlinepayment.service.impl;

import java.util.Date;
import java.util.List;

import com.beorn.onlinepayment.model.Rule;
import com.beorn.onlinepayment.service.base.RuleLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * The implementation of the rule local service.
 * 
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun
 * ServiceBuilder to copy their definitions into the
 * {@link com.beorn.onlinepayment.service.RuleLocalService} interface.
 * 
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the
 * propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 * 
 * @author Sébastien Meunier
 * @see com.beorn.onlinepayment.service.base.RuleLocalServiceBaseImpl
 * @see com.beorn.onlinepayment.service.RuleLocalServiceUtil
 */
public class RuleLocalServiceImpl extends RuleLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 * 
	 * Never reference this interface directly. Always use {@link
	 * com.beorn.onlinepayment.service.RuleLocalServiceUtil} to access the rule local service.
	 */

	public Rule addRule(long userId, long paymentPluginConfigId, String content, int priority, ServiceContext serviceContext)
			throws SystemException, PortalException {

		User user = UserLocalServiceUtil.getUser(userId);
		long groupId = serviceContext.getScopeGroupId();
		Date now = new Date();

		validate(null, paymentPluginConfigId, content, priority, serviceContext);

		long ruleId = CounterLocalServiceUtil.increment();

		Rule rule = rulePersistence.create(ruleId);

		rule.setGroupId(groupId);
		rule.setUserId(userId);
		rule.setCompanyId(user.getCompanyId());
		rule.setCreateDate(now);
		rule.setModifiedDate(now);

		rule.setPaymentPluginConfigId(paymentPluginConfigId);
		rule.setContent(content);
		rule.setPriority(priority);

		rulePersistence.update(rule, false);

		return rule;
	}

	public List<Rule> getSellerRules(long sellerId, int start, int end, OrderByComparator orderByComparator)
			throws SystemException {

		return ruleFinder.findBySellerId(sellerId, start, end, orderByComparator);
	}

	public int getSellerRulesCount(long sellerId) throws SystemException {
		return ruleFinder.countBySellerId(sellerId);
	}

	public List<Rule> getPaymentPluginConfigRules(long paymentPluginConfigId, int start, int end,
			OrderByComparator orderByComparator) throws SystemException {

		return paymentPluginConfigPersistence.getRules(paymentPluginConfigId, start, end, orderByComparator);
	}

	public int getPaymentPluginConfigRulesCount(long paymentPluginConfigId) throws SystemException {
		return paymentPluginConfigPersistence.getRulesSize(paymentPluginConfigId);
	}

	public Rule updateRule(long ruleId, long paymentPluginConfigId, String content, int priority,
			ServiceContext serviceContext) throws SystemException, PortalException {

		Date now = new Date();

		Rule rule = rulePersistence.findByPrimaryKey(ruleId);

		validate(rule, paymentPluginConfigId, content, priority, serviceContext);

		rule.setModifiedDate(now);

		rule.setPaymentPluginConfigId(paymentPluginConfigId);
		rule.setContent(content);
		rule.setPriority(priority);

		rulePersistence.update(rule, false);

		return rule;
	}

	public Rule deleteRule(long ruleId) throws PortalException, SystemException {
		return deleteRule(rulePersistence.findByPrimaryKey(ruleId));
	}

	public Rule deleteRule(Rule rule) throws SystemException, PortalException {
		return rulePersistence.remove(rule);
	}

	private void validate(Rule rule, long paymentPluginConfigId, String content, int priority, ServiceContext serviceContext)
			throws PortalException, SystemException {

		paymentPluginConfigPersistence.findByPrimaryKey(paymentPluginConfigId);
	}
}