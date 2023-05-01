package com.red_velvet.marvel.data.model


import com.google.gson.annotations.SerializedName

data class EventsResponse(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("resourceURI")
    val resourceURI: String? = "",
    @SerializedName("urls")
    val urls: List<Url>? = listOf(),
    @SerializedName("modified")
    val modified: String? = "",
    @SerializedName("start")
    val start: String? = "",
    @SerializedName("end")
    val end: String? = "",
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail? = Thumbnail(),
    @SerializedName("creators")
    val creators: Content? = Content(),
    @SerializedName("characters")
    val characters: Content? = Content(),
    @SerializedName("stories")
    val stories: Content? = Content(),
    @SerializedName("comics")
    val comics: Content? = Content(),
    @SerializedName("series")
    val series: Content? = Content(),
)