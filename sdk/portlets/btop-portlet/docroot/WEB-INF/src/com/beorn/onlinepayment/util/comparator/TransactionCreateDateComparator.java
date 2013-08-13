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

package com.beorn.onlinepayment.util.comparator;

import com.beorn.onlinepayment.model.Transaction;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Sébastien Meunier
 */

@SuppressWarnings("serial")
public class TransactionCreateDateComparator extends OrderByComparator {

	public static String ORDER_BY_ASC = "createDate ASC";

	public static String ORDER_BY_DESC = "createDate DESC";

	public TransactionCreateDateComparator() {
		this(false);
	}

	public TransactionCreateDateComparator(boolean asc) {
		_asc = asc;
	}

	public int compare(Object obj1, Object obj2) {
		Transaction transaction1 = (Transaction) obj1;
		Transaction transaction2 = (Transaction) obj2;

		int value = transaction1.getCreateDate().compareTo(transaction2.getCreateDate());

		if (_asc) {
			return value;

		} else {
			return -value;
		}

	}

	public String getOrderBy() {
		if (_asc) {
			return ORDER_BY_ASC;
		} else {
			return ORDER_BY_DESC;
		}
	}

	private boolean _asc;

}