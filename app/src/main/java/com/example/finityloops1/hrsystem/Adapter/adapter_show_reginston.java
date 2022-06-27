package com.example.finityloops1.hrsystem.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.finityloops1.hrsystem.MainActivity;
import com.example.finityloops1.hrsystem.R;

import java.util.ArrayList;

public class adapter_show_reginston extends RecyclerView.Adapter<adapter_show_reginston.view_ReginstonHolder> {
    ArrayList<String> Reason= new ArrayList();
    ArrayList<Integer> ID=new ArrayList<>();
    ArrayList<String> requestDate=new ArrayList();
    public adapter_show_reginston(ArrayList ID,ArrayList requestDate,ArrayList Reason){
        this.ID=ID;
        this.requestDate=requestDate;
        this.Reason=Reason;

    }
    @NonNull
    @Override
    public adapter_show_reginston.view_ReginstonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_regination,parent,false);
        adapter_show_reginston.view_ReginstonHolder holder= new adapter_show_reginston.view_ReginstonHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_show_reginston.view_ReginstonHolder holder, int position) {
        String fullname= MainActivity.FirstName+" "+MainActivity.SecondName+" "+MainActivity.ThirdName+" "+MainActivity.FourName;
        holder.name_employees_reginse.setText(fullname);
holder.app_holidays.setText("استقالة مرفوضة بتاريخ:"+"  "+requestDate.get(position).toString());
holder.holiday_numbers_reginose.setText(String.valueOf(ID.get(position)));
holder.edt_reason_reginse.setText(Reason.get(position));
    }

    @Override
    public int getItemCount() {
        return Reason.size();
    }
    public class view_ReginstonHolder extends RecyclerView.ViewHolder {
        TextView app_holidays;
        TextView holiday_numbers_reginose;
        TextView name_employees_reginse;
        TextView edt_reason_reginse;
        public view_ReginstonHolder(View itemView) {
            super(itemView);
            app_holidays= itemView.findViewById(R.id.app_holiday);
            holiday_numbers_reginose=itemView.findViewById(R.id.holiday_numbers_reginose);
            name_employees_reginse=itemView.findViewById(R.id.name_employees_reginse);
            edt_reason_reginse=itemView.findViewById(R.id.edt_reason_reginse);
        }
    }
}
