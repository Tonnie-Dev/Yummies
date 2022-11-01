package com.uxstate.yummies.presentation.screens.details_screen

import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.presentation.screens.details_screen.components.CookingDirectionsBottomSheet
import com.uxstate.yummies.presentation.screens.details_screen.components.SheetItems

@OptIn(ExperimentalMaterialApi::class)
@Destination
@Composable
fun DetailsScreen(meal: Meal) {

    BottomSheetScaffold(sheetContent = {

        // Bottom sheet
        CookingDirectionsBottomSheet(meal = meal)
    }) {

        // underlying sheet
        SheetItems(meal = meal)
    }
}