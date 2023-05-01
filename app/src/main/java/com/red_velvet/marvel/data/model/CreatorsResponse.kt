package com.red_velvet.marvel.data.model

import com.google.gson.annotations.SerializedName

data class CreatorsResponse(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("firstName")
    val firstName: String? = "",
    @SerializedName("lastName")
    val lastName: String? = "",
    @SerializedName("fullName")
    val fullName: String? = "",
    @SerializedName("modified")
    val modified: String? = "",
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail? = Thumbnail(),
    @SerializedName("resourceURI")
    val resourceURI: String? = "",
    @SerializedName("comics")
    val comics: Content? = Content(),
    @SerializedName("series")
    val series: Content? = Content(),
    @SerializedName("stories")
    val stories: Content? = Content(),
    @SerializedName("events")
    val events: Content? = Content(),
    @SerializedName("urls")
    val urls: List<Url>? = listOf(),
)
