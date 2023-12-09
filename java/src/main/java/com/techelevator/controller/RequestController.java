package com.techelevator.controller;

import com.techelevator.dao.EmployeeDao;
import com.techelevator.dao.RequestDao;
import com.techelevator.dao.ShiftDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Authority;
import com.techelevator.model.Request;
import com.techelevator.model.Shift;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
@PreAuthorize("isAuthenticated()")
@CrossOrigin
public class RequestController {
    private RequestDao requestDao;
    private ShiftDao shiftDao;
    private UserDao userDao;

    private EmployeeDao employeeDao;

    public RequestController(RequestDao requestDao, ShiftDao shiftDao, UserDao userDao) {
        this.requestDao = requestDao;
        this.shiftDao = shiftDao;
        this.userDao = userDao;
    }

    private final String API_BASE_REQUEST_URL = "/request";


    @RequestMapping(path = API_BASE_REQUEST_URL, method = RequestMethod.GET)
    //TODO change this to ResponseEntity, look at shift controller for examples
    public ResponseEntity<List<Request>> getAllRequests(Principal principal){
        List<Request> requestList = requestDao.getAllRequests();
        if (requestList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(requestList);
//        if(hasRoleAdmin(userDao.getUserFromPrincipal(principal).getAuthorities())){
////            return ResponseEntity.ok(request);
//        } else if (hasRoleUser(userDao.getUserFromPrincipal(principal).getAuthorities())) {
//
//        }
//        return requestDao.getAllRequests();
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


//    was having issues with userDao.getUserFromPrincipal(principle). It threw a null pointer.
//    so we broke the functionality into two endpoints. One for a teacher making a claim and one for an admin approving

    @RequestMapping(path = API_BASE_REQUEST_URL, method = RequestMethod.PUT)
    public Request updateRequest(@RequestBody Request request, Principal principal){

        //if admin approved request
         if(hasRoleAdmin(userDao.getUserFromPrincipal(principal).getAuthorities()) && request.isApproved()==true) {
             shiftDao.updateListOfShiftsToUncoveredByRequest(request);
             return requestDao.updateRequest(request);
         }
         // if admin denying request
        if(hasRoleAdmin(userDao.getUserFromPrincipal(principal).getAuthorities()) && request.isApproved()==false) {
             return requestDao.updateRequest(request);
         }

        Request requestResponse = new Request();
        //if a teacher is making a request
        if(hasRoleUser(userDao.getUserFromPrincipal(principal).getAuthorities())) {
            //TODO -- add a method to check that the request is the same as the principal's employee ID else throw error

            requestResponse = requestDao.getRequestByRequestId(request.getRequestId());
            // update each fields of the request manually using the incoming request object but exclude approved, pending, managerMessage
            requestResponse.setDate(request.getDate());
            requestResponse.setEmployeeMessage(request.getEmployeeMessage());
            requestResponse.setEmergency(request.isEmergency());
        }
        return requestDao.updateRequest(requestResponse);
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
