package com.uxstate.yummies.presentation.screens.details_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.presentation.screens.details_screen.components.MealImage

@Destination
@Composable
fun DetailsScreen(meal: Meal) {

    Column {
        MealImage(meal = meal)
    }
}