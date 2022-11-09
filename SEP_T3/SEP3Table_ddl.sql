DROP SCHEMA IF EXISTS sep3 CASCADE;

CREATE SCHEMA IF NOT EXISTS sep3;
SET SCHEMA 'sep3';

DROP TABLE IF EXISTS Users CASCADE;
CREATE TABLE IF NOT EXISTS Users
(
    user_id       SERIAL UNIQUE PRIMARY KEY,
    username      VARCHAR(16) UNIQUE NOT NULL,
    user_pass     VARCHAR(32)        NOT NULL,
    full_name     VARCHAR(100),
    email         VARCHAR(100),
    phone_number  VARCHAR(11)        NOT NULL,
    address       VARCHAR(50)        NOT NULL,
    registered_on TIMESTAMP ,
    last_seen     TIMESTAMP
);

DROP TABLE IF EXISTS Ratings CASCADE;
CREATE TABLE IF NOT EXISTS Ratings
(
    rating_id    SERIAL UNIQUE PRIMARY KEY,
    rating_value INT NOT NULL,
    user_from    INT NOT NULL,
    user_to      INT NOT NULL,
    FOREIGN KEY (user_from) REFERENCES Users (user_id) ON DELETE CASCADE,
    FOREIGN KEY (user_to) REFERENCES Users (user_id) ON DELETE CASCADE
);
DROP TABLE IF EXISTS Categories CASCADE;
CREATE TABLE Categories
(
    category_id SERIAL PRIMARY KEY,
    description VARCHAR(50)
);


DROP TABLE IF EXISTS Posts CASCADE;
CREATE TABLE Posts
(
    post_id       SERIAL PRIMARY KEY,
    creation_date DATE NOT NULL,
    description   TEXT,
    location      VARCHAR(100),
    category_id   INT  NOT NULL,
    sellerId      INT  NOT NULL,
    picture_url   VARCHAR(50),
    price         INT  NOT NULL,

    FOREIGN KEY (sellerId) REFERENCES Users (user_id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES Categories (category_id) ON DELETE CASCADE
);

INSERT INTO users(username, user_pass, full_name, email, phone_number, address,registered_on,last_seen)
VALUES ('Raedrim','test','Levente','love@you.com','+4591773044','Horsens',now(),now());



SELECT *
FROM users;
SELECT *
FROM posts;