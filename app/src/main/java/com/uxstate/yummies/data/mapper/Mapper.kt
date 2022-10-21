package com.uxstate.yummies.data.mapper

import com.uxstate.yummies.data.remote.dto.MealDTO
import com.uxstate.yummies.domain.model.Meal

//dto to Meal model class

fun MealDTO.toModel(): Meal {


    return Meal(
            id = this.idMeal.toInt(),
            name = this.strMeal,
            category = this.strCategory,
            origin = this.strArea,
            directions = this.strInstructions,
            imageUrl = this.strMeal,
            ingredients = combineIngredientsToAList(this),
            units = listOf(),
            tags = listOf()
    )
}


fun combineIngredientsToAList(mealDTO: MealDTO): List<String> {


    val ing1 = mealDTO.strIngredient1 ?: ""
    val ing2 = mealDTO.strIngredient2 ?: ""
    val ing3 = mealDTO.strIngredient3 ?: ""
    val ing4 = mealDTO.strIngredient4 ?: ""
    val ing5 = mealDTO.strIngredient5 ?: ""
    val ing6 = mealDTO.strIngredient6 ?: ""
    val ing7 = mealDTO.strIngredient7 ?: ""
    val ing8 = mealDTO.strIngredient8 ?: ""
    val ing9 = mealDTO.strIngredient9 ?: ""
    val ing10 = mealDTO.strIngredient10 ?: ""
    val ing11 = mealDTO.strIngredient11 ?: ""
    val ing12 = mealDTO.strIngredient12 ?: ""
    val ing13 = mealDTO.strIngredient13 ?: ""
    val ing14 = mealDTO.strIngredient14 ?: ""
    val ing15 = mealDTO.strIngredient15 ?: ""
    val ing16 = mealDTO.strIngredient16 ?: ""
    val ing17 = mealDTO.strIngredient17 ?: ""
    val ing18 = mealDTO.strIngredient18 ?: ""
    val ing19 = mealDTO.strIngredient19 ?: ""
    val ing20 = mealDTO.strIngredient20 ?: ""



    return mutableListOf(
            ing1,
            ing2,
            ing3,
            ing4,
            ing5,
            ing6,
            ing7,
            ing8,
            ing9,
            ing10,
            ing11,
            ing12,
            ing13,
            ing14,
            ing15,
            ing16,
            ing17,
            ing18,
            ing19,
            ing20
    )


}