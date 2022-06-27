package com.example.finityloops1.hrsystem.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.finityloops1.hrsystem.fragment.application_holidays;
import com.example.finityloops1.hrsystem.fragment.application_resignation;

public class Tab_Adapter extends FragmentPagerAdapter{
    private int number_of_columns;

    public Tab_Adapter(FragmentManager fragment,int number_of_columns) {
        super(fragment);
        this.number_of_columns=number_of_columns;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                application_holidays app_holidays= new application_holidays();

            return app_holidays;
            case 1:
                application_resignation app_resignation= new application_resignation();
                return app_resignation;
                default:
                    return null;
        }

    }

    @Override
    public int getCount() {
        return number_of_columns;
    }
}
