package com.svanberggroup.lequiz.Models;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Relation;
import androidx.room.TypeConverters;

import com.svanberggroup.lequiz.Database.UUIDConverter;

import java.util.List;
import java.util.UUID;

@Entity(tableName = "quiz_table")
public class Quiz {

    @PrimaryKey
    @NonNull
    @TypeConverters(UUIDConverter.class)
    @ColumnInfo(name = "id")
    private UUID mId;

    @ColumnInfo(name="title")
    private String mTitle;

    @Ignore
    private List<Question> mQuestions;

    public Quiz() {
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public List<Question> getQuestions() {
        return mQuestions;
    }
    public void setQuestions(List<Question> questions) {
        mQuestions = questions;
    }
    public void addQuestion(Question question) {
        mQuestions.add(question);
    }

}

