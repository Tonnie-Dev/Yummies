package com.uxstate.yummies.data.mapper

import com.uxstate.yummies.data.local.entities.CategoryEntity
import com.uxstate.yummies.data.local.entities.MealEntity
import com.uxstate.yummies.data.local.entities.StarredMealEntity
import com.uxstate.yummies.data.remote.dto.CategoryDTO
import com.uxstate.yummies.data.remote.dto.MealDTO
import com.uxstate.yummies.domain.model.Category
import com.uxstate.yummies.domain.model.Meal

// dto to Meal model class

fun MealDTO.toModel(): Meal {

    return Meal(
        id = this.idMeal.toInt(),
        name = this.strMeal,
        category = this.strCategory,
        origin = this.strArea,
        directions = this.strInstructions,
        imageUrl = this.strMeal,
        ingredients = combineStrIngredientsToList(this),
        units = combineStrMeasuresToList(this),
        isFavorite = false
    )
}

// dto to Category model class
fun CategoryDTO.toModel(): Category {

    return Category(
        categoryId = this.idCategory.toInt(),
        categoryType = this.strCategory,
        categoryDescription = this.strCategoryDescription,
        categoryImageUrl = this.strCategoryThumb
    )
}

// entity to Meal model class
fun MealEntity.toModel(): Meal {

    return Meal(
        id = this.id,
        name = this.name,
        category = this.category,
        origin = this.origin,
        directions = this.directions,
        imageUrl = this.imageUrl,
        ingredients = this.ingredients,
        units = this.units,
        isFavorite = this.isFavorite
    )
}

// entity to Category model class

fun CategoryEntity.toModel(): Category {

    return Category(
        categoryId = this.categoryId,
        categoryType = this.categoryType,
        categoryDescription = this.categoryDescription,
        categoryImageUrl = this.categoryImageUrl
    )
}

// dto to MealEntity entity
fun MealDTO.toEntity(): MealEntity {
    return MealEntity(
        id = this.idMeal.toInt(),
        name = this.strMeal,
        category = this.strCategory,
        origin = this.strArea,
        directions = this.strInstructions,
        imageUrl = this.strMealThumb,
        ingredients = combineStrIngredientsToList(this),
        units = combineStrMeasuresToList(this),
        isFavorite = false
    )
}

// dto to CategoryEntity entity
fun CategoryDTO.toEntity(): CategoryEntity {

    return CategoryEntity(
        categoryId = this.idCategory.toInt(),
        categoryType = this.strCategory,
        categoryDescription = this.strCategoryDescription,
        categoryImageUrl = this.strCategoryThumb
    )
}

//model to StarredMealEntity entity
fun Meal.toStarredEntity():StarredMealEntity {
    return StarredMealEntity(
            id = this.id,
            name = this.name,
            category = this.category,
            origin = this.origin,
            directions = this.directions,
            imageUrl = this.imageUrl,
            ingredients = this.ingredients,
            units = this.units,
            isFavorite = true
    )
}

// helper function 1
fun combineStrIngredientsToList(mealDTO: MealDTO): List<String> {

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

    val fullList = mutableListOf(
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

    return fullList.filterNot { ing -> ing == "" }
}

// helper function 2
fun combineStrMeasuresToList(mealDTO: MealDTO): List<String> {

    val msr1 = mealDTO.strMeasure1 ?: ""
    val msr2 = mealDTO.strMeasure2 ?: ""
    val msr3 = mealDTO.strMeasure3 ?: ""
    val msr4 = mealDTO.strMeasure4 ?: ""
    val msr5 = mealDTO.strMeasure5 ?: ""
    val msr6 = mealDTO.strMeasure6 ?: ""
    val msr7 = mealDTO.strMeasure7 ?: ""
    val msr8 = mealDTO.strMeasure8 ?: ""
    val msr9 = mealDTO.strMeasure9 ?: ""
    val msr10 = mealDTO.strMeasure10 ?: ""
    val msr11 = mealDTO.strMeasure11 ?: ""
    val msr12 = mealDTO.strMeasure12 ?: ""
    val msr13 = mealDTO.strMeasure13 ?: ""
    val msr14 = mealDTO.strMeasure14 ?: ""
    val msr15 = mealDTO.strMeasure15 ?: ""
    val msr16 = mealDTO.strMeasure16 ?: ""
    val msr17 = mealDTO.strMeasure17 ?: ""
    val msr18 = mealDTO.strMeasure18 ?: ""
    val msr19 = mealDTO.strMeasure19 ?: ""
    val msr20 = mealDTO.strMeasure20 ?: ""

    val fullList = mutableListOf(
        msr1,
        msr2,
        msr3,
        msr4,
        msr5,
        msr6,
        msr7,
        msr8,
        msr9,
        msr10,
        msr11,
        msr12,
        msr13,
        msr14,
        msr15,
        msr16,
        msr17,
        msr18,
        msr19,
        msr20
    )

    return fullList.filterNot { msr -> msr == "" }
}