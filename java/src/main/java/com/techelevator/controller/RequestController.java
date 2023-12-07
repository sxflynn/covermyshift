package com.techelevator.controller;

import com.techelevator.dao.RequestDao;
import com.techelevator.dao.ShiftDao;
import com.techelevator.model.Request;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class RequestController {
    private RequestDao requestDao;
    private ShiftDao shiftDao;

    public RequestController(RequestDao requestDao, ShiftDao shiftDao) {
        this.requestDao = requestDao;
        this.shiftDao = shiftDao;
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
        // if manager AND request.isapproved = true,
            // shiftDao.updateListOfShiftsToUncoveredByRequestId --> changes all those shifts to uncovered
            //Request requestResponse = getRequestById(requestId)
            //requestResponse.setPending = false;
            // return dao.updateRequest(requestResponse)

        //if its an admin and request.isApproved = false
            // update all fields
            // update pending to false
            // requestDao.updateRequest(request);
        // if its a user updating the request
            // update all fields but exclude approved, pending, manageMessage
            // requestDao.updateRequest(request);

        return requestDao.updateRequest(request);
    }

}
