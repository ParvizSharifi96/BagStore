package com.example.bagstore_14.ui.features.signIn

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bagstore_14.model.repository.user.UserRepository

class SignInViewModel(private val userRepository: UserRepository):ViewModel() {
    val email = MutableLiveData("")
    val password = MutableLiveData("")

        fun signInUser(){
        }
}