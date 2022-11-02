package com.uxstate.yummies.presentation.screens.details_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.uxstate.yummies.R
import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.presentation.core_components.HeaderTextItem
import com.uxstate.yummies.util.LocalSpacing

@Composable
fun SheetItems(meal: Meal, onClickBackArrow: () -> Unit) {
    val spacing = LocalSpacing.current
    Column {

        // Meal Image
        MealImage(meal = meal, onClickBackArrow = onClickBackArrow)

        // Image Details
        ImageDetailsPanel(meal = meal, onStar = { /*TODO*/ })

        HeaderTextItem(text = stringResource(id = R.string.ingredients_text_label))
        // ingredient
        LazyColumn(contentPadding = PaddingValues(spacing.spaceSmall)) {

            itemsIndexed(meal.ingredients) { i, ing ->

                IngredientCard(ingredient = ing, measure = meal.units[i])
            }
        }
    }
}