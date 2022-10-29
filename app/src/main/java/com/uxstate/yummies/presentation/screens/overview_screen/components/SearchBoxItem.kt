package com.uxstate.yummies.presentation.screens.overview_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.uxstate.yummies.R
import com.uxstate.yummies.presentation.ui.theme.gradientColors
import com.uxstate.yummies.util.LocalSpacing

@Composable
fun SearchBoxItem(
    query: String,
    modifier: Modifier = Modifier,
    onSearchTextChange: (text: String) -> Unit,
    onClearText: () -> Unit
) {

    val spacing = LocalSpacing.current
    Surface(
        modifier = modifier
            .padding(spacing.spaceExtraSmall)
            .background(brush = Brush.linearGradient(MaterialTheme.colors.gradientColors)),
        elevation = spacing.spaceExtraSmall

    ) {
        TextField(
            value = query,
            onValueChange = onSearchTextChange,
            placeholder = {
                Text(
                    text = stringResource(R.string.search_placeholder_text),
                    color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)
                )
            },
            textStyle = MaterialTheme.typography.h6,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    tint = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
                    contentDescription = stringResource(R.string.search_icon_label)
                )
            }, trailingIcon = {
            IconButton(onClick = onClearText) {

                Icon(
                    imageVector = Icons.Default.Close,

                    contentDescription = stringResource(R.string.clear_text_icon)
                )
            }
        },
            singleLine = true, modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                focusedIndicatorColor = MaterialTheme.colors.background,
                unfocusedIndicatorColor = MaterialTheme.colors.background,
                textColor = MaterialTheme.colors.onSurface
            )
        )
    }
}

@Preview
@Composable
fun SearchBoxItemPreview() {

    SearchBoxItem(query = "text", onSearchTextChange = {}, onClearText = {})
}