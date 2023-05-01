package com.red_velvet.marvel.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val url = request.url.newBuilder()
            .addQueryParameter("apikey", "194ab147353122cab8c2a660639fc434")
            .addQueryParameter("hash", "3e6fb23bb896e05f25121a20a9b160db")
            .addQueryParameter("ts", "1")
            .build()

        val newRequest = request.newBuilder().url(url).build()

        return chain.proceed(newRequest)
    }

}