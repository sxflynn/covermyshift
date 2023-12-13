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
(1, '2023-12-26', false, true, false, false, 'Need day off for rest', 1),

(2, '2023-12-13', true, true, false, false, 'Urgent: Medical appointment', 1),
(2, '2023-12-15', false, false, true, true, 'Personal day required', 1),

(3, '2023-12-18', true, true, false, false, 'Emergency: Car repair needed', 1),
(3, '2023-12-20', false, true, false, false, 'Requesting off for event', 1),

(4, '2023-12-19', true, true, false, false, 'Urgent: Childcare issue', 1),
(4, '2023-12-20', false, false, false, true, 'Day off for relaxation', 1);


INSERT INTO Shift (is_covered, shift_owner_id, start_time, end_time, shift_volunteer_id)
VALUES
  (TRUE, 1, '2023-12-01 08:00:00', '2023-12-01 12:00:00', null),
  (TRUE, 1, '2023-12-04 10:00:00', '2023-12-04 14:00:00', null),
  (TRUE, 1, '2023-12-05 09:00:00', '2023-12-05 13:00:00', null),
  (TRUE, 1, '2023-12-06 11:00:00', '2023-12-06 15:00:00', null),
  (TRUE, 1, '2023-12-07 08:00:00', '2023-12-07 12:00:00', null),
  (TRUE, 1, '2023-12-08 10:00:00', '2023-12-08 14:00:00', null),
  (TRUE, 1, '2023-12-11 09:00:00', '2023-12-11 13:00:00', null),
  (TRUE, 1, '2023-12-12 09:00:00', '2023-12-12 13:00:00', null),

  (TRUE, 1, '2023-12-13 08:00:00', '2023-12-13 12:00:00', null),


  (TRUE, 1, '2023-12-18 08:00:00', '2023-12-18 12:00:00', null),
  (TRUE, 1, '2023-12-19 10:00:00', '2023-12-19 14:00:00', null),
  (TRUE, 1, '2023-12-20 09:00:00', '2023-12-20 13:00:00', null),
  (TRUE, 1, '2023-12-21 11:00:00', '2023-12-21 15:00:00', null),
  (TRUE, 1, '2023-12-22 08:00:00', '2023-12-22 12:00:00', null),
  (TRUE, 1, '2023-12-25 10:00:00', '2023-12-25 14:00:00', null),
  (TRUE, 1, '2023-12-26 09:00:00', '2023-12-26 13:00:00', null),
  (TRUE, 1, '2023-12-27 11:00:00', '2023-12-27 15:00:00', null),
  (TRUE, 1, '2023-12-28 08:00:00', '2023-12-28 12:00:00', null),
  (TRUE, 1, '2023-12-29 10:00:00', '2023-12-29 14:00:00', null),
  (TRUE, 1, '2023-12-31 09:00:00', '2023-12-31 13:00:00', null),


  (TRUE, 2, '2023-12-01 12:00:00', '2023-12-01 16:00:00', null),
  (TRUE, 2, '2023-12-04 14:00:00', '2023-12-04 18:00:00', null),
  (TRUE, 2, '2023-12-05 13:00:00', '2023-12-05 17:00:00', null),
  (TRUE, 2, '2023-12-06 15:00:00', '2023-12-06 19:00:00', null),
  (TRUE, 2, '2023-12-07 12:00:00', '2023-12-07 16:00:00', null),
  (TRUE, 2, '2023-12-08 14:00:00', '2023-12-08 18:00:00', null),
  (TRUE, 2, '2023-12-11 15:00:00', '2023-12-11 19:00:00', null),
  (TRUE, 2, '2023-12-12 13:00:00', '2023-12-12 17:00:00', null),

  (TRUE, 2, '2023-12-14 14:00:00', '2023-12-14 18:00:00', null),

  (TRUE, 2, '2023-12-18 14:00:00', '2023-12-18 18:00:00', null),
  (TRUE, 2, '2023-12-19 13:00:00', '2023-12-19 17:00:00', null),
  (TRUE, 2, '2023-12-20 15:00:00', '2023-12-20 19:00:00', null),
  (TRUE, 2, '2023-12-21 12:00:00', '2023-12-21 16:00:00', null),
  (TRUE, 2, '2023-12-22 14:00:00', '2023-12-22 18:00:00', null),
  (TRUE, 2, '2023-12-25 13:00:00', '2023-12-25 17:00:00', null),
  (TRUE, 2, '2023-12-26 15:00:00', '2023-12-26 19:00:00', null),
  (TRUE, 2, '2023-12-27 12:00:00', '2023-12-27 16:00:00', null),
  (TRUE, 2, '2023-12-28 14:00:00', '2023-12-28 18:00:00', null),
  (TRUE, 2, '2023-12-29 13:00:00', '2023-12-29 17:00:00', null),
  (TRUE, 2, '2023-12-31 15:00:00', '2023-12-31 19:00:00', null),


  (TRUE, 3, '2023-12-04 08:00:00', '2023-12-04 12:00:00', null), -- Monday
  (TRUE, 3, '2023-12-05 08:00:00', '2023-12-05 12:00:00', null), -- Tuesday
  (TRUE, 3, '2023-12-07 08:00:00', '2023-12-07 12:00:00', null), -- Thursday
  (TRUE, 3, '2023-12-11 08:00:00', '2023-12-11 12:00:00', null), -- Monday
  (TRUE, 3, '2023-12-12 08:00:00', '2023-12-12 12:00:00', null), -- Tuesday
  (TRUE, 3, '2023-12-14 08:00:00', '2023-12-14 12:00:00', null), -- Thursday
  (TRUE, 2, '2023-12-15 09:00:00', '2023-12-15 13:00:00', 3),


  (TRUE, 3, '2023-12-21 08:00:00', '2023-12-21 12:00:00', null), -- Thursday
  (TRUE, 3, '2023-12-25 08:00:00', '2023-12-25 12:00:00', null), -- Monday
  (TRUE, 3, '2023-12-26 08:00:00', '2023-12-26 12:00:00', null), -- Tuesday
  (TRUE, 3, '2023-12-28 08:00:00', '2023-12-28 12:00:00', null), -- Thursday
  (TRUE, 3, '2023-12-31 08:00:00', '2023-12-31 12:00:00', null), -- Monday

  (TRUE, 4, '2023-12-06 08:00:00', '2023-12-06 12:00:00', null), -- Wednesday
  (TRUE, 4, '2023-12-08 08:00:00', '2023-12-08 12:00:00', null), -- Friday
  (TRUE, 4, '2023-12-13 08:00:00', '2023-12-13 12:00:00', null), -- Wednesday
  (TRUE, 4, '2023-12-15 08:00:00', '2023-12-15 12:00:00', null), -- Friday
  (FALSE, 4, '2023-12-20 08:00:00', '2023-12-20 12:00:00', null), -- Wednesday
  (TRUE, 4, '2023-12-22 08:00:00', '2023-12-22 12:00:00', null), -- Friday
  (TRUE, 4, '2023-12-06 08:00:00', '2023-12-06 12:00:00', null), -- Wednesday
  (TRUE, 4, '2023-12-08 08:00:00', '2023-12-08 12:00:00', null), -- Friday
  (TRUE, 4, '2023-12-13 08:00:00', '2023-12-13 12:00:00', null), -- Wednesday
  (TRUE, 4, '2023-12-15 08:00:00', '2023-12-15 12:00:00', null); -- Friday





COMMIT TRANSACTION;