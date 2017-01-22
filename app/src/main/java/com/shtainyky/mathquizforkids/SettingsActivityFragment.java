package com.shtainyky.mathquizforkids;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class SettingsActivityFragment extends Fragment implements View.OnClickListener {
    private View view;
    private TextView adding_text;
    private TextView subtraction_text;
    private TextView multiplication_text;
    private TextView division_text;
    private TextView adding_and_subtraction_text;
    private TextView multiplication_and_division_text;
    private TextView inequalities_text;
    Button letsPlayButton;
    String whereAddedFragment = "noFragment";
    boolean firstOperation = true;

    public SettingsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_settings, container, false);
        initializationViews();

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.adding_text:
                firstOperation = true;
                inflateFragment(0);
                QueryPreferences.setStoredPosition(getContext(), Constants.ADDING);
                break;
            case R.id.subtraction_text:
                firstOperation = true;
                inflateFragment(1);
                QueryPreferences.setStoredPosition(getContext(), Constants.SUBTRACTION);
                break;
            case R.id.adding_and_subtraction_text:
                firstOperation = true;
                inflateFragment(2);
                QueryPreferences.setStoredPosition(getContext(), Constants.ADDING_AND_SUBTRACTION);
                break;
            case R.id.inequalities_text:
                firstOperation = true;
                inflateFragment(3);
                QueryPreferences.setStoredPosition(getContext(), Constants.INEQUALITIES);
                break;
            case R.id.multiplication_text:
                firstOperation = false;
                inflateFragment(4);
                QueryPreferences.setStoredPosition(getContext(), Constants.MULTIPLICATION);
                break;
            case R.id.division_text:
                firstOperation = false;
                inflateFragment(5);
                QueryPreferences.setStoredPosition(getContext(), Constants.DIVISION);
                break;
            case R.id.multiplication_and_division_text:
                firstOperation = false;
                inflateFragment(6);
                QueryPreferences.setStoredPosition(getContext(), Constants.MULTIPLICATION_AND_DIVISION);
                break;
            case R.id.letsPlayButton:
                textForLetsPlayButton();
        }

    }

    private void textForLetsPlayButton() {
        String operation = QueryPreferences.getStoredPosition(getContext());
        int limit = QueryPreferences.getStoredPositionSwitchNumber(getContext());
        Resources resources = getResources();
        if (operation.equals(Constants.NOTHING))
            Toast.makeText(getContext(),
                    resources.getString(R.string.no_operation),
                    Toast.LENGTH_LONG).show();
        else if (limit == 0)
            Toast.makeText(getContext(),
                    resources.getString(R.string.no_limits),
                    Toast.LENGTH_LONG).show();
        else {
            String msg = resources.getString(R.string.choosen_setting) +
                    " " + operation;
            if (firstOperation)
                msg = msg + " " + resources.getString(R.string.till) + " " + limit;
            else
                msg = msg + " " + resources.getString(R.string.by) + " " + limit;
            Toast.makeText(getContext(),
                    msg, Toast.LENGTH_LONG).show();
        }
    }


    private void initializationViews() {
        letsPlayButton = (Button) view.findViewById(R.id.letsPlayButton);
        letsPlayButton.setOnClickListener(this);
        adding_text = (TextView) view.findViewById(R.id.adding_text);
        adding_text.setOnClickListener(this);
        subtraction_text = (TextView) view.findViewById(R.id.subtraction_text);
        subtraction_text.setOnClickListener(this);
        multiplication_text = (TextView) view.findViewById(R.id.multiplication_text);
        multiplication_text.setOnClickListener(this);
        division_text = (TextView) view.findViewById(R.id.division_text);
        division_text.setOnClickListener(this);
        adding_and_subtraction_text = (TextView) view.findViewById(R.id.adding_and_subtraction_text);
        adding_and_subtraction_text.setOnClickListener(this);
        multiplication_and_division_text = (TextView) view.findViewById(R.id.multiplication_and_division_text);
        multiplication_and_division_text.setOnClickListener(this);
        inequalities_text = (TextView) view.findViewById(R.id.inequalities_text);
        inequalities_text.setOnClickListener(this);
    }

    private void inflateFragment(int whichContainer) {
        QueryPreferences.setStoredPositionSwitchNumber(getContext(), 0);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment childFragment = new ChildFragmentForAddAndSub();
        int containerId = 0;
        FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
        switch (whereAddedFragment) {
            case "container_add":
                fragmentTransaction1.remove(getFragmentManager().findFragmentById(R.id.container_add));
                fragmentTransaction1.commit();
                break;
            case "container_sub":
                fragmentTransaction1.remove(getFragmentManager().findFragmentById(R.id.container_sub));
                fragmentTransaction1.commit();
                break;
            case "container_add_and_sub":
                fragmentTransaction1.remove(getFragmentManager().findFragmentById(R.id.container_add_and_sub));
                fragmentTransaction1.commit();
                break;
            case "container_inequalities":
                fragmentTransaction1.remove(getFragmentManager().findFragmentById(R.id.container_inequalities));
                fragmentTransaction1.commit();
                break;
            case "container_mult":
                fragmentTransaction1.remove(getFragmentManager().findFragmentById(R.id.container_mult));
                fragmentTransaction1.commit();
                break;
            case "container_div":
                fragmentTransaction1.remove(getFragmentManager().findFragmentById(R.id.container_div));
                fragmentTransaction1.commit();
                break;
            case "container_mult_and_div":
                fragmentTransaction1.remove(getFragmentManager().findFragmentById(R.id.container_mult_and_div));
                fragmentTransaction1.commit();
                break;
        }

        switch (whichContainer) {
            case 0:
                containerId = R.id.container_add;
                whereAddedFragment = "container_add";
                childFragment = new ChildFragmentForAddAndSub();
                break;
            case 1:
                containerId = R.id.container_sub;
                whereAddedFragment = "container_sub";
                childFragment = new ChildFragmentForAddAndSub();
                break;
            case 2:
                containerId = R.id.container_add_and_sub;
                whereAddedFragment = "container_add_and_sub";
                childFragment = new ChildFragmentForAddAndSub();
                break;
            case 3:
                containerId = R.id.container_inequalities;
                whereAddedFragment = "container_inequalities";
                childFragment = new ChildFragmentForAddAndSub();
                break;
            case 4:
                containerId = R.id.container_mult;
                whereAddedFragment = "container_mult";
                childFragment = new ChildFragmentForMultTables();
                break;
            case 5:
                containerId = R.id.container_div;
                whereAddedFragment = "container_div";
                childFragment = new ChildFragmentForMultTables();
                break;
            case 6:
                containerId = R.id.container_mult_and_div;
                whereAddedFragment = "container_mult_and_div";
                childFragment = new ChildFragmentForMultTables();
                break;

        }

        fragmentTransaction.add(containerId, childFragment);
        fragmentTransaction.commit();

    }


}
