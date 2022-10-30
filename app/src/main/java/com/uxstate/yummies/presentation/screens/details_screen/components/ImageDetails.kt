package com.uxstate.yummies.presentation.screens.details_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.uxstate.yummies.R
import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.presentation.core_components.HeaderTextItem
import com.uxstate.yummies.util.LocalSpacing

@Composable
fun ImageDetailsPanel(meal: Meal, modifier: Modifier = Modifier) {

    val spacing = LocalSpacing.current
    Card(modifier = modifier) {
        Column(modifier = Modifier.fillMaxWidth()) {
            HeaderTextItem(text = meal.name)

            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text(text = meal.origin)
                Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "",
                        tint = if (meal.isFavorite) MaterialTheme.colors.primary
                        else Color.Gray.copy(ContentAlpha.disabled),
                        modifier = Modifier.size(spacing.spaceLarge + spacing.spaceMedium)
                )
            }


        }
    }

}

