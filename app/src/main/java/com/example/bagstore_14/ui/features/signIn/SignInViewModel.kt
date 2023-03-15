package com.example.bagstore_14.ui.features.signIn

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignInViewModel:ViewModel() {
    val email = MutableLiveData("")
    val password = MutableLiveData("")

        fun signInUser(){
        }
}