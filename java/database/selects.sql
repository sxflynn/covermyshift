SELECT
s.shift_id AS shiftId, s.is_covered AS isCovered, s.shift_owner_id AS shiftOwnerId, e_owner.employee_name AS shiftOwnerName, s.shift_volunteer_id AS shiftVolunteerId, e_volunteer.employee_name AS shiftVolunteerName, s.start_time AS startTime, s.end_time AS endTime
FROM shift s
LEFT JOIN employee e_owner ON s.shift_owner_id = e_owner.employee_id
LEFT JOIN employee e_volunteer ON s.shift_volunteer_id = e_volunteer.employee_id;



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
