package com.uxstate.yummies.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories_table")
data class CategoryEntity(
    @PrimaryKey
    val categoryId: Int,
    val categoryType: String,
    val categoryDescription: String,
    val categoryImageUrl: String
)