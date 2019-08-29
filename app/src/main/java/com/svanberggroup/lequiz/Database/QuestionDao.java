package com.svanberggroup.lequiz.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.svanberggroup.lequiz.Models.Question;

import java.util.List;

@Dao
public interface QuestionDao {

    @Query("SELECT * FROM question_table")
    LiveData<List<Question>> getAllQuestions();

    @Query("SELECT * FROM question_table WHERE quiz_id LIKE :quizId")
    LiveData<List<Question>> getQuestions(String quizId);

    @Insert
    void insert(Question question);

    @Delete
    void delete(Question question);

    @Update
    void update(Question question);
}
