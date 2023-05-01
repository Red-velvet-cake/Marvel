package com.red_velvet.marvel.data.model


import com.google.gson.annotations.SerializedName


data class StoryResponse(
    @SerializedName("characters")
    val characters: Content? = Content(),
    @SerializedName("comics")
    val comics: Content? = Content(),
    @SerializedName("creators")
    val creators: Content? = Content(),
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("events")
    val events: Content? = Content(),
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("modified")
    val modified: String? = "",
    @SerializedName("originalIssue")
    val originalIssue: Item? = Item(),
    @SerializedName("resourceURI")
    val resourceURI: String? = "",
    @SerializedName("series")
    val series: Content? = Content(),
    @SerializedName("thumbnail")
    val thumbnail: Any? = Any(),
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("type")
    val type: String? = ""
)