package com.example.bagstore_14.model.repository.cart

import com.example.bagstore_14.model.net.ApiService
import com.google.gson.JsonObject


class CartRepositoryImpl(
    private val apiService: ApiService
) : CartRepository {
    override suspend fun addToCart(productId: String): Boolean {

        val jsonObject = JsonObject().apply {
            addProperty("productId", productId)
        }

        val result = apiService.addProductToCart(jsonObject)
        return result.success
    }



}