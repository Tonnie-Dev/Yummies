package com.uxstate.yummies.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.uxstate.yummies.data.local.entities.CategoryEntity
import com.uxstate.yummies.data.local.entities.MealEntity
import com.uxstate.yummies.data.local.entities.StarredMealEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface YummiesDAO {

    // MEALS dao functions
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeals(meals: List<MealEntity>)

    @Query(
        """
        SELECT * FROM meals_table
WHERE 
LOWER(name) LIKE '%' || LOWER (:query)|| '%'

    """
    )
    suspend fun getMealItems(query: String): List<MealEntity>

    @Query("SELECT * FROM meals_table WHERE category LIKE :category")
    fun getMealsByCategory(category: String): Flow<List<MealEntity>>

   // @Query("UPDATE meals_table SET isFavorite =:newStarStatus WHERE id =:id")
   // suspend fun updateStarStatus(id: Int, newStarStatus: Boolean)

    @Query("DELETE FROM meals_table")
    suspend fun clearMeals()

    // CATEGORIES dao functions
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoryEntity>)

    @Query("SELECT * FROM categories_table")
    suspend fun getCategoriesItems(): List<CategoryEntity>

    @Query("DELETE FROM categories_table")
    suspend fun clearCategories()

    // STAR meals dao functions
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStarredMeal(meal: StarredMealEntity)

    @Query("SELECT * FROM starred_meals_table WHERE id =:id")
    suspend fun getStarredMealById(id: Int): StarredMealEntity?

    @Query("SELECT EXISTS (SELECT 1 FROM starred_meals_table WHERE id =:id)")
    fun checkStarStatus(id: Int): Flow<Boolean>

    @Delete
    suspend fun removeFromStarredMeals(meal: StarredMealEntity)

    @Query("SELECT * FROM starred_meals_table")
    fun getAllStarredMeals(): Flow<List<StarredMealEntity>>
}