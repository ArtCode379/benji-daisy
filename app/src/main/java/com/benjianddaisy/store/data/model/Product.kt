package com.benjianddaisy.store.data.model

import androidx.annotation.DrawableRes

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val category: ProductCategory,
    val price: Double,
    @field:DrawableRes val imageRes: Int,
)