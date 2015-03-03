DROP TABLE IF EXISTS participants;
CREATE TABLE participants (
	id int NOT NULL auto_increment,
	code varchar(64) NOT NULL,
	email varchar(64) NOT NULL,
	name varchar(128) NOT NULL,
	created datetime DEFAULT NULL,
	arrival_time varchar(128),
	country_name varchar(256) NOT NULL,
	food_restrictions varchar(2048),
	volunteering varchar(2048),
	amount decimal(8,2),
	payment datetime DEFAULT NULL,
	PRIMARY KEY(id)
);

DROP TABLE IF EXISTS config;
CREATE TABLE config (
	id int NOT NULL auto_increment,
	name varchar(64) NOT NULL,
	val varchar(256) NOT NULL,
	PRIMARY KEY(id)
);
ALTER TABLE `config` ADD UNIQUE (`name`);
ALTER TABLE `config` ADD INDEX `config_name` (`name`);
insert into `config` (name, val) values ('maxTicketCount', '38');
insert into `config` (name, val) values ('maxForeignTicketCount', '0');
insert into `config` (name, val) values ('priceFull', '60.00');
insert into `config` (name, val) values ('priceForeign', '60.00');
insert into `config` (name, val) values ('priceReduced', '10.00');
insert into `config` (name, val) values ('serviceOpen','1');

DROP TABLE IF EXISTS ipn_responses;
CREATE TABLE ipn_responses (
	id int NOT NULL auto_increment,
	item_number varchar(128) DEFAULT NULL,
	payment_status varchar(128) DEFAULT NULL,
	payer_email varchar(128) DEFAULT NULL,
	mc_gross varchar(16) DEFAULT NULL,
	mc_currency varchar(16) DEFAULT NULL,
	payment_date varchar(128) DEFAULT NULL,
	pending_reason varchar(128) DEFAULT NULL,
	payment_type varchar(128) DEFAULT NULL,
	PRIMARY KEY(id)
);

DROP TABLE IF EXISTS resales;
CREATE TABLE resales (
	id int NOT NULL auto_increment,
	created datetime NOT NULL,
	expiration datetime NOT NULL,
	resale_type varchar(4) NOT NULL,
	message varchar(256) NOT NULL,
	email varchar(64) NOT NULL,
	days int(11) NOT NULL,
	PRIMARY KEY(id)
);
