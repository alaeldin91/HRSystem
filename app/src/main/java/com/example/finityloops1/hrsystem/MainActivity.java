package com.example.finityloops1.hrsystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.finityloops1.hrsystem.helper.prefConfig;
import com.example.finityloops1.hrsystem.model.ModelLogins;
import com.example.finityloops1.hrsystem.network.ApiClient;
import com.example.finityloops1.hrsystem.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
     EditText InputUserName;
     EditText InputPassword;
     public static  prefConfig preconfig;
     public static ApiInterface apiInterface;
     public static int UserID;
     public static String UserImage;
     public static String FirstName;
     public static String SecondName;
     public static String ThirdName;
     public static String FourName;
     public static String WorkPhone;

  public static   byte[] decodedString ;
  public static   Bitmap decodedByte ;

    public static String WorkEmail;
     public static int ImageProfile;
     Button login;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InputUserName=(EditText)findViewById(R.id.edt_username);
        InputPassword=(EditText)findViewById(R.id.edt_password);
        login =(Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        }
    private void login(){
         preconfig = new prefConfig(this);
         apiInterface= ApiClient.getClient().create(ApiInterface.class);
         String UserName=InputUserName.getText().toString();
         String Password=InputPassword.getText().toString();
        if(UserName.trim().length()==0){
            InputUserName.setError(getString(R.string.please_enter_username));
            InputUserName.requestFocus();
            preconfig.displaytoast(getString(R.string.please_enter_username));

        }
        else if(Password.trim().length()==0){
            InputPassword .setError(getString(R.string.please_enter_password));
            InputPassword.requestFocus();

            preconfig.displaytoast(getString(R.string.please_enter_password));
        }
        else {
            Call<ModelLogins> call =apiInterface.postlogin(UserName,Password);
            call.enqueue(new Callback<ModelLogins>() {
                @Override
                public void onResponse(Call<ModelLogins> call, Response<ModelLogins> response) {
                    if (response.body().getResponse().equals("ok")){

                        ModelLogins users= response.body();
                        UserImage =  response.body().getPhoto().toString();
                         decodedString = Base64.decode(UserImage, Base64.DEFAULT);
                         decodedByte = BitmapFactory.decodeByteArray(decodedString, 0,decodedString.length);

                        UserID= response.body().getUserid();
                        FirstName=response.body().getFirstName();
                        SecondName=response.body().getSecondName();
                        ThirdName=response.body().getThirdName();
                        FourName=response.body().getFourthName();
                        WorkEmail=response.body().getWorkEmail();
                        WorkPhone=response.body().getWorkPhone();


                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                        alertDialogBuilder.setTitle("..!!!"+getString(R.string.success));
                        alertDialogBuilder.setIcon(R.drawable.ic_tick);
                        alertDialogBuilder.setMessage(getString(R.string.welcomeemployee)+"  "+FirstName+" "+SecondName+" "+ThirdName+" "+FourName);
                        alertDialogBuilder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                           preconfig.displaytoast(getString(R.string.login_success));
                                Intent intent = new Intent(MainActivity.this,Home.class);
                                startActivity(intent);
                            }
                        });
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();


                    }
                    else {
                        //

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                        alertDialogBuilder.setTitle("...!!!"+getString(R.string.failed));
                        alertDialogBuilder.setIcon(R.drawable.ic_wrong);
                        alertDialogBuilder.setMessage(getString(R.string.login_failed));
                        alertDialogBuilder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                preconfig.displaytoast(getString(R.string.login_failed));

                            }
                        });
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();

                    }
                }

                @Override
                public void onFailure(Call<ModelLogins> call, final Throwable t) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertDialogBuilder.setTitle("...!!!"+getString(R.string.failed_connectionb));
                    alertDialogBuilder.setIcon(R.drawable.ic_error);
                    alertDialogBuilder.setMessage(getString(R.string.option_internet));
                    alertDialogBuilder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            preconfig.displaytoast(""+t.getMessage());

                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                }
            });
        }


    }


}
