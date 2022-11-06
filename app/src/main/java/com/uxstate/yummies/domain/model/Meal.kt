package com.uxstate.yummies.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Meal(
    val id: Int,
    val name: String,
    val category: String,
    val origin: String,
    val directions: String,
    val imageUrl: String,
    val ingredients: List<String>,
    val units: List<String>
) : Parcelable {

    val ingredientsCount: Int
        get() = ingredients.size
}
