package com.red_velvet.marvel.data.model


import com.google.gson.annotations.SerializedName

data class ItemXX(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("resourceURI")
    val resourceURI: String? = null,
    @SerializedName("type")
    val type: String? = null
)