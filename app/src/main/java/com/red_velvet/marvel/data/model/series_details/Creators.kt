package com.red_velvet.marvel.data.model.series_details


import com.google.gson.annotations.SerializedName

data class Creators(
    @SerializedName("available")
    val available: Int?,
    @SerializedName("collectionURI")
    val collectionURI: String?,
    @SerializedName("items")
    val items: List<Creator>?,
    @SerializedName("returned")
    val returned: Int?
)