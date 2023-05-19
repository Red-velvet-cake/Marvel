package com.red_velvet.marvel.data.model


import com.google.gson.annotations.SerializedName

data class EventDto(
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
    val creators: ResourceCollection? = ResourceCollection(),
    @SerializedName("characters")
    val characters: ResourceCollection? = ResourceCollection(),
    @SerializedName("stories")
    val stories: ResourceCollection? = ResourceCollection(),
    @SerializedName("comics")
    val comics: ResourceCollection? = ResourceCollection(),
    @SerializedName("series")
    val series: ResourceCollection? = ResourceCollection(),
)