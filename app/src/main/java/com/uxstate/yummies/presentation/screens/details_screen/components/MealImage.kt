package com.uxstate.yummies.presentation.screens.details_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.uxstate.yummies.R
import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.util.LocalSpacing

@Composable
fun MealImage(meal: Meal, onClickBackArrow: () -> Unit) {

    val spacing = LocalSpacing.current
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .crossfade(true)
            .data(meal.imageUrl)
            .error(R.drawable.ic_meal_error)
            .placeholder(R.drawable.loading_animation)
            .build()

    )

    Box(contentAlignment = Alignment.TopStart) {

        Image(
            painter = painter,

            contentDescription = meal.name,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(3f / 2f)
                .padding(spacing.spaceSmall),
            contentScale = ContentScale.Crop
        )

        IconButton(onClick = onClickBackArrow) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = stringResource(R.string.back_label),
                tint = Color.White,
                modifier = Modifier.size(spacing.spaceLarge)
            )
        }
    }
}

@Preview
@Composable
fun MealImagePreview() {
    MealImage(
        meal = Meal(
            id = 0,
            name = "",
            category = "",
            origin = "",
            directions = "",
            imageUrl = "",
            ingredients = listOf(),
            units = listOf(),
            isFavorite = false
        )
    ) {}
}