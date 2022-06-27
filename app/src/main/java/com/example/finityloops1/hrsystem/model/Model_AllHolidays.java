package com.example.finityloops1.hrsystem.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Model_AllHolidays {
    @SerializedName("result")
        @Expose
        private ModelAllResultHolidays[] result = null;

        public ModelAllResultHolidays[] getResult() {
            return result;
        }

        public void setResult(ModelAllResultHolidays[] result) {
            this.result = result;
        }

    }






