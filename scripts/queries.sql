use abusch;

-- 1. A query to retrieve the main attributes of each person (their code, and last/first name)
select code, lastName, firstname from Person;

-- 2. A query to retrieve the major fields for every person including their address (but excluding emails)
select code, lastName, firstName, street, city, state, zip, country from Person
join Address on Address.id = Person.addressId
join State on State.id = Address.stateId
join Country on Country.id = Address.countryId;

-- 3. A query to get the email addresses of a specific person
select email from Email where personId = ?;

-- 4. A query to change the email address of a specific email record
update Email set email = 'new.email@test.net' where id = ?;

-- 5. A query (or series of queries) to remove a specific person record
delete from OwnedAsset where accountId = (select id from Account where ownerId = ?);
delete from OwnedAsset where accountId = (select id from Account where managerId = ?);
delete from Account where id = (select id from Account where ownerId = ?);
delete from Account where id = (select id from Account where managerId = ?);
update Account set beneficiaryId = null where beneficiaryId = ?;
delete from Email where personId = ?;
delete from Person where id = ?;

-- 6. A query to get all the assets on a specific account
select * from OwnedAsset join Asset on Asset.id = OwnedAsset.assetId where accountId = ?;

-- 7. A query to find the total number of accounts owned by each person
select code, count(*) as accountsOwned from Account left join Person on Person.id = Account.ownerId group by ownerId;

-- 8. A query to find the total number of accounts managed by each person
select code, count(*) as accountsManaging from Account left join Person on Person.id = Account.managerId group by managerId;

-- 9. A query to find the total number of assets on each account
select number, count(*) as ownedAssets from OwnedAsset left join Account on Account.id = OwnedAsset.accountId group by accountId;

-- 10. A query to find the total value of all stock assets on a particular account (hint: you can take an aggregate of a mathematical expression).
select sum(sharePrice * numberOfShares) as value from OwnedAsset join Asset on Asset.id = OwnedAsset.assetId where accountId = ?;

-- 11. A query to find the total value of all stock assets on all accounts.
select sum(sharePrice * numberOfShares) as value from OwnedAsset join Asset on Asset.id = OwnedAsset.assetId;

-- 12. A query to detect duplicate property assets in a particular account (ie two Honus Wagner baseball cards on the same account).
select * from OwnedAsset join Asset on Asset.id = OwnedAsset.assetId where type = 'P' and accountId = 4 group by code having count(*) > 1;

-- 13. Write a query to detect a potential instances of fraud for option assets: the purchase
--     date should always be before the strike date. Report any instances where this is not
-- 	   the case.
select * from OwnedAsset where optionType is not null and datediff(purchaseDate, strikeDate) > 1;