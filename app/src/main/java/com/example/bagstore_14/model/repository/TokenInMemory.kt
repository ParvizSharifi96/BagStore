package com.example.bagstore_14.model.repository

object TokenInMemory {

    var username: String? = null
        private set

    var token: String? = null
        private set


    fun  refreshToken(username: String? , newToken:String?){

        this.username = username
        this.token = newToken
    }
}