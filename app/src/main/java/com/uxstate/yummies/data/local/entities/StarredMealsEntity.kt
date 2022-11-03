package com.uxstate.yummies.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "starred_meals")
data class StarredMealsEntity( @PrimaryKey(autoGenerate = false)
                               val id: Int,
                               val name: String,
                               val category: String,
                               val origin: String,
                               val directions: String,
                               val imageUrl: String,
                               val ingredients: List<String>,
                               val units: List<String>,
                               val isFavorite: Boolean) 