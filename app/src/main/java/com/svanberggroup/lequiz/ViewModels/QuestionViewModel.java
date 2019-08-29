package com.svanberggroup.lequiz.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.svanberggroup.lequiz.Database.QuestionRepository;
import com.svanberggroup.lequiz.Database.QuizRepository;
import com.svanberggroup.lequiz.Models.Question;
import com.svanberggroup.lequiz.Models.Quiz;

import java.util.List;

public class QuestionViewModel extends AndroidViewModel {
    private QuestionRepository mQuestionRepository;

    private LiveData<List<Question>> mAllQuestions;

    public QuestionViewModel(@NonNull Application application) {
        super(application);

        mQuestionRepository = new QuestionRepository(application);
        mAllQuestions = mQuestionRepository.getAllQuestions();

    }

    public LiveData<List<Question>> getAllQuestions() {
        return mAllQuestions;
    }


    public void insert(Question question) {
        mQuestionRepository.insert(question);
    }

    public void update(Question question) {
        mQuestionRepository.update(question);
    }

    public void delete(Question question) {
        mQuestionRepository.delete(question);
    }
}
