package com.red_velvet.marvel.data.model

import com.google.gson.annotations.SerializedName

data class ComicCreatorResponse(
    @SerializedName("comics")
    val comics:Content? = Content(),
    @SerializedName("events")
    val events: Content? = Content(),
    @SerializedName("firstName")
    val firstName: String="",
    @SerializedName("fullName")
    val fullName: String="",
    @SerializedName("id")
    val id: Int=0,
    @SerializedName("lastName")
    val lastName: String="",
    @SerializedName("middleName")
    val middleName: String="",
    @SerializedName("modified")
    val modified: String="",
    @SerializedName("resourceURI")
    val resourceURI: String="",
    @SerializedName("series")
    val series: Content? = Content(),
    @SerializedName("stories")
    val stories: Content? = Content(),
    @SerializedName("suffix")
    val suffix: String="",
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail?=Thumbnail(),
    @SerializedName("urls")
    val urls: List<Url>?
)
