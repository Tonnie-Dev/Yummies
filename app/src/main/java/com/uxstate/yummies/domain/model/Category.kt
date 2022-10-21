package com.uxstate.yummies.domain.model

import com.squareup.moshi.Json

data class Category(

    val categoryId: Int,
    val categoryType: String,
    val categoryDescription: String,
    val categoryImageUrl: String
)