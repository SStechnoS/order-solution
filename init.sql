CREATE TABLE customer
(
 id SERIAL NOT NULL,
 first_name varchar(255) NOT NULL,
 last_name varchar(255) NOT NULL,
 PRIMARY KEY (id),
 UNIQUE (first_name, last_name)
);

CREATE TABLE customer_order
(
 id SERIAL NOT NULL,
 order_sum decimal NOT NULL,
 customer_id integer NOT NULL,
 time_created timestamp NOT NULL,
 PRIMARY KEY (id),
 FOREIGN KEY(customer_id)
 REFERENCES customer(id)
);

INSERT INTO customer (first_name, last_name)
VALUES ('Arsenii', 'Ostapenko'),
('Alice', 'Beckham');

INSERT INTO customer_order (order_sum, customer_id, time_created)
VALUES (150, 1, '2024-02-13 08:05:00'),
(350, 1, '2024-04-07 12:00:00'),
(500, 1, '2024-07-30 10:45:00'),
(500, 2, '2024-05-07 07:34:00')
;