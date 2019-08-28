package com.svanberggroup.lequiz.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.svanberggroup.lequiz.Models.Quiz;

import java.util.List;

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

}
