package com.uxstate.yummies.domain.model

import com.squareup.moshi.Json

data class Category(

    val idCategory: Int,
    val strCategory: String,
    val strCategoryDescription: String,
    val strCategoryThumb: String
)