package com.techelevator.dao;

import com.techelevator.model.Employee;
import com.techelevator.model.User;

public interface EmployeeDao {

//    public int getEmployeeIdFromUser(User user);

    public Employee getEmployeeFromUsername(String username);
}
