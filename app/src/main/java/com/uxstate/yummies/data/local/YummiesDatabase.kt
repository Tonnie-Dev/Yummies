package com.uxstate.yummies.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.uxstate.yummies.data.local.entity.CategoryEntity
import com.uxstate.yummies.data.local.entity.MealEntity
import com.uxstate.yummies.data.local.type_converters.Converters

@Database(entities = [CategoryEntity::class, MealEntity::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class YummiesDatabase : RoomDatabase() {

    abstract val dao: YummiesDAO
}