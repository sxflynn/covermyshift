package com.techelevator.model;

public class Employee {

    int employeeId;
    String employeeName;
    String username;
    String email;

    //TODO add companyName string

    public Employee(int employeeId, String employeeName, String username, String email) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.username = username;
        this.email = email;

    }

    public Employee(){

    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
