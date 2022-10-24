package com.uxstate.yummies.presentation.screens.overview_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.uxstate.yummies.util.LocalSpacing

@RootNavGraph(start = true)
@Destination

@Composable
fun OverviewScreen(viewModel: OverviewViewModel = hiltViewModel()) {


    val meals by viewModel.stateMeals.collectAsState()
    val categories by viewModel.stateCategory.collectAsState()
    val spacing = LocalSpacing.current

Column(modifier = Modifier.fillMaxSize().padding(spacing.spaceSmall)) {



}


}