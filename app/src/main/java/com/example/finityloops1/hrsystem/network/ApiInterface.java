package com.example.finityloops1.hrsystem.network;



import com.example.finityloops1.hrsystem.model.ModelChangPassword;
import com.example.finityloops1.hrsystem.model.ModelInsertHoliday;
import com.example.finityloops1.hrsystem.model.ModelInsertResignation;
import com.example.finityloops1.hrsystem.model.ModelLogins;
import com.example.finityloops1.hrsystem.model.ModelShowResignation;
import com.example.finityloops1.hrsystem.model.ModelTypeHoliday;
import com.example.finityloops1.hrsystem.model.Model_AllHolidays;
import com.example.finityloops1.hrsystem.model.Model_showHolidays;
import com.example.finityloops1.hrsystem.model.Model_showReginstones;
import com.example.finityloops1.hrsystem.model.Result_typeModel;

import java.util.Date;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
@FormUrlEncoded
@POST("Login.php")
Call<ModelLogins> postlogin(@Field("user_name") String telephone, @Field("password") String password);
@GET("holidays_type.php")
Call<Result_typeModel> getType();
@GET("fetch_holiday.php")
Call<Model_showHolidays> getHolidays(@Query("emp_id") int emp_id);
@FormUrlEncoded
@POST("insert_dataholidays.php")
Call<ModelInsertHoliday> getinsertholidays(@Field("HolyTypeID") int HolyTypeID, @Field("EmpID") int EmpID, @Field("NoOFDays") int NoOFDays, @Field("StartDate")Date StartDate,@Field("EndDate")Date EndDate,@Field("Reason")String Reason,@Field("StatusID") int StatusID,@Field("RequstDate") Date RequstDate);
    @FormUrlEncoded
    @POST("application_resignation.php")
    Call<ModelInsertResignation> getinsertResignation(@Field("EmpID") int EmpID,@Field("RequstDate") Date RequstDate,@Field("Reason") String Reason,@Field("Status") int Status);
    @GET("fetch_resignation.php")
    Call<ModelShowResignation> getResignation(@Query("emp_id") int emp_id);
    @GET("ahow_holidayall.php")
    Call<Model_AllHolidays> getshowholidays(@Query("emp_id") int emp_id);
    @GET("show_resignationall.php")
    Call<Model_showReginstones> getshowresignation(@Query("emp_id") int emp_id);
    @FormUrlEncoded
    @POST("change_password.php")
    Call<ModelChangPassword> getupdate(@Field("password") String password, @Field("new_password") String new_password , @Field("emp_id") int  emp_id);
}
