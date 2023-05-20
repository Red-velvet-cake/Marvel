package com.red_velvet.marvel.data.remote.dtos

import com.google.gson.annotations.SerializedName

data class Creator(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("firstName")
    val firstName: String? = null,
    @SerializedName("middleName")
    val middleName: String? = null,
    @SerializedName("lastName")
    val lastName: String? = null,
    @SerializedName("suffix")
    val suffix: String? = null,
    @SerializedName("fullName")
    val fullName: String? = null,
    @SerializedName("modified")
    val modified: String? = null,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail? = Thumbnail(),
    @SerializedName("resourceURI")
    val resourceURI: String? = null,
    @SerializedName("comics")
    val comics: ResourceCollection? = ResourceCollection(),
    @SerializedName("series")
    val series: ResourceCollection? = ResourceCollection(),
    @SerializedName("stories")
    val stories: ResourceCollection? = ResourceCollection(),
    @SerializedName("events")
    val events: ResourceCollection? = ResourceCollection(),
    @SerializedName("urls")
    val urls: List<Url> = listOf()
)