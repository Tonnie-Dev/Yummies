package com.uxstate.yummies.domain.repository

import com.uxstate.yummies.domain.model.Category
import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.util.Resource
import kotlinx.coroutines.flow.Flow

interface YummiesRepository {

    fun fetchMealItems(query: String, fetchFromRemote: Boolean): Flow<Resource<List<Meal>>>
    fun fetchCategoriesItems(): Flow<Resource<List<Category>>>
}