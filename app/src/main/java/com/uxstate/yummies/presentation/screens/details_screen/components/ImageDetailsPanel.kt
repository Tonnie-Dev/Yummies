package com.uxstate.yummies.presentation.screens.details_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.presentation.core_components.HeaderTextItem
import com.uxstate.yummies.util.LocalSpacing

@Composable
fun ImageDetailsPanel(
    meal: Meal,
    onStar: () -> Unit,
    unStar: () -> Unit,
    modifier: Modifier = Modifier
) {

    val spacing = LocalSpacing.current
    var isStarred by remember { mutableStateOf(meal.isFavorite) }

    Card(modifier = modifier) {

        Row(

                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(spacing.spaceSmall)
        ) {

            Column() {
                HeaderTextItem(text = meal.name, style = MaterialTheme.typography.h4)
                Text(
                        text = meal.origin,
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.onSurface,
                        modifier = Modifier.padding(
                                spacing.spaceSmall
                        )
                )
            }

            if (isStarred) {
                IconButton(
                        onClick = {
                            onStar()
                            isStarred = !isStarred

                        },
                        modifier = Modifier.padding(spacing.spaceExtraSmall)
                ) {
                    Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "",
                            tint = if (isStarred) MaterialTheme.colors.primary
                            else Color.Gray.copy(ContentAlpha.disabled),
                            modifier = Modifier.size(spacing.spaceLarge + spacing.spaceMedium)
                    )
                }
            } else {

                IconButton(
                        onClick = {
                            unStar()
                            isStarred = !isStarred

                        },
                        modifier = Modifier.padding(spacing.spaceExtraSmall)
                ) {
                    Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "",
                            tint = if (isStarred) MaterialTheme.colors.primary
                            else Color.Gray.copy(ContentAlpha.disabled),
                            modifier = Modifier.size(spacing.spaceLarge + spacing.spaceMedium)
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
                    units = listOf(),
                    isFavorite = false
            ),
            onStar = {},
            unStar = {}
    )
}
