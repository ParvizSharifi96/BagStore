package com.example.bagstore_14.model.repository.product

import com.example.bagstore_14.model.data.Ads
import com.example.bagstore_14.model.data.Product
import com.example.bagstore_14.model.db.ProductDao
import com.example.bagstore_14.model.net.ApiService

class ProductRepositoryImpl(
    private val apiService: ApiService,
    private val productDao: ProductDao
    ) : ProductRepository {
    override suspend fun getAllProducts(isInternetConnected : Boolean): List<Product> {


        if(isInternetConnected) {

            // get data from net
            val dataFromServer = apiService.getAllProducts()
            if(dataFromServer.success) {
                productDao.insertOrUpdate(dataFromServer.products)
                return dataFromServer.products
            }

        } else {

            // get data from local
            return productDao.getAll()

        }

        return listOf()
    }

    override suspend fun getAllAds(isInternetConnected : Boolean): List<Ads> {
        if(isInternetConnected) {

            // get ads

            val dataFromServer = apiService.getAllAds()
            if(dataFromServer.success) {
                return dataFromServer.ads
            }

        }


        return listOf()
    }

    override suspend fun getAllProductByCategory(category: String): List<Product> {
        return productDao.getAllByCategory(category)
    }


}