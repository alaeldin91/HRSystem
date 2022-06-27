package com.example.finityloops1.hrsystem;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.example.finityloops1.hrsystem.Adapter.adapter_show_reginston;
import com.example.finityloops1.hrsystem.Adapter.adapter_showholidays;
import com.example.finityloops1.hrsystem.model.ModelAllResultHolidays;
import com.example.finityloops1.hrsystem.model.Model_ResultshowReginstons;
import com.example.finityloops1.hrsystem.model.Model_showReginstones;
import com.example.finityloops1.hrsystem.network.ApiInterface;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class show_Resignation extends AppCompatActivity {
    ArrayList<String> Reason= new ArrayList();
    ArrayList<String> ID=new ArrayList<>();
    ArrayList<String> requestDate=new ArrayList();
    Handler handler = new Handler();
    public boolean runthread = true;
    RecyclerView recyclerUsers;
    adapter_show_reginston adapter;
    private Context mContext;
    private Activity mActivity;

    private RelativeLayout mRelativeLayout;

    private PopupWindow mPopupWindow;



    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(runnable, 6000);
            if (runthread) {
                update();

            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__resignation);
        recyclerUsers = (RecyclerView) findViewById(R.id.recycle_resignation);
        mContext = getApplicationContext();
        mActivity = show_Resignation.this;
        recyclerUsers.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(show_Resignation.this);
        recyclerUsers.setLayoutManager(layoutManager);
        adapter = new adapter_show_reginston(ID, requestDate,Reason);
        recyclerUsers.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        runnable.run();


    }
    private void update(){
        int userid=MainActivity.UserID;
      ApiInterface apiInterface= MainActivity.apiInterface;
        Call<Model_showReginstones> call  =apiInterface.getshowresignation(userid);
        call.enqueue(new Callback<Model_showReginstones>() {
            @Override
            public void onResponse(Call<Model_showReginstones> call, Response<Model_showReginstones> response) {
                if (response.body().getResult().length > 0) {
                    Model_showReginstones json = response.body();
                    List<Model_ResultshowReginstons> list = new ArrayList(Arrays.asList(json.getResult()));
                    int size = adapter.getItemCount();
                    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    for (int i = 0; i < list.size(); i++) {
                        if (size <= 0) {
                            ID.add(String.valueOf(list.get(i).getID()));
                            Reason.add(list.get(i).getReason());
                            requestDate.add((list.get(i).getRequestDate().getDate().substring(0, 10)));
                            adapter.notifyItemInserted(i);
                        } else {
                            ID.add(String.valueOf(list.get(i).getID()));
                            requestDate.set(i, list.get(i).getRequestDate().getDate().substring(0, 10));
                            Reason.set(i, list.get(i).getReason());
                            adapter.notifyItemChanged(i);
                        }
                        size--;

                    }
                    if (size > 0) {
                        for (int i = 0; i <= 0; i++) {
                            ID.remove(list.size() - size);
                            requestDate.remove(list.size() - size);
                            Reason.remove(list.size() - size);
                            adapter.notifyItemRemoved(list.size() - size);
                        }
                    }
                    adapter.notifyDataSetChanged();


                }
                else {
                    LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);


                    View customView = inflater.inflate(R.layout.popup,null);


                    mPopupWindow = new PopupWindow(
                            customView,
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    );


                    if(Build.VERSION.SDK_INT>=21){
                        mPopupWindow.setElevation(5.0f);
                    }
                    ImageButton closeButton = (ImageButton) customView.findViewById(R.id.ib_close);


                    closeButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Dismiss the popup window
                            mPopupWindow.dismiss();
                        }
                    });

                }
            }


            @Override
            public void onFailure(Call<Model_showReginstones> call, Throwable t) {

            }
        });


    }
}
