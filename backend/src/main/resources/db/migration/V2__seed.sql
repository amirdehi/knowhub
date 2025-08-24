INSERT INTO roles(name) VALUES ('ADMIN'),('PROJECT_MANAGER'),('TEAM_MEMBER'),('GUEST');

INSERT INTO users(username,email,password_hash,display_name)
VALUES('admin','admin@example.com','$2a$10$Qkcd/5q9LuS9Y4h8g0tQmu6sGfWumwVfFqvQOa7kWZp2m7n3NnTWe','Administrator'); -- bcrypt for 'admin123'

INSERT INTO user_roles(user_id,role_id)
SELECT u.id, r.id FROM users u JOIN roles r ON r.name='ADMIN' WHERE u.username='admin';
