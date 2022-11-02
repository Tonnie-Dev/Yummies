package com.uxstate.yummies.presentation.screens.details_screen.details_event

import com.uxstate.yummies.domain.model.Meal

sealed class DetailsScreenEvent {

    data class OnStarMeal(val meal: Meal) : DetailsScreenEvent()
}
