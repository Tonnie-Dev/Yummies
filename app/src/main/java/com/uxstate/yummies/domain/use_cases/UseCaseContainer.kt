package com.uxstate.yummies.domain.use_cases

data class UseCaseContainer(
    val getMealsUseCase: GetMealsUseCase,
    val getCategoriesUseCase: GetCategoriesUseCase,
    val getMealsByCategoryUseCase: GetMealsByCategoryUseCase,
    val updateStarUseCase: UpdateStarUseCase,
    val starUseCase: StarUseCase,
    val unStarUseCase: UnStarUseCase,
    val checkStarredStatusUseCase: CheckStarredStatusUseCase
)