package com.techelevator.controller;

import com.techelevator.model.CoverageRequest;
import com.techelevator.model.Shift;
import com.techelevator.model.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDate;

@RestController
public class ViewController {


    public ViewController() {
    }

    private final String API_BASE_SHIFT_URL = "/shift";

    @RequestMapping(path = API_BASE_SHIFT_URL + "/uncovered", method = RequestMethod.GET)
    public Shift getAllUncoveredShifts(){
        Shift uncoveredShift = new Shift(1,false,1,"Steve",null,LocalDate.parse("2023-12-01"),1);
        return uncoveredShift;
    }

    @RequestMapping(path = API_BASE_SHIFT_URL, method = RequestMethod.PUT)
    public Shift updateShifttoUnCovered(@RequestBody CoverageRequest request){
        Shift newShift = new Shift(2,false,request.getEmployeeId(),request.getEmployeeName(),null,request.getDate(),1);
        return newShift;
    }

}
