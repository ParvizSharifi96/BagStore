package com.example.bagstore_14.di

import com.example.bagstore_14.ui.features.SignUp.SignUpViewModel
import com.example.bagstore_14.ui.features.signIn.SignInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModules = module {

    viewModel{SignUpViewModel()}
    viewModel{ SignInViewModel() }


}