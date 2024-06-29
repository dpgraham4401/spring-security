INSERT INTO foo.users(id, username, password)
VALUES ('4e97c051-76ef-40c9-812b-cb064abc437c', 'john', '$2a$10$bh59wmSe3rIc2uKGd3hJPeDVFNgJx9hnaCUUdsqSwwwppNEy6Anze')
ON CONFLICT DO NOTHING;

INSERT INTO foo.authorities(user_id, authority)
VALUES ('4e97c051-76ef-40c9-812b-cb064abc437c', 'read')
ON CONFLICT DO NOTHING;
