package com.uxstate.yummies.presentation.screens.saved_items_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.uxstate.yummies.R
import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.presentation.ui.theme.Gray100
import com.uxstate.yummies.util.LocalSpacing

@Composable
fun MealBoxItem(
    meal: Meal,
    modifier: Modifier = Modifier,
    onClickMeal: () -> Unit,
    onDelete: () -> Unit
) {

    val spacing = LocalSpacing.current
    Card(
            elevation = CardDefaults.elevatedCardElevation(spacing.spaceSmall),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),

            modifier = modifier
                    .fillMaxWidth()
                    .clickable {

                        onClickMeal()
                    }
                    .padding(spacing.spaceSmall)
    ) {
        Box(
                modifier = modifier, contentAlignment = Alignment.BottomCenter
        ) {

            val painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                            .data(meal.imageUrl)
                            .crossfade(true)
                            .build()
            )

            Image(
                    painter = painter,
                    contentDescription = meal.name,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                            .clip(RoundedCornerShape(spacing.spaceMedium))
                            .fillMaxWidth()
                            .aspectRatio(4f / 3f)
                            .padding(spacing.spaceLarge)
            )

            Surface(
                    color = Gray100,
                    shadowElevation = spacing.spaceExtraSmall,
                    modifier = Modifier
                            .align(
                                    Alignment.BottomCenter
                            )
                            .fillMaxWidth()
                            .padding(
                                    spacing.spaceLarge
                            )
            ) {

                Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                ) {
                    Column() {

                        Text(
                                text = meal.name,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.headlineSmall,
                                modifier = Modifier
                                        .padding(spacing.spaceExtraSmall)
                        )
                        Text(
                                text = " ${meal.ingredientsCount} Ingredients | ${meal.origin}",
                                color = Color.White,
                                modifier = Modifier
                                        .padding(spacing.spaceExtraSmall)
                        )
                    }
                    Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = stringResource(R.string.delete_meal),
                            tint = Color.White,
                            modifier = Modifier
                                    .size(spacing.spaceLarge)
                                    .clickable {
                                        onDelete()
                                    }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun MealBoxItemPreview() {
    MealBoxItem(
            meal = Meal(
                    id = 0,
                    name = "Salmon Sushi Matcha",
                    category = "",
                    origin = "Italian",
                    directions = "",
                    imageUrl = "",
                    ingredients = listOf("Onion", "Pepper"),
                    units = listOf()
            ),
            onClickMeal = {},
            onDelete = {}
    )
}