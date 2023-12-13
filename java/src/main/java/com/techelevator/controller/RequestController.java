package com.techelevator.controller;

import com.techelevator.dao.EmployeeDao;
import com.techelevator.dao.RequestDao;
import com.techelevator.dao.ShiftDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.*;
import org.springframework.http.HttpStatus;
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


    public RequestController(RequestDao requestDao, ShiftDao shiftDao, UserDao userDao, EmployeeDao employeeDao) {
        this.requestDao = requestDao;
        this.shiftDao = shiftDao;
        this.userDao = userDao;
        this.employeeDao = employeeDao;
    }

    private final String API_BASE_REQUEST_URL = "/request";


    @RequestMapping(path = API_BASE_REQUEST_URL, method = RequestMethod.GET)
    public ResponseEntity<List<Request>> getAllRequests(Principal principal){

        if (principalHasRole(principal, "ROLE_USER")){
            User employeeUser = userDao.getUserFromPrincipal(principal);
            String username = employeeUser.getUsername();
           Employee employee = employeeDao.getEmployeeFromUsername(username);

            List<Request> requestList = requestDao.getRequestsByEmployeeId(employee.getEmployeeId());
            if (requestList.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(requestList);
        }

        else if (principalHasRole(principal, "ROLE_ADMIN")){
            List<Request> requestList = requestDao.getAllRequests();
            if (requestList.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(requestList);
        }
        //TODO what is the best way to return if either role is not present?
        return ResponseEntity.noContent().build();
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


    @RequestMapping(path = API_BASE_REQUEST_URL + "/delete/{requestId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteRequestById(@PathVariable("requestId") int requestId, Principal principal) {
        int numberOfRows = 0;

        if (principalHasRole(principal, "ROLE_USER") && !requestBelongsToPrincipal(principal, requestId)) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("You do not have authority to delete the request");
        }

        if (principalHasRole(principal, "ROLE_ADMIN") || requestBelongsToPrincipal(principal, requestId)) {
            numberOfRows = requestDao.deleteRequestById(requestId);
            if (numberOfRows == 1) {
                return ResponseEntity.ok(numberOfRows);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Request not found");
        }
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("You do not have authority to delete the request");
    }

    @RequestMapping(path = API_BASE_REQUEST_URL, method = RequestMethod.PUT)
    public Request updateRequest(@RequestBody Request request, Principal principal){

        //if admin approved request
         if(principalHasRole(principal,"ROLE_ADMIN") && request.isApproved()==true) {
             shiftDao.updateListOfShiftsToUncoveredByRequest(request);
             return requestDao.updateRequest(request);
         }
         // if admin denying request
        if(principalHasRole(principal,"ROLE_ADMIN") && request.isApproved()==false) {
             return requestDao.updateRequest(request);
         }

        Request requestResponse = new Request();
        //if a teacher is making a request
        if(principalHasRole(principal,"ROLE_USER")) {
            //TODO -- add a method to check that the request is the same as the principal's employee ID else throw error

            requestResponse = requestDao.getRequestByRequestId(request.getRequestId());
            requestResponse.setDate(request.getDate());
            requestResponse.setEmployeeMessage(request.getEmployeeMessage());
            requestResponse.setEmergency(request.isEmergency());
        }
        return requestDao.updateRequest(requestResponse);
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

    public boolean requestBelongsToPrincipal(Principal principal, int requestId) {
        Request request = requestDao.getRequestByRequestId(requestId);
        if (request == null) {
            return false;
        }

        Employee employee = employeeDao.getEmployeeFromUsername(principal.getName());
        if (employee == null) {
            return false;
        }

        int principalEmployeeId = employee.getEmployeeId();
        int requestEmployeeId = request.getEmployeeId();

        return principalEmployeeId == requestEmployeeId;
    }


}
