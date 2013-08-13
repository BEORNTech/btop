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

package com.beorn.onlinepayment.rule.payment;

import java.util.HashMap;
import java.util.Map;

import com.beorn.onlinepayment.model.Transaction;
import com.beorn.onlinepayment.rule.parameter.ParametersConditionSubject;
import com.beorn.onlinepayment.service.TransactionLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Sébastien Meunier
 */
public class TransactionConditionSubject extends ParametersConditionSubject {

	public static final String TRANSACTION_AMOUNT = "transactionAmount";
	public static final String TRANSACTION_CURRENCY_CODE = "transactionCurrencyCode";
	public static final String COUNTRY_CODE = "countryCode";

	public TransactionConditionSubject(Transaction transaction,
			String countryCode) throws SystemException {
		super(buildParameters(transaction, countryCode));
	}

	private static Map<String, String> buildParameters(Transaction transaction,
			String countryCode) throws SystemException {
		Map<String, String> parameters = new HashMap<String, String>(
				TransactionLocalServiceUtil
						.getTransactionParametersMap(transaction
								.getTransactionId()));

		parameters.put(TRANSACTION_AMOUNT,
				String.valueOf(transaction.getAmount()));
		parameters.put(TRANSACTION_CURRENCY_CODE,
				String.valueOf(transaction.getCurrencyCode()));

		if (Validator.isNotNull(countryCode))
			parameters.put(COUNTRY_CODE, countryCode);

		_log.debug("TransactionRuleSubject built with parameters " + parameters);

		return parameters;
	}

	private static Log _log = LogFactoryUtil
			.getLog(TransactionConditionSubject.class);
}
