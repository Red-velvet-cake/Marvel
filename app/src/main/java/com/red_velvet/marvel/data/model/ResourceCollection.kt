package com.red_velvet.marvel.data.model

import com.google.gson.annotations.SerializedName

data class ResourceCollection(
    @SerializedName("available")
    val available: Int? = 0,
    @SerializedName("collectionURI")
    val collectionURI: String? = null,
    @SerializedName("items")
    val items: List<Resource>? = listOf(),
    @SerializedName("returned")
    val returned: Int? = 0,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("resourceURI")
    val resourceURI: String? = null
)