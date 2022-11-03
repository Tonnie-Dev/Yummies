package com.uxstate.yummies.domain.use_cases

import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.domain.repository.YummiesRepository

class UnStarUseCase(private val repository: YummiesRepository) {

    suspend operator fun invoke(meal: Meal) {

        repository.removeFromStarredMeals(meal)
    }
}