package com.uxstate.yummies.domain.use_cases

import com.uxstate.yummies.domain.model.Category
import com.uxstate.yummies.domain.repository.YummiesRepository
import com.uxstate.yummies.util.Resource
import kotlinx.coroutines.flow.Flow

class GetCategoriesUseCase(private val repository: YummiesRepository) {

    operator fun invoke(): Flow<Resource<List<Category>>> {

        return repository.fetchCategoriesItems()
    }
}