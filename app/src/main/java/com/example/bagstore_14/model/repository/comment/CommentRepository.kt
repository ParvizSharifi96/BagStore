package com.example.bagstore_14.model.repository.comment

import com.example.bagstore_14.model.data.Comment


interface CommentRepository {


    suspend fun getAllComments(productId : String) : List<Comment>
    suspend fun addNewComments(productId : String ,  text :String , IsSuccess : (String) -> Unit)




}