package com.techelevator.dao;

import com.techelevator.model.Request;
import com.techelevator.model.Shift;

import java.time.LocalDate;
import java.util.List;

public interface ShiftDao {

    public List<Shift> getAllCurrentShifts();

    public List<Shift> getAllCurrentShiftsByEmployeeId(int employeeId);

    public Shift updateShifts(Shift shift);

    public List<Shift> getAllShiftsByEmployeeId(int employeeId);

    public List<Shift> getAllCurrentUncoveredShifts();

    public List<Shift> getAllCurrentUncoveredShiftsNotEmployeeId(int employeeId);

    public List<Shift> getAllShiftsByRequestId(int requestId);
    public List<Shift> getAllShiftsByRequest(Request request);

    public Shift getShiftByShiftId(int shiftId);

    public List<Shift> getAllShifts();

public List<Shift> getAllShiftsByEmployeeIdAndDate(int employeeId, LocalDate date);

public int updateListOfShiftsToUncoveredByRequest(Request request);



}
