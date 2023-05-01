package com.red_velvet.marvel.data.model


import com.google.gson.annotations.SerializedName

data class GetAllSeries(
    @SerializedName("characters")
    val characters: Content? = Content(),
    @SerializedName("comics")
    val comics: Content? = Content(),
    @SerializedName("creators")
    val creators: Content? = Content(),
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("endYear")
    val endYear: Int? = 0,
    @SerializedName("events")
    val events: Content? = Content(),
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("modified")
    val modified: String? = "",
    @SerializedName("next")
    val next: Content? = Content(),
    @SerializedName("previous")
    val previous: Any? = Any(),
    @SerializedName("rating")
    val rating: String? = "",
    @SerializedName("resourceURI")
    val resourceURI: String? = "",
    @SerializedName("startYear")
    val startYear: Int? = 0,
    @SerializedName("stories")
    val stories: Content? = Content(),
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail? = Thumbnail(),
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("type")
    val type: String? = "",
    @SerializedName("urls")
    val urls: List<Url>? = listOf()
)