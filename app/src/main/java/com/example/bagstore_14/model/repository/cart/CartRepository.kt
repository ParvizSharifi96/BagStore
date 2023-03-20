package com.example.bagstore_14.model.repository.cart

interface CartRepository {


    suspend fun addToCart(productId: String): Boolean
    suspend fun getCartSize(): Int



}