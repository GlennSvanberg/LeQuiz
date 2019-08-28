package com.svanberggroup.lequiz;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Quiz.class}, version = 1)
public abstract class QuizRoomDatabase extends RoomDatabase {
    public abstract QuizDao quizDao();

    private static volatile QuizRoomDatabase INSTANCE;

    public static QuizRoomDatabase getDatabase(Context context){
        if(INSTANCE == null) {
            synchronized (QuizRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), QuizRoomDatabase.class, "quiz_database").addCallback(SRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback SRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final QuizDao mDao;

        PopulateDbAsync(QuizRoomDatabase db) {
            mDao = db.quizDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }
    }

}
