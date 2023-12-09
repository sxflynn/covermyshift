package com.techelevator.dao;

import com.techelevator.model.Employee;
import com.techelevator.model.User;

public interface EmployeeDao {
    /**
     * Retrieves the employee ID associated with the given User object.
     * This method is intended to extract the employee identifier from the provided User object,
     * which is typically used for linking user accounts with their corresponding employee records.
     *
     * @param user The User object from which the employee ID is to be extracted.
     *             It should not be null and should contain valid employee information.
     * @return The employee ID as an integer. If the User object does not have an associated employee ID,
     *         this method may return a default value (like 0) or throw an exception, depending on the implementation.
     *         It's important to handle this method's return value accordingly in the calling context.
     */
    public int getEmployeeIdFromUser(User user);

    public Employee getEmployeeFromUser(User user);

    public Employee getEmployeeFromUsername(String username);
}
