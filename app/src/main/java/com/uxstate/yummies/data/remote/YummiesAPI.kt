package com.uxstate.yummies.data.remote

import com.uxstate.yummies.data.remote.dto.CategoriesResponseDTO
import com.uxstate.yummies.data.remote.dto.MealsResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface YummiesAPI {

    //Search meal by name - www.themealdb.com/api/json/v1/1/search.php?s=Arrabiata

    @GET("search.php")
    suspend fun getMealsByName(@Query("s") mealName: String): MealsResponseDTO

    //Lookup full meal details by id - www.themealdb.com/api/json/v1/1/lookup.php?i=52772

    @GET("lookup.php")
    suspend fun getMealsById(@Query("i") mealId:String):MealsResponseDTO


    //List all meal categories - www.themealdb.com/api/json/v1/1/categories.php

    @GET("categories.php")
    suspend fun getCategories():CategoriesResponseDTO

    companion object {

        const val BASE_URL = "www.themealdb.com/api/json/v1/1/"
    }
}