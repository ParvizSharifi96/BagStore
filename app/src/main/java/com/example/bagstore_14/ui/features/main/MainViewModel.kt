package com.example.bagstore_14.ui.features.main


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bagstore_14.model.data.Ads
import com.example.bagstore_14.model.data.Product
import com.example.bagstore_14.model.repository.cart.CartRepository
import com.example.bagstore_14.model.repository.product.ProductRepository
import com.example.bagstore_14.util.coroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainViewModel(


    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository,
    isInternetConnected: Boolean

) : ViewModel() {

    val dataProducts = mutableStateOf<List<Product>>(listOf())
    val dataAds = mutableStateOf<List<Ads>>(listOf())
    val showProgressBar = mutableStateOf(false)
    val badgeNumber = mutableStateOf(0)

    init {


        refreshAllDataFromNet(isInternetConnected)
    }
    private fun refreshAllDataFromNet(isInternetConnected: Boolean) {

        viewModelScope.launch(coroutineExceptionHandler ){
            if (isInternetConnected)
                showProgressBar.value = true
            delay(1500)

            val newDataProducts=async { productRepository.getAllProducts(isInternetConnected) }
            val newDataAds = async { productRepository.getAllAds(isInternetConnected)  }


            updateData(newDataProducts.await() , newDataAds.await())
            showProgressBar.value = false
        }



    }

    private fun updateData(products : List<Product>, ads : List<Ads>) {


        dataProducts.value = products
        dataAds . value = ads

    }


     fun loadBadgeNumber(){
        viewModelScope.launch(coroutineExceptionHandler) {
            badgeNumber.value = cartRepository.getCartSize()
        }

    }


}