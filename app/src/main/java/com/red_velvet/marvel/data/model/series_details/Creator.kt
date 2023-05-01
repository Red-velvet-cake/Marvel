package com.red_velvet.marvel.data.model.series_details


import com.google.gson.annotations.SerializedName

data class Creator(
    @SerializedName("name")
    val name: String?,
    @SerializedName("resourceURI")
    val resourceURI: String?,
    @SerializedName("role")
    val role: String?
)