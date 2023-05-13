package com.red_velvet.marvel.data.util

import java.security.MessageDigest
import kotlin.text.Charsets.UTF_8

object SecurityUtil {
    fun generateHash(publicKey: String, privateKey: String, timeStamp: String): String {
        val input = timeStamp + privateKey + publicKey
        val bytes = MessageDigest.getInstance("MD5").digest(input.toByteArray(UTF_8))
        return bytes.joinToString("") { "%02x".format(it) }
    }
}