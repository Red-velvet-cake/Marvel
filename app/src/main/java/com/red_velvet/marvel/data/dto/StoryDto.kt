package com.red_velvet.marvel.data.dto

import com.google.gson.annotations.SerializedName

data class StoryDto(
    @SerializedName("characters")
    val characters: ResourceCollectionDto? = ResourceCollectionDto(),
    @SerializedName("comics")
    val comics: ResourceCollectionDto? = ResourceCollectionDto(),
    @SerializedName("creators")
    val creators: ResourceCollectionDto? = ResourceCollectionDto(),
    @SerializedName("description")
    val description: String,
    @SerializedName("events")
    val events: ResourceCollectionDto? = ResourceCollectionDto(),
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("modified")
    val modified: String? = null,
    @SerializedName("originalIssue")
    val originalIssue: ResourceDto? = ResourceDto(),
    @SerializedName("resourceURI")
    val resourceURI: String? = null,
    @SerializedName("series")
    val series: ResourceCollectionDto? = ResourceCollectionDto(),
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailDto? = ThumbnailDto(),
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("type")
    val type: String? = null
)