package com.techelevator.dao;

import com.techelevator.model.Employee;
import com.techelevator.model.Request;
import com.techelevator.model.Shift;

import java.util.List;

public interface ShiftDao {

    public List<Shift> getAllCurrentShifts();

    public Shift updateShifts(Shift shift);

    public List<Shift> getAllShiftsByEmployee(Employee employee);

    public List<Shift> getAllUncoveredShifts();

    public Shift getShiftByShiftId(int shiftId);





    public List<Shift> getAllShifts();



}
