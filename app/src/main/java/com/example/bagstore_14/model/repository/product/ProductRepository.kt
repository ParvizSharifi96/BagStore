package com.example.bagstore_14.model.repository.product

import com.example.bagstore_14.model.data.Ads
import com.example.bagstore_14.model.data.Product

interface ProductRepository {

  suspend  fun getAllProducts(isInternetConnected : Boolean) : List<Product>

    suspend fun getAllAds(isInternetConnected : Boolean) : List<Ads>




}