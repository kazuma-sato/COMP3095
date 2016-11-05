DELETE DATABASE IF EXISTS COMP3095;

CREATE DATABASE IF NOT EXISTS COMP3095;
USE COMP3095;
GRANT all ON COMP3095.* TO 'admin'@'localhost' IDENTIFIED BY 'admin'; 


CREATE TABLE users 
( 
	id int(11) AUTO_INCREMENT, 
	firstname varchar(255),
	lastname varchar(255),
	email varchar(255) NOT NULL, 
	phone varchar(10),
	year varchar(10),
	major varchar(10),
	username varchar(20) NOT NULL,
	password varchar(20) NOT NULL,

	CONSTRAINT pk_users PRIMARY KEY (id)
);

INSERT INTO users (id, firstname, lastname, email, phone, year, major, username, password) VALUES
(99, NULL, NULL, 'admin@domain.ca', NULL, NULL, NULL, 'admin', 'admin');