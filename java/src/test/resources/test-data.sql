BEGIN TRANSACTION;

INSERT INTO users (username, password_hash, role) VALUES ('user1', 'user1', 'ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('user2', 'user2', 'ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('user3', 'user3', 'ROLE_USER');

INSERT INTO employee (employee_name, username, email)
VALUES ('Steve C.', 'user1', 'steve@te.com'),
('Rachelle R.', 'user2', 'rachelle@te.com');

INSERT INTO request (employee_id, date, is_emergency, is_pending, is_covered, is_approved, message, workplace_id)
VALUES (1, '2023-12-01 00:00:00', false, true, false, false, 'My message',1),
(1, '2023-12-02 00:00:00', false, true, false, false, 'I want to chill on the beach this day',1),
(2, '2024-12-02 00:00:00', false, true, false, false, 'My message',1);

INSERT INTO Shift (is_covered, shift_owner_id, start_time, end_time, shift_volunteer_id)
VALUES
(FALSE, 1, '2022-12-05 08:00:00', '2022-12-05 16:00:00', null),
(TRUE, 1, '2023-12-16 08:00:00', '2023-12-16 16:00:00',2),
(FALSE, 2, '2024-12-07 08:00:00', '2024-12-07 16:00:00', null);


COMMIT TRANSACTION;
