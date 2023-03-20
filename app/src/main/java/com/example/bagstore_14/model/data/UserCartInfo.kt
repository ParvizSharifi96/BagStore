package com.example.bagstore_14.model.data


data class UserCartInfo(
    val success: Boolean,
    val productList: List<Product>,
    val message: String,
    val totalPrice: Int
)