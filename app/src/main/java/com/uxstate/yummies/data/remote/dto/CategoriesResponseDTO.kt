package com.uxstate.yummies.data.remote.dto

import com.squareup.moshi.Json

data class CategoriesResponseDTO(
    @Json(name = "categories")
    val categories: List<CategoryDTO>
)