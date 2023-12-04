package com.techelevator.model;

import java.time.LocalDate;

public class Request {

    private int requestId;
    private int employeeId;
    private String employeeName;
    private LocalDate date;
    private int workplaceId;
    private boolean isEmergency;
    private boolean isPending;
    private boolean isApproved;

    public Request(int requestId, int employeeId, String employeeName, LocalDate date, int workplaceId, boolean isEmergency, boolean isPending, boolean isApproved) {
        this.requestId = requestId;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.date = date;
        this.workplaceId = workplaceId;
        this.isEmergency = isEmergency;
        this.isPending = isPending;
        this.isApproved = isApproved;
    }

    public Request() {
    }


    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
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

    public int getWorkplaceId() {
        return workplaceId;
    }

    public void setWorkplaceId(int workplaceId) {
        this.workplaceId = workplaceId;
    }

    public boolean isEmergency() {
        return isEmergency;
    }

    public void setEmergency(boolean emergency) {
        isEmergency = emergency;
    }

    public boolean isPending() {
        return isPending;
    }

    public void setPending(boolean pending) {
        isPending = pending;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}
