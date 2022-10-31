package com.uxstate.yummies.presentation.screens.details_screen

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.presentation.screens.details_screen.components.MealImage

@Destination
@Composable
fun ScreenTwo(meal: Meal, navigator: DestinationsNavigator) {
    MealImage(meal = meal)
}