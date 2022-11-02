CREATE SCHEMA SEP3;
SET SCHEMA 'sep3';
CREATE TABLE Users
(
    userId       SERIAL PRIMARY KEY,
    userName     varchar(16)   NOT NULL,
    userPass     varchar(32)   NOT NULL,
    full_Name    varchar(100)  NOT NULL,
    email        varchar(100)  NOT NULL,
    phone_Number varchar(16)   NOT NULL CHECK ( Users.phone_Number LIKE '+45%' ),
    address      varchar(50)   NOT NULL,
    rating       decimal(3, 2) NOT NULL
);

CREATE TABLE Posts
(
    postId      serial PRIMARY KEY,
    description varchar(300) NOT NULL,
    date        DATE         NOT NULL,
    location    varchar(100) NOT NULL,
    category    varchar(50)  NOT NULL,
    seller      varchar(16)  NOT NULL
);

CREATE TABLE Items
(
    itemId  serial PRIMARY KEY,
    price   decimal(9, 2) NOT NULL,
    picture varchar(100)
);