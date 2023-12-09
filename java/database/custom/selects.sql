SELECT
s.shift_id AS shiftId, s.is_covered AS isCovered, s.shift_owner_id AS shiftOwnerId, e_owner.employee_name AS shiftOwnerName, s.shift_volunteer_id AS shiftVolunteerId, e_volunteer.employee_name AS shiftVolunteerName, s.start_time AS startTime, s.end_time AS endTime
FROM shift s
LEFT JOIN employee e_owner ON s.shift_owner_id = e_owner.employee_id
LEFT JOIN employee e_volunteer ON s.shift_volunteer_id = e_volunteer.employee_id;

SELECT request_id, employee.employee_id, employee.employee_name, date, request.message,request.workplace_id is_emergency, is_pending, is_covered, is_approved from request
JOIN employee ON employee.employee_id = request.employee_id

SELECT
    s.shift_id AS shiftId,
    s.is_covered AS isCovered,
    s.shift_owner_id AS shiftOwnerId,
    e_owner.employee_name AS shiftOwnerName,
    s.shift_volunteer_id AS shiftVolunteerId,
    e_volunteer.employee_name AS shiftVolunteerName,
    s.start_time AS startTime,
    s.end_time AS endTime
FROM shift s
LEFT JOIN employee e_owner ON s.shift_owner_id = e_owner.employee_id
LEFT JOIN employee e_volunteer ON s.shift_volunteer_id = e_volunteer.employee_id
WHERE
    s.shift_owner_id = 1 AND
    (
        DATE(s.start_time) = '2022-12-05' OR
        DATE(s.end_time) = '2022-12-05' OR
        '2022-12-05' BETWEEN DATE(s.start_time) AND DATE(s.end_time)
    );

UPDATE request
SET employee_id = 1, date = '2023-12-01', message = 'My message', manager_message = 'yes',
workplace_id = 1,is_emergency = false, is_pending = true, is_covered = false,is_approved = false
WHERE request_id = 1;

SELECT shift_id, shift.is_covered, shift_owner_id,shift_volunteer_id,shift.start_time,shift.end_time
FROM shift
JOIN employee AS owner ON shift.shift_owner_id = owner.employee_id
JOIN employee AS volunteer ON shift.shift_volunteer_id = volunteer.employee_id
JOIN request ON owner.employee_id = request.employee_id
WHERE request.request_id = 1;












SELECT * FROM employee


SELECT * FROM shift


select * from request

SELECT shift_id, is_covered, shift_owner_id,shift_volunteer_id,start_time,end_time FROM shift

SELECT request_id,employee_id, date, request.message, manager_message,
is_emergency, is_pending, is_covered,is_approved, workplace_id FROM
request

WHERE request_id = 1