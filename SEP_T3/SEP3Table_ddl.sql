DROP SCHEMA IF EXISTS sep3 CASCADE;

CREATE SCHEMA IF NOT EXISTS sep3;
SET SCHEMA 'sep3';

DROP TABLE IF EXISTS Users CASCADE;
CREATE TABLE IF NOT EXISTS Users
(
    userId       SERIAL UNIQUE PRIMARY KEY,
    userName     VARCHAR(16) UNIQUE NOT NULL,
    userPass     VARCHAR(32)        NOT NULL,
    full_Name    VARCHAR(100)       NOT NULL,
    email        VARCHAR(100)       NOT NULL,
    phone_Number VARCHAR(16)        NOT NULL CHECK ( Users.phone_Number LIKE '+45%' ),
    address      VARCHAR(50)        NOT NULL,
    rating       DECIMAL(3, 2)      NOT NULL
);

INSERT INTO users (username, userpass, full_name, email, phone_number, address, rating)
VALUES ('Raedrim', '12345678', 'Levente Szajko', 'levente.szajko@outlook.com', '+4522334455', 'Horsens', 4);

INSERT INTO users (username, userpass, full_name, email, phone_number, address, rating)
VALUES ('Kruno', 'no', 'Kruno Ne-ric', 'bing@bong.fuckyou', '+4522334455', 'Aarhus', 4);

DROP TABLE IF EXISTS Posts CASCADE;
CREATE TABLE Posts
(
    postId      SERIAL PRIMARY KEY,
    description VARCHAR(300) NOT NULL,
    date        DATE         NOT NULL,
    location    VARCHAR(100) NOT NULL,
    category    VARCHAR(50)  NOT NULL,
    sellerId    INT          NOT NULL,
    FOREIGN KEY (sellerId) REFERENCES Users (userId) ON DELETE CASCADE
);

INSERT INTO posts (description, date, location, category, sellerId)
VALUES ('no', NOW(), 'Horsens', 'trash', 1);

INSERT INTO posts (description, date, location, category, sellerId)
VALUES ('no', NOW(), 'Aarhus', 'trash', 1);

DROP TABLE IF EXISTS Items CASCADE;
CREATE TABLE Items
(
    itemId  SERIAL PRIMARY KEY,
    postId  INT,
    price   DECIMAL(9, 2) NOT NULL,
    picture VARCHAR(100),
    FOREIGN KEY (postId) REFERENCES posts (postId)
);

INSERT INTO items (postId, price, picture)
VALUES (1, 20, 'google.com');

SELECT *
FROM items;
SELECT *
FROM users;
SELECT *
FROM posts;