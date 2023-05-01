package com.red_velvet.marvel.data.model.seriesResponse


import com.google.gson.annotations.SerializedName
import com.red_velvet.marvel.data.model.Item

data class Characters(
    @SerializedName("available")
    val available: Int?,
    @SerializedName("collectionURI")
    val collectionURI: String?,
    @SerializedName("items")
    val items: List<Item?>?,
    @SerializedName("returned")
    val returned: Int?
)