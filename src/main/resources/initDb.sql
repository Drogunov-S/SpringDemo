CREATE TABLE person
(
    id      SERIAL,
    name    VARCHAR(30),
    surname VARCHAR(30),
    age     INTEGER,
    email   TEXT
);


INSERT INTO person(name, age, surname, email)
VALUES ('Tom', 12, 'bosh', 'wewe@yandex.ru')
     , ('GERY', 43, 'Aisbern', 'bestboy@i.ru')
     , ('Garry', 23, 'Brow', 'brow2341@mail.ru')
     , ('SERGEY', 65, 'Dr', 'Dr-r@i.com')
     , ('Oleg', 23, 'Smith', 'ol.123@rezolver.yu')
     , ('JIMMY ', 9, 'Boy', 'boyboyboy@boy.vok');

