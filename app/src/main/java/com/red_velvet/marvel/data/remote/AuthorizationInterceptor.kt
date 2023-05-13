package com.red_velvet.marvel.data.remote


import com.red_velvet.marvel.BuildConfig
import com.red_velvet.marvel.data.util.SecurityUtil
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {

    private val publicKey: String = BuildConfig.PUBLIC_KEY
    private val privateKey: String = BuildConfig.PRIVATE_KEY

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val timeStamp = System.currentTimeMillis().toString()
        val hash = SecurityUtil.generateHash(publicKey, privateKey, timeStamp)

        val newRequest = request.newBuilder()
            .url(buildUrl(request, timeStamp, hash))
            .build()
        return chain.proceed(newRequest)
    }

    private fun buildUrl(request: Request, timeStamp: String, hash: String): HttpUrl {
        return request.url.newBuilder()
            .addQueryParameter(API_KEY, publicKey)
            .addQueryParameter(HASH, hash)
            .addQueryParameter(TS, timeStamp)
            .build()
    }

    companion object {
        private const val API_KEY = "apikey"
        private const val HASH = "hash"
        private const val TS = "ts"
    }
}