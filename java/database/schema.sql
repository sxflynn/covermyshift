BEGIN TRANSACTION;

DROP TABLE IF EXISTS request, employee, users;

CREATE TABLE users (
    user_id SERIAL,
    username varchar(50) NOT NULL UNIQUE,
    password_hash varchar(200) NOT NULL,
    role varchar(50) NOT NULL,
    CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE employee (
    employee_id SERIAL PRIMARY KEY,
    employee_name VARCHAR(255),
    username VARCHAR(50) REFERENCES users(username),
    email VARCHAR(255)
);

CREATE TABLE request (
    request_id SERIAL PRIMARY KEY,
    employee_id INT REFERENCES employee(employee_id),
    date TIMESTAMP WITH TIME ZONE,
    message VARCHAR(5000),
    is_emergency BOOLEAN,
    is_pending BOOLEAN,
    is_covered BOOLEAN,
    is_approved BOOLEAN
);

CREATE TABLE Shift (
    shift_id SERIAL PRIMARY KEY,
    is_covered BOOLEAN,
    shift_owner_id INT,
    shift_owner_name VARCHAR(255),
    shift_volunteer_id INT,
    shift_volunteer_name VARCHAR(255),
    start_time TIMESTAMP,
    endTime TIMESTAMP
);


COMMIT TRANSACTION;