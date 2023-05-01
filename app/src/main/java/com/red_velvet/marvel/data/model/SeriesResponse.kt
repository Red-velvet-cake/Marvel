package com.red_velvet.marvel.data.model


import com.google.gson.annotations.SerializedName


data class SeriesResponse(
    @SerializedName("characters")
    val characters: Content? = null,
    @SerializedName("comics")
    val comics: Content? = null,
    @SerializedName("creators")
    val creators: Content? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("endYear")
    val endYear: Int? = null,
    @SerializedName("events")
    val events: Content? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("modified")
    val modified: String? = null,
    @SerializedName("next")
    val next: Item? = null,
    @SerializedName("previous")
    val previous: Item? = null,
    @SerializedName("rating")
    val rating: String? = null,
    @SerializedName("resourceURI")
    val resourceURI: String? = null,
    @SerializedName("startYear")
    val startYear: Int? = null,
    @SerializedName("stories")
    val stories: Content? = null,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("urls")
    val urls: List<Url>? = null
)