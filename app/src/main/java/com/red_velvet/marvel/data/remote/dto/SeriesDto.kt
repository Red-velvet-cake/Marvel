package com.red_velvet.marvel.data.remote.dto


import com.google.gson.annotations.SerializedName

data class SeriesDto(
    @SerializedName("characters")
    val characters: ResourceCollectionDto?,
    @SerializedName("comics")
    val comics: ResourceCollectionDto?,
    @SerializedName("creators")
    val creators: ResourceCollectionDto?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("endYear")
    val endYear: Int?,
    @SerializedName("events")
    val events: ResourceCollectionDto?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("modified")
    val modified: String?,
    @SerializedName("next")
    val next: ResourceDto?,
    @SerializedName("previous")
    val previous: ResourceDto?,
    @SerializedName("rating")
    val rating: String?,
    @SerializedName("resourceURI")
    val resourceURI: String?,
    @SerializedName("startYear")
    val startYear: Int?,
    @SerializedName("stories")
    val stories: ResourceCollectionDto?,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailDto?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("urls")
    val urls: List<UrlDto>?
)