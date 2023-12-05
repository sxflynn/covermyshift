package com.techelevator.dao;

import com.techelevator.model.Request;
import com.techelevator.model.Shift;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JbdcShiftDaoTests extends BaseDaoTests {

    private JdbcShiftDao dao;

    private final Shift SHIFT_1 = new Shift(
            1,
            false,
            1,
            "Steve C",
            null,
            null,
            LocalDateTime.parse("2022-12-05T08:00:00"),
            LocalDateTime.parse("2022-12-05T16:00:00")
    );

    private final Shift SHIFT_2 = new Shift(
            2,
            true,
            1,
            null,
            null,
            null,
            LocalDateTime.parse("2023-12-06T08:00:00"),
            LocalDateTime.parse("2023-12-06T16:00:00")
    );

    private final Shift SHIFT_3 = new Shift(
            3,
            false,
            1,
            null,
            null,
            null,
            LocalDateTime.parse("2024-12-07T08:00:00"),
            LocalDateTime.parse("2024-12-07T16:00:00")
    );

    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        dao = new JdbcShiftDao(jdbcTemplate);
    }

    @Test
    public void assert_shifts_match_works(){
        assertShiftsMatch(SHIFT_1,SHIFT_1);
    }

    @Test
    public void get_all_shifts_returns_shifts(){
        List<Shift> testList = new ArrayList<>();
        testList.add(SHIFT_1);
        testList.add(SHIFT_2);
        testList.add(SHIFT_3);
        List <Shift> realList = dao.getAllShifts();
    }

    public void assertShiftsMatch(Shift expectedShift, Shift realShift){
        Assert.assertEquals("Expected shift ID: " + expectedShift.getShiftId() + ", but was: " + realShift.getShiftId(), expectedShift.getShiftId(), realShift.getShiftId());
        Assert.assertEquals("Expected covered status: " + expectedShift.isCovered() + ", but was: " + realShift.isCovered(), expectedShift.isCovered(), realShift.isCovered());
        Assert.assertEquals("Expected shift owner ID: " + expectedShift.getShiftOwnerId() + ", but was: " + realShift.getShiftOwnerId(), expectedShift.getShiftOwnerId(), realShift.getShiftOwnerId());
        Assert.assertEquals("Expected shift owner name: " + expectedShift.getShiftOwnerName() + ", but was: " + realShift.getShiftOwnerName(), expectedShift.getShiftOwnerName(), realShift.getShiftOwnerName());
        Assert.assertEquals("Expected shift volunteer ID: " + expectedShift.getShiftVolunteerId() + ", but was: " + realShift.getShiftVolunteerId(), expectedShift.getShiftVolunteerId(), realShift.getShiftVolunteerId());
        Assert.assertEquals("Expected shift volunteer name: " + expectedShift.getShiftVolunteerName() + ", but was: " + realShift.getShiftVolunteerName(), expectedShift.getShiftVolunteerName(), realShift.getShiftVolunteerName());
        Assert.assertEquals("Expected start time: " + expectedShift.getStartTime() + ", but was: " + realShift.getStartTime(), expectedShift.getStartTime(), realShift.getStartTime());
        Assert.assertEquals("Expected end time: " + expectedShift.getEndTime() + ", but was: " + realShift.getEndTime(), expectedShift.getEndTime(), realShift.getEndTime());
    }




}
