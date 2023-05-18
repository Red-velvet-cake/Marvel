package com.red_velvet.marvel.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CreatorDto(
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
    val thumbnail: ThumbnailDto? = ThumbnailDto(),
    @SerializedName("resourceURI")
    val resourceURI: String? = null,
    @SerializedName("comics")
    val comics: ResourceCollectionDto? = ResourceCollectionDto(),
    @SerializedName("series")
    val series: ResourceCollectionDto? = ResourceCollectionDto(),
    @SerializedName("stories")
    val stories: ResourceCollectionDto? = ResourceCollectionDto(),
    @SerializedName("events")
    val events: ResourceCollectionDto? = ResourceCollectionDto(),
    @SerializedName("urls")
    val urls: List<UrlDto> = listOf()
)