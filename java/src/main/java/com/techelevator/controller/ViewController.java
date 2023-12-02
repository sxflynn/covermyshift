package com.techelevator.controller;

import com.techelevator.model.Shift;
import com.techelevator.model.User;
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
        Shift uncoveredShift = new Shift();
        uncoveredShift.setCovered(false);
        uncoveredShift.setShiftDate(LocalDate.parse("2023-12-01"));
        uncoveredShift.setShiftOwnerId(1);
        uncoveredShift.setCompanyId(1);
        return uncoveredShift;
    }


}
