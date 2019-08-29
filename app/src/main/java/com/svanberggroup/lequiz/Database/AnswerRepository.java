package com.svanberggroup.lequiz.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.svanberggroup.lequiz.Models.Answer;
import com.svanberggroup.lequiz.Models.Question;

import java.util.List;

public class AnswerRepository {

    private AnswerDao mAnswerDao;
    private LiveData<List<Answer>> mAllAnswers;

    public AnswerRepository(Application application) {
        QuizRoomDatabase db = QuizRoomDatabase.getDatabase(application);
        mAnswerDao = db.answerDao();
        mAllAnswers = mAnswerDao.getAllAnswers();
    }


    public LiveData<List<Answer>> getAllAnswers() {
        return mAllAnswers;
    }


    public LiveData<List<Answer>> getAnswers(String questionId) {
        return mAnswerDao.getAnswers(questionId);
    }


    public void delete(Answer answer) {
        new AnswerRepository.deleteAsyncTask(mAnswerDao).execute(answer);
    }
    private static class deleteAsyncTask extends AsyncTask<Answer, Void, Void> {
        private AnswerDao mAsyncTaskDao;
        public deleteAsyncTask(AnswerDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(Answer... answers) {
            mAsyncTaskDao.delete(answers[0]);
            return null;
        }
    }



    public void insert(Answer answer) {
        new AnswerRepository.insertAsyncTask(mAnswerDao).execute(answer);
    }
    private static class insertAsyncTask extends AsyncTask<Answer, Void, Void> {
        private AnswerDao mAsyncTaskDao;
        public insertAsyncTask(AnswerDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Answer... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }


    public void update(Answer answer) {
        new AnswerRepository.updateAsyncTask(mAnswerDao).execute(answer);
    }
    private static class updateAsyncTask extends AsyncTask<Answer, Void, Void> {
        private AnswerDao mAsyncTaskDao;
        public updateAsyncTask(AnswerDao dao) {mAsyncTaskDao = dao; }
        @Override
        protected Void doInBackground(Answer... answers) {
            mAsyncTaskDao.update(answers[0]);
            return null;
        }
    }
}

