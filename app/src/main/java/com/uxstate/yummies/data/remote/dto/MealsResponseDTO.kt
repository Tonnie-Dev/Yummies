package com.uxstate.yummies.data.remote.dto


import com.squareup.moshi.Json

data class MealsResponseDTO(
    @Json(name = "meals")
    val mealDTOS: List<MealDTO>
)