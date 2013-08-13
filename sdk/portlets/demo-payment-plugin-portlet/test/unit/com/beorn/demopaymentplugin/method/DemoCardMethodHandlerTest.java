package com.beorn.demopaymentplugin.method;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.beorn.paymentapi.model.ApiTransaction;
import com.beorn.paymentpluginapi.test.MethodHandlerTestBase;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.HttpUtil;

@PrepareForTest({ HttpUtil.class })
@RunWith(PowerMockRunner.class)
public class DemoCardMethodHandlerTest extends MethodHandlerTestBase {

	@Before
	public void setUp() {
		mockStatic(HttpUtil.class);

		when(HttpUtil.encodeURL(Mockito.anyString())).thenAnswer(
				new Answer<String>() {
					public String answer(InvocationOnMock invocationOnMock)
							throws Throwable {

						return "[encoded["
								+ (String) invocationOnMock.getArguments()[0]
								+ "]/encoded]";
					}
				});
	}

	@Test
	public void testInitialize() throws PortalException, SystemException {
		DemoCardMethodHandler methodHandler = new DemoCardMethodHandler();
		methodHandler.initialize(null);
	}

	@Test
	public void testGetPaymentUrl() throws PortalException, SystemException {
		DemoCardMethodHandler methodHandler = new DemoCardMethodHandler();

		long transactionId = 1;
		long sellerId = 2;
		double amount = 3;
		String currencyCode = "A";
		long status = 4;
		ApiTransaction transaction = new ApiTransaction(transactionId,
				sellerId, amount, currencyCode, status);
		String languageId = "B";
		String backUrl = "C";
		String successUrl = "D";
		String errorUrl = "E";

		String paymentUrl = methodHandler.getPaymentUrl(transaction,
				languageId, backUrl, successUrl, errorUrl);

		Assert.assertEquals(
				"/web/guest/paiement/-/demopaymentplugin/pay/1?amount=3.0&currencyCode=[encoded[A]/encoded]&languageId=[encoded[B]/encoded]&backUrl=[encoded[C]/encoded]&successUrl=[encoded[D]/encoded]&errorUrl=[encoded[E]/encoded]",
				paymentUrl);
	}
}
