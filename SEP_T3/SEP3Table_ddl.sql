DROP SCHEMA IF EXISTS sep3 CASCADE;

CREATE SCHEMA IF NOT EXISTS sep3;
SET SCHEMA 'sep3';

DROP TABLE IF EXISTS Users CASCADE;
CREATE TABLE IF NOT EXISTS Users
(
    user_id       SERIAL UNIQUE PRIMARY KEY,
    username      VARCHAR(16) UNIQUE NOT NULL,
    user_pass     VARCHAR(40)        NOT NULL,
    full_name     VARCHAR(100),
    email         VARCHAR(100),
    phone_number  VARCHAR(20)        NOT NULL,
    address       VARCHAR(50)        NOT NULL,
    registered_on TIMESTAMP ,
    last_seen     TIMESTAMP,
    role          VARCHAR(20)
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
    title         VARCHAR(50),
    description   TEXT,
    location      VARCHAR(100),
    category_id   INT  NOT NULL,
    seller_id      INT  NOT NULL,
    picture_url   TEXT,
    price         INT  NOT NULL,
    status        INT NOT NULL ,

    FOREIGN KEY (seller_id) REFERENCES Users (user_id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES Categories (category_id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS Reports CASCADE;
CREATE TABLE Reports
(
    report_id SERIAL PRIMARY KEY,
    reported_user_id INT,
    report_date TIMESTAMP NOT NULL ,
    reason TEXT,

    FOREIGN KEY (reported_user_id) REFERENCES Users(user_id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS Messages CASCADE;
CREATE TABLE Messages
(
    message_id SERIAL PRIMARY KEY,
    user_from INT,
    user_to INT,
    message_text TEXT,
    post_id INT,

    FOREIGN KEY(user_from) REFERENCES Users(user_id) ON DELETE CASCADE,
    FOREIGN KEY(user_to) REFERENCES Users(user_id) ON DELETE CASCADE ,
    FOREIGN KEY(post_id) REFERENCES Posts(post_id) ON DELETE CASCADE

);

INSERT INTO categories(description)values ('PC-Hardware');
INSERT INTO categories(description)values ('Consoles');
INSERT INTO categories(description)values ('Mobile Phones');
INSERT INTO categories(description)values ('Audio Equipment');

SELECT *
FROM Users;
SELECT *
FROM Users;