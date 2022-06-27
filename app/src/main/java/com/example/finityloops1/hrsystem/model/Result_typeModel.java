package com.example.finityloops1.hrsystem.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result_typeModel {



    @SerializedName("result")
    @Expose
    private ModelTypeHoliday[] result = null;

    public ModelTypeHoliday[] getResult() {
        return result;
    }

    public void setResult(ModelTypeHoliday[] result) {
        this.result = result;
    }

}



