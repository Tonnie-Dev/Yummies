package com.uxstate.yummies.presentation.screens.saved_items_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uxstate.yummies.R
import com.uxstate.yummies.presentation.core_components.HeaderTextItem
import com.uxstate.yummies.presentation.screens.saved_items_screen.components.MealBoxItem

@Destination
@Composable
fun SavedItemsScreen(
    viewModel: SavedItemsViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {

    val savedMeals = viewModel.savedMeals

    Scaffold(
        topBar = {
            Row() {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(
                        id = R.string.back_label
                    ),
                    tint = Color.White,
                    modifier = Modifier.clickable {
                        navigator.navigateUp()
                    }
                )
                HeaderTextItem(text = "Saved Meals")
            }
        }

    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {

            items(savedMeals) { meal ->

                MealBoxItem(meal = meal)
            }
        }
    }
}