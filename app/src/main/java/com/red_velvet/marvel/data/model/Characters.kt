package com.red_velvet.marvel.data.model


import com.google.gson.annotations.SerializedName

data class Characters(
    @SerializedName("available")
    val available: Int? = 0,
    @SerializedName("collectionURI")
    val collectionURI: String? = "",
    @SerializedName("items")
    val items: List<Item>? = listOf(),
    @SerializedName("returned")
    val returned: Int? = 0
)