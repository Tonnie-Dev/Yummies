package com.uxstate.yummies.presentation.core_components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.uxstate.yummies.util.LocalSpacing

@Composable
fun HeaderTextItem(
    text: String,
    modifier: Modifier = Modifier,
    textAlignment: TextAlign = TextAlign.Start,
    color: Color = MaterialTheme.colorScheme.onSurface,
    fontWeight: FontWeight = FontWeight.Bold,
    style: TextStyle = MaterialTheme.typography.bodySmall

) {

    val spacing = LocalSpacing.current
    Text(

        text = text,
        modifier = modifier.padding(spacing.spaceSmall),
        textAlign = textAlignment,
        color = color.copy(alpha = .5f),
        fontWeight = fontWeight,
        style = style,
        overflow = TextOverflow.Ellipsis

    )
}