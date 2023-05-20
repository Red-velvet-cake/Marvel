package com.red_velvet.marvel.data.remote.dto


import com.google.gson.annotations.SerializedName

data class UrlDto(
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("url")
    val url: String? = null
)