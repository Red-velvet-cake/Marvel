package com.red_velvet.marvel.ui.utils

import android.view.View
import com.red_velvet.marvel.data.dto.ThumbnailDto

fun ThumbnailDto.toUrl() = this.path + "." + this.extension

fun View.hideView() {
    visibility = View.GONE
}

fun View.showView() {
    visibility = View.VISIBLE
}