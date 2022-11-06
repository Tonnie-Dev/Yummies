package com.uxstate.yummies.presentation.screens.saved_items_screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.uxstate.yummies.presentation.core_components.HeaderTextItem
import com.uxstate.yummies.presentation.screens.saved_items_screen.components.MealBoxItem

@Destination
@Composable
fun SavedItemsScreen(viewModel: SavedItemsViewModel = hiltViewModel()) {

    val savedMeals = viewModel.savedMeals

    LazyColumn {
        item {
            HeaderTextItem(text = "Saved Meals")
        }

        items(savedMeals) { meal ->

            MealBoxItem(meal = meal)
        }
    }
}