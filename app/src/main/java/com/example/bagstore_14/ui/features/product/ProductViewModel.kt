package com.example.bagstore_14.ui.features.product


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bagstore_14.model.data.Comment
import com.example.bagstore_14.model.repository.comment.CommentRepository
import com.example.bagstore_14.model.repository.product.ProductRepository
import com.example.bagstore_14.util.EMPTY_PRODUCT
import com.example.bagstore_14.util.coroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ProductViewModel(
    private val protectedRepository: ProductRepository,
    private val commentRepository: CommentRepository
) : ViewModel() {
    val thisProduct = mutableStateOf(EMPTY_PRODUCT)
    val comments = mutableStateOf(listOf<Comment>())

    fun loadData(productId: String, isInternetConnected: Boolean) {
        loadProductFromCache(productId)
        if (isInternetConnected) {
            loadAllComments(productId)
        }
    }


    private fun loadProductFromCache(productId: String) {

        viewModelScope.launch(coroutineExceptionHandler) {


            thisProduct.value = protectedRepository.getProductById(productId)
        }


    }

    private fun loadAllComments(productId: String) {

        viewModelScope.launch(coroutineExceptionHandler) {


            comments.value = commentRepository.getAllComments(productId)

        }
    }


    fun addNewComment(productId: String, text: String, IsSuccess: (String) -> Unit) {
        viewModelScope.launch(coroutineExceptionHandler) {
            commentRepository.addNewComments(productId, text, IsSuccess)
            delay(100)
            comments.value =  commentRepository.getAllComments(productId)
        }
    }
}