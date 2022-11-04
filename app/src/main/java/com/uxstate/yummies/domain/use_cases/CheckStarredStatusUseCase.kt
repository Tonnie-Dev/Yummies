package com.uxstate.yummies.domain.use_cases

import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.domain.repository.YummiesRepository
import kotlinx.coroutines.flow.Flow


class CheckStarredStatusUseCase(private val repository: YummiesRepository) {

    operator fun invoke(meal: Meal): Flow<Boolean> {

        return repository.checkStarredStatus(meal)
    }
}