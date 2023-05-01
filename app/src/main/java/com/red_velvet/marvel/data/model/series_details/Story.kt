package com.red_velvet.marvel.data.model.series_details


import com.google.gson.annotations.SerializedName

data class Story(
    @SerializedName("name")
    val name: String?,
    @SerializedName("resourceURI")
    val resourceURI: String?,
    @SerializedName("type")
    val type: String?
)