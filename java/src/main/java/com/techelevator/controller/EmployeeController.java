package com.techelevator.controller;

import com.techelevator.dao.EmployeeDao;
import com.techelevator.dao.RequestDao;
import com.techelevator.dao.ShiftDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Employee;
import com.techelevator.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@PreAuthorize("isAuthenticated()")
@CrossOrigin
public class EmployeeController {

    private EmployeeDao employeeDao;
    private UserDao userDao;
    private final String API_BASE_EMPLOYEE_URL = "/employee";

    public EmployeeController(EmployeeDao employeeDao, UserDao userDao){
        this.employeeDao = employeeDao;
        this.userDao = userDao;
    }

    @RequestMapping(path = API_BASE_EMPLOYEE_URL, method = RequestMethod.GET)
    public Employee getEmployeeFromPrincipal(Principal principal){
        return employeeDao.getEmployeeFromUsername(userDao.getUserFromPrincipal(principal).getUsername());
    }
}
