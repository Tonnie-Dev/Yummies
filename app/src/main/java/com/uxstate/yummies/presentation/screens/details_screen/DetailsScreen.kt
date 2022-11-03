package com.uxstate.yummies.presentation.screens.details_screen

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.presentation.screens.details_screen.components.CookingDirectionsBottomSheet
import com.uxstate.yummies.presentation.screens.details_screen.components.SheetItems
import com.uxstate.yummies.presentation.screens.details_screen.details_event.DetailsScreenEvent
import com.uxstate.yummies.util.LocalSpacing

@OptIn(ExperimentalMaterialApi::class)
@Destination
@Composable
fun DetailsScreen(
    meal: Meal,
    navigator: DestinationsNavigator,
    viewModel: DetailsScreenViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = meal, block = {

        viewModel.checkStarredStatus(meal)
    })
    val isMealStarred by viewModel.currentMealAsPerDatabase.collectAsState()
    val spacing = LocalSpacing.current

    // bottom sheet state with initial value
    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )

    // scaffold state with bottom sheet state
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )
    // this scope is aware of the composition lifecycle
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = spacing.spaceExtraLarge + spacing.spaceLarge,
        sheetContent = {

            // Bottom sheet
            CookingDirectionsBottomSheet(
                meal = meal,
                modifier = Modifier.fillMaxHeight(2f / 3f)
            )
        }
    ) {

        // underlying sheet
        SheetItems(
            meal = meal,
            onClickBackArrow = { navigator.navigateUp() },
            isMealStarred = isMealStarred,
            onStarClick = {
                viewModel.onEvent(event = DetailsScreenEvent.OnStarMeal(meal = meal))
            },

            unStarClick = {

                viewModel.onEvent(event = DetailsScreenEvent.UnStarMeal(meal))
            }

        )
    }
}