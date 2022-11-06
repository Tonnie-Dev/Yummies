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
import timber.log.Timber

@OptIn(ExperimentalMaterialApi::class)
@Destination
@Composable
fun DetailsScreen(
    meal: Meal,
    navigator: DestinationsNavigator,
    isStarred: Boolean,
    viewModel: DetailsScreenViewModel = hiltViewModel()
) {
    val starredMealsList by viewModel.starredMeals.collectAsState()

    val starredStatus = starredMealsList.any { starredMeal -> starredMeal.id == meal.id }

  /*  LaunchedEffect(meal, block = {

        viewModel.checkStarredStatus(meal)
        Timber.i("Meal Status is in LaunchedEffect is: $starredStatus")
    })*/

    // val isMealStarred by viewModel.currentMealAsPerDatabase.collectAsState()
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
        // Timber.i("Starred contents are $starredMealsList")

        Timber.i("Poutine test returns (${meal.id == 52804}), isStarred value: ($starredStatus)")
        // underlying sheet
        SheetItems(
            meal = meal,
            onClickBackArrow = { navigator.navigateUp() },
            isMealStarred = isStarred,
            onStarClick = {
                viewModel.onEvent(event = DetailsScreenEvent.OnStarMeal(meal = meal))
            },

            unStarClick = {

                viewModel.onEvent(event = DetailsScreenEvent.UnStarMeal(meal))
            }

        )
    }
}