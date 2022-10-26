package com.uxstate.yummies.domain.use_cases

import com.uxstate.yummies.domain.repository.YummiesRepository

class GetCategorySearchWordUseCase(private val repository: YummiesRepository) {

    operator fun invoke(categoryId: Int): String {

        return repository.getCategorySearchWord(categoryId)
    }
}