package com.svanberggroup.lequiz.Fragements;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.svanberggroup.lequiz.Models.Quiz;
import com.svanberggroup.lequiz.R;
import com.svanberggroup.lequiz.ViewModels.QuizViewModel;

import java.util.UUID;
import java.util.zip.Inflater;

public class PlayQuizFragment extends Fragment {

    private Quiz mQuiz;

    private static final String ARG_QUIZ_ID = "quiz_id";

    private QuizViewModel mQuizViewModel;

    private TextView textVeiw;


    public static PlayQuizFragment newInstance(UUID quizId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_QUIZ_ID, quizId);

        PlayQuizFragment fragment = new PlayQuizFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mQuizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);


        if(getArguments().getSerializable(ARG_QUIZ_ID) != null) {
            mQuiz = new Quiz();
            mQuiz.setId((UUID) getArguments().getSerializable(ARG_QUIZ_ID));

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play_quiz, container, false);

        textVeiw = view.findViewById(R.id.textView);


        mQuizViewModel.getQuiz(mQuiz.getId().toString()).observe(getActivity(), new Observer<Quiz>() {
            @Override
            public void onChanged(Quiz quiz) {
                mQuiz = quiz;
                textVeiw.setText(mQuiz.getTitle());
            }
        });

        return view;
    }
}
