package com.red_velvet.marvel.data.model

import com.google.gson.annotations.SerializedName
import com.red_velvet.marvel.data.model.*

data class CharactersByEventIdResponse(
    @SerializedName("comics")
    val comics: Content? = Content(),
    @SerializedName("description")
    val description: String?,
    @SerializedName("events")
    val events: Content? = Content(),
    @SerializedName("id")
    val id: Int?,
    @SerializedName("modified")
    val modified: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("resourceURI")
    val resourceURI: String?,
    @SerializedName("series")
    val series: Content? = Content(),
    @SerializedName("stories")
    val stories: Content? = Content(),
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail?,
    @SerializedName("urls")
    val urls: List<Url?>
)