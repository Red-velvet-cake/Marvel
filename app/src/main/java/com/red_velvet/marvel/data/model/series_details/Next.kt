package com.red_velvet.marvel.data.model.series_details


import com.google.gson.annotations.SerializedName

data class Next(
    @SerializedName("name")
    val name: String?,
    @SerializedName("resourceURI")
    val resourceURI: String?
)