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

package com.beorn.paypalpaymentplugin.service.impl;

import java.util.Date;

import com.beorn.paypalpaymentplugin.DuplicateTokenException;
import com.beorn.paypalpaymentplugin.TokenTransactionIdException;
import com.beorn.paypalpaymentplugin.model.Token;
import com.beorn.paypalpaymentplugin.service.base.TokenLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the token local service.
 * 
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.beorn.paypalpaymentplugin.service.TokenLocalService} interface.
 * 
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 * 
 * @author Sebastien Meunier
 * @see com.beorn.paypalpaymentplugin.service.base.TokenLocalServiceBaseImpl
 * @see com.beorn.paypalpaymentplugin.service.TokenLocalServiceUtil
 */
public class TokenLocalServiceImpl extends TokenLocalServiceBaseImpl {

	public Token addToken(String tokenId, long transactionId, long status)
			throws PortalException, SystemException {

		Date now = new Date();

		validate(null, tokenId, transactionId, status);

		Token token = tokenPersistence.create(tokenId);

		token.setCreateDate(now);
		token.setModifiedDate(now);
		token.setTransactionId(transactionId);
		token.setStatus(status);

		tokenPersistence.update(token, false);

		return token;
	}

	public Token getToken(String tokenId) throws SystemException,
			PortalException {

		return tokenPersistence.findByPrimaryKey(tokenId);
	}

	public Token updateToken(String tokenId, long transactionId, long status)
			throws PortalException, SystemException {

		Date now = new Date();

		Token token = tokenPersistence.findByPrimaryKey(tokenId);

		validate(token, tokenId, transactionId, status);

		token.setModifiedDate(now);
		token.setTransactionId(transactionId);
		token.setStatus(status);

		tokenPersistence.update(token, false);

		return token;
	}

	private void validate(Token token, String tokenId, long transactionId,
			long status) throws SystemException, PortalException {

		if (token != null) {
			if (token.getTransactionId() != transactionId)
				throw new TokenTransactionIdException(
						"transaction id cannot change");
		}

		Token sameToken = tokenPersistence.fetchByPrimaryKey(tokenId);
		if (sameToken != null
				&& (token == null || token != null && !token.equals(sameToken))) {

			throw new DuplicateTokenException();
		}
	}
}