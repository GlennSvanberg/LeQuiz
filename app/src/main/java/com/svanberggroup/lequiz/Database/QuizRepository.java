package com.svanberggroup.lequiz.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.svanberggroup.lequiz.Models.Quiz;

import java.util.List;
import java.util.UUID;

public class QuizRepository {
    private QuizDao mQuizDao;
    private LiveData<List<Quiz>> mAllQuizzes;

    public QuizRepository(Application application) {
        QuizRoomDatabase db = QuizRoomDatabase.getDatabase(application);
        mQuizDao = db.quizDao();
        mAllQuizzes = mQuizDao.getAllQuizzes();
    }

    public LiveData<List<Quiz>> getAllQuizzes() {
        return mAllQuizzes;
    }

    public LiveData<Quiz> getQuiz(String quizId) {
        return mQuizDao.getQuiz(quizId);
    }

    public void delete(Quiz quiz) {
        new deleteAsyncTask(mQuizDao).execute(quiz);
    }

    private static class deleteAsyncTask extends AsyncTask<Quiz, Void, Void>{

        private QuizDao mAsyncTaskDao;

        public deleteAsyncTask(QuizDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(Quiz... quizzes) {
            mAsyncTaskDao.delete(quizzes[0]);
            return null;
        }
    }



    public void insert(Quiz quiz) {
        new insertAsyncTask(mQuizDao).execute(quiz);
    }
    private static class insertAsyncTask extends AsyncTask<Quiz, Void, Void> {
        private QuizDao mAsyncTaskDao;

        public insertAsyncTask(QuizDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Quiz... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }




    public void update(Quiz quiz) {
        new updateAsyncTask(mQuizDao).execute(quiz);
    }
    private static class updateAsyncTask extends AsyncTask<Quiz, Void, Void> {
        private QuizDao mAsyncTaskDao;

                public updateAsyncTask(QuizDao dao) {mAsyncTaskDao = dao; }

        @Override
        protected Void doInBackground(Quiz... quizzes) {
            mAsyncTaskDao.update(quizzes[0]);
            return null;
        }
    }
}
