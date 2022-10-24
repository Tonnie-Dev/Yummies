package com.uxstate.yummies.presentation.screens.overview_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.uxstate.yummies.domain.model.Category
import com.uxstate.yummies.util.LocalSpacing

@Composable
fun CategoryItem(category: Category, modifier: Modifier = Modifier, onClickCategory: () -> Unit) {

    val spacing = LocalSpacing.current
    Surface(
            shape = RoundedCornerShape(spacing.spaceMedium),
            elevation = spacing.spaceExtraSmall,
            modifier = modifier.padding(spacing.spaceExtraSmall)
    ) {

        
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            
            val painter = rememberAsyncImagePainter(model = ImageRequest.Builder().data().placeholder())
            Image(painter = , contentDescription = )
        }
    }
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