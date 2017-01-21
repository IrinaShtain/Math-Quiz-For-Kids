package com.shtainyky.mathquizforkids;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

public class ChildFragmentForAddAndSub extends Fragment {
    View view;
    RadioGroup radioGroup;
    int number;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.choosing_add_sub, null);
        radioGroup = (RadioGroup) view.findViewById(R.id.radiogroup_adding);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.till_ten:
                        number = 10;
                    case R.id.till_twenty:
                        number = 20;
                    case R.id.till_hundred:
                        number = 100;
                }
            }
        });
        return view;
    }

    public int getTillNumber() {
        return number;
    }

}

