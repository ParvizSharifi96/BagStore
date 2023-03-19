package com.example.bagstore_14.model.repository.comment

import com.example.bagstore_14.model.data.Comment
import com.example.bagstore_14.model.net.ApiService
import com.google.gson.JsonObject

class CommentRepositoryImpl(
    private val apiService: ApiService
) : CommentRepository {
    override suspend fun getAllComments(productId: String): List<Comment> {
        val jsonObject = JsonObject().apply {

            addProperty("productId", productId)
        }

        val data = apiService.getAllComments(jsonObject)
        if (data.success) {
            return data.comments
        }

        return listOf()
    }

    override suspend fun addNewComments(productId: String, text: String, IsSuccess: (String) -> Unit) {
        val jsonObject = JsonObject().apply {
            addProperty("productId" , productId)
            addProperty("text" , text)
        }
        val result = apiService.addNewComments(jsonObject)
        if (result.success){
            IsSuccess.invoke("Comment not added ")

        }


    }


}