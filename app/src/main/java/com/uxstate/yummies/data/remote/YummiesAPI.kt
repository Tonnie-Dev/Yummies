package com.uxstate.yummies.data.remote

import com.uxstate.yummies.data.remote.dto.MealsResponseDTO

interface YummiesAPI {

    suspend fun getMealsByName():MealsResponseDTO

    companion object{

        const val BASE_URL = "www.themealdb.com/api/json/v1/1/"
    }
}