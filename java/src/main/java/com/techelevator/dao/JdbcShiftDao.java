package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Shift;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcShiftDao implements ShiftDao {
    private final JdbcTemplate jdbcTemplate;

    private final String ALL_COLUMN_WITH_THE_SHIFT = "SELECT shift_id, is_covered, shift_owner_id,\n" +
            "shift_volunteer_id, start_time,end_time\n" + "FROM shift\n";

    public JdbcShiftDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Shift> getAllCurrentShifts() {
        List<Shift> shiftsList = new ArrayList<>();
        String sql = ALL_COLUMN_WITH_THE_SHIFT + "WHERE start_time >= CURRENT_TIMESTAMP\n" +
                "AND start_time < CURRENT_TIMESTAMP + INTERVAL '1 day';";
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

    @Override
    public Shift updateShifts(Shift shift) {
        Shift updatedshift = null;
        String sql = ALL_COLUMN_WITH_THE_SHIFT + " WHERE shift_id = ?;";
        try {
            int number0fShifts = jdbcTemplate.update(sql, shift.getShiftId(), shift.getShiftOwnerId(), shift.getShiftOwnerName(),
                    shift.getShiftVolunteerId(), shift.getShiftVolunteerName(), shift.isCovered(), shift.getStartTime(), shift.getEndTime());
            if (number0fShifts == 0) {
             throw new DaoException("Zero rows affected, expected at least one");
            }else {
                updatedshift = getShiftByShiftId(shift.getShiftId());
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return updatedshift;
    }

    @Override
    public List<Shift> getAllShiftsByEmployeeId(int employeeId) {
        List<Shift> getAllShiftsOfAllEmployees = new ArrayList<>();
        String sql = "SELECT shift.shift_id, shift.is_covered, shift.start_time, shift.end_time, " +
                "owner.employee_id AS owner_employee_id, owner.employee_name AS owner_employee_name, " +
                "volunteer.employee_id AS volunteer_employee_id, volunteer.employee_name AS volunteer_employee_name " +
                "FROM shift " +
                "LEFT JOIN employee AS owner ON shift.shift_owner_id = owner.employee_id " +
                "LEFT JOIN employee AS volunteer ON shift.shift_volunteer_id = volunteer.employee_id " +
                "WHERE owner.employee_id = ? OR volunteer.employee_id = ?";
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
        return null;
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
        shift.setShiftId(rowSet.getInt("shift_id"));
        shift.setCovered(rowSet.getBoolean("is_covered"));
        shift.setShiftOwnerId(rowSet.getInt("shift_owner_id"));
        shift.setShiftOwnerName(rowSet.getString("shift_owner_name"));
        shift.setShiftVolunteerId(rowSet.getInt("shift_volunteer_id"));
        shift.setShiftVolunteerName(rowSet.getString("shift_volunteer_name"));
        shift.setStartTime(rowSet.getTimestamp("start_time").toLocalDateTime());
        shift.setEndTime(rowSet.getTimestamp("end_time").toLocalDateTime());

        return shift;

    }
}
