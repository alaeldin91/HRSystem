package com.example.finityloops1.hrsystem.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Model_showHolidays {
    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("typeid")
    @Expose
    private int typeid;
    @SerializedName("num_date")
    @Expose
    private int numDate;
    @SerializedName("StartDate")
    @Expose
    private StartDate startDate;
    @SerializedName("EndDate")
    @Expose
    private EndDate endDate;
    @SerializedName("reason")
    @Expose
    private String reason;
    @SerializedName("ID")
    @Expose
    private int iD;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public int getNumDate() {
        return numDate;
    }

    public void setNumDate(int numDate) {
        this.numDate = numDate;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getID() {
        return iD;
    }

    public void setID(int iD) {
        this.iD = iD;
    }
}
