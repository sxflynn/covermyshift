package com.techelevator.controller;

import com.techelevator.dao.RequestDao;
import com.techelevator.model.Request;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class RequestController {
    private RequestDao requestDao;

    public RequestController(RequestDao requestDao) {
        this.requestDao = requestDao;
    }

    private final String API_BASE_REQUEST_URL = "/request";

    @RequestMapping(path = API_BASE_REQUEST_URL, method = RequestMethod.GET)
    public List<Request> getAllRequests(){
        return requestDao.getAllRequests();
    }

    @RequestMapping(path = API_BASE_REQUEST_URL, method=RequestMethod.POST)
    public Request addRequest(@RequestBody Request request){
        return requestDao.createRequest(request);
    }

    @RequestMapping(path = API_BASE_REQUEST_URL + "/current", method = RequestMethod.GET)
    public List<Request> getPresentAndFutureRequests(){
        //Returns all requests from 1 day ago plus into the future
        return requestDao.getCurrentAndFutureRequests();
    }

    @RequestMapping(path = API_BASE_REQUEST_URL, method = RequestMethod.PUT)
    public Request updateRequest(@RequestBody Request request){

        //add logic if manager role is updating the approval field then do other daos like updating shifts to uncovered
        // if userrole = manager AND request.isapproved = true,
            // shiftDao.updateShiftsByRequestId

        return requestDao.updateRequest(request);
    }


//    @RequestMapping(path = API_BASE_REQUEST_URL, method = RequestMethod.PUT)
//    public Shift updateShifttoUnCovered(@RequestBody CoverageRequest request){
//        Shift newShift = new Shift(2,false,request.getEmployeeId(),request.getEmployeeName(),null,request.getDate(),1);
//        return newShift;
//    }

}
