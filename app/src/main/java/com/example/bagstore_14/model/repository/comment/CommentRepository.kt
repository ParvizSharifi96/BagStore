package com.example.bagstore_14.model.repository.comment

import com.example.bagstore_14.model.data.Comment


interface CommentRepository {


    suspend fun getAllComments(productId : String) : List<Comment>



}