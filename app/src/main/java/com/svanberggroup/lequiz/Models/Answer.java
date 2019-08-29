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

@Entity(tableName = "answer_table", foreignKeys = @ForeignKey(entity = Question.class, parentColumns = "id", childColumns = "question_id", onDelete = CASCADE))
public class Answer {

    @PrimaryKey
    @NonNull
    @TypeConverters(UUIDConverter.class)
    @ColumnInfo(name = "id")
    private UUID mId;

    @NonNull
    @TypeConverters(UUIDConverter.class)
    @ColumnInfo(name = "question_id")
    private UUID mQuestionId;

    @ColumnInfo(name = "title")
    private String mTitle;

    public Answer(UUID questionId) {
        mId = UUID.randomUUID();
        mQuestionId = questionId;
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

    public UUID getQuestionId() {
        return mQuestionId;
    }

    public void setQuestionId(UUID questionId) {
        mQuestionId = questionId;
    }
}
