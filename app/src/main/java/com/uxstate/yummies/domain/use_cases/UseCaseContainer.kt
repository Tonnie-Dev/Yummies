package com.uxstate.yummies.domain.use_cases

data class UseCaseContainer(
    val getMealsUseCase: GetMealsUseCase,
    val getCategoriesUseCase: GetCategoriesUseCase
)