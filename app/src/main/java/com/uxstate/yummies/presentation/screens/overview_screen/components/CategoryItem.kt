package com.uxstate.yummies.presentation.screens.overview_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.uxstate.yummies.R
import com.uxstate.yummies.domain.model.Category
import com.uxstate.yummies.util.LocalSpacing

@Composable
fun CategoryItem(
    category: Category,
    modifier: Modifier = Modifier,
    onClickCategory: (category: String) -> Unit
) {

    val spacing = LocalSpacing.current
    Surface(
        shape = RoundedCornerShape(spacing.spaceMedium),
        elevation = spacing.spaceSmall,
        modifier = modifier
            .padding(spacing.spaceExtraSmall)
            .clickable { onClickCategory(category.categoryType) }
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(spacing.spaceExtraSmall)
        ) {

            //  val url = category.categoryImageUrl.toUri().buildUpon().scheme("https").build()
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(category.categoryImageUrl)
                    .crossfade(true)
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_category_error)
                    .build()
            )
            Image(
                painter = painter,
                contentDescription = stringResource(R.string.meal_category_label),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(spacing.spaceOneHundred)
                    .aspectRatio(17f / 20f)

            )

            Text(
                text = category.categoryType,

                style = MaterialTheme.typography.subtitle2
            )
        }
    }
}

@Preview
@Composable
fun CategoryItemPreview() {

    CategoryItem(
        category = Category(
            categoryId = 13,
            categoryType = "Miscellaneous",
            categoryDescription = "Mutton is Good",
            categoryImageUrl = ""
        ),
        onClickCategory = {}
    )
}