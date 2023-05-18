package com.red_velvet.marvel.data.dto


import com.google.gson.annotations.SerializedName

data class ThumbnailDto(
    @SerializedName("extension")
    val extension: String? = null,
    @SerializedName("path")
    val path: String? = null
)