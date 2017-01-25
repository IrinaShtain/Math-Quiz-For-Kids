package com.shtainyky.mathquizforkids;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.shtainyky.mathquizforkids.utils.Constants;
import com.shtainyky.mathquizforkids.utils.SettingsPreferences;

import java.text.NumberFormat;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "MathQuiz Activity";
    private static final int QUESTIONS_IN_QUIZ = 5;
    private Random random = new Random();
    private Animation shakeAnimation;
    private String operation;
    private int tillNumber;
    private int questionNumber = 1;
    private int buttonNumber;
    private int number_one;
    private int number_two;
    private int result;
    private int countWrongAnswer;
    private int countRightAnswer;
    private String string_result;
    boolean hasWrightAnswer = false;


    private TextView firstNumber;
    private TextView secondNumber;
    private TextView sign;
    private TextView questionNumberTextView;
    private TextView answerTextView;

    private Button button_zero;
    private Button button_one;
    private Button button_two;
    private Button button_three;
    private Button button_four;
    private Button button_five;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setRetainInstance(true);
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        questionNumberTextView = (TextView) view.findViewById(R.id.questionNumberTextView);
        answerTextView = (TextView) view.findViewById(R.id.answerTextView);


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

        firstQuestion();
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.incorrect_shake);
        shakeAnimation.setRepeatCount(3);


        return view;

    }

    @Override
    public void onStart() {
        firstQuestion();
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        if (!operation.equals(Constants.INEQUALITIES))
            hasWrightAnswer = ((Button) v).getText().equals(String.valueOf(result));
        else
            hasWrightAnswer = ((Button) v).getText().equals(String.valueOf(string_result));
        if (hasWrightAnswer) {
            if (countWrongAnswer == 0) countRightAnswer++;
            Toast.makeText(getContext(), R.string.correct_answer,
                    Toast.LENGTH_SHORT).show();
            questionNumberTextView.setText(getResources().getString(R.string.question, questionNumber++, QUESTIONS_IN_QUIZ));
            generateQuestion();
        } else {
            v.setEnabled(false);
            countWrongAnswer++;
            answerTextView.setText(R.string.incorrect_answer);
            answerTextView.setTextColor(getResources().getColor(R.color.incorrect_answer));
        }
        if (questionNumber > QUESTIONS_IN_QUIZ) {
            questionNumber = 1;
            clearData();
            showDialog();
        }


    }

    private void showDialog() {
        double percent = countRightAnswer * 100 / QUESTIONS_IN_QUIZ;
        String msg = getResources().getString(R.string.quiz_result_part) + " " + percent + " % "
                + getResources().getString(R.string.quiz_result);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getResources().getString(R.string.reset_quiz))
                .setMessage(msg)
                .setCancelable(false)
                .setNegativeButton(getResources().getString(R.string.choose_reset),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SettingsPreferences.textForLetsPlayButton(getContext());
                                firstQuestion();
                            }
                        })
                .setPositiveButton(getResources().getString(R.string.choose_setting),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent preferencesIntent = new Intent(getActivity(), SettingsActivity.class);
                                startActivity(preferencesIntent);
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void clearData() {
        button_zero.setText("");
        button_one.setText("");
        button_two.setText("");
        button_three.setText("");
        button_four.setText("");
        button_five.setText("");
        firstNumber.setText("");
        secondNumber.setText("");
        sign.setText("");
        answerTextView.setText("");

    }

    private void generateQuestion() {
        answerTextView.setText("");
        countWrongAnswer = 0;
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
                inequalities();
                break;
        }
        setUpButtons();


    }

    private void firstQuestion() {
        countWrongAnswer = 0;
        operation = SettingsPreferences.getStoredPosition(getContext());
        tillNumber = SettingsPreferences.getStoredPositionSwitchNumber(getContext());
        questionNumberTextView.setText(getResources().getString(R.string.question, 1, QUESTIONS_IN_QUIZ));
        generateQuestion();
    }

    private void setNumberAndSign(int firstNum, int secondNum, String newSign) {
        firstNumber.setText(String.valueOf(firstNum));
        secondNumber.setText(String.valueOf(secondNum));
        sign.setText(newSign);
    }

    private void randomNumbersForMultAndDiv(int number) {
        number_one = random.nextInt(number);
        number_two = random.nextInt(number);
        buttonNumber = number * 10;
    }

    private void randomNumbersForAddAndSub(int number) {
        number_one = random.nextInt(number + 1);
        number_two = random.nextInt(number - number_one + 1);
        buttonNumber = number;
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
            if (number_two == 0) number_two = 5;
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
            string_result = "<";
        else
            string_result = "=";

    }

    private void setUpButtons() {
        button_one.setEnabled(true);
        button_two.setEnabled(true);
        button_zero.setEnabled(true);
        if (!operation.equals(Constants.INEQUALITIES)) {
            Set<Integer> set = new HashSet<>();
            set.add(result);
            while (set.size() <= 5) {
                set.add(random.nextInt(buttonNumber));
            }
            int[] randoms = new int[6];
            int i = 0;
            for (Integer integ : set) {
                randoms[i] = integ;
                i++;
            }
            button_zero.setText(String.valueOf(randoms[0]));
            button_one.setText(String.valueOf(randoms[1]));
            button_two.setText(String.valueOf(randoms[2]));
            button_three.setText(String.valueOf(randoms[3]));
            button_four.setText(String.valueOf(randoms[4]));
            button_five.setText(String.valueOf(randoms[5]));
            button_three.setEnabled(true);
            button_four.setEnabled(true);
            button_five.setEnabled(true);

        } else {
            button_zero.setText("=");
            button_one.setText(">");
            button_two.setText("<");
            button_three.setText("");
            button_four.setText("");
            button_five.setText("");
            button_three.setEnabled(false);
            button_four.setEnabled(false);
            button_five.setEnabled(false);
        }

    }


}
