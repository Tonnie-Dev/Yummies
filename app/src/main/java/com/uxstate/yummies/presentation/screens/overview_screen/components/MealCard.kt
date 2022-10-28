package com.uxstate.yummies.presentation.screens.overview_screen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.util.LocalSpacing

@Composable
fun MealCard(
    meal: Meal,
    navigator: DestinationsNavigator,
    onClickMeal: () -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current

    Card(modifier = modifier.fillMaxWidth(), shape = RoundedCornerShape(spacing.spaceSmall)) {

    }
}