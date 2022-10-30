package com.uxstate.yummies.presentation.screens.overview_screen.states

import com.uxstate.yummies.domain.model.Category

data class StateCategories(
    val isLoading: Boolean = false,
    val categories: List<Category> = emptyList(),
    val isShowCategories: Boolean = false
)