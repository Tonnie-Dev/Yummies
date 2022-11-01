package com.uxstate.yummies.presentation.screens.details_screen

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.ramcosta.composedestinations.annotation.Destination
import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.presentation.screens.details_screen.components.CookingDirectionsBottomSheet
import com.uxstate.yummies.presentation.screens.details_screen.components.SheetItems

@OptIn(ExperimentalMaterialApi::class)
@Destination
@Composable
fun DetailsScreen(meal: Meal) {

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

    BottomSheetScaffold(sheetContent = {

        // Bottom sheet
        CookingDirectionsBottomSheet(meal = meal)
    }) {

        // underlying sheet
        SheetItems(meal = meal)
    }
}