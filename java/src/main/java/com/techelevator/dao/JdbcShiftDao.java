package com.techelevator.dao;

import com.techelevator.model.Employee;
import com.techelevator.model.Shift;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;

public class JdbcShiftDao implements ShiftDao {
    private final JdbcTemplate jdbcTemplate;


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
        return null;
    }


    public Shift mapRowToShift(SqlRowSet rowSet) {
        Shift shift = new Shift();
        shift.setShiftId(rowSet.getInt("shift_id"));
        shift.setCovered(rowSet.getBoolean("is_covered"));
        shift.setShiftOwnerId(rowSet.getInt("shift_owner_id"));
        shift.setShiftOwnerName(rowSet.getString("shift_owner_name"));
        shift.setShiftVolunteerId(rowSet.getInt("shift_volunteer_id"));
        shift.setShiftVolunteerName(rowSet.getString("shift_volunteer_name"));
        shift.setStartTime(rowSet.getDate("start_time").toLocalDate());
        shift.setEndTime(rowSet.getDate("end_time").toLocalDate());

        return shift;

    }
}
