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

//TODO Add authorization/authentication to this controller. All endpoints should require authentication.
//@PreAuthorize("isAuthenticated()")
@RestController
@CrossOrigin
public class RequestController {
    private RequestDao requestDao;
    private ShiftDao shiftDao;
    private UserDao userDao;

    private EmployeeDao employeeDao;

    public RequestController(RequestDao requestDao, ShiftDao shiftDao) {
        this.requestDao = requestDao;
        this.shiftDao = shiftDao;
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

    @RequestMapping(path = API_BASE_REQUEST_URL, method = RequestMethod.PUT)
    public Request updateRequest(@RequestBody Request request){
        //teacher claiming shift
         if (request.isPending() == false && request.isApproved() == true && request.isCovered() == false){

//            String currentUsername = userDao.getUserFromPrincipal(principal).getUsername();
            List<Shift> affectedShifts = shiftDao.getAllShiftsByEmployeeIdAndDate(request.getEmployeeId(),request.getDate());
            for (Shift shift : affectedShifts){
                shift.setCovered(true);
                shift.setShiftVolunteerName("Steve C.");
                shift.setShiftVolunteerId(1);
                shiftDao.updateShifts(shift);
            }
            request.setCovered(true);
            return requestDao.updateRequest(request);

        }

        //TODO implement the logic below

        //admin approving request

         else if(request.isApproved() == true){
             shiftDao.updateListOfShiftsToUncoveredByRequest(request);
             request.setPending(false);
             return requestDao.updateRequest(request);
         } else if (request.isApproved() == false) {
             request.setPending((false));
             return requestDao.updateRequest(request);

         }

            //this handles when an employee wants to give up a shift
//            shiftDao.updateListOfShiftsToUncoveredByRequest(request);

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
