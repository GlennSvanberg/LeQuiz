package com.svanberggroup.lequiz;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class QuizViewModel extends AndroidViewModel {

    private List<Quiz> mAllQuizzes;

    public QuizViewModel(@NonNull Application application) {
        super(application);

        List<Quiz> tempQuizzes = new ArrayList<>();
        Quiz quiz = new Quiz();
        quiz.setTitle("TEST");
        tempQuizzes.add(quiz);

        quiz = new Quiz();
        quiz.setTitle("TEST 2");
        tempQuizzes.add(quiz);

        mAllQuizzes = tempQuizzes;
    }

    public List<Quiz> getAllQuizzes() {
        return mAllQuizzes;
    }
}
