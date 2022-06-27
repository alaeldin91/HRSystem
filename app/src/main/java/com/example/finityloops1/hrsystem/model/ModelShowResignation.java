package com.example.finityloops1.hrsystem.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelShowResignation {

    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("EmpID")
    @Expose
    private int empID;
    @SerializedName("RequstDate")
    @Expose
    private RequstDate requstDate;
    @SerializedName("Reason")
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

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public RequstDate getRequstDate() {
        return requstDate;
    }

    public void setRequstDate(RequstDate requstDate) {
        this.requstDate = requstDate;
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
