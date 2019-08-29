package com.svanberggroup.lequiz.Fragements;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.svanberggroup.lequiz.Activities.EditQuizActivity;
import com.svanberggroup.lequiz.Models.Quiz;
import com.svanberggroup.lequiz.R;
import com.svanberggroup.lequiz.ViewModels.QuizViewModel;

import java.util.List;
import java.util.UUID;

public class EditQuizFragment extends Fragment {


    private EditText mQuizTitleField;

    private QuizViewModel mQuizViewModel;
    private Quiz mQuiz;
    private UUID mQuizId;

    private static final String ARG_QUIZ_ID = "quiz_id";


    public static EditQuizFragment newInstance(UUID quizId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_QUIZ_ID, quizId);

        EditQuizFragment fragment = new EditQuizFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mQuizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);


        if(getArguments().getSerializable(ARG_QUIZ_ID) == null) {
            mQuiz = new Quiz();
            mQuizViewModel.insert(mQuiz);
        } else {
            mQuizId = (UUID) getArguments().getSerializable(ARG_QUIZ_ID);
        }


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_edit_quiz,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_quiz:

                mQuizViewModel.delete(mQuiz);
                Toast.makeText(getActivity(), mQuiz.getTitle() + " has been deleted", Toast.LENGTH_SHORT).show();
                getActivity().finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mQuizViewModel.update(mQuiz);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_quiz, container, false);
        mQuizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);

        mQuizViewModel.getAllQuizzes().observe(getActivity(), new Observer<List<Quiz>>() {
            @Override
            public void onChanged(List<Quiz> quizzes) {
                for(Quiz quiz : quizzes) {
                    if(quiz.getId().equals(mQuizId)) {
                        mQuiz = quiz;
                        updateUI();
                        return;
                    }
                }

            }
        });

        mQuizTitleField = (EditText) view.findViewById(R.id.quiz_title);
        mQuizTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mQuiz.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;
    }
    private void updateUI() {
        mQuizTitleField.setText(mQuiz.getTitle());
    }
}
