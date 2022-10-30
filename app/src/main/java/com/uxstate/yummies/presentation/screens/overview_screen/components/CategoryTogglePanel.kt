package com.uxstate.yummies.presentation.screens.overview_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.uxstate.yummies.R
import com.uxstate.yummies.presentation.core_components.HeaderTextItem
import com.uxstate.yummies.util.LocalSpacing

@Composable
fun CategoryTogglePanel(modifier: Modifier = Modifier) {

    val spacing = LocalSpacing.current
    var isShowCategories by remember { mutableStateOf(false) }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(spacing.spaceSmall),

        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        HeaderTextItem(text = stringResource(id = R.string.categories_header_text))
        Button(onClick = { isShowCategories = !isShowCategories }) {

            Icon(
                imageVector = if (isShowCategories)
                    Icons.Default.KeyboardArrowUp
                else
                    Icons.Default.KeyboardArrowDown,
                contentDescription = if (isShowCategories)
                    "Show Categories"
                else
                    "Hide Categories"

            )
        }
    }
}

@Preview
@Composable
fun CategoryTogglePanelPrev() {
    CategoryTogglePanel()
}