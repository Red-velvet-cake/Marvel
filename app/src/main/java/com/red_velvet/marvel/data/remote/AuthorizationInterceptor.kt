package com.red_velvet.marvel.data.remote

import com.red_velvet.marvel.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest

class AuthorizationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val ts = BuildConfig.TIME_STAMP
        val publicKey = BuildConfig.PUBLIC_KEY
        val privateKey = BuildConfig.PRIVATE_KEY

        val url = request.url.newBuilder()
            .addQueryParameter("apikey", "$publicKey")
            .addQueryParameter("hash", "653d56017d0140a6a351d88dda97fc15")
            .addQueryParameter("ts", "$ts")
            .build()

        val newRequest = request.newBuilder().url(url).build()

        return chain.proceed(newRequest)
    }


}