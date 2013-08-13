Liferay.Service.register("Liferay.Service.Payment", "com.beorn.onlinepayment.service", "btop-portlet");

Liferay.Service.registerClass(
	Liferay.Service.Payment, "PaymentMethod",
	{
		search: true,
		searchCount: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Payment, "PaymentPlugin",
	{
		search: true,
		searchCount: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Payment, "Seller",
	{
		search: true,
		searchCount: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Payment, "Transaction",
	{
		search: true,
		searchCount: true
	}
);