package com.uxstate.yummies.presentation.screens.details_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.uxstate.yummies.domain.model.Meal

@Composable
fun IngredientCard(meal: Meal, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {

        Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
        ) {


        }

    }
}