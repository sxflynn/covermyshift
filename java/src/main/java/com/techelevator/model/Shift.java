package com.techelevator.model;

import java.time.LocalDate;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Shift {

    //TODO should this be true?
    boolean isCovered;

    int shiftOwnerId;
    Integer shiftVolunteerId;
    LocalDate shiftDate;

    int companyId;

    public Shift(boolean isCovered, int shiftOwnerId, Integer shiftVolunteerId, LocalDate shiftDate, int companyId) {
        this.isCovered = isCovered;
        this.shiftOwnerId = shiftOwnerId;
        this.shiftVolunteerId = shiftVolunteerId;
        this.shiftDate = shiftDate;
        this.companyId = companyId;
    }

    public Shift(){

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

    public Integer getShiftVolunteerId() {
        return shiftVolunteerId;
    }

    public void setShiftVolunteerId(Integer shiftVolunteerId) {
        this.shiftVolunteerId = shiftVolunteerId;
    }

    public LocalDate getShiftDate() {
        return shiftDate;
    }

    public void setShiftDate(LocalDate shiftDate) {
        this.shiftDate = shiftDate;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
