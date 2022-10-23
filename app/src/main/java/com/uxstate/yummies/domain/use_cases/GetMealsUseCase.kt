package com.uxstate.yummies.domain.use_cases

import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.domain.repository.YummiesRepository
import com.uxstate.yummies.util.Resource
import kotlinx.coroutines.flow.Flow

class GetMealsUseCase(private val repository: YummiesRepository) {

     operator fun invoke(query: String, fetchFromRemote: Boolean): Flow<Resource<List<Meal>>> {

        return repository.fetchMealItems(query = query, fetchFromRemote = fetchFromRemote)
    }
}