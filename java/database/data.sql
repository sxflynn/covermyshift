BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('rauh','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('john','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('laura','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO employee (employee_name, username, email)
VALUES ('Steve C.', 'user', 'stephenxflynn+steve@gmail.com'),
('Rachelle R.', 'rauh', 'stephenxflynn+rauh@gmail.com'),
('John F.', 'john','stephenxflynn+john@gmail.com'),
('Laura C.', 'laura','stephenxflynn+laura@gmail.com'),
('Kevin G.', 'admin', 'stephenxflynn+admin@gmail.com');

INSERT INTO request (employee_id, date, is_emergency, is_pending, is_covered, is_approved, message, workplace_id)
VALUES
(1, '2023-12-12', true, true, false, false, 'Emergency: Family situation', 1),
(1, '2023-12-14', false, true, false, false, 'Need day off for rest', 1),

(2, '2023-12-13', true, true, false, false, 'Urgent: Medical appointment', 1),
(2, '2023-12-15', false, true, false, false, 'Personal day required', 1),

(3, '2023-12-18', true, true, false, false, 'Emergency: Car repair needed', 1),
(3, '2023-12-20', false, true, false, false, 'Requesting off for event', 1),

(4, '2023-12-19', true, true, false, false, 'Urgent: Childcare issue', 1),
(4, '2023-12-21', false, true, false, false, 'Day off for relaxation', 1);


INSERT INTO Shift (is_covered, shift_owner_id, start_time, end_time, shift_volunteer_id)
VALUES
(TRUE, 1, '2022-12-11 08:00:00', '2022-12-11 11:00:00', null),
(TRUE, 1, '2023-12-11 08:00:00', '2023-12-11 11:00:00', null),
(TRUE, 1, '2023-12-13 08:00:00', '2023-12-13 11:00:00', null),
(TRUE, 1, '2023-12-15 08:00:00', '2023-12-15 11:00:00', null),

(TRUE, 2, '2023-12-11 12:00:00', '2023-12-11 15:00:00', null),
(TRUE, 2, '2023-12-13 12:00:00', '2023-12-13 15:00:00', null),
(TRUE, 2, '2023-12-15 12:00:00', '2023-12-15 15:00:00', null),

(TRUE, 3, '2023-12-12 08:00:00', '2023-12-12 11:00:00', null),
(TRUE, 3, '2023-12-14 08:00:00', '2023-12-14 11:00:00', null),
(TRUE, 3, '2023-12-15 08:00:00', '2023-12-15 11:00:00', null),

(TRUE, 4, '2023-12-12 12:00:00', '2023-12-12 15:00:00', null),
(TRUE, 4, '2023-12-14 12:00:00', '2023-12-14 15:00:00', null),
(TRUE, 4, '2023-12-15 12:00:00', '2023-12-15 15:00:00', null),

-- Continuing for the next week
(TRUE, 1, '2023-12-18 08:00:00', '2023-12-18 11:00:00', null),
(TRUE, 1, '2023-12-20 08:00:00', '2023-12-20 11:00:00', null),
(TRUE, 1, '2023-12-22 08:00:00', '2023-12-22 11:00:00', null),

(TRUE, 2, '2023-12-18 12:00:00', '2023-12-18 15:00:00', null),
(TRUE, 2, '2023-12-20 12:00:00', '2023-12-20 15:00:00', null),
(TRUE, 2, '2023-12-22 12:00:00', '2023-12-22 15:00:00', null),

(TRUE, 3, '2023-12-19 08:00:00', '2023-12-19 11:00:00', null),
(TRUE, 3, '2023-12-21 08:00:00', '2023-12-21 11:00:00', null),
(TRUE, 3, '2023-12-22 08:00:00', '2023-12-22 11:00:00', null),

(TRUE, 4, '2023-12-19 12:00:00', '2023-12-19 15:00:00', null),
(TRUE, 4, '2023-12-21 12:00:00', '2023-12-21 15:00:00', null),
(TRUE, 4, '2023-12-22 12:00:00', '2023-12-22 15:00:00', null);
COMMIT TRANSACTION;
