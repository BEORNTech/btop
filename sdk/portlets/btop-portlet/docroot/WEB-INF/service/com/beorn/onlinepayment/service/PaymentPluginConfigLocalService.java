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

package com.beorn.onlinepayment.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.BaseLocalService;
import com.liferay.portal.service.InvokableLocalService;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * The interface for the payment plugin config local service.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Sebastien Meunier
 * @see PaymentPluginConfigLocalServiceUtil
 * @see com.beorn.onlinepayment.service.base.PaymentPluginConfigLocalServiceBaseImpl
 * @see com.beorn.onlinepayment.service.impl.PaymentPluginConfigLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface PaymentPluginConfigLocalService extends BaseLocalService,
	InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PaymentPluginConfigLocalServiceUtil} to access the payment plugin config local service. Add custom service methods to {@link com.beorn.onlinepayment.service.impl.PaymentPluginConfigLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the payment plugin config to the database. Also notifies the appropriate model listeners.
	*
	* @param paymentPluginConfig the payment plugin config
	* @return the payment plugin config that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentPluginConfig addPaymentPluginConfig(
		com.beorn.onlinepayment.model.PaymentPluginConfig paymentPluginConfig)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Creates a new payment plugin config with the primary key. Does not add the payment plugin config to the database.
	*
	* @param paymentPluginConfigId the primary key for the new payment plugin config
	* @return the new payment plugin config
	*/
	public com.beorn.onlinepayment.model.PaymentPluginConfig createPaymentPluginConfig(
		long paymentPluginConfigId);

	/**
	* Deletes the payment plugin config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param paymentPluginConfigId the primary key of the payment plugin config
	* @return the payment plugin config that was removed
	* @throws PortalException if a payment plugin config with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentPluginConfig deletePaymentPluginConfig(
		long paymentPluginConfigId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Deletes the payment plugin config from the database. Also notifies the appropriate model listeners.
	*
	* @param paymentPluginConfig the payment plugin config
	* @return the payment plugin config that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentPluginConfig deletePaymentPluginConfig(
		com.beorn.onlinepayment.model.PaymentPluginConfig paymentPluginConfig)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.beorn.onlinepayment.model.PaymentPluginConfig fetchPaymentPluginConfig(
		long paymentPluginConfigId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the payment plugin config with the primary key.
	*
	* @param paymentPluginConfigId the primary key of the payment plugin config
	* @return the payment plugin config
	* @throws PortalException if a payment plugin config with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.beorn.onlinepayment.model.PaymentPluginConfig getPaymentPluginConfig(
		long paymentPluginConfigId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the payment plugin config with the UUID in the group.
	*
	* @param uuid the UUID of payment plugin config
	* @param groupId the group id of the payment plugin config
	* @return the payment plugin config
	* @throws PortalException if a payment plugin config with the UUID in the group could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.beorn.onlinepayment.model.PaymentPluginConfig getPaymentPluginConfigByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the payment plugin configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of payment plugin configs
	* @param end the upper bound of the range of payment plugin configs (not inclusive)
	* @return the range of payment plugin configs
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.beorn.onlinepayment.model.PaymentPluginConfig> getPaymentPluginConfigs(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of payment plugin configs.
	*
	* @return the number of payment plugin configs
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getPaymentPluginConfigsCount()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the payment plugin config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param paymentPluginConfig the payment plugin config
	* @return the payment plugin config that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentPluginConfig updatePaymentPluginConfig(
		com.beorn.onlinepayment.model.PaymentPluginConfig paymentPluginConfig)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the payment plugin config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param paymentPluginConfig the payment plugin config
	* @param merge whether to merge the payment plugin config with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the payment plugin config that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.beorn.onlinepayment.model.PaymentPluginConfig updatePaymentPluginConfig(
		com.beorn.onlinepayment.model.PaymentPluginConfig paymentPluginConfig,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier();

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier);

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	public com.beorn.onlinepayment.model.PaymentPluginConfig addPaymentPluginConfig(
		long userId, long sellerId, long paymentPluginId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.beorn.onlinepayment.model.PaymentPluginConfig addPaymentPluginConfig(
		long userId, long sellerId, long paymentPluginId,
		java.lang.String config, boolean configured,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.beorn.onlinepayment.model.PaymentPluginConfig> getSellerPaymentPluginConfigs(
		long sellerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.beorn.onlinepayment.model.PaymentPluginConfig> getSellerPaymentPluginConfigs(
		long sellerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSellerPaymentPluginConfigsCount(long sellerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.beorn.onlinepayment.model.PaymentPluginConfig> getPaymentPluginPaymentPluginConfigs(
		long paymentPluginId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getPaymentPluginPaymentPluginConfigsCount(long paymentPluginId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasPaymentPluginConfigBySellerIdAndPaymentPluginId(
		long sellerId, long paymentPluginId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.beorn.onlinepayment.model.PaymentPluginConfig getPaymentPluginConfigBySellerIdAndPaymentPluginId(
		long sellerId, long paymentPluginId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.beorn.onlinepayment.model.PaymentPluginConfig getSellerDefaultPaymentPluginConfig(
		long sellerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.beorn.onlinepayment.model.PaymentPluginConfig updatePaymentPluginConfig(
		long paymentPluginConfigId, long sellerId, long paymentPluginId,
		java.lang.String config, boolean configured,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public void updateDefaultPluginConfig(long sellerId,
		long defaultPaymentPluginConfigId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;
}