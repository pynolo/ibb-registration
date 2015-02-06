DROP TABLE IF EXISTS participants;
CREATE TABLE participants (
	id int NOT NULL auto_increment,
	code varchar(64) NOT NULL,
	email varchar(64) NOT NULL,
	name varchar(128) NOT NULL,
	created datetime DEFAULT NULL,
	arrival_time varchar(128),
	arrival_transportation varchar(128),
	country_code varchar(4),
	country_name varchar(256) NOT NULL,
	food_restrictions varchar(2048),
	PRIMARY KEY(id)
);