package com.uxstate.yummies.data.remote

import com.uxstate.yummies.data.remote.dto.CategoriesResponseDTO
import com.uxstate.yummies.data.remote.dto.MealsResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface YummiesAPI {

    //Search meal by name
    @GET("search.php")
    suspend fun getMealsByName(@Query("s") query: String): MealsResponseDTO


    //List all meal categories
    @GET("categories.php")
    suspend fun getCategories():CategoriesResponseDTO

    companion object {

        const val BASE_URL = "www.themealdb.com/api/json/v1/1/"
    }
}