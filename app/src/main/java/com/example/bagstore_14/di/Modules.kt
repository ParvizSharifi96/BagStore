package com.example.bagstore_14.di

import android.content.Context
import com.example.bagstore_14.model.net.createApiService
import com.example.bagstore_14.model.repository.user.UserRepository
import com.example.bagstore_14.model.repository.user.UserRepositoryImpl
import com.example.bagstore_14.ui.features.SignUp.SignUpViewModel
import com.example.bagstore_14.ui.features.signIn.SignInViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModules = module {
    single { androidContext().getSharedPreferences("data" , Context.MODE_PRIVATE) }
    single {createApiService() }
    single<UserRepository> { UserRepositoryImpl(get() ,get() ) }

    viewModel{SignUpViewModel(get())}
    viewModel{ SignInViewModel(get()) }


}