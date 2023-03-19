package com.example.bagstore_14.model.net


import com.example.bagstore_14.model.data.*
import com.example.bagstore_14.model.repository.TokenInMemory
import com.example.bagstore_14.util.BASE_URL
import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("signUp")
    suspend fun signUp(@Body jsonObject: JsonObject): LoginResponse

    @POST("signIn")
    suspend fun signIn(@Body jsonObject: JsonObject): LoginResponse

    @GET("refreshToken")
    fun refreshToken(): retrofit2.Call<LoginResponse>

    @GET("getProducts")
    suspend fun getAllProducts() : ProductResponse

    @GET("getSliderPics")
    suspend fun getAllAds() : AdsResponse


    @POST("getComments")
    suspend fun getAllComments(@Body jsonObject: JsonObject):CommentResponse


    @POST("getNewComments")
    suspend fun addNewComments(@Body jsonObject: JsonObject):AddNewCommentResponse


    @POST("addToCart")
    suspend fun addProductToCart(@Body jsonObject: JsonObject) : CartResponse
}


fun createApiService():ApiService{

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor {

            val oldRequest = it.request()
            val newRequest = oldRequest.newBuilder()
            if (TokenInMemory.token!= null)
                newRequest.addHeader("Authorization", TokenInMemory.token!!)
            newRequest.addHeader("Accept" , "application/json")
            newRequest.method(oldRequest.method , oldRequest.body)
            return@addInterceptor it.proceed(newRequest.build())


        }.build()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    return retrofit.create(ApiService::class.java)


}