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

import com.beorn.onlinepayment.model.OrderTransactionClp;
import com.beorn.onlinepayment.model.PaymentMethodClp;
import com.beorn.onlinepayment.model.PaymentPluginClp;
import com.beorn.onlinepayment.model.PaymentPluginConfigClp;
import com.beorn.onlinepayment.model.RuleClp;
import com.beorn.onlinepayment.model.SellerClp;
import com.beorn.onlinepayment.model.TransactionClp;
import com.beorn.onlinepayment.model.TransactionLogClp;
import com.beorn.onlinepayment.model.TransactionParameterClp;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ClassLoaderObjectInputStream;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class ClpSerializer {
	public static String getServletContextName() {
		if (Validator.isNotNull(_servletContextName)) {
			return _servletContextName;
		}

		synchronized (ClpSerializer.class) {
			if (Validator.isNotNull(_servletContextName)) {
				return _servletContextName;
			}

			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Class<?> portletPropsClass = classLoader.loadClass(
						"com.liferay.util.portlet.PortletProps");

				Method getMethod = portletPropsClass.getMethod("get",
						new Class<?>[] { String.class });

				String portletPropsServletContextName = (String)getMethod.invoke(null,
						"btop-portlet-deployment-context");

				if (Validator.isNotNull(portletPropsServletContextName)) {
					_servletContextName = portletPropsServletContextName;
				}
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"Unable to locate deployment context from portlet properties");
				}
			}

			if (Validator.isNull(_servletContextName)) {
				try {
					String propsUtilServletContextName = PropsUtil.get(
							"btop-portlet-deployment-context");

					if (Validator.isNotNull(propsUtilServletContextName)) {
						_servletContextName = propsUtilServletContextName;
					}
				}
				catch (Throwable t) {
					if (_log.isInfoEnabled()) {
						_log.info(
							"Unable to locate deployment context from portal properties");
					}
				}
			}

			if (Validator.isNull(_servletContextName)) {
				_servletContextName = "btop-portlet";
			}

			return _servletContextName;
		}
	}

	public static Object translateInput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(OrderTransactionClp.class.getName())) {
			return translateInputOrderTransaction(oldModel);
		}

		if (oldModelClassName.equals(PaymentMethodClp.class.getName())) {
			return translateInputPaymentMethod(oldModel);
		}

		if (oldModelClassName.equals(PaymentPluginClp.class.getName())) {
			return translateInputPaymentPlugin(oldModel);
		}

		if (oldModelClassName.equals(PaymentPluginConfigClp.class.getName())) {
			return translateInputPaymentPluginConfig(oldModel);
		}

		if (oldModelClassName.equals(RuleClp.class.getName())) {
			return translateInputRule(oldModel);
		}

		if (oldModelClassName.equals(SellerClp.class.getName())) {
			return translateInputSeller(oldModel);
		}

		if (oldModelClassName.equals(TransactionClp.class.getName())) {
			return translateInputTransaction(oldModel);
		}

		if (oldModelClassName.equals(TransactionLogClp.class.getName())) {
			return translateInputTransactionLog(oldModel);
		}

		if (oldModelClassName.equals(TransactionParameterClp.class.getName())) {
			return translateInputTransactionParameter(oldModel);
		}

		return oldModel;
	}

	public static Object translateInput(List<Object> oldList) {
		List<Object> newList = new ArrayList<Object>(oldList.size());

		for (int i = 0; i < oldList.size(); i++) {
			Object curObj = oldList.get(i);

			newList.add(translateInput(curObj));
		}

		return newList;
	}

	public static Object translateInputOrderTransaction(BaseModel<?> oldModel) {
		OrderTransactionClp oldClpModel = (OrderTransactionClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getOrderTransactionRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputPaymentMethod(BaseModel<?> oldModel) {
		PaymentMethodClp oldClpModel = (PaymentMethodClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getPaymentMethodRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputPaymentPlugin(BaseModel<?> oldModel) {
		PaymentPluginClp oldClpModel = (PaymentPluginClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getPaymentPluginRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputPaymentPluginConfig(
		BaseModel<?> oldModel) {
		PaymentPluginConfigClp oldClpModel = (PaymentPluginConfigClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getPaymentPluginConfigRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputRule(BaseModel<?> oldModel) {
		RuleClp oldClpModel = (RuleClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getRuleRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputSeller(BaseModel<?> oldModel) {
		SellerClp oldClpModel = (SellerClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getSellerRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputTransaction(BaseModel<?> oldModel) {
		TransactionClp oldClpModel = (TransactionClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getTransactionRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputTransactionLog(BaseModel<?> oldModel) {
		TransactionLogClp oldClpModel = (TransactionLogClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getTransactionLogRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputTransactionParameter(
		BaseModel<?> oldModel) {
		TransactionParameterClp oldClpModel = (TransactionParameterClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getTransactionParameterRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInput(Object obj) {
		if (obj instanceof BaseModel<?>) {
			return translateInput((BaseModel<?>)obj);
		}
		else if (obj instanceof List<?>) {
			return translateInput((List<Object>)obj);
		}
		else {
			return obj;
		}
	}

	public static Object translateOutput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(
					"com.beorn.onlinepayment.model.impl.OrderTransactionImpl")) {
			return translateOutputOrderTransaction(oldModel);
		}

		if (oldModelClassName.equals(
					"com.beorn.onlinepayment.model.impl.PaymentMethodImpl")) {
			return translateOutputPaymentMethod(oldModel);
		}

		if (oldModelClassName.equals(
					"com.beorn.onlinepayment.model.impl.PaymentPluginImpl")) {
			return translateOutputPaymentPlugin(oldModel);
		}

		if (oldModelClassName.equals(
					"com.beorn.onlinepayment.model.impl.PaymentPluginConfigImpl")) {
			return translateOutputPaymentPluginConfig(oldModel);
		}

		if (oldModelClassName.equals(
					"com.beorn.onlinepayment.model.impl.RuleImpl")) {
			return translateOutputRule(oldModel);
		}

		if (oldModelClassName.equals(
					"com.beorn.onlinepayment.model.impl.SellerImpl")) {
			return translateOutputSeller(oldModel);
		}

		if (oldModelClassName.equals(
					"com.beorn.onlinepayment.model.impl.TransactionImpl")) {
			return translateOutputTransaction(oldModel);
		}

		if (oldModelClassName.equals(
					"com.beorn.onlinepayment.model.impl.TransactionLogImpl")) {
			return translateOutputTransactionLog(oldModel);
		}

		if (oldModelClassName.equals(
					"com.beorn.onlinepayment.model.impl.TransactionParameterImpl")) {
			return translateOutputTransactionParameter(oldModel);
		}

		return oldModel;
	}

	public static Object translateOutput(List<Object> oldList) {
		List<Object> newList = new ArrayList<Object>(oldList.size());

		for (int i = 0; i < oldList.size(); i++) {
			Object curObj = oldList.get(i);

			newList.add(translateOutput(curObj));
		}

		return newList;
	}

	public static Object translateOutput(Object obj) {
		if (obj instanceof BaseModel<?>) {
			return translateOutput((BaseModel<?>)obj);
		}
		else if (obj instanceof List<?>) {
			return translateOutput((List<Object>)obj);
		}
		else {
			return obj;
		}
	}

	public static Throwable translateThrowable(Throwable throwable) {
		if (_useReflectionToTranslateThrowable) {
			try {
				UnsyncByteArrayOutputStream unsyncByteArrayOutputStream = new UnsyncByteArrayOutputStream();
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(unsyncByteArrayOutputStream);

				objectOutputStream.writeObject(throwable);

				objectOutputStream.flush();
				objectOutputStream.close();

				UnsyncByteArrayInputStream unsyncByteArrayInputStream = new UnsyncByteArrayInputStream(unsyncByteArrayOutputStream.unsafeGetByteArray(),
						0, unsyncByteArrayOutputStream.size());

				Thread currentThread = Thread.currentThread();

				ClassLoader contextClassLoader = currentThread.getContextClassLoader();

				ObjectInputStream objectInputStream = new ClassLoaderObjectInputStream(unsyncByteArrayInputStream,
						contextClassLoader);

				throwable = (Throwable)objectInputStream.readObject();

				objectInputStream.close();

				return throwable;
			}
			catch (SecurityException se) {
				if (_log.isInfoEnabled()) {
					_log.info("Do not use reflection to translate throwable");
				}

				_useReflectionToTranslateThrowable = false;
			}
			catch (Throwable throwable2) {
				_log.error(throwable2, throwable2);

				return throwable2;
			}
		}

		Class<?> clazz = throwable.getClass();

		String className = clazz.getName();

		if (className.equals(PortalException.class.getName())) {
			return new PortalException();
		}

		if (className.equals(SystemException.class.getName())) {
			return new SystemException();
		}

		if (className.equals(
					"com.beorn.onlinepayment.DuplicateOrderTransactionException")) {
			return new com.beorn.onlinepayment.DuplicateOrderTransactionException();
		}

		if (className.equals(
					"com.beorn.onlinepayment.DuplicatePaymentMethodException")) {
			return new com.beorn.onlinepayment.DuplicatePaymentMethodException();
		}

		if (className.equals(
					"com.beorn.onlinepayment.DuplicatePaymentPluginException")) {
			return new com.beorn.onlinepayment.DuplicatePaymentPluginException();
		}

		if (className.equals(
					"com.beorn.onlinepayment.DuplicatePaymentPluginConfigException")) {
			return new com.beorn.onlinepayment.DuplicatePaymentPluginConfigException();
		}

		if (className.equals("com.beorn.onlinepayment.DuplicateSellerException")) {
			return new com.beorn.onlinepayment.DuplicateSellerException();
		}

		if (className.equals(
					"com.beorn.onlinepayment.DuplicateTransactionLogException")) {
			return new com.beorn.onlinepayment.DuplicateTransactionLogException();
		}

		if (className.equals(
					"com.beorn.onlinepayment.PaymentMethodKeyException")) {
			return new com.beorn.onlinepayment.PaymentMethodKeyException();
		}

		if (className.equals(
					"com.beorn.onlinepayment.PaymentMethodNameException")) {
			return new com.beorn.onlinepayment.PaymentMethodNameException();
		}

		if (className.equals(
					"com.beorn.onlinepayment.PaymentPluginApplicationIdException")) {
			return new com.beorn.onlinepayment.PaymentPluginApplicationIdException();
		}

		if (className.equals(
					"com.beorn.onlinepayment.PaymentPluginNameException")) {
			return new com.beorn.onlinepayment.PaymentPluginNameException();
		}

		if (className.equals(
					"com.beorn.onlinepayment.RequiredPaymentMethodException")) {
			return new com.beorn.onlinepayment.RequiredPaymentMethodException();
		}

		if (className.equals("com.beorn.onlinepayment.RequiredSellerException")) {
			return new com.beorn.onlinepayment.RequiredSellerException();
		}

		if (className.equals("com.beorn.onlinepayment.SellerNameException")) {
			return new com.beorn.onlinepayment.SellerNameException();
		}

		if (className.equals(
					"com.beorn.onlinepayment.TransactionAmountException")) {
			return new com.beorn.onlinepayment.TransactionAmountException();
		}

		if (className.equals(
					"com.beorn.onlinepayment.TransactionAmountPaidException")) {
			return new com.beorn.onlinepayment.TransactionAmountPaidException();
		}

		if (className.equals(
					"com.beorn.onlinepayment.TransactionAmountRefundedException")) {
			return new com.beorn.onlinepayment.TransactionAmountRefundedException();
		}

		if (className.equals(
					"com.beorn.onlinepayment.TransactionApplicationIdException")) {
			return new com.beorn.onlinepayment.TransactionApplicationIdException();
		}

		if (className.equals(
					"com.beorn.onlinepayment.TransactionCurrencyCodeException")) {
			return new com.beorn.onlinepayment.TransactionCurrencyCodeException();
		}

		if (className.equals(
					"com.beorn.onlinepayment.TransactionLogPaymentApplicationIdException")) {
			return new com.beorn.onlinepayment.TransactionLogPaymentApplicationIdException();
		}

		if (className.equals(
					"com.beorn.onlinepayment.TransactionLogTypeException")) {
			return new com.beorn.onlinepayment.TransactionLogTypeException();
		}

		if (className.equals(
					"com.beorn.onlinepayment.TransactionPaymentApplicationIdException")) {
			return new com.beorn.onlinepayment.TransactionPaymentApplicationIdException();
		}

		if (className.equals(
					"com.beorn.onlinepayment.TransactionStatusException")) {
			return new com.beorn.onlinepayment.TransactionStatusException();
		}

		if (className.equals(
					"com.beorn.onlinepayment.NoSuchOrderTransactionException")) {
			return new com.beorn.onlinepayment.NoSuchOrderTransactionException();
		}

		if (className.equals("com.beorn.onlinepayment.NoSuchMethodException")) {
			return new com.beorn.onlinepayment.NoSuchMethodException();
		}

		if (className.equals("com.beorn.onlinepayment.NoSuchPluginException")) {
			return new com.beorn.onlinepayment.NoSuchPluginException();
		}

		if (className.equals(
					"com.beorn.onlinepayment.NoSuchPluginConfigException")) {
			return new com.beorn.onlinepayment.NoSuchPluginConfigException();
		}

		if (className.equals("com.beorn.onlinepayment.NoSuchRuleException")) {
			return new com.beorn.onlinepayment.NoSuchRuleException();
		}

		if (className.equals("com.beorn.onlinepayment.NoSuchSellerException")) {
			return new com.beorn.onlinepayment.NoSuchSellerException();
		}

		if (className.equals(
					"com.beorn.onlinepayment.NoSuchTransactionException")) {
			return new com.beorn.onlinepayment.NoSuchTransactionException();
		}

		if (className.equals(
					"com.beorn.onlinepayment.NoSuchTransactionLogException")) {
			return new com.beorn.onlinepayment.NoSuchTransactionLogException();
		}

		if (className.equals(
					"com.beorn.onlinepayment.NoSuchTransactionParameterException")) {
			return new com.beorn.onlinepayment.NoSuchTransactionParameterException();
		}

		return throwable;
	}

	public static Object translateOutputOrderTransaction(BaseModel<?> oldModel) {
		OrderTransactionClp newModel = new OrderTransactionClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setOrderTransactionRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputPaymentMethod(BaseModel<?> oldModel) {
		PaymentMethodClp newModel = new PaymentMethodClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setPaymentMethodRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputPaymentPlugin(BaseModel<?> oldModel) {
		PaymentPluginClp newModel = new PaymentPluginClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setPaymentPluginRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputPaymentPluginConfig(
		BaseModel<?> oldModel) {
		PaymentPluginConfigClp newModel = new PaymentPluginConfigClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setPaymentPluginConfigRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputRule(BaseModel<?> oldModel) {
		RuleClp newModel = new RuleClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setRuleRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputSeller(BaseModel<?> oldModel) {
		SellerClp newModel = new SellerClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setSellerRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputTransaction(BaseModel<?> oldModel) {
		TransactionClp newModel = new TransactionClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setTransactionRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputTransactionLog(BaseModel<?> oldModel) {
		TransactionLogClp newModel = new TransactionLogClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setTransactionLogRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputTransactionParameter(
		BaseModel<?> oldModel) {
		TransactionParameterClp newModel = new TransactionParameterClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setTransactionParameterRemoteModel(oldModel);

		return newModel;
	}

	private static Log _log = LogFactoryUtil.getLog(ClpSerializer.class);
	private static String _servletContextName;
	private static boolean _useReflectionToTranslateThrowable = true;
}