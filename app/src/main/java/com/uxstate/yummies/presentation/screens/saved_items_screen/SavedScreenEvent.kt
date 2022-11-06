package com.uxstate.yummies.presentation.screens.saved_items_screen

import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.presentation.screens.saved_items_screen.SavedScreenEvent.DeleteMeal.meal

sealed class SavedScreenEvent(val meal: Meal) {

    object DeleteMeal : SavedScreenEvent(meal = meal)
}
