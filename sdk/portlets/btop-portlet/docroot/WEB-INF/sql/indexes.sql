create unique index IX_EA1A19A on Payment_OrderTransaction (transactionId);
create index IX_2B03D945 on Payment_OrderTransaction (uuid_);

create index IX_F758D394 on Payment_PaymentMethod (companyId);
create unique index IX_EB99BEE8 on Payment_PaymentMethod (key_);
create index IX_B30B8F20 on Payment_PaymentMethod (uuid_);
create unique index IX_BB38E98A on Payment_PaymentMethod (uuid_, groupId);

create unique index IX_CFF885D5 on Payment_PaymentPlugin (applicationId);
create index IX_E6F28A82 on Payment_PaymentPlugin (companyId);
create index IX_E201ED0E on Payment_PaymentPlugin (uuid_);
create unique index IX_CB62575C on Payment_PaymentPlugin (uuid_, groupId);

create index IX_A5F0C260 on Payment_PaymentPluginConfig (companyId);
create index IX_DE06883C on Payment_PaymentPluginConfig (paymentPluginId);
create index IX_10F2D436 on Payment_PaymentPluginConfig (sellerId);
create index IX_3D2B898E on Payment_PaymentPluginConfig (sellerId, defaultPlugin);
create unique index IX_4B0EB28E on Payment_PaymentPluginConfig (sellerId, paymentPluginId);
create index IX_5D22C3EC on Payment_PaymentPluginConfig (uuid_);
create unique index IX_10825A3E on Payment_PaymentPluginConfig (uuid_, groupId);

create index IX_2A180424 on Payment_PaymentPlugin_PaymentMethod (paymentMethodId);
create index IX_3E1E2976 on Payment_PaymentPlugin_PaymentMethod (paymentPluginId);

create index IX_A04030ED on Payment_Rule (companyId);
create index IX_5E4B926B on Payment_Rule (paymentPluginConfigId);
create index IX_BE6180F9 on Payment_Rule (uuid_);
create unique index IX_D542FBD1 on Payment_Rule (uuid_, groupId);

create index IX_7CB54F8A on Payment_Seller (companyId);
create unique index IX_55F44FC9 on Payment_Seller (companyId, name);
create index IX_FBC07616 on Payment_Seller (uuid_);
create unique index IX_B7397754 on Payment_Seller (uuid_, groupId);

create index IX_E49C60B0 on Payment_Transaction (applicationId);
create index IX_67B57E1E on Payment_Transaction (applicationId, sellerId);
create index IX_4B0608EA on Payment_Transaction (applicationId, userId);
create index IX_13F60819 on Payment_Transaction (sellerId);
create index IX_4446D5A5 on Payment_Transaction (userId);
create index IX_5CFD2E9 on Payment_Transaction (uuid_);
create unique index IX_4B39D7E1 on Payment_Transaction (uuid_, groupId);

create index IX_3FF1CE3E on Payment_TransactionLog (paymentApplicationId);
create index IX_7EEDBF73 on Payment_TransactionLog (paymentApplicationId, remoteId);
create index IX_A72BE2EF on Payment_TransactionLog (uuid_);
create unique index IX_C2286E9B on Payment_TransactionLog (uuid_, groupId);

create index IX_D00653 on Payment_TransactionParameter (transactionId, key_);
create index IX_788BC6A on Payment_TransactionParameter (uuid_);
create unique index IX_A9790F80 on Payment_TransactionParameter (uuid_, groupId);