package com.red_velvet.marvel.data.remote.dto


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
    val urls: List<UrlDto>? = listOf(),
    @SerializedName("modified")
    val modified: String? = "",
    @SerializedName("start")
    val start: String? = "",
    @SerializedName("end")
    val end: String? = "",
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailDto? = ThumbnailDto(),
    @SerializedName("creators")
    val creators: ResourceCollectionDto? = ResourceCollectionDto(),
    @SerializedName("characters")
    val characters: ResourceCollectionDto? = ResourceCollectionDto(),
    @SerializedName("stories")
    val stories: ResourceCollectionDto? = ResourceCollectionDto(),
    @SerializedName("comics")
    val comics: ResourceCollectionDto? = ResourceCollectionDto(),
    @SerializedName("series")
    val series: ResourceCollectionDto? = ResourceCollectionDto(),
)