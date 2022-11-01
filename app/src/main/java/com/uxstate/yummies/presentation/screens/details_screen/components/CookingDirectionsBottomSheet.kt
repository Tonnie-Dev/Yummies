package com.uxstate.yummies.presentation.screens.details_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.presentation.core_components.HeaderTextItem
import com.uxstate.yummies.presentation.ui.theme.gradientColors
import com.uxstate.yummies.util.LocalSpacing

@Composable
fun CookingDirectionsBottomSheet(meal: Meal) {

    val spacing = LocalSpacing.current
    Card(
        elevation = spacing.spaceSmall,
        modifier = Modifier.clip(
            RoundedCornerShape(
                topStart = spacing.spaceLarge,
                topEnd = spacing.spaceLarge
            )
        )
    ) {
        Column(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = MaterialTheme.colors.gradientColors
                    )
                )

        ) {
            HeaderTextItem(text = "Cooking Directions")
            Text(text = meal.directions)
        }
    }
}