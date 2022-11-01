package com.uxstate.yummies.presentation.screens.details_screen.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.uxstate.yummies.domain.model.Meal

@Composable
fun CookingDirectionsBottomSheet(meal: Meal) {

    Text(text = meal.directions)
}