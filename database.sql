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
insert into `config` (name, val) values ('ticketCount', '0');
insert into `config` (name, val) values ('maxTicketCount', '38');
insert into `config` (name, val) values ('reducedTicketCount', '0');
insert into `config` (name, val) values ('maxReducedTicketCount', '0');
insert into `config` (name, val) values ('priceFull', '58.00');
insert into `config` (name, val) values ('priceReduced', '58.00');
insert into `config` (name, val) values ('serviceOpen','1');
