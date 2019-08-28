package com.svanberggroup.lequiz;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class QuizViewModel extends AndroidViewModel {

    private QuizRepository mQuizRepository;

    private LiveData<List<Quiz>> mAllQuizzes;

    public QuizViewModel(@NonNull Application application) {
        super(application);

        mQuizRepository = new QuizRepository(application);
        mAllQuizzes = mQuizRepository.getAllQuizzes();

    }

    public LiveData<List<Quiz>> getAllQuizzes() {
        return mAllQuizzes;
    }
}
