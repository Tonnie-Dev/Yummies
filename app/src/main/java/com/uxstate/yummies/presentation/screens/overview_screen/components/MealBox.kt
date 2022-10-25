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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(3f / 2f)
        )

        Surface(
            color = Color(0x7F000000),
            shape = RoundedCornerShape(spacing.spaceSmall),
            modifier = Modifier
                .align(
                    Alignment.BottomCenter
                )
                .fillMaxWidth()
        ) {
            Column() {

                val randomNumber = Random.nextInt(50)
                Text(
                    text = meal.name,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(spacing.spaceSmall)
                )
                Text(
                    text = " ${meal.ingredientsCount} Ingredients | ${meal.origin}",
                    color = Color.White,
                    modifier = Modifier.padding(spacing.spaceSmall)
                )
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
        )
    )
}