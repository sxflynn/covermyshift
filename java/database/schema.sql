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

CREATE TABLE shift (
    shift_id SERIAL PRIMARY KEY,
    is_covered BOOLEAN,
    shift_owner_id INT REFERENCES employee(employee_id),
    shift_volunteer_id INT REFERENCES employee(employee_id),
    start_time TIMESTAMP WITH TIME ZONE,
    end_time TIMESTAMP WITH TIME ZONE,
    CHECK (shift_owner_id IS NULL OR shift_volunteer_id IS NULL OR shift_owner_id != shift_volunteer_id)
);

CREATE TABLE request (
    request_id SERIAL PRIMARY KEY,
    employee_id INT REFERENCES employee(employee_id),
    date TIMESTAMP WITH TIME ZONE,
    message VARCHAR(5000),
    manager_message VARCHAR(5000),
    is_emergency BOOLEAN,
    is_pending BOOLEAN,
    is_covered BOOLEAN,
    is_approved BOOLEAN,
    workplace_id INT
);

COMMIT TRANSACTION;