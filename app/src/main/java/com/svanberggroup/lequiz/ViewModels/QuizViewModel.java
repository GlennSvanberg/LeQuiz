package com.svanberggroup.lequiz.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.svanberggroup.lequiz.Database.QuizRepository;
import com.svanberggroup.lequiz.Models.Quiz;

import java.util.List;
import java.util.UUID;


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

    public LiveData<Quiz> getQuiz(String quizId) {
        return mQuizRepository.getQuiz(quizId);
    }


    public void insert(Quiz quiz) {
        mQuizRepository.insert(quiz);
    }

    public void update(Quiz quiz) {
        mQuizRepository.update(quiz);
    }

    public void delete(Quiz quiz) {
        mQuizRepository.delete(quiz);
    }
}
