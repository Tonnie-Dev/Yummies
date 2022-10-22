package com.uxstate.yummies.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.uxstate.yummies.data.local.entity.CategoryEntity
import com.uxstate.yummies.data.local.entity.MealEntity


@Dao
interface YummiesDAO{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeals(meals:List<MealEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories:List<CategoryEntity>)

}