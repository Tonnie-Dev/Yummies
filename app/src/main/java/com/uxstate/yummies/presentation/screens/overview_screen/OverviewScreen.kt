package com.uxstate.yummies.presentation.screens.overview_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uxstate.yummies.R
import com.uxstate.yummies.presentation.core_components.HeaderTextItem
import com.uxstate.yummies.presentation.screens.overview_screen.components.CategoryItem
import com.uxstate.yummies.presentation.screens.overview_screen.components.MealCard
import com.uxstate.yummies.presentation.screens.overview_screen.components.SearchBoxItem
import com.uxstate.yummies.presentation.screens.overview_screen.overview_events.OverviewEvent
import com.uxstate.yummies.presentation.ui.theme.statusBarColor
import com.uxstate.yummies.util.LocalSpacing
import timber.log.Timber

@RootNavGraph(start = true)
@Destination

@Composable
fun OverviewScreen(
    viewModel: OverviewViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {

    val mealsState by viewModel.stateMeals.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = mealsState.isLoading
    )

    val categoriesState by viewModel.stateCategory.collectAsState()
    val spacing = LocalSpacing.current
    val uiController = rememberSystemUiController()
    uiController.setStatusBarColor(color = MaterialTheme.colors.statusBarColor)
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(spacing.spaceSmall)
        ) {

            // Search Box
            SearchBoxItem(
                query = mealsState.searchQuery,
                onSearchTextChange = {
                    viewModel.onEvent(OverviewEvent.OnSearchQueryChange(it))
                }, onClearText = {

                viewModel.onEvent(OverviewEvent.OnClearText)
            }
            )

            // Header 1
            HeaderTextItem(text = stringResource(R.string.categories_header_text))

            // Categories
            LazyRow() {

                Timber.i("The length of categories List is: ${categoriesState.categories.size}")
                items(categoriesState.categories) { category ->

                    CategoryItem(category = category) {
                        viewModel.onEvent(OverviewEvent.OnCategoryClick(it))
                    }
                }
            }

            // Header 2
            HeaderTextItem(text = stringResource(R.string.recipes_header_text))

            Box(modifier = Modifier.fillMaxWidth()) {

                if (mealsState.isLoading) {
                    CircularProgressIndicator()
                } else {

                    // apply swipe refresh view
                    SwipeRefresh(
                        state = swipeRefreshState,
                        onRefresh = {
                            viewModel.onEvent(event = OverviewEvent.OnRefresh)
                        }
                    ) {

                        // content to be refreshed
                        LazyColumn(
                            contentPadding =
                            PaddingValues(vertical = spacing.spaceMedium),
                            content = {

                                items(mealsState.meals) { meal ->

                                    MealCard(meal = meal, onClickMeal = {})
                                }
                            }
                        )
                    }
                }
            }

            /*  // Grid
              LazyVerticalGrid(columns = GridCells.Fixed(2), content = {

                  items(mealsState.meals) { meal ->

                      MealItem(meal = meal, onClickCategory = {})
                  }
              })*/
        }
    }
}