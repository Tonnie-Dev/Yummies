package com.uxstate.yummies.data.remote

import com.uxstate.yummies.data.remote.dto.MealsResponseDTO

interface YummiesAPI {

    suspend fun getMealsByName():MealsResponseDTO
}