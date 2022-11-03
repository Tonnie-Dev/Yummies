package com.uxstate.yummies.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.uxstate.yummies.data.local.entities.CategoryEntity
import com.uxstate.yummies.data.local.entities.MealEntity
import com.uxstate.yummies.data.local.entities.StarredMealsEntity
import com.uxstate.yummies.data.local.type_converters.Converters

@Database(entities = [CategoryEntity::class, MealEntity::class, StarredMealsEntity::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class YummiesDatabase : RoomDatabase() {

    abstract val dao: YummiesDAO
}