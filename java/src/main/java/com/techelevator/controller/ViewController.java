package com.techelevator.controller;

import com.techelevator.dao.RequestDao;
import com.techelevator.model.Request;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
public class ViewController {
    private RequestDao requestDao;

    public ViewController(RequestDao requestDao) {
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


//    @RequestMapping(path = API_BASE_REQUEST_URL, method = RequestMethod.PUT)
//    public Shift updateShifttoUnCovered(@RequestBody CoverageRequest request){
//        Shift newShift = new Shift(2,false,request.getEmployeeId(),request.getEmployeeName(),null,request.getDate(),1);
//        return newShift;
//    }

}
