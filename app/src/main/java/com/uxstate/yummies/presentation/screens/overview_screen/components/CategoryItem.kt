package com.uxstate.yummies.presentation.screens.overview_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.uxstate.yummies.R
import com.uxstate.yummies.domain.model.Category
import com.uxstate.yummies.presentation.ui.theme.gradientColors
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
            shadowElevation = spacing.spaceSmall,
            modifier = modifier
                    .padding(spacing.spaceExtraSmall)
                    .clickable { onClickCategory(category.categoryType) }
    ) {

        Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                        .background(
                                brush = Brush.linearGradient(
                                        colors = MaterialTheme.colorScheme.gradientColors
                                )
                        )
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
                            .height(spacing.spaceExtraLarge)
                            .aspectRatio(1f / 1f)

            )

            Text(
                    text = category.categoryType,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleSmall
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