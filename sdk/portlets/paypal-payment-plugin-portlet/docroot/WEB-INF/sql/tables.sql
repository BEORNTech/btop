create table PaypalPaymentPlugin_Token (
	uuid_ VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	tokenId VARCHAR(75) not null primary key,
	transactionId LONG,
	status LONG
);