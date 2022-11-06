package com.uxstate.yummies.presentation.screens.overview_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
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
    onToggle: () -> Unit
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
                backgroundColor = MaterialTheme.colors.cardColor
            ),
            onClick = onToggle

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
                backgroundColor = MaterialTheme.colors.cardColor
            ),
            onClick = { /*TODO*/ }
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
    CategoryTogglePanel(isShow = true, onToggle = {})
}