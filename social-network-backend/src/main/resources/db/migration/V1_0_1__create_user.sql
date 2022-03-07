CREATE TABLE users
(
    id       SERIAL,
    name     varchar(32) NOT NULL,
    surname  varchar(32) NOT NULL,
    email    varchar(45) NOT NULL,
    phone    varchar(20) NOT NULL,
    password varchar(64) NOT NULL,
    enabled  boolean     NOT NULL
)