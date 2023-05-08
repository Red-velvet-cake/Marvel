package com.red_velvet.marvel.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val url = request.url.newBuilder()
            .addQueryParameter("apikey", "bed3380a9c72ce9cd974909412585352")
            .addQueryParameter("hash", "59cfb1f6824ff03db3832888c4787cc3")
            .addQueryParameter("ts", "1")
            .build()

        val newRequest = request.newBuilder().url(url).build()

        return chain.proceed(newRequest)
    }

}