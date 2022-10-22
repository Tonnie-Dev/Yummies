package com.uxstate.yummies.data.local.type_converters

import androidx.room.TypeConverter

class Converter {

    // Write @TypeConverter - convert List to a String

    @TypeConverter
    fun writeListIntoDatabase(list: List<String>): String {

        return list.joinToString(separator = ",")
    }

    // Read @TypeConverter - convert String to list

    @TypeConverter
    fun readListFromDatabase(roomString:String):List<String> {

        return roomString.split( ",").map { it }
    }
}