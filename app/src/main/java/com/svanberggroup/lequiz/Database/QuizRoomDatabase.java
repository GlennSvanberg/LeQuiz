package com.svanberggroup.lequiz.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.svanberggroup.lequiz.Models.Question;
import com.svanberggroup.lequiz.Models.Quiz;

@Database(entities = {Quiz.class, Question.class}, version = 1)
public abstract class QuizRoomDatabase extends RoomDatabase {

    public abstract QuizDao quizDao();
    public abstract QuestionDao questionDao();

    private static volatile QuizRoomDatabase INSTANCE;

    public static QuizRoomDatabase getDatabase(Context context){
        if(INSTANCE == null) {
            synchronized (QuizRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), QuizRoomDatabase.class, "quiz_database").addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final QuizDao mQuizDao;
        private final QuestionDao mQuestionDao;

        PopulateDbAsync(QuizRoomDatabase db) {
            mQuizDao = db.quizDao();
            mQuestionDao = db.questionDao();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }
    }

}
