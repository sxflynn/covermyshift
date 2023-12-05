BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO employee (employee_name, username, email)
VALUES ('Steve C.', 'user', 'steve@te.com');

INSERT INTO request (employee_id, date, is_emergency, is_pending, is_covered, is_approved, message)
VALUES (1, '2023-12-01 00:00:00', false, true, false, false, 'My message'),
(1, '2023-12-02 00:00:00', false, true, false, false, 'My message'),
(1, '2024-12-02 00:00:00', false, true, false, false, 'My message');

INSERT INTO Shift (is_covered, shift_owner_id, start_time, end_time)
VALUES
(FALSE, 1, '2022-12-05 08:00:00', '2022-12-05 16:00:00'),
(TRUE, 1, '2023-12-06 08:00:00', '2023-12-06 16:00:00'),
(FALSE, 1, '2024-12-07 08:00:00', '2024-12-07 16:00:00');

COMMIT TRANSACTION;
