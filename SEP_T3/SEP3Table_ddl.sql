DROP SCHEMA IF EXISTS sep3 CASCADE;

CREATE SCHEMA IF NOT EXISTS sep3;
SET SCHEMA 'sep3';

DROP TABLE IF EXISTS Users CASCADE;
CREATE TABLE IF NOT EXISTS Users
(
    userId       SERIAL UNIQUE PRIMARY KEY,
    userName     varchar(16) UNIQUE NOT NULL,
    userPass     varchar(32)        NOT NULL,
    full_Name    varchar(100)       NOT NULL,
    email        varchar(100)       NOT NULL,
    phone_Number varchar(16)        NOT NULL CHECK ( Users.phone_Number LIKE '+45%' ),
    address      varchar(50)        NOT NULL,
    rating       decimal(3, 2)      NOT NULL
);
DROP TABLE IF EXISTS Posts CASCADE;
CREATE TABLE Posts
(
    postId      serial PRIMARY KEY,
    description varchar(300) NOT NULL,
    date        DATE         NOT NULL,
    location    varchar(100) NOT NULL,
    category    varchar(50)  NOT NULL,
    sellerId    int          NOT NULL,
    FOREIGN KEY (sellerId) REFERENCES Users (userId) ON DELETE CASCADE
);

DROP TABLE IF EXISTS Items CASCADE;
CREATE TABLE Items
(
    itemId  serial PRIMARY KEY,
    postId  int,
    price   decimal(9, 2) NOT NULL,
    picture varchar(100),
    FOREIGN KEY (postId) REFERENCES posts (postId)
);