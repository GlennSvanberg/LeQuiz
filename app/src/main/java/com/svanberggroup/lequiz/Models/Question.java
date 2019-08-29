package com.svanberggroup.lequiz.Models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.svanberggroup.lequiz.Database.UUIDConverter;

import java.util.UUID;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "question_table", foreignKeys = @ForeignKey(entity = Quiz.class, parentColumns = "id", childColumns = "quiz_id", onDelete = CASCADE))
public class Question {

    @PrimaryKey
    @NonNull
    @TypeConverters(UUIDConverter.class)
    @ColumnInfo(name = "id")
    private UUID mId;

    @NonNull
    @TypeConverters(UUIDConverter.class)
    @ColumnInfo(name = "quiz_id")
    private UUID mQuizId;

    @ColumnInfo(name = "title")
    private String mTitle;

    public Question(UUID quizId) {
        mId = UUID.randomUUID();
        mQuizId = quizId;
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

    public UUID getQuizId() {
        return mQuizId;
    }
    public void setQuizId(UUID quizId) {
        mQuizId = quizId;
    }
}

