package com.red_velvet.marvel.data.model.seriesResponse


import com.google.gson.annotations.SerializedName

data class Next(
    @SerializedName("name")
    val name: String?,
    @SerializedName("resourceURI")
    val resourceURI: String?
)