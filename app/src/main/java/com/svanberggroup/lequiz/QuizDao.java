package com.svanberggroup.lequiz;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuizDao {

    @Query("SELECT * FROM quiz")
    List<Quiz> getAll();

    @Query("SELECT * FROM quiz WHERE mid IN (:quizIds)")
    List<Quiz> loadAllByIds(int[] quizIds);

    @Insert
    void insertAll(Quiz... quizzes);

    @Delete
    void delete(Quiz quiz);
}
