package com.svanberggroup.lequiz;

import androidx.room.Database;
import androidx.room.RoomDatabase;



@Database(entities = {Quiz.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract QuizDao quizDao();
}

