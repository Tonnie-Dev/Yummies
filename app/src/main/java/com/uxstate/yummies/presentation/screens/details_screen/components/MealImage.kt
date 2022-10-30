package com.uxstate.yummies.presentation.screens.details_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.uxstate.yummies.R
import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.util.LocalSpacing

@Composable
fun MealImage(meal: Meal) {

    val spacing = LocalSpacing.current
    val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                    .crossfade(true)
                    .data(meal.imageUrl)
                    .error(R.drawable.ic_meal_error)
                    .placeholder(R.drawable.loading_animation)
                    .build()

    )

    Image(
            painter = painter,
            contentDescription = meal.name,
            modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(3f / 2f)
                    .padding(spacing.spaceSmall)
    )
}

@Preview
@Composable
fun MealImagePreview() {
    MealImage(meal = Meal(
            id = 0,
            name = "",
            category = "",
            origin = "",
            directions = "",
            imageUrl = "",
            ingredients = listOf(),
            units = listOf(),
            isFavorite = false
    ))
}