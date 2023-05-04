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
        val hash = md5Hashing("$ts$privateKey$publicKey")

        val url = request.url.newBuilder()
            .addQueryParameter("apikey", "$publicKey")
            .addQueryParameter("hash", "$hash")
            .addQueryParameter("ts", "$ts")
            .build()

        val newRequest = request.newBuilder().url(url).build()

        return chain.proceed(newRequest)
    }
    private fun md5Hashing(input:String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }

}