package com.svanberggroup.lequiz.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.svanberggroup.lequiz.Models.Answer;
import com.svanberggroup.lequiz.Models.Question;

import java.util.List;

@Dao
public interface AnswerDao {
    @Query("SELECT * FROM answer_table")
    LiveData<List<Answer>> getAllAnswers();

    @Query("SELECT * FROM answer_table WHERE question_id LIKE (:questionId)")
    LiveData<List<Answer>> getAnswers(String questionId);

    @Insert
    void insert(Answer answer);

    @Delete
    void delete(Answer answer);

    @Update
    void update(Answer answer);
}
