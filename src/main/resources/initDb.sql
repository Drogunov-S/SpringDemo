DROP TABLE IF EXISTS person;
DROP TYPE role_enum;

CREATE TYPE role_enum AS ENUM('GUEST', 'USER', 'ADMIN');

CREATE TABLE person(
    id      INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name    VARCHAR(30) NOT NULL,
    surname VARCHAR(30) NOT NULL,
    age     INT CHECK ( age > 0 ),
    email   VARCHAR(255) UNIQUE,
    address VARCHAR(255) NOT NULL,
    role role_enum DEFAULT 'GUEST'
);

INSERT INTO person(name, age, surname, email, address)
VALUES ('JIMMY ', 9, 'Boy', 'boyboyboy@boy.vok', 'Россия, Краснодар, 123324, ул. Партизан дом 1 кв 2');

INSERT INTO person(name, age, surname, email, address, role)
VALUES ('Tom', 12, 'bosh', 'wewe@yandex.ru', 'Россия, Краснодар, 123456, ул. Партизан дом 5 кв 1', 'GUEST')
  , ('GERY', 43, 'Aisbern', 'bestboy@i.ru', 'Россия, Москва, 125476, ул. Видова дом 2', 'ADMIN')
  , ('Garry', 23, 'Brow', 'brow2341@mail.ru', 'USA, California, 230506, Wood street 7', 'GUEST')
  , ('SERGEY', 65, 'Dr', 'Dr-r@i.com', 'Parish, London, 134215, Elfi street 33', 'GUEST')
  , ('Oleg', 23, 'Smith', 'ol.123@rezolver.yu', 'Россия, Краснодар, 353000, ул. Партизан дом 3 кв 1', 'USER')
;
