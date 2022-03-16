CREATE TABLE users
(
    id       BIGSERIAL,
    name     varchar(32) NOT NULL,
    surname  varchar(32) NOT NULL,
    email    varchar(45) UNIQUE NOT NULL,
    phone    varchar(15) UNIQUE NOT NULL,
    password varchar(64) NOT NULL,
    enabled  boolean     NOT NULL,
    primary key (id)
);

CREATE TABLE users_roles
(
    user_id BIGSERIAL,
    role varchar(255)
);

ALTER TABLE if exists users_roles
    add constraint users_roles_foreign_key
    foreign key (user_id) references users;