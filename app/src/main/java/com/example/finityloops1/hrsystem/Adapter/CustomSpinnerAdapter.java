package com.example.finityloops1.hrsystem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.finityloops1.hrsystem.R;
import com.example.finityloops1.hrsystem.model.ModelTypeHoliday;
import com.example.finityloops1.hrsystem.model.Result_typeModel;

import java.util.ArrayList;
import java.util.List;

public class CustomSpinnerAdapter extends BaseAdapter {
    Context context;
    private ArrayList<Integer> typeid = new ArrayList();
    private ArrayList<ModelTypeHoliday> typesname = new ArrayList();

    public CustomSpinnerAdapter(Context context, ArrayList<ModelTypeHoliday>typesname){
this.context=context;

this.typesname=typesname;

    }
    @Override
    public int getCount() {

        return typesname.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {


        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
      view= inflater.inflate(R.layout.custom_spinner_items, null);
        TextView typename=(TextView) view.findViewById(R.id.typeholidays);
        typename.setText(typesname.get(i).getName());
        return view;
    }
}
