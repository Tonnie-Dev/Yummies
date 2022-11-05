package com.uxstate.yummies.domain.repository

import com.uxstate.yummies.domain.model.Category
import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.util.Resource
import kotlinx.coroutines.flow.Flow

interface YummiesRepository {

    // Meals functions
    fun fetchMealItems(query: String, fetchFromRemote: Boolean): Flow<Resource<List<Meal>>>
    fun getWordByCategory(category: String): Flow<List<Meal>>
    suspend fun updateStarStatus(meal: Meal, newStarStatus: Boolean)

    // Categories function
    fun fetchCategoriesItems(): Flow<Resource<List<Category>>>

    // Starred Meals function
    suspend fun removeFromStarredMeals(meal: Meal)
    suspend fun insertStarredMeal(meal: Meal)
    fun checkStarredStatus(meal: Meal): Flow<Boolean>
    fun getStarredMeals():Flow<List<Meal>>
}