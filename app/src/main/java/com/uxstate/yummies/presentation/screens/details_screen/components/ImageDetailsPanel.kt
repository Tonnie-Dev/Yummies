package com.uxstate.yummies.presentation.screens.details_screen.components

import androidx.compose.foundation.clickable
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
import timber.log.Timber

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

            if (isMealStarred) {

                Timber.i("Inside If-Block - truthBlock")
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "",
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier.size(spacing.spaceLarge + spacing.spaceMedium).clickable {

                        // onStar()
                        isMealStarred = !isMealStarred
                    }
                )
              /*  IconButton(
                    onClick = {
                        onStar()
                        isStarred = !isStarred
                    },
                    modifier = Modifier.padding(spacing.spaceExtraSmall)
                ) {

                }*/
            } else {

                Timber.i("Inside Else - NegativeBlock")
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "",
                    tint = Color.Gray.copy(ContentAlpha.disabled),
                    modifier = Modifier.size(spacing.spaceLarge + spacing.spaceMedium).clickable {

                        // unStar()
                        isMealStarred = !isMealStarred
                    }
                )
                /*IconButton(
                    onClick = {

                    },
                    modifier = Modifier.padding(spacing.spaceExtraSmall)
                ) {

                }*/
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
        unStar = {},
        isStarred = false
    )
}
