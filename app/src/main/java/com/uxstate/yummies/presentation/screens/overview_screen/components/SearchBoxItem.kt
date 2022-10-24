package com.uxstate.yummies.presentation.screens.overview_screen.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.uxstate.yummies.util.LocalSpacing

@Composable
fun SearchBoxItem(
    query: String,
    modifier: Modifier = Modifier,
    onSearchTextChange: (text: String) -> Unit
) {

    val spacing = LocalSpacing.current
    Surface(
            modifier = modifier.padding(spacing.spaceExtraSmall),
            elevation = spacing.spaceExtraSmall
    ) {

    }
}