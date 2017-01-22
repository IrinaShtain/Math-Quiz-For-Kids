package com.shtainyky.mathquizforkids;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "MathQuiz Activity";
    private static final int QUESTIONS_IN_QUIZ = 10;
    private Animation shakeAnimation;
    int operationType = 1;
    int number_one;
    int number_two;


    TextView firstNumber;
    TextView secondNumber;
    TextView sign;

    Button button_zero;
    Button button_one;
    Button button_two;
    Button button_three;
    Button button_four;
    Button button_five;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        sign = (TextView) view.findViewById(R.id.sign);
        firstNumber = (TextView) view.findViewById(R.id.num_first);
        secondNumber = (TextView) view.findViewById(R.id.num_second);

        button_zero = (Button) view.findViewById(R.id.button_zero);
        button_one = (Button) view.findViewById(R.id.button_one);
        button_two = (Button) view.findViewById(R.id.button_two);
        button_three = (Button) view.findViewById(R.id.button_three);
        button_four = (Button) view.findViewById(R.id.button_four);
        button_five = (Button) view.findViewById(R.id.button_five);

        button_zero.setOnClickListener(this);
        button_one.setOnClickListener(this);
        button_two.setOnClickListener(this);
        button_three.setOnClickListener(this);
        button_four.setOnClickListener(this);
        button_five.setOnClickListener(this);

        shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.incorrect_shake);
        shakeAnimation.setRepeatCount(3);


        return view;

    }

    @Override
    public void onClick(View v) {

    }
    private  void generateQuiz()
    {

    }

}
