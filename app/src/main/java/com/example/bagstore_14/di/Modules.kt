package com.example.bagstore_14.di

import android.content.Context
import androidx.room.Room
import com.example.bagstore_14.model.db.AppDatabase
import com.example.bagstore_14.model.net.createApiService
import com.example.bagstore_14.model.repository.cart.CartRepository
import com.example.bagstore_14.model.repository.cart.CartRepositoryImpl
import com.example.bagstore_14.model.repository.comment.CommentRepository
import com.example.bagstore_14.model.repository.comment.CommentRepositoryImpl
import com.example.bagstore_14.model.repository.product.ProductRepository
import com.example.bagstore_14.model.repository.product.ProductRepositoryImpl
import com.example.bagstore_14.model.repository.user.UserRepository
import com.example.bagstore_14.model.repository.user.UserRepositoryImpl
import com.example.bagstore_14.ui.features.SignUp.SignUpViewModel
import com.example.bagstore_14.ui.features.category.CategoryViewModel
import com.example.bagstore_14.ui.features.main.MainViewModel
import com.example.bagstore_14.ui.features.product.ProductViewModel
import com.example.bagstore_14.ui.features.signIn.SignInViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModules = module {
    single { androidContext().getSharedPreferences("data", Context.MODE_PRIVATE) }
    single { createApiService() }

    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "app_dataBase.db").build()
    }

    single<UserRepository> { UserRepositoryImpl(get(), get()) }
    single<ProductRepository> {
        ProductRepositoryImpl(
            get(),
            get<AppDatabase>().productDao(),

            )
    }
    single<CommentRepository> { CommentRepositoryImpl(get()) }
    single<CartRepository> { CartRepositoryImpl(get()) }

    viewModel { ProductViewModel(get() , get(), get()) }
    viewModel { SignUpViewModel(get()) }
    viewModel { SignInViewModel(get()) }
    viewModel { (isNetConnected: Boolean) -> MainViewModel(get(),get() , isNetConnected) }
    viewModel { CategoryViewModel(get()) }

}