package com.example.finityloops1.hrsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class details_holiday extends AppCompatActivity {
TextView holiday_numbers_details;
TextView name_employees_details;
TextView date_number_details;
TextView start_date_details;
TextView end_date_details;
TextView reson_txt_details;
TextView app_holiday;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_holiday);
        holiday_numbers_details=(TextView)findViewById(R.id.holiday_numbers_details);
        name_employees_details=(TextView)findViewById(R.id.name_employees_details);
        date_number_details=(TextView)findViewById(R.id.date_number_details);
        start_date_details=(TextView)findViewById(R.id.start_date_details);
        end_date_details=(TextView)findViewById(R.id.end_date_details);
        reson_txt_details=(TextView)findViewById(R.id.reson_txt_details);
        app_holiday=(TextView)findViewById(R.id.app_holidays);
        Intent i= getIntent();
        String app_holidays= i.getStringExtra("requestDate").substring(0,10);
        app_holiday.setText(getString(R.string.application_holiday_title_details)+" "+app_holidays);
       String holiday_number=i.getStringExtra("ID");
       String fullname= MainActivity.FirstName+" "+MainActivity.SecondName+" "+MainActivity.ThirdName+" "+MainActivity.FourName;
    String date_number=String.valueOf(i.getIntExtra("numDays",0));
    String startdate= i.getStringExtra("StartDate").substring(0,10);
    String end_date=i.getStringExtra("endDate").substring(0,10);
    String reason = i.getStringExtra("reason");
    holiday_numbers_details.setText(holiday_number);
    name_employees_details.setText(fullname);
    date_number_details.setText(date_number);
    start_date_details.setText(startdate);
    end_date_details.setText(end_date);
        reson_txt_details.setText(reason);
    }
}
