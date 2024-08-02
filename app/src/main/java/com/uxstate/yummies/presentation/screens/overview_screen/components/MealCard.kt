package com.uxstate.yummies.presentation.screens.overview_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.uxstate.yummies.R
import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.presentation.ui.theme.gradientColors
import com.uxstate.yummies.presentation.ui.theme.starredStarColor
import com.uxstate.yummies.util.LocalSpacing

@Composable
fun MealCard(
    meal: Meal,
    onClickMeal: (meal: Meal) -> Unit,
    isStarred: Boolean,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current

    Card(
            modifier = modifier
                    .fillMaxWidth()
                    .clickable { onClickMeal(meal) }
                    .padding(spacing.spaceSmall),

            elevation = CardDefaults.cardElevation(spacing.spaceSmall),
            shape = RoundedCornerShape(spacing.spaceSmall)
    ) {

        Row(
                modifier = Modifier.background(
                        brush = Brush.linearGradient(
                                colors = MaterialTheme.colorScheme.gradientColors
                        )
                )
        ) {

            Column(
                    modifier = Modifier
                            .weight(4.5f)
                            .padding(spacing.spaceMedium),
                    verticalArrangement = Arrangement.Center,

                    ) {
                Text(
                        text = meal.name,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineSmall,
                        maxLines = 2,
                        color = MaterialTheme.colorScheme.onSurface
                )

                Row {
                    Icon(
                            painter = painterResource(id = R.drawable.ic_flag),
                            tint = Color.Black.copy(alpha = .5f),
                            contentDescription = stringResource(R.string.origin_text_label)
                    )
                    Text(
                            text = meal.origin,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Row(
                        modifier = Modifier
                                .border(
                                        width = spacing.spaceSingleDp,
                                        color = Color.Black,
                                        shape = CutCornerShape(spacing.spaceSmall)
                                )
                                .padding(spacing.spaceExtraSmall)
                ) {
                    Text(
                            text = "${meal.ingredientsCount} Ingredients",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(spacing.spaceExtraSmall)
                    )
                }

                Spacer(modifier = Modifier.height(spacing.spaceSmall))

                Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "",
                        tint = if (isStarred) MaterialTheme.colorScheme.starredStarColor
                        else Color.Gray.copy(.3f),
                        modifier = Modifier.size(spacing.spaceLarge)
                )
            }

            val painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                            .data(meal.imageUrl)
                            // .size(Size.ORIGINAL)
                            .crossfade(true)
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.ic_meal_error)
                            .build()
            )
            Image(
                    painter = painter,
                    contentDescription = stringResource(R.string.meal_category_label),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                            .size(spacing.spaceOneHundredFifty)
                            .weight(5.5f)
                            .clip(CircleShape)

            )
        }
    }
}

@Preview
@Composable
fun MealCardPreview() {
    MealCard(
            meal = Meal(
                    id = 0,
                    name = "Mexican Junk",
                    category = "",
                    origin = "",
                    directions = "",
                    imageUrl = "",
                    ingredients = listOf("A", "B", "C"),
                    units = listOf()
            ),
            onClickMeal = { /*TODO*/ },

            isStarred = false

    )
}