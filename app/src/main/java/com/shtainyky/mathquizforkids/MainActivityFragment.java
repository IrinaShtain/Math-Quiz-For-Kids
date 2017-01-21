package com.shtainyky.mathquizforkids;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private static final String TAG = "MathQuiz Activity";
    private static final int QUESTIONS_IN_QUIZ = 10;
    private Animation shakeAnimation;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.incorrect_shake);
        shakeAnimation.setRepeatCount(3);

        return view;

    }

}
