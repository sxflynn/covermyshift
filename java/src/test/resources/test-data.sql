BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user1','user1','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user2','user2','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user3','user3','ROLE_USER');

INSERT INTO employee (employeeName, username, email)
VALUES (1, 'Steve C.', 'user', 'steve@te.com');


INSERT INTO request (employeeId, employeeName, date, workplaceId, isEmergency, isPending, isCovered, isApproved)
VALUES (1, 'Steve C.', '2023-12-01 00:00:00', 1, false, true, false, false);

INSERT INTO request (employeeId, employeeName, date, workplaceId, isEmergency, isPending, isCovered, isApproved)
VALUES (1, 'Steve C.', '2023-12-02 00:00:00', 1, false, true, false, false);

COMMIT TRANSACTION;
