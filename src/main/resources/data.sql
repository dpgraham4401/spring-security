-- user john with password 12345
INSERT INTO foo.users(id, username, password)
VALUES ('4e97c051-76ef-40c9-812b-cb064abc437c', 'john', '$2a$10$bh59wmSe3rIc2uKGd3hJPeDVFNgJx9hnaCUUdsqSwwwppNEy6Anze')
ON CONFLICT DO NOTHING;

-- user david with password 12345
INSERT INTO foo.users(id, username, password)
VALUES ('7bb77e1a-c95c-4e29-bd8f-74b1204895a6', 'david', '$2a$10$bh59wmSe3rIc2uKGd3hJPeDVFNgJx9hnaCUUdsqSwwwppNEy6Anze')
ON CONFLICT DO NOTHING;

-- give john read and write permissions
INSERT INTO foo.authorities(user_id, authority)
VALUES ('4e97c051-76ef-40c9-812b-cb064abc437c', 'READ'),
       ('4e97c051-76ef-40c9-812b-cb064abc437c', 'WRITE')
ON CONFLICT DO NOTHING;

-- give david read permission
INSERT INTO foo.authorities(user_id, authority)
VALUES ('7bb77e1a-c95c-4e29-bd8f-74b1204895a6', 'READ')
ON CONFLICT DO NOTHING;

-- create greetings objects that belong to David
INSERT INTO foo.greetings(user_id, message, is_positive)
VALUES ('7bb77e1a-c95c-4e29-bd8f-74b1204895a6', 'Guten Tag', false)
ON CONFLICT DO NOTHING;


-- create greetings objects that belong to John
INSERT INTO foo.greetings(user_id, message, is_positive)
VALUES ('4e97c051-76ef-40c9-812b-cb064abc437c', 'Howdy', true)
ON CONFLICT DO NOTHING;
