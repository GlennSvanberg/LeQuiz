package com.svanberggroup.lequiz.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.svanberggroup.lequiz.Database.AnswerRepository;
import com.svanberggroup.lequiz.Models.Answer;

import java.util.List;

public class AnswerViewModel extends AndroidViewModel {

    private AnswerRepository mAnswerRepository;

    private LiveData<List<Answer>> mAllAnswers;

    public AnswerViewModel(@NonNull Application application) {
        super(application);

        mAnswerRepository = new AnswerRepository(application);
        mAllAnswers = mAnswerRepository.getAllAnswers();
    }

    public LiveData<List<Answer>> getAllAnswers() {
        return mAllAnswers;
    }

    public LiveData<List<Answer>> getAnswers(String questionId) {

        return mAnswerRepository.getAnswers(questionId);
    }

    public void insert(Answer answer) {
        mAnswerRepository.insert(answer);
    }

    public void update(Answer answer) {
        mAnswerRepository.update(answer);
    }

    public void update(List<Answer> answers) {
        mAnswerRepository.update(answers);
    }

    public void delete(Answer answer) {
        mAnswerRepository.delete(answer);
    }
}

