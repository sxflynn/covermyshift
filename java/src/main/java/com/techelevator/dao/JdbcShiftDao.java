package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Shift;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.AlgorithmConstraints;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcShiftDao implements ShiftDao {
    private final JdbcTemplate jdbcTemplate;

    private final String ALL_COLUMN_WITH_THE_SHIFT = "SELECT\n" +
            "    s.shift_id AS shiftId, s.is_covered AS isCovered, s.shift_owner_id AS shiftOwnerId, e_owner.employee_name AS shiftOwnerName, s.shift_volunteer_id AS shiftVolunteerId,\n" +
            "    e_volunteer.employee_name AS shiftVolunteerName, s.start_time AS startTime, s.end_time AS endTime\n" +
            "FROM shift s\n" +
            "LEFT JOIN employee e_owner ON s.shift_owner_id = e_owner.employee_id\n" +
            "LEFT JOIN employee e_volunteer ON s.shift_volunteer_id = e_volunteer.employee_id\n ";

    public JdbcShiftDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Shift> getAllCurrentShifts() {
        List<Shift> shiftsList = new ArrayList<>();
        String sql = ALL_COLUMN_WITH_THE_SHIFT +
                "WHERE start_time >= CURRENT_TIMESTAMP";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Shift shift = mapRowsToShifts(results);
                shiftsList.add(shift);
            }

            if (shiftsList.isEmpty()) {
                System.out.println("No shifts found for the specified criteria.");
            }

        } catch (CannotGetJdbcConnectionException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
            throw new DaoException("Unable to connect to server or database", e);
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
            throw new DaoException("An unexpected error occurred", e);
        }
        return shiftsList;
    }

    @Override
    public Shift updateShifts(Shift shift) {
        Shift updatedShift = null;
        String sql = "UPDATE shift \n" +
                "SET is_covered = ?, shift_volunteer_id = ?, start_time = ?, end_time = ?\n" +
                "WHERE shift_id = ?;";
        try {
            int numberOfShifts = jdbcTemplate.update(sql, shift.isCovered(), shift.getShiftVolunteerId(), shift.getStartTime(), shift.getEndTime(), shift.getShiftId());
            if (numberOfShifts == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            } else {
                updatedShift = getShiftByShiftId(shift.getShiftId());
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to the server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);

        }
        return  updatedShift;
    }

    @Override
    public List<Shift> getAllShiftsByEmployeeId(int employeeId) {
        List<Shift> getAllShiftsOfAllEmployees = new ArrayList<>();
        String sql = ALL_COLUMN_WITH_THE_SHIFT + "WHERE e_owner.employee_id = ? OR e_volunteer.employee_id = ?";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, employeeId, employeeId);
            while (result.next()) {
                Shift shiftAllEmployee = mapRowsToShifts(result);
                getAllShiftsOfAllEmployees.add(shiftAllEmployee);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return getAllShiftsOfAllEmployees;

    }

    @Override
    public List<Shift> getAllUncoveredShifts() {
        List<Shift> uncoveredShiftList;
        String sql = ALL_COLUMN_WITH_THE_SHIFT + "WHERE s.is_covered = false;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            uncoveredShiftList = new ArrayList<>();
            while (results.next()) {
                Shift shift = mapRowsToShifts(results);
                uncoveredShiftList.add(shift);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return uncoveredShiftList;
    }

    @Override
    public Shift getShiftByShiftId(int shiftId) {
        Shift shiftById = null;
        String sql = ALL_COLUMN_WITH_THE_SHIFT + "WHERE shift_id = ?;";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, shiftId);
            if ((result.next())) {
                shiftById = mapRowsToShifts(result);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return shiftById;
    }

    @Override
    public List<Shift> getAllShifts() {
        List<Shift> shiftsList = new ArrayList<>();
        String sql = ALL_COLUMN_WITH_THE_SHIFT;
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Shift shift = mapRowsToShifts(results);
                shiftsList.add(shift);

            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return shiftsList;
    }


    public Shift mapRowsToShifts(SqlRowSet rowSet) {
        Shift shift = new Shift();
        shift.setShiftId(rowSet.getInt("shiftId"));
        shift.setCovered(rowSet.getBoolean("isCovered"));
        shift.setShiftOwnerId(rowSet.getInt("shiftOwnerId"));
        shift.setShiftOwnerName(rowSet.getString("shiftOwnerName"));
        if (rowSet.getObject("shiftVolunteerId") == null) {
            shift.setShiftVolunteerId(null);
        } else {
            shift.setShiftVolunteerId(rowSet.getInt("shiftVolunteerId"));
        }
        shift.setShiftVolunteerName(rowSet.getString("shiftVolunteerName"));
        shift.setStartTime(rowSet.getTimestamp("startTime").toLocalDateTime());
        shift.setEndTime(rowSet.getTimestamp("endTime").toLocalDateTime());

        return shift;
    }


}
