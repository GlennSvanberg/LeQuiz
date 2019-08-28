package com.svanberggroup.lequiz;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class QuizRepository {
    private LiveData<List<Quiz>> mAllQuizzes;

    public QuizRepository(Application application) {

    }
}
