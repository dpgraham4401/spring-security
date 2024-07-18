CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE SCHEMA IF NOT EXISTS foo;

CREATE TABLE IF NOT EXISTS foo.users
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(50)  NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    enabled  BOOLEAN      NOT NULL DEFAULT true
);

CREATE INDEX IF NOT EXISTS idx_username ON foo.users (username);

CREATE TABLE IF NOT EXISTS foo.authorities
(
    id        SERIAL PRIMARY KEY,
    user_id   INTEGER      NOT NULL,
    authority VARCHAR(255) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY (user_id) REFERENCES foo.users (id)
);

CREATE UNIQUE INDEX IF NOT EXISTS ix_auth_username ON foo.authorities (user_id, authority);

CREATE TABLE IF NOT EXISTS foo.products
(
    id    SERIAL PRIMARY KEY,
    owner VARCHAR      NOT NULL,
    name  VARCHAR(255) NOT NULL,
    price FLOAT        NOT NULL,
    CONSTRAINT fk_greetings_users FOREIGN KEY (owner) REFERENCES foo.users (username),
    CONSTRAINT unique_name_user_id UNIQUE (name, owner)
);