package com.example.finityloops1.hrsystem.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.finityloops1.hrsystem.R;


public class prefConfig {
    private SharedPreferences pref;
    private Context context;
    public prefConfig(Context context){
        this.context=context;
        pref=context.getSharedPreferences(context.getString(R.string.pref),context.MODE_PRIVATE);
    }
    public void writestatuslogin(boolean status){
        SharedPreferences.Editor editor=pref.edit();
        editor.putBoolean(context.getString(R.string.pref_login_status),status);
        editor.commit();
    }
    public boolean readstatuslogin(){
        return pref.getBoolean(context.getString(R.string.pref_login_status),false);
    }

    public void writeName(String name){
        SharedPreferences.Editor editor=pref.edit();
        editor.putString(context.getString(R.string.pref__user_name),name);
    }
    public void displaytoast(String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();

    }
    public String readName(){
        return pref.getString(context.getString(R.string.pref__user_name),"User");
    }


}
