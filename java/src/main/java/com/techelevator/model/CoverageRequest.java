package com.techelevator.model;

import java.time.LocalDate;

public class CoverageRequest {

    int employeeId;
    String employeeName;

    LocalDate date;

    public CoverageRequest() {
    }

    public CoverageRequest(int employeeId, String employeeName, LocalDate date) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.date = date;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
