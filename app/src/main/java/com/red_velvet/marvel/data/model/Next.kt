package com.red_velvet.marvel.data.model


import com.google.gson.annotations.SerializedName

data class Next(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("resourceURI")
    val resourceURI: String? = null
)