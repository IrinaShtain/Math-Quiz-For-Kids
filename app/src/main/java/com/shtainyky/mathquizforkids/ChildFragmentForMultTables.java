package com.shtainyky.mathquizforkids;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

public class ChildFragmentForMultTables extends Fragment {
    View view;
    int tableNumber;
    private RadioGroup radioGroup;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.chosing_mult_div, null);
        radioGroup = (RadioGroup) view.findViewById(R.id.radiogroup_mult);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.second_table:
                        tableNumber = 2;
                    case R.id.third_table:
                        tableNumber = 3;
                    case R.id.fourth_table:
                        tableNumber = 4;
                    case R.id.fiveth_table:
                        tableNumber = 5;
                    case R.id.sixth_table:
                        tableNumber = 6;
                    case R.id.seventh_table:
                        tableNumber = 7;
                    case R.id.eightth_table:
                        tableNumber = 8;
                    case R.id.nineth_table:
                        tableNumber = 9;
                    case R.id.tenth_table:
                        tableNumber = 10;
                }
            }
        });
        return view;

    }

    public int getTableNumber() {
        return tableNumber;
    }

}
