package com.techelevator.model;

import java.time.LocalDate;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Shift {

    int shiftId;
    //TODO should this be true?
    boolean isCovered;

    int shiftOwnerId;
    String shiftOwnerName;
    Integer shiftVolunteerId;
    String shiftVolunteerName;
    LocalDate startTime;
    LocalDate endTime;

    public Shift(int shiftId, boolean isCovered, int shiftOwnerId, String shiftOwnerName, Integer shiftVolunteerId, String shiftVolunteerName, LocalDate startTime, LocalDate endTime) {
        this.shiftId = shiftId;
        this.isCovered = isCovered;
        this.shiftOwnerId = shiftOwnerId;
        this.shiftOwnerName = shiftOwnerName;
        this.shiftVolunteerId = shiftVolunteerId;
        this.shiftVolunteerName = shiftVolunteerName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Shift() {
    }

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public boolean isCovered() {
        return isCovered;
    }

    public void setCovered(boolean covered) {
        isCovered = covered;
    }

    public int getShiftOwnerId() {
        return shiftOwnerId;
    }

    public void setShiftOwnerId(int shiftOwnerId) {
        this.shiftOwnerId = shiftOwnerId;
    }

    public String getShiftOwnerName() {
        return shiftOwnerName;
    }

    public void setShiftOwnerName(String shiftOwnerName) {
        this.shiftOwnerName = shiftOwnerName;
    }

    public Integer getShiftVolunteerId() {
        return shiftVolunteerId;
    }

    public void setShiftVolunteerId(Integer shiftVolunteerId) {
        this.shiftVolunteerId = shiftVolunteerId;
    }

    public String getShiftVolunteerName() {
        return shiftVolunteerName;
    }

    public void setShiftVolunteerName(String shiftVolunteerName) {
        this.shiftVolunteerName = shiftVolunteerName;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }
}
