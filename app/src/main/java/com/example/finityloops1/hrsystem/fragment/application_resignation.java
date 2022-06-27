package com.example.finityloops1.hrsystem.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.finityloops1.hrsystem.MainActivity;
import com.example.finityloops1.hrsystem.R;
import com.example.finityloops1.hrsystem.model.ModelInsertResignation;
import com.example.finityloops1.hrsystem.model.ModelShowResignation;

import java.sql.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class application_resignation extends Fragment {
    EditText edt_reasons;
    TextView employee_name_txt;
    TextView resignation_number;
    public static String FirstName;
    public static int UserID;
    public static String SecondName;
    public static String ThirdName;
    public static String FourName;
    public static String WorkPhone;
    public static String WorkEmail;
    public static String reasons;
    public static int statusID;
    public static int requestdate;
    Button send_dataapp;
    public application_resignation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_application_resignation, container, false);
        edt_reasons =(EditText)view.findViewById(R.id.edt_reasons);
        employee_name_txt=(TextView)view.findViewById(R.id.employee_name_txt);
        resignation_number=(TextView)view.findViewById(R.id.resignation_number);
        send_dataapp=(Button)view.findViewById(R.id.send_dataapp);
        FirstName=MainActivity.FirstName;
        SecondName=MainActivity.SecondName;
        ThirdName=MainActivity.ThirdName;
        FourName=MainActivity.FourName;
        employee_name_txt.setText(FirstName+" "+ SecondName+" "+ThirdName+" "+" "+FourName);
        send_dataapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertresignation();
            }
        });
        showresignation();

        return  view;
    }

    private void showresignation() {
        UserID= MainActivity.UserID;
Call<ModelShowResignation> call = MainActivity.apiInterface.getResignation(UserID);
call.enqueue(new Callback<ModelShowResignation>() {
    @Override
    public void onResponse(Call<ModelShowResignation> call, Response<ModelShowResignation> response) {
        if (response.body().getResponse().equals("waiting")){
            ModelShowResignation  json=response.body();
            reasons= json.getReason();
            edt_reasons.setText(reasons);
            String reginstonID= String.valueOf(json.getID());
            resignation_number.setText(reginstonID);
            send_dataapp.setEnabled(false);
            edt_reasons.setEnabled(false);
            }
          else if(response.body().getResponse().equals("approval")){
                ModelShowResignation  json=response.body();
                reasons= json.getReason();
                edt_reasons.setText(reasons);
                String reginstonID= String.valueOf(json.getID());
                resignation_number.setText(reginstonID);
            send_dataapp.setEnabled(false);
            edt_reasons.setEnabled(false);
            }
            else {
                resignation_number.setText(getString(R.string.news));
                employee_name_txt.setText(FirstName+" "+SecondName+" "+ThirdName+" "+FourName);
                edt_reasons.setText("");


            }
    }

    @Override
    public void onFailure(Call<ModelShowResignation> call, Throwable t) {

    }
});
    }


    private void insertresignation(){
        int EmployeeID= MainActivity.UserID;
        reasons= edt_reasons.getText().toString();
        statusID=3;
        Date datenow = new Date(System.currentTimeMillis());
        Call<ModelInsertResignation> call =MainActivity.apiInterface.getinsertResignation(EmployeeID,datenow,reasons,statusID);
        call.enqueue(new Callback<ModelInsertResignation>() {
            @Override
            public void onResponse(Call<ModelInsertResignation> call, Response<ModelInsertResignation> response) {
                if (response.body().getResponse().equals("ok")){
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
            public void onFailure(Call<ModelInsertResignation> call, Throwable t) {

            }
        });


    }

}
