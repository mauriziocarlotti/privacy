CREATE DATABASE IF NOT EXISTS privacy CHARACTER SET utf8 COLLATE utf8_general_ci;
CREATE USER privacy@'127.0.0.1' IDENTIFIED BY 'Privacy2000';
GRANT ALL ON privacy.* TO 'privacy'@'127.0.0.1';
FLUSH PRIVILEGES;

CREATE TABLE category (

	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(100),
	description VARCHAR(255),
	image_url VARCHAR(255),
	is_active BOOLEAN,

CONSTRAINT pk_category_id PRIMARY KEY (id)
);

INSERT INTO category (name, description, image_url, is_active) VALUES ('Informativa completa', 'Questa è informativa completa','CAT_01.png', 1);
INSERT INTO category (name, description, image_url, is_active) VALUES ('Informativa per sito web', 'Questa è informativa per newsletter web','CAT_02.png', 1);
INSERT INTO category (name, description, image_url, is_active) VALUES ('Informativa generica', 'Questa è informativa generica','CAT_03.png', 1);

CREATE TABLE user_detail (

	id INT NOT NULL AUTO_INCREMENT,
	first_name VARCHAR(255),
	last_name VARCHAR(255),
	role VARCHAR(200),
	enabled BOOLEAN,
	password VARCHAR(255),
	
	email VARCHAR(255),
	contact_number VARCHAR(255),

CONSTRAINT pk_user_id PRIMARY KEY (id)
);

INSERT INTO user_detail (first_name, last_name, role, enabled, password, email, contact_number) VALUES ('maurizio','carlotti','ADMIN',true,'admin','maurizio.carlotti@satelsrl.it','335309675');
INSERT INTO user_detail (first_name, last_name, role, enabled, password, email, contact_number) VALUES ('roberto','carlotti','ADMIN',true,'admin','roberto.carlotti@satelsrl.it','335309675');

CREATE TABLE customer (
	id INT NOT NULL AUTO_INCREMENT,
	first_name VARCHAR(255),
	last_name VARCHAR(255),
	is_active BOOLEAN,
	description VARCHAR(255),
	email VARCHAR(255),
	codice VARCHAR(255),
	contact_number VARCHAR(255),
	category_id INT,
	supplier_id INT,
	
CONSTRAINT pk_customer_id PRIMARY KEY (id),
CONSTRAINT fk_customer_category_id FOREIGN KEY (category_id) REFERENCES category (id),
CONSTRAINT fk_customer_supplier_id FOREIGN KEY (supplier_id) REFERENCES user_detail (id)
);

INSERT INTO customer (first_name, last_name, is_active, description, email, contact_number, category_id, supplier_id) VALUES ('Mario', 'Rossi', true, '', 'mario.rossi@libero.it', '0000',1,1);
INSERT INTO customer (first_name, last_name, is_active, description, email, contact_number, category_id, supplier_id) VALUES ('Giuseppe', 'Verdi', true, '', 'giuseppe.verdi@libero.it', '0000',2,1);

CREATE TABLE address (
	id INT NOT NULL AUTO_INCREMENT,
	customer_id int,
	address_line_one VARCHAR(255),
	address_line_two VARCHAR(255),
	city VARCHAR(255),
	country VARCHAR(255),
	postal_code VARCHAR(255),
	is_shipping BOOLEAN,
	is_billing BOOLEAN,
	
CONSTRAINT pk_address_id PRIMARY KEY (id)
);
	