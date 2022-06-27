package com.example.finityloops1.hrsystem.fragment;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.finityloops1.hrsystem.Adapter.CustomSpinnerAdapter;
import com.example.finityloops1.hrsystem.Home;
import com.example.finityloops1.hrsystem.MainActivity;
import com.example.finityloops1.hrsystem.R;
import com.example.finityloops1.hrsystem.model.EndDate;
import com.example.finityloops1.hrsystem.model.ModelInsertHoliday;
import com.example.finityloops1.hrsystem.model.ModelTypeHoliday;
import com.example.finityloops1.hrsystem.model.Model_showHolidays;
import com.example.finityloops1.hrsystem.model.Result_typeModel;
import com.example.finityloops1.hrsystem.model.StartDate;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class application_holidays extends Fragment implements View.OnClickListener{
    TextView numberholidays;
    TextView name_employer;
    EditText edtinput_startdate;
    EditText date_number;
    TextView holiday_id;
    Button send_data;
    EditText edtinput_enddate;
    public static int UserID;
    public static String UserImage;
    EditText edt_reason;
    public   int pos;
    public static String FirstName;
    public static String SecondName;
    public static String ThirdName;
    public static String FourName;
    public static String WorkPhone;
    public static String WorkEmail;
    public static int NoOFDays;
    public static String startDate;
    public static  String enddate;
     public static String reason;
     public static int requestdate;
    TextView numtype;
   public TextView fullName;
    Button btn_date;
    private int mYear, mMonth, mDay;
    int end_holidays;
    private CustomSpinnerAdapter adapterspinner;

    int number_days;

    ArrayList<ModelTypeHoliday> list;
    Spinner spinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
        }


    }

    public application_holidays() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_application_holidays, container, false);
        spinner=(Spinner) view.findViewById(R.id.spinner_type_holidays) ;
        edtinput_startdate=(EditText)view.findViewById(R.id.edt_startdate);
        holiday_id=(TextView)view.findViewById(R.id.holiday_numbers);
        send_data=(Button)view.findViewById(R.id.send_data);
        fullName=(TextView)view.findViewById(R.id.name_employees) ;
        edtinput_enddate=(EditText)view.findViewById(R.id.edt_enddate);
        final Button send_data=(Button)view.findViewById(R.id.send_data);
        btn_date=(Button)view.findViewById(R.id.btn_date);
        date_number=(EditText)view.findViewById(R.id.date_number);
        edt_reason=(EditText)view.findViewById(R.id.edt_reason);
        btn_date.setOnClickListener(this);
        send_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt_reason.getText().toString().trim().length() == 0) {
                    date_number.setError("ادخل السبب");
                    date_number.requestFocus();


                } else {

                    try {
                        insertdata();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        UserID= MainActivity.UserID;
        FirstName=MainActivity.FirstName;
        SecondName=MainActivity.SecondName;
        ThirdName=MainActivity.ThirdName;
        FourName=MainActivity.FourName;

        fullName.setText(FirstName+" "+SecondName+" "+ThirdName+" "+FourName);
        show_dataholidays();
        fetchdropdown();



        return view;
    }


   @SuppressLint("SimpleDateFormat")
   private void insertdata() throws ParseException {

       fetchdropdown();



       UserID=MainActivity.UserID;
       Calendar c = Calendar.getInstance();
      NoOFDays=Integer.parseInt(date_number.getText().toString());
       DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String stdate=edtinput_startdate.getText().toString();

       java.util.Date dateObject = formatter.parse(stdate);
         Date startdates= new Date(dateObject.getTime());
      String endates= edtinput_enddate.getText().toString();
       java.util.Date dateObject2= formatter.parse(endates);
      Date enddates =new Date(dateObject2.getTime());
       Date datenow = new Date(System.currentTimeMillis());

       reason=edt_reason.getText().toString();
       int statusid= 3;





        Call<ModelInsertHoliday> call = MainActivity.apiInterface.getinsertholidays(pos,UserID,NoOFDays,startdates,enddates,reason,statusid,datenow);

        call.enqueue(new Callback<ModelInsertHoliday>() {
            @Override
            public void onResponse(Call<ModelInsertHoliday> call, Response<ModelInsertHoliday> response) {
                if (response.body().getResponse().equals("ok")) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                    alertDialogBuilder.setTitle("..!!!"+getString(R.string.success));
                    alertDialogBuilder.setIcon(R.drawable.ic_tick);
                    alertDialogBuilder.setMessage(getString(R.string.send_ok));
                    alertDialogBuilder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {


                        }
                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();


                }



                else {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                    alertDialogBuilder.setTitle("...!!!"+getString(R.string.failed));
                    alertDialogBuilder.setIcon(R.drawable.ic_wrong);
                    alertDialogBuilder.setMessage(getString(R.string.failed_send_request));
                    alertDialogBuilder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                           MainActivity .preconfig.displaytoast(getString(R.string.no_send_request));

                        }
                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            }

            @Override
            public void onFailure(Call<ModelInsertHoliday> call, Throwable t) {

            }
        });




   }
    //show all data spinner
    private void fetchdropdown(){

        retrofit2.Call<Result_typeModel> call=MainActivity.apiInterface.getType();
        call.enqueue(new Callback<Result_typeModel>() {
            @Override
            public void onResponse(retrofit2.Call<Result_typeModel> call, Response<Result_typeModel> response) {

                Log.d("JSON  LIST",call.toString());
                Result_typeModel json=response.body();
                list= new ArrayList<>(Arrays.asList(json.getResult()));
                    adapterspinner= new CustomSpinnerAdapter(getActivity(),list);
                    spinner.setAdapter(adapterspinner);
                 spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                     @Override
                     public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                         pos=position+1;

                     }

                     @Override
                     public void onNothingSelected(AdapterView<?> adapterView) {

                     }
                 });


            }



            @Override
            public void onFailure(retrofit2.Call<Result_typeModel> call, Throwable t) {

            }
        });



    }

    private void show_dataholidays(){
        fetchdropdown();
        UserID= MainActivity.UserID;
        FirstName=MainActivity.FirstName;
        SecondName=MainActivity.SecondName;
        ThirdName=MainActivity.ThirdName;
        FourName=MainActivity.FourName;


       Call<Model_showHolidays> call = MainActivity.apiInterface.getHolidays(UserID);
        call.enqueue(new Callback<Model_showHolidays>() {
            @Override
            public void onResponse(Call<Model_showHolidays> call, Response<Model_showHolidays> response) {
                if (response.body().getResponse().equals("waiting")){
                   Model_showHolidays json= response.body();
                   String num_holidays= String.valueOf(json.getID());
                    holiday_id.setText(num_holidays);
                    //int type_holydays= json.getTypeid();
                 //spinner.setSelection(json.getTypeid());
                 String datenumber= String.valueOf(json.getNumDate());
                    date_number.setText(datenumber);
                  String startdate = json.getStartDate().getDate().toString().substring(0,10);
                    edtinput_startdate.setText(startdate);
                    String end_date= json.getEndDate().getDate().toString().substring(0,10);
                    String reasons= json.getReason();
                    edtinput_enddate.setText(end_date);
                    edt_reason.setText(reasons);
                    send_data.setEnabled(false);
                    edt_reason.setEnabled(false);

                }
                else{
                 holiday_id.setText(getString(R.string.news));
                    fullName.setText(FirstName+" "+SecondName+" "+ThirdName+" "+FourName);
                    date_number.setText("");
                    edtinput_startdate.setText("");
                    edtinput_enddate.setText("");

                }
            }

            @Override
            public void onFailure(Call<Model_showHolidays> call, Throwable t) {

            }
        });

    }

    @Override
    public void onClick(View view) {  //button onclick date

        if (view==btn_date){
            final Calendar calendar= Calendar.getInstance();

            mYear=calendar.get(Calendar.YEAR);
            mMonth =calendar.get(Calendar.MONTH);
            mDay=calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog= new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
               edtinput_startdate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);



                }
            },mYear,mMonth,mDay);
            datePickerDialog.show();
            if(date_number.getText().toString().trim().length()==0){
                date_number .setError(getString(R.string.input_date));
                date_number.requestFocus();


            }
           else {
                number_days = Integer.parseInt(date_number.getText().toString());
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                Calendar calendar1= new GregorianCalendar(mYear,mMonth,mDay);
                  calendar1.add(Calendar.DAY_OF_MONTH, number_days);



                String end_date = df.format(calendar1.getTime());
                edtinput_enddate.setText(end_date);


            }
        }

    }
    //end onclick button btn_data onClick
}
