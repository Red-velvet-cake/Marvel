package com.red_velvet.marvel.data.model


import com.google.gson.annotations.SerializedName

data class Resource(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("resourceURI")
    val resourceURI: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("role")
    val role: String? = null
)