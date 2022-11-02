package com.uxstate.yummies.presentation.screens.details_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.presentation.core_components.HeaderTextItem
import com.uxstate.yummies.presentation.ui.theme.gradientColors
import com.uxstate.yummies.util.LocalSpacing

@Composable
fun CookingDirectionsBottomSheet(meal: Meal, modifier: Modifier = Modifier) {

    val spacing = LocalSpacing.current
    Card(
        elevation = spacing.spaceSmall,
        modifier = modifier.clip(
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
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(spacing.spaceMedium)
            ) {
                Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "")
                HeaderTextItem(
                    text = "Cooking Directions",
                    style = MaterialTheme.typography.h5
                )
            }

            val input = meal.directions
            val formattedDirections = input.replace(Regex("\\.\\s?"), "\\.\n\n")

            Text(
                text = ("\n $formattedDirections").trimIndent(),
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .padding(vertical = spacing.spaceSmall)
                    .verticalScroll(
                        rememberScrollState()
                    )
            )
        }
    }
}