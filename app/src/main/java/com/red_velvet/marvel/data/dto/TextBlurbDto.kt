package com.red_velvet.marvel.data.dto


import com.google.gson.annotations.SerializedName

data class TextBlurbDto(
    @SerializedName("language")
    val language: String? = null,
    @SerializedName("text")
    val text: String? = null,
    @SerializedName("type")
    val type: String? = null
)