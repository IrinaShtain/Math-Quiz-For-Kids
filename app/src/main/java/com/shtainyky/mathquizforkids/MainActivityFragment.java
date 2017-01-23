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

import com.shtainyky.mathquizforkids.utils.Constants;
import com.shtainyky.mathquizforkids.utils.SettingsPreferences;

import java.util.Random;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "MathQuiz Activity";
    private static final int QUESTIONS_IN_QUIZ = 10;
    Random random = new Random();
    private Animation shakeAnimation;
    String operation;
    int tillNumber;
    int number_one;
    int number_two;
    int result;
    String string_result;


    TextView firstNumber;
    TextView secondNumber;
    TextView sign;
    TextView questionNumberTextView;

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
        questionNumberTextView = (TextView) view.findViewById(R.id.questionNumberTextView);

        operation = SettingsPreferences.getStoredPosition(getContext());
        tillNumber = SettingsPreferences.getStoredPositionSwitchNumber(getContext());

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

        generateQuiz();

        shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.incorrect_shake);
        shakeAnimation.setRepeatCount(3);


        return view;

    }

    @Override
    public void onStart() {
        operation = SettingsPreferences.getStoredPosition(getContext());
        tillNumber = SettingsPreferences.getStoredPositionSwitchNumber(getContext());
        generateQuiz();
        super.onStart();
    }

    @Override
    public void onClick(View v) {

    }

    private void generateQuiz() {
        for (int i = 1; i <= QUESTIONS_IN_QUIZ; i++) {
            questionNumberTextView.setText(getResources().getString(R.string.question, i, QUESTIONS_IN_QUIZ));
            generateQuestion();
        }
    }

    private void generateQuestion() {
        switch (operation) {
            case Constants.ADDING:
                adding();
                break;
            case Constants.SUBTRACTION:
                subtraction();
                break;
            case Constants.ADDING_OR_SUBTRACTION:
                if (random.nextBoolean())
                    adding();
                else
                    subtraction();
                break;
            case Constants.MULTIPLICATION:
                multiplication();
                break;
            case Constants.DIVISION:
                division();
                break;
            case Constants.MULTIPLICATION_OR_DIVISION:
                if (random.nextBoolean())
                    multiplication();
                else
                    division();
                break;
            case Constants.INEQUALITIES:
                break;


        }
    }

    private void setNumberAndSign(int firstNum, int secondNum, String newSign) {
        firstNumber.setText(String.valueOf(firstNum));
        secondNumber.setText(String.valueOf(secondNum));
        sign.setText(newSign);
    }

    private void randomNumbersForMultAndDiv(int number) {
        number_one = random.nextInt(number);
        number_two = random.nextInt(number);
    }

    private void randomNumbersForAddAndSub(int number) {
        number_one = random.nextInt(number + 1);
        number_two = random.nextInt(number - number_one + 1);
    }

    private void adding() {
        randomNumbersForAddAndSub(tillNumber);
        setNumberAndSign(number_one, number_two, "+");
        result = number_one + number_two;
    }

    private void subtraction() {
        randomNumbersForAddAndSub(tillNumber);
        if (number_one > number_two) {
            result = number_one - number_two;
            setNumberAndSign(number_one, number_two, "-");
        } else {
            result = number_two - number_one;
            setNumberAndSign(number_two, number_one, "-");
        }
    }

    private void multiplication() {
        if (tillNumber != 11) {
            randomNumbersForMultAndDiv(tillNumber + 1);
            setNumberAndSign(number_one, tillNumber, "*");
            result = number_one * tillNumber;
        } else {
            randomNumbersForMultAndDiv(tillNumber);
            setNumberAndSign(number_one, number_two, "*");
            result = number_one * number_two;
        }
    }

    private void division() {
        if (tillNumber != 11) {
            randomNumbersForMultAndDiv(tillNumber + 1);
            setNumberAndSign(number_one * tillNumber, tillNumber, "/");
            result = number_one;
        } else {
            randomNumbersForMultAndDiv(tillNumber);
            setNumberAndSign(number_one * number_two, number_two, "/");
            result = number_one;
        }
    }

    private void inequalities() {
        randomNumbersForMultAndDiv(tillNumber + 1);
        setNumberAndSign(number_one, number_two, "?");
        if (number_one > number_two)
            string_result = ">";
        else if (number_one < number_two)
            string_result = ">";
        else
            string_result = "=";

    }


}
