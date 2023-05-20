package com.red_velvet.marvel.data.remote.dtos

import com.google.gson.annotations.SerializedName

data class Story(
    @SerializedName("characters")
    val characters: ResourceCollection? = ResourceCollection(),
    @SerializedName("comics")
    val comics: ResourceCollection? = ResourceCollection(),
    @SerializedName("creators")
    val creators: ResourceCollection? = ResourceCollection(),
    @SerializedName("description")
    val description: String,
    @SerializedName("events")
    val events: ResourceCollection? = ResourceCollection(),
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("modified")
    val modified: String? = null,
    @SerializedName("originalIssue")
    val originalIssue: Resource? = Resource(),
    @SerializedName("resourceURI")
    val resourceURI: String? = null,
    @SerializedName("series")
    val series: ResourceCollection? = ResourceCollection(),
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail? = Thumbnail(),
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("type")
    val type: String? = null
)