package com.red_velvet.marvel.data.model


import com.google.gson.annotations.SerializedName

data class Variant(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("resourceURI")
    val resourceURI: String? = null
)