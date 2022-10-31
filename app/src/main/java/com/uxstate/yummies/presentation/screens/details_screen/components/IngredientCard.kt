package com.uxstate.yummies.presentation.screens.details_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.uxstate.yummies.R
import com.uxstate.yummies.util.LocalSpacing

@Composable
fun IngredientCard(ingredient: String, measure: String, modifier: Modifier = Modifier) {

    val spacing = LocalSpacing.current
    Card(modifier = modifier) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.spaceSmall),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_ing_item),
                contentDescription = ingredient
            )

            Text(text = ingredient, style = MaterialTheme.typography.body1)
            Text(text = measure, style = MaterialTheme.typography.caption)
        }
    }
}

@Preview
@Composable
fun IngredientCardPreview() {

    IngredientCard(ingredient = "Water", measure = "1/2 cups")
}