package com.uxstate.yummies.presentation.screens.overview_screen

import androidx.compose.animation.*
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
import androidx.compose.ui.Alignment
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
import com.uxstate.yummies.presentation.screens.destinations.DetailsScreenDestination
import com.uxstate.yummies.presentation.screens.overview_screen.components.CategoryItem
import com.uxstate.yummies.presentation.screens.overview_screen.components.CategoryTogglePanel
import com.uxstate.yummies.presentation.screens.overview_screen.components.MealCard
import com.uxstate.yummies.presentation.screens.overview_screen.components.SearchBoxItem
import com.uxstate.yummies.presentation.screens.overview_screen.overview_events.OverviewEvent
import com.uxstate.yummies.presentation.ui.theme.statusBarColor
import com.uxstate.yummies.util.LocalSpacing
import timber.log.Timber

@Destination
@RootNavGraph(start = true)
@Composable
fun OverviewScreen(
    viewModel: OverviewViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val starredMeals by viewModel.starredMeals.collectAsState()

    Timber.i("Received meals are: $starredMeals")
    val mealsState by viewModel.stateMeals.collectAsState()
    val categoriesState by viewModel.stateCategory.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = mealsState.isLoading
    )

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
    /*        Surface(
                modifier = Modifier
                    .padding(spacing.spaceSmall)
                    .background(brush = Brush.linearGradient(MaterialTheme.colors.gradientColors)),
                elevation = spacing.spaceSmall

            ) {

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .border(
                            width = spacing.spaceDoubleDp,
                            color = MaterialTheme.colors.primary
                        )

                ) {

                }
            }*/
            // Header 1
            CategoryTogglePanel(isShow = categoriesState.isShowCategories) {
                viewModel.onEvent(OverviewEvent.OnToggleCategoryPanel)
            }

            AnimatedVisibility(
                visible = categoriesState.isShowCategories,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {

                Timber.i("Received meals are: $starredMeals")
                // Categories
                LazyRow() {

                    items(categoriesState.categories) { category ->

                        CategoryItem(category = category) {
                            viewModel.onEvent(OverviewEvent.OnCategoryClick(it))
                        }
                    }
                }
            }

            // Header 2
            HeaderTextItem(text = stringResource(R.string.recipes_header_text))

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

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
                            PaddingValues(vertical = spacing.spaceMedium)
                        ) {

                            items(mealsState.meals) { meal ->

                                val isStarred = starredMeals.any {
                                    starredMeal ->
                                    starredMeal.id == meal.id
                                }
                                MealCard(
                                    meal = meal,
                                    onClickMeal = {
                                        navigator.navigate(
                                            DetailsScreenDestination(
                                                meal = it,
                                                isStarred = isStarred
                                            )
                                        )
                                    },
                                    isStarred = isStarred
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
