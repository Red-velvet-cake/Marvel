package com.red_velvet.marvel.data.model.charsbycomicid


import com.google.gson.annotations.SerializedName
import com.red_velvet.marvel.data.model.Content
import com.red_velvet.marvel.data.model.Thumbnail
import com.red_velvet.marvel.data.model.Url

data class CharsByComicIdResponse(
    @SerializedName("comics")
    val comics: Content?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("events")
    val events: Content?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("modified")
    val modified: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("resourceURI")
    val resourceURI: String?,
    @SerializedName("series")
    val series: Content?,
    @SerializedName("stories")
    val stories: Content?,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail?,
    @SerializedName("urls")
    val urls: List<Url>?
)