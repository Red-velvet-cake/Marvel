package com.red_velvet.marvel.data.model.seriesDetails


import com.google.gson.annotations.SerializedName
import com.red_velvet.marvel.data.model.Content
import com.red_velvet.marvel.data.model.Thumbnail
import com.red_velvet.marvel.data.model.Url

data class SeriesDetailsResponse(
    @SerializedName("characters")
    val characters: Content?,
    @SerializedName("comics")
    val comics: Content?,
    @SerializedName("creators")
    val creators: Content?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("endYear")
    val endYear: Int?,
    @SerializedName("events")
    val events: Content?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("modified")
    val modified: String?,
    @SerializedName("next")
    val next: Next?,
    @SerializedName("previous")
    val previous: Any?,
    @SerializedName("rating")
    val rating: String?,
    @SerializedName("resourceURI")
    val resourceURI: String?,
    @SerializedName("startYear")
    val startYear: Int?,
    @SerializedName("stories")
    val stories: Content?,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("urls")
    val urls: List<Url>?
)