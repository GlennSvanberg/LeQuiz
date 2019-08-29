package com.svanberggroup.lequiz.Models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.svanberggroup.lequiz.Database.UUIDConverter;

import java.util.UUID;

@Entity(tableName = "question_table")
public class Question {

    @PrimaryKey
    @NonNull
    @TypeConverters(UUIDConverter.class)
    private UUID mId;

    @ColumnInfo(name = "title")
    private String mTitle;

    public Question() {
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

}

