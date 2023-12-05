package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Employee;
import com.techelevator.model.Shift;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcShiftDao implements ShiftDao {
    private final JdbcTemplate jdbcTemplate;

    private final String ALL_COLUMN_WITH_THE_SHIFT = "SELECT shift_id, is_covered, shift_owner_id,\n" +
            "shift_volunteer_id, start_time,end_time\n" +
            "FROM shift\n";


    public JdbcShiftDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Shift> getAllCurrentShifts() {
        return null;
    }

    @Override
    public Shift updateShifts(Shift shift) {
        return null;
    }

    @Override
    public List<Shift> getAllShiftsByEmployee(Employee employee) {
        return null;
    }

    @Override
    public List<Shift> getAllUncoveredShifts() {
        return null;
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
