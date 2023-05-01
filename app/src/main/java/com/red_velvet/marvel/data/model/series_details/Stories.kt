package com.red_velvet.marvel.data.model.series_details


import com.google.gson.annotations.SerializedName

data class Stories(
    @SerializedName("available")
    val available: Int?,
    @SerializedName("collectionURI")
    val collectionURI: String?,
    @SerializedName("items")
    val items: List<Story>?,
    @SerializedName("returned")
    val returned: Int?
)