BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('rauh','rauh','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO users (username, password_hash, role) VALUES ('user1', 'user1', 'ROLE_USER');

INSERT INTO employee (employee_name, username, email)
VALUES ('Steve C.', 'user', 'steve@te.com'),
('Rachelle R.', 'rauh', 'rachelle@te.com');

INSERT INTO request (employee_id, date, is_emergency, is_pending, is_covered, is_approved, message, workplace_id)
VALUES (1, '2023-12-01 00:00:00', false, true, false, false, 'My message',1),
(1, '2023-12-02 00:00:00', false, true, false, false, 'I want to chill on the beach this day',1),
(2, '2024-12-02 00:00:00', false, true, false, false, 'My message',1),
(2, '2024-12-08 00:00:00', false, true, false, false, 'I need Dec 8th off',1),
(1, '2023-01-04', true, true, false, false, 'Regular shift swap request', 4),
(2, '2023-01-05', false, true, false, false, 'Vacation leave request', 5),
(2, '2023-01-06', true, true, false, false, 'Emergency shift coverage needed', 6),
(1, '2023-01-07', false, true, false, false, 'Can I leave early today?', 7),
(2, '2023-01-08', false, true, false, false, 'Shift swap request', 8),
(2, '2023-01-09', false, true, false, false, 'Regular day off request', 9),
(1, '2023-01-10', true, true, false, false, 'Emergency shift coverage needed', 10),
(2, '2023-01-11', false, true, false, false, 'Can I work an extra shift?', 11),
(2, '2023-01-12', false, true, false, false, 'Regular shift swap request', 12),
(1, '2023-01-13', false, true, false, false, 'Shift swap request', 13);

INSERT INTO Shift (is_covered, shift_owner_id, start_time, end_time, shift_volunteer_id)
VALUES
(TRUE, 1, '2022-12-05 08:00:00', '2022-12-05 16:00:00', null),
(TRUE, 1, '2023-12-16 08:00:00', '2023-12-16 16:00:00',2),
(TRUE, 2, '2024-12-07 08:00:00', '2024-12-07 16:00:00', null),
(TRUE, 2, '2025-12-08 08:00:00', '2025-12-08 09:00:00', null),
(TRUE, 2, '2025-12-08 09:00:00', '2025-12-08 10:00:00', 1),
(FALSE, 2, '2025-12-08 10:00:00', '2025-12-08 11:00:00', null),
(TRUE, 1, '2025-12-08 10:00:00', '2025-12-08 11:00:00', 2),
(false, 2, '2023-01-05 12:00:00', '2023-01-05 20:00:00', null),
        (false, 2, '2023-01-06 09:00:00', '2023-01-06 17:00:00', null),
        (TRUE, 1, '2023-01-07 08:00:00', '2023-01-07 16:00:00', null),
        (false, 2, '2023-01-08 12:00:00', '2023-01-08 20:00:00', null),
        (false, 2, '2023-01-09 09:00:00', '2023-01-09 17:00:00', null),
        (TRUE, 1, '2023-01-10 08:00:00', '2023-01-10 16:00:00', null),
        (false, 2, '2023-01-11 12:00:00', '2023-01-11 20:00:00', null),
        (false, 2, '2023-01-12 09:00:00', '2023-01-12 17:00:00', null),
        (TRUE, 1, '2023-01-13 08:00:00', '2023-01-13 16:00:00', null);
COMMIT TRANSACTION;
