package com.uxstate.yummies.presentation.screens.overview_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.uxstate.yummies.presentation.ui.theme.cardColor
import com.uxstate.yummies.util.LocalSpacing

@Composable
fun CategoryTogglePanel(
    modifier: Modifier = Modifier,
    isShow: Boolean,
    onClickCategories: () -> Unit,
    onClickFavorites: () -> Unit
) {

    val spacing = LocalSpacing.current
    //  var isShowCategories by remember { mutableStateOf(isShow) }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = spacing.spaceExtraSmall),

        horizontalArrangement = Arrangement.SpaceAround
    ) {

        Button(

            colors = ButtonDefaults.buttonColors(
               contentColor = MaterialTheme.colorScheme.cardColor
            ),
            onClick = onClickCategories

        ) {

            Icon(
                imageVector = if (isShow)
                    Icons.Default.KeyboardArrowUp
                else
                    Icons.Default.KeyboardArrowDown,
                contentDescription = if (isShow)
                    "Show Categories"
                else
                    "Hide Categories",
                tint = Color.Black

            )

            Text(text = "Categories")
        }
        Button(
            colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.cardColor
            ),
            onClick = { onClickFavorites() }
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite"
            )

            Text(text = "Favorites")
        }
    }
}

@Preview
@Composable
fun CategoryTogglePanelPrev() {
    CategoryTogglePanel(isShow = true, onClickCategories = {}, onClickFavorites = {})
}