package com.example.finityloops1.hrsystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.finityloops1.hrsystem.Adapter.adapter_showholidays;
import com.example.finityloops1.hrsystem.model.ModelAllResultHolidays;
import com.example.finityloops1.hrsystem.model.Model_AllHolidays;
import com.example.finityloops1.hrsystem.model.RequstDate;
import com.example.finityloops1.hrsystem.network.ApiInterface;


import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class show_holidays extends AppCompatActivity  implements adapter_showholidays.OnItemClickListener{
    Handler handler = new Handler();
    public boolean runthread = true;


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(runnable, 6000);
            if (runthread) {
                update();

            }
        }
    };
    RecyclerView recyclerUsers;
    adapter_showholidays adapter;
    ArrayList<String> typeName= new ArrayList();
    ArrayList<String> statusName= new ArrayList();
    ArrayList<Integer> numDays= new ArrayList();
    ArrayList<String> requestDate=new ArrayList<>();
    ArrayList<String> endDate=new ArrayList<>();
    ArrayList<String> ID=new ArrayList<>();
    ArrayList<String>startDate= new ArrayList<>();
    ArrayList<String>Reason= new ArrayList<>();
   TextView IDs;
   TextView typename;
   TextView status;
   TextView Request_date;
TextView titles;
    public static final int NAME_SYNCED_WITH_SERVER = 1;
    public static final int NAME_NOT_SYNCED_WITH_SERVER = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_holidays);
        recyclerUsers = (RecyclerView) findViewById(R.id.recycler_holidays);
        IDs=(TextView)findViewById(R.id.IDs);
        typename=(TextView)findViewById(R.id.typename);
        status=(TextView)findViewById(R.id.status);
        Request_date=(TextView)findViewById(R.id.Request_date);
        titles=(TextView)findViewById(R.id.titles);
        recyclerUsers.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(show_holidays.this);
        recyclerUsers.setLayoutManager(layoutManager);


        adapter = new adapter_showholidays(typeName, statusName,numDays,requestDate,endDate,startDate,Reason,ID);
        recyclerUsers.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener(this);
        runnable.run();
    }

    private void update() {
         int userid=MainActivity.UserID;
        ApiInterface apiInterface = MainActivity.apiInterface;
        Call<Model_AllHolidays> call = apiInterface.getshowholidays(userid);
        call.enqueue(new Callback<Model_AllHolidays>() {
            @Override
            public void onResponse(Call<Model_AllHolidays> call, Response<Model_AllHolidays> response) {
                if (response.body().getResult().length > 0) {

                    Model_AllHolidays json = response.body();

                    List<ModelAllResultHolidays> list = new ArrayList(Arrays.asList(json.getResult()));
                    int size = adapter.getItemCount();
                    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    for (int i = 0; i < list.size(); i++) {
                        if (size <= 0) {
                            typeName.add(list.get(i).getTypeName());
                            statusName.add(list.get(i).getStatusName());
                            numDays.add(list.get(i).getNumDays());
                            requestDate.add((list.get(i).getRequestDate().getDate().substring(0, 10)));
                            endDate.add(list.get(i).getEndDate().getDate());
                            startDate.add(list.get(i).getStartDate().getDate());
                            Reason.add(list.get(i).getReason());
                            ID.add(String.valueOf(list.get(i).getID()));
                            adapter.notifyItemInserted(i);
                        } else {
                            typeName.set(i, list.get(i).getTypeName());
                            statusName.set(i, list.get(i).getStatusName());
                            numDays.set(i, list.get(i).getNumDays());
                            requestDate.set(i, list.get(i).getRequestDate().getDate().substring(0, 10));
                            endDate.set(i, list.get(i).getEndDate().getDate());
                            startDate.set(i, list.get(i).getStartDate().getDate());
                            Reason.set(i, list.get(i).getReason());
                            ID.add(String.valueOf(list.get(i).getID()));
                            adapter.notifyItemChanged(i);
                        }
                        size--;


                    }
                    if (size > 0) {
                        for (int i = 0; i <= 0; i++) {
                            typeName.remove(list.size() - size);
                            statusName.remove(list.size() - size);

                            numDays.remove(list.size() - size);

                            requestDate.remove(list.size() - size);

                            endDate.remove(list.size() - size);

                            startDate.remove(list.size() - size);
                            ID.remove(list.size() - size);
                            Reason.remove(list.size() - size);
                            adapter.notifyItemRemoved(list.size() - size);

                        }
                    }
                    adapter.notifyDataSetChanged();
                }
                else {
                    titles.setVisibility(View.VISIBLE);
                    IDs.setVisibility(View.GONE);
                    typename.setVisibility(View.GONE);
                    status.setVisibility(View.GONE);
                    Request_date.setVisibility(View.GONE);


                }


            }
            @Override
            public void onFailure(Call<Model_AllHolidays> call, Throwable t) {

            }
        });
    }

    @Override
    public void OnItemClick(int position) {
        Intent i= new Intent(show_holidays.this,details_holiday.class);
        i.putExtra("requestDate",requestDate.get(position));
        i.putExtra("ID",ID.get(position));
        i.putExtra("numDays",numDays.get(position));
        i.putExtra("endDate",endDate.get(position));
        i.putExtra("StartDate",startDate.get(position));
        i.putExtra("reason",Reason.get(position));
        startActivity(i);
    }
}