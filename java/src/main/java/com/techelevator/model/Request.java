package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Request {

    private int requestId;
    private int employeeId;
    private String employeeName;
    private LocalDate date;
    private String message;
    private boolean isEmergency;
    private boolean isCovered;
    private boolean isPending;
    private boolean isApproved;

    // FOR SPRINT 2
    private List<Integer> shiftId;

    // AFTER SPRING 1 DELETE THIS CONSTRUCTOR
    public Request(int requestId, int employeeId, String employeeName, LocalDate date, boolean isEmergency, boolean isPending, boolean isApproved, String message, boolean isCovered) {
        this.requestId = requestId;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.date = date;
        this.isEmergency = isEmergency;
        this.isPending = isPending;
        this.isApproved = isApproved;
        this.message = message;
        this.isCovered = isCovered;
    }

// USE THIS CONSTRUCTOR FOR SPRINT 2
    public Request(int requestId, int employeeId, String employeeName, LocalDate date, boolean isEmergency, boolean isPending, boolean isApproved, String message, boolean isCovered, List<Integer> shiftId) {
        this.requestId = requestId;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.date = date;
        this.isEmergency = isEmergency;
        this.isPending = isPending;
        this.isApproved = isApproved;
        this.message = message;
        this.isCovered = isCovered;
        this.shiftId = shiftId;
    }

    public Request() {
    }

    public List<Integer> getShiftId() {
        return shiftId;
    }

    public void setShiftId(List<Integer> shiftId) {
        this.shiftId = shiftId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isCovered() {
        return isCovered;
    }

    public void setCovered(boolean covered) {
        isCovered = covered;
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
