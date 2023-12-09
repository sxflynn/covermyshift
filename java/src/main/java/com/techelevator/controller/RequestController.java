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

//TODO HIGH PRIORITY Add authorization/authentication to this controller. All endpoints should require authentication.
// You know its correct if the bearer token is required to call a GET in postman

@RestController
@PreAuthorize("isAuthenticated()")
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


//    was having issues with userDao.getUserFromPrincipal(principle). It threw a null pointer.
//    so we broke the functionality into two endpoints. One for a teacher making a claim and one for an admin approving
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(path = API_BASE_REQUEST_URL, method = RequestMethod.PUT)
    public Request claimShift(@RequestBody Request request) {
//        this is when a user claims a shift


        return request;
    }



    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = API_BASE_REQUEST_URL, method = RequestMethod.PUT)
    public Request updateRequest(@RequestBody Request request){

        System.out.println(request);
//

//        admin approving request

//         if(hasRoleAdmin(userDao.getUserFromPrincipal(principal).getAuthorities()) && request.isApproved()==true)
        if(request.isApproved() == true){
             shiftDao.updateListOfShiftsToUncoveredByRequest(request);
             return requestDao.updateRequest(request);

//             admin denying request
         } else if (request.isApproved()==false) {
             return requestDao.updateRequest(request);
         }
//        System.out.println("You are not an admin.. so sorry");

//        if (request.isApproved()) {
////            String currentUsername = userDao.getUserFromPrincipal(principal).getUsername();
//            List<Shift> affectedShifts = shiftDao.getAllShiftsByEmployeeIdAndDate(request.getEmployeeId(), request.getDate());
//            for (Shift shift : affectedShifts) {
//                shift.setCovered(false);
//                shiftDao.updateShifts(shift);
//            }
//            return requestDao.updateRequest(request);
//        }


        //TODO HIGH PRIORITY implement the logic below when principal works


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
