package com.example.bagstore_14.ui.features.category


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bagstore_14.model.data.Ads
import com.example.bagstore_14.model.data.Product
import com.example.bagstore_14.model.repository.product.ProductRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class CategoryViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {
    val dataProducts = mutableStateOf<List<Product>>(listOf())

    fun loadDataByCategory(category: String) {

        viewModelScope.launch {

            val dataFromLocal = productRepository.getAllProductsByCategory(category)
            dataProducts.value = dataFromLocal

        }

    }

}