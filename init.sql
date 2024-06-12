CREATE TABLE customer
(
 id SERIAL NOT NULL,
 first_name varchar(255) NOT NULL,
 last_name varchar(255) NOT NULL,
 PRIMARY KEY (id),
 UNIQUE (first_name, last_name)
);

INSERT INTO customer (first_name, last_name)
VALUES ('Arsenii', 'Ostapenko'),
('Alice', 'Beckham');