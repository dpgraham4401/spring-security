CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE SCHEMA IF NOT EXISTS spring;

CREATE TABLE IF NOT EXISTS spring.users
(
    id       UUID PRIMARY KEY      DEFAULT uuid_generate_v4(),
    username VARCHAR(50)  NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    enabled  BOOLEAN      NOT NULL DEFAULT true
);

CREATE INDEX idx_username ON spring.users (username);

CREATE TABLE IF NOT EXISTS spring.authorities
(
    id        SERIAL PRIMARY KEY,
    username  VARCHAR(255) NOT NULL,
    authority VARCHAR(255) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES spring.users (username)
);

CREATE UNIQUE INDEX ix_auth_username ON spring.authorities (username, authority);
