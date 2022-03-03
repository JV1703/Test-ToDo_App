package com.example.todoapp.data

import androidx.room.TypeConverter

class Converter {

    @TypeConverter /*this annotation is used to let ROOM know the function is for type conversion*/
    fun fromPriority(priority: Priority): String {
        return priority.name
    }

    @TypeConverter
    fun toPriority(priority: String): Priority {
        return Priority.valueOf(priority)
    }
}