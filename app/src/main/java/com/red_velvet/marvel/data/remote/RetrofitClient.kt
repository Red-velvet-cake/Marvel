package com.red_velvet.marvel.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val client =
        OkHttpClient().newBuilder().addInterceptor(AuthorizationInterceptor()).build()

    private const val BASE_URL = "http://gateway.marvel.com/v1/public/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    val apiService = retrofit.create(MarvelService::class.java)

}