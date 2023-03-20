package com.example.bagstore_14.ui.features.cart


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bagstore_14.model.data.CartResponse
import com.example.bagstore_14.model.data.Product
import com.example.bagstore_14.model.repository.cart.CartRepository
import com.example.bagstore_14.model.repository.product.ProductRepository
import kotlinx.coroutines.launch


class CartViewModel(
    private val cartRepository: CartRepository

) : ViewModel() {


}