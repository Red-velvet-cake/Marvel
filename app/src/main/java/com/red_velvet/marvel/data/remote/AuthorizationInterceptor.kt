package com.red_velvet.marvel.data.remote


import com.red_velvet.marvel.ui.utils.SecurityUtil
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val timeStamp = System.currentTimeMillis().toString()
        val hash = SecurityUtil.generateHash(timeStamp)

        val newRequest = request.newBuilder()
            .url(buildUrl(request, timeStamp, hash))
            .build()
        return chain.proceed(newRequest)
    }

    private fun buildUrl(request: Request, timeStamp: String, hash: String): HttpUrl {
        return request.url.newBuilder()
            .addQueryParameter(API_KEY, SecurityUtil.publicKey)
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