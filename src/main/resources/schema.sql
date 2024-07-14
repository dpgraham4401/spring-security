CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE SCHEMA IF NOT EXISTS foo;

CREATE TABLE IF NOT EXISTS foo.users
(
    id       UUID PRIMARY KEY      DEFAULT uuid_generate_v4(),
    username VARCHAR(50)  NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    enabled  BOOLEAN      NOT NULL DEFAULT true
);

CREATE INDEX IF NOT EXISTS idx_username ON foo.users (username);

CREATE TABLE IF NOT EXISTS foo.authorities
(
    id        SERIAL PRIMARY KEY,
    user_id   UUID         NOT NULL,
    authority VARCHAR(255) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY (user_id) REFERENCES foo.users (id)
);

CREATE UNIQUE INDEX IF NOT EXISTS ix_auth_username ON foo.authorities (user_id, authority);

CREATE TABLE IF NOT EXISTS foo.greetings
(
    id          SERIAL PRIMARY KEY,
    user_id     UUID         NOT NULL,
    message     VARCHAR(255) NOT NULL,
    is_positive BOOLEAN      NOT NULL DEFAULT true,
    CONSTRAINT fk_greetings_users FOREIGN KEY (user_id) REFERENCES foo.users (id)
);

ALTER TABLE foo.greetings
    ADD CONSTRAINT unique_user_id UNIQUE (user_id);