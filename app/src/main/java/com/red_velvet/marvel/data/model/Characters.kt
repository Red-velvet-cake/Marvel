package com.red_velvet.marvel.data.model


import com.google.gson.annotations.SerializedName

data class Characters(
    @SerializedName("available")
    val available: Int? = null,
    @SerializedName("collectionURI")
    val collectionURI: String? = null,
    @SerializedName("items")
    val items: List<CharacterItem?>? = null,
    @SerializedName("returned")
    val returned: Int? = null
)