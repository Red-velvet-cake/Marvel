package com.red_velvet.marvel.data.model


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("characters")
    val characters: Characters? = Characters(),
    @SerializedName("comics")
    val comics: Comics? = Comics(),
    @SerializedName("creators")
    val creators: Creators? = Creators(),
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("endYear")
    val endYear: Int? = 0,
    @SerializedName("events")
    val events: Events? = Events(),
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("modified")
    val modified: String? = "",
    @SerializedName("next")
    val next: Next? = Next(),
    @SerializedName("previous")
    val previous: Any? = Any(),
    @SerializedName("rating")
    val rating: String? = "",
    @SerializedName("resourceURI")
    val resourceURI: String? = "",
    @SerializedName("startYear")
    val startYear: Int? = 0,
    @SerializedName("stories")
    val stories: Stories? = Stories(),
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail? = Thumbnail(),
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("type")
    val type: String? = "",
    @SerializedName("urls")
    val urls: List<Url>? = listOf()
)