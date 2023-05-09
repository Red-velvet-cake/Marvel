package com.red_velvet.marvel.ui.utils

import com.red_velvet.marvel.BuildConfig
import java.security.MessageDigest
import kotlin.text.Charsets.UTF_8

object SecurityUtil {

    const val publicKey: String = BuildConfig.PUBLIC_KEY
    private const val privateKey: String = BuildConfig.PRIVATE_KEY

    fun generateHash(timeStamp: String): String {
        val input = timeStamp + privateKey + publicKey
        val bytes = MessageDigest.getInstance("MD5").digest(input.toByteArray(UTF_8))
        return bytes.joinToString("") { "%02x".format(it) }
    }
}