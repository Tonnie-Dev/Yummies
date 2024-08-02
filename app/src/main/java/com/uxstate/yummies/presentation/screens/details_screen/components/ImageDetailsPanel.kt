package com.uxstate.yummies.presentation.screens.details_screen.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star


import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.presentation.core_components.HeaderTextItem
import com.uxstate.yummies.presentation.ui.theme.starredStarColor
import com.uxstate.yummies.util.LocalSpacing

@Composable
fun ImageDetailsPanel(
    meal: Meal,
    onStar: () -> Unit,
    unStar: () -> Unit,
    modifier: Modifier = Modifier,
    isStarred: Boolean
) {

    val spacing = LocalSpacing.current
    var isMealStarred by remember { mutableStateOf(isStarred) }

    Card(modifier = modifier) {

        Row(

                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(spacing.spaceSmall)
        ) {

            Column(Modifier.weight(8.5f)) {
                HeaderTextItem(text = meal.name, style = MaterialTheme.typography.titleLarge)

                Text(
                        text = meal.origin,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(
                                spacing.spaceSmall
                        )
                )
            }

            Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                            .weight(1.5f)
                            .animateContentSize(
                                    animationSpec = tween(
                                            durationMillis = 300,
                                            easing = LinearOutSlowInEasing
                                    )
                            )
            ) {
                if (isMealStarred) {

                    // larger icon
                    Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "",
                           tint = MaterialTheme.colorScheme.starredStarColor,
                            modifier = Modifier
                                    .size(spacing.spaceExtraLarge + spacing.spaceSmall)
                                    .clickable {

                                        unStar()
                                        isMealStarred = !isMealStarred
                                    }

                    )
                } else {
                    // smaller icon
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "",
                        tint = Color.Gray.copy(alpha = 0.38f),
                        modifier = Modifier
                            .size(spacing.spaceLarge + spacing.spaceMedium + spacing.spaceSmall)
                            .clickable {

                                        onStar()
                                        isMealStarred = !isMealStarred
                                    }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ImageDetailsPanelPreview() {
    ImageDetailsPanel(
            meal = Meal(
                    id = 0,
                    name = "Chapoo Dododo",
                    category = "",
                    origin = "Jamaica",
                    directions = "",
                    imageUrl = "",
                    ingredients = listOf(),
                    units = listOf()
            ),
            onStar = {},
            unStar = {},
            isStarred = false
    )
}
