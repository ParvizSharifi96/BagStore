package com.example.bagstore_14.model.repository.product

import com.example.bagstore_14.model.data.Ads
import com.example.bagstore_14.model.data.Product

interface ProductRepository {

  suspend  fun getAllProducts() : List<Product>

    suspend fun getAllAds() : List<Ads>




}