package com.uxstate.yummies.presentation.screens.overview_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.util.LocalSpacing
import kotlin.random.Random

@Composable
fun MealBoxItem(meal: Meal, modifier: Modifier = Modifier) {

    val spacing = LocalSpacing.current
    Box(
            modifier = modifier, contentAlignment = Alignment.BottomCenter
    )

    {

        val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                        .data(meal.imageUrl)
                        .crossfade(true)
                        .build()
        )

        Image(
                painter = painter,
                contentDescription = meal.name,
                modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(2f / 1f)
        )

        Surface(color = Color(0x7F000000), shape = RoundedCornerShape(spacing.spaceMedium)) {
            Column() {

                val randomNumber = Random.nextInt(50)
                Text(text = meal.name)
                Text(text = "Ingredients | $randomNumber Min")
            }


        }

    }
}