package com.example.finityloops1.hrsystem.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finityloops1.hrsystem.R;
import com.example.finityloops1.hrsystem.model.ModelAllResultHolidays;
import com.example.finityloops1.hrsystem.model.Model_AllHolidays;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Callback;


    public class adapter_showholidays extends RecyclerView.Adapter<adapter_showholidays.ViewHoldercompany> {
        ArrayList<String> typeName= new ArrayList();
        ArrayList<String> statusName= new ArrayList();
        ArrayList<Integer> numDays= new ArrayList();
        ArrayList<String> requestDate=new ArrayList();
        ArrayList<String> endDate=new ArrayList();
        ArrayList<String>startDate= new ArrayList();
        ArrayList<String>Reason= new ArrayList();
        ArrayList<Integer> ID=new ArrayList<>();
        OnItemClickListener mlistener;


        public interface  OnItemClickListener{
            public void OnItemClick(int position);
        }
        public void setOnItemClickListener(OnItemClickListener listener){
            this.mlistener= (OnItemClickListener) listener;
        }

        public adapter_showholidays(ArrayList<String> typeName, ArrayList<String> statusName,ArrayList numDays,ArrayList requestDate,ArrayList endDate,ArrayList startDate,ArrayList Reason,ArrayList ID) {
           this. typeName = typeName;
          this.statusName = statusName;
          this.numDays=numDays;
          this.requestDate=requestDate;
          this.endDate=endDate;
          this.startDate=startDate;
          this.Reason=Reason;
          this.ID=ID;
        }

        @NonNull
        @Override
        public adapter_showholidays.ViewHoldercompany onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_recyclerholidays,parent,false);
            ViewHoldercompany holder= new ViewHoldercompany(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull adapter_showholidays.ViewHoldercompany holder, int position) {
            holder.typename_input.setText(typeName.get(position));
            holder.status_input.setText(statusName.get(position).toString());
            holder.Request_date_input.setText(requestDate.get(position).toString());
            holder.ID.setText("#"+String.valueOf(ID.get(position)));


        }

        @Override
        public int getItemCount() {
            return typeName.size();
        }

        public class ViewHoldercompany extends RecyclerView.ViewHolder   {
            TextView typename_input;
             TextView Request_date_input;
            TextView status_input;
            TextView ID;


            public ViewHoldercompany(View itemView) {
                super(itemView);
                typename_input =itemView.findViewById(R.id.typename_input);
                Request_date_input=itemView.findViewById(R.id.Request_date_input);
                status_input=itemView.findViewById(R.id.status_input);
                ID=itemView.findViewById(R.id.ID);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mlistener!=null){
                            int position= getAdapterPosition();
                            if (position!=RecyclerView.NO_POSITION){
                                mlistener.OnItemClick(position);

                            }
                        }
                    }
                });

            }
        }
    }