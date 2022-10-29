package com.uxstate.yummies.presentation.screens.overview_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
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
import com.uxstate.yummies.util.LocalSpacing

@Composable
fun MealCard(
    meal: Meal,
    // navigator: DestinationsNavigator,
    onClickMeal: () -> Unit,
    modifier: Modifier = Modifier,
    isFavorite: Boolean = false
) {
    val spacing = LocalSpacing.current

    Card(
        modifier = modifier
                .fillMaxWidth()
                .padding(spacing.spaceSmall),

        elevation = spacing.spaceSmall,
        shape = RoundedCornerShape(spacing.spaceSmall)
    ) {

        Row(
            modifier = Modifier.background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFFED234), Color(0xFFFAF1d6), Color(0xFFF4F4F4)
                    )
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
                    style = MaterialTheme.typography.h6,
                    maxLines = 2,
                    color = MaterialTheme.colors.onSurface
                )

                Row() {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_flag),
                        contentDescription = stringResource(R.string.origin_text_label)
                    )
                    Text(text = meal.origin, style = MaterialTheme.typography.body1)
                }

             Row(modifier = Modifier.border(width = spacing.)) {

             }
                Text(
                    text = "${meal.ingredientsCount} Ingredients",
                    style = MaterialTheme.typography.subtitle2
                )

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "",
                        tint = if (isFavorite) Color.Cyan else Color.Gray,
                        modifier = Modifier.size(spacing.spaceLarge)
                    )
                }
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
        onClickMeal = { /*TODO*/ }
    )
}