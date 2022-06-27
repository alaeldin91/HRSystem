package com.example.finityloops1.hrsystem.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Model_showReginstones {
    @SerializedName("result")
    @Expose
    private Model_ResultshowReginstons[] result = null;

    public Model_ResultshowReginstons[] getResult() {
        return result;
    }

    public void setResult(Model_ResultshowReginstons[] result) {

        this.result = result;
    }
}
