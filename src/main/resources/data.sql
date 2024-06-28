INSERT INTO spring.users(username, password)
VALUES ('john', '12345');
INSERT INTO spring.users(username, password)
VALUES ('david', 'password');

INSERT INTO spring.authorities(username, authority)
VALUES ('john', 'read');
INSERT INTO spring.authorities(username, authority)
VALUES ('david', 'write');
