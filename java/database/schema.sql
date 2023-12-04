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
    employeeId SERIAL PRIMARY KEY,
    employeeName VARCHAR(255),
    username VARCHAR(50) REFERENCES users(username),
    email VARCHAR(255)
);

CREATE TABLE request (
    RequestId SERIAL PRIMARY KEY,
    employeeId INT REFERENCES employee(employeeId),
    date TIMESTAMP,
    isEmergency BOOLEAN,
    isPending BOOLEAN,
    isCovered BOOLEAN,
    isApproved BOOLEAN
);

COMMIT TRANSACTION;