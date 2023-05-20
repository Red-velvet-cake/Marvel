package com.red_velvet.marvel.ui.utils

import android.view.View
import com.red_velvet.marvel.data.remote.dtos.Thumbnail

fun Thumbnail.toUrl() = this.path + "." + this.extension

fun View.hideView() {
    visibility = View.GONE
}

fun View.showView() {
    visibility = View.VISIBLE
}