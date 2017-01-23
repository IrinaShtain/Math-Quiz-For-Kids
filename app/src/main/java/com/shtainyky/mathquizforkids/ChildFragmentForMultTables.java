package com.shtainyky.mathquizforkids;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.shtainyky.mathquizforkids.utils.SettingsPreferences;

public class ChildFragmentForMultTables extends Fragment {
    View view;
    private Spinner spinner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.chosing_mult_div, null);
        spinner = (Spinner) view.findViewById(R.id.spinner);
        String[] strings = {
                getActivity().getResources().getString(R.string.second_table),
                getActivity().getResources().getString(R.string.third_table),
                getActivity().getResources().getString(R.string.fourth_table),
                getActivity().getResources().getString(R.string.fiveth_table),
                getActivity().getResources().getString(R.string.sixth_table),
                getActivity().getResources().getString(R.string.seventh_table),
                getActivity().getResources().getString(R.string.eightth_table),
                getActivity().getResources().getString(R.string.nineth_table),
                getActivity().getResources().getString(R.string.tenth_table),
                getActivity().getResources().getString(R.string.random_table)
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, strings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        SettingsPreferences.setStoredPositionSwitchNumber(getContext(), 2);
                        break;
                    case 1:
                        SettingsPreferences.setStoredPositionSwitchNumber(getContext(), 3);
                        break;
                    case 2:
                        SettingsPreferences.setStoredPositionSwitchNumber(getContext(), 4);
                        break;
                    case 3:
                        SettingsPreferences.setStoredPositionSwitchNumber(getContext(), 5);
                        break;
                    case 4:
                        SettingsPreferences.setStoredPositionSwitchNumber(getContext(), 6);
                        break;
                    case 5:
                        SettingsPreferences.setStoredPositionSwitchNumber(getContext(), 7);
                        break;
                    case 6:
                        SettingsPreferences.setStoredPositionSwitchNumber(getContext(), 8);
                        break;
                    case 7:
                        SettingsPreferences.setStoredPositionSwitchNumber(getContext(), 9);
                        break;
                    case 8:
                        SettingsPreferences.setStoredPositionSwitchNumber(getContext(), 10);
                        break;
                    case 9:
                        SettingsPreferences.setStoredPositionSwitchNumber(getContext(), 11);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;

    }
}
