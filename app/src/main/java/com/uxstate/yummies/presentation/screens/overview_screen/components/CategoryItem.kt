package com.uxstate.yummies.presentation.screens.overview_screen.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.uxstate.yummies.domain.model.Category

@Composable
fun CategoryItem(category: Category, modifier: Modifier = Modifier, onClickCategory: () -> Unit) {

}


@Preview
@Composable
fun CategoryItemPreview() {

    CategoryItem(category = Category(
            categoryId = 13,
            categoryType = "Mutton",
            categoryDescription = "Mutton is Good",
            categoryImageUrl = ""
    ), onClickCategory = {})
}