package com.techelevator.controller;

import com.techelevator.dao.RequestDao;
import com.techelevator.dao.ShiftDao;
import com.techelevator.model.Request;
import com.techelevator.model.Shift;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//TODO Add authorization/authentication to this controller. All endpoints should require authentication.
@RestController
@CrossOrigin
public class ShiftController {
    private ShiftDao shiftDao;
    private RequestDao requestDao;

    public ShiftController(ShiftDao shiftDao, RequestDao requestDao){
        this.shiftDao = shiftDao;
        this.requestDao = requestDao;
    }

    private final String API_BASE_SHIFT_URL = "/shift";
    @RequestMapping(path = API_BASE_SHIFT_URL + "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Shift>> getAllShifts(){
        List<Shift> allShiftList = shiftDao.getAllShifts();
        if (allShiftList.isEmpty()){
            return ResponseEntity.noContent().build(); // returns 204 no content
        }
        return ResponseEntity.ok(allShiftList);
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

    @RequestMapping(path = API_BASE_SHIFT_URL + "/request/{requestId}", method = RequestMethod.GET)
    public ResponseEntity<List<Shift>> getAllShiftsByRequestId(@PathVariable("requestId") int requestId) {
        //TODO get the Request from the request ID and then feed that into the shiftDao function below


//        List<Shift> shiftList = shiftDao.getAllShiftsByRequest(request);
        return ResponseEntity.ok(null);
    }





}
