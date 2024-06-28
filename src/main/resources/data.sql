INSERT INTO foo.users(id, username, password)
VALUES ('4e97c051-76ef-40c9-812b-cb064abc437c', 'john', '12345')
ON CONFLICT DO NOTHING;
INSERT INTO foo.users(id, username, password)
VALUES ('e33fb593-7497-4983-bbe4-7df7f22ad331', 'david', 'password')
ON CONFLICT DO NOTHING;

INSERT INTO foo.authorities(user_id, authority)
VALUES ('4e97c051-76ef-40c9-812b-cb064abc437c', 'read')
ON CONFLICT DO NOTHING;
INSERT INTO foo.authorities(user_id, authority)
VALUES ('e33fb593-7497-4983-bbe4-7df7f22ad331', 'write')
ON CONFLICT DO NOTHING;
