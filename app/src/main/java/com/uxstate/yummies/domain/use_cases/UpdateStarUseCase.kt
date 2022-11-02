package com.uxstate.yummies.domain.use_cases

import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.domain.repository.YummiesRepository

class UpdateStarUseCase(private val repository: YummiesRepository) {
    suspend operator fun invoke(meal: Meal, newStarStatus:Boolean){

        repository.updateStarStatus(
                meal = meal, newStarStatus = newStarStatus
        )
    }
}