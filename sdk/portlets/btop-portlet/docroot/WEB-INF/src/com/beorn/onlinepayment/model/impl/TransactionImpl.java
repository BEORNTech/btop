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

/**
 * The extended model implementation for the Transaction service. Represents a row in the
 * &quot;Payment_Transaction&quot; database table, with each column mapped to a property of this
 * class.
 * 
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added,
 * rerun ServiceBuilder to copy their definitions into the
 * {@link com.beorn.onlinepayment.model.Transaction} interface.
 * </p>
 * 
 * @author Sébastien Meunier
 */
public class TransactionImpl extends TransactionBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 * 
	 * Never reference this class directly. All methods that expect a transaction model instance
	 * should use the {@link com.beorn.onlinepayment.model.Transaction} interface instead.
	 */
	public TransactionImpl() {
	}
}