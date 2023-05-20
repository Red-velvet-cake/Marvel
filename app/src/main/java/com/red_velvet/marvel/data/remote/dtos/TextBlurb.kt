package com.red_velvet.marvel.data.remote.dtos


import com.google.gson.annotations.SerializedName

data class TextBlurb(
    @SerializedName("language")
    val language: String? = null,
    @SerializedName("text")
    val text: String? = null,
    @SerializedName("type")
    val type: String? = null
)