package com.example.finityloops1.hrsystem.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Model_ResultshowReginstons {

    @SerializedName("ID")
    @Expose
    private int ID;
    @SerializedName("requestDate")
    @Expose
    private EndDate requestDate;
    @SerializedName("reason")
    @Expose
    private String reason;

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setRequestDate(EndDate requestDate) {
        this.requestDate = requestDate;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


    public int getID() {
        return ID;
    }

    public EndDate getRequestDate() {
        return requestDate;
    }

    public String getReason() {
        return reason;
    }


}
