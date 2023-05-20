package com.red_velvet.marvel.data.remote.dtos


import com.google.gson.annotations.SerializedName
import com.red_velvet.marvel.data.model.Resource
import com.red_velvet.marvel.data.model.ResourceCollection
import com.red_velvet.marvel.data.model.Thumbnail
import com.red_velvet.marvel.data.model.Url

data class SeriesDto(
    @SerializedName("characters")
    val characters: ResourceCollection?,
    @SerializedName("comics")
    val comics: ResourceCollection?,
    @SerializedName("creators")
    val creators: ResourceCollection?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("endYear")
    val endYear: Int?,
    @SerializedName("events")
    val events: ResourceCollection?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("modified")
    val modified: String?,
    @SerializedName("next")
    val next: Resource?,
    @SerializedName("previous")
    val previous: Resource?,
    @SerializedName("rating")
    val rating: String?,
    @SerializedName("resourceURI")
    val resourceURI: String?,
    @SerializedName("startYear")
    val startYear: Int?,
    @SerializedName("stories")
    val stories: ResourceCollection?,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("urls")
    val urls: List<Url>?
)