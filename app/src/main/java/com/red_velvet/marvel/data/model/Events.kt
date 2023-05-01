package com.red_velvet.marvel.data.model


import com.google.gson.annotations.SerializedName

data class Events(
    @SerializedName("available")
    val available: Int? = null,
    @SerializedName("collectionURI")
    val collectionURI: String? = null,
    @SerializedName("items")
    val items: List<Any?>? = null,
    @SerializedName("returned")
    val returned: Int? = null
)