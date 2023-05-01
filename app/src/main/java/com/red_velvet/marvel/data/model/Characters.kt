package com.red_velvet.marvel.data.model


import com.google.gson.annotations.SerializedName

data class Characters(
    @SerializedName("available")
    val available: Int?,
    @SerializedName("collectionURI")
    val collectionURI: String?,
    @SerializedName("items")
    val items: List<Any?>?,
    @SerializedName("returned")
    val returned: Int?
)