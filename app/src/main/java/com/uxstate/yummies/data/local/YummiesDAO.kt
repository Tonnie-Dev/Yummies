package com.uxstate.yummies.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.uxstate.yummies.data.local.entity.CategoryEntity
import com.uxstate.yummies.data.local.entity.MealEntity

@Dao
interface YummiesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeals(meals: List<MealEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoryEntity>)

    @Query(
        """
        SELECT * FROM meals_table
WHERE 
LOWER(name) LIKE '%' || LOWER (:query)|| '%'

    """
    )
    suspend fun getMealItems(query: String): List<MealEntity>

    @Query("SELECT * FROM categories_table")
    suspend fun getCategoriesItems(): List<CategoryEntity>

    @Query("DELETE FROM meals_table")
    suspend fun clearMeals()

    @Query("DELETE FROM categories_table")
    suspend fun clearCategories()
}