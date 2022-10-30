package com.uxstate.yummies.presentation.screens.details_screen.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.util.LocalSpacing
import com.uxstate.yummies.R

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
}