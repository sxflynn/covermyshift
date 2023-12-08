package com.techelevator.controller;

import com.techelevator.dao.RequestDao;
import com.techelevator.dao.ShiftDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Authority;
import com.techelevator.model.Request;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;

//TODO Add authorization/authentication to this controller. All endpoints should require authentication.
@RestController
@CrossOrigin
public class RequestController {
    private RequestDao requestDao;
    private ShiftDao shiftDao;
    private UserDao userDao;

    public RequestController(RequestDao requestDao, ShiftDao shiftDao) {
        this.requestDao = requestDao;
        this.shiftDao = shiftDao;
    }

    private final String API_BASE_REQUEST_URL = "/request";

    @RequestMapping(path = API_BASE_REQUEST_URL, method = RequestMethod.GET)
    //TODO change this to ResponseEntity, look at shift controller for examples
    public List<Request> getAllRequests(){
        return requestDao.getAllRequests();
    }

    @RequestMapping(path = API_BASE_REQUEST_URL, method=RequestMethod.POST)
    public Request addRequest(@RequestBody Request request){
        return requestDao.createRequest(request);
    }

    @RequestMapping(path = API_BASE_REQUEST_URL + "/current", method = RequestMethod.GET)
    //TODO change this to ResponseEntity
    public List<Request> getPresentAndFutureRequests(){
        return requestDao.getCurrentAndFutureRequests();
    }

    @RequestMapping(path = API_BASE_REQUEST_URL, method = RequestMethod.PUT)
    public Request updateRequest(@RequestBody Request request, Principal principal){

        //TODO implement the logic below

        // boolean isAdmin = (use userDao.getUserFromPrincipal and hasRoleAdmin to set this boolean)

        // if isAdmin AND request.isapproved = true
            // int shiftDao.updateListOfShiftsToUncoveredByRequest(request) --> changes all those shifts to uncovered
            // request.setPending = false;
            // return dao.updateRequest(requestResponse)

        //if isAdmin AND request.isApproved = false
            // set request.ispending to false
            // return requestDao.updateRequest(request);

        // if NOT isAdmin
        // instead of passing this request straight to the dao, we need to ensure only user fields are touched
            // retrieve the request from the dao using the requestId and set to new variable like requestResponse
            // update each fields of the request manually using the incoming request object but exclude approved, pending, managerMessage
            // return requestDao.updateRequest(requestResponse);

        return requestDao.updateRequest(request);
    }


    public boolean hasRoleUser(Set<Authority> authorities) {
        for (Authority authority : authorities) {
            if ("ROLE_USER".equals(authority.getName())) {
                return true;
            }
        }
        return false;
    }

    public boolean hasRoleAdmin(Set<Authority> authorities) {
        for (Authority authority : authorities) {
            if ("ROLE_ADMIN".equals(authority.getName())) {
                return true;
            }
        }
        return false;
    }
}
