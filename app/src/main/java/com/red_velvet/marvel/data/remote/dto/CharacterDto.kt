package com.red_velvet.marvel.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.red_velvet.marvel.data.remote.dto.*

data class CharacterDto(
    @SerializedName("comics")
    val comics: ResourceCollectionDto? = ResourceCollectionDto(),
    @SerializedName("description")
    val description: String?,
    @SerializedName("events")
    val events: ResourceCollectionDto? = ResourceCollectionDto(),
    @SerializedName("id")
    val id: Int?,
    @SerializedName("modified")
    val modified: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("resourceURI")
    val resourceURI: String?,
    @SerializedName("series")
    val series: ResourceCollectionDto? = ResourceCollectionDto(),
    @SerializedName("stories")
    val stories: ResourceCollectionDto? = ResourceCollectionDto(),
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailDto?,
    @SerializedName("urls")
    val urls: List<UrlDto?>
)