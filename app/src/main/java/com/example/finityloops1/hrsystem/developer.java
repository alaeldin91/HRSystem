package com.example.finityloops1.hrsystem;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;


public class developer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
     ImageView   img=(ImageView)findViewById(R.id.imgvw);
     TextView txt=(TextView)findViewById(R.id.txt_details) ;
        Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.antirotate);

        Animation animFadeout = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
        Animation animantirotate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.antirotate);
        txt.startAnimation(animantirotate);
        img.startAnimation(animFadeIn);









    }
}
