package com.uxstate.yummies.presentation.screens.details_screen

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.presentation.screens.details_screen.components.CookingDirectionsBottomSheet
import com.uxstate.yummies.presentation.screens.details_screen.components.SheetItems
import com.uxstate.yummies.util.LocalSpacing

@OptIn(ExperimentalMaterialApi::class)
@Destination
@Composable
fun DetailsScreen(meal: Meal) {

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
            CookingDirectionsBottomSheet(meal = meal, modifier = Modifier.fillMaxHeight(2f / 3f))
        }
    ) {

        // underlying sheet
        SheetItems(meal = meal)
    }
}