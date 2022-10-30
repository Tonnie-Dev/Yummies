package com.uxstate.yummies.presentation.screens.overview_screen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.uxstate.yummies.util.LocalSpacing

@Composable
fun CategoryToggleButton(modifier: Modifier = Modifier) {

    val spacing = LocalSpacing.current
    Row(
            modifier = modifier
                    .fillMaxWidth()
                    .padding(spacing.spaceSmall)
    ) {

    }
}