package com.svanberggroup.lequiz.Fragements;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.svanberggroup.lequiz.Models.Quiz;
import com.svanberggroup.lequiz.R;
import com.svanberggroup.lequiz.ViewModels.QuizViewModel;

public class EditQuizFragment extends Fragment {

    /**
     * Required interface for hosting activities
     */
    public interface Callbacks {
        void onQuizUpdated(Quiz quiz);
    }

    private EditText mQuizTitleField;

    private QuizViewModel mQuizViewModel;
    private Quiz mQuiz;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mQuizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        
        mQuiz = new Quiz();
        mQuizViewModel.insert(mQuiz);



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

        mQuizTitleField = (EditText) view.findViewById(R.id.quiz_title);
        mQuizTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mQuiz.setTitle(charSequence.toString());
                mQuizViewModel.update(mQuiz);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;
    }
}
