BEGIN TRANSACTION;

INSERT INTO users (username, password_hash, role) VALUES ('user1', 'user1', 'ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('user2', 'user2', 'ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('user3', 'user3', 'ROLE_USER');

INSERT INTO employee (employee_name, username, email) VALUES ('Steve C.', 'user1', 'steve@te.com');

INSERT INTO request (employee_id, date, is_emergency, is_pending, is_covered, is_approved, message)
VALUES (1, '2023-12-01 00:00:00', false, true, false, false, 'My message'),
(1, '2023-12-02 00:00:00', false, true, false, false, 'My message'),
(1, '2024-12-02 00:00:00', false, true, false, false, 'My message');


COMMIT TRANSACTION;
