package com.uxstate.yummies.domain.use_cases

import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.domain.repository.YummiesRepository
import kotlinx.coroutines.flow.Flow

class GetStarredMeals(private val repository: YummiesRepository) {

    operator fun invoke(): Flow<List<Meal>> {

        return repository.getStarredMeals()
    }
}