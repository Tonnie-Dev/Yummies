package com.uxstate.yummies.data.remote.dto


import com.squareup.moshi.Json

data class Category(
    @Json(name = "idCategory")
    val idCategory: String,
    @Json(name = "strCategory")
    val strCategory: String,
    @Json(name = "strCategoryDescription")
    val strCategoryDescription: String,
    @Json(name = "strCategoryThumb")
    val strCategoryThumb: String
)