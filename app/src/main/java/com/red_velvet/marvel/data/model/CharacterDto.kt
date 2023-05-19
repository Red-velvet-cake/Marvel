package com.red_velvet.marvel.data.model

import com.google.gson.annotations.SerializedName
import com.red_velvet.marvel.data.model.*

data class CharacterDto(
    @SerializedName("comics")
    val comics: ResourceCollection? = ResourceCollection(),
    @SerializedName("description")
    val description: String?,
    @SerializedName("events")
    val events: ResourceCollection? = ResourceCollection(),
    @SerializedName("id")
    val id: Int?,
    @SerializedName("modified")
    val modified: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("resourceURI")
    val resourceURI: String?,
    @SerializedName("series")
    val series: ResourceCollection? = ResourceCollection(),
    @SerializedName("stories")
    val stories: ResourceCollection? = ResourceCollection(),
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail?,
    @SerializedName("urls")
    val urls: List<Url?>
)