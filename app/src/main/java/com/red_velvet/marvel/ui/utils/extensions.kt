package com.red_velvet.marvel.ui.utils

import android.view.View
import com.red_velvet.marvel.data.model.Thumbnail
import java.security.MessageDigest

fun Thumbnail.toUrl() = this.path + "." + this.extension

fun View.hideView() {
    visibility = View.GONE
}

fun View.showView() {
    visibility = View.VISIBLE
}

fun String.md5(): String {
    val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray(Charsets.UTF_8))
    return bytes.joinToString("") { "%02x".format(it) }
}