package com.example.bagstore_14.ui.features.product


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bagstore_14.model.repository.product.ProductRepository
import com.example.bagstore_14.util.EMPTY_PRODUCT
import com.example.bagstore_14.util.coroutineExceptionHandler
import kotlinx.coroutines.launch


class ProductViewModel(
    private val protectedRepository: ProductRepository
) : ViewModel() {
    val thisProduct = mutableStateOf(EMPTY_PRODUCT)
    fun loadData(productId: String){
        loadProductFromCache(productId)
    }


   private fun loadProductFromCache(productId: String) {

        viewModelScope.launch(coroutineExceptionHandler) {


           thisProduct.value = protectedRepository.getProductById(productId)
        }


    }


}