package com.uxstate.yummies.presentation.screens.overview_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.uxstate.yummies.R
import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.util.LocalSpacing

@Composable
fun MealItem(meal: Meal, modifier: Modifier = Modifier, onClickCategory: () -> Unit) {

    val spacing = LocalSpacing.current
    Surface(
        shape = RoundedCornerShape(spacing.spaceMedium),
        elevation = spacing.spaceSmall,
        modifier = modifier.padding(spacing.spaceExtraSmall)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(spacing.spaceExtraSmall)
        ) {

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
                modifier = Modifier.fillMaxWidth()

            )

            Text(
                text = meal.name,

                style = MaterialTheme.typography.subtitle2,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
fun MealItemPreview() {

    MealItem(
        meal = Meal(
            id = 0,
            name = "Salmon",
            category = "",
            origin = "",
            directions = "",
            imageUrl = "",
            ingredients = listOf(),
            units = listOf()
        ),
        onClickCategory = {}
    )
}