create table Payment_OrderTransaction (
	uuid_ VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	orderId LONG not null primary key,
	transactionId LONG
);

create table Payment_PaymentMethod (
	uuid_ VARCHAR(75) null,
	companyId LONG,
	groupId LONG,
	userId LONG,
	paymentMethodId LONG not null primary key,
	createDate DATE null,
	modifiedDate DATE null,
	key_ VARCHAR(75) null,
	name STRING null
);

create table Payment_PaymentPlugin (
	uuid_ VARCHAR(75) null,
	companyId LONG,
	groupId LONG,
	userId LONG,
	paymentPluginId LONG not null primary key,
	createDate DATE null,
	modifiedDate DATE null,
	applicationId VARCHAR(255) null,
	name STRING null,
	pluginConfigParameters TEXT null,
	sellerConfigParameters TEXT null,
	pluginConfig TEXT null,
	configured BOOLEAN
);

create table Payment_PaymentPluginConfig (
	uuid_ VARCHAR(75) null,
	companyId LONG,
	groupId LONG,
	userId LONG,
	paymentPluginConfigId LONG not null primary key,
	createDate DATE null,
	modifiedDate DATE null,
	sellerId LONG,
	paymentPluginId LONG,
	config TEXT null,
	configured BOOLEAN,
	defaultPlugin BOOLEAN
);

create table Payment_PaymentPlugin_PaymentMethod (
	paymentPluginId LONG not null,
	paymentMethodId LONG not null,
	primary key (paymentPluginId, paymentMethodId)
);

create table Payment_Rule (
	uuid_ VARCHAR(75) null,
	companyId LONG,
	groupId LONG,
	userId LONG,
	ruleId LONG not null primary key,
	createDate DATE null,
	modifiedDate DATE null,
	paymentPluginConfigId LONG,
	content TEXT null,
	priority INTEGER
);

create table Payment_Seller (
	uuid_ VARCHAR(75) null,
	companyId LONG,
	groupId LONG,
	userId LONG,
	sellerId LONG not null primary key,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(255) null,
	active_ BOOLEAN
);

create table Payment_Transaction (
	uuid_ VARCHAR(75) null,
	companyId LONG,
	groupId LONG,
	userId LONG,
	transactionId LONG not null primary key,
	createDate DATE null,
	modifiedDate DATE null,
	applicationId VARCHAR(255) null,
	sellerId LONG,
	amount DOUBLE,
	currencyCode VARCHAR(75) null,
	status LONG,
	paymentApplicationId VARCHAR(75) null,
	amountPaid DOUBLE,
	amountRefunded DOUBLE
);

create table Payment_TransactionLog (
	uuid_ VARCHAR(75) null,
	companyId LONG,
	groupId LONG,
	userId LONG,
	transactionLogId LONG not null primary key,
	createDate DATE null,
	modifiedDate DATE null,
	transactionId LONG,
	paymentApplicationId VARCHAR(75) null,
	remoteId VARCHAR(75) null,
	amount DOUBLE,
	type_ LONG
);

create table Payment_TransactionParameter (
	uuid_ VARCHAR(75) null,
	companyId LONG,
	groupId LONG,
	userId LONG,
	transactionParameterId LONG not null primary key,
	createDate DATE null,
	modifiedDate DATE null,
	transactionId LONG,
	key_ VARCHAR(255) null,
	value TEXT null
);