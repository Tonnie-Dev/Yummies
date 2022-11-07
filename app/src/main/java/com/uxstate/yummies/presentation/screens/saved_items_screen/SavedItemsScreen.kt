package com.uxstate.yummies.presentation.screens.saved_items_screen

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uxstate.yummies.R
import com.uxstate.yummies.presentation.core_components.HeaderTextItem
import com.uxstate.yummies.presentation.screens.destinations.DetailsScreenDestination
import com.uxstate.yummies.presentation.screens.saved_items_screen.components.MealBoxItem
import com.uxstate.yummies.util.Constants.PLACEMENT_ANIM_DURATION
import com.uxstate.yummies.util.LocalSpacing

@OptIn(ExperimentalFoundationApi::class)
@Destination
@Composable
fun SavedItemsScreen(
    viewModel: SavedItemsViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {

    val savedMeals = viewModel.savedMeals
    val spacing = LocalSpacing.current

    Scaffold(
        topBar = {
            TopAppBar {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(
                            id = R.string.back_label
                        ),
                        tint = Color.Black,
                        modifier = Modifier
                            .clickable {
                                navigator.navigateUp()
                            }
                            .size(spacing.spaceLarge)
                    )
                    HeaderTextItem(text = "Saved Meals")
                }
            }
        }

    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {

            items(items = savedMeals, key = { it.id }) { meal ->

                MealBoxItem(
                    meal = meal,
                    onClickMeal = {
                        navigator.popBackStack()
                        navigator.navigate(
                            DetailsScreenDestination(
                                meal = meal,
                                isStarred = true
                            )

                        )
                    },
                    onDelete = {
                        viewModel.onEvent(SavedScreenEvent.DeleteMeal(meal = meal))
                    },
                    modifier = Modifier.animateItemPlacement(
                        animationSpec = tween(
                            durationMillis = PLACEMENT_ANIM_DURATION
                        )
                    )
                )
            }
        }
    }
}