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

import java.util.List;

import com.beorn.onlinepayment.model.PaymentMethod;
import com.beorn.onlinepayment.service.base.PaymentMethodServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * The implementation of the payment method remote service.
 * 
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun
 * ServiceBuilder to copy their definitions into the
 * {@link com.beorn.onlinepayment.service.PaymentMethodService} interface.
 * 
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on
 * the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 * 
 * @author Sébastien Meunier
 * @see com.beorn.onlinepayment.service.base.PaymentMethodServiceBaseImpl
 * @see com.beorn.onlinepayment.service.PaymentMethodServiceUtil
 */
public class PaymentMethodServiceImpl extends PaymentMethodServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 * 
	 * Never reference this interface directly. Always use {@link
	 * com.beorn.onlinepayment.service.PaymentMethodServiceUtil} to access the payment method remote
	 * service.
	 */

	public List<PaymentMethod> search(long companyId, String keywords, int start, int end,
			OrderByComparator orderByComparator) throws SystemException {

		return paymentMethodFinder.search(companyId, keywords, start, end, orderByComparator, true);
	}

	public int searchCount(long companyId, String keywords) throws SystemException {
		return paymentMethodFinder.searchCount(companyId, keywords, true);
	}
}