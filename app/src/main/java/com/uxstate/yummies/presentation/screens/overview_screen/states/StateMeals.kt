package com.uxstate.yummies.presentation.screens.overview_screen.states

import com.uxstate.yummies.domain.model.Meal

data class StateMeals(
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val meals: List<Meal> = emptyList(),
    val fetchFromRemote:Boolean = false
) {
}