package com.svanberggroup.lequiz.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.svanberggroup.lequiz.Models.Question;


import java.util.List;


public class QuestionRepository {
    private QuestionDao mQuestionDao;
    private LiveData<List<Question>> mAllQuestions;

    public QuestionRepository(Application application) {
        QuizRoomDatabase db = QuizRoomDatabase.getDatabase(application);
        mQuestionDao = db.questionDao();
        mAllQuestions = mQuestionDao.getAllQuestions();
    }

    public LiveData<List<Question>> getAllQuestions() {
        return mAllQuestions;
    }

    public LiveData<List<Question>> getQuestions(String quizId) {
        return mQuestionDao.getQuestions(quizId);
    }

    public void delete(Question question) {
        new deleteAsyncTask(mQuestionDao).execute(question);
    }
    private static class deleteAsyncTask extends AsyncTask<Question, Void, Void> {

        private QuestionDao mAsyncTaskDao;

        public deleteAsyncTask(QuestionDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(Question... questions) {
            mAsyncTaskDao.delete(questions[0]);
            return null;
        }
    }



    public void insert(Question question) {
        new insertAsyncTask(mQuestionDao).execute(question);
    }
    private static class insertAsyncTask extends AsyncTask<Question, Void, Void> {
        private QuestionDao mAsyncTaskDao;

        public insertAsyncTask(QuestionDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Question... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }




    public void update(Question question) {
        new updateAsyncTask(mQuestionDao).execute(question);
    }
    private static class updateAsyncTask extends AsyncTask<Question, Void, Void> {
        private QuestionDao mAsyncTaskDao;

        public updateAsyncTask(QuestionDao dao) {mAsyncTaskDao = dao; }

        @Override
        protected Void doInBackground(Question... questions) {
            mAsyncTaskDao.update(questions[0]);
            return null;
        }
    }
}
