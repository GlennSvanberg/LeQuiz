package com.svanberggroup.lequiz.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.svanberggroup.lequiz.Models.Quiz;

import java.util.List;

@Dao
public interface QuizDao {

    @Query("SELECT * FROM quiz_table")
    LiveData<List<Quiz>> getAllQuizzes();

    @Query("SELECT * FROM quiz_table")
    LiveData<List<Quiz>> getQuizzes();


    @Query("SELECT * FROM quiz_table WHERE id IN (:quizIds)")
    LiveData<List<Quiz>> loadAllByIds(int[] quizIds);

    @Insert
    void insert(Quiz quiz);

    @Delete
    void delete(Quiz quiz);

    @Update
    void update(Quiz quiz);
}
