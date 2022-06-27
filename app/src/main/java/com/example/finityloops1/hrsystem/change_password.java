package com.example.finityloops1.hrsystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finityloops1.hrsystem.model.ModelChangPassword;
import com.example.finityloops1.hrsystem.network.ApiInterface;

import retrofit2.Callback;
import retrofit2.Response;

public class change_password extends AppCompatActivity {
EditText txt_old_password;
EditText txt_new_password;
Button btn_update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        txt_old_password=(EditText)findViewById(R.id.old_password);
        txt_new_password=(EditText)findViewById(R.id.new_password);
        btn_update=(Button)findViewById(R.id.update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update_password();
            }
        });

    }
    private void update_password() {
        String old_password = txt_old_password.getText().toString();
        String new_password = txt_new_password.getText().toString();

            ApiInterface apiInterface = MainActivity.apiInterface;
            int userid = MainActivity.UserID;
            retrofit2.Call<ModelChangPassword> call = apiInterface.getupdate(old_password, new_password, userid);
            call.enqueue(new Callback<ModelChangPassword>() {
                @Override
                public void onResponse(retrofit2.Call<ModelChangPassword> call, Response<ModelChangPassword> response) {
                    if (response.body().getResponse().equals("ok")) {
                        //   startActivity(new Intent(change_password.this, MainActivity.class));
                        //    MainActivity.preconfig.displaytoast(getString(R.string.success_password));


                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(change_password.this);
                        alertDialogBuilder.setTitle("..!!!" + " " + getString(R.string.success));
                        alertDialogBuilder.setIcon(R.drawable.ic_tick);
                        alertDialogBuilder.setMessage(getString(R.string.success_password));
                        alertDialogBuilder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                                Intent intent = new Intent(change_password.this, MainActivity.class);
                                startActivity(intent);
                            }
                        });
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    } else {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(change_password.this);
                        alertDialogBuilder.setTitle("...!!!" + getString(R.string.failed));

                        alertDialogBuilder.setIcon(R.drawable.ic_wrong);
                        alertDialogBuilder.setMessage(getString(R.string.failed_password_update));
                        alertDialogBuilder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                            }
                        });
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();


                    }
                }

                @Override
                public void onFailure(retrofit2.Call<ModelChangPassword> call, Throwable t) {

                }
            });
        }

    }



