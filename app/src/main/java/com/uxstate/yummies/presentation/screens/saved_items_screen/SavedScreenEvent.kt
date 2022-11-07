package com.uxstate.yummies.presentation.screens.saved_items_screen

import com.uxstate.yummies.domain.model.Meal

sealed class SavedScreenEvent() {

    data class DeleteMeal(val meal: Meal) : SavedScreenEvent()
}
