package com.uxstate.yummies.presentation.screens.details_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
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
                .padding(spacing.spaceSmall)

        ) {

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(spacing.spaceMedium)
            ) {
                Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "")
                HeaderTextItem(
                    text = "Cooking Directions",
                    textAlignment = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            val input = meal.directions
            val formattedDirections = input.replace("\\.\\s?", "\\.\n")

            Text(text = formattedDirections)
        }
    }
}