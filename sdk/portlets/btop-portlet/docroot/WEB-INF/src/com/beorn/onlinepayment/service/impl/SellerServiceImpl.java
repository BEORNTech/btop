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

import com.beorn.onlinepayment.model.Seller;
import com.beorn.onlinepayment.service.base.SellerServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * The implementation of the seller remote service.
 * 
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun
 * ServiceBuilder to copy their definitions into the
 * {@link com.beorn.onlinepayment.service.SellerService} interface.
 * 
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on
 * the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 * 
 * @author Sébastien Meunier
 * @see com.beorn.onlinepayment.service.base.SellerServiceBaseImpl
 * @see com.beorn.onlinepayment.service.SellerServiceUtil
 */
public class SellerServiceImpl extends SellerServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 * 
	 * Never reference this interface directly. Always use {@link
	 * com.beorn.onlinepayment.service.SellerServiceUtil} to access the seller remote service.
	 */

	public List<Seller> search(long companyId, String keywords, Boolean active, int start, int end,
			OrderByComparator orderByComparator) throws SystemException {

		return sellerFinder.search(companyId, keywords, active, start, end, orderByComparator, true);
	}

	public int searchCount(long companyId, String keywords, Boolean active) throws SystemException {
		return sellerFinder.searchCount(companyId, keywords, active, true);
	}
}