BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, employee, request;

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
    Request_id SERIAL PRIMARY KEY,
    employee_id INT REFERENCES employee(employee_Id),
    employee_name VARCHAR(255),
    date TIMESTAMP,
    workplace_id INT,
    is_emergency BOOLEAN,
    is_pending BOOLEAN,
    is_covered BOOLEAN,
    is_approved BOOLEAN
);

COMMIT TRANSACTION;