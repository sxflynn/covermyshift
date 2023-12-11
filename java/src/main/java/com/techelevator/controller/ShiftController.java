package com.techelevator.controller;

import com.techelevator.dao.RequestDao;
import com.techelevator.dao.ShiftDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.*;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@PreAuthorize("isAuthenticated()")
@RestController
@CrossOrigin
public class ShiftController {
    private ShiftDao shiftDao;
    private RequestDao requestDao;
    private UserDao userDao;

    public ShiftController(ShiftDao shiftDao, RequestDao requestDao, UserDao userDao){
        this.shiftDao = shiftDao;
        this.requestDao = requestDao;
        this.userDao = userDao;
    }

    private final String API_BASE_SHIFT_URL = "/shift";
    @RequestMapping(path = API_BASE_SHIFT_URL + "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Shift>> getAllShifts(Principal principal){
        if (principalHasRole(principal, "ROLE_USER")){
            List<Shift> shiftList = shiftDao.getAllCurrentUncoveredShifts();
            if (shiftList.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(shiftList);
        }

        if (principalHasRole(principal, "ROLE_ADMIN")){
            List<Shift> shiftList = shiftDao.getAllShifts();
            if (shiftList.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(shiftList);
        }
        //TODO what is the best way to return if either role is not present?
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(path = API_BASE_SHIFT_URL + "/current", method = RequestMethod.GET)
    public ResponseEntity<List<Shift>> getAllCurrentShifts(){
        List<Shift> allCurrentShift = shiftDao.getAllCurrentShifts();
        if (allCurrentShift.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(allCurrentShift);
    }

    @RequestMapping(path = API_BASE_SHIFT_URL, method = RequestMethod.PUT)
    public Shift updateShifts(@RequestBody Shift shift){
        return shiftDao.updateShifts(shift);
    }

    @RequestMapping(path = API_BASE_SHIFT_URL + "/employee/{employeeId}", method = RequestMethod.GET)
    public ResponseEntity<List<Shift>> getAllShiftsByEmployeeId(@PathVariable("employeeId") int employeeId) {
        List<Shift> shiftList = shiftDao.getAllShiftsByEmployeeId(employeeId);
        if (shiftList.isEmpty()) {
            return ResponseEntity.noContent().build(); // returns 204 no content
        }
        return ResponseEntity.ok(shiftList);
    }


    @RequestMapping(path = API_BASE_SHIFT_URL + "/shifts/{shiftId}", method = RequestMethod.GET)
    public ResponseEntity<Shift> getShiftByShiftId(@PathVariable("shiftId") int shiftId) {
        Shift shift = shiftDao.getShiftByShiftId(shiftId);
        if (shift == null) {
            // Shift not found, return 404 Not Found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        // Shift found, return 200 OK with the shift data
        return ResponseEntity.ok(shift);
    }

    @RequestMapping(path = API_BASE_SHIFT_URL + "/uncovered", method = RequestMethod.GET)
    public ResponseEntity<List<Shift>> getAllUncoveredShifts() {
        List<Shift> shiftList = shiftDao.getAllUncoveredShifts();
        return ResponseEntity.ok(shiftList);
    }

    @RequestMapping(path = API_BASE_SHIFT_URL + "/uncovered/now", method = RequestMethod.GET)
    public ResponseEntity<List<Shift>> getAllCurrentUncoveredShifts() {
        List<Shift> shiftList = shiftDao.getAllCurrentUncoveredShifts();
        return ResponseEntity.ok(shiftList);
    }

    @RequestMapping(path = API_BASE_SHIFT_URL + "/request/{requestId}", method = RequestMethod.GET)
    public ResponseEntity<List<Shift>> getAllShiftsByRequestId(@PathVariable("requestId") int requestId) {
        //TODO HIGH PRIORITY get the Request from the request ID and then feed that into the shiftDao function below


//        List<Shift> shiftList = shiftDao.getAllShiftsByRequest(request);
        return ResponseEntity.ok(null);
    }


    public boolean principalHasRole(Principal principal, String role) {
        Set<Authority> authorities = userDao.getUserFromPrincipal(principal).getAuthorities();
        for (Authority authority : authorities) {
            if (role.equals(authority.getName())) {
                return true;
            }
        }
        return false;
    }



}
