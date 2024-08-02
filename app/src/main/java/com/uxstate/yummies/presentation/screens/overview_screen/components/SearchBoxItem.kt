package com.uxstate.yummies.presentation.screens.overview_screen.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.uxstate.yummies.R
import com.uxstate.yummies.presentation.ui.theme.YummiesTheme
import com.uxstate.yummies.util.LocalSpacing

@OptIn(ExperimentalMaterial3Api::class)
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
                    .padding(spacing.spaceMedium),
            elevation = CardDefaults.elevatedCardElevation(spacing.spaceSmall),
            shape = RoundedCornerShape(spacing.spaceLarge)
    ) {

        TextField(
                value = query,
                onValueChange = onSearchTextChange,
                placeholder = {
                    Text(
                            text = stringResource(R.string.search_placeholder_text),
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = .5f)
                    )
                },
                textStyle = MaterialTheme.typography.headlineSmall,
                leadingIcon = {
                    Icon(
                            imageVector = Icons.Default.Search,
                            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = .3f),
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
                colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        cursorColor = MaterialTheme.colorScheme.primary,
                        focusedIndicatorColor = MaterialTheme.colorScheme.surface,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.surface,
                        
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