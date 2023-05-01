package com.red_velvet.marvel.data.model

import com.google.gson.annotations.SerializedName

data class StoryComicsResponse(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("modified")
    val modified: String? = "",
    @SerializedName("textObjects")
    val textObjects: List<TextObject>? = listOf(),
    @SerializedName("resourceURI")
    val resourceURI: String? = "",
    @SerializedName("urls")
    val urls: List<Url>? = listOf(),
    @SerializedName("series")
    val series: Content? = Content(),
    @SerializedName("variants")
    val variants: List<Variant>? = listOf(),
    @SerializedName("collections")
    val collections: List<Any>? = listOf(),
    @SerializedName("collectedIssues")
    val collectedIssues: List<CollectedIssue>? = listOf(),
    @SerializedName("dates")
    val dates: List<Date>? = listOf(),
    @SerializedName("prices")
    val prices: List<Price>? = listOf(),
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail? = Thumbnail(),
    @SerializedName("images")
    val images: List<Image>? = listOf(),
    @SerializedName("creators")
    val creators: Content? = Content(),
    @SerializedName("characters")
    val characters: Content? = Content(),
    @SerializedName("stories")
    val stories: Content? = Content(),
    @SerializedName("events")
    val events: Content? = Content(),

)
