BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO employee (employee_name, username, email)
VALUES ('Steve C.', 'user', 'steve@te.com');

INSERT INTO request (employee_id, date, is_emergency, is_pending, is_covered, is_approved, message)
VALUES (1, '2023-12-01 00:00:00', false, true, false, false, 'My message'),
(1, '2023-12-02 00:00:00', false, true, false, false, 'My message'),
(1, '2024-12-02 00:00:00', false, true, false, false, 'My message');

COMMIT TRANSACTION;
