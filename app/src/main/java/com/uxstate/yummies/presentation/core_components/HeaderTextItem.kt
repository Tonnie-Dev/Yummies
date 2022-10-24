package com.uxstate.yummies.presentation.core_components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun HeaderTextItem(
    text: String,
    modifier: Modifier = Modifier,
    textAlignment: TextAlign = TextAlign.Start,
    color: Color = MaterialTheme.colors.onSurface,
    fontWeight: FontWeight = FontWeight.Bold,
    style: TextStyle = MaterialTheme.typography.h6

) {

    Text(

        text = text,
        modifier = modifier,
        textAlign = textAlignment,
        color = color,
        fontWeight = fontWeight,
        style = style

    )
}