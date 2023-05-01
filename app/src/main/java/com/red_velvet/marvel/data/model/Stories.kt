package com.red_velvet.marvel.data.model


import com.google.gson.annotations.SerializedName
import com.red_velvet.marvel.data.model.ItemX

data class Stories(
    @SerializedName("available")
    val available: Int?,
    @SerializedName("collectionURI")
    val collectionURI: String?,
    @SerializedName("items")
    val items: List<ItemX>?,
    @SerializedName("returned")
    val returned: Int?
)