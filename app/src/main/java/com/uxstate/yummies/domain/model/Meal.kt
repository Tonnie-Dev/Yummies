package com.uxstate.yummies.domain.model

data class Meal(
    val id: Int,
    val name: String,
    val category: String,
    val origin: String,
    val directions: String,
    val imageUrl: String,
    val ingredients: List<String>,
    val units: List<String>,
    val isFavorite:Boolean
) {

    val ingredientsCount: Int
        get() = ingredients.size
}
