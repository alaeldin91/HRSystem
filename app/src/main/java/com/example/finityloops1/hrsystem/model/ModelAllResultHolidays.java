package com.example.finityloops1.hrsystem.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ModelAllResultHolidays {
    @SerializedName("typeName")
    @Expose
    private String typeName;
    @SerializedName("ID")
    @Expose
    private int ID;
    @SerializedName("statusName")
    @Expose
    private String statusName;
    @SerializedName("numDays")
    @Expose
    private int numDays;
    @SerializedName("StartDate")
    @Expose
    private StartDate startDate;
    @SerializedName("endDate")
    @Expose
    private EndDate endDate;
    @SerializedName("requestDate")
    @Expose
    private RequstDate requestDate;
    @SerializedName("reason")
    @Expose
    private String reason;

    public String getTypeName() {
        return typeName;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public int getID() {
        return ID;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }


    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public int getNumDays() {
        return numDays;
    }

    public void setNumDays(int numDays) {
        this.numDays = numDays;
    }

    public StartDate getStartDate() {
        return startDate;
    }

    public void setStartDate(StartDate startDate) {
        this.startDate = startDate;
    }

    public EndDate getEndDate() {
        return endDate;
    }

    public void setEndDate(EndDate endDate) {
        this.endDate = endDate;
    }

    public RequstDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(RequstDate requestDate) {
        this.requestDate = requestDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}