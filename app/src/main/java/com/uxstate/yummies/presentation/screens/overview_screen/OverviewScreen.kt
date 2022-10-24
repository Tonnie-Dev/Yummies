package com.uxstate.yummies.presentation.screens.overview_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uxstate.yummies.presentation.screens.overview_screen.components.SearchBoxItem
import com.uxstate.yummies.presentation.screens.overview_screen.overview_events.OverviewEvent
import com.uxstate.yummies.util.LocalSpacing

@RootNavGraph(start = true)
@Destination

@Composable
fun OverviewScreen(
    viewModel: OverviewViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {

    val meals by viewModel.stateMeals.collectAsState()
    val categories by viewModel.stateCategory.collectAsState()
    val spacing = LocalSpacing.current

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(spacing.spaceSmall)
        ) {

            // Header
            Text(
                text = "Find Best Recipe For Cooking",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h4
            )

            // Search Box
            SearchBoxItem(query = meals.searchQuery, onSearchTextChange = {
                viewModel.onEvent(
                    OverviewEvent.OnSearchQueryChange(it)
                )
            }) {
            }

            // Header
            Text(
                text = "Categories",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h6
            )

            //
        }
    }
}