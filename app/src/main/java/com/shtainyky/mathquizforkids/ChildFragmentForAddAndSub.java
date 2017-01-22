package com.shtainyky.mathquizforkids;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

public class ChildFragmentForAddAndSub extends Fragment {
    View view;
    RadioGroup radioGroup;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.choosing_add_sub, null);
        radioGroup = (RadioGroup) view.findViewById(R.id.radiogroup_adding);
        listenForChanging();
        return view;
    }
    private void listenForChanging()
    {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.till_ten:
                        QueryPreferences.setStoredPositionSwitchNumber(getContext(), 10);
                        break;
                    case R.id.till_twenty:
                        QueryPreferences.setStoredPositionSwitchNumber(getContext(), 20);
                        break;
                    case R.id.till_hundred:
                        QueryPreferences.setStoredPositionSwitchNumber(getContext(), 100);
                        break;
                }
            }
        });
    }
}

