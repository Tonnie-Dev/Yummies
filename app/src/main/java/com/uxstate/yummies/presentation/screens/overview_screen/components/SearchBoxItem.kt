package com.uxstate.yummies.presentation.screens.overview_screen.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.uxstate.yummies.R
import com.uxstate.yummies.presentation.ui.theme.YummiesTheme
import com.uxstate.yummies.presentation.ui.theme.cardColor
import com.uxstate.yummies.presentation.ui.theme.starredStarColor
import com.uxstate.yummies.util.LocalSpacing

@Composable
fun SearchBoxItem(
    query: String,
    modifier: Modifier = Modifier,
    onSearchTextChange: (text: String) -> Unit,
    onClearText: () -> Unit
) {

    val spacing = LocalSpacing.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(spacing.spaceExtraSmall),
        elevation = spacing.spaceSmall,
        shape = RoundedCornerShape(spacing.spaceSmall)
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
                backgroundColor = MaterialTheme.colors.cardColor,
                cursorColor = MaterialTheme.colors.starredStarColor,
                focusedIndicatorColor = MaterialTheme.colors.cardColor,
                unfocusedIndicatorColor = MaterialTheme.colors.cardColor,
                textColor = MaterialTheme.colors.onSurface
            )
        )
    }
}

@Preview
@Composable
fun SearchBoxItemPreview() {
    YummiesTheme() {
        SearchBoxItem(query = "text", onSearchTextChange = {}, onClearText = {})
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SearchBoxItemPreviewDark() {
    YummiesTheme() {
        SearchBoxItem(query = "text", onSearchTextChange = {}, onClearText = {})
    }
}