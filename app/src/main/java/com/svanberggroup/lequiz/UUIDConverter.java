package com.svanberggroup.lequiz;

import androidx.room.TypeConverter;

import java.util.UUID;

public class UUIDConverter {
    @TypeConverter
    public static String toString(UUID id) {
        return id.toString();
    }

    @TypeConverter
    public static UUID toUUID(String id) {
        return UUID.fromString(id);
    }
}
