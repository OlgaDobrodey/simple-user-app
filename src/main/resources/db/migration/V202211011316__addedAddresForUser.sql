CREATE TABLE address(
    id SERIAL PRIMARY KEY,
    city VARCHAR NOT NULL,
    street VARCHAR NOT NULL,
    house VARCHAR NOT NULL
);

ALTER TABLE users
ADD COLUMN address INT;

ALTER TABLE users
ADD FOREIGN KEY (address) REFERENCES address(id);

INSERT INTO address(city, street, house)
VALUES ('Minsk','Mira', '12a'),
       ('Vitebsk','Mira','14'),
       ('Minsk','Lenina','15a'),
       ('City1','Street1', '12a'),
       ('City2','MStreet2','14'),
       ('City3','Street3','15a');

UPDATE users
SET address = 1
WHERE id = 1;

UPDATE users
SET address = 2
WHERE id = 2;

UPDATE users
SET address = 3
WHERE id = 3;

UPDATE users
SET address = 4
WHERE id = 4;

UPDATE users
SET address = 5
WHERE id = 5;


