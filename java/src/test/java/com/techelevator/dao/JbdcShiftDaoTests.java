package com.techelevator.dao;

import com.techelevator.model.Employee;
import com.techelevator.model.Request;
import com.techelevator.model.Shift;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class JbdcShiftDaoTests extends BaseDaoTests {

    private JdbcShiftDao dao;

    private final Shift SHIFT_1 = new Shift(
            1,
            false,
            1,
            "Steve C.",
            null,
            null,
            LocalDateTime.parse("2022-12-05T08:00:00"),
            LocalDateTime.parse("2022-12-05T16:00:00")
    );

    private final Shift SHIFT_2 = new Shift(
            2,
            true,
            1,
            "Steve C.",
            2,
            "Rachelle R.",
            LocalDateTime.parse("2023-12-16T08:00:00"),
            LocalDateTime.parse("2023-12-16T16:00:00")
    );

    private final Shift SHIFT_3 = new Shift(
            3,
            false,
            2,
            "Rachelle R.",
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
        for (int i = 0; i < testList.size(); i++) {
            assertShiftsMatch(testList.get(i),realList.get(i));
        }
    }

    @Test
    public void get_shift_by_shift_id_returns_correct_shift(){
        Shift testShift = SHIFT_2;
        Shift realShift = dao.getShiftByShiftId(2);
        assertShiftsMatch(testShift,realShift);
    }


    @Test
    public void update_shift_updates_correct_shift(){
        Shift testShift = SHIFT_1;
        testShift.setCovered(true);
        Shift realShift = dao.updateShifts(testShift);
        Assert.assertNotNull(realShift);
        assertShiftsMatch(testShift,realShift);
    }


    @Test
    public void get_shift_by_employee_id_returns_correct_shift(){
        List<Shift> testList = new ArrayList<>();
        testList.add(SHIFT_2);
        testList.add(SHIFT_3);
        List<Shift> realList = dao.getAllShiftsByEmployeeId(2);
        assertShiftsMatch(testList.get(0),realList.get(0));
        assertShiftsMatch(testList.get(1),realList.get(1));
    }

    @Test
    public void get_all_uncovered_shifts(){
        Shift testShift = SHIFT_2;
        List<Shift> realList = new ArrayList<>();
        realList = dao.getAllUncoveredShifts();
        assertShiftsMatch(realList.get(0),SHIFT_1);
        assertShiftsMatch(realList.get(1),SHIFT_3);
    }


    @Test
    public void get_all_current_shifts_returns_current_shifts(){
        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        LocalDateTime pastDateStartTime = now.minusDays(10); // Today minus 10 days
        LocalDateTime pastDateEndTime = now.minusDays(10).plusHours(8); // Assuming an 8-hour difference for the end time
        LocalDateTime futureDateStartTime = now.plusDays(365); // Today plus 365 days
        LocalDateTime futureDateEndTime = now.plusDays(365).plusHours(8); // Assuming an 8-hour difference for the end time
        Shift oldTest = SHIFT_1;
        oldTest.setStartTime(pastDateStartTime);
        oldTest.setEndTime(pastDateEndTime);
        Shift futureTest2 = SHIFT_2;
        futureTest2.setStartTime(futureDateStartTime);
        futureTest2.setEndTime(futureDateEndTime);

        Shift futureTest3 = SHIFT_3;
        futureTest3.setStartTime(futureDateStartTime);
        futureTest3.setEndTime(futureDateEndTime);

        Shift oldRealShift = dao.getShiftByShiftId(1);
        oldRealShift.setStartTime(pastDateStartTime);
        oldRealShift.setEndTime(pastDateEndTime);
        Shift futureRealShift2 = dao.getShiftByShiftId(2);
        futureRealShift2.setStartTime(futureDateStartTime);
        futureRealShift2.setEndTime(futureDateEndTime);

        Shift futureRealShift3 = dao.getShiftByShiftId(3);
        futureRealShift3.setStartTime(futureDateStartTime);
        futureRealShift3.setEndTime(futureDateEndTime);

        dao.updateShifts(oldRealShift);
        dao.updateShifts(futureRealShift2);
        dao.updateShifts(futureRealShift3);

        List<Shift> realUpdatedShiftList = dao.getAllCurrentShifts();
        assertShiftsMatch(futureTest2,realUpdatedShiftList.get(0));
        assertShiftsMatch(futureTest3,realUpdatedShiftList.get(1));

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
