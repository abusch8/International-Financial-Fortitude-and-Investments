use abusch;

drop table if exists OwnedAsset;
drop table if exists Asset;
drop table if exists Account;
drop table if exists Email;
drop table if exists Person;
drop table if exists Address;
drop table if exists Country;
drop table if exists State;

create table State (
  id int not null primary key auto_increment,
  state varchar(255) not null
);

create table Country (
  id int not null primary key auto_increment,
  country varchar(255) not null
);

create table Address (
  id int not null primary key auto_increment,
  street varchar(255) not null,
  city varchar(255) not null,
  stateId int not null,
  zip varchar(255) not null,
  countryId int not null,
  foreign key (stateId) references State(id),
  foreign key (countryId) references Country(id)
);

create table Person (
  id int not null primary key auto_increment,
  code varchar(255) not null unique,
  lastName varchar(255) not null,
  firstName varchar(255) not null,
  addressId int not null,
  foreign key (addressId) references Address(id)
);

create table Email (
  id int not null primary key auto_increment,
  email varchar(255) not null,
  personId int not null,
  foreign key (personId) references Person(id)
);

create table Account (
  id int not null primary key auto_increment,
  type varchar(1) not null,
  number varchar(255) not null unique,
  ownerId int not null,
  managerId int not null,
  beneficiaryId int,
  foreign key (ownerId) references Person(id),
  foreign key (managerId) references Person(id),
  foreign key (beneficiaryId) references Person(id)
);

create table Asset (
  id int not null primary key auto_increment,

  -- common
  type varchar(1) not null,
  code varchar(255) not null unique,
  label varchar(255) not null,

  -- property
  appraisedValue double,

  -- crypto
  exchangeRate double,
  exchangeFeeRate double,

  -- stock
  symbol varchar(4),
  sharePrice double
);

create table OwnedAsset (
  id int not null primary key auto_increment,
  accountId int not null,
  assetId int not null,

  -- common
  purchaseDate date not null,

  -- property
  purchasePrice double,

  -- crypto
  purchaseExchangeRate double,
  numberOfCoins double,

  -- stock
  purchaseSharePrice double,
  numberOfShares double,
  dividendTotal double,

  -- option
  optionType varchar(1),
  strikePricePerShare double,
  shareLimit double,
  premiumPerShare double,
  strikeDate date,

  foreign key (accountId) references Account(id),
  foreign key (assetId) references Asset(id)
);

insert into State (id, state) values
  (1, 'NE'),
  (2, 'IL');

insert into Country (id, country) values
  (1, 'USA');

insert into Address (id, street, city, stateId, zip, countryId) values
  (1, '123 A Street', 'Lincoln', 1, '68503', 1),
  (2, '123 B Street', 'Omaha', 1, '68116', 1),
  (3, '123 C Street', 'Chicago', 2, '32133-1233', 1),
  (4, '123 D Street', 'Lincoln', 1, '68503', 1),
  (5, '123 E Street', 'Lincoln', 1, '68503', 1);

insert into Person (id, code, lastName, firstName, addressId) values
  (1, '944c', 'Starlin', 'Castro', 1),
  (2, '306a', 'Brock', 'Sampson', 2),
  (3, '2342', 'Miles', 'O''Brein', 3),
  (4, 'aef1', 'Gordon', 'Gekko', 4),
  (5, '321f', 'Bud', 'Fox', 5);

insert into Email (id, email, personId) values
  (1, 'scastro@cubs.com', 1),
  (2, 'starlin_castro13@gmail.com', 1),
  (3, 'brock_f_sampson@gmail.com', 2),
  (4, 'bsampson@venture.com', 2),
  (5, 'bfox@gmail.com', 5);

insert into Account (id, type, number, ownerId, managerId, beneficiaryId) values
  (1, 'P', 'A01', 1, 2, 3),
  (2, 'P', 'A02', 2, 3, 4),
  (3, 'N', 'A03', 3, 4, 5),
  (4, 'N', 'A04', 4, 5, 1),
  (5, 'N', 'A05', 5, 1, 2),
  (6, 'P', 'A06', 5, 4, 3);

-- property
insert into Asset (id, code, type, label, appraisedValue) values
  (1, 'P01', 'P', 'Boardwalk', 400000),
  (2, 'P02', 'P', '57 Chevy', 47000),
  (3, 'P03', 'P', 'Honus Wagner Card', 1200000),
  (4, 'P04', 'P', '1993 Ford Festiva', 3.50),
  (5, 'P05', 'P', '2020 Honda Fit', 14500);

-- crypto
insert into Asset (id, code, type, label, exchangeRate, exchangeFeeRate) values
  (6, 'C01', 'C', 'Bitcoin', 65694.50, 1.25),
  (7, 'C02', 'C', 'Dogecoin', 0.2788, 2.75),
  (8, 'C03', 'C', 'Ethereum', 4745.83, 1.75),
  (9, 'C04', 'C', 'StupidCoin', 1.01, 8.5),
  (10, 'C05', 'C', 'Flow', 13.85, 5.5);

-- stock
insert into Asset (id, code, type, label, symbol, sharePrice) values
  (11, 'S01', 'S', 'Google', 'GOOG', 814.708),
  (12, 'S02', 'S', 'Apple', 'AAPL', 429.80),
  (13, 'S03', 'S', 'Chipotle', 'CMG', 341.91),
  (14, 'S04', 'S', 'Costco', 'COST', 106.13),
  (15, 'S05', 'S', '3D Systems', 'DDD', 35.40);

-- property
insert into OwnedAsset (id, accountId, assetId, purchaseDate, purchasePrice) values 
  (1, 4, 1, '2021-04-05', 400000),
  (2, 1, 3, '2010-08-30', 500000),
  (3, 2, 4, '1995-07-01', 3500),
  (4, 4, 5, '2020-03-01', 10500),
  (5, 4, 5, '2020-03-01', 10500);

-- crypto
insert into OwnedAsset (id, accountId, assetId, purchaseDate, purchaseExchangeRate, numberOfCoins) values
  (6, 1, 6, '2015-03-01', 5000, 5.25),
  (7, 1, 9, '2021-01-01', 65000, 0.001);

-- stock
insert into OwnedAsset (id, accountId, assetId, purchaseDate, optionType, purchaseSharePrice, numberOfShares, dividendTotal) values 
  (11, 2, 12, '2013-04-25', 'S', 340.21, 1500, 87.25),
  (12, 2, 11, '2020-01-01', 'S', 928.52, 150, 0),
  (13, 3, 15, '2016-11-03', 'S', 125.5, 10, 0),
  (14, 3, 12, '2001-10-30', 'S', 120.83, 1000, 10321),
  (15, 5, 11, '2016-02-03', 'S', 411.57, 100, 1232.52),
  (16, 5, 15, '2018-02-28', 'S', 12.5, 10, 0);

-- options
insert into OwnedAsset (id, accountId, assetId, purchaseDate, optionType, strikeDate, shareLimit, premiumPerShare, strikePricePerShare) values 
  (17, 2, 11, '2021-01-01', 'C', '2021-12-31', 100, 10, 3200),
  (18, 2, 12, '2021-01-01', 'C', '2021-12-31', 100, 100, 2800),
  (19, 4, 11, '2021-01-01', 'P', '2021-12-31', 100, 15, 3200),
  (20, 4, 13, '2021-01-01', 'P', '2019-12-31', 100, 25, 2900);