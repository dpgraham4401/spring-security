-- user john with password 12345
INSERT INTO foo.users(id, username, password)
VALUES (1, 'john', '$2a$10$bh59wmSe3rIc2uKGd3hJPeDVFNgJx9hnaCUUdsqSwwwppNEy6Anze')
ON CONFLICT DO NOTHING;

-- user david with password 12345
INSERT INTO foo.users(id, username, password)
VALUES (2, 'david', '$2a$10$bh59wmSe3rIc2uKGd3hJPeDVFNgJx9hnaCUUdsqSwwwppNEy6Anze')
ON CONFLICT DO NOTHING;

-- give john read and write permissions
INSERT INTO foo.authorities(user_id, authority)
VALUES (1, 'READ'),
       (1, 'WRITE')
ON CONFLICT DO NOTHING;

-- give david read permission
INSERT INTO foo.authorities(user_id, authority)
VALUES (2, 'READ')
ON CONFLICT DO NOTHING;

-- create product that belong to John
INSERT INTO foo.products(owner, name, price)
VALUES ('john', 'bowl', 4.99)
ON CONFLICT DO NOTHING;

-- create product that belong to John
INSERT INTO foo.products(owner, name, price)
VALUES ('john', 'plate', 5.99)
ON CONFLICT DO NOTHING;

-- create product that belong to John
INSERT INTO foo.products(owner, name, price)
VALUES ('john', 'spoon', 1.69)
ON CONFLICT DO NOTHING;

-- create product that belong to David
INSERT INTO foo.products(owner, name, price)
VALUES ('david', 'book', 32.00)
ON CONFLICT DO NOTHING;
