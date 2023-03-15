package com.example.bagstore_14.ui.features.SignUp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bagstore_14.model.repository.user.UserRepository

class SignUpViewModel(private val userRepository: UserRepository):ViewModel() {

    val name = MutableLiveData("")
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val confirmPassword = MutableLiveData("")

        fun signUpUser(){


        }

}