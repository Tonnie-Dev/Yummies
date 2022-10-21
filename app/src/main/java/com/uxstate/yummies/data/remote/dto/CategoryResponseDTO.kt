package com.uxstate.yummies.data.remote.dto


import com.squareup.moshi.Json

data class CategoryResponseDTO(
    @Json(name = "categories")
    val categories: List<Category>
)